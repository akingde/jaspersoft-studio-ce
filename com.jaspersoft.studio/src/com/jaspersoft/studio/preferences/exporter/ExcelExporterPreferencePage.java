/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.preferences.exporter;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * 
 */
public class ExcelExporterPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ExcelExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.RulersGridPreferencePage_description);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		Group group = new Group(getFieldEditorParent(), SWT.NONE);
		group.setText(Messages.RulersGridPreferencePage_ruler_options);

		GridLayout gridLayout = new GridLayout(2, false);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);

		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;

		// addField(new BooleanFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_SHOWRULER,
		// Messages.RulersGridPreferencePage_show_rulers, group));
		// addField(new BooleanFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGUIDES,
		// Messages.common_snap_to_guides, group));

		group.setLayoutData(gridData);
		group.setLayout(gridLayout);

		group = new Group(getFieldEditorParent(), SWT.NONE);
		group.setText(Messages.RulersGridPreferencePage_grid_options);

		gridLayout = new GridLayout(2, false);
		gridData = new GridData(GridData.FILL_HORIZONTAL);

		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;

		// addField(new BooleanFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_SHOWGRID,
		// Messages.RulersGridPreferencePage_show_grid, group));
		// addField(new BooleanFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGRID, Messages.common_snap_to_grid,
		// group));
		// addField(new BooleanFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGEOMETRY,
		// Messages.common_snap_to_geometry, group));
		// IntegerFieldEditor spaceX = new IntegerFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_GRIDSPACEX,
		// Messages.RulersGridPreferencePage_grid_spacing_x, group);
		// spaceX.setValidRange(2, 100);
		// addField(spaceX);
		// IntegerFieldEditor spaceY = new IntegerFieldEditor(PreferenceConstants.P_PAGE_RULERGRID_GRIDSPACEY,
		// Messages.RulersGridPreferencePage_grid_spacing_y, group);
		// spaceY.setValidRange(2, 100);
		// addField(spaceY);

		group.setLayoutData(gridData);
		group.setLayout(gridLayout);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}