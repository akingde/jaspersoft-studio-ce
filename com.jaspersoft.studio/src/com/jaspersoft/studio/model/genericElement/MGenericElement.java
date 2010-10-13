package com.jaspersoft.studio.model.genericElement;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRGenericElementParameter;
import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.genericElement.ParameterPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.genericElement.dialog.ParameterDTO;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MGenericElement extends MGraphicElement {
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("generic");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m cross tab.
	 */
	public MGenericElement() {
		super();
	}

	/**
	 * Instantiates a new m cross tab.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrCrosstab
	 *          the jr crosstab
	 * @param newIndex
	 *          the new index
	 */
	public MGenericElement(ANode parent, JRDesignGenericElement jrCrosstab, int newIndex) {
		super(parent, newIndex);
		setValue(jrCrosstab);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private RComboBoxPropertyDescriptor evaluationGroupNameD;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				JRDesignGenericElement.PROPERTY_EVALUATION_TIME, "Evaluation Time", EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD.setDescription("When the element expression should be evaluated.");
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(JRDesignGenericElement.PROPERTY_EVALUATION_GROUP_NAME,
				"Evaluation Group Name", new String[] { "" });
		evaluationGroupNameD.setDescription("The element will be evaluated when the specified group changes.");
		desc.add(evaluationGroupNameD);

		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(JRDesignGenericElement.PROPERTY_GENERIC_TYPE
				+ PROPERTY_NAME, "Generic Type Name");
		nameD.setDescription("Generic type name.");
		desc.add(nameD);

		NTextPropertyDescriptor nameSpaceD = new NTextPropertyDescriptor(JRDesignGenericElement.PROPERTY_GENERIC_TYPE
				+ PROPERTY_NAMESPACE, "Generic Type Namespace");
		nameSpaceD.setDescription("Generic type namespace.");
		desc.add(nameSpaceD);

		ParameterPropertyDescriptor parametersD = new ParameterPropertyDescriptor(
				JRDesignGenericElement.PROPERTY_PARAMETERS, "Parameters");
		parametersD.setDescription("The datasource expression.");
		desc.add(parametersD);

		parametersD.setCategory("GenericElement Properties");
		nameD.setCategory("GenericElement Properties");
		nameSpaceD.setCategory("GenericElement Properties");
		evaluationTimeD.setCategory("GenericElement Properties");
		evaluationGroupNameD.setCategory("GenericElement Properties");

	}

	public static final String PROPERTY_NAME = "NAME";
	public static final String PROPERTY_NAMESPACE = "NAMESPACE";
	private ParameterDTO propertyDTO;

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignGenericElement jrElement = (JRDesignGenericElement) getValue();
		if (id.equals(JRDesignGenericElement.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue(), 1, false);
		if (id.equals(JRDesignGenericElement.PROPERTY_EVALUATION_GROUP_NAME))
			return jrElement.getEvaluationGroupName();
		JRGenericElementType genericType = jrElement.getGenericType();
		if (genericType != null) {
			if (id.equals(JRDesignGenericElement.PROPERTY_GENERIC_TYPE + PROPERTY_NAME))
				return genericType.getName();
			if (id.equals(JRDesignGenericElement.PROPERTY_GENERIC_TYPE + PROPERTY_NAMESPACE))
				return genericType.getNamespace();
		}
		if (id.equals(JRDesignGenericElement.PROPERTY_PARAMETERS)) {
			if (propertyDTO == null) {
				propertyDTO = new ParameterDTO();
				propertyDTO.setJasperDesign(getJasperDesign());
				propertyDTO.setValue(jrElement.getParameters());
			}
			return propertyDTO;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignGenericElement jrElement = (JRDesignGenericElement) getValue();
		JRGenericElementType genericType = jrElement.getGenericType();
		if (id.equals(JRDesignGenericElement.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1,
					false));
		else if (id.equals(JRDesignGenericElement.PROPERTY_EVALUATION_GROUP_NAME))
			jrElement.setEvaluationGroupName((String) value);
		else if (id.equals(JRDesignGenericElement.PROPERTY_PARAMETERS)) {
			if (value instanceof ParameterDTO) {
				ParameterDTO v = (ParameterDTO) value;

				for (JRGenericElementParameter prm : propertyDTO.getValue())
					jrElement.removeParameter(prm);

				for (JRGenericElementParameter param : v.getValue())
					jrElement.addParameter(param);

				propertyDTO = v;
			}
		}

		else if (genericType != null) {
			if (id.equals(JRDesignGenericElement.PROPERTY_GENERIC_TYPE + PROPERTY_NAME)) {
				jrElement.setGenericType(new JRGenericElementType(genericType.getNamespace(), (String) value));
			} else if (id.equals(JRDesignGenericElement.PROPERTY_GENERIC_TYPE + PROPERTY_NAMESPACE)) {
				jrElement.setGenericType(new JRGenericElementType((String) value, genericType.getName()));
			}
		} else
			super.setPropertyValue(id, value);
	}
}
