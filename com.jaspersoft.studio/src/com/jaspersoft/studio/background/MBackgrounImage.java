/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background;

import java.beans.PropertyChangeEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TransparencyPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPPixel;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * 
 * Model element for the background image
 * 
 * @author Orlandin Marco
 * 
 */
public class MBackgrounImage extends APropertyNode implements IGraphicElement {

	/**
	 * Key of the properties that store the transparency of the background. 
	 * It is stored as a Float and it should go from 0f to 1.0f
	 */
	public static final String PROPERTY_ALPHA = "background.image.alpha"; //$NON-NLS-1$
	
	/**
	 * Key of the properties that store the path of the image to use as background. 
	 * It is stored as a String
	 */	
	public static final String PROPERTY_PATH = "background.image.path"; //$NON-NLS-1$
	
	/**
	 * Key of the properties that store the width of the background. 
	 * It is stored as an Integer
	 */
	public static final String PROPERTY_WIDTH = "background.image.widht"; //$NON-NLS-1$
	
	/**
	 * Key of the properties that store the height of the background. 
	 * It is stored as an Integer
	 */
	public static final String PROPERTY_HEIGHT = "background.image.height"; //$NON-NLS-1$
	
	/**
	 * Key of the properties that store the x position of the background. 
	 * It is stored as an Integer
	 */
	public static final String PROPERTY_X = "background.image.x"; //$NON-NLS-1$
	
	/**
	 * Key of the properties that store the y position of the background. 
	 * It is stored as an Integer
	 */
	public static final String PROPERTY_Y = "background.image.y"; //$NON-NLS-1$
	
	/**
	 * Key of the properties that store if the background image must keep
	 * its ratio on resize. It is stored as a boolean
	 */
	public static final String PROPERTY_KEEP_RATIO = "background.image.keep_ratio"; //$NON-NLS-1$
	
	/**
	 * Cache for the properties descriptor
	 */
	private IPropertyDescriptor[] descriptors;
	
	/**
	 * Path of the last background image loaded
	 */
	private String lastPath;
	
	/**
	 * Data of the last image loaded
	 */
	private ImageData lastImageData;
	
	private static final long serialVersionUID = 757632360685857870L;

	/**
	 * Create the background model element
	 * 
	 * @param parent parent of the node
	 */
	public MBackgrounImage(ANode parent) {
		super(parent, -1);
		setValue(getJasperDesign());
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return always null, the background has not an icon
	 */
	public static IIconDescriptor getIconDescriptor() {
		return null;
	}
	
	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		return "Background"; //$NON-NLS-1$
	}

	@Override
	public String getToolTip() {
		return "Image shown on the background"; //$NON-NLS-1$
	}

	/**
	 * Return the property descriptors of the element
	 */
	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors for the background and initialize the default map
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		// bounds
		JSSBackgroundPixelLocationValidator heightValidator = new JSSBackgroundPixelLocationValidator(PROPERTY_HEIGHT);
		heightValidator.setTargetNode(this);
		PixelPropertyDescriptor heightD = new PixelPropertyDescriptor(PROPERTY_HEIGHT, Messages.common_height);
		heightD.setCategory(Messages.common_size);
		heightD.setDescription(Messages.MGraphicElement_height_description);
		heightD.setValidator(heightValidator);
		desc.add(heightD);

		JSSBackgroundPixelLocationValidator widthValidator = new JSSBackgroundPixelLocationValidator(PROPERTY_WIDTH);
		widthValidator.setTargetNode(this);
		PixelPropertyDescriptor widthD = new PixelPropertyDescriptor(PROPERTY_WIDTH, Messages.MGraphicElement_width);
		widthD.setCategory(Messages.common_size);
		widthD.setDescription(Messages.MGraphicElement_width_description);
		widthD.setValidator(widthValidator);
		desc.add(widthD);

		JSSBackgroundPixelLocationValidator xValidator = new JSSBackgroundPixelLocationValidator(PROPERTY_X);
		xValidator.setTargetNode(this);
		PixelPropertyDescriptor xD = new PixelPropertyDescriptor(PROPERTY_X, Messages.common_left){
			public ASPropertyWidget<PixelPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
				SPPixel spNumber = new SPPixel(parent, section, this) {
					protected int getPixelOffset() {
						return -10;
					};
				};
				return spNumber;
			};
		};
		xD.setCategory(Messages.MGraphicElement_location_category);
		xD.setDescription(Messages.MGraphicElement_left_description);
		xD.setValidator(xValidator);
		desc.add(xD);

		JSSBackgroundPixelLocationValidator yValidator = new JSSBackgroundPixelLocationValidator(PROPERTY_Y);
		yValidator.setTargetNode(this);
		PixelPropertyDescriptor yD = new PixelPropertyDescriptor(PROPERTY_Y, Messages.common_top) {
			public ASPropertyWidget<PixelPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
				SPPixel spNumber = new SPPixel(parent, section, this) {
					protected int getPixelOffset() {
						return -10;
					};
				};
				return spNumber;
			};
		};
		yD.setCategory(Messages.MGraphicElement_location_category);
		yD.setDescription(Messages.MGraphicElement_top_description);
		yD.setValidator(yValidator);
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
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(PROPERTY_KEEP_RATIO, new DefaultValue(false, false));
		defaultsMap.put(PROPERTY_ALPHA, new DefaultValue(0.5f, false));
		defaultsMap.put(PROPERTY_HEIGHT, new DefaultValue(100, false));
		defaultsMap.put(PROPERTY_WIDTH, new DefaultValue(100, false));
		defaultsMap.put(PROPERTY_X, new DefaultValue(10, false));
		defaultsMap.put(PROPERTY_Y, new DefaultValue(10, false));
		return defaultsMap;
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
			if (value == null) {
				return 10;
			} else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_Y)){
			Object value =  jrObj.getProperty(PROPERTY_Y);
			if (value == null) {
				return 10;
			} else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_HEIGHT)){
			Object value =  jrObj.getProperty(PROPERTY_HEIGHT);
			if (value == null) {
				ImageData image = getImageData();
				if (image != null) return image.height;
				return 100;
			} else return Integer.parseInt((String)value);
		} else if (id.equals(PROPERTY_WIDTH)){
			Object value =  jrObj.getProperty(PROPERTY_WIDTH);
			if (value == null) {
				ImageData image = getImageData();
				if (image != null) return image.width;
				return 100;
			} else return Integer.parseInt((String)value);
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
		//firePropertyChange(new PropertyChangeEvent(this, arg1, arg2, arg3));
	}
	
	/**
	 * Load the image data from the path written in the model.
	 * The image data is cached until the path changes. 
	 * 
	 * @return the loaded image data, can be null if the path is not valid
	 */
	protected ImageData getImageData() {
		String fileName = (String) getPropertyValue(PROPERTY_PATH);
		if (fileName == null) return null;
		if (fileName.equals(lastPath)) return lastImageData;
		lastPath = fileName;
		lastImageData = null;
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
	
	/**
	 * Return the alpha value written in the model, if it is not available
	 * return a default value of 0.5f.
	 * 
	 * @return a not null and valid alpha value
	 */
	public float getAlpha(){
		Float alpha = (Float)getPropertyValue(PROPERTY_ALPHA);
		if (alpha != null) return alpha;
		return 0.5f;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getDefaultX(), getDefaultY(), getDefaultWidth(), getDefaultHeight());
	}

	/**
	 * Return the width value written in the model, if it is not available
	 * try to read it from the image if a path is defined. If the path is 
	 * not defined or the image is not found return a default value
	 * 
	 * @return a not null and valid width value
	 */
	@Override
	public int getDefaultWidth() {
		return (Integer)getPropertyValue(PROPERTY_WIDTH);
	}

	/**
	 * Return the height value written in the model, if it is not available
	 * try to read it from the image if a path is defined. If the path is 
	 * not defined or the image is not found return a default value
	 * 
	 * @return a not null and valid height value
	 */
	@Override
	public int getDefaultHeight() {
		return (Integer)getPropertyValue(PROPERTY_HEIGHT);
	}
	
	/**
	 * Return the x value written in the model, if it is not available
	 * return a default value of 10.
	 * 
	 * @return a not null and valid x value
	 */
	public int getDefaultX(){
		return (Integer)getPropertyValue(PROPERTY_X);
	}
	
	/**
	 * Return the y value written in the model, if it is not available
	 * return a default value of 10.
	 * 
	 * @return a not null and valid y value
	 */
	public int getDefaultY(){
		return (Integer)getPropertyValue(PROPERTY_Y);
	}
	
	/**
	 * Return the keep ratio value written in the model, if it is not available
	 * return a default value false.
	 * 
	 * @return a not null and valid keep ratio value
	 */
	public boolean isKeepRatio(){
		Boolean keepRatio = (Boolean)getPropertyValue(PROPERTY_KEEP_RATIO);
		if (keepRatio != null) return keepRatio;
		return false;
	}

	/**
	 * The background dosen't provide a jr element
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}
	
	/**
	 * Search the editpart between it listeners
	 */
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

	/**
	 * Add the listener to the properties map of the report, since the background image relay on that
	 * to store its informations
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
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//The properties map was changed so i need to refresh the part.
		//The event doesn't contains the property of the map changed so i'm not sure
		//that the change regards the background image, so it must be always refreshed
		EditPart figureEditPart = getFigureEditPart();
		if (figureEditPart != null){
			figureEditPart.refresh();
		}
		
		if (PROPERTY_ALPHA.equals(evt.getPropertyName()) || PROPERTY_PATH.equals(evt.getPropertyName()) || PROPERTY_WIDTH.equals(evt.getPropertyName()) || 
				PROPERTY_HEIGHT.equals(evt.getPropertyName()) || PROPERTY_X.equals(evt.getPropertyName()) || PROPERTY_Y.equals(evt.getPropertyName()) || 
					PROPERTY_KEEP_RATIO.equals(evt.getPropertyName())) {
			//fire the property change event when one of the background related property change, because the section of the background image is notified
			//by this fire property change an need it to update the widgets on the properties view
			firePropertyChange(evt);
		}
	}
}
