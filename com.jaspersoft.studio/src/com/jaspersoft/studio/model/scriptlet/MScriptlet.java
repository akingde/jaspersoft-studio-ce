/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.scriptlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;

/*
 * The Class MScriptlet.
 * 
 * @author Chicu Veaceslav
 */
public class MScriptlet extends APropertyNode implements ICopyable, IDragable {
	
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
			iconDescriptor = new NodeIconDescriptor("scriptlet"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m scriptlet.
	 */
	public MScriptlet() {
		super();
	}

	/**
	 * Instantiates a new m scriptlet.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MScriptlet(ANode parent, JRScriptlet jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRScriptlet) getValue()).getName();
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(JRDesignScriptlet.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MScriptlet_name_description);
		desc.add(nameD);

		List<Class<?>> clist = new ArrayList<Class<?>>();
		clist.add(JRAbstractScriptlet.class);
		clist.add(JRDefaultScriptlet.class);
		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME,
				Messages.common_class);
		classD.setClasses(clist);
		classD.setDescription(Messages.MScriptlet_class_description);
		desc.add(classD);
		classD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#scriptlet_class"));

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignScriptlet.PROPERTY_DESCRIPTION,
				Messages.common_description);
		descriptionD.setDescription(Messages.MScriptlet_description_description);
		desc.add(descriptionD);
		descriptionD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#scriptletDescription"));

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#scriptlet");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignScriptlet jrField = (JRDesignScriptlet) getValue();
		if (id.equals(JRDesignScriptlet.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME))
			return jrField.getValueClassName();
		if (id.equals(JRDesignScriptlet.PROPERTY_DESCRIPTION))
			return jrField.getDescription();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignScriptlet jrField = (JRDesignScriptlet) getValue();
		if (id.equals(JRDesignScriptlet.PROPERTY_NAME)) {
			if (value instanceof String && !((String) value).isEmpty()) {
				String newName = (String) value;
				String oldName = jrField.getName();
				JRDesignDataset d = ModelUtils.getDataset(this);
				if (d != null) {
					Map<String, JRParameter> pmap = d.getParametersMap();
					JRDesignParameter p = (JRDesignParameter) pmap.get(oldName + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX);
					if (p != null) {
						p.setName(newName + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX);
						pmap.remove(oldName + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX);
						pmap.put(newName + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX, p);
					}
					jrField.setName(newName);
				}
			}
		} else if (id.equals(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME)) {
			if (value instanceof String) {
				if (((String) value).isEmpty())
					value = null;
				jrField.setValueClassName((String) value);
				JRDesignDataset d = ModelUtils.getDataset(this);
				if (d != null) {
					Map<String, JRParameter> pmap = d.getParametersMap();
					JRDesignParameter p = (JRDesignParameter) pmap
							.get(jrField.getName() + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX);
					if (p != null)
						p.setValueClassName(jrField.getValueClassName());
				}
			}
		} else if (id.equals(JRDesignScriptlet.PROPERTY_DESCRIPTION))
			jrField.setDescription((String) value);
	}

	/**
	 * Creates the jr scriptlet.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design scriptlet
	 */
	public static JRDesignScriptlet createJRScriptlet(JRDesignDataset jrDataset) {
		JRDesignScriptlet jrScriptlet = new JRDesignScriptlet();
		jrScriptlet.setName(ModelUtils.getDefaultName(jrDataset.getScriptletsMap(), "Scriptlet_")); //$NON-NLS-1$
		jrScriptlet.setValueClass(JRDefaultScriptlet.class);
		return jrScriptlet;

	}

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MScriptlets)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}
	
	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
