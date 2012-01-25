package com.jaspersoft.studio.plugin;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public interface IEditorContributor {

	public static final String KEY_FILE = "ifile";

	public void onLoad(JasperDesign jd, EditorPart editor);

	public void onSave(JasperReportsConfiguration jrConfig);

	public void onRun();

	public AContributorAction[] getActions();
}
