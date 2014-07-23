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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.number.SpinnerFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

/**
 * Prefrences dialog to handle some default properties of the elements on their creation
 * 
 * @author Orlandin Marco
 *
 */
public class ElementsDefaultPreferencePage extends FieldEditorOverlayPage {
	
	public static final String PAGE_ID = "com.jaspersoft.studio.editor.elementsdefault"; //$NON-NLS-1$
	
	public static final String STATIC_TEXT_WIDTH = "staticTextWidth"; //$NON-NLS-1$
	
	public static final String STATIC_TEXT_HEIGHT = "staticTextHeight"; //$NON-NLS-1$
	
	public static final String TEXT_FIELD_WIDTH = "textFieldWidth"; //$NON-NLS-1$
	
	public static final String TEXT_FIELD_HEIGHT = "textFieldHeight"; //$NON-NLS-1$
	
	public ElementsDefaultPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
	}
	
	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(STATIC_TEXT_WIDTH, 100);
		store.setDefault(STATIC_TEXT_HEIGHT, 20);
		store.setDefault(TEXT_FIELD_WIDTH, 100);
		store.setDefault(TEXT_FIELD_HEIGHT, 20);
	}
	
	private SpinnerFieldEditor getSizeEditor(String ID, String text){
		SpinnerFieldEditor spinner = new SpinnerFieldEditor(ID, text, getFieldEditorParent());
		spinner.setMinimum(1);
		spinner.setMaximum(Integer.MAX_VALUE);
		spinner.getLabelControl(getFieldEditorParent()).setToolTipText(Messages.ElementsDefaultPreferencePage_tooltip);
		return spinner;
	}
  
	public void createFieldEditors() {
		addField(getSizeEditor(STATIC_TEXT_WIDTH, Messages.ElementsDefaultPreferencePage_staticWidth));
		addField(getSizeEditor(STATIC_TEXT_HEIGHT, Messages.ElementsDefaultPreferencePage_staticHeight));
		addField(getSizeEditor(TEXT_FIELD_WIDTH, Messages.ElementsDefaultPreferencePage_fieldWidth));
		addField(getSizeEditor(TEXT_FIELD_HEIGHT, Messages.ElementsDefaultPreferencePage_fieldHeight));
	}
	
	@Override
	protected String getPageId() {
		return PAGE_ID;
	}

	@Override
	public void init(IWorkbench workbench) {
	}
}
