package com.jaspersoft.studio.help;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class HelpPrefixBuilder implements IHelpRefBuilder {

	private String helpref;

	public HelpPrefixBuilder(String prefix, IPropertyDescriptor descriptor) {
		helpref = prefix + "_" + descriptor.getId();
	}

	@Override
	public String getHelpReference() {
		return helpref;
	}

}
