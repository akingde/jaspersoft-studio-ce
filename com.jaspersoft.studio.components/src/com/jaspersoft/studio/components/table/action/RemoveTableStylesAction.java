/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.action;

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
import com.jaspersoft.studio.components.preferences.ComponentsPreferencePageExtension;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.command.ForceRefreshCommand;
import com.jaspersoft.studio.model.style.command.DeleteStyleCommand;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.GroupCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.eclipse.ui.util.ExtendedMessageDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Action to delete all the styles from a table element
 * 
 * @author Orlandin Marco
 *
 */
public class RemoveTableStylesAction extends ACachedSelectionAction {
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.RemoveTableStyles";  //$NON-NLS-1$
	
	/**
	 * boolean flag to specify if the style element should be deleted or only be removed from the table
	 */
	private boolean deleteStyles = false;
	
	/**
	 * hashmap used internally to keep trace of the deleted styles
	 */
	private HashSet<String> deletedStyles;
	
	
	public RemoveTableStylesAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.RemoveStylesAction_actionTitle);
		setId(RemoveTableStylesAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-style-remove-16.png")); //$NON-NLS-1$
	}

	/**
	 * The action is enable only if enabled if and only if one of the edit part of the selection 
	 * has as model type an MTable
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> tables = editor.getSelectionCache().getSelectionModelForType(MTable.class);
		for(Object obj :tables){
			MTable table = (MTable)obj;
			StandardTable jrTable = (StandardTable)((JRDesignComponentElement)table.getValue()).getComponent();
			if (hasStyles(jrTable.getColumns())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return a list of the selected edit parts that has a model of type MTable
	 * 
	 * @return a not null list of edit part with an MTable as model
	 */
	private List<EditPart> getSelectedTables(){
		List<EditPart> result = editor.getSelectionCache().getSelectionModelPartForType(MTable.class);
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
	 * @param report the report where the table is contained
	 */
	protected void createCommand(Cell cell, JSSCompoundCommand container, MReport report){
		if (cell != null && cell instanceof DesignCell){
			container.add(new RemoveStyleCommand((DesignCell)cell));
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
	 * Create the commands to remove the style from a list of columns, one by one
	 * 
	 * @param columns not null list of columns
	 * @param container compound command where the new commands will be stored
	 * @param report the report where the table is contained
	 */
	protected void createCommandForColumns(List<BaseColumn> columns, JSSCompoundCommand command, MReport report){
		for (BaseColumn col : columns){
			createCommand(col.getColumnFooter(), command, report);
			createCommand(col.getColumnHeader(), command, report);
			createCommand(col.getTableFooter(), command, report);
			createCommand(col.getTableHeader(), command, report);
			
			for(GroupCell cell : col.getGroupFooters()){
				createCommand(cell.getCell(), command, report);
			}
			
			for(GroupCell cell : col.getGroupHeaders()){
				createCommand(cell.getCell(), command, report);
			}
			
			if (col instanceof StandardColumn){
				StandardColumn baseCol = (StandardColumn)col;
				createCommand(baseCol.getDetailCell(), command, report);
			}
			
			if (col instanceof StandardColumnGroup){
				StandardColumnGroup colGroup = (StandardColumnGroup)col;
				createCommandForColumns(colGroup.getColumns(), command, report);
			}
		}
	}
	
	protected boolean hasStyles(List<BaseColumn> columns){
		for (BaseColumn col : columns){
			if (hasStyle(col.getColumnFooter())) return true;
			if (hasStyle(col.getColumnHeader())) return true;
			if (hasStyle(col.getTableFooter())) return true;
			if (hasStyle(col.getTableHeader())) return true;
			
			for(GroupCell cell : col.getGroupFooters()){
				if (hasStyle(cell.getCell())){
					return true;
				}
			}
			
			for(GroupCell cell : col.getGroupHeaders()){
				if (hasStyle(cell.getCell())){
					return true;
				}
			}
			
			if (col instanceof StandardColumn){
				StandardColumn baseCol = (StandardColumn)col;
				if (hasStyle(baseCol.getDetailCell())){
					return true;
				}
			}
			
			if (col instanceof StandardColumnGroup){
				StandardColumnGroup colGroup = (StandardColumnGroup)col;
				if (hasStyles(colGroup.getColumns())) return true;
			}
		}
		return false;
	}
	
	protected boolean hasStyle(Cell cell){
		if (cell != null && (cell.getStyle() != null || cell.getStyleNameReference() != null)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Generate the command to remove all the styles from the table, it's essentially a compound command
	 * composed of many commands
	 * 
	 * @param editParts the edit parts containing an MCrosstab as model
	 * 
	 * @return the command to remove all the styles
	 */
	protected Command changeStyleCommand(List<EditPart> parts) {
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		deletedStyles = new HashSet<String>();
		for(EditPart part : parts){
			MTable table = (MTable)part.getModel();
			MReport report = ModelUtils.getReport(table);
			command.setReferenceNodeIfNull(table);
			StandardTable jrTable = (StandardTable)((JRDesignComponentElement)table.getValue()).getComponent();
			//This command is added before and after all the other commands to force its
			//refresh when the other commands are executed ore undone
			command.add(new ForceRefreshCommand(table));
			createCommandForColumns(jrTable.getColumns(), command, report);
			//Remove the styles property if any
			JRPropertiesMap tableMap = table.getPropertiesMap();
			command.add(new SetPropertyValueCommand(tableMap, ApplyTableStyleAction.TABLE_HEADER_PROPERTY, null));
			command.add(new SetPropertyValueCommand(tableMap, ApplyTableStyleAction.COLUMN_HEADER_PROPERTY, null));
			command.add(new SetPropertyValueCommand(tableMap, ApplyTableStyleAction.DETAIL_PROPERTY, null));
			command.add(new ForceRefreshCommand(table));
		}
		return command;
	}
}
