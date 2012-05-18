/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.util.StringInput;
import com.jaspersoft.studio.swt.widgets.CSashForm;
import com.jaspersoft.studio.utils.FileUtils;

public class ReportUnitEditor extends PreviewJRPrint {
	public static final String ID = "com.jaspersoft.studio.server.editor.ReportUnitEditor";
	private String reportUnitURI;

	public ReportUnitEditor() {
		super(false);
	}

	@Override
	protected void loadJRPrint(IEditorInput input) throws PartInitException {
		try {
			reportUnitURI = FileUtils
					.readInputStreamAsString(((StringInput) getEditorInput())
							.getStorage().getContents());
		} catch (Exception e1) {
			throw new PartInitException(e1.getMessage(), e1);
		}
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				runReport();
			}
		});

	}

	public void runReport() {
		if (isNotRunning()) {
			// check if we can run the report
			topToolBarManager.setEnabled(false);
			topToolBarManager1.setEnabled(false);
			leftToolbar.setEnabled(false);
			getLeftContainer().setEnabled(false);
			getLeftContainer().switchView(null,
					ReportRunControler.FORM_PARAMETERS);

			reportControler.setReportUnit(reportUnitURI);
		}
	}

	@Override
	protected PreviewTopToolBarManager getTopToolBarManager1(Composite container) {
		if (topToolBarManager1 == null)
			topToolBarManager1 = new PreviewTopToolBarManager(this, container);
		return (PreviewTopToolBarManager) topToolBarManager1;
	}

	private CSashForm sashform;
	private LeftToolBarManager leftToolbar;

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		getTopToolBarManager1(container);
		getTopToolBarManager(container);

		sashform = new CSashForm(container, SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		sashform.setLayoutData(gd);

		createLeft(parent, sashform);

		createRight(sashform);

		sashform.setWeights(new int[] { 100, 150 });
	}

	protected void createLeft(Composite parent, SashForm sf) {
		Composite leftComposite = new Composite(sf, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		leftComposite.setLayout(layout);

		leftToolbar = new LeftToolBarManager(this, leftComposite);

		final Composite cleftcompo = new Composite(leftComposite, SWT.NONE);
		cleftcompo.setBackground(parent.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		cleftcompo.setLayoutData(new GridData(GridData.FILL_BOTH));
		cleftcompo.setLayout(new StackLayout());

		getLeftContainer().populate(cleftcompo,
				getReportControler().createControls(cleftcompo, ph));
		getLeftContainer().switchView(null, ReportRunControler.FORM_PARAMETERS);
	}

	private MultiPageContainer leftContainer;

	public MultiPageContainer getLeftContainer() {
		if (leftContainer == null)
			leftContainer = new MultiPageContainer() {
				@Override
				public void switchView(Statistics stats, APreview view) {
					super.switchView(stats, view);
					for (String key : pmap.keySet()) {
						if (pmap.get(key) == view) {
							leftToolbar.setLabelText(key);
							break;
						}
					}
				}
			};
		return leftContainer;
	}

	@Override
	public void setNotRunning(boolean stopRunning) {
		super.setNotRunning(stopRunning);
		if (stopRunning) {
			getLeftContainer().setEnabled(true);
			leftToolbar.setEnabled(true);
		}
	}

	public void showParameters(boolean showprm) {
		if (showprm)
			sashform.upRestore();
		else
			sashform.upHide();
	}

	private ReportRunControler reportControler;

	private ReportRunControler getReportControler() {
		if (reportControler == null)
			reportControler = new ReportRunControler(this);
		return reportControler;
	}
}
