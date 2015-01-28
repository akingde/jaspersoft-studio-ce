/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.background;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TransparencyPropertyDescriptor;

/**
 * 
 * @author Orlandin Marco
 * 
 */
public class MBackgrounImage extends APropertyNode implements IGraphicElement {

	public static final String PROPERTY_ALPHA = "background.image.alpha"; //$NON-NLS-1$
	
	public static final String PROPERTY_PATH = "background.image.path"; //$NON-NLS-1$
	
	public static final String PROPERTY_WIDTH = "background.image.widht"; //$NON-NLS-1$
	
	public static final String PROPERTY_HEIGHT = "background.image.height"; //$NON-NLS-1$
	
	public static final String PROPERTY_X = "background.image.x"; //$NON-NLS-1$
	
	public static final String PROPERTY_Y = "background.image.y"; //$NON-NLS-1$
	
	public static final String PROPERTY_KEEP_RATIO = "background.image.keep_ratio"; //$NON-NLS-1$
	
	private static final long serialVersionUID = 757632360685857870L;
	
	private String lastPath;
	
	private ImageData lastImageData;
	
	public MBackgrounImage() {
		super();
	}

	public MBackgrounImage(ANode parent, Properties properties) {
		super(parent, -1);
		setValue(getJasperDesign());
	}

	public MBackgrounImage(ANode parent) {
		super(parent, -1);
		setValue(getJasperDesign());
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return "Background"; //$NON-NLS-1$
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

	private IPropertyDescriptor[] descriptors;
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
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// bounds
		PixelPropertyDescriptor heightD = new PixelPropertyDescriptor(PROPERTY_HEIGHT, Messages.common_height);
		heightD.setCategory(Messages.common_size);
		heightD.setDescription(Messages.MGraphicElement_height_description);
		desc.add(heightD);

		PixelPropertyDescriptor widthD = new PixelPropertyDescriptor(PROPERTY_WIDTH, Messages.MGraphicElement_width);
		widthD.setCategory(Messages.common_size);
		widthD.setDescription(Messages.MGraphicElement_width_description);
		desc.add(widthD);

		PixelPropertyDescriptor xD = new PixelPropertyDescriptor(PROPERTY_X, Messages.common_left);
		xD.setCategory(Messages.MGraphicElement_location_category);
		xD.setDescription(Messages.MGraphicElement_left_description);
		desc.add(xD);

		PixelPropertyDescriptor yD = new PixelPropertyDescriptor(PROPERTY_Y, Messages.common_top);
		yD.setCategory(Messages.MGraphicElement_location_category);
		yD.setDescription(Messages.MGraphicElement_top_description);
		desc.add(yD);
		
		TransparencyPropertyDescriptor transparency = new TransparencyPropertyDescriptor(PROPERTY_ALPHA, Messages.MBackgrounImage_labelTransparency);
		transparency.setCategory(Messages.MBackgrounImage_labelCategory);
		transparency.setDescription(Messages.MBackgrounImage_descriptionTransparency);
		desc.add(transparency);
		
		CheckBoxPropertyDescriptor keepRatio = new CheckBoxPropertyDescriptor(PROPERTY_KEEP_RATIO, Messages.MBackgrounImage_labelKeepRatio);
		keepRatio.setCategory(Messages.MBackgrounImage_labelCategory); 
		keepRatio.setDescription(Messages.MBackgrounImage_descriptionKeepRatio);
		desc.add(keepRatio);
	}
	
	protected ImageData getImageData() {
		String fileName = (String) getPropertyValue(PROPERTY_PATH);
		if (fileName == null) return null;
		if (fileName.equals(lastPath)) return lastImageData;
		lastPath = fileName;
		try {
			FileInputStream stream = new FileInputStream(fileName);
			Image tempImage = new Image(null, stream);
			lastImageData = tempImage.getImageData();
			tempImage.dispose();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastImageData;
	}


	@Override
	public Object getPropertyValue(Object id) {
		JasperDesign jrObj = getValue();
		if (id.equals(PROPERTY_ALPHA)){
			Object value = jrObj.getProperty(PROPERTY_ALPHA);
			if (value == null) return null;
			else return Float.parseFloat((String)value);
		} else if (id.equals(PROPERTY_PATH)){
			return jrObj.getProperty(PROPERTY_PATH);
		} else if (id.equals(PROPERTY_X)) {
			Object value = jrObj.getProperty(PROPERTY_X);
			if (value == null) return null;
			else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_Y)){
			Object value =  jrObj.getProperty(PROPERTY_Y);
			if (value == null) return null;
			else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_HEIGHT)){
			Object value =  jrObj.getProperty(PROPERTY_HEIGHT);
			if (value == null) return null;
			else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_WIDTH)){
			Object value =  jrObj.getProperty(PROPERTY_WIDTH);
			if (value == null) return null;
			else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_KEEP_RATIO)){
			Object value =  jrObj.getProperty(PROPERTY_KEEP_RATIO);
			if (value == null) return null;
			else return Boolean.parseBoolean((String)value);
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JasperDesign jrObj = getValue();
		if (id.equals(PROPERTY_ALPHA)){
			jrObj.setProperty(PROPERTY_ALPHA, Float.toString((Float)value));
		} else if (id.equals(PROPERTY_PATH)){
			if (value == null) jrObj.removeProperty(PROPERTY_PATH);
			else jrObj.setProperty(PROPERTY_PATH, (String)value);
		} else if (id.equals(PROPERTY_X)) {
			jrObj.setProperty(PROPERTY_X, Integer.toString((Integer)value));
		} else if (id.equals(PROPERTY_Y)){
			jrObj.setProperty(PROPERTY_Y, Integer.toString((Integer)value));
		} else if (id.equals(PROPERTY_HEIGHT)){
			jrObj.setProperty(PROPERTY_HEIGHT, Integer.toString((Integer)value));
		} else if (id.equals(PROPERTY_WIDTH)){
			jrObj.setProperty(PROPERTY_WIDTH, Integer.toString((Integer)value));
		} else if (id.equals(PROPERTY_KEEP_RATIO)){
			jrObj.setProperty(PROPERTY_KEEP_RATIO, Boolean.toString((Boolean)value));
		}
	}
	
	public float getAlpha(){
		Float alpha = (Float)getPropertyValue(PROPERTY_ALPHA);
		if (alpha != null) return alpha;
		return 0.5f;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getDefaultX(), getDefaultY(), getDefaultWidth(), getDefaultHeight());
	}

	@Override
	public int getDefaultWidth() {
		Integer width = (Integer)getPropertyValue(PROPERTY_WIDTH);
		if (width != null) return width;
		ImageData image = getImageData();
		if (image != null) return image.width;
		return 100;
	}

	@Override
	public int getDefaultHeight() {
		Integer height = (Integer)getPropertyValue(PROPERTY_HEIGHT);
		if (height != null) return height;
		ImageData image = getImageData();
		if (image != null) return image.height;
		return 100;
	}
	
	public int getDefaultX(){
		Integer x = (Integer)getPropertyValue(PROPERTY_X);
		if (x != null) return x;
		return 10;
	}
	
	public int getDefaultY(){
		Integer y = (Integer)getPropertyValue(PROPERTY_Y);
		if (y != null) return y;
		return 10;
	}
	
	public boolean isKeepRatio(){
		Boolean keepRatio = (Boolean)getPropertyValue(PROPERTY_KEEP_RATIO);
		if (keepRatio != null) return keepRatio;
		return false;
	}

	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}
	
	public EditPart getFigureEditPart() {
		for (Object o : getPropertyChangeSupport().getPropertyChangeListeners()) {
			if (o instanceof BackgroundImageEditPart)
				return (EditPart) o;
		}
		return null;
	}

	public JasperDesign getValue(){
		return (JasperDesign)super.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			JasperDesign jasperDesign = getValue();
			JRDesignDataset jrDesignDataset = (JRDesignDataset) jasperDesign.getMainDataset();
			JRPropertiesMap pmap = jrDesignDataset.getPropertiesMap();
			pmap.getEventSupport().removePropertyChangeListener(this);
		}
		if (value != null) {
			JasperDesign jasperDesign = (JasperDesign) value;
			JRDesignDataset jrDesignDataset = (JRDesignDataset) jasperDesign.getMainDataset();
			JRPropertiesMap pmap = jrDesignDataset.getPropertiesMap();
			pmap.getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}
}
