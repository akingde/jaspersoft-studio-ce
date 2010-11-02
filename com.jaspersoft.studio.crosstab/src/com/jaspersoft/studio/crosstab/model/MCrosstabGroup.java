package com.jaspersoft.studio.crosstab.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public abstract class MCrosstabGroup extends APropertyNode implements IPropertySource {

	/**
	 * Instantiates a new m field.
	 */
	public MCrosstabGroup() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MCrosstabGroup(ANode parent, JRCrosstabGroup jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRCrosstabGroup) getValue()).getName();
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

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

		ComboBoxPropertyDescriptor totalPositionD = new ComboBoxPropertyDescriptor(
				JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION, "Total Position", EnumHelper.getEnumNames(
						CrosstabTotalPositionEnum.values(), NullEnum.NOTNULL));
		totalPositionD.setDescription("Total Position.");
		desc.add(totalPositionD);

		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(JRDesignCrosstabGroup.PROPERTY_NAME, "Name");
		nameD.setDescription("Name");
		desc.add(nameD);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabGroup jrField = (JRDesignCrosstabGroup) getValue();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION))
			return EnumHelper.getValue(jrField.getTotalPositionValue(), 0, false);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabGroup jrField = (JRDesignCrosstabGroup) getValue();
		if (id.equals(JRDesignCrosstabGroup.PROPERTY_NAME))
			jrField.setName((String) value);
		else if (id.equals(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION))
			jrField.setTotalPosition((CrosstabTotalPositionEnum) EnumHelper.getSetValue(CrosstabTotalPositionEnum.values(),
					value, 0, false));
	}

}