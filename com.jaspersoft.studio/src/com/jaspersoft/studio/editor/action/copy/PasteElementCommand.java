/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.CloseSubeditorsCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.command.CopyDatasetCommand;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.command.CreateConditionalStyleCommand;

/**
 *  Command used to paste the copied models. The paste 
 *  is done inside a specific parent.
 *  
 *  @author Orlandin Marco 
 */
public class PasteElementCommand extends Command {
	
	/**
	 * The node that will copied to be pasted
	 */
	protected ANode node;
	
	/**
	 * The command executed to paste the elements
	 */
	protected JSSCompoundCommand cmd;
	
	/**
	 * The parent where the node will be pasted
	 */
	protected ANode parent;
	
	/**
	 * The node that will be pasted
	 */
	private ANode createdElement;

	/**
	 * Create the command 
	 * 
	 * @param parent The parent where the node will be pasted
	 * @param node The node that will copied to be pasted, must be not null
	 */
	public PasteElementCommand(ANode parent, ANode node) {
		super();
		this.parent = parent;
		this.node = node;
	}

	@Override
	public boolean canExecute() {
		return node instanceof MDataset || (node instanceof ICopyable && ((ICopyable)node).isCopyable2(parent) != ICopyable.RESULT.NOT_COPYABLE);
	}

	@Override
	public void execute() {
		cmd = new JSSCompoundCommand(node);
		createdElement = null;
		// create new Node put, clone into it
		try {
			Object value = node.getValue();
			if (value instanceof JRCloneable) {
				ANode n = node.getClass().newInstance();
				Rectangle rect = null;
				n.setJasperConfiguration(node.getJasperConfiguration());
				n.setValue(((JRCloneable) value).clone());
				
				if (node.isCut() && node.getParent() != null) {
					ANode parent = (ANode) node.getParent();
					Command deleteCommand = OutlineTreeEditPartFactory.getDeleteCommand(parent, node);
					if (deleteCommand != null){
						Command cmdd = new CloseSubeditorsCommand(deleteCommand, node);
						cmd.add(cmdd);
					}
				} else if (n instanceof MGraphicElement) {
					MGraphicElement mge = (MGraphicElement) n;
					JRDesignElement de = (JRDesignElement) mge.getValue();
					if (parent == node.getParent()) {
						de.setX(de.getX() + 5);
						de.setY(de.getY() + 5);
					}
					rect = mge.getBounds();
					rect.setLocation(de.getX(), de.getY());
				}
				if (node instanceof MDataset) {
					Command cmdc = new CopyDatasetCommand((MDataset) node, ((ANode) parent).getJasperDesign());
					cmd.add(cmdc);
				} if (node instanceof MConditionalStyle) {
					MStyle newParent = (MStyle)parent;
					//If the current selected node is a conditional style take
					//its parent
					if (parent instanceof MConditionalStyle){
						newParent = (MStyle)((MConditionalStyle) parent).getParent();
					}
					Command cmdc = new CreateConditionalStyleCommand(newParent, (JRDesignConditionalStyle)n.getValue());
					cmd.add(cmdc);
				}	else {
					// create command
					Command cmdc = OutlineTreeEditPartFactory.getCreateCommand((ANode) parent, n, rect, -1);
					if (cmdc != null) {
						createdElement = n;
						cmd.add(cmdc);
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		redo();
	}

	@Override
	public void redo() {
		if (cmd != null)
			cmd.execute();
	}

	@Override
	public boolean canUndo() {
		return (cmd != null && cmd.canUndo());
	}

	@Override
	public void undo() {	
		//close the subeditor opened for the created nodes or their subchildrens
		new CloseSubeditorsCommand(createdElement).execute();
		if (cmd != null){
			cmd.undo();
			cmd = null;
		}
	}

}
