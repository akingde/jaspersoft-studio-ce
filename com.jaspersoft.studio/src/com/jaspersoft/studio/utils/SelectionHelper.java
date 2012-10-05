/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.eclipse.util.ClassLoaderUtil;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.util.StringInput;
import com.jaspersoft.studio.editor.util.StringStorage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;

public class SelectionHelper {

	public static EditPart getEditPart(JRDesignElement jrElement) {
		ANode node = getNode(jrElement);
		if (node != null && node.getFigureEditPart() != null) {
			return node.getFigureEditPart();
		}
		return null;
	}

	public static ANode getNode(JRDesignElement jrElement) {
		JrxmlEditor jrxmlEditor = (JrxmlEditor) getActiveJRXMLEditor();
		MRoot root = (MRoot) jrxmlEditor.getModel();
		ANode node = ((MReport) root.getChildren().get(0)).getNode(jrElement);
		return node;
	}

	public static IEditorPart getActiveJRXMLEditor() {
		IWorkbenchWindow activeWorkbenchWindow = JaspersoftStudioPlugin.getInstance().getWorkbench()
				.getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
			IEditorPart p = activeWorkbenchWindow.getActivePage().getActiveEditor();
			return p;
		}
		return null;
	}

	public static boolean isSelected(JRDesignElement jrElement) {
		EditPart ep = getEditPart(jrElement);
		if (ep != null) {
			ISelection sel = ep.getViewer().getSelection();
			if (sel instanceof StructuredSelection) {
				for (Object o : ((StructuredSelection) sel).toList()) {
					if (o instanceof EditPart && ((EditPart) o) == ep)
						return true;
				}
			}
		}
		return false;
	}

	public static void setSelection(JRDesignElement jrElement, boolean add) {
		EditPart ep = getEditPart(jrElement);
		if (ep != null) {
			ISelection sel = ep.getViewer().getSelection();
			List<Object> s = new ArrayList<Object>();
			s.add(ep);
			if (add) {
				if (sel instanceof StructuredSelection) {
					for (Object o : ((StructuredSelection) sel).toList()) {
						s.add(o);
					}
				}
			}
			ep.getViewer().select(ep);
			ep.getViewer().reveal(ep);
		}

	}

	public static final void openEditor(File file) {
		IFileStore fileStore = EFS.getLocalFileSystem().getStore(file.toURI());
		if (!fileStore.fetchInfo().isDirectory() && fileStore.fetchInfo().exists()) {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window == null)
				window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];

			IWorkbenchPage page = window.getActivePage();
			try {
				IDE.openEditorOnFileStore(page, fileStore);
			} catch (PartInitException e) {
				UIUtils.showError(e);
			}
		}
	}

	public static final void openEditor(String content, String name) {
		IStorage storage = new StringStorage(content);
		IStorageEditorInput input = new StringInput(storage);
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
		IWorkbenchPage page = window.getActivePage();
		try {
			page.openEditor(input, name);
		} catch (PartInitException e) {
			UIUtils.showError(e);
		}
	}

	public static final boolean openEditor(FileEditorInput editorInput, String path) {
		return openEditor(editorInput.getFile(), path);
	}

	public static final boolean openEditor(IFile file, String path) {
		try {
			if (file != null && path != null) {
				// String pathname = FileUtils.findRelativePath(rpath, path);
				FileResolver fileResolver = getFileResolver(file);

				File fileToBeOpened = fileResolver.resolveFile(path);

				if (file != null && fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
					IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToBeOpened.toURI());

					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

					IDE.openEditorOnFileStore(page, fileStore);
					return true;
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static FileResolver getFileResolver() {
		IEditorPart ep = getActiveJRXMLEditor();
		if (ep != null && ep.getEditorInput() instanceof IFileEditorInput) {
			IFileEditorInput fe = ((IFileEditorInput) ep.getEditorInput());
			return getFileResolver(fe.getFile());
		}
		SimpleFileResolver fileResolver = new SimpleFileResolver(Arrays.asList(new File[] { new File(".") })); //$NON-NLS-1$
		fileResolver.setResolveAbsolutePath(true);
		return fileResolver;
	}

	public static FileResolver getFileResolver(IFile file) {
		SimpleFileResolver fileResolver = null;
		if (file == null)
			fileResolver = new SimpleFileResolver(Arrays.asList(new File[] { new File("."), //$NON-NLS-1$
			}));
		else
			fileResolver = new SimpleFileResolver(Arrays.asList(new File[] { new File(file.getParent().getLocationURI()),
					new File("."), //$NON-NLS-1$
					new File(file.getProject().getLocationURI()) }));
		fileResolver.setResolveAbsolutePath(true);
		return fileResolver;
	}

	public static void setClassLoader(IFile file, IProgressMonitor monitor) {
		try {
			Thread.currentThread().setContextClassLoader(ClassLoaderUtil.getClassLoader4Project(monitor, file.getProject()));
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the currently selected {@link IJavaProject} instance, based upon the current opened JRXMLEditor window.
	 * 
	 * @return the {@link IJavaProject} if exists, <code>null</code> otherwise
	 */
	public static IJavaProject getJavaProjectFromCurrentJRXMLEditor() {
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if (activeJRXMLEditor != null && activeJRXMLEditor.getEditorInput() instanceof IFileEditorInput) {
			IProject prj = ((IFileEditorInput) activeJRXMLEditor.getEditorInput()).getFile().getProject();
			IJavaProject javaProj = JavaCore.create(prj);
			return javaProj;
		}
		return null;
	}
}
