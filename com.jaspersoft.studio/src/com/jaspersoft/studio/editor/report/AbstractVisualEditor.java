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
package com.jaspersoft.studio.editor.report;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.CopyTemplateAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.gef.ui.actions.MatchWidthAction;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleRulerVisibilityAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.editor.action.ShowPropertyViewAction;
import com.jaspersoft.studio.editor.action.copy.CopyAction;
import com.jaspersoft.studio.editor.action.copy.CutAction;
import com.jaspersoft.studio.editor.action.copy.PasteAction;
import com.jaspersoft.studio.editor.action.order.BringBackwardAction;
import com.jaspersoft.studio.editor.action.order.BringForwardAction;
import com.jaspersoft.studio.editor.action.order.BringToBackAction;
import com.jaspersoft.studio.editor.action.order.BringToFrontAction;
import com.jaspersoft.studio.editor.action.size.MatchSizeAction;
import com.jaspersoft.studio.editor.dnd.TextTransferDropTargetListener;
import com.jaspersoft.studio.editor.java2d.J2DGraphicalEditorWithFlyoutPalette;
import com.jaspersoft.studio.editor.menu.AppContextMenuProvider;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateConditionalStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDatasetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DeleteGroupReportAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.INode;

/**
 * The Class AbstractVisualEditor.
 * 
 * @author Chicu Veaceslav
 */
public abstract class AbstractVisualEditor extends J2DGraphicalEditorWithFlyoutPalette implements IAdaptable {

	/**
	 * Instantiates a new abstract visual editor.
	 */
	public AbstractVisualEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getEditDomain()
	 */
	@Override
	public DefaultEditDomain getEditDomain() {
		return super.getEditDomain();
	}

	/** The model. */
	private INode model;

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *          the new model
	 */
	public void setModel(INode model) {
		this.model = model;
		// getGraphicalViewer().setRootEditPart(new MainDesignerRootEditPart());
		if (model != null)
			getGraphicalViewer().setContents(model);
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
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getActionRegistry()
	 */
	@Override
	public ActionRegistry getActionRegistry() {
		return super.getActionRegistry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getSelectionSynchronizer()
	 */
	@Override
	public SelectionSynchronizer getSelectionSynchronizer() {
		return super.getSelectionSynchronizer();
	}

	/**
	 * Gets the editor.
	 * 
	 * @return the editor
	 */
	public FigureCanvas getEditor() {
		return (FigureCanvas) getGraphicalViewer().getControl();
	}

	/** The shared key handler. */
	private KeyHandler sharedKeyHandler;

	/**
	 * Gets the common key handler.
	 * 
	 * @return the common key handler
	 */
	public KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();
			sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(
					GEFActionConstants.DIRECT_EDIT));
		}
		return sharedKeyHandler;
	}

	/**
	 * Creates the additional actions.
	 */
	protected void createAdditionalActions() {
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		// Show Grid Action
		graphicalViewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, true);
		graphicalViewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, true);
		IAction showGrid = new ToggleGridAction(graphicalViewer);
		getActionRegistry().registerAction(showGrid);
		// snap to grid
		IAction snapAction = new ToggleSnapToGeometryAction(graphicalViewer);
		getActionRegistry().registerAction(snapAction);
		// show rullers
		IAction showRulers = new ToggleRulerVisibilityAction(graphicalViewer);
		getActionRegistry().registerAction(showRulers);
		// zoom manager actions
		ZoomManager zoomManager = (ZoomManager) graphicalViewer.getProperty(ZoomManager.class.toString());

		getActionRegistry().registerAction(new ZoomInAction(zoomManager));
		getActionRegistry().registerAction(new ZoomOutAction(zoomManager));
		graphicalViewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);

		// set context menu
		graphicalViewer.setContextMenu(new AppContextMenuProvider(graphicalViewer, getActionRegistry()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		updateActions(getSelectionActions());
	}

	/** The outline page. */
	private JDReportOutlineView outlinePage;
	private EditorContributor editorContributor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class)
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		if (type == IContentOutlinePage.class) {

			TreeViewer viewer = new TreeViewer();
			outlinePage = new JDReportOutlineView(this, viewer);

			return outlinePage;
		}
		if (type == EditorContributor.class) {
			if (editorContributor == null)
				editorContributor = new EditorContributor(getEditDomain());
			return editorContributor;
		}

		return super.getAdapter(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		getEditDomain().getCommandStack().markSaveLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
	 */
	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		super.initializeGraphicalViewer();
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.addDropTargetListener(new TemplateTransferDropTargetListener(graphicalViewer) {
			@Override
			protected CreationFactory getFactory(Object template) {
				return new JDPaletteCreationFactory((Class<?>) template);
			}
		});
		graphicalViewer.addDropTargetListener((TransferDropTargetListener) new TextTransferDropTargetListener(
				graphicalViewer, TextTransfer.getInstance())

		);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getGraphicalViewer()
	 */
	@Override
	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPalettePage()
	 */
	@Override
	protected CustomPalettePage createPalettePage() {
		return new CustomPalettePage(getPaletteViewerProvider()) {
			public void init(IPageSite pageSite) {
				super.init(pageSite);
				IAction copy = getActionRegistry().getAction(ActionFactory.COPY.getId());
				pageSite.getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
	 */
	@Override
	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			private IMenuListener menuListener;

			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
			}

			protected void hookPaletteViewer(PaletteViewer viewer) {
				super.hookPaletteViewer(viewer);
				final CopyTemplateAction copy = new CopyTemplateAction(AbstractVisualEditor.this);
				if (copy != null) {
					viewer.addSelectionChangedListener(copy);
				}
				if (menuListener == null)
					menuListener = new IMenuListener() {
						public void menuAboutToShow(IMenuManager manager) {
							if (copy != null)
								manager.appendToGroup(GEFActionConstants.GROUP_COPY, copy);
						}
					};
				viewer.getContextMenu().addMenuListener(menuListener);
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new CutAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new CopyAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new PasteAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MatchWidthAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MatchHeightAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		// create actions
		action = new CreateFieldAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateFieldAction.ID);

		action = new CreateSortFieldAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateSortFieldAction.ID);

		action = new CreateVariableAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateVariableAction.ID);

		action = new CreateScriptletAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateScriptletAction.ID);

		action = new CreateParameterAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateParameterAction.ID);

		action = new CreateGroupAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateGroupAction.ID);

		action = new CreateDatasetAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateDatasetAction.ID);

		action = new CreateStyleAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateStyleAction.ID);

		action = new CreateConditionalStyleAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateConditionalStyleAction.ID);

		action = new CreateStyleTemplateAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateStyleTemplateAction.ID);

		action = new CreateBandAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateBandAction.ID);

		action = new DeleteGroupReportAction(this);
		registry.registerAction(action);
		getSelectionActions().add(DeleteGroupReportAction.ID);

		// ------------
		action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------
		action = new BringForwardAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new BringToFrontAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new BringToBackAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new BringBackwardAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.MIDDLE);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ---------------------

		action = new MatchWidthAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MatchHeightAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MatchSizeAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// -------------------
		action = new ShowPropertyViewAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

	}
}
