package com.jaspersoft.studio.editor.expression;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;

import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.ANode;
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
	
}
