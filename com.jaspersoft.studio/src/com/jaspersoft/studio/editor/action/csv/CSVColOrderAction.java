/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.csv;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.export.CsvMetadataReportConfiguration;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.decorator.csv.ColumnsOrderDialog;
import com.jaspersoft.studio.editor.gef.decorator.csv.NameDataChooserDialog;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.Misc;

/**
 * This CSV action is used to display all the columns defined and to provide a dialog to change its order
 * 
 * @author Orlandin Marco
 * 
 */
public class CSVColOrderAction extends CSVAction {

	private String columnNames;

	public CSVColOrderAction(IWorkbenchPart part, String actionName) {
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
		ColumnsOrderDialog dialog = new ColumnsOrderDialog(UIUtils.getShell(), getRoot().getJasperDesign());
		int dialogResult = dialog.open();
		if (dialogResult == NameDataChooserDialog.OK) {
			columnNames = dialog.getOrders();
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
