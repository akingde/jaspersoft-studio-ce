package com.jaspersoft.studio.crosstab.model.measure;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;

public class MMeasure extends APropertyNode implements IPropertySource, ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("measure");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MMeasure() {
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
	public MMeasure(ANode parent, JRCrosstabMeasure jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRCrosstabMeasure) getValue()).getName();
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
		return getIconDescriptor().getToolTip();
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		// JRDesignField jrField = (JRDesignField) getValue();
		// if (id.equals(JRDesignField.PROPERTY_NAME))
		// return jrField.getName();
		// if (id.equals(JRDesignField.PROPERTY_VALUE_CLASS_NAME))
		// return jrField.getValueClassName();
		// if (id.equals(JRDesignField.PROPERTY_DESCRIPTION))
		// return jrField.getDescription();
		// if (id.equals(PROPERTY_MAP))
		// return jrField.getPropertiesMap();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		// JRDesignField jrField = (JRDesignField) getValue();
		// if (id.equals(JRDesignParameter.PROPERTY_NAME))
		// jrField.setName((String) value);
		// else if (id.equals(JRDesignParameter.PROPERTY_VALUE_CLASS_NAME))
		// jrField.setValueClassName((String) value);
		// else if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION))
		// jrField.setDescription((String) value);
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MMeasures)
			return true;
		return false;
	}

}