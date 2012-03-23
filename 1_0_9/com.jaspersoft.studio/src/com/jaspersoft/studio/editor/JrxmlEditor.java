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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.eclipse.builder.JasperReportsBuilder;
import net.sf.jasperreports.eclipse.builder.JasperReportsNature;
import net.sf.jasperreports.eclipse.util.ClassLoaderUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.viewers.ISelection;
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
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.editor.outline.page.EmptyOutlinePage;
import com.jaspersoft.studio.editor.outline.page.MultiOutlineView;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.editor.xml.XMLEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.plugin.AContributorAction;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.jasper.ProxyFileResolver;

/*
 * An example showing how to create a multi-page editor. This example has 3 pages: <ul> <li>page 0 contains a nested
 * text editor. <li>page 1 allows you to change the font used in page 2 <li>page 2 shows the words in page 0 in sorted
 * order </ul>
 */
public class JrxmlEditor extends MultiPageEditorPart implements IResourceChangeListener, IGotoMarker, IJROBjectEditor {

	public static final String DEFAULT_PROJECT = "MyReports";

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
	private ModelPropertyChangeListener modelPropChangeListener = new ModelPropertyChangeListener();
	private JasperReportsConfiguration jrContext;

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
			reportContainer = new ReportContainer(this, jrContext);

			reportContainer.addPageChangedListener(new IPageChangedListener() {

				public void pageChanged(PageChangedEvent event) {
					updateContentOutline(PAGE_DESIGNER);
				}
			});
			reportContainer.getPropertyChangeSupport().addPropertyChangeListener(modelPropChangeListener);

			int index = addPage(reportContainer, getEditorInput());
			setPageText(index, Messages.JrxmlEditor_design);
		} catch (PartInitException e) {
			UIUtils.showError(new Exception(Messages.common_error_creating_nested_visual_editor));
		}
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
							xmlFresh = false;
						}

						public void documentAboutToBeChanged(DocumentEvent event) {

						}
					});
		} catch (PartInitException e) {
			UIUtils.showError(new Exception(Messages.common_error_creating_nested_visual_editor));
		}
	}

	/**
	 * Creates page 2 of the multi-page editor, which shows the sorted text.
	 */
	void createPage2() {
		previewEditor = new PreviewContainer(false, jrContext) {
			@Override
			public void runReport(com.jaspersoft.studio.data.DataAdapterDescriptor myDataAdapterDesc) {
				if (myDataAdapterDesc != null) {
					JasperDesign jasperDesign = getMReport().getJasperDesign();
					String oldp = jasperDesign.getProperty(MReport.DEFAULT_DATAADAPTER);
					if (oldp == null || (oldp != null && !oldp.equals(myDataAdapterDesc.getName()))) {
						getMReport().putParameter(MReport.DEFAULT_DATAADAPTER, myDataAdapterDesc);
						jasperDesign.setProperty(MReport.DEFAULT_DATAADAPTER, myDataAdapterDesc.getName());
						modelPropChangeListener.propertyChange(new PropertyChangeEvent(jasperDesign, "xzzdataset", null, oldp));
						isDirty = true;
					}
				}
				super.runReport(myDataAdapterDesc);
			};
		};
		try {
			int index = addPage(previewEditor, getEditorInput());
			setPageText(index, Messages.JrxmlEditor_preview);
		} catch (PartInitException e) {
			UIUtils.showError(new Exception(Messages.common_error_creating_nested_visual_editor));
		}
	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	@Override
	protected void createPages() {
		// CTabFolder ctfolder = (CTabFolder) getContainer();
		// ctfolder.setUnselectedCloseVisible(true);
		//
		// ToolBar toolBar = new ToolBar(ctfolder, SWT.FLAT | SWT.HORIZONTAL | SWT.WRAP | SWT.RIGHT);
		// ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		// item.setText("+ ");
		// item = new ToolItem(toolBar, SWT.PUSH);
		// item.setText("- ");
		// item = new ToolItem(toolBar, SWT.PUSH);
		// item.setText(">> ");
		//
		// int tabHeight = toolBar.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		// tabHeight = Math.max(tabHeight, ctfolder.getTabHeight());
		// ctfolder.setTabHeight(tabHeight);
		//
		// ctfolder.setTopRight(toolBar);

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

		outline = (IContentOutlinePage) getEditor(page).getAdapter(IContentOutlinePage.class);
		if (outline == null)
			outline = new EmptyOutlinePage();

		outlinePage.setPageActive(outline);

	}

	/**
	 * The <code>MultiPageEditorPart</code> implementation of this <code>IWorkbenchPart</code> method disposes all nested
	 * editors. Subclasses may extend.
	 */
	@Override
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
	@Override
	public void doSave(IProgressMonitor monitor) {
		IFile resource = ((IFileEditorInput) getEditorInput()).getFile();
		if ((!xmlEditor.isDirty() && reportContainer.isDirty()) || getActiveEditor() != xmlEditor || !modelFresh) {
			version = JRXmlWriterHelper.getVersion(resource, p, true);
			model2xml(version);
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
				e.printStackTrace();
			}
		}
		JaspersoftStudioPlugin.getExtensionManager().onSave(jrContext);

		xmlEditor.doSave(monitor);
		reportContainer.doSave(monitor);
		previewEditor.doSave(monitor);
		firePropertyChange(PROP_DIRTY);
	}

	/**
	 * Saves the multi-page editor's document as another file. Also updates the text for page 0's tab, and updates this
	 * multi-page editor's input to correspond to the nested editor's.
	 */
	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.setOriginalFile(((FileEditorInput) getEditorInput()).getFile());
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
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		// FIXME: THIS IS NOT THE RIGHT PLACE TO LOAD MODEL, WE SHOULD LOAD FROM
		// TEXT EDITOR TO AVOID 2 TIME READING THE FILE
		if (editorInput instanceof FileStoreEditorInput) {
			try {
				FileStoreEditorInput fsei = (FileStoreEditorInput) editorInput;
				IPath location = new Path(fsei.getURI().getPath());
				// Create a new temporary project object and open it.
				IProject project = null;
				for (IProject prj : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
					if (prj.isOpen()) {
						if (project == null)
							project = prj;
						else if (prj.getNature(JasperReportsNature.NATURE_ID) != null)
							project = prj;
					}
				}
				if (project == null)
					project = ResourcesPlugin.getWorkspace().getRoot().getProject(DEFAULT_PROJECT);
				// Create a project if one doesn't exist and open it.
				if (!project.exists())
					project.create(null);
				if (!project.isOpen())
					project.open(null);

				IFile file = project.getFile(location.lastSegment());
				file.createLink(location, IResource.REPLACE, null);

				editorInput = new FileEditorInput(file);
			} catch (CoreException e) {
				throw new PartInitException(e.getMessage(), e);
			}
		}
		super.init(site, editorInput);
		setPartName(editorInput.getName());
		InputStream in = null;
		IFile file = null;
		try {
			if (editorInput instanceof IFileEditorInput) {
				file = ((IFileEditorInput) editorInput).getFile();
				in = file.getContents();
			} else
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
			p = new PropertiesHelper(file.getProject());

			getJrContext(file);

			in = getXML(editorInput, file.getCharset(true), in, version);
			JasperDesign jd = JRXmlLoader.load(in);
			JaspersoftStudioPlugin.getExtensionManager().onLoad(jd, this);
			editorActions = JaspersoftStudioPlugin.getExtensionManager().getActions();
			for (AContributorAction a : editorActions) {
				a.setJrConfig(jrContext);
				((JrxmlEditorContributor) getEditorSite().getActionBarContributor()).addGlobaRetargetAction(a);
			}
			jrContext.setJasperDesign(jd);
			setModel(ReportFactory.createReport(jrContext));
		} catch (JRException e) {
			setModel(null);
			handleJRException(editorInput, e, false);
		} catch (CoreException e) {
			setModel(null);
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
		if (jrContext == null) {
			jrContext = new JasperReportsConfiguration();
			ProxyFileResolver resolver = new ProxyFileResolver();
			resolver.addResolver(SelectionHelper.getFileResolver(file));
			jrContext.setFileResolver(resolver);
			jrContext.setClassLoader(ClassLoaderUtil.getClassLoader4Project(null, file.getProject()));
		}
		jrContext.put(IEditorContributor.KEY_FILE, file);
		jrContext.put(PropertiesHelper.JRCONTEXT_PREFERENCE_HELPER_KEY, p);
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

	public static InputStream getXML(IEditorInput editorInput, String encoding, InputStream in, String version)
			throws JRException {
		String fileExtension = getFileExtension(editorInput);
		if (fileExtension.equals("jasper")) { //$NON-NLS-1$
			JasperReport report = (JasperReport) JRLoader.loadObject(in);
			// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			String str;
			try {
				str = JRXmlWriterHelper.writeReport(report, JRXmlWriterHelper.fixencoding(encoding), version);
				return new ByteArrayInputStream(str.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					UIUtils.showError(e);
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

			Display.getDefault().asyncExec(new Runnable() {
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
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	/** The model fresh. */
	private boolean modelFresh = true;

	/** The xml fresh. */
	private boolean xmlFresh = true;

	private PreviewContainer previewEditor;

	/**
	 * Calculates the contents of page 2 when the it is activated.
	 * 
	 * @param newPageIndex
	 *          the new page index
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		if (newPageIndex == PAGE_DESIGNER || newPageIndex == PAGE_XMLEDITOR || newPageIndex == PAGE_PREVIEW) {
			if (activePage == PAGE_DESIGNER) {
				tmpselection = reportContainer.getActiveEditor().getSite().getSelectionProvider().getSelection();
			}
			switch (newPageIndex) {
			case PAGE_DESIGNER:
				if (activePage == PAGE_XMLEDITOR && !xmlFresh) {
					try {
						xml2model();
					} catch (JRException e) {
						handleJRException(getEditorInput(), e, false);
					}
				}
				if (activePage != PAGE_PREVIEW) {
					updateVisualView();
					modelFresh = true;
				}
				reportContainer.getActiveEditor().getSite().getSelectionProvider().setSelection(tmpselection);
				break;
			case PAGE_XMLEDITOR:
				if (!modelFresh)
					model2xml();
				break;
			case PAGE_PREVIEW:
				if (activePage == PAGE_XMLEDITOR)
					try {
						xml2model();
					} catch (JRException e) {
						handleJRException(getEditorInput(), e, false);
					}
				else {
					if (!modelFresh)
						model2xml();
				}
				model2preview();
				break;
			}
		}
		getSite().getSelectionProvider().setSelection(null);
		super.pageChange(newPageIndex);
		updateContentOutline(getActivePage());
		activePage = newPageIndex;
	}

	private ISelection tmpselection;
	private int activePage = 0;

	private PropertiesHelper p;

	private String version = "last";

	private List<AContributorAction> editorActions;

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
		setModel(ReportFactory.createReport(jrContext));
		modelFresh = true;
	}

	private void model2xml() {
		model2xml("last");
	}

	/**
	 * Model2xml.
	 */
	private void model2xml(String version) {
		try {
			JasperDesign report = getJasperDesign();
			// save the last used dataadapter in the report
			MReport mReport = getMReport();
			if (mReport != null) {
				Object obj = mReport.getParameter(MReport.DEFAULT_DATAADAPTER);
				if (obj != null && obj instanceof DataAdapterDescriptor) {
					String dataAdapterDesc = previewEditor.getDataAdapterDesc().getName();
					report.removeProperty(MReport.DEFAULT_DATAADAPTER);
					report.setProperty(MReport.DEFAULT_DATAADAPTER, dataAdapterDesc);
				}
			}

			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			String xml = JRXmlWriterHelper.writeReport(report, JRXmlWriterHelper.fixencoding(file.getCharset(true)), version);
			IDocumentProvider dp = xmlEditor.getDocumentProvider();
			IDocument doc = dp.getDocument(xmlEditor.getEditorInput());
			doc.set(xml);
			xmlFresh = true;
		} catch (final Exception e) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					UIUtils.showError(e);
				}
			});
		}

	}

	protected JasperDesign getJasperDesign() {
		if (getModel() != null)
			return (JasperDesign) ((MRoot) getModel()).getValue();
		return null;
	}

	/**
	 * Model2xml.
	 */
	private void model2preview() {
		previewEditor.setJasperDesign(getJasperDesign());
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
			getMReport().getPropertyChangeSupport().removePropertyChangeListener(modelPropChangeListener);
		if (model != null && model.getChildren() != null && !model.getChildren().isEmpty())
			model.getChildren().get(0).getPropertyChangeSupport().addPropertyChangeListener(modelPropChangeListener);
		this.model = model;
		updateVisualView();
		if (jrContext != null)
			jrContext.setJasperDesign(getJasperDesign());
	}

	private MReport getMReport() {
		return (MReport) this.model.getChildren().get(0);
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

	public void openEditor(Object obj, ANode node) {
		reportContainer.openEditor(obj, node);
	}

}
