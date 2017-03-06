/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.column;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.descriptor.propexpr.JPropertyExpressionsCellEditor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;

public class JRPropertiesColumnSupport extends PropertyColumnSupport {
	private MDataset mdataset;

	public JRPropertiesColumnSupport(ColumnViewer viewer, TColumn c) {
		super(viewer, c);
		this.mdataset = (MDataset) c.getValue();
	}

	@Override
	protected CellEditor createCellEditor() {
		return new JPropertyExpressionsCellEditor((Composite) viewer.getControl());
	}

	@Override
	protected void setValue(Object element, Object value) {
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
			viewer.update(element, null);
		}
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof JRDesignField) {
			JRDesignField prop = (JRDesignField) element;
			JRPropertyExpression[] propertyExpressions = prop.getPropertyExpressions();
			if (propertyExpressions != null)
				propertyExpressions = propertyExpressions.clone();
			return new PropertyExpressionsDTO(propertyExpressions, MField.getPropertiesMapClone(prop), element,
					ModelUtils.getExpressionContext(mdataset));
		} else if (element instanceof JRDesignParameter) {
			JRDesignParameter prop = (JRDesignParameter) element;
			return new PropertyExpressionsDTO(null, MParameter.getPropertiesMapClone(prop), element,
					ModelUtils.getExpressionContext(mdataset));
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof JRDesignField) {
			JRDesignField field = (JRDesignField) element;
			int size = 0;
			JRPropertiesMap pmap = field.getPropertiesMap();
			if (pmap != null && pmap.getPropertyNames() != null)
				size += pmap.getPropertyNames().length;
			JRPropertyExpression[] pexp = field.getPropertyExpressions();
			if (pexp != null)
				size += field.getPropertyExpressions().length;
			return size == 0 ? "" : (size == 1 ? "1 Property" : size + " Properties");
		} else if (element instanceof JRDesignParameter) {
			JRDesignParameter field = (JRDesignParameter) element;
			int size = 0;
			JRPropertiesMap pmap = field.getPropertiesMap();
			if (pmap != null && pmap.getPropertyNames() != null)
				size += pmap.getPropertyNames().length;
			return size == 0 ? "" : (size == 1 ? "1 Property" : size + " Properties");
		}
		return "";
	}

}
