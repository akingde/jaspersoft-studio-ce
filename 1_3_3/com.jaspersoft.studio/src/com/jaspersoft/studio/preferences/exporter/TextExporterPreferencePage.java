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
package com.jaspersoft.studio.preferences.exporter;

import net.sf.jasperreports.engine.export.JRTextExporterParameter;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.number.FloatFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.editor.text.TextFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.Misc;
/*
 * 
 */
public class TextExporterPreferencePage extends FieldEditorOverlayPage {

	public static final String NSF_EXPORT_TEXT_LINE_SEPARATOR = "net.sf.jasperreports.export.text.line.separator"; //$NON-NLS-1$
	public static final String NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT = "net.sf.jasperreports.export.text.between.page.text"; //$NON-NLS-1$

	public TextExporterPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.TextExporterPreferencePage_6);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		addField(new FloatFieldEditor(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH,
				Messages.TextExporterPreferencePage_7, getFieldEditorParent()));
		addField(new FloatFieldEditor(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT,
				Messages.TextExporterPreferencePage_8, getFieldEditorParent()));

		addField(new IntegerFieldEditor(JRTextExporterParameter.PROPERTY_PAGE_WIDTH, Messages.TextExporterPreferencePage_9,
				getFieldEditorParent()));
		addField(new IntegerFieldEditor(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT,
				Messages.TextExporterPreferencePage_10, getFieldEditorParent()));

		addField(new NStringFieldEditor(NSF_EXPORT_TEXT_LINE_SEPARATOR, Messages.TextExporterPreferencePage_11, 4,
				getFieldEditorParent()));

		Composite c = new Composite(getFieldEditorParent(), SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);
		c.setLayout(new GridLayout());

		TextFieldEditor te = new TextFieldEditor(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT, Messages.TextExporterPreferencePage_12,
				c);
		te.getTextControl(c).setLayoutData(new GridData(GridData.FILL_BOTH));
		addField(te);

	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT), "0")); //$NON-NLS-1$
		store.setDefault(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH), "0")); //$NON-NLS-1$
		store.setDefault(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(JRTextExporterParameter.PROPERTY_PAGE_HEIGHT), "0")); //$NON-NLS-1$
		store.setDefault(JRTextExporterParameter.PROPERTY_PAGE_WIDTH,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(JRTextExporterParameter.PROPERTY_PAGE_WIDTH), "0")); //$NON-NLS-1$

		store.setDefault(NSF_EXPORT_TEXT_LINE_SEPARATOR, "\n"); //$NON-NLS-1$
		store.setDefault(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT,
				Misc.nvl(PropertiesHelper.DPROP.getProperty(NSF_EXPORT_TEXT_BETWEEN_PAGE_TEXT), "")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected String getPageId() {
		return "com.jaspersoft.studio.preferences.exporter.TextExporterPreferencePage.property"; //$NON-NLS-1$
	}

}
