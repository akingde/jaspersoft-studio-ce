/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout.spreadsheet.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.spreadsheet.SpreadsheetLayout;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.IPostDelete;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Post delete action defined for the child of a container using a {@link SpreadsheetLayout}.
 * This will search a node connected to the delete one and in case it is found it delete also that.
 */
public class SpreadsheetPostDelete implements IPostDelete {

	@Override
	public Command postDelete(ANode target, ANode parent) {
		JRPropertiesHolder holder = LayoutManager.getPropertyHolder(parent);
		if (holder != null){
			String layout = holder.getPropertiesMap().getProperty(ILayout.KEY);
			if (ModelUtils.safeEquals(layout, SpreadsheetLayout.class.getName())){
				JRPropertiesHolder targetHolder = LayoutManager.getPropertyHolder(target);
				String deleteElementUUID = targetHolder.getPropertiesMap().getProperty(SpreadsheetLayout.PROPERTY_ID);
				
				if (deleteElementUUID != null){
					JasperDesign jd = parent.getJasperDesign();
					List<JRDesignElement> connectedElements = SpreadsheetLayout.getElementsForUUID(deleteElementUUID, jd, ModelUtils.getReport(parent));
					if (connectedElements != null && !connectedElements.isEmpty()){
						boolean shouldDelete = UIUtils.showConfirmation("Delete Confirmation", "Do you want to delete the elements associated to this one?");
						
						if (shouldDelete){
							List<Command> deleteCommands = new ArrayList<Command>();
							HashSet<Object> connectedParents = new HashSet<Object>();
							for(JRDesignElement connectedElement : connectedElements){
								if (connectedElement != target.getValue()){
									ANode node = SelectionHelper.getNode(connectedElement);
									if (node != null && node.getParent() != null){
										JRPropertiesHolder parentHolder = (JRPropertiesHolder)node.getParent().getValue();
										String parentLayout = parentHolder.getPropertiesMap().getProperty(ILayout.KEY);
										if (SpreadsheetLayout.class.getName().equals(parentLayout)){
											Command deleteCmd =  OutlineTreeEditPartFactory.getDeleteCommand(node.getParent(), node);
											if (deleteCmd != null){	
												deleteCommands.add(deleteCmd);
												connectedParents.add(node.getParent().getValue());
											}
										}
									}
								}
							}
							if (!deleteCommands.isEmpty()){
								CompoundCommand result = new CompoundCommand();
								for(Object connectedParent : connectedParents){
									JRPropertiesHolder parentHolder = (JRPropertiesHolder)connectedParent;
									SetPropertyValueCommand removeLayoutComd = new SetPropertyValueCommand(parentHolder.getPropertiesMap(), ILayout.KEY, null);
									result.add(removeLayoutComd);
								}
								for(Command deleteCommand : deleteCommands){
									result.add(deleteCommand);
								}
								for(Object connectedParent : connectedParents){
									JRPropertiesHolder parentHolder = (JRPropertiesHolder)connectedParent;
									SetPropertyValueCommand setLayoutComd = new SetPropertyValueCommand(parentHolder.getPropertiesMap(), ILayout.KEY, SpreadsheetLayout.class.getName());
									result.add(setLayoutComd);
								}
								return result;
							}
						}
					}
				}
			}
		}
		return null;
	}

}
