/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import java.io.File;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.editors.JRBookDesignEditor;
import com.jaspersoft.studio.editor.action.CompileAction;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.utils.PartUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JasperDesign;

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
	
	/**
	 * Return an image of the right size to be used in the editor toolbar 
	 *  
	 * @return a not null image data with height 25
	 */
	public static ImageDescriptor getToolBarImageDescriptor(){
		return JRBookActivator.getDefault().getImageDescriptor("/icons/build_tab.gif");
	}
}
