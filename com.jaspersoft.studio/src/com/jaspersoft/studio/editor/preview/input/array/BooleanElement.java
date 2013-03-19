package com.jaspersoft.studio.editor.preview.input.array;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class BooleanElement extends AWElement {

	private Button bbuton;

	@Override
	public Class<?> getSupportedType() {
		return Boolean.class;
	}

	@Override
	public Control createControl(Composite parent) {
		bbuton = new Button(parent, SWT.CHECK);
		bbuton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValue(bbuton.getSelection());
				updateLabel();
			}
		});
		updateLabel();
		return bbuton;
	}

	private void updateLabel() {
		bbuton.setText(new Boolean(bbuton.getSelection()).toString());
	}

}
