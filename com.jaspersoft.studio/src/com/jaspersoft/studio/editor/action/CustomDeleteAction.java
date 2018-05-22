/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;


/**
 * This custom delete action override the original one provide by eclipse.
 * The only differences it that this one check if the list of commands have
 * a warning message. In this case display all the messages into only one 
 * dialog and ask a confirmation to proceed
 * 
 * @author Orlandin Marco
 *
 */
public class CustomDeleteAction extends DeleteAction{
	
	/**
	 * Key that is used in the extended data map of the request to abort an operation while generating 
	 * commands
	 */
	public static final String CANCEL_OPERATION_KEY = "cancelOperation";

	/**
	 * Key that is used in the extended data map of the request to mark that is generating the commands 
	 * for a calculateEnabled operation
	 */
	public static final String IS_CALCULATING_ENABLE = "calculatingEnable";

	public CustomDeleteAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	private ANode getModel(){
		List<?> objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			if (object.getModel() instanceof ANode) return (ANode)object.getModel();
		} 
		return null;
	}
	
	/**
	 * Check if child is a descendant of a possible parent
	 * 
	 * @param child the child to check
	 * @param possibileParent the parent checked
	 * @return true is possibleParent is an ancestor of child, false otherwise
	 */
	private boolean isPartDescendent(ANode child, ANode possibileParent){
		ANode parent = child.getParent();
		while(parent != null){
			if (parent == possibileParent){
				return true;
			}
			parent = parent.getParent();
		}
		return false;
	}
	
	/**
	 * Check if a single selected part in the list is a descendant of another part 
	 * placed before it. If it so the second part is always placed immediately before the first
	 * 
	 * @param selectedParts the current list of part to delete
	 * @return true if a part was moved by this method, doesn't assure there aren't other part to move.
	 * False if now parts were moved so it is already in the correct order
	 */
	protected boolean reorderPart(ArrayList<EditPart> selectedParts){
		if (selectedParts.size() <= 1) return false;
		for(int i = 0; i< selectedParts.size(); i++){
			for(int j = i+ 1; j<selectedParts.size(); j++){
				ANode model1 = (ANode)selectedParts.get(i).getModel();
				ANode model2 = (ANode)selectedParts.get(j).getModel();	
				if (isPartDescendent(model2, model1)){
					EditPart part = selectedParts.remove(j);
					selectedParts.add(i, part);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Get the set of part to create the delete commands.
	 * The part are reordered to have the child of other elements
	 * in the selection set delete first their parent
	 * 
	 * @return ordered list of part to delete
	 */
	protected List<EditPart> getOrderedSelectedObjects() {
		List<?> selectedObject = super.getSelectedObjects();
		ArrayList<EditPart> selectedParts = new ArrayList<EditPart>();
		for(Object obj : selectedObject){
			selectedParts.add((EditPart)obj);
		}
		//Reoder the parts until all the children are before the parent
		while(reorderPart(selectedParts));
		return selectedParts;
	}
	
	/**
	 * Create a command to remove the selected objects. It sets the key of the request to abort
	 * the operation and for the calculating enabled
	 * 
	 * @param objects
	 *            The objects to be deleted.
	 * @param isCalculatingEnabled true if the delete commands are generated for the calculateEnabled
	 * 			false if they are generated for the execution
	 * @return The command to remove the selected objects.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CompoundCommand createDeleteCommand(List objects, boolean isCalculatingEnabled) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
		deleteReq.setEditParts(objects);
		
		deleteReq.getExtendedData().put(CANCEL_OPERATION_KEY, false);
		deleteReq.getExtendedData().put(IS_CALCULATING_ENABLE, isCalculatingEnabled);
		
		CompoundCommand compoundCmd = new CompoundCommand();
		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			Command cmd = object.getCommand(deleteReq);
			if (Boolean.TRUE.equals(deleteReq.getExtendedData().get(CANCEL_OPERATION_KEY))) {
				return null;
			} else if (cmd != null) {
				compoundCmd.add(cmd);
			}
		}
		return compoundCmd;
	}
	
	
	/**
	 * Returns <code>true</code> if the selected objects can be deleted. Returns
	 * <code>false</code> if there are no objects selected or the selected
	 * objects are not {@link EditPart}s.
	 * 
	 * @return <code>true</code> if the command should be enabled
	 */
	protected boolean calculateEnabled() {
		Command cmd = createDeleteCommand(getSelectedObjects(), true);
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}
	
	@Override
	public void run() {
		CompoundCommand deleteCommandds = createDeleteCommand(getOrderedSelectedObjects(), false);
		if (deleteCommandds != null) {
			ANode model = getModel();
			
			//it is really important deselect all because otherwise GEF will trigger a selectionChange event
			//for every part it delete (because a deleted part is first deselected), with an huge impact
			//on performances with big selections
			EditPart treeEditPart = model.getTreeEditPart();
			if (treeEditPart != null) {
				treeEditPart.getViewer().deselectAll();
			}
			
			JSSCompoundCommand compCommand = new JSSCompoundCommand(deleteCommandds, model);
			StringBuilder messages = new StringBuilder();
			messages.append(Messages.CustomDeleteAction_messageListStart+"\n"); //$NON-NLS-1$
			boolean messageFound = false;
			for(Object oCommand : compCommand.getCommands()){
				if (oCommand instanceof MessageProviderCommand){
					MessageProviderCommand messageCommand = (MessageProviderCommand)oCommand;
					CommandMessage message = messageCommand.getMessage();
					if (message != null) {
						messages.append(message.getMessage()+"\n"); //$NON-NLS-1$
						messageFound = true;
					}
				}
			}
			messages.append(Messages.CustomDeleteAction_messageListEnd);
			if (messageFound){
				if (UIUtils.showConfirmation(Messages.ADatasetObjectDeleteCommand_confirmationtitle,messages.toString())){
					execute(compCommand);
					setSelection(StructuredSelection.EMPTY);
				}
			} else {
				execute(compCommand);
				setSelection(StructuredSelection.EMPTY);
			}
		}
	}
	
}
