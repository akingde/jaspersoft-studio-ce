/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.frame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BorderSplitType;

/*
 * The Class MFrame.
 */
public class MFrame extends MGraphicElementLineBox implements IPastable, IPastableGraphic, IContainer,
		IContainerLayout, IContainerEditPart, IGraphicElementContainer {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	
	/**
	 * Property used on JSS level to show or hide the elements placed outside the frame
	 */
	public static final String PROPERTY_SHOW_OUT_OF_BOUND = "ShowOutOfBoundContent"; //$NON-NLS-1$
	
	private static IPropertyDescriptor[] descriptors;

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


	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		RComboBoxPropertyDescriptor positionTypeD = new RComboBoxPropertyDescriptor(
				JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE, Messages.MFrame_splitType, new String[] { "", //$NON-NLS-2$
						MessagesByKeys.getString(BorderSplitType.NO_BORDERS.getName()),
						MessagesByKeys.getString(BorderSplitType.DRAW_BORDERS.getName()) });
		positionTypeD.setDescription(Messages.MGraphicElement_position_type_description);
		desc.add(positionTypeD);
		positionTypeD.setCategory(Messages.MGraphicElement_location_category);
		
		CheckBoxPropertyDescriptor showOutOfBoundsContent = new CheckBoxPropertyDescriptor(PROPERTY_SHOW_OUT_OF_BOUND, Messages.MFrame_showOutOfBounds);
		showOutOfBoundsContent.setDescription(Messages.MFrame_showOutOfBoundsDescription);
		desc.add(showOutOfBoundsContent);
		showOutOfBoundsContent.setCategory(Messages.MGraphicElement_location_category);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignFrame jrElement = getValue();
		if (id.equals(JRDesignFrame.PROPERTY_BORDER_SPLIT_TYPE)) {
			if (jrElement.getBorderSplitType() == null)
				return ""; //$NON-NLS-1$
			return MessagesByKeys.getString(jrElement.getBorderSplitType().getName());
		} else if (id.equals(PROPERTY_SHOW_OUT_OF_BOUND)){
			String value = jrElement.getPropertiesMap().getProperty(PROPERTY_SHOW_OUT_OF_BOUND);
			if (value == null) return true;
			else return Boolean.valueOf(value);
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

		} else if (id.equals(PROPERTY_SHOW_OUT_OF_BOUND)){
			jrElement.getPropertiesMap().setProperty(PROPERTY_SHOW_OUT_OF_BOUND, Boolean.toString((Boolean)value));
			getPropertyChangeSupport().firePropertyChange(JSSCompoundCommand.REFRESH_UI_EVENT, null, null);
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
		JRDesignElement jrDesignElement = new JRDesignFrame(jasperDesign);

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

	public Integer getTopPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getTopPadding();
		return 0;
	}

	public Integer getLeftPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getLeftPadding();
		return 0;
	}

	public Integer getBottomPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getBottomPadding();
		return 0;
	}

	public Integer getRightPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getRightPadding();
		return 0;
	}
	
	public Integer getPadding() {
		JRDesignFrame frame = (JRDesignFrame) getValue();
		if (frame != null)
			return frame.getLineBox().getPadding();
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
	public HashMap<String, List<ANode>> getUsedStyles() {
		HashMap<String, List<ANode>> usedStyles = super.getUsedStyles();
		for (INode node : getChildren()) {
			if (node instanceof ANode) {
				mergeElementStyle(usedStyles, ((ANode) node).getUsedStyles());
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
	
	@Override
	public ILayout getDefaultLayout() {
		return LayoutManager.getLayout(FreeLayout.class.getName());
	}
}
