/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionDTO;
import com.jaspersoft.studio.model.dataset.DatasetPropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.HintsPropertiesList;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.ItemPropertyLayoutData;
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
import net.sf.jasperreports.properties.PropertyMetadata;

/**
 * {@link ASPropertyWidget} used to provide a widget to edit a textual property or an expression property of an element.
 * The type of the widget depend from the property expected value, so string properties will have a text widget, numeric
 * properties a numeric widget and so on. The widget come from the widget framework
 * 
 * @author Orlandin Marco
 */
public class ASPropertyExpressionWidget extends ASPropertyWidget<ExpressionPropertyDescriptor> {

	/**
	 * The name of the edited property
	 */
	private String propertyName;

	/**
	 * The dto of the edited property
	 */
	private PropertyExpressionDTO dto;

	/**
	 * The widget wrapper used to edit the property
	 */
	private WItemProperty wip;

	/**
	 * The JR element to which the property belong
	 */
	private Object element;

	/**
	 * Create the widget
	 * 
	 * @param parent
	 *          parent composite of the widget
	 * @param section
	 *          section where the widget is placed
	 * @param pDescriptor
	 *          the descriptor of this widget
	 * @param propertyName
	 *          the name of the handled property
	 */
	public ASPropertyExpressionWidget(Composite parent, AbstractSection section, ExpressionPropertyDescriptor pDescriptor,
			String propertyName) {
		super(parent, section, pDescriptor);
		this.propertyName = propertyName;
		createComponent(parent);
		if (getControl() != null)
			setupFocusControl(pDescriptor, getControl());
	}

	@Override
	protected void createComponent(Composite parent) {
		// need to check this to avoid this method is called by the super constructor before the name is set
		if (propertyName != null) {
			// get the metadata of the property
			element = section.getElement().getValue();
			List<PropertyMetadata> propertiesInfo = HintsPropertiesList.getPropertiesMetadata(element,
					section.getElement().getExpressionContext());
			PropertyMetadata propertyInfo = null;
			for (PropertyMetadata property : propertiesInfo) {
				if (propertyName.equalsIgnoreCase(property.getName())) {
					propertyInfo = property;
					break;
				}
			}
			// get the widget of the property
			ItemPropertyDescription<?> ipd = getWidget(propertyInfo);
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
					SetDtoCommand command = new SetDtoCommand(dto);
					CommandStack cs = section.getEditDomain().getCommandStack();
					cs.execute(command);
				}

				@Override
				public void removeProperty(String propertyName) {
					removePropertyExpression(element, propertyName);
				}
			});
			wip.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			ItemPropertyLayoutData wipLayoutData = new ItemPropertyLayoutData();
			wipLayoutData.leftMargin = 0;
			wip.setContentLayoutData(wipLayoutData);
			// Avoid to do the layout of the widget
			wip.updateWidget(false);
		}

	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		dto = null;
		element = pnode.getValue();
		getValue();
		if (wip != null)
			wip.updateWidget();
	}

	@Override
	public Control getControl() {
		return wip;
	}

	@Override
	public Control getControlToBorder() {
		return wip != null ? wip.getControl() : null;
	}

	/**
	 * Using the metadata description of the property return the appropriate widget to handle its type
	 * 
	 * @param propertyMetadata
	 *          the metadata of the property, if null it will used a text widget that is the most basic widget
	 * @return a not null {@link ItemPropertyDescription} used to handle the property
	 */
	protected ItemPropertyDescription<?> getWidget(PropertyMetadata propertyMetadata) {
		ItemPropertyDescription<?> ipd = null;
		String propertyType = propertyMetadata != null ? propertyMetadata.getValueType() : String.class.getName();
		if (propertyType.equals(Boolean.class.getName()))
			ipd = new ComboItemPropertyDescription<Boolean>(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, Boolean.parseBoolean(propertyMetadata.getDefaultValue()),
					new String[] { "", "true", "false" });
		else if (propertyName.equals("net.sf.jasperreports.data.adapter")
				|| propertyType.equals(DataAdapter.class.getName()))
			ipd = new JRDataAdapterPropertyDescription(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, section.getElement().getJasperConfiguration());
		else if (propertyType.equals(String.class.getName()))
			ipd = new TextPropertyDescription<String>(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, propertyMetadata.getDefaultValue());
		else if (propertyType.equals(Class.class.getName()))
			ipd = new ClassItemPropertyDescription(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, propertyMetadata.getDefaultValue(), new String[] {});
		else if (propertyType.equals(Integer.class.getName()) || propertyType.equals(Long.class.getName()))
			ipd = new IntegerPropertyDescription(propertyName, propertyMetadata.getLabel(), propertyMetadata.getDescription(),
					false,
					propertyMetadata.getDefaultValue() != null ? Integer.parseInt(propertyMetadata.getDefaultValue()) : null,
					null, null);
		else if (propertyType.equals(BigDecimal.class.getName()) || propertyType.equals(Double.class.getName()))
			ipd = new BigDecimalPropertyDescription(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false,
					propertyMetadata.getDefaultValue() != null ? new BigDecimal(propertyMetadata.getDefaultValue()) : null, null,
					null);
		else if (propertyType.equals(Float.class.getName()))
			ipd = new FloatPropertyDescription(propertyName, propertyMetadata.getLabel(), propertyMetadata.getDescription(),
					false,
					propertyMetadata.getDefaultValue() != null ? Float.parseFloat(propertyMetadata.getDefaultValue()) : null,
					null, null);
		else if (propertyType.equals(Color.class.getName()))
			ipd = new ColorPropertyDescription<Color>(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false,
					propertyMetadata.getDefaultValue() != null ? Color.decode(propertyMetadata.getDefaultValue()) : null);
		else if (propertyType.equals(TimeZone.class.getName()))
			ipd = new TimezoneComboPropertyDescription(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, propertyMetadata.getDefaultValue());
		else if (propertyType.equals(Locale.class.getName()))
			ipd = new LocaleComboPropertyDescription(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, propertyMetadata.getDefaultValue());
		else if (propertyType.equals("jssDA"))
			ipd = new JSSDataAdapterPropertyDescription(propertyName, propertyMetadata.getLabel(),
					propertyMetadata.getDescription(), false, section.getElement().getJasperConfiguration());
		else {
			try {
				Class<?> clazz = Class.forName(propertyType);
				if (clazz.isEnum()) {
					Object[] obj = clazz.getEnumConstants();
					String[] items = new String[obj.length];
					for (int i = 0; i < obj.length; i++)
						items[i] = obj[i].toString();
					ipd = new ComboItemPropertyDescription<String>(propertyName, propertyMetadata.getLabel(),
							propertyMetadata.getDescription(), false, propertyMetadata.getDefaultValue(), items);
				}
			} catch (ClassNotFoundException e) {
			}
			if (ipd == null)
				ipd = new TextPropertyDescription<String>(propertyName, propertyMetadata.getLabel(),
						propertyMetadata.getDescription(), false, propertyMetadata.getDefaultValue());
		}
		return ipd;
	}

	public void setValue(Object value) {
		dto = null;
		if (element instanceof JRPropertiesHolder) {
			final JRPropertiesHolder field = (JRPropertiesHolder) element;
			if (isPropertyExpressions(element) && value instanceof PropertyExpressionDTO) {
				PropertyExpressionDTO dto = (PropertyExpressionDTO) value;
				if (dto.isExpression()) {
					if (dto.getValue() == null || dto.getValue().isEmpty())
						removePropertyExpression(element, propertyName);
					else {
						removePropertyExpression(element, propertyName);
						if (dto instanceof DatasetPropertyExpressionDTO)
							addPropertyExpression(element, propertyName, dto.getValueAsExpression(),
									((DatasetPropertyExpressionDTO) dto).getEvalTime());
						else
							addPropertyExpression(element, propertyName, dto.getValueAsExpression(), null);
					}
				} else {
					removePropertyExpression(element, propertyName);
					if (dto.getValue() == null || dto.getValue().isEmpty())
						field.getPropertiesMap().removeProperty(propertyName);
					else
						field.getPropertiesMap().setProperty(propertyName, dto.getValue());
				}
			} else {
				if (value == null || value.toString().isEmpty())
					field.getPropertiesMap().removeProperty(propertyName);
				else
					field.getPropertiesMap().setProperty(propertyName, value.toString());
			}
		} else if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO d = (PropertyExpressionsDTO) element;
			for (PropertyExpressionDTO dto : d.getProperties())
				if (dto.getName().equals(propertyName)) {
					dto.setExpression(value instanceof JRDesignExpression);
					dto.setValue(value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString());
					return;
				}
			PropertyExpressionDTO dto = null;
			if (element instanceof DatasetPropertyExpressionsDTO)
				if (value instanceof PropertyExpressionDTO) {
					PropertyExpressionDTO pedto = (PropertyExpressionDTO) value;
					dto = new DatasetPropertyExpressionDTO(pedto.isExpression(), propertyName,
							pedto.isExpression() ? pedto.getValueAsExpression().toString() : pedto.getValue(),
							PropertyEvaluationTimeEnum.LATE);
				} else
					dto = new DatasetPropertyExpressionDTO(value instanceof JRDesignExpression, propertyName,
							value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString(),
							PropertyEvaluationTimeEnum.LATE);
			else {
				if (value instanceof PropertyExpressionDTO)
					dto = (PropertyExpressionDTO) value;
				else
					dto = new PropertyExpressionDTO(value instanceof JRDesignExpression, propertyName,
							value instanceof JRDesignExpression ? ((JRDesignExpression) value).getText() : value.toString());
			}
			((PropertyExpressionsDTO) element).getProperties().add(dto);
		} else if (element instanceof JRPropertiesMap) {
			JRPropertiesMap map = (JRPropertiesMap) element;
			map.setProperty(propertyName, (String) value);
		}
	}

	protected PropertyExpressionDTO getValue() {
		if (dto != null)
			return dto;
		if (element instanceof JRPropertiesHolder) {
			JRPropertiesHolder field = (JRPropertiesHolder) element;
			boolean isExpression = false;
			String value = field.getPropertiesMap().getProperty(propertyName);
			if (isPropertyExpressions(element)) {
				JRPropertyExpression[] pexps = getPropertyExpressions(element);
				if (pexps != null)
					for (JRPropertyExpression pe : pexps)
						if (pe.getName().equals(propertyName) && pe.getValueExpression() != null) {
							isExpression = true;
							value = pe.getValueExpression().getText();
							if (pe instanceof DatasetPropertyExpression) {
								dto = new DatasetPropertyExpressionDTO(isExpression, propertyName, value,
										((DatasetPropertyExpression) pe).getEvaluationTime());
								return dto;
							}
						}
			}
			return new PropertyExpressionDTO(isExpression, propertyName, value);
		} else if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO d = (PropertyExpressionsDTO) element;
			for (PropertyExpressionDTO pe : d.getProperties())
				if (pe.getName().equals(propertyName)) {
					dto = pe;
					return dto;
				}
		} else if (element instanceof JRPropertiesMap) {
			JRPropertiesMap field = (JRPropertiesMap) element;
			String value = field.getProperty(propertyName);
			return new PropertyExpressionDTO(false, propertyName, value);
		}
		dto = new PropertyExpressionDTO(false, propertyName, "");
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
			if (toDel != null)
				d.getProperties().remove(toDel);
		}
	}

	public void addPropertyExpression(Object element, String name, JRExpression exp, PropertyEvaluationTimeEnum pet) {
		if (element instanceof JRDesignField) {
			JRDesignPropertyExpression pe = new JRDesignPropertyExpression();
			pe.setName(propertyName);
			pe.setValueExpression(exp);
			((JRDesignField) element).addPropertyExpression(pe);
		} else if (element instanceof JRElement) {
			JRDesignPropertyExpression pe = new JRDesignPropertyExpression();
			pe.setName(propertyName);
			pe.setValueExpression(exp);
			((JRDesignElement) element).addPropertyExpression(pe);
		} else if (element instanceof JasperDesign) {
			DesignDatasetPropertyExpression pe = new DesignDatasetPropertyExpression();
			pe.setName(propertyName);
			pe.setValueExpression(exp);
			pe.setEvaluationTime(pet);
			((JasperDesign) element).addPropertyExpression(pe);
		} else if (element instanceof JRDesignDataset) {
			DesignDatasetPropertyExpression pe = new DesignDatasetPropertyExpression();
			pe.setName(propertyName);
			pe.setValueExpression(exp);
			pe.setEvaluationTime(pet);
			((JRDesignDataset) element).addPropertyExpression(pe);
		}
	}

	/**
	 * Command used to change the value of the property. Using a command to do this allow the undo of the set of the value
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class SetDtoCommand extends Command {

		/**
		 * The DTO value to set on the property
		 */
		private Object newValue;

		/**
		 * The DTO value of the property before the set, used for the undo
		 */
		private PropertyExpressionDTO oldValue;

		/**
		 * The value of the DTO used to set the property
		 * 
		 * @param newValue
		 *          a not null DTO value
		 */
		public SetDtoCommand(Object newValue) {
			this.newValue = newValue;
		}

		@Override
		public void execute() {
			// store the old value
			oldValue = getValue();
			setValue(newValue);
			// really important to trigger the property with source the JR object and not the node
			// using the node could cause problem with the refresh of the advanced properties view
			section.getElement().propertyChange(
					new PropertyChangeEvent(section.getElement().getValue(), MGraphicElement.PROPERTY_MAP, newValue, oldValue));
		}

		@Override
		public void undo() {
			setValue(oldValue);
			section.getElement().propertyChange(
					new PropertyChangeEvent(section.getElement().getValue(), MGraphicElement.PROPERTY_MAP, newValue, oldValue));
		}

		@Override
		public void redo() {
			setValue(newValue);
			section.getElement().propertyChange(
					new PropertyChangeEvent(section.getElement().getValue(), MGraphicElement.PROPERTY_MAP, newValue, oldValue));
		}
	}

}
