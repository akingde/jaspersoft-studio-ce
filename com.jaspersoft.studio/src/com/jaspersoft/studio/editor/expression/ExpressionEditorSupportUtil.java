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
package com.jaspersoft.studio.editor.expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.expressions.annotations.JRExprFunctionBean;

import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.ExpressionEditorPreferencePage;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Utility methods that allow the user to work with the expression editor "world".
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ExpressionEditorSupportUtil {

	private static final IExpressionEditorSupportFactory supportFactory;
	private static final Map<String, ExpressionEditorSupport> editorSupports;
	private static ExpressionContext currentContext;
	
	static {
		supportFactory = JaspersoftStudioPlugin.getExtensionManager().getExpressionEditorSupportFactory();
		editorSupports = new HashMap<String, ExpressionEditorSupport>(3); // java, groovy, javascript are languages that
																																			// should be supported
	}

	/**
	 * Checks if it exists a support factory for the Expression Editor.
	 * 
	 * @return <code>true</code> if a support factory exists, <code>false</code> otherwise
	 */
	public static boolean existsExpressionEditorSupportFactory() {
		return supportFactory != null;
	}

	/**
	 * Gets the current support factory for the Expression Editor.
	 * 
	 * @return the support factory, <code>null</code> if none
	 */
	public static IExpressionEditorSupportFactory getExpressionEditorSupportFactory() {
		return supportFactory;
	}

	/**
	 * Checks if it exists editor support for the specified language.
	 * 
	 * @param language
	 *          the language for which support is searched
	 * @return <code>true</code> if language is supported, <code>false</code> otherwise
	 */
	public static boolean existsSupportForLanguage(String language) {
		return getEditorSupportForLanguage(language) != null;
	}

	/**
	 * Gets the editor support for the specified language.
	 * 
	 * @param language
	 *          the language for which support is searched
	 * @return the editor support, <code>null</code> if none
	 */
	public static ExpressionEditorSupport getEditorSupportForLanguage(String language) {
		if (supportFactory == null || language == null) {
			return null;
		}
		ExpressionEditorSupport expressionEditorSupport = null;
		if (!editorSupports.containsKey(language)) {
			expressionEditorSupport = supportFactory.getExpressionEditorSupport(language);
			if (expressionEditorSupport != null) {
				editorSupports.put(language, expressionEditorSupport);
			}
		} else {
			expressionEditorSupport = editorSupports.get(language);
		}
		return expressionEditorSupport;
	}

	/**
	 * Gets the editor support for the language attribute set for the report.
	 * 
	 * @return the editor support, <code>null</code> if none
	 */
	public static ExpressionEditorSupport getEditorSupportForReportLanguage() {
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if (activeJRXMLEditor != null && activeJRXMLEditor instanceof JrxmlEditor) {
			return getEditorSupportForLanguage(((JrxmlEditor) activeJRXMLEditor).getModel().getJasperDesign().getLanguage());
		} else {
			return null;
		}
	}

	/**
	 * Gets a "generic" {@link ExpressionContext} object that uses the main report dataset as information.
	 * 
	 * @return a "generic" expression context
	 * @see ExpressionContext
	 */
	public static ExpressionContext getReportExpressionContext() {
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if (activeJRXMLEditor != null && activeJRXMLEditor instanceof JrxmlEditor) {
			final ANode mroot = (ANode) ((JrxmlEditor) activeJRXMLEditor).getModel();
			final ANode mreport = (ANode) mroot.getChildren().get(0);
			JRDataset mainDS = mreport.getJasperDesign().getMainDataset();
			ExpressionContext exprContext = new ExpressionContext((JRDesignDataset) mainDS, mreport.getJasperConfiguration());
			return exprContext;
		}
		return null;
	}

	/**
	 * Add a list of static imports to the specified {@link JasperDesign} instance in order to correctly use the functions
	 * library inside the expressions.
	 * 
	 * @param jd
	 *          the jasper design object to be enriched
	 * @param jrContext
	 *          the JasperReports context associated
	 */
	public static void addFunctionsLibraryImports(JasperDesign jd, JasperReportsConfiguration jrContext) {
		Assert.isNotNull(jd);
		Collection<JRExpression> collectedExpressions = JRExpressionCollector.collector(jrContext, jd)
				.getExpressions();
		List<String> libraryClasses = getStaticImportsForExpressions(collectedExpressions);
		for (String clazzName : libraryClasses) {
			jd.addImport("static " + clazzName + ".*");
		}
	}

	/**
	 * Remove a list of static imports to the specified {@link JasperDesign} instance.
	 * <p>
	 * 
	 * The imports removed are those ones needed by the functions library used in the new expression editor.
	 * 
	 * @param jd
	 *          the jasper design object to be updated
	 */
	public static void removeFunctionsLibraryImports(JasperDesign jd) {
		Assert.isNotNull(jd);
		List<String> libraryClasses = FunctionsLibraryUtil.getLibraryClasses();
		for (String clazzName : libraryClasses) {
			jd.removeImport("static " + clazzName + ".*");
		}
	}

	/**
	 * Update the functions library information for the specified jasper design.
	 * <p>
	 * 
	 * If allowed the static imports related to the functions library are added to the jasper design, otherwise they are
	 * removed.
	 * 
	 * @param jasperDesign
	 *          the jasper design to modify
	 * @param jrContext
	 *          the JasperReports context associated
	 * 
	 * @see ExpressionEditorPreferencePage#P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS
	 */
	public static void updateFunctionsLibraryImports(JasperDesign jasperDesign, JasperReportsConfiguration jrContext) {
		Assert.isNotNull(jasperDesign);
		// Always remove previously set functions library imports
		ExpressionEditorSupportUtil.removeFunctionsLibraryImports(jasperDesign);
		// Add the imports needed for the functions library, if preference is set
		boolean useImports = jrContext
				.getPropertyBoolean(ExpressionEditorPreferencePage.P_INCLUDE_FUCTIONS_LIBRARY_IMPORTS);
		if (useImports) {
			ExpressionEditorSupportUtil.addFunctionsLibraryImports(jasperDesign, jrContext);
		}
	}

	/**
	 * Retrieves a list of needed static imports for expression functions depending on the specified list of
	 * {@link JRExpression}.
	 * 
	 * @param expressions
	 *          a collection of expressions from which to extract the needed static imports
	 * @return a list of static imports that should be added to the main report
	 */
	public static List<String> getStaticImportsForExpressions(Collection<JRExpression> expressions) {
		Set<String> importsSet = new HashSet<String>();
		for (JRExpression jre : expressions) {
			List<JRExprFunctionBean> functions = FunctionsLibraryUtil.findFunctions(jre);
			for (JRExprFunctionBean f : functions) {
				importsSet.add(f.getFunctionClassName());
			}
		}
		return Arrays.asList(importsSet.toArray(new String[] {}));
	}

	/**
	 * Sets the current expression context that is currently used in the Expression Editor UI.
	 * <p>
	 * 
	 * Should be reset to <code>null</null> when the editor UI is closed.
	 * 
	 * @param exprContext
	 *          the current expression context to set
	 */
	public static void setCurrentExpressionContext(ExpressionContext exprContext) {
		currentContext = exprContext;
	}
	
	/**
	 * @return the current expression context if any, <code>null</code> otherwise
	 */
	public static ExpressionContext getCurrentExpressionContext() {
		return currentContext;
	}

	/**
	 * @return the current expression context if any is set, otherwise tries to return the default expression context for
	 *         the currently opened report
	 */
	public static ExpressionContext safeGetCurrentExpressionContext() {
		ExpressionContext expContext = getCurrentExpressionContext();
		if(expContext==null){
			return getReportExpressionContext();
		}
		else {
			return expContext;
		}
	}
	
}
