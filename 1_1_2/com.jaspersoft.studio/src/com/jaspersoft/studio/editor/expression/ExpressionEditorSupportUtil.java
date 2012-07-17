package com.jaspersoft.studio.editor.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.expressions.functions.util.FunctionsLibraryUtil;

import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.ExpressionEditorPreferencePage;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Utility methods that allow the user to work with the expression editor "world".
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ExpressionEditorSupportUtil {

	private static final IExpressionEditorSupportFactory supportFactory;
	private static final Map<String, ExpressionEditorSupport> editorSupports;
	
	static {
		supportFactory = JaspersoftStudioPlugin.getExtensionManager().getExpressionEditorSupportFactory();
		editorSupports=new HashMap<String, ExpressionEditorSupport>(3); // java, groovy, javascript are languages that should be supported
	}
	
	/**
	 * Checks if it exists a support factory for the Expression Editor.
	 * 
	 * @return <code>true</code> if a support factory exists, <code>false</code> otherwise 
	 */
	public static boolean existsExpressionEditorSupportFactory(){
		return supportFactory!=null;
	}
	
	/**
	 * Gets the current support factory for the Expression Editor.
	 * 
	 * @return the support factory, <code>null</code> if none
	 */
	public static IExpressionEditorSupportFactory getExpressionEditorSupportFactory(){
		return supportFactory;
	}
	
	/**
	 * Checks if it exists editor support for the specified language.
	 * 
	 * @param language the language for which support is searched
	 * @return <code>true</code> if language is supported, <code>false</code> otherwise
	 */
	public static boolean existsSupportForLanguage(String language){
		return getEditorSupportForLanguage(language)!=null;
	}
	
	/**
	 * Gets the editor support for the specified language.
	 * 
	 * @param language the language for which support is searched
	 * @return the editor support, <code>null</code> if none
	 */
	public static ExpressionEditorSupport getEditorSupportForLanguage(String language){
		if(supportFactory==null || language==null){
			return null;
		}
		ExpressionEditorSupport expressionEditorSupport=null;
		if(!editorSupports.containsKey(language)){
			expressionEditorSupport=supportFactory.getExpressionEditorSupport(language);
			if(expressionEditorSupport!=null){
				editorSupports.put(language, expressionEditorSupport);
			}
		}
		else{
			expressionEditorSupport=editorSupports.get(language);
		}
		return expressionEditorSupport;
	}
	
	/**
	 * Gets the editor support for the language attribute set for the report.
	 * 
	 * @return the editor support, <code>null</code> if none
	 */
	public static ExpressionEditorSupport getEditorSupportForReportLanguage(){
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if(activeJRXMLEditor!=null && activeJRXMLEditor instanceof JrxmlEditor){
			return getEditorSupportForLanguage(
					((JrxmlEditor)activeJRXMLEditor).getModel().getJasperDesign().getLanguage());
		}
		else{
			return null;
		}
	}
	
	/**
	 * Gets a "generic" {@link ExpressionContext} object that uses the main
	 * report dataset as information.
	 * 
	 * @return a "generic" expression context
	 * @see ExpressionContext
	 */
	public static ExpressionContext getReportExpressionContext(){
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if(activeJRXMLEditor!=null && activeJRXMLEditor instanceof JrxmlEditor){
				final ANode mroot = (ANode)((JrxmlEditor)activeJRXMLEditor).getModel();
				final ANode mreport = (ANode) mroot.getChildren().get(0);
				JRDataset mainDS = mreport.getJasperDesign().getMainDataset();
				ExpressionContext exprContext=new ExpressionContext((JRDesignDataset)mainDS,mreport.getJasperConfiguration());
				return exprContext;
		}
		return null;
	}
	
	/**
	 * Add a list of static imports to the specified {@link JasperDesign} instance in order
	 * to correctly use the functions library inside the expressions.
	 * 
	 * @param jd the jasper design object to be enriched
	 */
	public static void addFunctionsLibraryImports(JasperDesign jd){
		Assert.isNotNull(jd);
		List<String> libraryClasses = FunctionsLibraryUtil.getLibraryClasses();
		for(String clazzName : libraryClasses){
			jd.addImport("static " + clazzName + ".*");
		}
	}

	/**
	 * Remove a list of static imports to the specified {@link JasperDesign} instance.
	 * <p>
	 * 
	 * The imports removed are those ones needed by the functions library used in 
	 * the new expression editor. 
	 *  
	 * @param jd the jasper design object to be updated
	 */
	public static void removeFunctionsLibraryImports(JasperDesign jd){
		Assert.isNotNull(jd);
		List<String> libraryClasses = FunctionsLibraryUtil.getLibraryClasses();
		for(String clazzName : libraryClasses){
			jd.removeImport("static " + clazzName + ".*");
		}
	}
	
	/**
	 * Update the functions library information for the specified jasper design.
	 * <p>
	 * 
	 * If allowed the static imports related to the functions library are added
	 * to the jasper design, otherwise they are removed.
	 * 
	 * @param jasperDesign the jasper design to modify
	 * 
	 * @see ExpressionEditorPreferencePage#P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS 
	 */
	public static void updateFunctionsLibraryImports(JasperDesign jasperDesign) {
		Assert.isNotNull(jasperDesign);
		// Add the imports needed for the functions library, if needed
		boolean useImports = JaspersoftStudioPlugin.getInstance().getPreferenceStore().getBoolean(
				ExpressionEditorPreferencePage.P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS);
		if(useImports){
			ExpressionEditorSupportUtil.addFunctionsLibraryImports(jasperDesign);
		}
		else{
			ExpressionEditorSupportUtil.removeFunctionsLibraryImports(jasperDesign);
		}
	}
	
}
