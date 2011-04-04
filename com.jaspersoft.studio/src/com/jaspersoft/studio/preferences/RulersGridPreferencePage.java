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
package com.jaspersoft.studio.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
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
public class RulersGridPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String P_PAGE_RULERGRID_SHOWRULER = "pageRulerGrid_SHOWRULER"; //$NON-NLS-1$
	public static final String P_PAGE_RULERGRID_SNAPTOGUIDES = "pageRulerGrid_SNAPTOGUIDES"; //$NON-NLS-1$
	public static final String P_PAGE_RULERGRID_SHOWGRID = "pageRulerGrid_SHOWGRID"; //$NON-NLS-1$
	public static final String P_PAGE_RULERGRID_SNAPTOGRID = "pageRulerGrid_SNAPTOGRID"; //$NON-NLS-1$
	public static final String P_PAGE_RULERGRID_SNAPTOGEOMETRY = "pageRulerGrid_SNAPTOGEOMETRY"; //$NON-NLS-1$
	public static final String P_PAGE_RULERGRID_GRIDSPACEX = "pageRulerGrid_GRIDSPACEX"; //$NON-NLS-1$
	public static final String P_PAGE_RULERGRID_GRIDSPACEY = "pageRulerGrid_GRIDSPACEY"; //$NON-NLS-1$

	public RulersGridPreferencePage() {
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
		group.setLayout(new GridLayout(2, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		group.setLayoutData(gridData);

		addField(new BooleanFieldEditor(P_PAGE_RULERGRID_SHOWRULER, Messages.RulersGridPreferencePage_show_rulers, group));
		addField(new BooleanFieldEditor(P_PAGE_RULERGRID_SNAPTOGUIDES, Messages.common_snap_to_guides, group));

		group = new Group(getFieldEditorParent(), SWT.NONE);
		group.setText(Messages.RulersGridPreferencePage_grid_options);
		group.setLayout(new GridLayout(2, false));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		group.setLayoutData(gridData);

		addField(new BooleanFieldEditor(P_PAGE_RULERGRID_SHOWGRID, Messages.RulersGridPreferencePage_show_grid, group));
		addField(new BooleanFieldEditor(P_PAGE_RULERGRID_SNAPTOGRID, Messages.common_snap_to_grid, group));
		addField(new BooleanFieldEditor(P_PAGE_RULERGRID_SNAPTOGEOMETRY, Messages.common_snap_to_geometry, group));
		IntegerFieldEditor spaceX = new IntegerFieldEditor(P_PAGE_RULERGRID_GRIDSPACEX,
				Messages.RulersGridPreferencePage_grid_spacing_x, group);
		spaceX.setValidRange(2, 100);
		addField(spaceX);

		IntegerFieldEditor spaceY = new IntegerFieldEditor(P_PAGE_RULERGRID_GRIDSPACEY,
				Messages.RulersGridPreferencePage_grid_spacing_y, group);
		spaceY.setValidRange(2, 100);
		addField(spaceY);

	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(P_PAGE_RULERGRID_SHOWRULER, new Boolean(true));
		store.setDefault(P_PAGE_RULERGRID_SNAPTOGUIDES, new Boolean(true));

		store.setDefault(P_PAGE_RULERGRID_SHOWGRID, new Boolean(true));
		store.setDefault(P_PAGE_RULERGRID_SNAPTOGRID, new Boolean(true));
		store.setDefault(P_PAGE_RULERGRID_SNAPTOGEOMETRY, new Boolean(true));
		store.setDefault(P_PAGE_RULERGRID_GRIDSPACEX, new Integer(10));
		store.setDefault(P_PAGE_RULERGRID_GRIDSPACEY, new Integer(10));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}