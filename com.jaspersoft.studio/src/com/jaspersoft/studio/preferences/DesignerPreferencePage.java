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

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * 
 */
public class DesignerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public DesignerPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.DesignerPreferencePage_description);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new ComboFieldEditor(PreferenceConstants.P_ELEMENT_DESIGN_BORDER_STYLE, Messages.DesignerPreferencePage_element_design_border_style,
				new String[][] { { Messages.DesignerPreferencePage_corners, "corners" }, { Messages.common_rectangle, "rectangle" } }, getFieldEditorParent())); //$NON-NLS-1$ //$NON-NLS-2$
		addField(new ComboFieldEditor(PreferenceConstants.P_PAGE_DESIGN_BORDER_STYLE, Messages.DesignerPreferencePage_page_border_style, new String[][] {
				{ Messages.DesignerPreferencePage_fancy_shadow, "shadow" }, { Messages.DesignerPreferencePage_simple_shadow, "rectangle" } }, getFieldEditorParent())); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}