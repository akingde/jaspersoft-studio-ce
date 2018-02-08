/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.text;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.base.JRBaseStaticText;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * The Class MStaticText.
 */
public class MStaticText extends MTextElement {

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
			iconDescriptor = new NodeIconDescriptor("statictext"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m static text.
	 */
	public MStaticText() {
		super();
	}

	/**
	 * Instantiates a new m static text.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrStaticText
	 *            the jr static text
	 * @param newIndex
	 *            the new index
	 */
	public MStaticText(ANode parent, JRStaticText jrStaticText, int newIndex) {
		super(parent, newIndex);
		setValue(jrStaticText);
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

		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(JRBaseStaticText.PROPERTY_TEXT,
				Messages.common_text);
		desc.add(textD);
		textD.setCategory(Messages.MStaticText_text_description);

		textD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#text"));
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignStaticText jrElement = (JRDesignStaticText) getValue();
		if (id.equals(JRBaseStaticText.PROPERTY_TEXT))
			return jrElement.getText();
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignStaticText jrElement = (JRDesignStaticText) getValue();

		if (id.equals(JRBaseStaticText.PROPERTY_TEXT)) {
			jrElement.setText((String) value);
		} else
			super.setPropertyValue(id, value);
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
		JRDesignStaticText jrDesignStaticText = new JRDesignStaticText(jasperDesign);
		jrDesignStaticText.setText(Messages.common_static_text);

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignStaticText);
		}
		return jrDesignStaticText;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String p = getElementNameProperty();
		if (!Misc.isNullOrEmpty(p))
			return p;
		JRStaticText st = (JRStaticText) getValue();
		if (st != null) {
			p = st.getText();
			if (!Misc.isNullOrEmpty(p))
				return p;
		}
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
		if (getValue() != null) {
			return getIconDescriptor().getTitle() + ": " + ((JRStaticText) getValue()).getText();
		}
		return getIconDescriptor().getToolTip();
	}

	/**
	 * Return the graphical properties for an MStaticText
	 */
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseStaticText.PROPERTY_TEXT);
		return result;
	}
}
