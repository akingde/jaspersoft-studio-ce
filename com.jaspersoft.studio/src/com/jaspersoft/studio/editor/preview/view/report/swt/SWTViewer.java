/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.report.swt;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.IPreferencePage;
import com.jaspersoft.studio.editor.preview.view.report.ExportMenu;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.FirstPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.LastPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.NextPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.PageNumberContributionItem;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.PreviousPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.ZoomActualSizeAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.ZoomComboContributionItem;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.ZoomFitPageAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.ZoomFitPageWidthAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.ZoomInAction;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.ZoomOutAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SWTViewer extends APreview implements IJRPrintable, IPreferencePage {

	protected ReportViewer rptviewer;

	public SWTViewer(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		rptviewer = new ReportViewer(jContext);
		Control ctrl = rptviewer.createControl(composite);
		ctrl.setLayoutData(new GridData(GridData.FILL_BOTH));

		return composite;
	}

	private IToolBarManager tmanager;

	@Override
	public void contribute2ToolBar(IToolBarManager tmanager) {
		this.tmanager = tmanager;
		tmanager.add(new FirstPageAction(rptviewer));
		tmanager.add(new PreviousPageAction(rptviewer));
		tmanager.add(new PageNumberContributionItem(rptviewer));
		tmanager.add(new NextPageAction(rptviewer));
		tmanager.add(new LastPageAction(rptviewer));
		tmanager.add(new Separator());

		tmanager.add(new ZoomInAction(rptviewer));
		tmanager.add(new ZoomOutAction(rptviewer));

		tmanager.add(new ZoomComboContributionItem(rptviewer));
		tmanager.add(new ZoomFitPageWidthAction(rptviewer));
		tmanager.add(new ZoomFitPageAction(rptviewer));
		tmanager.add(new ZoomActualSizeAction(rptviewer));
		tmanager.add(new Separator());

		tmanager.add(ExportMenu.getExportMenu(rptviewer, jContext));
	}

	protected JasperPrint jrprint;

	public void setJRPRint(Statistics stats, JasperPrint jrprint) {
		int ind = Math.max(0, rptviewer.getPageIndex());
		if (jrprint != null) {
			ind = Math.max(ind, jrprint.getPages().size());

		}
//		if (tmanager != null) {
//			contribute2ToolBar(tmanager);
//			tmanager.update(true);
//			((ToolBarManager) tmanager).getControl().pack();
//		}
		rptviewer.setDocument(jrprint);
		rptviewer.setPageIndex(ind);
		rptviewer.gotoFirstPage();

		this.jrprint = jrprint;
	}

	private boolean refresh = false;

	@Override
	public void pageGenerated(final JasperPrint arg0, int page) {
		if (refresh)
			return;
		refresh = true;
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				int ind = rptviewer.getPageIndex();
				rptviewer.setDocument(arg0);
				rptviewer.setPageIndex(ind);
				jrprint = arg0;
				refresh = false;
			}
		});

	}

	@Override
	public void pageUpdated(final JasperPrint arg0, final int page) {
		if (rptviewer.getPageIndex() == page) {
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					rptviewer.setDocument(arg0);
					rptviewer.setPageIndex(page);
					jrprint = arg0;
				}
			});
		}
	}

	@Override
	public PreferencePage getPreferencePage() {
		return null;
	}

}
