/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.base.JRBaseLine;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.LineDirectionEnum;

/*
 * The Class MLine.
 */
public class MLine extends MGraphicElementLinePen {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	
	private static IPropertyDescriptor[] descriptors;
	
	private static NamedEnumPropertyDescriptor<LineDirectionEnum> directionD;
	
	private static NamedEnumPropertyDescriptor<FillEnum> fillD;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("line"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m line.
	 */
	public MLine() {
		super();
	}

	/**
	 * Instantiates a new m line.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrLine
	 *          the jr line
	 * @param newImage
	 *          the new image
	 */
	public MLine(ANode parent, JRDesignLine jrLine, int newImage) {
		super(parent, newImage);
		setValue(jrLine);
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
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		directionD = new NamedEnumPropertyDescriptor<LineDirectionEnum>(JRBaseLine.PROPERTY_DIRECTION, Messages.MLine_direction, LineDirectionEnum.BOTTOM_UP, NullEnum.NULL);
		directionD.setDescription(Messages.MLine_direction_description);
		directionD.setCategory(Messages.MLine_line_category);
		desc.add(directionD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#line");
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		int directionValue = NamedEnumPropertyDescriptor.getIntValue(LineDirectionEnum.TOP_DOWN, NullEnum.NULL, LineDirectionEnum.TOP_DOWN);
		defaultsMap.put(JRBaseLine.PROPERTY_DIRECTION, new DefaultValue(directionValue, true));
		defaultsMap.put(JRBaseStyle.PROPERTY_FILL, new DefaultValue(null, true));
		
		return defaultsMap;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignLine jrElement = (JRDesignLine) getValue();
		if (id.equals(JRBaseLine.PROPERTY_DIRECTION))
			return directionD.getIntValue(jrElement.getDirectionValue());
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			return fillD.getIntValue(jrElement.getOwnFillValue());
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignLine jrElement = (JRDesignLine) getValue();
		if (id.equals(JRBaseLine.PROPERTY_DIRECTION))
			jrElement.setDirection(directionD.getEnumValue(value));
		if (id.equals(JRBaseStyle.PROPERTY_FILL))
			jrElement.setFill(fillD.getEnumValue(value));
		else
			super.setPropertyValue(id, value);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 30;
	}

	@Override
	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignLine jrDesignLine = new JRDesignLine(jasperDesign);

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignLine);
		}

		jrDesignLine.setWidth(getDefaultWidth());
		jrDesignLine.setHeight(getDefaultHeight());
		return jrDesignLine;
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

	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseLine.PROPERTY_DIRECTION);
		result.add(JRBaseStyle.PROPERTY_FILL);
		return result;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRLine jrSource = (JRLine) getValue();
		if (jrSource != null) {
			JRLine jrTarget = (JRLine) target;
			jrTarget.setFill(jrSource.getOwnFillValue());
			jrTarget.setDirection(jrSource.getDirectionValue());
		}
	}

}
