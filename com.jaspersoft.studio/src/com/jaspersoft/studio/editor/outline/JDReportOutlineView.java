/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;

import com.jaspersoft.studio.editor.IGraphicalEditor;
import com.jaspersoft.studio.editor.dnd.JSSTemplateTransferDropTargetListener;
import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.editor.java2d.figure.ScrollableThumbnail;
import com.jaspersoft.studio.editor.java2d.figure.Thumbnail;
import com.jaspersoft.studio.editor.menu.AppContextMenuProvider;
import com.jaspersoft.studio.editor.outline.part.TreeEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

/*
 * The Class JDReportOutlineView.
 */
public class JDReportOutlineView extends ContentOutlinePage implements IAdaptable {

	/** The editor. */
	protected IGraphicalEditor editor;

	/** The page book. */
	private PageBook pageBook;

	/** The outline. */
	private Control outline;

	/** The overview. */
	private Canvas overview;

	/** The show overview action. */
	private IAction showOutlineAction, showOverviewAction;

	/** The Constant ID_OUTLINE. */
	static final int ID_OUTLINE = 0;

	/** The Constant ID_OVERVIEW. */
	static final int ID_OVERVIEW = 1;

	/** The thumbnail. */
	private Thumbnail thumbnail;

	/** The dispose listener. */
	private DisposeListener disposeListener;

	/**
	 * Instantiates a new jD report outline view.
	 * 
	 * @param editor
	 *          the editor
	 * @param viewer
	 *          the viewer
	 */
	public JDReportOutlineView(IGraphicalEditor editor, EditPartViewer viewer) {
		super(viewer);
		this.editor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
	 */
	@Override
	public void init(IPageSite pageSite) {
		super.init(pageSite);
		ActionRegistry registry = editor.getActionRegistry();
		IActionBars bars = pageSite.getActionBars();
		for (Iterator<IAction> it = registry.getActions(); it.hasNext();) {
			IAction ia = it.next();
			bars.setGlobalActionHandler(ia.getId(), ia);
		}

		bars.updateActionBars();
	}

	protected void initActions(ActionRegistry registry, IActionBars bars) {

	}

	protected ContextMenuProvider getMenuContentProvider() {
		ContextMenuProvider provider = new AppContextMenuProvider(getViewer(), editor.getActionRegistry());
		return provider;
	}

	/**
	 * Configure outline viewer.
	 */
	protected void configureOutlineViewer() {
		getViewer().setEditDomain(editor.getEditDomain());
		getViewer().setEditPartFactory(getEditPartFactory());
		ContextMenuProvider provider = getMenuContentProvider();
		getViewer().setContextMenu(provider);

		getViewer().addDropTargetListener(new JSSTemplateTransferDropTargetListener(getViewer()));
		getViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getViewer()) {
			@Override
			protected Object getTemplate() {
				List<Object> models = new ArrayList<Object>();
				Object obj = super.getTemplate();
				if (obj == null) {
					List<?> selection = getViewer().getSelectedEditParts();
					for (Object it : selection) {
						if (it instanceof EditPart) {
							Object model = ((EditPart) it).getModel();
							if (model instanceof IDragable) {
								models.add(model);
							}
						}
					}
				}
				return models;
			}
		});

		IPageSite site = getSite();
		site.registerContextMenu(provider.getId(), provider, site.getSelectionProvider());

		IToolBarManager tbm = site.getActionBars().getToolBarManager();
		showOutlineAction = new Action() {
			@Override
			public void run() {
				showPage(ID_OUTLINE);
			}
		};
		showOutlineAction
				.setImageDescriptor(ImageDescriptor.createFromFile(JDReportOutlineView.class, "icons/outline.gif")); //$NON-NLS-1$
		showOutlineAction.setToolTipText(Messages.JDReportOutlineView_show_outline_tool_tip);
		tbm.add(showOutlineAction);
		showOverviewAction = new Action() {
			@Override
			public void run() {
				showPage(ID_OVERVIEW);
			}
		};
		showOverviewAction.setImageDescriptor(ImageDescriptor.createFromFile(JDReportOutlineView.class,
				"icons/overview.gif")); //$NON-NLS-1$
		showOverviewAction.setToolTipText(Messages.JDReportOutlineView_show_overview_tool_tip);
		tbm.add(showOverviewAction);

		showPage(ID_OUTLINE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.ContentOutlinePage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		pageBook = new PageBook(parent, SWT.NONE);
		outline = getViewer().createControl(pageBook);
		overview = new Canvas(pageBook, SWT.NONE);
		pageBook.showPage(outline);
		configureOutlineViewer();
		hookOutlineViewer();
		setContents(editor.getModel());

		if (outline instanceof Tree) {
			Tree tree = (Tree) outline;
			tree.addMouseListener(new MouseListener() {

				public void mouseUp(MouseEvent e) {

				}

				public void mouseDown(MouseEvent e) {

				}

				public void mouseDoubleClick(MouseEvent e) {
					if (e.getSource() instanceof Tree) {
						Tree t = (Tree) e.getSource();
						TreeItem[] ti = t.getSelection();
						if (ti != null && ti.length > 0) {
							Object obj = ti[0].getData();
							if (obj instanceof TreeEditPart) {

								EditPart part = (EditPart) ((AbstractVisualEditor) editor).getGraphicalViewer().getEditPartRegistry()
										.get(((TreeEditPart) obj).getModel());
								if (part != null) {
									SelectionRequest request = new SelectionRequest();
									request.setType(RequestConstants.REQ_OPEN);
									part.performRequest(request);
								} else {
									TreeEditPart atep = (TreeEditPart) obj;
									if (atep.getModel() instanceof ANode) {
										EditableFigureEditPart.openEditor(((ANode) atep.getModel()).getValue(), (IEditorPart) editor,
												(ANode) atep.getModel());
									}
								}
							}
						}
					}
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#dispose()
	 */
	@Override
	public void dispose() {
		unhookOutlineViewer();
		if (thumbnail != null) {
			thumbnail = null;
		}
		super.dispose();
	}

	private EditorContributor editorContributor;

	private EditPartFactory editPartFactory;

	public EditPartFactory getEditPartFactory() {
		if (editPartFactory == null)
			editPartFactory = new OutlineTreeEditPartFactory();
		return editPartFactory;
	}

	public void setEditPartFactory(EditPartFactory editPartFactory) {
		this.editPartFactory = editPartFactory;
		getViewer().setEditPartFactory(getEditPartFactory());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		if (type == ZoomManager.class)
			return editor.getGraphicalViewer().getProperty(ZoomManager.class.toString());
		if (type == EditorContributor.class) {
			if (editorContributor == null)
				editorContributor = new EditorContributor(editor.getEditDomain());
			return editorContributor;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.ContentOutlinePage#getControl()
	 */
	@Override
	public Control getControl() {
		return pageBook;
	}

	/**
	 * Hook outline viewer.
	 */
	protected void hookOutlineViewer() {
		editor.getSelectionSynchronizer().addViewer(getViewer());
	}

	/**
	 * Initialize overview.
	 */
	protected void initializeOverview() {
		LightweightSystem lws = new J2DLightweightSystem(overview);
		RootEditPart rep = editor.getGraphicalViewer().getRootEditPart();
		if (rep instanceof MainDesignerRootEditPart) {
			ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) rep;
			thumbnail = new ScrollableThumbnail((Viewport) root.getFigure());
			thumbnail.setBorder(new MarginBorder(3));
			thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			editor.getEditor().addDisposeListener(disposeListener);
		}
		lws.setControl(overview);
	}

	/**
	 * Sets the contents.
	 * 
	 * @param contents
	 *          the new contents
	 */
	public void setContents(Object contents) {
		if (getViewer().getEditPartFactory() != null)
			getViewer().setContents(contents);
		if (outline instanceof Tree) {
			Tree tree = (Tree) outline;
			if (!tree.isDisposed() && tree.getItems() != null && tree.getItems().length > 0)
				tree.getItem(0).setExpanded(true);
		}

	}

	/**
	 * Show page.
	 * 
	 * @param id
	 *          the id
	 */
	protected void showPage(int id) {
		if (id == ID_OUTLINE) {
			showOutlineAction.setChecked(true);
			showOverviewAction.setChecked(false);
			pageBook.showPage(outline);
			if (thumbnail != null)
				thumbnail.setVisible(false);
		} else if (id == ID_OVERVIEW) {
			if (thumbnail == null)
				initializeOverview();
			showOutlineAction.setChecked(false);
			showOverviewAction.setChecked(true);
			pageBook.showPage(overview);
			thumbnail.setVisible(true);
		}
	}

	/**
	 * Unhook outline viewer.
	 */
	protected void unhookOutlineViewer() {
		editor.getSelectionSynchronizer().removeViewer(getViewer());
		FigureCanvas editor2 = editor.getEditor();
		if (disposeListener != null && editor2 != null && !editor2.isDisposed())
			editor2.removeDisposeListener(disposeListener);
	}
}
