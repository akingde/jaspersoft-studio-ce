/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.model.parameter.MCrosstabParameter;
import com.jaspersoft.studio.model.command.SetExpressionTextCommand;
import com.jaspersoft.studio.property.IPostSetValue;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignParameter;

/**
 * When a crosstab parameter is renamed this listener will update
 * the expression unsing the old parameter name
 * 
 * @author Orlandin Marco
 *
 */
public class PostSetCrosstabParameterName implements IPostSetValue {

	/**
	 * Search all the expressions inside the crosstab, search in them parameters that reference the 
	 * old parameter name and update them with the new one
	 */
	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		if (target instanceof MCrosstabParameter && (prop.equals(JRDesignParameter.PROPERTY_NAME))) {
			MCrosstabParameter parameter = ((MCrosstabParameter)target);
			MCrosstab crosstab = parameter.getMCrosstab();
			JRExpressionCollector crosstabCollector = JRExpressionCollector.collector(crosstab.getJasperConfiguration(), 
																					crosstab.getJasperDesign(), 
																					crosstab.getValue());
			List<JRExpression> crosstabExpressions = crosstabCollector.getExpressions(crosstab.getValue());
			JSSCompoundCommand c = new JSSCompoundCommand(crosstab);
			//Escape for the replace all
			String oldVarReference = "\\$P\\{" + oldValue + "}";
			String newVarReference = "\\$P\\{" + newValue + "}";
			for(JRExpression exp : crosstabExpressions){
				String text = exp.getText();
				if (text != null && text.length() > 4 && text.contains("$P{" + oldValue + "}")) {
					text = text.replaceAll(oldVarReference, newVarReference);
					c.add(new SetExpressionTextCommand(exp, text));
				}
			}
			return c.isEmpty() ? null : c;
		}
		return null;
	}
}
