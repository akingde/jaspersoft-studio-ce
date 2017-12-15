/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 * Command used to change the layout of a specific node, it handle also the deactivation of the old 
 * layout and the activation of the new one
 * 
 * @author Orlandin Marco
 *
 */
public class ChangeLayoutCommand extends Command {
	
	/**
	 * The node where the layout will be changed
	 */
	private APropertyNode targetNode;
	
	/**
	 * Name of the class of the new layout 
	 */
	private String newLayout;
	
	/**
	 * Name of the class of the old layout on the node, used for the undo
	 */
	private String oldLayout;

	/**
	 * Additional commands executed for the deactivation of the old layout and the reactivation of the new one
	 */
	private List<Command> exectueAdditionalCommands;
	
	/**
	 * Crate the command to change the layout
	 * 
	 * @param targetNode the target node of the layout change
	 * @param newLayout the new layout
	 */
	public ChangeLayoutCommand(APropertyNode targetNode, ILayout newLayout){
		this.targetNode = targetNode;
		this.newLayout = newLayout.getClass().getName();
	}

	@Override
	public void execute() {
		exectueAdditionalCommands = new ArrayList<Command>();
		JRPropertiesHolder nodePropertiesHolder = LayoutManager.getPropertyHolder(targetNode);
		if (nodePropertiesHolder != null && !ModelUtils.safeEquals(newLayout, oldLayout)){
			JRPropertiesMap pmap = (JRPropertiesMap) targetNode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
			pmap = (JRPropertiesMap) pmap.clone();
			oldLayout = pmap.getProperty(ILayout.KEY);
			pmap.setProperty(ILayout.KEY, newLayout);
			targetNode.setPropertyValue(MGraphicElement.PROPERTY_MAP, pmap);
			if (newLayout != null){
				Command activationCmd = LayoutManager.getLayout(newLayout).activate(targetNode);
				if (activationCmd != null){
					exectueAdditionalCommands.add(activationCmd);
				}
			}
			if (oldLayout != null){
				Command deactivationCmd = LayoutManager.getLayout(oldLayout).deactivate(targetNode);
				if (deactivationCmd != null){
					exectueAdditionalCommands.add(deactivationCmd);
				}
			}
			for(Command cmd : exectueAdditionalCommands){
				cmd.execute();
			}
		}
	}
	
	/**
	 * The undo will undo also the acivation/deactivation command
	 */
	@Override
	public void undo() {
		JRPropertiesHolder nodePropertiesHolder = LayoutManager.getPropertyHolder(targetNode);
		if (nodePropertiesHolder != null && !ModelUtils.safeEquals(newLayout, oldLayout)){
			JRPropertiesMap pmap = nodePropertiesHolder.getPropertiesMap();
			pmap.setProperty(ILayout.KEY, oldLayout);
			for(Command cmd : exectueAdditionalCommands){
				cmd.undo();
			}
			//FIXME: this will trigger the refresh, however there could be a more efficient way to do this
			targetNode.setPropertyValue(MGraphicElement.PROPERTY_MAP, pmap.clone());
		}
	}
}
