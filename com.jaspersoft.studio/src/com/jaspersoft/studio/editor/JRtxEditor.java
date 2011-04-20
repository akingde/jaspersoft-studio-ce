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

package com.jaspersoft.studio.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

import net.sf.jasperreports.eclipse.builder.JasperReportsNature;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;
import net.sf.jasperreports.engine.xml.JRXmlTemplateWriter;

import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.outline.page.MultiOutlineView;
import com.jaspersoft.studio.editor.style.StyleTemplateEditor;
import com.jaspersoft.studio.editor.xml.XMLEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.style.MStylesTemplate;
import com.jaspersoft.studio.model.style.StyleTemplateFactory;

public class JRtxEditor extends MultiPageEditorPart implements IResourceChangeListener {
	public JRtxEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	@Override
	protected void pageChange(int newPageIndex) {
		switch (newPageIndex) {
		case 0:
			xml2model();
			break;
		case 1:
			model2xml();
			break;
		}
		super.pageChange(newPageIndex);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		model2xml();
		styleEditor.doSave(monitor);
		xmlEditor.doSave(monitor);
		firePropertyChange(PROP_DIRTY);
	}

	private void xml2model() {
		IDocumentProvider dp = xmlEditor.getDocumentProvider();
		IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
		JRTemplate jd = JRXmlTemplateLoader.load(new ByteArrayInputStream(doc.get().getBytes()));
		ANode m = new MRoot(null, new JasperDesign());
		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		MStylesTemplate ms = new MStylesTemplate(m, file);
		ms.setValue(jd);
		StyleTemplateFactory.createTemplate(ms, new HashSet<String>(), true, file, file.getLocation().toFile(),
				(JRSimpleTemplate) jd);
		setModel(m);
	}

	private void model2xml() {
		try {
			if (model != null) {
				JRSimpleTemplate report = (JRSimpleTemplate) model.getChildren().get(0).getValue();
				String xml = JRXmlTemplateWriter.writeTemplate(report, ((IFileEditorInput) getEditorInput()).getFile()
						.getCharset(true));
				xml = xml.replaceFirst("<jasperTemplate ", "<!-- Created with Jaspersoft Studio -->\n<jasperTemplate "); //$NON-NLS-1$ //$NON-NLS-2$
				IDocumentProvider dp = xmlEditor.getDocumentProvider();
				IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
				doc.set(xml);
			}
		} catch (final Exception e) {
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					IStatus status = new OperationStatus(IStatus.ERROR, JaspersoftStudioPlugin.getUniqueIdentifier(), 1,
							"Error transforming model to xml.", e.getCause()); //$NON-NLS-1$
					ErrorDialog.openError(Display.getDefault().getActiveShell(),
							Messages.JrxmlEditor_error_loading_jrxml_to_model, null, status);
				}
			});
		}

	}

	/**
	 * Closes all project files on project close.
	 * 
	 * @param event
	 *          the event
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i < pages.length; i++) {
						if (((FileEditorInput) xmlEditor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(xmlEditor.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
					}
				}
			});
		}
		if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
			event.getDelta().getKind();
		}
	}

	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				IFileEditorInput modelFile = new FileEditorInput(file);
				setInputWithNotify(modelFile);
				xmlEditor.setInput(modelFile);
				setPartName(file.getName());
				IProgressMonitor progressMonitor = getActiveEditor().getEditorSite().getActionBars().getStatusLineManager()
						.getProgressMonitor();
				doSave(progressMonitor);
			}
		}
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
					ResourcesPlugin.getWorkspace().getRoot().getProject("JSSPROJECT");
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
		}
		super.init(site, input);
		setSite(site);
		setPartName(input.getName());
		setInput(input);

		InputStream in = null;
		try {
			IFile file = ((IFileEditorInput) input).getFile();
			if (input instanceof IFileEditorInput) {
				in = file.getContents();
			} else {
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
			}
			JRSimpleTemplate jd = null;
			jd = (JRSimpleTemplate) JRXmlTemplateLoader.load(in);
			JasperDesign jasperDesign = new JasperDesign();
			ANode m = new MRoot(null, jasperDesign);
			MStylesTemplate ms = new MStylesTemplate(m, file);
			ms.setValue(jd);
			StyleTemplateFactory.createTemplate(ms, new HashSet<String>(), true, file, file.getLocation().toFile(), jd);
			setModel(m);
		} catch (CoreException e) {
			e.printStackTrace();
			throw new PartInitException(e.getMessage(), e);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					setModel(null);
					throw new PartInitException("error closing input stream", e); //$NON-NLS-1$
				}
		}
	}

	/** The model. */
	private INode model = null;

	public void setModel(INode model) {
		if (this.model != null && this.model.getChildren() != null && !this.model.getChildren().isEmpty())
			this.model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelPropertyChangeListener);
		if (model != null && model.getChildren() != null && !model.getChildren().isEmpty())
			model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelPropertyChangeListener);
		this.model = model;
		if (styleEditor != null)
			styleEditor.setModel(model);
	}

	private ModelPropertyChangeListener modelPropertyChangeListener = new ModelPropertyChangeListener();

	private final class ModelPropertyChangeListener implements PropertyChangeListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			getSite().getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					firePropertyChange(ISaveablePart.PROP_DIRTY);
				}
			});

		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	protected void createPages() {
		createPage0();
		createPage1();
	}

	private XMLEditor xmlEditor;
	private StyleTemplateEditor styleEditor;
	private MultiOutlineView outlinePage;

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			outlinePage = new MultiOutlineView(this);
			Display.getCurrent().asyncExec(new Runnable() {

				public void run() {
					updateContentOutline(getActivePage());
				}
			});
			return outlinePage;
		}
		return super.getAdapter(type);
	}

	private void updateContentOutline(int page) {
		if (outlinePage == null)
			return;
		IContentOutlinePage outline = null;
		if (page == 0)
			outline = (IContentOutlinePage) styleEditor.getAdapter(IContentOutlinePage.class);
		outlinePage.setPageActive(outline);

	}

	/**
	 * Creates page 1 of the multi-page editor, which allows you to change the font used in page 2.
	 */
	void createPage0() {
		try {
			styleEditor = new StyleTemplateEditor();

			int index = addPage(styleEditor, getEditorInput());
			setPageText(index, "Preview");
		} catch (PartInitException e) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages.common_error_creating_nested_visual_editor,
					null, e.getStatus());
		}
		styleEditor.setModel(model);
	}

	/**
	 * Creates page 0 of the multi-page editor, which contains a text editor.
	 */
	void createPage1() {
		try {
			xmlEditor = new XMLEditor();
			int index = addPage(xmlEditor, getEditorInput());
			setPageText(index, Messages.common_source);
			xmlEditor.getDocumentProvider().getDocument(xmlEditor.getEditorInput())
					.addDocumentListener(new IDocumentListener() {

						public void documentChanged(DocumentEvent event) {
						}

						public void documentAboutToBeChanged(DocumentEvent event) {

						}
					});
		} catch (PartInitException e) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages.common_error_creating_nested_text_editor,
					null, e.getStatus());
		}
	}
}
