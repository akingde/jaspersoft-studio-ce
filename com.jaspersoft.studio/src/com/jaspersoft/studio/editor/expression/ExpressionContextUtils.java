package com.jaspersoft.studio.editor.expression;

import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * Utility methods related to the {@link ExpressionContext} object.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class ExpressionContextUtils {

	private ExpressionContextUtils(){
		// PREVENT INSTANTIATION 
	}
	
	/**
	 * Returns the list of JasperReports parameters that can be used 
	 * in the specified expression context.
	 * 
	 * @param exprContext the expression context
	 * @return the list of parameters
	 */
	public static List<JRParameter> getAllParameters(ExpressionContext exprContext){
		JRDesignDataset ds = exprContext.getDatasets().get(0);
		return ds.getParametersList();
	}
	
	/**
	 * Returns the list of JasperReports variables that can be used 
	 * in the specified expression context.
	 * 
	 * @param exprContext the expression context
	 * @return the list of variables
	 */
	public static List<JRVariable> getAllVariables(ExpressionContext exprContext){
		JRDesignDataset ds = exprContext.getDatasets().get(0);
    return ds.getVariablesList();
	}
	
	/**
	 * Returns the list of JasperReports fields that can be used 
	 * in the specified expression context.
	 * 
	 * @param exprContext the expression context
	 * @return the list of fields
	 */
	public static List<JRField> getAllFields(ExpressionContext exprContext){
		JRDesignDataset ds = exprContext.getDatasets().get(0);
		return ds.getFieldsList();
	}
}
