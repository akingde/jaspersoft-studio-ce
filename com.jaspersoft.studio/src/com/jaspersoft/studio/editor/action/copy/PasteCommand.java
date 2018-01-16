/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.selection.SelectElementCommand;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.style.StyleTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.CloseSubeditorsCommand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.command.CopyDatasetCommand;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.command.CreateConditionalStyleCommand;

import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 *  Command used to paste in the editor a graphical object
 */
public class PasteCommand extends Command {
	
	protected Map<ANode, Command> list;
	
	protected IPastable parent;
	
	protected int createdNodes;
	
	protected Collection<ICopyable> copiedNodes;
	
	protected SelectElementCommand selectCommand = null;
	
	/**
	 * List of the graphical nodes created by the paste command
	 */
	private List<INode> createdElements;

	public PasteCommand(IPastable parent, Collection<ICopyable> copiedNodes) {
		super();
		this.parent = parent;
		createdNodes = 0;
		this.copiedNodes = copiedNodes;
	}

	@Override
	public boolean canExecute() {
		if (copiedNodes == null || copiedNodes.isEmpty()) {
			return false;
		}
		if (list == null) {
			Object obj = Clipboard.getDefault().getContents();
			if (obj == null)
				return false;
			list = new LinkedHashMap<ANode, Command>();
			if (obj instanceof AbstractPastableObject) {
				for (ICopyable copyableNode : copiedNodes) {
					if (copyableNode instanceof ANode){
						ANode node = (ANode) copyableNode;
						if (isPastableNode(node)) list.put(node, null);
					}
				}
			} else if (obj instanceof ANode && isPastableNode(obj))
				list.put((ANode) obj, null);
		}
		return !list.isEmpty();
	}

	@Override
	public void execute() {
		if (list == null && !canExecute())
			return;
		createdNodes = 0;
		createdElements = new ArrayList<INode>();
		List<JRChild> createdDesignElements = new ArrayList<JRChild>();
		for (ANode node : list.keySet()) {
			JSSCompoundCommand cmd = new JSSCompoundCommand(node);
			// create new Node put, clone into it
			try {
				Object value = node.getValue();
				if (value instanceof JRCloneable) {
					ANode n = node.getClass().newInstance();
					Rectangle rect = null;
					n.setJasperConfiguration(node.getJasperConfiguration());
					Object cloneObject = ((JRCloneable) value).clone();
					n.setValue(cloneObject);
					if (cloneObject instanceof JRDesignElement){
						createdDesignElements.add((JRDesignElement)cloneObject);
					}
					
					if (node.isCut() && node.getParent() != null) {
						ANode parent = (ANode) node.getParent();
						Command deleteCommand = OutlineTreeEditPartFactory.getDeleteCommand(parent, node);
						//Look it the style factory can resolve the command
						//FIXME: the style factory and the outline factory should not be binded so tightly
						//we should resolve the factory looking at the editor
						if (deleteCommand == null){
							deleteCommand = StyleTreeEditPartFactory.getDeleteCommand(parent, node);
						}
						if (deleteCommand != null){
							Command cmdd = new CloseSubeditorsCommand(deleteCommand, node);
							cmd.add(cmdd);
						}
					} else if (n instanceof MGraphicElement) {
						MGraphicElement mge = (MGraphicElement) n;
						JRDesignElement de = (JRDesignElement) mge.getValue();
						rect = mge.getBounds();
						rect.setLocation(de.getX(), de.getY());
					}
					if (node instanceof MDataset) {
						Command cmdc = new CopyDatasetCommand((MDataset) node, ((ANode) parent).getJasperDesign());
						cmd.add(cmdc);
						createdNodes++;
						list.put(node, cmd);
					} if (node instanceof MConditionalStyle) {
						MStyle newParent = (MStyle)parent;
						//If the current selected node is a conditional style take
						//its parent
						if (parent instanceof MConditionalStyle){
							newParent = (MStyle)((MConditionalStyle) parent).getParent();
						}
						Command cmdc = new CreateConditionalStyleCommand(newParent, (JRDesignConditionalStyle)n.getValue());
						cmd.add(cmdc);
						createdNodes++;
						list.put(node, cmd);
					}	else {
						//Look it the style factory can resolve the command
						//FIXME: the style factory and the outline factory should not be binded so tightly
						//we should resolve the factory looking at the editor
						FixPositionCommand fixPositionCommand = null;
						Command cmdc = OutlineTreeEditPartFactory.getCreateCommand((ANode) parent, n, rect, -1);
						if (cmdc == null){
							cmdc = StyleTreeEditPartFactory.getCreateCommand((ANode)parent, n, rect, -1);
						} else {
							//if it is a CreateElement command avoid to apply the default after a paste oepration
							if (cmdc instanceof CreateElementCommand) {
								CreateElementCommand createCommand = (CreateElementCommand)cmdc;
								createCommand.setApplyDefault(false);
							}
							if (n instanceof MGraphicElement){
								MGraphicElement mge = (MGraphicElement) n;
								fixPositionCommand = new FixPositionCommand(mge, node.getParent(), (ANode)parent);	
							}
						}	
						if (cmdc != null) {
							createdElements.add(n);
							cmd.add(cmdc);
							if (fixPositionCommand != null){
								cmd.add(fixPositionCommand);
							}
							createdNodes++;
						}

						if (!cmd.isEmpty())
							list.put(node, cmd);
					}
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		selectCommand = new SelectElementCommand(createdDesignElements);
		redo();
	}
 
	@Override
	public void redo() {
		for (Command cmd : list.values())
			if (cmd != null)
				cmd.execute();
		if (selectCommand != null){
			selectCommand.execute();
		}
	}

	public int getCreatedNodesNumber() {
		return createdNodes;
	}

	public IPastable getPasteParent() {
		return parent;
	}

	@Override
	public boolean canUndo() {
		return !(list.isEmpty());
	}

	@Override
	public void undo() {	
		//close the subeditor opened for the created nodes or their subchildrens
		for(INode createdElement : createdElements) {
			new CloseSubeditorsCommand(createdElement).execute();
		}
		
		Iterator<Command> it = list.values().iterator();
		while (it.hasNext()) {
			Command cmd = it.next();
			cmd.undo();
		}
		createdNodes = 0;
		if (selectCommand != null){
			selectCommand.undo();
		}
	}

	public boolean isPastableNode(Object node) {
		return node instanceof MDataset || (node instanceof ICopyable && ((ICopyable) node).isCopyable2(parent) != ICopyable.RESULT.NOT_COPYABLE);
	}
}
