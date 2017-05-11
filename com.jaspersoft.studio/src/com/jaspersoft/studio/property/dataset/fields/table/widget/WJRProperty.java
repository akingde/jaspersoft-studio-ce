/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
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
import com.jaspersoft.studio.widgets.framework.ui.JRDataAdapterPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.JSSDataAdapterPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.LocaleComboPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TimezoneComboPropertyDescription;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.DatasetPropertyExpression;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
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

	private static Map<String, IWCallback> wmap = new HashMap<String, IWCallback>();

	public static void addCallback(String key, IWCallback wcnt) {
		wmap.put(key, wcnt);
	}

	@Override
	protected void initControl(final Composite parent, final TColumn c) {
		if (isPropertyExpressions(element)) {
			ItemPropertyDescription<?> ipd = null;
			String pname = c.getPropertyName();
			if (wmap.containsKey(c.getPropertyType())) {
				c.setValue(element);
				ipd = wmap.get(c.getPropertyType()).create(c);
			}
			if (c.getPropertyType().equals(Boolean.class.getName()))
				ipd = new ComboItemPropertyDescription<Boolean>(pname, c.getLabel(), c.getDescription(), false,
						Boolean.parseBoolean(c.getDefaultValue()), new String[] { "", "true", "false" });
			else if (c.getPropertyName().equals("net.sf.jasperreports.data.adapter")
					|| c.getPropertyType().equals(DataAdapter.class.getName()))
				ipd = new JRDataAdapterPropertyDescription(pname, c.getLabel(), c.getDescription(), false, getjConfig());
			else if (c.getPropertyType().equals(String.class.getName()))
				ipd = new TextPropertyDescription<String>(pname, c.getLabel(), c.getDescription(), false, c.getDefaultValue());
			else if (c.getPropertyType().equals(Class.class.getName()))
				ipd = new ClassItemPropertyDescription(pname, c.getLabel(), c.getDescription(), false, c.getDefaultValue(),
						new String[] {});
			else if (c.getPropertyType().equals(Integer.class.getName()) || c.getPropertyType().equals(Long.class.getName()))
				ipd = new IntegerPropertyDescription(pname, c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? Integer.parseInt(c.getDefaultValue()) : null, null, null);
			else if (c.getPropertyType().equals(BigDecimal.class.getName())
					|| c.getPropertyType().equals(Double.class.getName()))
				ipd = new BigDecimalPropertyDescription(c.getPropertyName(), c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? new BigDecimal(c.getDefaultValue()) : null, null, null);
			else if (c.getPropertyType().equals(Float.class.getName()))
				ipd = new FloatPropertyDescription(pname, c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? Float.parseFloat(c.getDefaultValue()) : null, null, null);
			else if (c.getPropertyType().equals(Color.class.getName()))
				ipd = new ColorPropertyDescription<Color>(pname, c.getLabel(), c.getDescription(), false,
						c.getDefaultValue() != null ? Color.decode(c.getDefaultValue()) : null);
			else if (c.getPropertyType().equals(TimeZone.class.getName()))
				ipd = new TimezoneComboPropertyDescription(pname, c.getLabel(), c.getDescription(), false, c.getDefaultValue());
			else if (c.getPropertyType().equals(Locale.class.getName()))
				ipd = new LocaleComboPropertyDescription(pname, c.getLabel(), c.getDescription(), false, c.getDefaultValue());
			else if (c.getPropertyType().equals("jssDA"))
				ipd = new JSSDataAdapterPropertyDescription(pname, c.getLabel(), c.getDescription(), false, getjConfig());
			else {
				try {
					Class<?> clazz = Class.forName(c.getPropertyType());
					if (clazz.isEnum()) {
						Object[] obj = clazz.getEnumConstants();
						String[] items = new String[obj.length];
						for (int i = 0; i < obj.length; i++)
							items[i] = obj[i].toString();
						ipd = new ComboItemPropertyDescription<String>(pname, c.getLabel(), c.getDescription(), false,
								c.getDefaultValue(), items);
					}
				} catch (ClassNotFoundException e) {
				}
				if (ipd == null)
					ipd = new TextPropertyDescription<String>(pname, c.getLabel(), c.getDescription(), false,
							c.getDefaultValue());
			}
			if (c.isLabelEditable() || (pname.contains("{") && pname.contains("}"))) {
				lblText = new Text(parent, SWT.BORDER);
				lblText.setText(Misc.nvl(c.getLabel(), pname));
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				if (!(pname.contains("{") && pname.contains("}"))) {
					gd.horizontalIndent = 20;
					lblText.addFocusListener(new FocusAdapter() {
						private ControlDecoration cd;

						@Override
						public void focusGained(FocusEvent e) {
							if (cd == null) {
								cd = new ControlDecoration(lblText, SWT.CENTER);
								cd.setDescriptionText("Remove property");
								cd.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/delete_style.gif"));
								cd.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										removePropertyExpression(element, getValue().getName());
										lblText.dispose();
										wip.dispose();
										parent.layout(true);
									}
								});
							}
							cd.show();
						}

						@Override
						public void focusLost(FocusEvent e) {
							cd.hide();
						}
					});
				}
				lblText.setLayoutData(gd);
				lblText.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						PropertyExpressionDTO dto = getValue();
						removePropertyExpression(element, dto.getName());
						c.setPropertyName(lblText.getText());
						dto.setName(lblText.getText());
						setValue(dto);
					}
				});

			} else {
				if (c.getPropertyMetadata().isDeprecated()) {
					lbl = new StyledText(parent, SWT.NONE);
					((StyledText) lbl).setText(Misc.nvl(c.getLabel(), pname));
					StyleRange styleRange = new StyleRange();
					styleRange.start = 0;
					styleRange.length = ((StyledText) lbl).getText().length();
					styleRange.strikeout = true;
					styleRange.fontStyle = SWT.ITALIC;
					((StyledText) lbl).setStyleRange(styleRange);
				} else {
					lbl = new Label(parent, SWT.NONE);
					((Label) lbl).setText(Misc.nvl(c.getLabel(), pname));
				}
			}
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
					if (value == null)
						return;
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
			if (lbl != null)
				lbl.setToolTipText(getToolTipText());
			if (lblText != null)
				lblText.setToolTipText(getToolTipText());
		} else
			super.initControl(parent, c);
	}

	private Control lbl;
	private Text lblText;

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
			if (c.getPropertyMetadata().isDeprecated())
				tt += "\nDeprecated\n";
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
				if (value instanceof PropertyExpressionDTO) {
					PropertyExpressionDTO pedto = (PropertyExpressionDTO) value;
					dto = new DatasetPropertyExpressionDTO(pedto.isExpression(), c.getPropertyName(),
							pedto.isExpression() ? pedto.getValueAsExpression().toString() : pedto.getValue(),
							PropertyEvaluationTimeEnum.LATE);
				} else
					dto = new DatasetPropertyExpressionDTO(value instanceof JRDesignExpression, c.getPropertyName(),
							value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString(),
							PropertyEvaluationTimeEnum.LATE);
			else {
				if (value instanceof PropertyExpressionDTO)
					dto = (PropertyExpressionDTO) value;
				else
					dto = new PropertyExpressionDTO(value instanceof JRDesignExpression, c.getPropertyName(),
							value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString());
			}
			((PropertyExpressionsDTO) element).getProperties().add(dto);
		} else if (element instanceof JRPropertiesMap) {
			JRPropertiesMap map = (JRPropertiesMap) element;
			map.setProperty(c.getPropertyName(), (String) value);
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
		} else if (element instanceof JRPropertiesMap) {
			JRPropertiesMap field = (JRPropertiesMap) element;
			String value = field.getProperty(c.getPropertyName());
			return new PropertyExpressionDTO(false, c.getPropertyName(), value);
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
		else if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO d = (PropertyExpressionsDTO) element;
			PropertyExpressionDTO toDel = null;
			for (PropertyExpressionDTO dto : d.getProperties()) {
				if (dto.getName().equals(name)) {
					toDel = dto;
					break;
				}
			}
			if (toDel != null) {
				d.removeProperty(toDel.getName(), toDel.isExpression());
				dto = null;
			}
		}
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