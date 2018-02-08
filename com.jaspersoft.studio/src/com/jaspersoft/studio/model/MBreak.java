/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.base.JRBaseBreak;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BreakTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

/*
 * The Class MBreak.
 */
public class MBreak extends MGraphicElement {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("break"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m break.
	 */
	public MBreak() {
		super();
	}

	/**
	 * Instantiates a new m break.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrBreak
	 *          the jr break
	 * @param newIndex
	 *          the new index
	 */
	public MBreak(ANode parent, JRDesignBreak jrBreak, int newIndex) {
		super(parent, newIndex);
		setValue(jrBreak);
	}

	private static IPropertyDescriptor[] descriptors;
	private static NamedEnumPropertyDescriptor<BreakTypeEnum> typeD;


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

		typeD = new NamedEnumPropertyDescriptor<BreakTypeEnum>(JRBaseBreak.PROPERTY_TYPE, Messages.MBreak_type,
				BreakTypeEnum.COLUMN, NullEnum.NOTNULL);
		typeD.setDescription(Messages.MBreak_type_description);
		desc.add(typeD);
		typeD.setCategory(Messages.MBreak_break_properties_category);

		

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#break");
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String,DefaultValue> defaultsMap = super.createDefaultsMap();
		
		int rotationValue = NamedEnumPropertyDescriptor.getIntValue(BreakTypeEnum.PAGE, NullEnum.NOTNULL, BreakTypeEnum.PAGE);
		defaultsMap.put(JRBaseBreak.PROPERTY_TYPE, new DefaultValue(rotationValue, false));
		
		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignBreak jrElement = (JRDesignBreak) getValue();
		if (id.equals(JRBaseBreak.PROPERTY_TYPE))
			return typeD.getIntValue(jrElement.getTypeValue());
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignBreak jrElement = (JRDesignBreak) getValue();

		if (id.equals(JRBaseBreak.PROPERTY_TYPE))
			jrElement.setType(typeD.getEnumValue(value));
		else
			super.setPropertyValue(id, value);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 3;
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
		JRDesignBreak brk = new JRDesignBreak(jasperDesign);
		brk.setWidth(getDefaultWidth());
		brk.setHeight(getDefaultHeight());
		
		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), brk);
		}
			
		return brk;
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

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignBreak jrSource = (JRDesignBreak) getValue();
		if (jrSource != null) {
			JRDesignBreak jrTarget = (JRDesignBreak) target;
			jrTarget.setType(jrSource.getTypeValue());
		}
	}

}
