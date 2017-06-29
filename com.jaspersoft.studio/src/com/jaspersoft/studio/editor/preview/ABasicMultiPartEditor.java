/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.jaspersoft.studio.editor.DeltaVisitor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ABasicMultiPartEditor extends MultiPageEditorPart {
	
	protected boolean listenResource;

	protected JasperReportsConfiguration jrContext;
	
	private IPartListener partListener;
	
	private ResourceTracker resourceListener;
	
	boolean closing = false;
	
	public ABasicMultiPartEditor(boolean listenResource) {
		this.listenResource = listenResource;
		if (listenResource) {
			partListener = new ResourcePartListener();
			resourceListener = new ResourceTracker();
		}
	}

	class ResourceTracker implements IResourceChangeListener {
		public void resourceChanged(final IResourceChangeEvent event) {
			switch (event.getType()) {
			case IResourceChangeEvent.PRE_CLOSE:
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
						for (int i = 0; i < pages.length; i++) {
							// if (((FileEditorInput) xmlEditor.getEditorInput()).getFile().getProject().equals(event.getResource()))
							// {
							// IEditorPart editorPart = pages[i].findEditor(xmlEditor.getEditorInput());
							// pages[i].closeEditor(editorPart, true);
							// }
						}
					}
				});
				break;
			case IResourceChangeEvent.PRE_DELETE:
				break;
			case IResourceChangeEvent.POST_CHANGE:
				try {
					DeltaVisitor visitor = new DeltaVisitor(ABasicMultiPartEditor.this);
					event.getDelta().accept(visitor);
				} catch (CoreException e) {
					UIUtils.showError(e);
				}
				break;
			case IResourceChangeEvent.PRE_BUILD:
			case IResourceChangeEvent.POST_BUILD:
				break;
			}
		}
	}

	class ResourcePartListener implements IPartListener {
		// If an open, unsaved file was deleted, query the user to either do a "Save As"
		// or close the editor.
		public void partActivated(IWorkbenchPart part) {
			if (part != ABasicMultiPartEditor.this)
				return;
			if (!((IFileEditorInput) getEditorInput()).getFile().exists())
				getSite().getPage().closeEditor(ABasicMultiPartEditor.this, false);
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partClosed(IWorkbenchPart part) {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			if (!file.exists()) {
				try {
					file.delete(true, new NullProgressMonitor());
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}

		public void partDeactivated(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
		}
	};

	@Override
	public void dispose() {
		if (partListener != null)
			getSite().getWorkbenchWindow().getPartService().removePartListener(partListener);
		partListener = null;
		if (resourceListener != null)
			((IFileEditorInput) getEditorInput()).getFile().getWorkspace().removeResourceChangeListener(resourceListener);
		disposeContext();
		super.dispose();
	}
	
	/**
	 * Method called to dispose the current context, can be overridden to provide
	 * a different behavior
	 */
	protected void disposeContext(){
		if (jrContext != null)
			jrContext.dispose();
	}
	
	/**
	 * If the jrContext was not set yet it create a new one basing on the 
	 * passed file. The jrContext created this way will be disposed at the end.
	 * It it safe to dispose a JRConfig created this way, since it is a not shared
	 * instance
	 */
	protected void initJRContext(IFile file) throws CoreException, JavaModelException {
		if (jrContext == null)
			jrContext = JasperReportsConfiguration.getDefaultJRConfig(file);
	}

	@Override
	protected void setInput(IEditorInput input) {
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
				file.getWorkspace().addResourceChangeListener(resourceListener,
						IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.POST_CHANGE);
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
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		IFile file = null;
		if (input instanceof FileStoreEditorInput) {
			input = FileUtils.checkAndConvertEditorInput(input, new NullProgressMonitor());
			init(site, input);
			return;
		} else if (input instanceof IFileEditorInput) {
			file = ((IFileEditorInput) input).getFile();
		}

		try {
			initJRContext(file);
			setSite(site);
			setPartName(input.getName());
			setInput(input);
		} catch (Exception e) {
			throw new PartInitException(e.getMessage(), e);
		}
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == JasperReportsContext.class)
			return jrContext;
		return super.getAdapter(adapter);
	}

	protected void closeEditor() {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null) {
			final IWorkbenchPage apage = activeWorkbenchWindow.getActivePage();
			if (apage != null)
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						closing = true;
						apage.closeEditor(ABasicMultiPartEditor.this, false);
					}
				});
		}
	}
}
