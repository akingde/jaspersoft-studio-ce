/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.textfield;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/*
 * /* The Class MPercentage.
 */
public class MPercentage extends MTextField {
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
			iconDescriptor = new NodeIconDescriptor("percentage"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m percentage.
	 */
	public MPercentage() {
		super();
	}

	/**
	 * Instantiates a new m percentage.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrStaticText
	 *          the jr static text
	 * @param newIndex
	 *          the new index
	 */
	public MPercentage(ANode parent, JRTextField jrStaticText, int newIndex) {
		super(parent, jrStaticText, newIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.model.textfield.MTextField#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignTextField createJRElement(JasperDesign jasperDesign) {
		JRDesignTextField jrDesignTextField = new JRDesignTextField(jasperDesign);
		jrDesignTextField.setPattern("#,##0.00%");
		return jrDesignTextField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.textfield.MTextField#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		if (getValue() != null) {
			JRTextField jrTextField = (JRTextField) getValue();
			if (jrTextField.getExpression() != null)
				return jrTextField.getExpression().getText();
		}
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.textfield.MTextField#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.textfield.MTextField#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
