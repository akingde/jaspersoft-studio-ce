/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.osgi.service.prefs.Preferences;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

import net.sf.jasperreports.eclipse.util.StringUtils;

/**
 * Expression editor preference page.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ExpressionEditorPreferencePage extends FieldEditorOverlayPage {

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.ExpressionEditorPreferencePage.property"; //$NON-NLS-1$
	public static final String P_USER_DEFINED_EXPRESSIONS = "userDefinedExpressions";//$NON-NLS-1$
	@Deprecated
	public static final String P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS = "includeFunctionsLibraryImports";//$NON-NLS-1$
	public static final String P_CONFIRMATION_ON_CLOSE = "askConfirmationOnEditorClose"; //$NON-NLS-1$ 
	public static final String P_REMEMBER_EXPEDITOR_SIZE = "rememberExpEditorSize"; //$NON-NLS-1$
	public static final String P_REMEMBER_EXPEDITOR_LOCATION = "rememberExpEditorLocation"; //$NON-NLS-1$
	public static final String V_EXPEDITOR_SIZE_WIDTH = "expressionEditorWidth"; //$NON-NLS-1$
	public static final String V_EXPEDITOR_SIZE_HEIGHT = "expressionEditorHeight"; //$NON-NLS-1$
	public static final String V_EXPEDITOR_LOCATION_X = "expressionEditorLocationX"; //$NON-NLS-1$
	public static final String V_EXPEDITOR_LOCATION_Y = "expressionEditorLocationY"; //$NON-NLS-1$

	public ExpressionEditorPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.ExpressionEditorPreferencePage_subtitle);
	}

	@Override
	protected void createFieldEditors() {
		addField(new ExpressionListFieldEditor(P_USER_DEFINED_EXPRESSIONS, Messages.ExpressionEditorPreferencePage_userDefinedExpressions,
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(P_CONFIRMATION_ON_CLOSE, Messages.ExpressionEditorPreferencePage_confirmationOnClosing, getFieldEditorParent()));
		addField(new BooleanFieldEditor(P_REMEMBER_EXPEDITOR_SIZE, Messages.ExpressionEditorPreferencePage_rememberSize, getFieldEditorParent()));
		addField(new BooleanFieldEditor(P_REMEMBER_EXPEDITOR_LOCATION, Messages.ExpressionEditorPreferencePage_rememberLocation, getFieldEditorParent()));

		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {

	}

	/**
	 * @return the list of expressions defined by the user in the preferences
	 */
	public static List<String> getUserDefinedExpressionList() {
		Preferences preferences = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
		String expressionListStr = preferences.get(P_USER_DEFINED_EXPRESSIONS, null);
		ArrayList<String> v = new ArrayList<String>();
		if (expressionListStr != null) {
			StringTokenizer st = new StringTokenizer(expressionListStr, ExpressionListFieldEditor.EXPRESSION_SEP);
			while (st.hasMoreElements()) {
				v.add(StringUtils.safeDecode64((String) st.nextElement()));
			}
		}
		return v;
	}
	
	/**
	 * Adds a new user defined expression to the list of the existing ones.
	 * 
	 * @param text the new expression to add
	 */
	public static void addUserDefinedExpression(String text){
		List<String> expressions = getUserDefinedExpressionList();
		if(!expressions.contains(text)){
			expressions.add(text);
			Preferences preferences = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
			preferences.put(P_USER_DEFINED_EXPRESSIONS, encodeUserDefinedExpression(expressions));
		}
	}
	

	/**
	 * Produces a Base64 encoded property string from the list of user defined expressions.</br>
	 * 
	 * @param expressions the list of user defined expressions
	 * @return the enconded property value
	 */
	public static String encodeUserDefinedExpression(List<String> expressions){
        StringBuffer expressionsBuff = new StringBuffer("");//$NON-NLS-1$
        for (String e : expressions) {
        	expressionsBuff.append(Base64.getEncoder().encodeToString(e.getBytes()));
        	expressionsBuff.append(ExpressionListFieldEditor.EXPRESSION_SEP);
        }
        return expressionsBuff.toString();
	}
	
	/**
	 * Init default properties if needed.
	 * 
	 * @param store the preference store
	 */
	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(P_CONFIRMATION_ON_CLOSE, false);
		store.setDefault(P_REMEMBER_EXPEDITOR_SIZE, false);
		store.setDefault(P_REMEMBER_EXPEDITOR_LOCATION, false);
	}

	@Override
	public String getPageId() {
		return PAGE_ID;
	}
}
