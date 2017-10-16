/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.MGraphicElement;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteElementCommand extends Command {
	
	protected JasperDesign jDesign;
	/** The jr group. */
	private JRElementGroup jrGroup;

	/**  
	 * The jr element.
	 */
	private JRDesignElement jrElement;
	
	/**
	 * The node of the jr element
	 */
	private ANode nodeElement;
	
	/**
	 * The node of the parent of the jrelement
	 */
	private ANode nodeParent;

	/** The element position. */
	private int elementPosition = 0;
	
	private JRPropertiesHolder[] pholder;
	
	/**
	 * For the post delete commands also a JSScompound command is used,
	 * to disable useless refresh operation 
	 */
	private JSSCompoundCommand commands;
	
	private LayoutCommand lCmd;

	/**
	 * Instantiates a new delete element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteElementCommand(MGraphicElement srcNode) {
		super();
		jrElement = (JRDesignElement) srcNode.getValue();
		jrGroup = jrElement.getElementGroup();
		jDesign = srcNode.getJasperDesign();
		nodeElement = srcNode;
		nodeParent = srcNode.getParent();
		if (nodeParent instanceof IContainerLayout){
			pholder = ((IContainerLayout) nodeParent).getPropertyHolder();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrGroup != null && jrGroup.getChildren() != null) {
			elementPosition = jrGroup.getChildren().indexOf(jrElement);
			if (elementPosition != -1){
				//remove the elements
				if (jrGroup instanceof JRDesignElementGroup) {
					((JRDesignElementGroup) jrGroup).removeElement(jrElement);
				} else if (jrGroup instanceof JRDesignFrame) {
					((JRDesignFrame) jrGroup).removeElement(jrElement);
				}
				
				//execute the post commands
				if (commands == null){
					List<Command> commandsList = JaspersoftStudioPlugin.getPostDeleteManager().postDelete(nodeElement, nodeParent);
					ANode startingNode = null;
					//Copy the contributed delete descriptors inside the jss compound command
					if (commandsList != null && !commandsList.isEmpty()){
						commands = new JSSCompoundCommand(JSSCompoundCommand.getMainNode(startingNode));
						for(Command c : commandsList){
							commands.add(c);
						}
					}
				}
				if (commands != null){
					commands.execute();
				}
				
				//do the layoutof the container
				if (jrGroup instanceof JRPropertiesHolder) {
					String uuid = null;
					if (jrGroup instanceof JRBaseElement)
						uuid = ((JRBaseElement) jrGroup).getUUID().toString();
					Dimension d = new Dimension(0, 0);
					if (jrGroup instanceof JRCommonElement) {
						//JRCommonElement jce = (JRCommonElement) jrGroup;
						// Commented for back-compatibility in 3.6.
						// Replaced with the following line.
						// d.setSize(jce.getWidth(), jce.getHeight());
						//d.setSize(new Dimension(jce.getWidth(), jce.getHeight()));
						d = LayoutManager.getPaddedSize((JRCommonElement)jrGroup);
					}
					if (jrGroup instanceof JRDesignBand) {
						int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
						// Commented for back-compatibility in 3.6.
						// Replaced with the following line.
						// d.setSize(w, ((JRDesignBand) jrGroup).getHeight());
						d.setSize(new Dimension(w, ((JRDesignBand) jrGroup).getHeight()));
					}
					if (lCmd == null) {
						ILayout layout = LayoutManager.getLayout(pholder, jDesign, uuid);
						lCmd = new LayoutCommand(jDesign, jrGroup, layout, d);
						lCmd.execute();
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (elementPosition == -1) return true;
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (elementPosition != -1){
			if (commands != null){
				commands.undo();
			}
			if (lCmd != null)
				lCmd.undo();
			if (jrGroup != null && jrGroup.getChildren() != null) {
				if (jrGroup instanceof JRDesignElementGroup) {
					if (elementPosition > ((JRDesignElementGroup) jrGroup).getChildren().size())
						((JRDesignElementGroup) jrGroup).addElement(jrElement);
					else
						((JRDesignElementGroup) jrGroup).addElement(elementPosition, jrElement);
				} else if (jrGroup instanceof JRDesignFrame) {
					if (elementPosition > ((JRDesignFrame) jrGroup).getChildren().size())
						((JRDesignFrame) jrGroup).addElement(jrElement);
					else
						((JRDesignFrame) jrGroup).addElement(elementPosition, jrElement);
				}
			}
		}
	}
}
