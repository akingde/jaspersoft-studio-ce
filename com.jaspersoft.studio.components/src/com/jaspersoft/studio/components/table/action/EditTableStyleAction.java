/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.action;

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
import com.jaspersoft.studio.components.preferences.ComponentsPreferencePageExtension;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.table.command.UpdateStyleCommand;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableStyleWizard;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.eclipse.ui.util.ExtendedMessageDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Action to open the Style dialog and use it to change the style of a table
 * 
 * @author Orlandin Marco
 *
 */
public class EditTableStyleAction extends ACachedSelectionAction {
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.EditStyle";  //$NON-NLS-1$
	
	public EditTableStyleAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.EditStyleAction_actionName);
		setId(ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-style-16.png")); //$NON-NLS-1$
	}

	/**
	 * The action is enable only if enabled if and only if the first element of the selection 
	 * is a TableEditPart with inside an MTable
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> tables = editor.getSelectionCache().getSelectionModelForType(MTable.class);
		return (tables.size() ==1);
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		//Create the wizard
		TableStyleWizard wizard = new TableStyleWizard();
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard){
			//Ovverride this method to change the default text of the finish button with another text
			@Override
			protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
				Button button = super.createButton(parent, id, label, defaultButton);
				if (id == IDialogConstants.FINISH_ID) button.setText(Messages.EditStyleAction_okButton);
				return button;
			}
		};
		if (dialog.open() == Dialog.OK){
			List<Object> tables = editor.getSelectionCache().getSelectionModelForType(MTable.class);
			MTable tableModel = (MTable)tables.get(0);
			if (hasStyles(tableModel)){
				int response = getResponse();
				//response == 0 update the old styles, response == 1 create new styles, response == 2 cancel the operation
				if (response == 0 || response == 1){
					TableStyle selectedStyle = wizard.getTableStyle();
					execute(changeStyleCommand(tableModel, selectedStyle,response == 0));
				} 
			} else {
				//Don't prompt question if the table is not using styles
				TableStyle selectedStyle = wizard.getTableStyle();
				execute(changeStyleCommand(tableModel, selectedStyle, false));
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
			ExtendedMessageDialog question = new ExtendedMessageDialog(shell, Messages.EditStyleAction_dialogTitle, null, 
																Messages.EditStyleAction_dialogText, MessageDialog.QUESTION, 
																	new String[]{Messages.EditStyleAction_dialogUpdateButton, 
																					Messages.EditStyleAction_dialogNewButton, 
																						Messages.EditStyleAction_dialogCancelButton}, 
																		0, Messages.EditTableStyleAction_rememberDecision);
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
	 * Check if the passed table is using styles in its cells
	 * 
	 * @param tableModel a not null table model
	 * @return true if at least once cell of the table is using a style, false otherwise
	 */
	private boolean hasStyles(MTable tableModel){
		StandardTable table = tableModel.getStandardTable();
		JRPropertiesMap tableMap = tableModel.getValue().getPropertiesMap();
		JasperDesign jd = tableModel.getJasperDesign();
		JRDesignStyle[] currentStyles = ApplyTableStyleAction.getStylesFromTable(table, tableMap, jd);
		for(int i=0; i< currentStyles.length; i++){
			if (currentStyles[i] != null) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Return the command to change the table style
	 * 
	 * @param table the model of the table
	 * @param newStyle the new TableStyle defined by the user
	 * @param updateOldStyles true if the new styles will overwrite the old ones, false if the old ones will keep and 
	 * the new ones will have a different name
	 * @return the command to update the styles of the table
	 */
	protected Command changeStyleCommand(MTable table, TableStyle newStyle, boolean updateOldStyles) {
		JSSCompoundCommand command = new JSSCompoundCommand(table);
		command.setDebugLabel(getText());
		UpdateStyleCommand updateCommand = new UpdateStyleCommand(table, newStyle,updateOldStyles);
		command.add(updateCommand);
		return command;
	}
}
