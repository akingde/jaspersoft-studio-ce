/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.UpdateCrosstabStyleCommand;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard.CrosstabStyleWizard;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStyle;
import com.jaspersoft.studio.components.preferences.ComponentsPreferencePageExtension;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

import net.sf.jasperreports.eclipse.ui.util.ExtendedMessageDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Action to open the Style dialog and use it to change the style of a Crosstab
 * 
 * @author Orlandin Marco
 *
 */
public class EditCrosstabStyleAction extends ACachedSelectionAction {
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.crosstab.action.EditStyle";  //$NON-NLS-1$
	
	public EditCrosstabStyleAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.CrosstabStyleWizard_actionName);
		setId(ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/crosstab-style-16.png")); //$NON-NLS-1$
	}

	/**
	 * The action is enable only if enabled if and only if the first element of the selection 
	 * is a CrosstabEditPart with inside an MCrosstab
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> crosstabs = editor.getSelectionCache().getSelectionModelForType(MCrosstab.class);
		return (crosstabs.size() ==1);
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		//Create the wizard
		CrosstabStyleWizard wizard = new CrosstabStyleWizard();
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard){
			//Ovverride this method to change the default text of the finish button with another text
			@Override
			protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
				Button button = super.createButton(parent, id, label, defaultButton);
				if (id == IDialogConstants.FINISH_ID) button.setText(Messages.EditCrosstabStyleAction_okButton);
				return button;
			}
		};
		if (dialog.open() == Dialog.OK){
			int response = getResponse();
			//response == 0 update the old styles, response == 1 create new styles, response == 2 cancel the operation
			if (response == 0 || response == 1){
				CrosstabStyle selectedStyle = wizard.getTableStyle();
				List<Object> crosstabs = editor.getSelectionCache().getSelectionModelForType(MCrosstab.class);
				MCrosstab tableModel = (MCrosstab)crosstabs.get(0);
				execute(changeStyleCommand(tableModel, selectedStyle,response == 0));
			} 
		}
	}
	
	/**
	 * Return the response on how to handle the old styles, first check if there is something store
	 * in the preferences and use the information to avoid to propose the question dialog if 
	 * there is a default behavior stored. Otherwise show the dialog and store the decision if the flag
	 * to remember it is checked.
	 * 
	 * @return 0 if the old styles should be update, 1 if new styles will be created, 2 if the operation is cancelled
	 */
	protected int getResponse(){
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String styleBehavior = store.getString(ComponentsPreferencePageExtension.BEHAVIOR_ON_STYLE_CHANGE);
		if (styleBehavior.equals(ComponentsPreferencePageExtension.BEHAVIOR_UPDATE_STYLES)){
			return 0;
		} else if (styleBehavior.equals(ComponentsPreferencePageExtension.BEHAVIOR_CREATE_STYLES)) {
			return 1;
		} else { 
			//no preferences, ask what to do
			Shell shell = UIUtils.getShell();
			ExtendedMessageDialog question = new ExtendedMessageDialog(shell, Messages.EditCrosstabStyleAction_questionTitle, null, 
																Messages.EditCrosstabStyleAction_questionText, MessageDialog.QUESTION, 
																	new String[]{Messages.EditCrosstabStyleAction_questionUpdate, 
																					Messages.EditCrosstabStyleAction_questionNewStyles, 
																						Messages.EditCrosstabStyleAction_questionCancel}, 
																		0, Messages.EditCrosstabStyleAction_remeberDecision);
			int response = question.open();
			//Store the decision if the flag is checked
			if (question.getCheckboxResult()){
				if (response == 0){
					store.setValue(ComponentsPreferencePageExtension.BEHAVIOR_ON_STYLE_CHANGE, ComponentsPreferencePageExtension.BEHAVIOR_UPDATE_STYLES);
				} else if (response == 1) {
					store.setValue(ComponentsPreferencePageExtension.BEHAVIOR_ON_STYLE_CHANGE, ComponentsPreferencePageExtension.BEHAVIOR_CREATE_STYLES);
				}
			}
			return response;
		}
	}

	/**
	 * 
	 * Return the command to change the crosstab style
	 * 
	 * @param crosstab the model of the crosstab
	 * @param newStyle the new CrosstabStyle defined by the user
	 * @param updateOldStyles true if the new styles will overwrite the old ones, false if the old ones will keep and 
	 * the new ones will have a different name
	 * @return the command to update the styles of the crosstab
	 */
	protected Command changeStyleCommand(MCrosstab crosstab, CrosstabStyle newStyle, boolean updateOldStyles) {
		JSSCompoundCommand command = new JSSCompoundCommand(crosstab);
		command.setDebugLabel(getText());
		UpdateCrosstabStyleCommand updateCommand = new UpdateCrosstabStyleCommand(crosstab, newStyle,updateOldStyles);
		command.add(updateCommand);
		return command;
	}
}
