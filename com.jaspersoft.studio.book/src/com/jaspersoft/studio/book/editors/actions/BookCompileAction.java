/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import java.io.File;
import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.editors.JRBookDesignEditor;
import com.jaspersoft.studio.editor.action.CompileAction;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.utils.PartUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action to compile a report book and all its resources recursively
 * 
 * @author Orlandin Marco Marco
 *
 */
public class BookCompileAction extends CompileAction {

	public BookCompileAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Return the main dataset, that is the only dataset of a jasperbook
	 */
	@Override
	protected JasperReportsConfiguration getMDatasetToShow() {
		final JRBookDesignEditor part = (JRBookDesignEditor) getWorkbenchPart();
		if (part.getModel() != null && !part.getModel().getChildren().isEmpty()) {
			MReport mreport = (MReport) part.getModel().getChildren().get(0);
			// get report main dataset
			return mreport.getJasperConfiguration();
		}
		return null;
	}
	
	/**
	 * Return the list of subreports used by a the current book report.
	 */
	@Override
	protected Map<File, IFile> getSubreports(JasperReportsConfiguration jConfig, IFile mfile, JasperDesign jd, IProgressMonitor monitor) {
		monitor.setTaskName("Compiling Subreports");
		Map<File, IFile> partial = super.getSubreports(jConfig, mfile, jd, monitor);
		partial.putAll(PartUtils.getSubreportsFromParts(jConfig, jd, true, monitor));
		return partial;
	}
}
