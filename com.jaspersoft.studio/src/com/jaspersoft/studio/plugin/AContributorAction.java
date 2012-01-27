package com.jaspersoft.studio.plugin;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AContributorAction extends Action {

	protected JasperReportsConfiguration jrConfig;

	public AContributorAction(String id, String text) {
		super();
		setId(id);
		setText(text);
	}

	public void setJrConfig(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

}
