package com.jaspersoft.studio.server.properties.action;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.server.properties.ASection;

public class EditOkAction extends Action {
	public static final String ID = "saveproperties-js";
	private ASection section;

	public EditOkAction(ASection section) {
		super();
		setId(ID);
		setText("OK");
		this.section = section;
	}

	@Override
	public void run() {
		section.saveProperties();
	}
}
