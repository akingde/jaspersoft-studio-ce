/*******************************************************************************
 * Copyright (C) 2010 - 2018. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JasperReport;

public interface IReportRunner {
	public String getID();

	public String getLabel();

	public APreview getPreview(Composite parent, JasperReportsConfiguration jrConfig);

	public void run(PreviewContainer pcontainer, final IFile file, final JasperReport jasperReport,
			JasperReportsConfiguration jrContext, Map<String, Object> jasperParameters, IProgressMonitor monitor);
}
