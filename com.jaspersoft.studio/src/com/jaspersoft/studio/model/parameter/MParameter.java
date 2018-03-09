/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.parameter;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionContext.Visibility;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.type.ParameterEvaluationTimeEnum;

/*
 * The Class MParameter.
 * 
 * @author Chicu Veaceslav
 */
public class MParameter extends MParameterSystem implements ICopyable {

	public static final String PROPERTY_MAP = "PROPERTY_MAP"; //$NON-NLS-1$
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	private static NamedEnumPropertyDescriptor<ParameterEvaluationTimeEnum> evaluationTimeD;
	// Must use its own descriptors since they are different from the ones of the
	// superclass
	private IPropertyDescriptor[] descriptors;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("parameter"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m parameter.
	 */
	public MParameter() {
		super();
	}

	/**
	 * Instantiates a new m parameter.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrParameter
	 *            the jr parameter
	 * @param newIndex
	 *            the new index
	 */
	public MParameter(ANode parent, JRDesignParameter jrParameter, int newIndex) {
		super(parent, jrParameter, newIndex);
	}

	@Override
	public Color getForeground() {
		return null;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignParameter.PROPERTY_DESCRIPTION,
				Messages.common_description);
		descriptionD.setDescription(Messages.MParameter_description_description);
		desc.add(descriptionD);
		descriptionD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#parameterDescription")); //$NON-NLS-1$

		CheckBoxPropertyDescriptor isForPromptingD = new CheckBoxPropertyDescriptor(
				JRDesignParameter.PROPERTY_FOR_PROMPTING, Messages.MParameter_is_for_prompting, NullEnum.NOTNULL);
		isForPromptingD.setDescription(Messages.MParameter_is_for_prompting_description);
		desc.add(isForPromptingD);

		JRExpressionPropertyDescriptor defValueExprD = new JRExpressionPropertyDescriptor(
				JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION, Messages.MParameter_default_value_expression);
		defValueExprD.setDescription(Messages.MParameter_default_value_expression_description);
		desc.add(defValueExprD);
		defValueExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#defaultValueExpression")); //$NON-NLS-1$

		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP,
				Messages.common_properties, getJasperConfiguration(), getValue());
		propertiesD.setDescription(Messages.MParameter_properties_description);
		desc.add(propertiesD);
		propertiesD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#property")); //$NON-NLS-1$

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(
				JRDesignParameter.PROPERTY_NESTED_TYPE_NAME, Messages.MParameter_nested_type_name);
		classD.setDescription(Messages.MParameter_nested_type_name_description);
		desc.add(classD);

		evaluationTimeD = new NamedEnumPropertyDescriptor<>(JRDesignParameter.PROPERTY_EVALUATION_TIME,
				Messages.common_evaluation_time, ParameterEvaluationTimeEnum.EARLY, NullEnum.NULL);
		evaluationTimeD.setDescription(Messages.MParameter_3);
		desc.add(evaluationTimeD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#parameter"); //$NON-NLS-1$
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(JRDesignParameter.PROPERTY_FOR_PROMPTING, new DefaultValue(Boolean.TRUE, false));
		defaultsMap.put(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME, new DefaultValue(null, true));
		defaultsMap.put(JRDesignParameter.PROPERTY_EVALUATION_TIME,
				new DefaultValue(ParameterEvaluationTimeEnum.LATE, false));

		return defaultsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.model.parameter.MParameterSystem#getPropertyValue(java.
	 * lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignParameter jrParameter = getValue();
		if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION))
			return jrParameter.getDescription();
		if (id.equals(JRDesignParameter.PROPERTY_FOR_PROMPTING))
			return jrParameter.isForPrompting();
		if (id.equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrParameter.getDefaultValueExpression());
		if (id.equals(PROPERTY_MAP)) {
			return jrParameter.getPropertiesMap();
		}
		if (id.equals(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME))
			return jrParameter.getNestedTypeName();
		if (id.equals(JRDesignParameter.PROPERTY_EVALUATION_TIME)) {
			if (evaluationTimeD == null)
				getPropertyDescriptors();
			return evaluationTimeD.getIntValue(jrParameter.getEvaluationTime());
		}
		return super.getPropertyValue(id);
	}

	public static JRPropertiesMap getPropertiesMapClone(JRPropertiesHolder jrField) {
		JRPropertiesMap propertiesMap = jrField.getPropertiesMap();
		if (propertiesMap != null)
			propertiesMap = propertiesMap.cloneProperties();
		return propertiesMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.model.parameter.MParameterSystem#setPropertyValue(java.
	 * lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignParameter jrParameter = getValue();

		if (id.equals(JRDesignParameter.PROPERTY_EVALUATION_TIME)) {
			if (evaluationTimeD == null)
				getPropertyDescriptors();
			jrParameter.setEvaluationTime(evaluationTimeD.getEnumValue(value));
		} else if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION)) {
			if (StringUtils.isNullOrEmpty((String) value)) {
				jrParameter.setDescription(null);
			} else {
				jrParameter.setDescription((String) value);
			}
		} else if (id.equals(JRDesignParameter.PROPERTY_FOR_PROMPTING) && isMainDataset()) {
			jrParameter.setForPrompting(((Boolean) value).booleanValue());
		} else if (id.equals(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME)) {
			jrParameter.setNestedTypeName((String) value);
		} else if (id.equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION)) {
			jrParameter.setDefaultValueExpression(ExprUtil.setValues(jrParameter.getDefaultValueExpression(), value));
		} else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrParameter.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrParameter.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrParameter.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, false, true);
		}
		super.setPropertyValue(id, value);
	}

	/**
	 * Creates the jr parameter.
	 * 
	 * @param jrDataset
	 *            the jr dataset
	 * @return the jR design parameter
	 */
	public static JRDesignParameter createJRParameter(JRDesignDataset jrDataset) {
		JRDesignParameter jrDesignParameter = new JRDesignParameter();
		jrDesignParameter.setSystemDefined(false);
		jrDesignParameter.setName(ModelUtils.getDefaultName(jrDataset.getParametersMap(), "Parameter")); //$NON-NLS-1$
		return jrDesignParameter;
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		for (IPropertyDescriptor d : descriptors) {
			if (d.getId().equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION)) {
				// fix the visibilities mask: allows only PARAMETERS
				ExpressionContext expContext = getExpressionContext();
				if (expContext != null) {
					expContext.setVisibilities(EnumSet.of(Visibility.SHOW_PARAMETERS));
					((IExpressionContextSetter) d).setExpressionContext(expContext);
				}
			}
		}
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MParameters)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}

	public JRDesignDataset getDataset() {
		return ModelUtils.getDataset(this);
	}

	public ExpressionContext getExpressionContext() {
		JRDesignDataset dataSet = ModelUtils.getDataset(this);
		JasperReportsConfiguration conf = getJasperConfiguration();
		if (dataSet != null && conf != null)
			return new ExpressionContext(dataSet, conf);
		return null;
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		setEditable(true);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (ExpressionContext.class.equals(adapter)) {
			return getExpressionContext();
		}
		return super.getAdapter(adapter);
	}

	public boolean isMainDataset() {
		if (getParent() instanceof MParameters) {
			MParameters<?> prms = (MParameters<?>) getParent();
			if (prms.getValue() instanceof JRDesignDataset)
				if (getJasperDesign().getMainDataset() == prms.getValue())
					return true;
		}
		return false;
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
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
