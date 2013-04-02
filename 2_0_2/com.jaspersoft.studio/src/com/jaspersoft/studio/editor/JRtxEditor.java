/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;
import net.sf.jasperreports.engine.xml.JRXmlTemplateWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IElementStateListener;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.editor.outline.page.MultiOutlineView;
import com.jaspersoft.studio.editor.style.StyleTemplateEditor;
import com.jaspersoft.studio.editor.xml.XMLEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.style.MStylesTemplate;
import com.jaspersoft.studio.model.style.StyleTemplateFactory;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.jasper.ProxyFileResolver;

public class JRtxEditor extends MultiPageEditorPart implements IResourceChangeListener {
	private JasperReportsConfiguration jrContext;

	public JRtxEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.POST_CHANGE);
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
		ms.setJasperConfiguration(jrContext);
		StyleTemplateFactory.createTemplate(ms, new HashSet<String>(), true, file, file.getLocation().toFile(),
				(JRSimpleTemplate) jd);
		setModel(m);
	}

	private void model2xml() {
		try {
			if (model != null) {
				JRSimpleTemplate report = (JRSimpleTemplate) model.getChildren().get(0).getValue();
				IFile file = ((IFileEditorInput) getEditorInput()).getFile();
				String xml = JRXmlTemplateWriter.writeTemplate(report, JRXmlWriterHelper.fixencoding(file.getCharset(true)));
				xml = xml.replaceFirst("<jasperTemplate ", "<!-- Created with Jaspersoft Studio -->\n<jasperTemplate "); //$NON-NLS-1$ //$NON-NLS-2$
				IDocumentProvider dp = xmlEditor.getDocumentProvider();
				IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
				doc.set(xml);
			}
		} catch (final Exception e) {
			UIUtils.showError(e);
		}
	}

	/**
	 * Closes all project files on project close.
	 * 
	 * @param event
	 *          the event
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		switch (event.getType()) {
		case IResourceChangeEvent.PRE_CLOSE:
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
			break;
		case IResourceChangeEvent.PRE_DELETE:
			break;
		case IResourceChangeEvent.POST_CHANGE:
			try {
				DeltaVisitor visitor = new DeltaVisitor(this);
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

	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				IProgressMonitor monitor = getActiveEditor().getEditorSite().getActionBars().getStatusLineManager()
						.getProgressMonitor();
				try {
					file.create(new ByteArrayInputStream("FILE".getBytes("UTF-8")), true, monitor);
					IFileEditorInput modelFile = new FileEditorInput(file);
					setInputWithNotify(modelFile);
					xmlEditor.setInput(modelFile);
					setPartName(file.getName());

					doSave(monitor);
				} catch (CoreException e) {
					UIUtils.showError(e);
				} catch (UnsupportedEncodingException e) {
					UIUtils.showError(e);
				}
			}
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		input = FileUtils.checkAndConvertEditorInput(input);
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

			getJrContext(file);

			ANode m = new MRoot(null, jasperDesign);
			MStylesTemplate ms = new MStylesTemplate(m, file);
			ms.setJasperConfiguration(jrContext);
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

	public void addFileResolver(FileResolver resolver) {
		((ProxyFileResolver) jrContext.getFileResolver()).addResolver(resolver);
	}

	protected void getJrContext(IFile file) throws CoreException, JavaModelException {
		if (jrContext == null)
			jrContext = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), file);
	}

	@Override
	public void dispose() {
		if (jrContext != null)
			jrContext.dispose();
		super.dispose();
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
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getContainer(), "com.jaspersoft.studio.doc.editor_jrtx");

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
			Display.getDefault().asyncExec(new Runnable() {

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
			styleEditor = new StyleTemplateEditor(jrContext);

			int index = addPage(styleEditor, getEditorInput());
			setPageText(index, "Preview");
		} catch (PartInitException e) {
			UIUtils.showError(e);
		}
	}

	private class StateListener implements IElementStateListener {

		public void elementDirtyStateChanged(Object element, boolean isDirty) {

		}

		public void elementContentAboutToBeReplaced(Object element) {

		}

		public void elementContentReplaced(Object element) {

		}

		public void elementDeleted(Object element) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					getSite().getPage().closeEditor(JRtxEditor.this, false);
				}
			});
		}

		public void elementMoved(Object originalElement, Object movedElement) {

		}

	}

	/**
	 * Creates page 0 of the multi-page editor, which contains a text editor.
	 */
	void createPage1() {
		try {
			xmlEditor = new XMLEditor(jrContext);
			int index = addPage(xmlEditor, getEditorInput());
			setPageText(index, Messages.common_source);
			xmlEditor.getDocumentProvider().getDocument(xmlEditor.getEditorInput())
					.addDocumentListener(new IDocumentListener() {

						public void documentChanged(DocumentEvent event) {
						}

						public void documentAboutToBeChanged(DocumentEvent event) {

						}
					});
			xmlEditor.getDocumentProvider().addElementStateListener(new StateListener());
		} catch (PartInitException e) {
			UIUtils.showError(e);
		}
	}
}
