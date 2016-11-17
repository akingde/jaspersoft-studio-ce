/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.action;

import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.dialog.ApplyCrosstabStyleAction;
import com.jaspersoft.studio.components.preferences.ComponentsPreferencePageExtension;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.command.ForceRefreshCommand;
import com.jaspersoft.studio.model.style.command.DeleteStyleCommand;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.eclipse.ui.util.ExtendedMessageDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Action to delete all the styles from a crosstab element
 * 
 * @author Orlandin Marco
 *
 */
public class RemoveCrosstabStylesAction extends ACachedSelectionAction {
	
	/**
	 * boolean flag to specify if the style element should be deleted or only be removed from the table
	 */
	private boolean deleteStyles = false;
	
	/**
	 * hashmap used internally to keep trace of the deleted styles
	 */
	private HashSet<String> deletedStyles;
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.RemoveCrosstabStyles";  //$NON-NLS-1$
	
	public RemoveCrosstabStylesAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.RemoveStylesAction_actionTitle);
		setId(RemoveCrosstabStylesAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/crosstab-style-remove-16.png")); //$NON-NLS-1$
	}

	/**
	 * The action is enable only if enabled if and only if one of the edit part of the selection 
	 * has as model type an MCrosstab
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> crosstabs = editor.getSelectionCache().getSelectionModelForType(MCrosstab.class);
		return crosstabs.size() > 0;
	}
	
	/**
	 * Return a list of the selected edit parts that has a model of type MCrosstab
	 * 
	 * @return a not null list of edit part with an MCrosstab as model
	 */
	private List<EditPart> getSelectedTables(){
		List<EditPart> result = editor.getSelectionCache().getSelectionModelPartForType(MCrosstab.class);
		return result;
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		deleteStyles = false;
		int selection = getResponse();
		if (selection != 2 && selection != SWT.DEFAULT){
			deleteStyles = selection == 0;
			List<EditPart> parts = getSelectedTables();
			execute(changeStyleCommand(parts));
			for(EditPart part : parts){
				if (part instanceof FigureEditPart) ((FigureEditPart)part).refreshVisuals();
			}
		}
	}
	
	/**
	 * Return the response on how to handle the old styles, first check if there is something store
	 * in the preferences and use the information to avoid to propose the question dialog if 
	 * there is a default behavior stored. Otherwise show the dialog and store the decision if the flag
	 * to remember it is checked.
	 * 
	 * @return 0 if the old styles should be deleted, 1 if old styles should be maintained and the references removed from the element, 
	 * 2 if the operation is cancelled
	 */
	protected int getResponse(){
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String styleBehavior = store.getString(ComponentsPreferencePageExtension.BEHAVIOR_ON_STYLE_DELETE);
		if (styleBehavior.equals(ComponentsPreferencePageExtension.BEHAVIOR_DELETE_STYLES)){
			return 0;
		} else if (styleBehavior.equals(ComponentsPreferencePageExtension.BEHAVIOR_DELETE_STYLES_REFERENCES)) {
			return 1;
		} else { 
			//no preferences, ask what to do
			Shell shell = UIUtils.getShell();
			ExtendedMessageDialog question = new ExtendedMessageDialog(shell, Messages.RemoveStylesAction_messageTitle, null, 
																			Messages.RemoveStylesAction_messageText, MessageDialog.QUESTION, 
																			 new String[] {Messages.RemoveStylesAction_option1, 
																					 			Messages.RemoveStylesAction_option2, 
																					 				Messages.RemoveStylesAction_option3  }, 
																			 	2, Messages.EditTableStyleAction_rememberDecision);
			int response = question.open();
			//Store the decision if the flag is checked
			if (question.getCheckboxResult()){
				if (response == 0){
					store.setValue(ComponentsPreferencePageExtension.BEHAVIOR_ON_STYLE_DELETE, ComponentsPreferencePageExtension.BEHAVIOR_DELETE_STYLES);
				} else if (response == 1) {
					store.setValue(ComponentsPreferencePageExtension.BEHAVIOR_ON_STYLE_DELETE, ComponentsPreferencePageExtension.BEHAVIOR_DELETE_STYLES_REFERENCES);
				}
			}
			return response;
		}
	}

	/**
	 * Create the command to remove the style from a single cell and to delete the style 
	 * itself if the deleteStyle flag is enabled and if the command to delete the style
	 * was not already generated
	 * 
	 * @param cell the cell from where the style must be removed 
	 * @param container compound command where the new commands will be stored
	 */
	protected void createCommand(JRCellContents cell, JSSCompoundCommand container, MReport report){
		if (cell != null && cell instanceof JRDesignCellContents){
			container.add(new RemoveStyleCommand((JRDesignCellContents)cell));
			if (deleteStyles && cell.getStyle() != null){
				JRStyle style = cell.getStyle();
				if (!deletedStyles.contains(style.getName())){
					deletedStyles.add(style.getName());
					container.add(new DeleteStyleCommand(report, (JRDesignStyle)style));
				}
			}
		}
	}
	
	
	/**
	 * 
	 * Generate the command to remove all the styles from the crosstab, it's essentially a compound command
	 * composed of many commands
	 * 
	 * @param editParts the edit parts containing an MCrosstab as model
	 * 
	 * @return the command to remove all the styles
	 */
	protected Command changeStyleCommand(List<EditPart> editParts) {
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		deletedStyles = new HashSet<String>();
		for(EditPart editPart : editParts){
			MCrosstab crosstabModel = (MCrosstab)editPart.getModel();
			MReport report = ModelUtils.getReport(crosstabModel);
			command.setReferenceNodeIfNull(crosstabModel);
			JRDesignCrosstab crosstab = (JRDesignCrosstab)crosstabModel.getValue();
			//This command is added before and after all the other commands to force its
			//refresh when the other commands are executed ore undone
			command.add(new ForceRefreshCommand(crosstabModel));
			for (JRCrosstabRowGroup rowGroup : crosstab.getRowGroupsList()){
				JRDesignCrosstabRowGroup designGroup = (JRDesignCrosstabRowGroup)rowGroup;
				createCommand(designGroup.getTotalHeader(), command, report);
				createCommand(designGroup.getHeader(), command, report);
			}
			for (JRCrosstabColumnGroup colGroup : crosstab.getColumnGroupsList()){
				JRDesignCrosstabColumnGroup designGroup = (JRDesignCrosstabColumnGroup)colGroup;
				createCommand(designGroup.getTotalHeader(), command, report);
				createCommand(designGroup.getHeader(), command, report);
			}
			for(JRCrosstabCell dataCell : crosstab.getCellsList()){
				createCommand(dataCell.getContents(), command, report);
			}
			
			//Remove the styles property if any
			JRPropertiesMap crosstabMap = crosstab.getPropertiesMap();
			command.add(new SetPropertyValueCommand(crosstabMap, ApplyCrosstabStyleAction.CROSSTAB_DETAIL_PROPERTY, null));
			command.add(new SetPropertyValueCommand(crosstabMap, ApplyCrosstabStyleAction.CROSSTAB_GROUP_PROPERTY, null));
			command.add(new SetPropertyValueCommand(crosstabMap, ApplyCrosstabStyleAction.CROSSTAB_HEADER_PROPERTY, null));
			command.add(new SetPropertyValueCommand(crosstabMap, ApplyCrosstabStyleAction.CROSSTAB_TOTAL_PROPERTY, null));
			
			command.add(new ForceRefreshCommand(crosstabModel));
		}
		return command;
	}
}
