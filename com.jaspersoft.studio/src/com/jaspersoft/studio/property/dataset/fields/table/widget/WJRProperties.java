/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;

public class WJRProperties extends AWidget {
	private MDataset mdataset;

	public WJRProperties(Composite parent, TColumn c, final Object element, JasperReportsConfiguration jConfig) {
		super(parent, c, element, jConfig);
		this.mdataset = (MDataset) c.getValue();
	}

	@Override
	public void setValue(Object value) {
		if (element != null && value instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO dto = (PropertyExpressionsDTO) value;
			if (element instanceof JRDesignField) {
				JRDesignField field = (JRDesignField) element;
				JRPropertyExpression[] expr = field.getPropertyExpressions();
				// Remove the old expression properties if any
				if (expr != null)
					for (JRPropertyExpression ex : expr)
						field.removePropertyExpression(ex);
				// Add the new expression properties
				for (PropertyExpressionDTO p : dto.getProperties())
					if (p.isExpression()) {
						JRDesignPropertyExpression newExp = new JRDesignPropertyExpression();
						newExp.setName(p.getName());
						newExp.setValueExpression(p.getValueAsExpression());
						field.addPropertyExpression(newExp);
					}
			}
			if (element instanceof JRPropertiesHolder) {
				JRPropertiesHolder field = (JRPropertiesHolder) element;
				String[] names = field.getPropertiesMap().getPropertyNames();
				for (int i = 0; i < names.length; i++)
					field.getPropertiesMap().removeProperty(names[i]);
				for (PropertyExpressionDTO p : dto.getProperties())
					if (!p.isExpression())
						field.getPropertiesMap().setProperty(p.getName(), p.getValue());
			}
		}
	}

	@Override
	protected Object getValue() {
		if (element instanceof JRDesignField) {
			JRDesignField prop = (JRDesignField) element;
			JRPropertyExpression[] propertyExpressions = prop.getPropertyExpressions();
			if (propertyExpressions != null)
				propertyExpressions = propertyExpressions.clone();
			return new PropertyExpressionsDTO(propertyExpressions, MField.getPropertiesMapClone(prop), element,
					ModelUtils.getExpressionContext(mdataset));
		} else if (element instanceof JRPropertiesHolder)
			return new PropertyExpressionsDTO(null, MParameter.getPropertiesMapClone((JRPropertiesHolder) element),
					element, ModelUtils.getExpressionContext(mdataset));
		else if (element instanceof JRPropertiesMap)
			return new PropertyExpressionsDTO(null, ((JRPropertiesMap) element).cloneProperties(), element,
					ModelUtils.getExpressionContext(mdataset));
		return null;
	}

}