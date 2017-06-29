/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;

public interface IEditorContributor {

	public void onLoad(JasperDesign jd, EditorPart editor);

	public void onSave(JasperReportsContext jrConfig, IProgressMonitor monitor);

	public void onSaveAs(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor);
	
	public void onRename(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor);

	public void onRun(JasperReportsConfiguration jrConfig, JasperReport jr, Map<String, Object> params);

	public AContributorAction[] getActions();

	public String getTitleToolTip(JasperReportsContext jrConfig, String toolTip);
}
