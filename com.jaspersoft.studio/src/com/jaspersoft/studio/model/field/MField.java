/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeComboCellEditor;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.JPropertyExpressionsDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.HintsPropertiesList;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSValidatedTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPClassTypeCombo;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;

/*
 * The Class MField.
 * 
 * @author Chicu Veaceslav
 */
public class MField extends APropertyNode implements ICopyable, IDragable {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	private static IPropertyDescriptor[] descriptors;
	private static FieldNameValidator validator;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("field"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MField() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *            the parent
	 * @param jfRield
	 *            the jf rield
	 * @param newIndex
	 *            the new index
	 */
	public MField(ANode parent, JRDesignField jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	@Override
	public JRDesignField getValue() {
		return (JRDesignField) super.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRDesignField) getValue()).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		String tt = getIconDescriptor().getToolTip();
		String plabel = getValue().getPropertiesMap().getProperty(DataQueryAdapters.FIELD_LABEL);
		if (!Misc.isNullOrEmpty(plabel))
			tt += "\nLabel: " + plabel;
		return tt;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		// Set into the validator the actual reference
		validator.setTargetNode(this);
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		JPropertyExpressionsDescriptor propertiesD = new JPropertyExpressionsDescriptor(
				JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, Messages.MGraphicElement_property_expressions);
		propertiesD.setDescription(Messages.MGraphicElement_property_expressions_description);
		desc.add(propertiesD);
		propertiesD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#property"));

		validator = new FieldNameValidator();
		validator.setTargetNode(this);
		JSSTextPropertyDescriptor nameD = new JSSValidatedTextPropertyDescriptor(JRDesignField.PROPERTY_NAME,
				Messages.common_name, validator);
		nameD.setDescription(Messages.MField_name_description);
		desc.add(nameD);

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(JRDesignField.PROPERTY_VALUE_CLASS_NAME,
				Messages.common_class, ClassTypeComboCellEditor.DEFAULT_ITEMS) {
			@Override
			public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent,
					AbstractSection section) {
				SPClassTypeCombo<RWComboBoxPropertyDescriptor> classNameWidget = new SPClassTypeCombo<RWComboBoxPropertyDescriptor>(
						parent, section, this);
				classNameWidget.setClassesOfType(classes);
				classNameWidget.setReadOnly(readOnly);
				return classNameWidget;
			}
		};
		classD.setDescription(Messages.MField_class_description);
		desc.add(classD);
		classD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#field_class"));

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignField.PROPERTY_DESCRIPTION,
				Messages.common_description);
		descriptionD.setDescription(Messages.MField_description_description);
		desc.add(descriptionD);
		descriptionD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#fieldDescription"));

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#field");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.
	 * lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignField jrField = getValue();
		if (id.equals(JRDesignField.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignField.PROPERTY_VALUE_CLASS_NAME))
			return jrField.getValueClassName();
		if (id.equals(JRDesignField.PROPERTY_DESCRIPTION))
			return jrField.getDescription();
		if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			JRPropertyExpression[] propertyExpressions = jrField.getPropertyExpressions();
			if (propertyExpressions != null)
				propertyExpressions = propertyExpressions.clone();

			ExpressionContext ec = ModelUtils.getExpressionContext(this);
			JRDesignDataset d = ModelUtils.getDataset(this);
			if (d != null) {
				if (d.getQuery() != null)
					ec.getJasperReportsConfiguration().put(HintsPropertiesList.COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE,
							d.getQuery().getLanguage());
				else
					ec.getJasperReportsConfiguration()
							.remove(HintsPropertiesList.COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE);
			}
			return new PropertyExpressionsDTO(propertyExpressions, getPropertiesMapClone(jrField), getValue(), ec);
		}
		if (id.equals(PROPERTY_MAP))
			return getPropertiesMapClone(jrField);
		return null;
	}

	/**
	 * Return a copy of the properties map
	 * 
	 * @param jrField
	 *            field for where the map is read
	 * 
	 * @return copy of the properties map, if the map was null then it retrun null
	 */
	public static JRPropertiesMap getPropertiesMapClone(JRDesignField jrField) {
		JRPropertiesMap propertiesMap = jrField.getPropertiesMap();
		if (propertiesMap != null)
			propertiesMap = propertiesMap.cloneProperties();
		return propertiesMap;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (JRDesignParameter.PROPERTY_NAME.equals(evt.getPropertyName())) {
			JRDesignField jrField = (JRDesignField) getValue();
			JRDesignDataset d = ModelUtils.getDataset(this);
			if (d != null) {
				d.getFieldsMap().remove(evt.getOldValue());
				d.getFieldsMap().put(jrField.getName(), jrField);
			}
		}
		super.propertyChange(evt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.
	 * lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignField jrField = (JRDesignField) getValue();
		if (id.equals(JRDesignParameter.PROPERTY_NAME)) {
			if (!value.equals("")) {
				JRDesignDataset d = ModelUtils.getDataset(this);
				for (JRField p : d.getFieldsList()) {
					if (p == jrField)
						continue;
					if (p.getName().equals(value)) {
						// warn?
						return;
					}
				}
				jrField.setName((String) value);
			}
		} else if (id.equals(JRDesignParameter.PROPERTY_VALUE_CLASS_NAME)) {
			jrField.setValueClassName((String) value);
		} else if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION)) {
			if (StringUtils.isNullOrEmpty((String) value)) {
				jrField.setDescription(null);
			} else {
				jrField.setDescription((String) value);
			}
		} else if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			if (value instanceof PropertyExpressionsDTO) {
				PropertyExpressionsDTO dto = (PropertyExpressionsDTO) value;
				JRPropertyExpression[] expr = jrField.getPropertyExpressions();
				// Remove the old expression properties if any
				if (expr != null) {
					for (JRPropertyExpression ex : expr)
						jrField.removePropertyExpression(ex);
				}
				// Add the new expression properties
				for (PropertyExpressionDTO p : dto.getProperties()) {
					if (p.isExpression()) {
						JRDesignPropertyExpression newExp = new JRDesignPropertyExpression();
						newExp.setName(p.getName());
						newExp.setValueExpression(p.getValueAsExpression());
						jrField.addPropertyExpression(newExp);
					}
				}
				// now change properties, first remove the old ones if any
				JRPropertiesMap originalMap = jrField.getPropertiesMap().cloneProperties();
				String[] names = jrField.getPropertiesMap().getPropertyNames();
				for (int i = 0; i < names.length; i++) {
					jrField.getPropertiesMap().removeProperty(names[i]);
				}
				// now add the new properties
				for (PropertyExpressionDTO p : dto.getProperties()) {
					if (!p.isExpression()) {
						jrField.getPropertiesMap().setProperty(p.getName(), p.getValue());
					}
				}
				// really important to trigger the property with source the JR
				// object and not the node
				// using the node could cause problem with the refresh of the
				// advanced properties view
				firePropertyChange(
						new PropertyChangeEvent(jrField, PROPERTY_MAP, originalMap, jrField.getPropertiesMap()));
			}
		} else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrField.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrField.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrField.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, false, true);
		}
	}

	/**
	 * Creates the jr field.
	 * 
	 * @param jrDataset
	 *            the jr dataset
	 * @return the jR design field
	 */
	public static JRDesignField createJRField(JRDesignDataset jrDataset) {
		JRDesignField jrDesignField = new JRDesignField();
		jrDesignField.setName(ModelUtils.getDefaultName(jrDataset.getFieldsMap(), "Field_")); //$NON-NLS-1$
		return jrDesignField;
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MFields)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}

	public ExpressionContext getExpressionContext() {
		JRDesignDataset dataSet = ModelUtils.getDataset(this);
		JasperReportsConfiguration conf = getJasperConfiguration();
		if (dataSet != null && conf != null)
			return new ExpressionContext(dataSet, conf);
		return null;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (ExpressionContext.class.equals(adapter)) {
			return getExpressionContext();
		}
		return super.getAdapter(adapter);
	}

	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
