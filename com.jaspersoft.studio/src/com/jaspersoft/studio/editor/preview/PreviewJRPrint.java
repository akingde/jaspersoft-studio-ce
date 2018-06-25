/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.editor.action.PrintAction;
import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.toolbar.ATopToolBarManager;
import com.jaspersoft.studio.editor.preview.toolbar.TopToolBarManagerJRPrint;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.AViewsFactory;
import com.jaspersoft.studio.editor.preview.view.ViewsFactory;
import com.jaspersoft.studio.editor.preview.view.control.VSimpleErrorPreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Console;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;

public class PreviewJRPrint extends ABasicEditor {
	private boolean hideParameters = true;

	public void setHideParameters(boolean hideParameters) {
		this.hideParameters = hideParameters;
	}

	public boolean isHideParameters() {
		return hideParameters;
	}

	private JasperPrint jasperPrint;

	public PreviewJRPrint() {
		super(true);
	}

	public PreviewJRPrint(boolean listenresource) {
		super(listenresource);
	}

	private ActionRegistry actionRegistry;

	protected ActionRegistry getActionRegistry() {
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();
		return actionRegistry;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == ActionRegistry.class)
			return getActionRegistry();
		if (adapter == JasperPrint.class)
			return getJasperPrint();
		return super.getAdapter(adapter);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		loadJRPrint(getEditorInput());

		ActionRegistry registry = getActionRegistry();
		IAction action = new PrintAction(this);
		registry.registerAction(action);
		getEditorSite().getActionBars().setGlobalActionHandler(ActionFactory.PRINT.getId(), action);
	}

	protected void loadJRPrint(IEditorInput input) throws PartInitException {
		InputStream in = null;
		try {
			IFile file = null;
			if (input instanceof IFileEditorInput) {
				file = ((IFileEditorInput) input).getFile();
				in = file.getContents();
			} else {
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
			}
			Statistics stats = new Statistics();
			if (file.getFileExtension().equalsIgnoreCase("jrpxml")) { //$NON-NLS-1$
				setJasperPrint(stats, JRPrintXmlLoader.load(jrContext, in));
			} else {
				Object obj = JRLoader.loadObject(jrContext, in);
				if (obj instanceof JasperPrint)
					setJasperPrint(stats, (JasperPrint) obj);
			}
		} catch (Exception e) {
			throw new PartInitException(Messages.PreviewJRPrint_1, e);
		}
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(final Statistics stats, JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
		UIUtils.getDisplay().asyncExec(() -> {
			if (getDefaultViewer() instanceof IJRPrintable) {
				JasperPrint jrprint = getJasperPrint();
				if (jrprint != null)
					getRightContainer().switchView(stats, getDefaultViewerKey());
				else
					getRightContainer().switchView(stats, errorPreview);
			}
		});
	}

	protected String currentViewer;

	public String getCurrentViewer() {
		return currentViewer;
	}

	/**
	 * Set the current preview type
	 * 
	 * @param viewerKey
	 *            key of the type to show
	 * @param refresh
	 *            flag to set if the preview should also be refreshed
	 */
	public void setCurrentViewer(String viewerKey, boolean refresh) {
		if (getViewFactory().getKeys().contains(viewerKey)) {
			currentViewer = viewerKey;
			if (refresh)
				rightContainer.switchView(currentViewer);
		}
	}

	public String getDefaultViewerKey() {
		if (currentViewer == null)
			currentViewer = ViewsFactory.VIEWER_JAVA;
		return currentViewer;
	}

	public APreview getDefaultViewer() {
		return getRightContainer().getViewer(getDefaultViewerKey());
	}

	protected void afterRightSwitchView() {
		// do nothing
	}

	protected MultiPageContainer rightContainer;

	public MultiPageContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new MultiPageContainer() {
				private boolean same = false;

				@Override
				public void afterSwitchView() {
					afterRightSwitchView();
				}

				@Override
				public void switchView(Statistics stats, String key) {
					same = currentViewer == key;
					currentViewer = key;
					APreview view = pmap.get(key);
					if (view instanceof IJRPrintable) {
						JasperPrint jp = ((IJRPrintable) view).getJrPrint();
						if (jp == null)
							same = false;
					}
					super.switchView(stats, key);
				}

				@Override
				public void switchView(String key) {
					same = currentViewer == key;
					currentViewer = key;
					super.switchView(key);
				}

				@Override
				public void switchView(Statistics stats, final APreview view) {
					if (!same && !switchRightView(view, stats, this))
						return;
					super.switchView(stats, view);
					if (!same || !view.isContributed2ToolBar())
						Display.getDefault().syncExec(() -> {
							if (actionToolBarManager != null)
								actionToolBarManager.contributeItems(view);
						});
					else
						actionToolBarManager.refreshToolbar();
				}

				@Override
				public void dispose() {
					super.dispose();
					actionToolBarManager.removeAll();
				}
			};
		}
		return rightContainer;
	}

	public boolean switchRightView(APreview view, Statistics stats, MultiPageContainer container) {
		if (view instanceof IJRPrintable) {
			try {
				((IJRPrintable) view).setJRPRint(stats, jasperPrint);
				if (console != null)
					console.setStatistics(stats);
			} catch (Exception e) {
				errorPreview.setMessage(Messages.PreviewJRPrint_2);
				container.switchView(stats, errorPreview);

				getConsole().addError(e, null);
				return false;
			}
		}
		return true;
	}

	@Override
	public void dispose() {
		super.dispose();
		getRightContainer().dispose();
	}

	protected TopToolBarManagerJRPrint actionToolBarManager;

	protected TopToolBarManagerJRPrint getActionToolBarManager(Composite container) {
		if (actionToolBarManager == null)
			actionToolBarManager = new TopToolBarManagerJRPrint(this, container);
		return actionToolBarManager;
	}

	protected ATopToolBarManager dataDapterToolBarManager;

	protected ATopToolBarManager getDataAdapterToolBarManager(Composite container) {
		if (dataDapterToolBarManager == null)
			dataDapterToolBarManager = new ATopToolBarManager(this, container) {

				@Override
				protected void fillToolbar(IToolBarManager tbManager) {
					// do nothing
				}
			};
		return dataDapterToolBarManager;
	}

	protected VSimpleErrorPreview errorPreview;

	public VSimpleErrorPreview getErrorView() {
		return errorPreview;
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		PlatformUI.getWorkbench().getHelpSystem().setHelp(container, "com.jaspersoft.studio.doc.editor_jrprint"); //$NON-NLS-1$

		getDataAdapterToolBarManager(container);
		getActionToolBarManager(container);

		Composite rcmp = createRight(container);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		rcmp.setLayoutData(gd);
	}

	protected Composite createRight(Composite parent) {
		rightComposite = new Composite(parent, SWT.BORDER);

		StackLayout stacklayoutView = new StackLayout();
		rightComposite.setLayout(stacklayoutView);

		getRightContainer().populate(rightComposite, getViewFactory().createPreviews(rightComposite, jrContext));

		errorPreview = new VSimpleErrorPreview(rightComposite, jrContext);

		return rightComposite;
	}

	protected AViewsFactory viewFactory;

	public AViewsFactory getViewFactory() {
		if (viewFactory == null)
			viewFactory = new ViewsFactory();
		return viewFactory;
	}

	@Override
	public void setFocus() {
		if (dataDapterToolBarManager != null)
			dataDapterToolBarManager.setFocus();
	}

	private boolean notRunning = true;

	public void setNotRunning(boolean norun) {
		this.notRunning = norun;

		if (dataDapterToolBarManager != null) {
			dataDapterToolBarManager.refreshToolbar();
			if (norun)
				dataDapterToolBarManager.setEnabled(true);
		}

		if (actionToolBarManager != null) {
			actionToolBarManager.refreshToolbar();
			if (norun)
				actionToolBarManager.setEnabled(true);
		}
	}

	public boolean isNotRunning() {
		return notRunning;
	}

	private Console console;
	protected Composite rightComposite;

	public Console getConsole() {
		if (console == null)
			console = Console.showConsole(getEditorInput().getName(), jrContext);
		return console;
	}

}
