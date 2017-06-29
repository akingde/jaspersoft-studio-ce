/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.command.SetExpressionTextCommand;
import com.jaspersoft.studio.property.IPostSetValue;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;

/**
 * When a crosstab group is renamed this listener will update
 * the values of the variables depending on the group name, updating
 * them to fit the new one
 * 
 * @author Orlandin Marco
 *
 */
public class PostSetGroupName implements IPostSetValue {

	/**
	 * Search all the expressions inside the crosstab, search in them variables that  reference the 
	 * old group name and update them with the new one
	 */
	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		if (target instanceof MCrosstabGroup && (prop.equals(JRDesignCrosstabGroup.PROPERTY_NAME))) {
			MCrosstabGroup group = ((MCrosstabGroup)target);
			MCrosstab crosstab = group.getMCrosstab();
			JRExpressionCollector crosstabCollector = JRExpressionCollector.collector(crosstab.getJasperConfiguration(), 
																					crosstab.getJasperDesign(), 
																					crosstab.getValue());
			List<JRExpression> crosstabExpressions = crosstabCollector.getExpressions(crosstab.getValue());
			JSSCompoundCommand c = new JSSCompoundCommand(crosstab);
			//Escape for the replace all
			String oldVarReference = "\\$V\\{" + oldValue + "}";
			String newVarReference = "\\$V\\{" + newValue + "}";
			for(JRExpression exp : crosstabExpressions){
				String text = exp.getText();
				if (text != null && text.length() > 4 && text.contains("$V{" + oldValue + "}")) {
					text = text.replaceAll(oldVarReference, newVarReference);
					c.add(new SetExpressionTextCommand(exp, text));
				}
			}
			//Update the label of the child node
			for(INode child : group.getChildren()){
				((MCell)child).updateName();
			}
			return c.isEmpty() ? null : c;
		}
		return null;
	}
}
