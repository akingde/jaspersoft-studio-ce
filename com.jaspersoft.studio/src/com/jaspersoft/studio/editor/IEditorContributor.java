/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;

public interface IEditorContributor {

	public void onInitContext(JasperReportsConfiguration jConfig);

	public void onLoad(JasperDesign jd, EditorPart editor);

	public void onSave(JasperReportsContext jrConfig, IProgressMonitor monitor);

	public void onSaveAs(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor);

	public void onRename(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor);

	public void onRun(JasperReportsConfiguration jrConfig, JasperReport jr, Map<String, Object> params);

	public AContributorAction[] getActions();
	
	/**
	 * Return the actions that will be integrated into the report editor contextual menu
	 * 
	 * @param editor the current editor (can be used to build the action or to provide different 
	 * actions for a specific editor, ie report,table...)
	 * @return an array of action or null
	 */
	public Action[] getEditorActions(AbstractVisualEditor editor);
	
	/**
	 * Return the id of all the actions provided by this contributor
	 * 
	 * @return an array of actions id or null
	 */
	public String[] getEditorActionsIDs();

	public String getTitleToolTip(JasperReportsContext jrConfig, String toolTip);
}
