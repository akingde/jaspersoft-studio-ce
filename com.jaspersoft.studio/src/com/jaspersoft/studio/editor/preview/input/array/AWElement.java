/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AWElement {

	public abstract Class<?> getSupportedType();

	public void create(Composite parent) {
		control = createControl(parent);
	}

	protected abstract Control createControl(Composite parent);

	private Control control;

	public Control getControl() {
		return control;
	}

	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
