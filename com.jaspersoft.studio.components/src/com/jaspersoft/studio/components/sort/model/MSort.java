/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.sort.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.sort.SortComponent;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.sort.SortNodeIconDescriptor;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.ImageHAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.ImageVAlignPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * 
 * @author veaceslav chicu
 * 
 */
public class MSort extends MGraphicElement {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static final String[] evalTimeEnumNames = EnumHelper.getEnumNames(EvaluationTimeEnum.values(), NullEnum.NOTNULL);
	
	private static NamedEnumPropertyDescriptor<SortFieldTypeEnum> sortFieldType;

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private RComboBoxPropertyDescriptor evaluationGroupNameD;
	
	private IPropertyDescriptor[] descriptors;

	private ImageHAlignPropertyDescriptor horizAlign;

	private ImageVAlignPropertyDescriptor vertAlign;
	
	public MSort() {
		super();
	}

	public MSort(ANode parent, JRDesignComponentElement jrObject, int newIndex) {
		super(parent, jrObject, newIndex);
	}

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.removePropertyChangeListener(this);
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	private Object getComponent() {
		return getComponent(getValue());
	}

	private Object getComponent(Object value) {
		if (value != null) {
			JRDesignComponentElement jrElement = (JRDesignComponentElement) value;
			return jrElement.getComponent();
		}
		return null;
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignComponentElement jrcomponent = new JRDesignComponentElement();
		SortComponent component = new SortComponent();
		component.setSortFieldType(SortFieldTypeEnum.FIELD);
		jrcomponent.setComponent(component);
		jrcomponent
				.setComponentKey(new ComponentKey(
						"http://jasperreports.sourceforge.net/jasperreports/components", "c", "sort")); //$NON-NLS-1$

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), jrcomponent);
		}

		return jrcomponent;
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new SortNodeIconDescriptor("sort"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String p = getElementNameProperty();
		return Misc.isNullOrEmpty(p) ? getIconDescriptor().getTitle() : p;
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

	/**
	 * @return the sortFieldType
	 */
	public static NamedEnumPropertyDescriptor<SortFieldTypeEnum> getSortFieldType() {
		if (sortFieldType == null) {
			new NamedEnumPropertyDescriptor<SortFieldTypeEnum>(
					SortComponent.PROPERTY_COLUMN_TYPE, "SortField Type",
					SortFieldTypeEnum.FIELD, NullEnum.NOTNULL);
			sortFieldType.setDescription("SortField type");
		}
		return sortFieldType;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
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

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				SortComponent.PROPERTY_EVALUATION_TIME,
				Messages.common_evaluation_time, EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD
				.setDescription(Messages.MGenericElement_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(
				SortComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MGenericElement_evaluation_group_name,
				new String[] { "" }); //$NON-NLS-2$
		evaluationGroupNameD
				.setDescription(Messages.MGenericElement_evaluation_group_name_description);
		desc.add(evaluationGroupNameD);

		ColorPropertyDescriptor color = new ColorPropertyDescriptor(
				SortComponent.PROPERTY_HANDLER_COLOR, "Handler Color",
				NullEnum.NULL);
		color.setDescription("Handler color");
		desc.add(color);

		horizAlign = new ImageHAlignPropertyDescriptor(
				SortComponent.PROPERTY_HANDLER_HORIZONTAL_ALIGN,
				"Handler Horizontal Alignement", NullEnum.NOTNULL);
		horizAlign.setDescription("Handler horizontal alignement");
		desc.add(horizAlign);

		vertAlign = new ImageVAlignPropertyDescriptor(
				SortComponent.PROPERTY_HANDLER_VERTICAL_ALIGN,
				"Handler Vertical Alignement", NullEnum.NOTNULL);
		vertAlign.setDescription("Handler vertical alignement");
		desc.add(vertAlign);

		sortFieldType = getSortFieldType();
		desc.add(sortFieldType);

		NTextPropertyDescriptor sortFieldName = new NTextPropertyDescriptor(
				SortComponent.PROPERTY_COLUMN_NAME, "SortField Name");
		sortFieldName.setDescription("SortField name");
		desc.add(sortFieldName);

		color.setCategory("Sort Properties");
		sortFieldType.setCategory("Sort Properties");
		sortFieldName.setCategory("Sort Properties");

		horizAlign.setCategory("Sort Properties");
		vertAlign.setCategory("Sort Properties");
		evaluationTimeD.setCategory("Sort Properties");
		evaluationGroupNameD.setCategory("Sort Properties");
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(SortComponent.PROPERTY_HANDLER_VERTICAL_ALIGN, new DefaultValue(VerticalImageAlignEnum.MIDDLE, false));
		defaultsMap.put(SortComponent.PROPERTY_HANDLER_HORIZONTAL_ALIGN, new DefaultValue(HorizontalImageAlignEnum.LEFT, false));
		defaultsMap.put(SortComponent.PROPERTY_EVALUATION_TIME, new DefaultValue(EvaluationTimeEnum.NOW, false));
		defaultsMap.put(SortComponent.PROPERTY_HANDLER_COLOR, new DefaultValue(null, true));
		defaultsMap.put(SortComponent.PROPERTY_COLUMN_TYPE, new DefaultValue(SortFieldTypeEnum.FIELD, false));
		
		return defaultsMap;
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		SortComponent component = (SortComponent) jrElement.getComponent();

		if (id.equals(SortComponent.PROPERTY_HANDLER_COLOR))
			return Colors.getSWTRGB4AWTGBColor(component.getHandlerColor());
		if (id.equals(SortComponent.PROPERTY_COLUMN_NAME))
			return component.getSortFieldName();
		if (id.equals(SortComponent.PROPERTY_COLUMN_TYPE))
			return sortFieldType.getIntValue(component.getSortFieldType());

		if (id.equals(SortComponent.PROPERTY_EVALUATION_TIME)){
			return EnumHelper.getEnumIndexByTranslatedName(evalTimeEnumNames, component.getEvaluationTime());
		}
		if (id.equals(SortComponent.PROPERTY_EVALUATION_GROUP))
			return component.getEvaluationGroup();
		if (id.equals(SortComponent.PROPERTY_HANDLER_HORIZONTAL_ALIGN))
			return horizAlign.getIntValue(component
					.getHandlerHorizontalImageAlign());
		if (id.equals(SortComponent.PROPERTY_HANDLER_VERTICAL_ALIGN))
			return vertAlign.getIntValue(component
					.getHandlerVerticalImageAlign());
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		SortComponent component = (SortComponent) jrElement.getComponent();

		if (id.equals(SortComponent.PROPERTY_HANDLER_COLOR))
			component.setHandlerColor(Colors
					.getAWT4SWTRGBColor((AlfaRGB) value));
		else if (id.equals(SortComponent.PROPERTY_COLUMN_NAME))
			component.setSortFieldName((String) value);
		else if (id.equals(SortComponent.PROPERTY_COLUMN_TYPE))
			component.setSortFieldType(sortFieldType.getEnumValue(value));

		else if (id.equals(SortComponent.PROPERTY_EVALUATION_TIME)){
			EvaluationTimeEnum evalTime = EnumHelper.getEnumByObjectValue(EvaluationTimeEnum.values(), value);
			component.setEvaluationTime(evalTime);
			if(evalTime != null && !evalTime.equals(EvaluationTimeEnum.GROUP)) {
				component.setEvaluationGroup(null);
			}
		}
		else if (id.equals(SortComponent.PROPERTY_EVALUATION_GROUP)) {
			component.setEvaluationGroup(ModelUtils.getGroupNameForProperty(value));
		} else if (id.equals(SortComponent.PROPERTY_HANDLER_HORIZONTAL_ALIGN)) {
			component.setHandlerHorizontalImageAlign(horizAlign
					.getEnumValue(value));
		} else if (id.equals(SortComponent.PROPERTY_HANDLER_VERTICAL_ALIGN)) {
			component.setHandlerVerticalImageAlign(vertAlign
					.getEnumValue(value));
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		SortComponent jrSourceSort = (SortComponent) jrSourceElement
				.getComponent();

		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		SortComponent jrTargetSort = (SortComponent) jrTargetElement
				.getComponent();

		jrTargetSort.setHandlerColor(getColorClone(jrSourceSort
				.getHandlerColor()));
		jrTargetSort.setHandlerHorizontalImageAlign(jrSourceSort
				.getHandlerHorizontalImageAlign());
		jrTargetSort.setHandlerVerticalImageAlign(jrSourceSort
				.getHandlerVerticalImageAlign());
	}
}
