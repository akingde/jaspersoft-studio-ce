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
package com.jaspersoft.studio.editor.outline;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;

import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.menu.AppContextMenuProvider;
import com.jaspersoft.studio.editor.outline.actions.CreateBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateConditionalStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDatasetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DeleteGroupReportAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.EditorContributor;

// TODO: Auto-generated Javadoc
/**
 * The Class JDReportOutlineView.
 */
public class JDReportOutlineView extends ContentOutlinePage implements IAdaptable {

	/** The editor. */
	private AbstractVisualEditor editor;

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
	public JDReportOutlineView(AbstractVisualEditor editor, EditPartViewer viewer) {
		super(viewer);
		this.editor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
	 */
	public void init(IPageSite pageSite) {
		super.init(pageSite);
		ActionRegistry registry = editor.getActionRegistry();
		IActionBars bars = pageSite.getActionBars();
		String id = ActionFactory.UNDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.REDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = ActionFactory.DELETE.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = DeleteGroupReportAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateFieldAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateVariableAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateScriptletAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateParameterAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateGroupAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateDatasetAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateStyleAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateConditionalStyleAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateStyleTemplateAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		id = CreateBandAction.ID;
		bars.setGlobalActionHandler(id, registry.getAction(id));

		bars.updateActionBars();
	}

	/**
	 * Configure outline viewer.
	 */
	protected void configureOutlineViewer() {
		getViewer().setEditDomain(editor.getEditDomain());
		getViewer().setEditPartFactory(new OutlineTreeEditPartFactory());
		ContextMenuProvider provider = new AppContextMenuProvider(getViewer(), editor.getActionRegistry());
		getViewer().setContextMenu(provider);
		getSite().registerContextMenu("com.jaspersoft.studio.outline.contextmenu", //$NON-NLS-1$
				provider, getSite().getSelectionProvider());
		getViewer().addDropTargetListener((TransferDropTargetListener) new TemplateTransferDropTargetListener(getViewer()) {
			@Override
			protected CreationFactory getFactory(Object template) {
				return new JDPaletteCreationFactory((Class<?>) template);
			}
		});

		IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
		showOutlineAction = new Action() {
			public void run() {
				showPage(ID_OUTLINE);
			}
		};
		showOutlineAction
				.setImageDescriptor(ImageDescriptor.createFromFile(JDReportOutlineView.class, "icons/outline.gif")); //$NON-NLS-1$
		showOutlineAction.setToolTipText("Show outline");
		tbm.add(showOutlineAction);
		showOverviewAction = new Action() {
			public void run() {
				showPage(ID_OVERVIEW);
			}
		};
		showOverviewAction.setImageDescriptor(ImageDescriptor.createFromFile(JDReportOutlineView.class,
				"icons/overview.gif")); //$NON-NLS-1$
		showOverviewAction.setToolTipText("Show overview");
		tbm.add(showOverviewAction);
		showPage(ID_OUTLINE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.ContentOutlinePage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		pageBook = new PageBook(parent, SWT.NONE);
		outline = getViewer().createControl(pageBook);
		overview = new Canvas(pageBook, SWT.NONE);
		pageBook.showPage(outline);
		configureOutlineViewer();
		hookOutlineViewer();
		getViewer().setContents(editor.getModel());
		if (outline instanceof Tree) {
			((Tree) outline).getItem(0).setExpanded(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#dispose()
	 */
	public void dispose() {
		unhookOutlineViewer();
		if (thumbnail != null) {
			thumbnail = null;
		}
		super.dispose();
	}

	private EditorContributor editorContributor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class type) {
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
		LightweightSystem lws = new LightweightSystem(overview);
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
	}

	/**
	 * Sets the contents.
	 * 
	 * @param contents
	 *          the new contents
	 */
	public void setContents(Object contents) {
		getViewer().setContents(contents);
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
