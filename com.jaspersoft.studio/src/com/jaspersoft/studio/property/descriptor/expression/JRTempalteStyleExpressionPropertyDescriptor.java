/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPTemplateStyleExpression;

/**
 * Property descriptor for the expression property of the template style, it show a different
 * widget to handle the expression, that allow to open a dialgo to easily select the style resource
 * 
 * @author Orlandin Marco
 *
 */
public class JRTempalteStyleExpressionPropertyDescriptor extends JRExpressionPropertyDescriptor {

	public JRTempalteStyleExpressionPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section) {
		expEditor = new SPTemplateStyleExpression(parent, section, this);
		expEditor.setExpressionContext(expContext);
		return expEditor;
	}
}
