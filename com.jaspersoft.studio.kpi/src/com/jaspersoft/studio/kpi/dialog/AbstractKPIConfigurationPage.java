package com.jaspersoft.studio.kpi.dialog;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AbstractKPIConfigurationPage {

	protected Composite mainComposite;
	
	protected JasperDesign jd;
	
	public Composite getComposite(Composite container, JasperDesign jd){
		if (mainComposite == null){
			this.jd = jd;
			mainComposite = createComposite(container);
		}
		return mainComposite;
	}
	
	public abstract String getName();
	
	public String getTitle(){
		return getName();
	}
	
	protected abstract Composite createComposite(Composite parent);

	// UTILITY METHODS
	
	/**
	 * Return the expression context for the main dataset
	 * 
	 * @return a not null expression context
	 */
	protected ExpressionContext getExpressionContext(JRDesignDataset dataset) {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(dataset, jConfig);
	}
	
	
	/**
	 * Return a variable from the JasperDesign with a specific name, if the variable
	 * is not found the it is created and returned. The variable is searched in the specified dataset
	 * 
	 * @param variableName the name of the variable
	 * @param dataset a not null dataset
	 * @return a not null JRDesignVariable
	 */
	protected JRDesignVariable getVariable(String variableName, JRDesignDataset dataset){
		JRVariable variable = dataset.getVariablesMap().get(variableName);
		if (variable == null){
			JRDesignVariable newVariable = new JRDesignVariable();
			newVariable.setName(variableName);
			try {
				dataset.addVariable(newVariable);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newVariable;
		}
		return ((JRDesignVariable)variable);
	}
	
	/**
	 * Return a variable from the JasperDesign with a specific name, if the variable
	 * is not found the it is created and returned. The variable is searched in the main dataset
	 * 
	 * @param variableName the name of the variable
	 * @return a not null JRDesignVariable
	 */
	protected JRDesignVariable getVariable(String variableName){
		return getVariable(variableName, jd.getMainDesignDataset());
	}
	
	/**
	 * Return a parameter from the JasperDesign with a specific name, if the parameter
	 * is not found the it is created and returned. It is searched in the specified dataset
	 * 
	 * @param parameterName the name of the parameter
	 * @parm dataset a not null dataset
	 * @return a not null JRDesignParameter
	 */
	protected JRDesignParameter getParameter(String parameterName, JRDesignDataset dataset){
		JRParameter parameter = dataset.getParametersMap().get(parameterName);
		if (parameter == null){
			JRDesignParameter newParameter = new JRDesignParameter();
			newParameter.setName(parameterName);
			try {
				dataset.addParameter(newParameter);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newParameter;
		}
		return ((JRDesignParameter)parameter);
	}
	
	/**
	 * Return a parameter from the JasperDesign with a specific name, if the parameter
	 * is not found the it is created and returned. It is searched in the main dataset
	 * 
	 * @param parameterName the name of the parameter
	 * @return a not null JRDesignParameter
	 */
	protected JRDesignParameter getParameter(String parameterName){
		return getParameter(parameterName, jd.getMainDesignDataset());
	}
}
