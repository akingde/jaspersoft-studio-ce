/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.csv;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.decorator.csv.ColumnNamesSettingDialog;
import com.jaspersoft.studio.editor.gef.decorator.csv.NameDataChooserDialog;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.export.CsvMetadataReportConfiguration;

/**
 * This CSV action is used to display all the columns defined and to provide a dialog to modify their names
 * 
 * @author Orlandin Marco
 * 
 */
public class CSVColSettingAction extends CSVAction {

	private String columnNames;

	public CSVColSettingAction(IWorkbenchPart part, String actionName) {
		super(part, CSVAction.COL_NAMES, actionName);
	}

	/**
	 * Since this action is used to change a general property, it can't be considered as checked or unchecked, so this
	 * method will return always false and the action will be show as unchecked
	 */
	@Override
	public boolean isChecked() {
		return false;
	}

	/**
	 * Open the dialog to change the column order, and if it is closed with the ok button then the change will be done
	 * using the commands
	 */
	@Override
	public void run() {
		ColumnNamesSettingDialog dialog = new ColumnNamesSettingDialog(UIUtils.getShell(), getRoot().getJasperDesign());
		int dialogResult = dialog.open();
		if (dialogResult == NameDataChooserDialog.OK) {
			columnNames = dialog.getColumnNames();
			execute(createCommand());
		}
	}

	/**
	 * Return the commands stack necessary to change the column names order
	 * 
	 * @param columnNames
	 *          initial column order, name of the columns comma separated
	 */
	@Override
	protected Command createCommand() {
		APropertyNode columnValue = getRoot();
		if (columnValue != null) {
			JRPropertiesMap rootMap = (JRPropertiesMap) columnValue.getPropertyValue(MGraphicElement.PROPERTY_MAP);
			if (rootMap == null)
				rootMap = new JRPropertiesMap();
			if (Misc.isNullOrEmpty(columnNames))
				rootMap.removeProperty(CsvMetadataReportConfiguration.PROPERTY_COLUMN_NAMES_PREFIX);
			else
				rootMap.setProperty(CsvMetadataReportConfiguration.PROPERTY_COLUMN_NAMES_PREFIX, columnNames);

			SetValueCommand setRootNames = new SetValueCommand();
			// the property is set on the root
			setRootNames.setTarget((APropertyNode) columnValue.getRoot());
			setRootNames.setPropertyId(MGraphicElement.PROPERTY_MAP);
			setRootNames.setPropertyValue(rootMap);
			return setRootNames;
		}
		return null;
	}

	/**
	 * This action can be performed any time, if there are no columns will be simply displayed an empty list
	 */
	@Override
	protected boolean calculateEnabled() {
		return true;
	}
}
