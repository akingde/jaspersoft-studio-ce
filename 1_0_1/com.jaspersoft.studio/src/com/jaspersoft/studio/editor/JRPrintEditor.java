/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.sf.jasperreports.eclipse.builder.JasperReportsNature;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.internal.InternalImages;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

import com.jasperassistant.designer.viewer.ReportViewer;
import com.jasperassistant.designer.viewer.actions.FirstPageAction;
import com.jasperassistant.designer.viewer.actions.LastPageAction;
import com.jasperassistant.designer.viewer.actions.NextPageAction;
import com.jasperassistant.designer.viewer.actions.PageNumberContributionItem;
import com.jasperassistant.designer.viewer.actions.PreviousPageAction;
import com.jasperassistant.designer.viewer.actions.ZoomActualSizeAction;
import com.jasperassistant.designer.viewer.actions.ZoomComboContributionItem;
import com.jasperassistant.designer.viewer.actions.ZoomFitPageAction;
import com.jasperassistant.designer.viewer.actions.ZoomFitPageWidthAction;
import com.jasperassistant.designer.viewer.actions.ZoomInAction;
import com.jasperassistant.designer.viewer.actions.ZoomOutAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsCsvAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsCsvMetadataAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsDocxAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsExcelAPIAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsHtmlAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsJasperReportsAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsOdsAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsOdtAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsPdfAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsPptxAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsRtfAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsTextAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsXHtmlAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsXlsAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsXlsxAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsXmlAction;
import com.jaspersoft.studio.editor.preview.action.ExportAsXmlWithImagesAction;
import com.jaspersoft.studio.editor.preview.action.ExportMenuAction;
import com.jaspersoft.studio.utils.ErrorUtil;

public class JRPrintEditor extends EditorPart {

	public JRPrintEditor(boolean listenResource) {
		if (listenResource) {
			partListener = new ResourcePartListener();
			resourceListener = new ResourceTracker();
		}
	}

	class ResourceTracker implements IResourceChangeListener, IResourceDeltaVisitor {
		public void resourceChanged(IResourceChangeEvent event) {
			IResourceDelta delta = event.getDelta();
			try {
				if (delta != null)
					delta.accept(this);
			} catch (CoreException exception) {
				// What should be done here?
			}
		}

		public boolean visit(IResourceDelta delta) {
			if (delta == null || !delta.getResource().equals(((IFileEditorInput) getEditorInput()).getFile()))
				return true;
			Display display = getSite().getShell().getDisplay();
			if (delta.getKind() == IResourceDelta.REMOVED) {
				if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) { // if the file was deleted
					// NOTE: The case where an open, unsaved file is deleted is being handled by the
					// PartListener added to the Workbench in the initialize() method.
					display.asyncExec(new Runnable() {
						public void run() {
							if (!isDirty())
								getSite().getPage().closeEditor(JRPrintEditor.this, false);
						}
					});
				} else { // else if it was moved or renamed
					final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getMovedToPath());
					display.asyncExec(new Runnable() {
						public void run() {
							superSetInput(new FileEditorInput(newFile));
						}
					});
				}
			} else if (delta.getKind() == IResourceDelta.CHANGED) {
				// the file was overwritten somehow (could have been replaced by another
				// version in the respository)
				final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getFullPath());
				display.asyncExec(new Runnable() {
					public void run() {
						setInput(new FileEditorInput(newFile));
					}
				});

			}
			return false;
		}
	}

	class ResourcePartListener implements IPartListener {
		// If an open, unsaved file was deleted, query the user to either do a "Save As"
		// or close the editor.
		public void partActivated(IWorkbenchPart part) {
			if (part != JRPrintEditor.this)
				return;
			if (!((IFileEditorInput) getEditorInput()).getFile().exists())
				getSite().getPage().closeEditor(JRPrintEditor.this, false);
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
		}
	};

	private IPartListener partListener;
	private ResourceTracker resourceListener;

	@Override
	public void dispose() {
		if (partListener != null)
			getSite().getWorkbenchWindow().getPartService().removePartListener(partListener);
		partListener = null;
		if (resourceListener != null)
			((IFileEditorInput) getEditorInput()).getFile().getWorkspace().removeResourceChangeListener(resourceListener);
		super.dispose();
	}

	protected void superSetInput(IEditorInput input) {
		// The workspace never changes for an editor. So, removing and re-adding the
		// resourceListener is not necessary. But it is being done here for the sake
		// of proper implementation. Plus, the resourceListener needs to be added
		// to the workspace the first time around.
		if (resourceListener != null && getEditorInput() != null) {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.getWorkspace().removeResourceChangeListener(resourceListener);
		}

		super.setInput(input);

		if (resourceListener != null && getEditorInput() != null) {
			if (getEditorInput() instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) getEditorInput()).getFile();
				file.getWorkspace().addResourceChangeListener(resourceListener);
				setPartName(file.getName());
			} else if (getEditorInput() instanceof FileStoreEditorInput) {
			}
		}
	}

	@Override
	protected void setSite(IWorkbenchPartSite site) {
		super.setSite(site);
		if (partListener != null)
			getSite().getWorkbenchWindow().getPartService().addPartListener(partListener);
	}

	@Override
	protected void setInput(IEditorInput input) {
		superSetInput(input);
		InputStream in = null;
		try {
			if (input instanceof FileStoreEditorInput) {
				in = new FileInputStream(((FileStoreEditorInput) input).getURI().getPath());
			} else if (input instanceof IFileEditorInput) {
				in = ((IFileEditorInput) input).getFile().getContents();
			} else {
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
			}
			jasperPrint = loadJRObject(in);
			setReportDocument(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------
	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (input instanceof FileStoreEditorInput) {
			try {
				FileStoreEditorInput fsei = (FileStoreEditorInput) input;

				IPath location = new Path(fsei.getURI().getPath());

				// Create a new temporary project object and open it.
				IProject project = null;
				for (IProject prj : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
					if (prj.isOpen()) {
						if (project == null)
							project = prj;
						else

						if (prj.getNature(JasperReportsNature.NATURE_ID) != null)
							project = prj;

					}
				}
				if (project == null)
					ResourcesPlugin.getWorkspace().getRoot().getProject(JrxmlEditor.DEFAULT_PROJECT);
				// Create a project if one doesn't exist and open it.
				if (!project.exists())
					project.create(null);
				if (!project.isOpen())
					project.open(null);

				IFile file = project.getFile(location.lastSegment());
				file.createLink(location, IResource.REPLACE, null);

				input = new FileEditorInput(file);
			} catch (CoreException e) {
				throw new PartInitException(e.getMessage(), e);
			}
			init(site, input);
			return;
		}
		setSite(site);
		setPartName(input.getName());
		setInput(input);
	}

	protected JasperPrint loadJRObject(InputStream in) throws JRException {
		return (JasperPrint) JRLoader.loadObject(in);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	private ReportViewer reportViewer = new ReportViewer(SWT.BORDER | SWT.NO_FOCUS);
	private Control reportViewerControl;

	public ReportViewer getReportViewer() {
		return reportViewer;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public Control getReportViewerControl() {
		return reportViewerControl;
	}

	public IToolBarManager getTbManager() {
		return tbManager;
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = layout.marginHeight = 0;
		container.setLayout(layout);

		initToolBar(container);

		reportViewerControl = reportViewer.createControl(container);
		reportViewerControl.setLayoutData(new GridData(GridData.FILL_BOTH));

		reportViewerControl.addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
				e.getSource();
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void setFocus() {
		if (reportViewerControl != null)
			reportViewerControl.setFocus();
	}

	private IToolBarManager tbManager;

	public void initToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		GridData gd = new GridData();
		toolBar.setLayoutData(gd);

		tbManager = new ToolBarManager(toolBar);

		refreshToolbar();

		toolBar.update();
	}

	protected void refreshToolbar() {
		tbManager.removeAll();
		IFile file = ((IFileEditorInput) getEditorInput()).getFile();

		ExportMenuAction exportMenu = new ExportMenuAction(reportViewer);
		IAction pdfAction = null;
		MenuManager mm = exportMenu.getMenuManager();
		mm.add(new ExportAsJasperReportsAction(reportViewer, file));
		mm.add(new Separator());

		mm.add(pdfAction = new ExportAsPdfAction(reportViewer, file));
		mm.add(new ExportAsHtmlAction(reportViewer, file));
		mm.add(new ExportAsXHtmlAction(reportViewer, file));
		mm.add(new Separator());

		mm.add(new ExportAsRtfAction(reportViewer, file));
		mm.add(new ExportAsDocxAction(reportViewer, file));
		mm.add(new ExportAsOdtAction(reportViewer, file));
		mm.add(new ExportAsOdsAction(reportViewer, file));
		mm.add(new ExportAsPptxAction(reportViewer, file));
		mm.add(new ExportAsTextAction(reportViewer, file));

		mm.add(new Separator());
		mm.add(new ExportAsXlsAction(reportViewer, file));
		mm.add(new ExportAsXlsxAction(reportViewer, file));
		mm.add(new ExportAsExcelAPIAction(reportViewer, file));

		mm.add(new ExportAsCsvAction(reportViewer, file));
		mm.add(new ExportAsCsvMetadataAction(reportViewer, file));

		mm.add(new Separator());
		mm.add(new ExportAsXmlAction(reportViewer, file));
		mm.add(new ExportAsXmlWithImagesAction(reportViewer, file));
		exportMenu.setDefaultAction(pdfAction);

		tbManager.add(exportMenu);

		// tbManager.add(new PrintAction(reportViewer));
		tbManager.add(new GroupMarker("DATASOURCEGROUP")); //$NON-NLS-1$

		tbManager.add(new Separator());
		tbManager.add(new FirstPageAction(reportViewer));
		tbManager.add(new PreviousPageAction(reportViewer));
		if (SWT.getPlatform().equals("win32")) //$NON-NLS-1$
			tbManager.add(new PageNumberContributionItem(reportViewer));
		tbManager.add(new NextPageAction(reportViewer));
		tbManager.add(new LastPageAction(reportViewer));
		tbManager.add(new Separator());
		tbManager.add(new ZoomActualSizeAction(reportViewer));
		tbManager.add(new ZoomFitPageAction(reportViewer));
		tbManager.add(new ZoomFitPageWidthAction(reportViewer));
		tbManager.add(new Separator());
		ZoomOutAction zoomOutAction = new ZoomOutAction(reportViewer);
		zoomOutAction.setAccelerator(SWT.CTRL + '-');
		zoomOutAction.setImageDescriptor(InternalImages.DESC_ZOOM_OUT);
		tbManager.add(zoomOutAction);
		tbManager.add(new ZoomComboContributionItem(reportViewer));
		ZoomInAction zoomInAction = new ZoomInAction(reportViewer);
		zoomInAction.setImageDescriptor(InternalImages.DESC_ZOOM_IN);
		zoomInAction.setAccelerator(SWT.CTRL + '+');
		tbManager.add(zoomInAction);

		tbManager.update(true);
	}

	private JasperPrint jasperPrint;
	private boolean notRunning = true;

	public boolean isNotRunning() {
		return notRunning;
	}

	public void setNotRunning(boolean norun) {
		this.notRunning = norun;
		if (tbManager != null)
			tbManager.update(true);
	}

	protected void unsetReportDocument(final String msg, final boolean noRun) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				getReportViewer().unsetDocument(msg);
				setNotRunning(noRun);
				System.out.println(msg);
			}
		});
	}

	protected void setReportDocument(final boolean noRun) {
		if (getJasperPrint() != null) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					JasperPrint jrprint = getJasperPrint();
					if (jrprint != null) {
						if (jrprint.getPages().isEmpty()) {
							getReportViewer().unsetDocument("Document is empty.");
						} else
							try {
								getReportViewer().setDocument(jrprint);

								// open document in the prefered editor

							} catch (Exception e) {
								unsetReportDocument(ErrorUtil.getStackTrace(e), true);
							}
					}
					setNotRunning(noRun);
				}
			});
		}
	}

}
