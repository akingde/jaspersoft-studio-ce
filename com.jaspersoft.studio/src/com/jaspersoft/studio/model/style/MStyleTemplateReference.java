/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRTemplateReference;

/*
 * The Class MStyleTemplateReference.
 * 
 * @author Chicu Veaceslav
 */
public class MStyleTemplateReference extends APropertyNode implements IPropertySource, ICopyable {
	
	public static final String PROPERTY_LOCATION = "location";
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	
	private static IPropertyDescriptor[] descriptors;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("stylereference"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m style template.
	 */
	public MStyleTemplateReference() {
		super();
	}

	/**
	 * Instantiates a new m style template.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrstyle
	 *          the jrstyle
	 * @param newIndex
	 *          the new index
	 */
	public MStyleTemplateReference(ANode parent, JRTemplateReference jrstyle, int newIndex) {
		super(parent, newIndex);
		setValue(jrstyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		JRTemplateReference jt = (JRTemplateReference) getValue();
		if (jt != null && jt.getLocation() != null)
			return iconDescriptor.getTitle() + "(" + jt.getLocation() + ")";//$NON-NLS-1$ //$NON-NLS-2$
		return iconDescriptor.getTitle();
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
		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(PROPERTY_LOCATION, Messages.MStyleTemplateReference_location); //$NON-NLS-1$
		nameD.setDescription(Messages.MStyleTemplateReference_location_description);
		desc.add(nameD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRTemplateReference jrTemplate = (JRTemplateReference) getValue();
		if (id.equals(PROPERTY_LOCATION)) { //$NON-NLS-1$
			return jrTemplate.getLocation();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		if (isEditable()) {
			JRTemplateReference jrTemplate = (JRTemplateReference) getValue();
			if (id.equals(PROPERTY_LOCATION)) //$NON-NLS-1$
				jrTemplate.setLocation((String) value);
		}
	}

	/**
	 * Creates the jr template.
	 * 
	 * @return the jR design report template
	 */
	public static JRTemplateReference createJRTemplate() {
		JRTemplateReference jrDesignReportTemplate = new JRTemplateReference();
		return jrDesignReportTemplate;
	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MStyleTemplate)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}

	/**
	 * Since the style don't see when its children are updated (because the the relation between 
	 * style template and its inner styles is done only by our model, not by the jr structure). So
	 * when we add children to a style JR don't fire any event. Because of this to have a graphical 
	 * Refresh we must fire the event manually to have the update and see the children 
	 */
	private void fireChildrenChangeEvent(){
		//Need to be executed inside the graphic thread
		UIUtils.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				PropertyChangeEvent event = new PropertyChangeEvent(this, "refresh", null, null);
				getPropertyChangeSupport().firePropertyChange(event);
			}
		});
	}
	
	/**
	 * Refresh the children of a template style by reloading them
	 */
	public void refreshChildren(){
		JRTemplateReference jrTemplate = (JRTemplateReference) getValue();
		
		//Clear the old children
		for(INode child : new ArrayList<INode>(getChildren())){
			((ANode)child).setParent(null, -1);
		}
		getChildren().clear();
		
		StyleTemplateFactory.createTemplateReference(this, jrTemplate.getLocation(), -1, new HashSet<String>(), false);
		fireChildrenChangeEvent();
	}
	
	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
