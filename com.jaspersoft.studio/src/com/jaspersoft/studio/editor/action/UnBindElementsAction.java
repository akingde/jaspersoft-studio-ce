/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.spreadsheet.SpreadsheetLayout;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetPropertyValueCommand;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 * Action to remove the association between elements in the spreadsheet layout
 * 
 * @author Orlandin Marco
 * 
 */
public class UnBindElementsAction extends ACachedSelectionAction {
  
  public static final String ID = "UnBindElements"; //$NON-NLS-1$
	
	public UnBindElementsAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.UnBindElementsAction_name);
		setId(ID);
		setEnabled(false);
	}
	
	@Override
	public boolean isEnabled() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		if (mGraphElements.isEmpty()) return false;
		for(Object rawNode : mGraphElements){
			ANode currentNode = (ANode)rawNode;
			
			ANode parentNode = currentNode.getParent();
			JRPropertiesHolder parentHolder = LayoutManager.getPropertyHolder(parentNode);
	
			if (parentHolder != null) {
				String parentLayout = parentHolder.getPropertiesMap().getProperty(ILayout.KEY);
				if (!SpreadsheetLayout.class.getName().equals(parentLayout)) {
					return false;
				}
			}
			
			JRPropertiesHolder holder = LayoutManager.getPropertyHolder(currentNode);
			if (!holder.getPropertiesMap().containsProperty(SpreadsheetLayout.PROPERTY_ID)){
				return false;
			}
		}
		return true;
	}

	
	@Override
	public void run() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		MGraphicElement firstElement = (MGraphicElement)mGraphElements.get(0);
		JSSCompoundCommand cmd = new JSSCompoundCommand(firstElement);
		for(Object rawNode : mGraphElements){
			MGraphicElement element = (MGraphicElement)rawNode;
			JRPropertiesMap map = element.getValue().getPropertiesMap();
			SetPropertyValueCommand setCmd = new SetPropertyValueCommand(map, SpreadsheetLayout.PROPERTY_ID, null);
			cmd.add(setCmd);
		}
		Command layoutCommand = LayoutManager.createRelayoutCommand(firstElement.getParent());
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
