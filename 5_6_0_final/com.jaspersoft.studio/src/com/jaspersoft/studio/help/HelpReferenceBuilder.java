package com.jaspersoft.studio.help;

public class HelpReferenceBuilder implements IHelpRefBuilder {

	private String helpref;

	public HelpReferenceBuilder(String reference) {
		this.helpref = reference;
	}

	@Override
	public String getHelpReference() {
		return helpref;
	}

}
