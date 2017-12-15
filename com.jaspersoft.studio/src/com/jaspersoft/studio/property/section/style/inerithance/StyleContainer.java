/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.style.inerithance;

import net.sf.jasperreports.engine.design.JRDesignReportTemplate;

import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplate;

/**
 * This class encapsulate a style and eventually its external reference, used to handle easily the external styles
 * 
 * @author Orlandin Marco
 * 
 */
public class StyleContainer {

	/**
	 * The style
	 */
	private MStyle style;

	/**
	 * True if the style come from an external reference
	 */
	private boolean external;

	/**
	 * A reference to the external reference
	 */
	private MStyleTemplate externalTemplate;

	/**
	 * Encapsulate a local style
	 * 
	 * @param style
	 *          the style model
	 */
	public StyleContainer(MStyle style) {
		external = false;
		externalTemplate = null;
		this.style = style;
	}

	/**
	 * Encapsulate an external style with its container
	 * 
	 * @param style
	 *          the style
	 * @param reference
	 *          the container
	 */
	public StyleContainer(MStyle style, MStyleTemplate reference) {
		external = true;
		externalTemplate = reference;
		this.style = style;
	}

	/**
	 * Return if the style is external
	 * 
	 * @return true if the style it's external, false otherwise
	 */
	public boolean isExternal() {
		return external;
	}

	/**
	 * Return the style
	 * 
	 * @return the model of the style
	 */
	public MStyle getStyle() {
		return style;
	}

	/**
	 * Return the template
	 * 
	 * @return the template of the style
	 */
	public MStyleTemplate getTemplate() {
		return externalTemplate;
	}

	/**
	 * Return the value of the template
	 * 
	 * @return the JRElement of the template
	 */
	public JRDesignReportTemplate getTemplateValue() {
		return (JRDesignReportTemplate) externalTemplate.getValue();
	}
}
