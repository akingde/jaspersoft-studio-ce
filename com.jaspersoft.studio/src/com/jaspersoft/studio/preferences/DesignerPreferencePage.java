/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.font.FontsComboFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.property.section.report.util.Unit;

/*
 * 
 */
public class DesignerPreferencePage extends FieldEditorOverlayPage {

	public static final String DEFAULT_BORDERSTYLE = "shadow"; //$NON-NLS-1$
	public static final String DEFAULT_MARGINCOLOR = "170,168,255"; //$NON-NLS-1$
	public static final String DEFAULT_PAGE_BACKGROUND = "255,255,255"; //$NON-NLS-1$

	public static final String DEFAULT_ELEMENT_DESIGN_BORDER_COLOR = "0,0,0"; //$NON-NLS-1$

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.DesignerPreferencePage.property"; //$NON-NLS-1$

	public static final String P_ELEMENT_DESIGN_BORDER_STYLE = "elementDesignBorderStyle"; //$NON-NLS-1$
	public static final String P_PAGE_DESIGN_BORDER_STYLE = "pageDesignBorderStyle"; //$NON-NLS-1$
	public static final String P_PAGE_DEFAULT_UNITS = "pageDEFAULTUNITS"; //$NON-NLS-1$
	public static final String P_SHOW_REPORT_BAND_NAMES = "showReportBandNames"; //$NON-NLS-1$
	public static final String P_CONTAINER_MARGIN_COLOR = "containerMarginColor"; //$NON-NLS-1$
	public static final String P_PAGE_MARGIN_COLOR = "pageMarginColor"; //$NON-NLS-1$
	public static final String P_PAGE_BACKGROUND = "pageBackground"; //$NON-NLS-1$

	public static final String P_ELEMENT_DESIGN_BORDER_COLOR = "elementDesignBorderColor"; //$NON-NLS-1$

	// editors font information
	public static final String P_EDITORS_FONT_FAMILY = "editorsFontFamily"; //$NON-NLS-1$
	public static final String P_EDITORS_FONT_HEIGHT = "editorsFontHeight"; //$NON-NLS-1$
	public static final String DEFAULT_EDITORS_FONT_FAMILY = "Monospaced"; //$NON-NLS-1$
	public static final String DEFAULT_EDITORS_FONT_HEIGHT = "10"; //$NON-NLS-1$

	public DesignerPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.DesignerPreferencePage_description);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new ComboFieldEditor(
				P_ELEMENT_DESIGN_BORDER_STYLE,
				Messages.DesignerPreferencePage_element_design_border_style,
				new String[][] {
						{ Messages.DesignerPreferencePage_corners, "corners" }, { Messages.common_rectangle, "rectangle" } }, getFieldEditorParent())); //$NON-NLS-1$ //$NON-NLS-2$
		addField(new ColorFieldEditor(P_ELEMENT_DESIGN_BORDER_COLOR, Messages.DesignerPreferencePage_elementbordercolor,
				getFieldEditorParent()));

		addField(new ComboFieldEditor(P_PAGE_DESIGN_BORDER_STYLE, Messages.DesignerPreferencePage_page_border_style,
				new String[][] { { Messages.DesignerPreferencePage_fancy_shadow, DEFAULT_BORDERSTYLE },
						{ Messages.DesignerPreferencePage_simple_shadow, "rectangle" } }, getFieldEditorParent())); //$NON-NLS-1$ //$NON-NLS-2$

		addField(new ComboFieldEditor(P_PAGE_DEFAULT_UNITS, Messages.DesignerPreferencePage_unit, Unit.getUnits2(),
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(P_SHOW_REPORT_BAND_NAMES, Messages.DesignerPreferencePage_show_band_names,
				getFieldEditorParent()));
		addField(new ColorFieldEditor(P_CONTAINER_MARGIN_COLOR, Messages.DesignerPreferencePage_common_bandmargincolor,
				getFieldEditorParent()));
		addField(new ColorFieldEditor(P_PAGE_MARGIN_COLOR, Messages.DesignerPreferencePage_pageprintmargincolor,
				getFieldEditorParent()));
		addField(new ColorFieldEditor(P_PAGE_BACKGROUND, Messages.DesignerPreferencePage_pagebackground,
				getFieldEditorParent()));
		FontsComboFieldEditor fontFamilyFieldEditor = new FontsComboFieldEditor(
				P_EDITORS_FONT_FAMILY, Messages.DesignerPreferencePage_InternalEditorsFontFamily, getFieldEditorParent());
		fontFamilyFieldEditor.setTooltipText(Messages.DesignerPreferencePage_InternalEditorsFontFamilyTooltip);
		addField(fontFamilyFieldEditor);
		NStringFieldEditor fontHeightEditor = new NStringFieldEditor(
				P_EDITORS_FONT_HEIGHT, Messages.DesignerPreferencePage_InternalEditorsFontHeight, 3, getFieldEditorParent());
		fontHeightEditor.setTooltipText(Messages.DesignerPreferencePage_InternalEditorsFontHeightTooltip);
		addField(fontHeightEditor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(P_PAGE_DESIGN_BORDER_STYLE, DEFAULT_BORDERSTYLE);
		store.setDefault(P_ELEMENT_DESIGN_BORDER_COLOR, DEFAULT_ELEMENT_DESIGN_BORDER_COLOR);
		store.setDefault(P_ELEMENT_DESIGN_BORDER_STYLE, "rectangle"); //$NON-NLS-1$
		store.setDefault(P_PAGE_DEFAULT_UNITS, "px"); //$NON-NLS-1$
		store.setDefault(P_CONTAINER_MARGIN_COLOR, DEFAULT_MARGINCOLOR);
		store.setDefault(P_PAGE_MARGIN_COLOR, DEFAULT_MARGINCOLOR);
		store.setDefault(P_SHOW_REPORT_BAND_NAMES, true);
		store.setDefault(P_PAGE_BACKGROUND, DEFAULT_PAGE_BACKGROUND); 
		store.setDefault(P_EDITORS_FONT_FAMILY, DEFAULT_EDITORS_FONT_FAMILY);
		store.setDefault(P_EDITORS_FONT_HEIGHT, DEFAULT_EDITORS_FONT_HEIGHT);
	}

	@Override
	protected String getPageId() {
		return PAGE_ID;
	}
}
