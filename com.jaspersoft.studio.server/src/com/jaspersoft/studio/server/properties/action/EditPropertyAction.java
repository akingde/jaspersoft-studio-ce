package com.jaspersoft.studio.server.properties.action;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.server.properties.ASection;

public class EditPropertyAction extends Action {
	public static final String ID = "editproperties-js";
	private ASection section;

	public EditPropertyAction(ASection section) {
		super();
		setId(ID);
		setText("Edit");
		this.section = section;
	}

	@Override
	public void run() {
		section.editProperties();
	}

}
