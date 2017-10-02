/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.LazyLayoutCommand;
import com.jaspersoft.studio.editor.layout.spreadsheet.SpreadsheetLayout;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.SetPropertyValueCommand;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 * Action bind elements together to be used with the spreadsheet layout
 * The elements must be on different containers and at least one of 
 * the selected elements must be in the detail.
 * 
 * @author Orlandin Marco
 * 
 */
public class BindElementsAction extends ACachedSelectionAction {
  
  public static final String ID = "BindElements"; //$NON-NLS-1$
	
	public BindElementsAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.BindElementsAction_name);
		setId(ID);
		setEnabled(false);
	}
	
	/**
	 * Do the validity check on the is enabled, doing this the enablement is validated on 
	 * the open of the menu and not on selection. This action requires at least two selected
	 * elements with no common parents
	 */
	@Override
	public boolean isEnabled() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		if (mGraphElements.size() < 2) return false;
		HashSet<ANode> foundAncestor = new HashSet<ANode>();
		for(Object rawNode : mGraphElements){
			ANode currentNode = (ANode)rawNode;
			ANode currentParent = currentNode.getParent();
			
			JRPropertiesHolder parentHolder = LayoutManager.getPropertyHolder(currentParent);
			
			if (parentHolder != null) {
				String parentLayout = parentHolder.getPropertiesMap().getProperty(ILayout.KEY);
				if (!SpreadsheetLayout.class.getName().equals(parentLayout)) {
					return false;
				}
			}
			
			while(currentParent != null && !(currentParent instanceof MReport)){
				if (foundAncestor.contains(currentParent)){
					return false;
				} else {
					foundAncestor.add(currentParent);
				}
				currentParent = currentParent.getParent();
			}
		}
		return true;
	}

	
	@Override
	public void run() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		MGraphicElement firstElement = (MGraphicElement)mGraphElements.get(0);
		JSSCompoundCommand cmd = new JSSCompoundCommand(firstElement);
		String uuid = UUID.randomUUID().toString();
		for(Object rawNode : mGraphElements){
			MGraphicElement element = (MGraphicElement)rawNode;
			JRPropertiesMap map = element.getValue().getPropertiesMap();
			SetPropertyValueCommand setCmd = new SetPropertyValueCommand(map, SpreadsheetLayout.PROPERTY_ID, uuid);
			cmd.add(setCmd);
			
			
			JRPropertiesHolder parentHolder = LayoutManager.getPropertyHolder(element.getParent());
			if (parentHolder != null){
				String layout = parentHolder.getPropertiesMap().getProperty(ILayout.KEY);
				if (!SpreadsheetLayout.class.getName().equals(layout)){
					SetPropertyValueCommand setLayoutCmd = new SetPropertyValueCommand(parentHolder.getPropertiesMap(), ILayout.KEY, SpreadsheetLayout.class.getName());
					cmd.add(setLayoutCmd);
				}
			}
		}
		Command layoutCommand = new LazyLayoutCommand(firstElement.getParent());
		cmd.add(layoutCommand);
		SpreadsheetLayout.refreshCache(firstElement.getJasperDesign());
		command = cmd;
		fresh = true;
		super.run();
	}

	@Override
	protected Command createCommand() {
		return null;
	}
}
