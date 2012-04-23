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

import net.sf.jasperreports.eclipse.builder.JasperReportsNature;
import net.sf.jasperreports.eclipse.util.ClassLoaderUtil;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;

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
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.widgets.Display;
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

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.jasper.ProxyFileResolver;

public abstract class ABasicEditor extends EditorPart {
	protected PropertiesHelper ph;
	protected boolean listenResource;

	public ABasicEditor(boolean listenResource) {
		this.listenResource = listenResource;
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
								getSite().getPage().closeEditor(ABasicEditor.this, false);
						}
					});
				} else { // else if it was moved or renamed
					final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getMovedToPath());
					display.asyncExec(new Runnable() {
						public void run() {
							setInput(new FileEditorInput(newFile));
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
			if (part != ABasicEditor.this)
				return;
			if (!((IFileEditorInput) getEditorInput()).getFile().exists())
				getSite().getPage().closeEditor(ABasicEditor.this, false);
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
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		IFile file = null;
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

				file = project.getFile(location.lastSegment());
				file.createLink(location, IResource.REPLACE, null);

				input = new FileEditorInput(file);
			} catch (CoreException e) {
				throw new PartInitException(e.getMessage(), e);
			}
			init(site, input);
			return;
		} else if (input instanceof IFileEditorInput) {
			file = ((IFileEditorInput) input).getFile();
		}

		try {
			getJrContext(file);
			setSite(site);
			setPartName(input.getName());
			setInput(input);
		} catch (Exception e) {
			throw new PartInitException(e.getMessage(), e);
		}
	}

	protected JasperReportsConfiguration jrContext;

	protected void getJrContext(IFile file) throws CoreException, JavaModelException {
		if (jrContext == null) {
			jrContext = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance());
			ProxyFileResolver resolver = new ProxyFileResolver();
			resolver.addResolver(SelectionHelper.getFileResolver(file));
			jrContext.setFileResolver(resolver);
			jrContext.setClassLoader(ClassLoaderUtil.getClassLoader4Project(null, file.getProject()));
		}
		jrContext.put(IEditorContributor.KEY_FILE, file);
		ph = PropertiesHelper.getInstance(jrContext);
		jrContext.put(PropertiesHelper.JRCONTEXT_PREFERENCE_HELPER_KEY, ph);
	}

	protected boolean isDirty = false;

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void doSaveAs() {
		isDirty = false;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		isDirty = false;
	}

	public PropertiesHelper getPropertiesHelper() {
		return ph;
	}
}
