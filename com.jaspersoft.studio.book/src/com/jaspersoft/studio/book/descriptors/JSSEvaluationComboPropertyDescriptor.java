package com.jaspersoft.studio.book.descriptors;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.book.widgets.SPEvaluationReadCombo;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

public class JSSEvaluationComboPropertyDescriptor extends JSSComboPropertyDescriptor {
	

	public JSSEvaluationComboPropertyDescriptor(Object id, String displayName, String[] labels) {
		super(id, displayName, labels);
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		combo =  new SPEvaluationReadCombo(parent, section, this);
		return combo;
	}
}
