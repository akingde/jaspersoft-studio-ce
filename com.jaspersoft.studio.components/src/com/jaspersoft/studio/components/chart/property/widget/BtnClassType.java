package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.ClassType;

public class BtnClassType {
	private ClassType clWidget;

	public BtnClassType(Composite parent, AbstractSection section,
			String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent,
			final AbstractSection section, final String property, String tooltip) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginLeft = 0;
		composite.setLayout(layout);

		clWidget = new ClassType(composite, tooltip);
		clWidget.addListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				section.changeProperty(property, clWidget.getClassType());
			}
		});
	}

	public void setData(String b) {
		clWidget.setClassType(b);
	}
}
