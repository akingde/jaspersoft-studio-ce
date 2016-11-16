/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.descriptors;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.book.widgets.SPEvaluationReadCombo;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class JSSEvaluationComboPropertyDescriptor extends JSSComboPropertyDescriptor {

	public JSSEvaluationComboPropertyDescriptor(Object id, String displayName, String[] labels) {
		super(id, displayName, labels);
	}

	public SPEvaluationReadCombo createWidget(Composite parent, AbstractSection section) {
		combo = new SPEvaluationReadCombo(parent, section, this);
		return (SPEvaluationReadCombo) combo;
	}
}
