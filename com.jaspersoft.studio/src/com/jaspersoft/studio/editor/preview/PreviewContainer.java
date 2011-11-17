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
package com.jaspersoft.studio.editor.preview;

import java.io.IOException;
import java.io.InputStream;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.preview.toolbar.LeftToolBarManager;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.swt.widgets.CSashForm;
import com.jaspersoft.studio.utils.Console;

public class PreviewContainer extends PreviewJRPrint implements IDataAdapterRunnable {

	public PreviewContainer() {
		super(true);
	}

	public PreviewContainer(boolean listenResource) {
		super(listenResource);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		if (listenResource) {
			InputStream in = null;
			IFile file = null;
			try {
				if (input instanceof IFileEditorInput) {
					file = ((IFileEditorInput) input).getFile();
					in = file.getContents();
				} else {
					throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
				}
				JasperDesign jd = null;

				in = JrxmlEditor.getXML(input, file.getCharset(true), in, null);
				jd = JRXmlLoader.load(in);
				setJasperDesign(jd);
			} catch (Exception e) {
				throw new PartInitException(e.getMessage(), e);
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						throw new PartInitException("error closing input stream", e); //$NON-NLS-1$
					}
			}
		}
	}

	@Override
	protected void loadJRPrint(IEditorInput input) throws PartInitException {
		setJasperPrint(null);
	}

	private MultiPageContainer leftContainer;

	public MultiPageContainer getLeftContainer() {
		if (leftContainer == null)
			leftContainer = new MultiPageContainer() {
				@Override
				public void switchView(APreview view) {
					super.switchView(view);
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
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		sashform = new CSashForm(container, SWT.HORIZONTAL);
		sashform.setLayoutData(new GridData(GridData.FILL_BOTH));

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
		setupDataAdapter();

		Composite cleftcompo = new Composite(leftComposite, SWT.NONE);
		cleftcompo.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		cleftcompo.setLayoutData(new GridData(GridData.FILL_BOTH));
		cleftcompo.setLayout(new StackLayout());

		getLeftContainer().populate(cleftcompo, getReportControler().createControls(cleftcompo, ph));
		getLeftContainer().switchView(ReportControler.FORM_PARAMETERS);
	}

	public void runReport(DataAdapterDescriptor myDataAdapter) {
		if (isNotRunning()) {
			// check if we can run the report
			topToolBarManager.setEnabled(false);
			leftToolbar.setEnabled(false);
			getLeftContainer().setEnabled(false);
			getLeftContainer().switchView(ReportControler.FORM_PARAMETERS);

			// Cache the DataAdapter used for this report only if it is not null.
			if (myDataAdapter != null) {
				// $TODO should we save the reference in the JRXML ?
				dataAdapterDesc = myDataAdapter;
			} else {
				dataAdapterDesc = leftToolbar.getDataSourceWidget().getSelected();
			}

			reportControler.runReport();
		}
	}

	private boolean notRunning = true;

	public void setNotRunning(boolean norun) {
		this.notRunning = norun;
		if (topToolBarManager != null)
			topToolBarManager.refreshToolbar();
		if (norun) {
			getLeftContainer().setEnabled(true);
			topToolBarManager.setEnabled(true);
			leftToolbar.setEnabled(true);
		}
	}

	public boolean isNotRunning() {
		return notRunning;
	}

	private ReportControler reportControler;

	private ReportControler getReportControler() {
		if (reportControler == null)
			reportControler = new ReportControler(this);
		return reportControler;
	}

	private Console console;

	public Console getConsole() {
		if (console == null)
			console = Console.showConsole(getEditorInput().getName());
		return console;
	}

	public void setJasperDesign(JasperDesign jasperDesign) {
		getReportControler().setJasperDesign(jasperDesign);
		setupDataAdapter();
	}

	private void setupDataAdapter() {
		if (leftToolbar != null && getReportControler().getjDesign() != null) {
			String strda = getReportControler().getjDesign().getProperty(MReport.DEFAULT_DATAADAPTER);
			if (strda != null)
				leftToolbar.setDataAdapters(strda);
		}
	}

	private DataAdapterDescriptor dataAdapterDesc;
	private SashForm sashform;
	private LeftToolBarManager leftToolbar;

	public DataAdapterDescriptor getDataAdapterDesc() {
		return dataAdapterDesc;
	}

}
