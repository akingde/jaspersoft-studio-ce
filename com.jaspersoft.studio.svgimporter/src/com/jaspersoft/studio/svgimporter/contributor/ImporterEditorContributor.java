/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter.contributor;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.editor.IEditorContributor;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.svgimporter.action.ImportContentAction;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Contributor for the extension point that will provide the import action to the main editor
 * 
 * @author Orlandin Marco
 *
 */
public class ImporterEditorContributor implements IEditorContributor {

	private static String[] ACTION_IDs = {ImportContentAction.ID};
	
	@Override
	public void onInitContext(JasperReportsConfiguration jConfig) {
	}

	@Override
	public void onLoad(JasperDesign jd, EditorPart editor) {
	}

	@Override
	public void onSave(JasperReportsContext jrConfig, IProgressMonitor monitor) {
	}

	@Override
	public void onSaveAs(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor) {
	}

	@Override
	public void onRename(IFile oldName, IFile newName, JasperReportsContext jrConfig, IProgressMonitor monitor) {
	}

	@Override
	public void onRun(JasperReportsConfiguration jrConfig, JasperReport jr, Map<String, Object> params) {
	}

	@Override
	public AContributorAction[] getActions() {
		return null;
	}
	
	@Override
	public Action[] getEditorActions(AbstractVisualEditor editor) {
		return new Action[] {new ImportContentAction((IWorkbenchPart)editor)};
	}

	@Override
	public String getTitleToolTip(JasperReportsContext jrConfig, String toolTip) {
		return null;
	}

	@Override
	public String[] getEditorActionsIDs() {
		return ACTION_IDs;
	}

}
