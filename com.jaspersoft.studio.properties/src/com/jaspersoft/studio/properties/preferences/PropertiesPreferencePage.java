/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.properties.preferences;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.properties.Activator;

/*
 * 
 */
public class PropertiesPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private IAdaptable element;
	
	public static final String SINGLE_COLUMN_ID = "com.jaspersoft.studio.properties.preferences.PropertiesPreferencePage.single_column";
	
	public PropertiesPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Properties view configuration");
		setTitle("Properties View");
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		Composite parent = getFieldEditorParent();
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		BooleanFieldEditor be = new BooleanFieldEditor(SINGLE_COLUMN_ID, "Show the properties on a single column", container);
		addField(be);
	}


	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(SINGLE_COLUMN_ID, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}


	public IAdaptable getElement() {
		return element;
	}


	public void setElement(IAdaptable element) {
		this.element = element;
	}
}
