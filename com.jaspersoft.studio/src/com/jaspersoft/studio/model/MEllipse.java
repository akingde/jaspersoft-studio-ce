/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREllipse;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignEllipse;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.FillEnum;

/*
 * The Class MEllipse.
 */
public class MEllipse extends MGraphicElementLinePen {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private static IIconDescriptor iconDescriptor;
	private static IPropertyDescriptor[] descriptors;
	private static final String[] fillEnumNames = EnumHelper.getEnumNames(FillEnum.values(), NullEnum.INHERITED);

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("ellipse"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m ellipse.
	 */
	public MEllipse() {
		super();
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

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FILL,
				Messages.common_fill, fillEnumNames);
		fillD.setDescription(Messages.MEllipse_fill_description);
		desc.add(fillD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#graphicElement");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignEllipse jrElement = (JRDesignEllipse) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL)) {
			return EnumHelper.getEnumIndexByTranslatedName(fillEnumNames, jrElement.getOwnFillValue());
		}
		return super.getPropertyValue(id);
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		JRDesignEllipse jrElement = (JRDesignEllipse) getValue();
		JSSStyleResolver resolver = getStyleResolver();
		if (id.equals(JRBaseStyle.PROPERTY_FILL)) {
			FillEnum fillValue = resolver.getFillValue(jrElement);
			return EnumHelper.getEnumIndexByTranslatedName(fillEnumNames, fillValue);
		}
		return super.getPropertyActualValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignEllipse jrElement = (JRDesignEllipse) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_FILL)) {
			jrElement.setFill(EnumHelper.getEnumByObjectValue(FillEnum.values(), value, true));
		} else {
			super.setPropertyValue(id, value);
		}
	}

	/**
	 * Instantiates a new m ellipse.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrEllipse
	 *            the jr ellipse
	 * @param newIndex
	 *            the new index
	 */
	public MEllipse(ANode parent, JRDesignEllipse jrEllipse, int newIndex) {
		super(parent, newIndex);
		setValue(jrEllipse);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 50;
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
	 * @see
	 * com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.
	 * engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignEllipse jrDesignEllipse = new JRDesignEllipse(jasperDesign);

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignEllipse);
		}

		jrDesignEllipse.setWidth(getDefaultWidth());
		jrDesignEllipse.setWidth(getDefaultHeight());
		return jrDesignEllipse;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getEditableValue()
	 */
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JREllipse jrSource = (JREllipse) getValue();
		if (jrSource != null) {
			((JREllipse) target).setFill(jrSource.getOwnFillValue());
		}
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseStyle.PROPERTY_FILL);
		return result;
	}

}
