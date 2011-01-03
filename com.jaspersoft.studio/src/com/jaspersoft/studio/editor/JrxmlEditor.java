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
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.sf.jasperreports.eclipse.builder.JasperReportsBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
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
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.xml.sax.SAXParseException;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.outline.page.EmptyOutlinePage;
import com.jaspersoft.studio.editor.outline.page.MultiOutlineView;
import com.jaspersoft.studio.editor.preview.PreviewEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.editor.xml.XMLEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ReportFactory;

/**
 * An example showing how to create a multi-page editor. This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class JrxmlEditor extends MultiPageEditorPart implements IResourceChangeListener, IGotoMarker, IJROBjectEditor {

	/**
	 * The listener interface for receiving modelPropertyChange events. The class that is interested in processing a
	 * modelPropertyChange event implements this interface, and the object created with that class is registered with a
	 * component using the component's <code>addModelPropertyChangeListener<code> method. When
	 * the modelPropertyChange event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see ModelPropertyChangeEvent
	 */
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
					modelFresh = false;
				}
			});

		}
	}

	/** The Constant PAGE_DESIGNER. */
	public static final int PAGE_DESIGNER = 0;

	/** The Constant PAGE_XMLEDITOR. */
	public static final int PAGE_XMLEDITOR = 1;

	/** The Constant PAGE_PREVIEW. */
	public static final int PAGE_PREVIEW = 2;

	/** The model. */
	private INode model = null;

	/** The text editor used in page 0. */
	private ReportContainer reportContainer;
	/** Xml editor used in page 1. */
	private XMLEditor xmlEditor;

	/** The model property change listener. */
	private ModelPropertyChangeListener modelPropertyChangeListener = new ModelPropertyChangeListener();

	/**
	 * Creates a multi-page editor example.
	 */
	public JrxmlEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	/**
	 * Creates page 1 of the multi-page editor, which allows you to change the font used in page 2.
	 */
	void createPage0() {
		try {
			reportContainer = new ReportContainer(this);

			reportContainer.addPageChangedListener(new IPageChangedListener() {

				public void pageChanged(PageChangedEvent event) {
					updateContentOutline(PAGE_DESIGNER);
				}
			});

			int index = addPage(reportContainer, getEditorInput());
			setPageText(index, Messages.JrxmlEditor_design);
		} catch (PartInitException e) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages.common_error_creating_nested_visual_editor, null,
					e.getStatus());
		}
	}

	/**
	 * Creates page 0 of the multi-page editor, which contains a text editor.
	 */
	void createPage1() {
		try {
			xmlEditor = new XMLEditor();
			int index = addPage(xmlEditor, getEditorInput());
			setPageText(index, Messages.JrxmlEditor_source);
			xmlEditor.getDocumentProvider().getDocument(xmlEditor.getEditorInput())
					.addDocumentListener(new IDocumentListener() {

						public void documentChanged(DocumentEvent event) {
							xmlFresh = false;
						}

						public void documentAboutToBeChanged(DocumentEvent event) {

						}
					});
		} catch (PartInitException e) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages.JrxmlEditor_error_creating_nested_text_editor, null,
					e.getStatus());
		}
	}

	/**
	 * Creates page 2 of the multi-page editor, which shows the sorted text.
	 */
	void createPage2() {
		previewEditor = new PreviewEditor();
		try {
			int index = addPage(previewEditor, getEditorInput());
			setPageText(index, Messages.JrxmlEditor_preview);
		} catch (PartInitException e) {
			ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages.common_error_creating_nested_visual_editor, null,
					e.getStatus());
		}

	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		createPage1();
		createPage2();
	}

	private MultiOutlineView outlinePage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
	 */
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
		if (page == PAGE_DESIGNER)
			outline = (IContentOutlinePage) reportContainer.getAdapter(IContentOutlinePage.class);
		outlinePage.setPageActive(outline);

	}

	/**
	 * The <code>MultiPageEditorPart</code> implementation of this <code>IWorkbenchPart</code> method disposes all nested
	 * editors. Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		setModel(null);
		super.dispose();
	}

	/**
	 * Saves the multi-page editor's document.
	 * 
	 * @param monitor
	 *          the monitor
	 */
	public void doSave(IProgressMonitor monitor) {
		IResource resource = ((IFileEditorInput) getEditorInput()).getFile();
		if ((!xmlEditor.isDirty() && reportContainer.isDirty()) || getActiveEditor() != xmlEditor) {
			model2xml();
		} else {
			try { // just go thru the model, to look what happend with our markers
				xml2model();
				resource.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			} catch (JRException e) {
				handleJRException(getEditorInput(), e, true);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		if (getFileExtension(getEditorInput()).equals("")) { //$NON-NLS-1$
			// save binary
			try {
				new JasperReportsBuilder().compileJRXML(resource, monitor);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		xmlEditor.doSave(monitor);
		reportContainer.doSave(monitor);
		firePropertyChange(PROP_DIRTY);
	}

	/**
	 * Saves the multi-page editor's document as another file. Also updates the text for page 0's tab, and updates this
	 * multi-page editor's input to correspond to the nested editor's.
	 */
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

	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method checks that the input is an instance of
	 * <code>IFileEditorInput</code>.
	 * 
	 * @param site
	 *          the site
	 * @param editorInput
	 *          the editor input
	 * @throws PartInitException
	 *           the part init exception
	 */
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		// FIXME: THIS IS NOT THE RIGHT PLACE TO LOAD MODEL, WE SHOULD LOAD FROM
		// TEXT EDITOR TO AVOID 2 TIME READING THE FILE
		super.init(site, editorInput);
		setPartName(editorInput.getName());
		InputStream in = null;
		try {
			if (editorInput instanceof FileStoreEditorInput) {
				in = new FileInputStream(((FileStoreEditorInput) editorInput).getURI().getPath());
			} else if (editorInput instanceof IFileEditorInput) {
				in = ((IFileEditorInput) editorInput).getFile().getContents();
			} else {
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
			}
			JasperDesign jd = null;
			in = JrxmlEditor.getXML(editorInput, "UTF-8", in); //$NON-NLS-1$
			jd = JRXmlLoader.load(in);
			setModel(ReportFactory.createReport(jd));
		} catch (JRException e) {
			setModel(null);
			handleJRException(editorInput, e, false);
		} catch (CoreException e) {
			setModel(null);
			throw new PartInitException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			setModel(null);
			throw new PartInitException("File not found", e); //$NON-NLS-1$
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

	public static String getFileExtension(IEditorInput editorInput) {
		String fileExtention = ""; //$NON-NLS-1$
		if (editorInput instanceof FileStoreEditorInput) {
			String path = ((FileStoreEditorInput) editorInput).getURI().getPath();
			fileExtention = path.substring(path.lastIndexOf(".") + 1, path.length()); //$NON-NLS-1$
		} else if (editorInput instanceof IFileEditorInput) {
			fileExtention = ((IFileEditorInput) editorInput).getFile().getFileExtension();
		}
		return fileExtention;
	}

	public static InputStream getXML(IEditorInput editorInput, String encoding, InputStream in) throws JRException {
		String fileExtension = getFileExtension(editorInput);
		if (fileExtension.equals("jasper")) { //$NON-NLS-1$
			JasperReport report = (JasperReport) JRLoader.loadObject(in);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRXmlWriter.writeReport(report, outputStream, encoding);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} else
			return in;
	}

	/**
	 * Handle jr exception.
	 * 
	 * @param editorInput
	 *          the editor input
	 * @param e
	 *          the e
	 * @param mute
	 *          the mute
	 */
	public void handleJRException(IEditorInput editorInput, final JRException e, boolean mute) {
		if (!mute) {
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					IStatus status = new OperationStatus(IStatus.ERROR, JaspersoftStudioPlugin.getUniqueIdentifier(), 1,
							"Your .jasper file contain errors, please fix them in xml editor.", e.getCause()); //$NON-NLS-1$
					ErrorDialog.openError(Display.getDefault().getActiveShell(), Messages.JrxmlEditor_error_loading_jrxml_to_model, null, status);
				}
			});
		}
		try {
			int lineNumber = 0;
			if (e.getCause() instanceof SAXParseException) {
				SAXParseException saxe = (SAXParseException) e.getCause();
				lineNumber = saxe.getLineNumber();
			}
			IResource resource = ((IFileEditorInput) editorInput).getFile();
			resource.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);

			final IMarker marker = resource.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.MESSAGE, e.getMessage());
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
			marker.setAttribute(IMarker.TRANSIENT, true);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.USER_EDITABLE, false);

			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					gotoMarker(marker);
					setActivePage(PAGE_XMLEDITOR);
				}
			});
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/** The model fresh. */
	private boolean modelFresh = true;

	/** The xml fresh. */
	private boolean xmlFresh = true;

	private PreviewEditor previewEditor;

	/**
	 * Calculates the contents of page 2 when the it is activated.
	 * 
	 * @param newPageIndex
	 *          the new page index
	 */
	protected void pageChange(int newPageIndex) {
		if (newPageIndex == PAGE_DESIGNER || newPageIndex == PAGE_XMLEDITOR || newPageIndex == PAGE_PREVIEW) {
			switch (newPageIndex) {
			case PAGE_DESIGNER:
				if (!xmlFresh) {
					try {
						xml2model();
						xmlFresh = true;
					} catch (JRException e) {
						handleJRException(getEditorInput(), e, false);
					}
				}
				updateVisualView();
				modelFresh = true;
				// getSite().setSelectionProvider(reportContainer.getActiveEditor().getSite().getSelectionProvider());
				break;
			case PAGE_XMLEDITOR:
				if (!modelFresh) {
					model2xml();
					xmlFresh = true;
				}
				// getSite().setSelectionProvider(xmlEditor.getSelectionProvider());
				break;
			case PAGE_PREVIEW:
				if (getActiveEditor() == xmlEditor)
					try {
						xml2model();
					} catch (JRException e) {
						handleJRException(getEditorInput(), e, false);
					}
				model2preview();
				// getSite().setSelectionProvider(previewEditor.getSite().getSelectionProvider());
				break;
			}
		}
		getSite().getSelectionProvider().setSelection(null);
		super.pageChange(newPageIndex);
		updateContentOutline(getActivePage());
	}

	/**
	 * Xml2model.
	 * 
	 * @throws JRException
	 *           the jR exception
	 */
	private void xml2model() throws JRException {
		IDocumentProvider dp = xmlEditor.getDocumentProvider();
		IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
		JasperDesign jd = JRXmlLoader.load(new ByteArrayInputStream(doc.get().getBytes()));
		setModel(ReportFactory.createReport(jd));
	}

	/**
	 * Model2xml.
	 */
	private void model2xml() {
		JasperDesign report = (JasperDesign) ((MRoot) getModel()).getValue();
		String xml = JasperCompileManager.writeReportToXml(report);
		xml = xml.replaceFirst("<jasperReport ", "<!-- Created with Jaspersoft Studio -->\n<jasperReport "); //$NON-NLS-1$ //$NON-NLS-2$
		IDocumentProvider dp = xmlEditor.getDocumentProvider();
		IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
		doc.set(xml);
	}

	/**
	 * Model2xml.
	 */
	private void model2preview() {
		previewEditor.setJasperDesign((JasperDesign) ((MRoot) getModel()).getValue());
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				previewEditor.runReport(null);
			}
		});
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

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *          the new model
	 */
	public void setModel(INode model) {
		if (this.model != null && this.model.getChildren() != null && !this.model.getChildren().isEmpty())
			this.model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelPropertyChangeListener);
		if (model != null && model.getChildren() != null && !model.getChildren().isEmpty())
			model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelPropertyChangeListener);
		this.model = model;
		updateVisualView();
	}

	/**
	 * Update visual view.
	 */
	public void updateVisualView() {
		if (reportContainer != null)
			reportContainer.setModel(getModel());
	}

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public INode getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ide.IGotoMarker
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(PAGE_XMLEDITOR);
		IDE.gotoMarker(xmlEditor, marker);
	}

	public void openEditor(Object obj) {
		reportContainer.openEditor(obj);
	}

}
