package com.jaspersoft.studio.plugin;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.part.EditorPart;

public interface IEditorContributor {

	public void onLoad(JasperDesign jd, EditorPart editor);

	public void onSave(JasperDesign jd);

	public void onRun();
}
