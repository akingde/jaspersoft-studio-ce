package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.Format;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class CustomPattern extends APattern {

	public CustomPattern(Composite parent, String pattern, Format formatter, Object sample) {
		super(parent, formatter, sample);
		setPattern(pattern);
		setDescription("Type the format pattern, using one of the existing formats as a starting point.");
	}

	@Override
	public Control createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());
		Label l = new Label(container, SWT.NONE);
		l.setText("Formats");
		return container;
	}

}
