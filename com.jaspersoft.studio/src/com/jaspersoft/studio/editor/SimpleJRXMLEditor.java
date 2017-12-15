/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.util.Arrays;

import net.sf.jasperreports.eclipse.builder.jdt.JRErrorHandler;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageSelectionProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.editor.preview.view.control.VErrorPreview;
import com.jaspersoft.studio.editor.report.CachedSelectionProvider;
import com.jaspersoft.studio.editor.report.CommonSelectionCacheProvider;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.editor.report.SimpleReportEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * A reduced version of the report editor that can be used to edit tool. 
 * This editor dosen't provide the preview and the compilation and the actions
 * related to it. Some other actions were removed, like the create dataset action,
 * since their not needed for a tool
 * 
 * @author Orlandin Marco
 *
 */
public class SimpleJRXMLEditor extends AbstractJRXMLEditor implements  IJROBjectEditor, CachedSelectionProvider {

	/**
	 * The editor id
	 */
	public static final String ID = "com.jaspersoft.studio.editor.SimpleJRXMLEditor";
	
	/**
	 * The visual editor of the report
	 */
	protected ReportContainer reportContainer;

	/**
	 * Part of the refactored listener structure from MultiPageEditorPart, refactored
	 * since it is not accessible on the original class
	 */
	protected ListenerList pageChangeListeners = new ListenerList(ListenerList.IDENTITY);
	
	/**
	 * Create the preview and source pages
	 */
	@Override
	protected void createPages() {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getContainer(), getEditorHelpID());
		if (jrContext != null)
			try {
				createDesignEditorPage();
				createSourceEditorPage();
			} catch (PartInitException e) {
				UIUtils.showError(new Exception(Messages.common_error_creating_nested_visual_editor));
			} catch (Throwable e) {
				closeEditorOnErrors();
				JaspersoftStudioPlugin.getInstance().logError(e);
			}
		IPartListener2 pl = new IPartListener2() {

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				if (partRef.getPart(false) == getSite().getPart())
					partActivated = true;
			}

			@Override
			public void partBroughtToTop(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partClosed(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partDeactivated(IWorkbenchPartReference partRef) {
				if (partRef.getPart(false) == getSite().getPart())
					partActivated = false;
			}

			@Override
			public void partOpened(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partHidden(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partVisible(IWorkbenchPartReference partRef) {
			}

			@Override
			public void partInputChanged(IWorkbenchPartReference partRef) {
			}

		};
		getSite().getPage().addPartListener(pl);
	}

	/**
	 * Save the report 
	 */
	@Override
	protected void doSaveEditors(final IProgressMonitor monitor) {
		xmlEditor.doSave(monitor);
		getDesignEditor().doSave(monitor);

		xmlEditor.isDirty();
		getDesignEditor().isDirty();
		xmlFresh = true;
	}

	/**
	 * Make the editor dirty when some property changes
	 */
	@Override
	protected void handlePropertyChange(final int propertyId) {
		if (!isRefreshing) {
			if (propertyId == ISaveablePart.PROP_DIRTY){
				xmlFresh = false;
			}
			// Can indirectly refresh the widgets so it must be executed inside the
			// graphic thread, but to avoid concurrency problems it is a sync exec
			UIUtils.getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					SimpleJRXMLEditor.super.handlePropertyChange(propertyId);
				}
			});
		}
	}

	@Override
	protected void pageChange(final int newPageIndex) {
		if (newPageIndex == PAGE_DESIGNER || newPageIndex == PAGE_SOURCEEDITOR) {
			if (activePage == PAGE_DESIGNER) {
				if (outlinePage != null) {
					tmpselection = outlinePage.getSite().getSelectionProvider().getSelection();
				} else {
					tmpselection = getDesignerPageSelection();
				}
			}
			String ver = JRXmlWriterHelper.getVersion(getCurrentFile(), jrContext, false);
			switch (newPageIndex) {
			case PAGE_DESIGNER:
				if (activePage == PAGE_SOURCEEDITOR && !xmlFresh) {
					try {
						xml2model();
					} catch (Exception e) {
						toXML = true;
						handleJRException(getEditorInput(), e, false);
					}
					updateVisualView();
				} else {
					updateVisualView();
				}
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						if (outlinePage != null) {
							outlinePage.getSite().getSelectionProvider().setSelection(tmpselection);
						} else {
							setDesignerPageSelection(tmpselection);
						}
					}
				});
				break;
			case PAGE_SOURCEEDITOR:
				if (toXML)
					toXML = false;
				else {
					// This flag avoid to have the xml editor dirty when switching
					// because of the timestamp
					isRefreshing = true;
					model2xml(ver);
					isRefreshing = false;
				}
				break;
			}
		}
		notify(newPageIndex);
		updateContentOutline(getActivePage());
		activePage = newPageIndex;
	}
	
	/**
	 * This is essentially a refactor of the pageChange method of the MultiPageEditorPart.
	 * This need to be called by the overrided page change but since we don't want to call
	 * also the pageChange of the AbstractJRXMLEditor the only solution is to refactor it and
	 * the listeners structure used by it, because in the original class it is private 
	 * 
	 * 
	 * Notifies this multi-page editor that the page with the given id has been
	 * activated. This method is called when the user selects a different tab.
	 * <p>
	 * The <code>MultiPageEditorPart</code> implementation of this method sets
	 * focus to the new page, and notifies the action bar contributor (if there
	 * is one). This checks whether the action bar contributor is an instance of
	 * <code>MultiPageEditorActionBarContributor</code>, and, if so, calls
	 * <code>setActivePage</code> with the active nested editor. This also
	 * fires a selection change event if required.
	 * </p>
	 * <p>
	 * Subclasses may extend this method.
	 * </p>
	 * 
	 * @param newPageIndex
	 *            the index of the activated page
	 */
	protected void notify(int newPageIndex) {
		deactivateSite(false, false);

		IPartService partService = (IPartService) getSite().getService(
				IPartService.class);
		if (partService.getActivePart() == this) {
			setFocus();
		}

		IEditorPart activeEditor = getEditor(newPageIndex);

		IEditorActionBarContributor contributor = getEditorSite()
				.getActionBarContributor();
		if (contributor != null
				&& contributor instanceof MultiPageEditorActionBarContributor) {
			((MultiPageEditorActionBarContributor) contributor)
					.setActivePage(activeEditor);
		}

		if (activeEditor != null) {
			ISelectionProvider selectionProvider = activeEditor.getSite()
					.getSelectionProvider();
			if (selectionProvider != null) {
				ISelectionProvider outerProvider = getSite()
						.getSelectionProvider();
				if (outerProvider instanceof MultiPageSelectionProvider) {
					SelectionChangedEvent event = new SelectionChangedEvent(
							selectionProvider, selectionProvider.getSelection());

					MultiPageSelectionProvider provider = (MultiPageSelectionProvider) outerProvider;
					provider.fireSelectionChanged(event);
					provider.firePostSelectionChanged(event);
				} 
			}
		}

		activateSite();
		Object selectedPage = getSelectedPage();
		if (selectedPage != null) {
			firePageChanged(new PageChangedEvent(this, selectedPage));
		}
	}
	
	/**
	 * Part of the refactored listener structure from MultiPageEditorPart, refactored
	 * since it is not accessible on the original class
	 */
	protected void firePageChanged(final PageChangedEvent event) {
		Object[] listeners = pageChangeListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final IPageChangedListener l = (IPageChangedListener) listeners[i];
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					l.pageChanged(event);
				}
			});
		}
	}
	
	
	/**
	 * Add the page change listener to be notified when the page changes. The
	 * newly selected page will be the Object returned from
	 * {@link #getSelectedPage()}. In the default case, this will be the active
	 * page Control, IEditorPart, or <code>null</code>.
	 * <p>
	 * This method has no effect if the listener has already been added.
	 * </p>
	 */
	@Override
	public void addPageChangedListener(IPageChangedListener listener) {
		pageChangeListeners.add(listener);
	}

	/**
	 * Remove the page change listener.
	 * <p>
	 * This method has no effect if the listener is not in the list.
	 * </p>
	 */
	@Override
	public void removePageChangedListener(IPageChangedListener listener) {
		pageChangeListeners.remove(listener);
	}
	
	@Override
	protected String getEditorHelpID() {
		return "com.jaspersoft.studio.doc.editor_simple_jrxml";
	}
	
	/**
	 * Converts the current model to XML, specifying a version it should be compliant with.
	 * 
	 * @param version the JasperReports version that should be used for compliance.
	 */
	@Override
	protected String model2xml(String version) {
		String xml = null;
		try {
			MReport mReport = getMReport();
			if (mReport != null) {
				JasperDesign report = mReport.getJasperDesign();
				xml = JRXmlWriterHelper.writeReport(jrContext, report, FileUtils.UTF8_ENCODING, version);
				IDocumentProvider dp = xmlEditor.getDocumentProvider();
				IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
				xmlFresh = true;
				if (xml != null && !Arrays.equals(doc.get().getBytes(), xml.getBytes())) {
					doc.set(xml);
				}
			}
		} catch (Throwable e) {
			UIUtils.showError(e);
		}
		return xml;
	}

	/**
	 * Check if the current editor is dirty
	 * 
	 * @return true if the design or the preview editor are dirty, or if the xml inside the xml editor is not fresh. False
	 *         otherwise
	 */
	@Override
	public boolean isDirty() {
		boolean designDirty = getDesignEditor() != null && getDesignEditor().isDirty();
		if (designDirty)
			return true;
		return !xmlFresh;
	}

	/**
	 * Create the editor used to edit visually the report
	 */
	@Override
	protected void createDesignEditorPage() throws PartInitException {
		reportContainer = new ReportContainer(this, jrContext){
			
			/**
			 * Return the simple report editor as visual editor 
			 */
			@Override
			protected ReportEditor createReportEditor(JasperReportsConfiguration context) {
				return new SimpleReportEditor(context);
			}
		};
		
		reportContainer.addPageChangedListener(new IPageChangedListener() {

			public void pageChanged(PageChangedEvent event) {
				updateContentOutline(getActivePage());
			}
		});

		int index = addPage(reportContainer, getEditorInput());
		setPageText(index, Messages.JrxmlEditor_design);
	}
	
	/**
	 * Force the refresh of the visual editor
	 */
	@Override
	public void updateVisualView() {
		if (reportContainer != null)
			reportContainer.setModel(getModel());
	}

	/**
	 * Move to the marker of an error
	 */
	@Override
	public void gotoMarker(IMarker marker) {
		if (activePage == PAGE_DESIGNER) {
			try {
				Object expr = marker.getAttribute(JRErrorHandler.MARKER_ERROR_JREXPRESSION);
				if (expr != null && expr instanceof String) {
					JRDesignExpression expression = new JRDesignExpression();
					expression.setId(new Integer((String) expr));
					JasperDesign jd = getJasperDesign();
					JRExpressionCollector rc = JRExpressionCollector.collector(jrContext, jd);
					if (!VErrorPreview.openExpressionEditor(jrContext, rc, (JRDesignDataset) jd.getMainDataset(), expression))
						for (JRDataset d : jd.getDatasetsList())
							if (VErrorPreview.openExpressionEditor(jrContext, rc, (JRDesignDataset) d, expression))
								return;
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		setActivePage(PAGE_SOURCEEDITOR);
		IDE.gotoMarker(xmlEditor, marker);
	}

	@Override
	public CommonSelectionCacheProvider getSelectionCache() {
		return reportContainer.getSelectionCache();
	}

	@Override
	protected boolean isDesignerDirty() {
		return reportContainer.isDirty();
	}

	@Override
	protected ISelection getDesignerPageSelection() {
		return reportContainer.getActiveEditor().getSite().getSelectionProvider().getSelection();
	}

	@Override
	protected void setDesignerPageSelection(ISelection newSelection) {
		reportContainer.getActiveEditor().getSite().getSelectionProvider().setSelection(newSelection);
	}
	
	@Override
	protected EditorPart getDesignEditor() {
		return reportContainer;
	}

	/**
	 * Create a simpler report model for the tool
	 */
	@Override
	protected INode createEditorModel() {
		return ReportFactory.createToolReport(jrContext);
	}

	/**
	 * Opens an editor (even an internal one), using the 
	 * specified object and Anode information.
	 */
	@Override
	public void openEditor(Object obj, ANode node) {
		reportContainer.openEditor(obj, node);
	}
	
	@Override
	public void dispose() {
		pageChangeListeners.clear();
		super.dispose();
	}
	
	@Override
	public String getTitleToolTip() {
		return JaspersoftStudioPlugin.getExtensionManager().getTitleToolTip(jrContext, super.getTitleToolTip());
	}

	/**
	 * @return the design editor
	 */
	public ReportContainer getReportContainer() {
		return reportContainer;
	}
	
	/**
	 * Gets the current active inner editor.
	 * The {@link ReportContainer} is itself a {@link MultiPageEditorPart}, so
	 * it can contains different opened editors (i.e. lists, tables, cross-tabs).
	 * 
	 * @return the second level active editor
	 */
	@Override
	public IEditorPart getActiveInnerEditor() {
		IEditorPart iep = getActiveEditor();
		if (iep instanceof ReportContainer)
			return ((ReportContainer) iep).getActiveEditor();
		return iep;
	}
}
