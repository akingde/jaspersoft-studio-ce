package com.jaspersoft.studio.server.properties.action;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.server.properties.ASection;

public class EditCancelAction extends Action {
	public static final String ID = "cancelproperties-js";
	private ASection section;

	public EditCancelAction(ASection section) {
		super();
		setId(ID);
		setText("Cancel");
		this.section = section;
	}

	@Override
	public void run() {
		section.cancelEditProperties();
	}
}
