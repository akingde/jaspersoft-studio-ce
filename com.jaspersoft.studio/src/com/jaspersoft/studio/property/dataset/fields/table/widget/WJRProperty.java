/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import java.awt.Color;
import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionDTO;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionsDTO;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.ui.BigDecimalPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ClassItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ColorPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FloatPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.IntegerPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.DatasetPropertyExpression;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.design.DesignDatasetPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.PropertyEvaluationTimeEnum;

public class WJRProperty extends AWidget {

	public WJRProperty(Composite parent, TColumn c, Object element, JasperReportsConfiguration jConfig) {
		super(parent, c, element, jConfig);
	}

	@Override
	protected void initControl(Composite parent, TColumn c) {
		if (isPropertyExpressions(element)) {
			ItemPropertyDescription<?> ipd = null;
			if (c.getPropertyType().equals(Boolean.class.getName()))
				ipd = new ComboItemPropertyDescription<Boolean>(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						Boolean.parseBoolean(c.getDefaultValue()), new String[] { "", "true", "false" });
			else if (c.getPropertyType().equals(String.class.getName()))
				ipd = new TextPropertyDescription<String>(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue());
			else if (c.getPropertyType().equals(Class.class.getName()))
				ipd = new ClassItemPropertyDescription(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue(), new String[] {});
			else if (c.getPropertyType().equals(Integer.class.getName()) || c.getPropertyType().equals(Long.class.getName()))
				ipd = new IntegerPropertyDescription(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? Integer.parseInt(c.getDefaultValue()) : null, null, null);
			else if (c.getPropertyType().equals(BigDecimal.class.getName())
					|| c.getPropertyType().equals(Double.class.getName()))
				ipd = new BigDecimalPropertyDescription(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? new BigDecimal(c.getDefaultValue()) : null, null, null);
			else if (c.getPropertyType().equals(Float.class.getName()))
				ipd = new FloatPropertyDescription(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? Float.parseFloat(c.getDefaultValue()) : null, null, null);
			else if (c.getPropertyType().equals(Color.class.getName()))
				ipd = new ColorPropertyDescription<Color>(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? Color.decode(c.getDefaultValue()) : null);
			else {
				try {
					Class<?> clazz = Class.forName(c.getPropertyType());
					if (clazz.isEnum()) {
						Object[] obj = clazz.getEnumConstants();
						String[] items = new String[obj.length];
						for (int i = 0; i < obj.length; i++)
							items[i] = obj[i].toString();
						ipd = new ComboItemPropertyDescription<String>(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
								c.getDefaultValue(), items);
					}
				} catch (ClassNotFoundException e) {
				}
				if (ipd == null)
					ipd = new TextPropertyDescription<String>(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
							c.getDefaultValue());
			}

			Label lbl = new Label(parent, SWT.NONE);
			lbl.setText(Misc.nvl(c.getLabel(), c.getPropertyName()));

			wip = new WItemProperty(parent, SWT.NONE, ipd, new IPropertyEditor() {

				@Override
				public String getPropertyValue(String propertyName) {
					PropertyExpressionDTO dto = getValue();
					if (!dto.isExpression())
						return getValue().getValue();
					return null;
				}

				@Override
				public JRExpression getPropertyValueExpression(String propertyName) {
					PropertyExpressionDTO dto = getValue();
					if (dto.isExpression())
						return getValue().getValueAsExpression();
					return null;
				}

				@Override
				public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
					PropertyExpressionDTO dto = getValue();
					dto.setValue(value);
					dto.setExpression(false);
					if (valueExpression != null) {
						dto.setExpression(true);
						dto.setValue(valueExpression.getText());
					}
					setValue(dto);
				}

				@Override
				public void removeProperty(String propertyName) {
					removePropertyExpression(element, propertyName);
				}
			});
			wip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			// Avoid to do the layout of the widget
			wip.updateWidget(false);

			lbl.setToolTipText(getToolTipText());
		} else
			super.initControl(parent, c);
	}

	@Override
	protected String getToolTipText() {
		if (isPropertyExpressions(element)) {
			String tt = wip.getStaticValue();
			if (tt == null) {
				JRExpression exp = wip.getExpressionValue();
				if (exp != null)
					tt = wip.getExpressionValue().getText();
			}
			if (!Misc.isNullOrEmpty(tt))
				tt += "\n\n";
			if (c.getType().equals("jrProperty"))
				tt += c.getPropertyName() + "\n";
			tt += "Type: " + c.getPropertyType();
			if (!Misc.isNullOrEmpty(c.getDescription())) {
				if (!Misc.isNullOrEmpty(tt))
					tt += "\n\n";
				tt += c.getDescription();
			}
			return tt;
		}
		return super.getToolTipText();
	}

	private WItemProperty wip;

	@Override
	public void setValue(Object value) {
		dto = null;
		if (element instanceof JRPropertiesHolder) {
			final JRPropertiesHolder field = (JRPropertiesHolder) element;
			if (isPropertyExpressions(element) && value instanceof PropertyExpressionDTO) {
				PropertyExpressionDTO dto = (PropertyExpressionDTO) value;
				if (dto.isExpression()) {
					if (dto.getValue() == null || dto.getValue().isEmpty())
						removePropertyExpression(element, c.getPropertyName());
					else {
						removePropertyExpression(element, c.getPropertyName());
						if (dto instanceof DatasetPropertyExpressionDTO)
							addPropertyExpression(element, c.getPropertyName(), dto.getValueAsExpression(),
									((DatasetPropertyExpressionDTO) dto).getEvalTime());
						else
							addPropertyExpression(element, c.getPropertyName(), dto.getValueAsExpression(), null);
					}
				} else {
					removePropertyExpression(element, c.getPropertyName());
					if (dto.getValue() == null || dto.getValue().isEmpty())
						field.getPropertiesMap().removeProperty(c.getPropertyName());
					else
						field.getPropertiesMap().setProperty(c.getPropertyName(), dto.getValue());
				}
			} else {
				if (value == null || value.toString().isEmpty())
					field.getPropertiesMap().removeProperty(c.getPropertyName());
				else
					field.getPropertiesMap().setProperty(c.getPropertyName(), value.toString());
			}
		} else if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO d = (PropertyExpressionsDTO) element;
			for (PropertyExpressionDTO dto : d.getProperties())
				if (dto.getName().equals(c.getPropertyName())) {
					dto.setExpression(value instanceof JRDesignExpression);
					dto.setValue(value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString());
					return;
				}
			PropertyExpressionDTO dto = null;
			if (element instanceof DatasetPropertyExpressionsDTO)
				dto = new DatasetPropertyExpressionDTO(value instanceof JRDesignExpression, c.getPropertyName(),
						value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString(),
						PropertyEvaluationTimeEnum.LATE);
			else
				dto = new PropertyExpressionDTO(value instanceof JRDesignExpression, c.getPropertyName(),
						value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString());
			((PropertyExpressionsDTO) element).getProperties().add(dto);
		}
	}

	private PropertyExpressionDTO dto;

	@Override
	protected PropertyExpressionDTO getValue() {
		if (dto != null)
			return dto;
		if (element instanceof JRPropertiesHolder) {
			JRPropertiesHolder field = (JRPropertiesHolder) element;
			boolean isExpression = false;
			String value = field.getPropertiesMap().getProperty(c.getPropertyName());
			if (isPropertyExpressions(element)) {
				JRPropertyExpression[] pexps = getPropertyExpressions(element);
				if (pexps != null)
					for (JRPropertyExpression pe : pexps)
						if (pe.getName().equals(c.getPropertyName()) && pe.getValueExpression() != null) {
							isExpression = true;
							value = pe.getValueExpression().getText();
							if (pe instanceof DatasetPropertyExpression) {
								dto = new DatasetPropertyExpressionDTO(isExpression, c.getPropertyName(), value,
										((DatasetPropertyExpression) pe).getEvaluationTime());
								return dto;
							}
						}
			}
			return new PropertyExpressionDTO(isExpression, c.getPropertyName(), value);
		} else if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO d = (PropertyExpressionsDTO) element;
			for (PropertyExpressionDTO pe : d.getProperties())
				if (pe.getName().equals(c.getPropertyName())) {
					dto = pe;
					return dto;
				}
		}
		dto = new PropertyExpressionDTO(false, c.getPropertyName(), "");
		return dto;
	}

	public boolean isPropertyExpressions(Object element) {
		if (element instanceof JRDesignField || element instanceof JRElement || element instanceof JRReport
				|| element instanceof JRDataset || element instanceof PropertyExpressionsDTO)
			return true;
		return false;
	}

	public JRPropertyExpression[] getPropertyExpressions(Object element) {
		if (element instanceof JRDesignField)
			return ((JRField) element).getPropertyExpressions();
		else if (element instanceof JRElement)
			return ((JRElement) element).getPropertyExpressions();
		else if (element instanceof JRReport)
			return ((JRReport) element).getPropertyExpressions();
		else if (element instanceof JRDataset)
			return ((JRDataset) element).getPropertyExpressions();
		return null;
	}

	public void removePropertyExpression(Object element, String name) {
		if (element instanceof JRDesignField)
			((JRDesignField) element).removePropertyExpression(name);
		else if (element instanceof JRElement)
			((JRDesignElement) element).removePropertyExpression(name);
		else if (element instanceof JasperDesign)
			((JasperDesign) element).removePropertyExpression(name);
		else if (element instanceof JRDesignDataset)
			((JRDesignDataset) element).removePropertyExpression(name);
	}

	public void addPropertyExpression(Object element, String name, JRExpression exp, PropertyEvaluationTimeEnum pet) {
		if (element instanceof JRDesignField) {
			JRDesignPropertyExpression pe = new JRDesignPropertyExpression();
			pe.setName(c.getPropertyName());
			pe.setValueExpression(exp);
			((JRDesignField) element).addPropertyExpression(pe);
		} else if (element instanceof JRElement) {
			JRDesignPropertyExpression pe = new JRDesignPropertyExpression();
			pe.setName(c.getPropertyName());
			pe.setValueExpression(exp);
			((JRDesignElement) element).addPropertyExpression(pe);
		} else if (element instanceof JasperDesign) {
			DesignDatasetPropertyExpression pe = new DesignDatasetPropertyExpression();
			pe.setName(c.getPropertyName());
			pe.setValueExpression(exp);
			pe.setEvaluationTime(pet);
			((JasperDesign) element).addPropertyExpression(pe);
		} else if (element instanceof JRDesignDataset) {
			DesignDatasetPropertyExpression pe = new DesignDatasetPropertyExpression();
			pe.setName(c.getPropertyName());
			pe.setValueExpression(exp);
			pe.setEvaluationTime(pet);
			((JRDesignDataset) element).addPropertyExpression(pe);
		}
	}
}