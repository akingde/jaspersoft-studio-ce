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
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.actions.SwitchViewsAction;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.toolbar.LeftToolBarManager;
import com.jaspersoft.studio.editor.preview.toolbar.PreviewTopToolBarManager;
import com.jaspersoft.studio.editor.preview.toolbar.TopToolBarManagerJRPrint;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.editor.preview.view.report.html.JiveViewer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.swt.toolbar.ToolItemContribution;
import com.jaspersoft.studio.swt.widgets.CSashForm;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class PreviewContainer extends PreviewJRPrint implements IDataAdapterRunnable, IParametrable, IRunReport {

	public PreviewContainer() {
		super(true);
	}

	public PreviewContainer(boolean listenResource, JasperReportsConfiguration jrContext) {
		super(listenResource);
		this.jrContext = jrContext;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	protected void loadJRPrint(IEditorInput input) throws PartInitException {
		setJasperPrint(null, null);
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
				in = JrxmlEditor.getXML(jrContext, input, file.getCharset(true), in, null);
				getJrContext(file);
				jrContext.setJasperDesign(JRXmlLoader.load(in));
				setJasperDesign(jrContext);
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

		sashform.setWeights(new int[] { 40, 60 });
	}

	@Override
	protected PreviewTopToolBarManager getTopToolBarManager1(Composite container) {
		if (topToolBarManager1 == null) {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			topToolBarManager1 = new PreviewTopToolBarManager(this, container, DataAdapterManager.getDataAdapter(file));
		}
		return (PreviewTopToolBarManager) topToolBarManager1;
	}

	protected TopToolBarManagerJRPrint getTopToolBarManager(Composite container) {
		if (topToolBarManager == null)
			topToolBarManager = new TopToolBarManagerJRPrint(this, container) {
				protected void fillToolbar(IToolBarManager tbManager) {
					if (runMode.equals(RunStopAction.MODERUN_LOCAL)) {
						if (pvModeAction == null)
							pvModeAction = new SwitchViewsAction(container.getRightContainer(), Messages.PreviewContainer_javatitle,
									true);
						tbManager.add(pvModeAction);
					}
					tbManager.add(new Separator());
				}
			};
		return topToolBarManager;
	}

	protected void createLeft(Composite parent, SashForm sf) {
		Composite leftComposite = new Composite(sf, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		leftComposite.setLayout(layout);

		leftToolbar = new LeftToolBarManager(this, leftComposite);
		setupDataAdapter();

		final Composite cleftcompo = new Composite(leftComposite, SWT.NONE);
		cleftcompo.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		cleftcompo.setLayoutData(new GridData(GridData.FILL_BOTH));
		cleftcompo.setLayout(new StackLayout());

		Composite bottom = new Composite(leftComposite, SWT.NONE);
		bottom.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		bottom.setLayout(new GridLayout(2, false));

		ToolBar tb = new ToolBar(bottom, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		ToolBarManager tbm = new ToolBarManager(tb);
		tbm.add(new RunStopAction(this));
		ToolItemContribution tireset = new ToolItemContribution("", SWT.PUSH); //$NON-NLS-1$
		tbm.add(tireset);
		tbm.update(true);
		ToolItem toolItem = tireset.getToolItem();
		toolItem.setText(Messages.PreviewContainer_resetactiontitle);
		toolItem.setToolTipText(Messages.PreviewContainer_resetactiontooltip);
		toolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				reportControler.resetParametersToDefault();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		tbm.update(true);

		getLeftContainer().populate(cleftcompo, getReportControler().createControls(cleftcompo));
		getLeftContainer().switchView(null, ReportControler.FORM_PARAMETERS);
	}

	private JiveViewer jiveViewer;

	@Override
	protected Composite createRight(Composite parent) {
		super.createRight(parent);

		jiveViewer = new JiveViewer(rightComposite, jrContext);

		return rightComposite;
	}

	@Override
	protected boolean switchRightView(APreview view, Statistics stats, MultiPageContainer container) {
		reportControler.viewerChanged(view);
		return super.switchRightView(view, stats, container);
	}

	public void runReport(final DataAdapterDescriptor myDataAdapter) {
		if (isNotRunning()) {
			// check if we can run the report
			topToolBarManager.setEnabled(false);
			topToolBarManager1.setEnabled(false);
			leftToolbar.setEnabled(false);
			getLeftContainer().setEnabled(false);
			getLeftContainer().switchView(null, ReportControler.FORM_PARAMETERS);

			// Cache the DataAdapter used for this report only if it is not null.
			if (myDataAdapter != null) {
				// TODO should we save the reference in the JRXML ?
				dataAdapterDesc = myDataAdapter;
			} else {
				dataAdapterDesc = ((PreviewTopToolBarManager) topToolBarManager1).getDataSourceWidget().getSelected();
			}

			reportControler.runReport();
		}
	}

	@Override
	public void setNotRunning(boolean stoprun) {
		super.setNotRunning(stoprun);
		if (stoprun) {
			getLeftContainer().setEnabled(true);
			leftToolbar.setEnabled(true);
		}
		isRunDirty = false;
	}

	public void showParameters(boolean showprm) {
		if (showprm)
			sashform.upRestore();
		else
			sashform.upHide();
	}

	private ReportControler reportControler;

	public ReportControler getReportControler() {
		if (reportControler == null) {
			reportControler = new ReportControler(this, jrContext);
		}
		return reportControler;
	}

	protected boolean isRunDirty = true;

	public void setRunDirty(boolean isRunDirty) {
		this.isRunDirty = isRunDirty;
	}

	public void setJasperDesign(final JasperReportsConfiguration jConfig) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				Thread.currentThread().setContextClassLoader(jrContext.getClassLoader());
				getReportControler().setJrContext(jConfig);
				setupDataAdapter();

				if (isRunDirty || getJasperPrint() == null)
					runReport(dataAdapterDesc);
				isRunDirty = false;
			}
		});
	}

	private void setupDataAdapter() {
		JasperDesign jd = getReportControler().getJrContext().getJasperDesign();
		PreviewTopToolBarManager pt = (PreviewTopToolBarManager) topToolBarManager1;
		if (pt != null && jd != null) {
			String strda = jd.getProperty(DataQueryAdapters.DEFAULT_DATAADAPTER);
			if (strda != null)
				pt.setDataAdapters(strda);
		}
	}

	private DataAdapterDescriptor dataAdapterDesc;

	public DataAdapterDescriptor getDataAdapterDesc() {
		return dataAdapterDesc;
	}

	private String runMode = RunStopAction.MODERUN_LOCAL;

	public void setMode(String mode) {
		this.runMode = mode;
		if (mode.equals(RunStopAction.MODERUN_JIVE))
			getRightContainer().switchView(null, jiveViewer);
		else if (mode.equals(RunStopAction.MODERUN_LOCAL))
			getRightContainer().switchView(null, getDefaultViewerKey());
	}

	public String getMode() {
		return runMode;
	}

	public JiveViewer getJiveViewer() {
		return jiveViewer;
	}

	@Override
	public void runReport() {
		runReport(null);
	}

}
