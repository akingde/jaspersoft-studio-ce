/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.file;

import java.io.File;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.viewer.ReportViewer;
import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.IPreferencePage;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.report.ExportMenu;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AFileViewer extends APreview implements IJRPrintable, IPreferencePage {

	private ReportViewer rptviewer;
	private Text browser;

	public AFileViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	protected abstract AbstractExportAction createExporter(ReportViewer rptv);

	protected abstract String getExtension();

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		if (jrprint != null)
			tmanager.add(ExportMenu.getExportMenu(rptviewer, jContext));
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		browser = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));

		rptviewer = new ReportViewer(jContext);
		return composite;
	}

	private JasperPrint jrprint;

	public void setJRPRint(Statistics stats, JasperPrint jrprint) throws Exception {
		if (this.jrprint == null || this.jrprint != jrprint) {
			rptviewer.setDocument(jrprint);

			File tmpFile = File.createTempFile("report", getExtension());

			AbstractExportAction exp = createExporter(rptviewer);
			stats.startCount(ReportControler.ST_EXPORTTIME);
			exp.export(tmpFile);
			stats.endCount(ReportControler.ST_EXPORTTIME);
			stats.setValue(ReportControler.ST_REPORTSIZE, tmpFile.length());

			browser.setText(FileUtils.readFileAsAString(tmpFile));
		}
		this.jrprint = jrprint;
	}

}
