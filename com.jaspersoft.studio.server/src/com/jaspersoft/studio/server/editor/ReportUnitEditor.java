/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.IParametrable;
import com.jaspersoft.studio.editor.preview.IRunReport;
import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.AViewsFactory;
import com.jaspersoft.studio.editor.report.CachedSelectionProvider;
import com.jaspersoft.studio.editor.report.CommonSelectionCacheProvider;
import com.jaspersoft.studio.editor.util.StringInput;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.server.editor.action.RunStopAction;
import com.jaspersoft.studio.swt.toolbar.ToolItemContribution;
import com.jaspersoft.studio.swt.widgets.CSashForm;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

public class ReportUnitEditor extends PreviewJRPrint implements IRunReport, IParametrable, CachedSelectionProvider {
	public static final String ID = "com.jaspersoft.studio.server.editor.ReportUnitEditor";
	private String reportUnitURI;

	public ReportUnitEditor() {
		super(false);
	}

	@Override
	protected void loadJRPrint(IEditorInput input) throws PartInitException {
		try {
			reportUnitURI = FileUtils
					.readInputStreamAsString(((StringInput) getEditorInput()).getStorage().getContents());
		} catch (Exception e1) {
			throw new PartInitException(e1.getMessage(), e1);
		}
		UIUtils.getDisplay().asyncExec(this::runReport);

	}

	@Override
	public void setCurrentViewer(String viewerKey, boolean refresh) {
		super.setCurrentViewer(viewerKey, refresh);

		getActionToolBarManager(null).contributeItems(getRightContainer().getViewer(viewerKey));
	}

	@Override
	protected void afterRightSwitchView() {
		runReport();
	}

	public void runReport() {
		if (isNotRunning()) {
			// check if we can run the report
			actionToolBarManager.setEnabled(false);
			dataDapterToolBarManager.setEnabled(false);
			if (leftToolbar != null)
				leftToolbar.setEnabled(false);
			getLeftContainer().setEnabled(false);
			getLeftContainer().switchView(null, ReportRunControler.FORM_PARAMETERS);

			reportControler.setReportUnit(reportUnitURI);
		}
	}

	@Override
	protected PreviewTopToolBarManager getDataAdapterToolBarManager(Composite container) {
		if (dataDapterToolBarManager == null)
			dataDapterToolBarManager = new PreviewTopToolBarManager(this, container);
		return (PreviewTopToolBarManager) dataDapterToolBarManager;
	}

	private CSashForm sashform;
	private LeftToolBarManager leftToolbar;

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(3, false));

		getDataAdapterToolBarManager(container);
		getActionToolBarManager(container);

		Button lbutton = new Button(container, SWT.PUSH);
		lbutton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		lbutton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/application-sidebar-expand.png"));
		lbutton.setToolTipText("Show Parameters");
		lbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sashform.upRestore();
			}
		});

		sashform = new CSashForm(container, SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		sashform.setLayoutData(gd);

		createLeft(parent, sashform);

		createRight(sashform);

		sashform.setWeights(new int[] { 100, 150 });
	}

	@Override
	public String getTitleToolTip() {
		if (reportUnitURI != null) {
			int lastPartIndex = reportUnitURI.lastIndexOf(":"); //$NON-NLS-1$
			if (lastPartIndex != -1) {
				return reportUnitURI.substring(0, lastPartIndex);
			}
		}
		return super.getTitleToolTip();
	}

	protected void createLeft(Composite parent, SashForm sf) {
		Composite leftComposite = new Composite(sf, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		leftComposite.setLayout(layout);

		leftToolbar = new LeftToolBarManager(this, leftComposite);

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
		toolItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Job job = new Job("Update Report Options") {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask(Messages.PreviewContainer_resetactiontooltip, IProgressMonitor.UNKNOWN);
						try {
							reportControler.resetParametersToDefault(monitor);
						} catch (Exception e) {
							UIUtils.showError(e);
						}
						return Status.OK_STATUS;
					}
				};
				job.setPriority(Job.LONG);
				job.schedule();
			}

		});
		tbm.update(true);

		getLeftContainer().populate(cleftcompo, getReportControler().createControls(cleftcompo, jrContext));
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
							leftToolbar.setLabelText(MessagesByKeys.getString(key));
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

	@Override
	public boolean switchRightView(APreview view, Statistics stats, MultiPageContainer container) {
		reportControler.viewerChanged(view);
		return super.switchRightView(view, stats, container);
	}

	public String getDefaultViewerKey() {
		if (currentViewer == null)
			currentViewer = ReportUnitViewsFactory.DEFAULT;
		return currentViewer;
	}

	@Override
	public AViewsFactory getViewFactory() {
		if (viewFactory == null)
			viewFactory = new ReportUnitViewsFactory();
		return viewFactory;
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

	@Override
	public void setMode(String mode) {
		// do nothing
	}

	private CommonSelectionCacheProvider selectionCache = new CommonSelectionCacheProvider();

	@Override
	public CommonSelectionCacheProvider getSelectionCache() {
		return selectionCache;
	}
}
