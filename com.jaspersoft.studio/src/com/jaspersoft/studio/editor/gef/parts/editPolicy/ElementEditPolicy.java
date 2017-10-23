/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;

import com.jaspersoft.studio.editor.action.CustomDeleteAction;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.spreadsheet.SpreadsheetLayout;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.ui.util.ExtendedMessageDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * The edit policy for the delete of elements, it hardcode the code to handle
 * the delete of elements contained into a spreadsheet layouted container. It will propose
 * in this case addtional action
 */
public class ElementEditPolicy extends ComponentEditPolicy {
	
	/**
	 * Key that is used in the extended data map of the request to exchange informations with the other instances of the policies
	 * about the generated commands
	 */
	public static final String DELETED_MODELS_KEY = "deletedModels";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command createDeleteCommand(GroupRequest request) {
		if (getHost() != null && getHost().getModel() != null) {
			ANode node = (ANode) getHost().getModel();
			ANode parent = node.getParent();
			if (parent != null) {
				Object isCalculatingEnablement = request.getExtendedData().get(CustomDeleteAction.IS_CALCULATING_ENABLE);
				if (Boolean.FALSE.equals(isCalculatingEnablement) && SpreadsheetLayout.class.equals(LayoutManager.getContainerLayout((ANode)parent))){
					return handleSpreadsheetDelete(node, parent, request);
				} else {
					return OutlineTreeEditPartFactory.getDeleteCommand(parent, node);
				}
			}
		}
		return null;
	}
	
	/**
	 * Return from the extended data map of the request the map of the nodes deleted with a flag that mark if for them a delete command
	 * has been generated. If this map is not present it is created, since the request is shared between the policies this information
	 * is used to know at any point if a delete command for a specific node has been generated
	 * 
	 * @param request the request the request 
	 * @return a map where the key is a JRElement select on which a delete has been requested, the value is a boolean set to true if
	 * the command to delete the JRElement has been generated, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	protected HashMap<Object, Boolean> getDeletedNodes(GroupRequest request) {
		HashMap<Object, Boolean> deletedElements = (HashMap<Object, Boolean>)request.getExtendedData().get(DELETED_MODELS_KEY);
		if (deletedElements == null) {
			deletedElements = new HashMap<Object, Boolean>();
			for (Object object : request.getEditParts()) {
				EditPart part = (EditPart) object;
				deletedElements.put(((ANode)part.getModel()).getValue(), Boolean.FALSE);
			}
			request.getExtendedData().put(DELETED_MODELS_KEY, deletedElements);
		}
		return deletedElements;
	}

	protected boolean areConnectedElementAlreadyDeleted(List<JRDesignElement> connectedElements, HashMap<Object, Boolean> deletedElements) {
		for(Object connectedElement : connectedElements) {
			Boolean isDeleted = deletedElements.get(connectedElement);
			if (isDeleted == null || !isDeleted) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the current selection is wiping the an entire column
	 * 
	 * @param connectedElements the elements of the column
	 * @param deletedElements the element selected to be deleted
	 * @return true if all the element of the column are selected to be delete, false otherwise
	 */
	protected boolean isWipingEntireColumn(List<JRDesignElement> connectedElements, HashMap<Object, Boolean> deletedElements) {
		for(Object connectedElement : connectedElements) {
			if (!deletedElements.containsKey(connectedElement)) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected Command handleSpreadsheetDelete(ANode target, ANode parent, GroupRequest request) {
		List<Command> deleteCommands = new ArrayList<Command>();
		HashMap<Object, Boolean> deletedElements = getDeletedNodes(request);
		HashSet<Object> connectedParents = new HashSet<Object>();
		
		//check if we need to generate the command to delete the selected node
		Boolean hasGeneratedCommand = deletedElements.get(target.getValue());
		if (hasGeneratedCommand == null || !hasGeneratedCommand) {
			deletedElements.put(target.getValue(), true);
			connectedParents.add(parent.getValue());
			deleteCommands.add(OutlineTreeEditPartFactory.getDeleteCommand(parent, target));
		}
		
		//check if we need to generate the commands for the elements connected to the selected node
		JRPropertiesHolder targetHolder = LayoutManager.getPropertyHolder(target);
		String deleteElementUUID = targetHolder.getPropertiesMap().getProperty(SpreadsheetLayout.PROPERTY_ID);
		if (deleteElementUUID != null){
			JasperDesign jd = parent.getJasperDesign();
			List<JRDesignElement> connectedElements = SpreadsheetLayout.getElementsForUUID(deleteElementUUID, jd, ModelUtils.getReport(parent));
			//execute this code only if an element of the column is outside the selection or inside but not yet marked for a delete
			if (connectedElements != null && !connectedElements.isEmpty() && !areConnectedElementAlreadyDeleted(connectedElements, deletedElements)){
				//open the message dialog only if at least an element of the column is outside the selection
				int messageResult = 1;
				if (!isWipingEntireColumn(connectedElements, deletedElements)) {
					messageResult = openMessageDialog(parent);
				}
				if (messageResult == 2 || messageResult == SWT.DEFAULT) {
					//cancel the operation
					request.getExtendedData().put(CustomDeleteAction.CANCEL_OPERATION_KEY, Boolean.TRUE);
					return null;
				} else {
					//proceed with the delete
					boolean deleteColumn = messageResult == 0;
					if (deleteColumn){
						for(JRDesignElement connectedElement : connectedElements){
							if (connectedElement != target.getValue()){
								ANode node = SelectionHelper.getNode(connectedElement);
								//check that the node is not already one among the ones that will be deleted
								if (node != null && node.getParent() != null && !deletedElements.containsKey(node.getValue())){
									JRPropertiesHolder parentHolder = (JRPropertiesHolder)node.getParent().getValue();
									String parentLayout = parentHolder.getPropertiesMap().getProperty(ILayout.KEY);
									if (SpreadsheetLayout.class.getName().equals(parentLayout)){
										Command deleteCmd =  OutlineTreeEditPartFactory.getDeleteCommand(node.getParent(), node);
										if (deleteCmd != null){	
											deletedElements.put(node.getValue(), true);
											deleteCommands.add(deleteCmd);
											connectedParents.add(node.getParent().getValue());
										}
									}
								}
							}
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
			result.add(LayoutManager.createRelayoutCommand(parent));
			return result;
		}
		return null;
	}
	
	/**
	 * When deleting something inside a spreasheet layouted container propose the message to delete the whole column,
	 * the single element or abort the operation 
	 * 
	 * @param deletedParent the deleted node, used because if it is a descendant of the detail band the message is different
	 * @return 0 to delete completely the column, 1 for only the selected element 2 or -1 to abort the operation
	 */
	protected int openMessageDialog(ANode deletedParent) {
		boolean isDetailBand = false;
		ANode currentParent = deletedParent;
		while (currentParent != null) {
			if (currentParent instanceof MBand) {
				MBand band = (MBand)currentParent;
				if (band.getBandType() == BandTypeEnum.DETAIL) {
					isDetailBand = true;
				}
				break;
			} else {
				currentParent = currentParent.getParent();
			}
		}
		
		ExtendedMessageDialog dialog = null;
		if (isDetailBand) {
			dialog = new ExtendedMessageDialog(UIUtils.getShell(), Messages.SpreadsheetPostDelete_title ,null, Messages.SpreadsheetPostDelete_messageDetail , MessageDialog.WARNING, new String[] { Messages.SpreadsheetPostDelete_optionDeleteColumn, Messages.SpreadsheetPostDelete_optionDeleteSelected, "Cancel"}, 2, null);
		} else {
			dialog = new ExtendedMessageDialog(UIUtils.getShell(), Messages.SpreadsheetPostDelete_title ,null, Messages.SpreadsheetPostDelete_message, MessageDialog.QUESTION, new String[] { Messages.SpreadsheetPostDelete_optionDeleteColumn, Messages.SpreadsheetPostDelete_optionDeleteSelected, "Cancel"}, 2, null);
		}
		return dialog.open();
	}
}
