package com.jaspersoft.studio.data.sql.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public abstract class AAction extends Action {

	public AAction() {
		super();
	}

	public AAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	public AAction(String text, int style) {
		super(text, style);
	}

	public AAction(String text) {
		super(text);
	}

	public abstract boolean calculateEnabled(Object[] selection);
}
