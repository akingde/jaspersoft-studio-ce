/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.preview.view.report.html;

import java.io.File;
import java.io.IOException;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.actions.export.AbstractExportAction;
import com.jaspersoft.studio.editor.preview.actions.export.ExportAsHtmlAction;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.IPreferencePage;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.report.ExportMenu;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.editor.preview.view.report.IURLViewable;
import com.jaspersoft.studio.editor.preview.view.report.swt.ReportViewer;
import com.jaspersoft.studio.preferences.exporter.HTMLExporterPreferencePage;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class HTMLViewer extends APreview implements IJRPrintable, IURLViewable, IPreferencePage {

	public HTMLViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		try {
			browser = new Browser(composite, SWT.NONE);
			browser.setLayoutData(new GridData(GridData.FILL_BOTH));
			browser.setJavascriptEnabled(true);
			rptviewer = new ReportViewer(jContext);
		} catch (Error e) {
			e.printStackTrace();
		}
		return composite;
	}

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		if (tmpFile != null)
			tmanager.add(new URLContributionItem(tmpFile.toURI().toASCIIString()));
		if (jrprint != null) {
			tmanager.add(ExportMenu.getExportMenu(rptviewer, jContext));
		}
	}

	private JasperPrint jrprint;
	private ReportViewer rptviewer;

	public void setJRPRint(Statistics stats, JasperPrint jrprint) throws Exception {
		if (this.jrprint != jrprint) {
			rptviewer.setDocument(jrprint);

			tmpFile = File.createTempFile("report", getExtension(), getTmpPath());

			AbstractExportAction exp = createExporter(rptviewer);
			stats.startCount(ReportControler.ST_EXPORTTIME);
			exp.export(tmpFile);
			stats.endCount(ReportControler.ST_EXPORTTIME);
			stats.setValue(ReportControler.ST_REPORTSIZE, tmpFile.length());

			browser.setUrl(tmpFile.toURI().toASCIIString());
		}
		this.jrprint = jrprint;
	}

	protected String getExtension() {
		return ".html";
	}

	protected AbstractExportAction createExporter(ReportViewer rptv) {
		return new ExportAsHtmlAction(rptv, jContext);
	}

	private File tmpDir;
	private Browser browser;
	private File tmpFile;

	private File getTmpPath() throws IOException {
		if (tmpDir != null)
			FileUtils.recursiveDelete(tmpDir);

		tmpDir = FileUtils.createTempDir();

		return tmpDir;
	}

	@Override
	public void dispose() {
		super.dispose();
		if (tmpDir != null)
			FileUtils.recursiveDelete(tmpDir);
	}

	public void setURL(String url) throws Exception {
		browser.setUrl(url);
	}

	@Override
	public void pageGenerated(JasperPrint arg0, int arg1) {

	}

	@Override
	public void pageUpdated(JasperPrint arg0, int arg1) {

	}

	@Override
	public PreferencePage getPreferencePage() {
		return new HTMLExporterPreferencePage();
	}

}
