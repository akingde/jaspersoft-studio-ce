package com.jaspersoft.studio.editor.preview.input;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

public class ADataInput {

	public static void setMandatory(IParameter param, Control num) {
		if (param.isMandatory()) {
			ControlDecoration controlDecoration = new ControlDecoration(num, SWT.LEFT | SWT.TOP);
			controlDecoration.setDescriptionText("this field is mandatory");
			controlDecoration.setImage(FieldDecorationRegistry.getDefault()
					.getFieldDecoration(FieldDecorationRegistry.DEC_REQUIRED).getImage());
		}
	}
}
