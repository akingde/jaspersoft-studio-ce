package com.jaspersoft.studio.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.service.prefs.Preferences;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

/**
 * Expression editor preference page.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ExpressionEditorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public static final String P_USER_DEFINED_EXPRESSIONS="userDefinedExpressions";//$NON-NLS-1$
	public static final String P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS="includeFunctionsLibraryImports";//$NON-NLS-1$
	
	public ExpressionEditorPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Expression Editor configuration");
	}

	@Override
	protected void createFieldEditors() {
		addField(new ExpressionListFieldEditor(
				P_USER_DEFINED_EXPRESSIONS, "User defined expressions", getFieldEditorParent()));
		addField(new BooleanFieldEditor(
				P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS, "Include static imports used for functions library", getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		
	}
	
	/**
 	 * @return the list of expressions defined by the user in the preferences
	 */
	public static List<String> getUserDefinedExpressionList(){
		Preferences preferences = PropertiesHelper.INSTANCE_SCOPE.getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
		String expressionListStr = preferences.get(P_USER_DEFINED_EXPRESSIONS, null);
		ArrayList<String> v = new ArrayList<String>();
		if(expressionListStr!=null){
		    StringTokenizer st = new StringTokenizer(expressionListStr, ExpressionListFieldEditor.EXPRESSION_SEP);
	        while (st.hasMoreElements()) {
	            v.add((String)st.nextElement());
	        }
		}
        return v;
	}
	
	public static void getDefaults(IPreferenceStore store){
		store.setDefault(ExpressionEditorPreferencePage.P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS, false); //$//$NON-NLS-1$
	}
}
