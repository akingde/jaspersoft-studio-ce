/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import com.jaspersoft.studio.components.customvisualization.model.CVCProprtiesExpressionDTO;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.customvisualization.design.CVDesignComponent;

/**
 * Editor to write and read the property between the CVC component and 
 * the dynamic widgets. Used in the properties view
 * 
 * @author Orlandin Marco
 *
 */
public class SectionCVCPropertyEditor extends CVCPropertyEditor {
	
	/**
	 * The section used to write the value inside the widget
	 */
	private AbstractSection section;
	
	public SectionCVCPropertyEditor(AbstractSection section, CVCProprtiesExpressionDTO itemProps) {
		super(itemProps);
		this.section = section;
	}

	@Override
	protected void executeChangeCommand() {
		section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
	}
}
