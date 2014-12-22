/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.frame;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BorderSplitType;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.Misc;

/*
 * The Class MFrame.
 */
public class MFrame extends MGraphicElementLineBox implements IPastable, IPastableGraphic, IContainer,
		IContainerLayout, IContainerEditPart, IGraphicElementContainer {
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
			iconDescriptor = new NodeIconDescriptor("frame"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m frame.
	 */
	public MFrame() {
		super();
	}

	/**
	 * Instantiates a new m frame.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrFrame
	 *          the jr frame
	 * @param newIndex
	 *          the new index
	 */
	public MFrame(ANode parent, JRDesignFrame jrFrame, int newIndex) {
		super(parent, newIndex);
		setValue(jrFrame);
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

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		RComboBoxPropertyDescriptor positionTypeD = new RComboBoxPropertyDescriptor(
				JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE, "Border Split Type", new String[] { "",
						MessagesByKeys.getString(BorderSplitType.NO_BORDERS.getName()),
						MessagesByKeys.getString(BorderSplitType.DRAW_BORDERS.getName()) });
		positionTypeD.setDescription(Messages.MGraphicElement_position_type_description);
		desc.add(positionTypeD);
		positionTypeD.setCategory(Messages.MGraphicElement_location_category);

	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignFrame jrElement = getValue();
		if (id.equals(JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE)) {
			if (jrElement.getBorderSplitType() == null)
				return "";
			return MessagesByKeys.getString(jrElement.getBorderSplitType().getName());
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignFrame jrElement = getValue();
		if (id.equals(JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE)) {
			if (Misc.isNullOrEmpty((String) value))
				jrElement.setBorderSplitType(null);
			else {
				if (value.equals(MessagesByKeys.getString(BorderSplitType.NO_BORDERS.getName())))
					jrElement.setBorderSplitType(BorderSplitType.NO_BORDERS);
				else
					jrElement.setBorderSplitType(BorderSplitType.DRAW_BORDERS);
			}

		}
		super.setPropertyValue(id, value);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 200;
	}

	@Override
	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignElement jrDesignElement = new JRDesignFrame();

		DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignElement);

		jrDesignElement.setWidth(getDefaultWidth());
		jrDesignElement.setHeight(getDefaultHeight());
		return jrDesignElement;
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

	public int getTopPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getTopPadding();
		return 0;
	}

	public int getLeftPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getLeftPadding();
		return 0;
	}

	@Override
	public JRDesignFrame getValue() {
		return (JRDesignFrame) super.getValue();
	}

	@Override
	public Dimension getSize() {
		JRDesignFrame jrDesignFrame = getValue();
		int h = jrDesignFrame.getHeight();
		int w = jrDesignFrame.getWidth();
		return new Dimension(w, h);
	}

	@Override
	public JRPropertiesHolder[] getPropertyHolder() {
		return new JRPropertiesHolder[] { getValue() };
	}
	
	@Override
	public HashSet<String> getUsedStyles() {
		HashSet<String> usedStyles = super.getUsedStyles();
		for (INode node : getChildren()) {
			if (node instanceof IGraphicalPropertiesHandler) {
				HashSet<String> childStyles = ((IGraphicalPropertiesHandler) node).getUsedStyles();
				usedStyles.addAll(childStyles);
			}
		}
		return usedStyles;
	}

	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRDesignFrame.PROPERTY_CHILDREN);
		result.add(JRDesignElement.PROPERTY_ELEMENT_GROUP);
		return result;
	}
}
