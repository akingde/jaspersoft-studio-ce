/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.classpath.ClassLoaderUtil;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.util.FileResolver;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.IMultiEditor;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.util.StringInput;
import com.jaspersoft.studio.editor.util.StringStorage;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;

public class SelectionHelper {

	public static EditPart getEditPart(JRDesignElement jrElement) {
		ANode node = getNode(jrElement);
		
		if (node != null) {
			EditPart figure = node.getFigureEditPart();
			if (figure != null){
				return figure;
			}
		}
		return null;
	}

	public static ANode getNode(JRDesignElement jrElement) {
		JrxmlEditor jrxmlEditor = (JrxmlEditor) getActiveJRXMLEditor();
		MRoot root = (MRoot) jrxmlEditor.getModel();
		ANode node = ((MReport) root.getChildren().get(0)).getNode(jrElement);
		return node;
	}

	/**
	 * Extract from the current jrxml editor the background edit part and return it
	 * 
	 * @return a background edit part from the current editor if it was found, or null if the editor or the part are not
	 *         found
	 */
	public static EditPart getBackgroundEditPart() {
		JrxmlEditor jrxmlEditor = (JrxmlEditor) getActiveJRXMLEditor();
		if (jrxmlEditor != null) {
			MRoot root = (MRoot) jrxmlEditor.getModel();
			for (INode node : ((MReport) root.getChildren().get(0)).getChildren()) {
				if (node instanceof MBackgrounImage) {
					return ((MBackgrounImage) node).getFigureEditPart();
				}
			}
		}
		return null;
	}

	/**
	 * Return the root node of the actually opened editor or null if it is not available
	 */
	public static ANode getOpenedRoot() {
		IEditorPart editPart = getActiveJRXMLEditor();
		if (editPart instanceof IMultiEditor) {
			IMultiEditor editor = (IMultiEditor) editPart;
			if (editor != null)
				return (ANode) editor.getModel();
		}
		return null;
	}

	public static IEditorPart getActiveJRXMLEditor() {
		IWorkbenchWindow activeWorkbenchWindow = JaspersoftStudioPlugin.getInstance().getWorkbench()
				.getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
			IEditorPart p = activeWorkbenchWindow.getActivePage().getActiveEditor();
			if (p == null) {
				// look among the editor references
				IEditorReference[] editorReferences = activeWorkbenchWindow.getActivePage().getEditorReferences();
				if (editorReferences.length == 1) {
					IWorkbenchPart part = editorReferences[0].getPart(false);
					if (part instanceof IEditorPart) {
						p = (IEditorPart) part;
					}
				}
			}
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
			// The selection is set only if the refresh is enabled
			ANode mainNode = JSSCompoundCommand.getMainNode((ANode) ep.getModel());
			if (!JSSCompoundCommand.isRefreshEventsIgnored(mainNode)) {
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

	}

	/**
	 * Select the background edit part, if available, otherwise it dosen't nothing. The background must be visible
	 * otherwise it dosen't do nothing
	 */
	public static void setBackgroundSelected() {
		EditPart ep = getBackgroundEditPart();
		if (ep != null && ((GraphicalEditPart) ep).getFigure().isVisible()) {
			// The selection is set only if the refresh is enabled
			ep.getViewer().deselectAll();
			ep.getViewer().select(ep);
		}
	}

	/**
	 * Deselect the background edit part, if available, otherwise it dosen't nothing.
	 */
	public static void deselectBackground() {
		EditPart ep = getBackgroundEditPart();
		if (ep != null) {
			ep.getViewer().deselect(ep);
		}
	}

	/**
	 * Check if the background in the current editor is in edit mode
	 * 
	 * @return true if the background in the current editor is editable, false otherwise. If the background or the editor
	 *         are not found it return false
	 */
	public static boolean isBackgroundEditable() {
		IEditorPart currentEditor = getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor) {
			JrxmlEditor jrxmlEditor = (JrxmlEditor) currentEditor;
			return jrxmlEditor.getReportContainer().isBackgroundImageEditable();
		}
		return false;
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

	private static Map<String, IStorageEditorInput> streditors = new HashMap<String, IStorageEditorInput>();

	public static final void openEditor(String content, String name) {
		IStorageEditorInput input = streditors.get(content);
		if (input == null) {
			IStorage storage = new StringStorage(content);
			input = new StringInput(storage);
			streditors.put(content, input);
		}
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null)
			window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
		IWorkbenchPage page = window.getActivePage();
		try {
			IEditorReference[] er = page.findEditors(input, name, IWorkbenchPage.MATCH_INPUT);
			if (er != null)
				page.closeEditors(er, false);

			page.openEditor(input, name);
		} catch (PartInitException e) {
			UIUtils.showError(e);
		}
	}

	public static final boolean openEditor(FileEditorInput editorInput, String path) {
		return openEditor(editorInput.getFile(), path);
	}

	public static final boolean openEditor(IFile file) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, file);
		} catch (PartInitException e) {
			UIUtils.showError(e);
		}
		return true;
	}

	public static final void openEditorFile(final IFile file) {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				openEditor(file);
			}
		});
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
		URLFileResolver fileResolver = new URLFileResolver(Arrays.asList(new File[] { new File(".") })); //$NON-NLS-1$
		fileResolver.setResolveAbsolutePath(true);
		return fileResolver;
	}

	public static URLFileResolver getFileResolver(IFile file) {
		URLFileResolver fileResolver = null;
		if (file == null)
			fileResolver = new URLFileResolver(Arrays.asList(new File[] { new File("."), //$NON-NLS-1$
			}));
		else
			fileResolver = new URLFileResolver(Arrays.asList(new File[] { new File(file.getParent().getLocationURI()),
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

	public static boolean isSelected(ISelection selection, List<Class<?>> classes) {
		StructuredSelection sel = (StructuredSelection) selection;
		for (Iterator<?> it = sel.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			boolean iscompatible = false;
			for (Class<?> c : classes) {
				if (c.isAssignableFrom(obj.getClass())) {
					iscompatible = iscompatible || true;
					break;
				}
			}
			if (!iscompatible)
				return false;
		}
		return true;
	}

	public static boolean isMainEditorOpened() {
		IEditorPart activeJRXMLEditor = getActiveJRXMLEditor();
		if (activeJRXMLEditor instanceof JrxmlEditor) {
			return ((JrxmlEditor) activeJRXMLEditor).getReportContainer().getActivePage() == 0;
		}
		return false;
	}

	/**
	 * Checks if the current selection of the active workbench window is virtual resource.
	 * 
	 * @return <code>true</code> if selection is a virtual resource, <code>false</code> otherwise
	 * @see IResource#isVirtual()
	 */
	public static boolean isCurrentSelectionVirtualResource() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();
			if (firstElement instanceof IResource && ((IResource) firstElement).isVirtual()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return a list of all the editors currently opened. The editor must had been in foreground at least once during the
	 * session to consider it opened
	 * 
	 * @return a not null list of jrxml editor
	 */
	public static List<JrxmlEditor> getOpenedEditors() {
		List<JrxmlEditor> editors = new ArrayList<JrxmlEditor>();
		IWorkbenchWindow activeWorkbenchWindow = JaspersoftStudioPlugin.getInstance().getWorkbench()
				.getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
			IEditorReference[] editorRefs = activeWorkbenchWindow.getActivePage().getEditorReferences();
			for (IEditorReference editorRef : editorRefs) {
				IEditorPart editor = editorRef.getEditor(false);
				if (editor instanceof JrxmlEditor) {
					editors.add((JrxmlEditor) editor);
				}
			}
		}
		return editors;
	}

}
