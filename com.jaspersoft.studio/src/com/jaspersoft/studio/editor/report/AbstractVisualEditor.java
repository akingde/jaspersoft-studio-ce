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

import java.util.List;

import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.CopyTemplateAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.gef.ui.actions.MatchWidthAction;
import org.eclipse.gef.ui.actions.ToggleRulerVisibilityAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.IGraphicalEditor;
import com.jaspersoft.studio.editor.action.ShowPropertyViewAction;
import com.jaspersoft.studio.editor.action.align.Align2BorderAction;
import com.jaspersoft.studio.editor.action.copy.CopyAction;
import com.jaspersoft.studio.editor.action.copy.CutAction;
import com.jaspersoft.studio.editor.action.copy.PasteAction;
import com.jaspersoft.studio.editor.action.order.BringBackwardAction;
import com.jaspersoft.studio.editor.action.order.BringForwardAction;
import com.jaspersoft.studio.editor.action.order.BringToBackAction;
import com.jaspersoft.studio.editor.action.order.BringToFrontAction;
import com.jaspersoft.studio.editor.action.size.MatchSizeAction;
import com.jaspersoft.studio.editor.action.size.Size2BorderAction;
import com.jaspersoft.studio.editor.action.snap.ShowGridAction;
import com.jaspersoft.studio.editor.action.snap.SizeGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGuidesAction;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerComposite;
import com.jaspersoft.studio.editor.java2d.J2DGraphicalEditorWithFlyoutPalette;
import com.jaspersoft.studio.editor.menu.AppContextMenuProvider;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.editor.palette.JDPaletteFactory;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.preferences.PreferenceConstants;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * The Class AbstractVisualEditor.
 * 
 * @author Chicu Veaceslav
 */
public abstract class AbstractVisualEditor extends J2DGraphicalEditorWithFlyoutPalette implements IAdaptable,
		IGraphicalEditor {
	private Image partImage = JaspersoftStudioPlugin.getImage(MReport.getIconDescriptor().getIcon16());

	public Image getPartImage() {
		return partImage;
	}

	/**
	 * Instantiates a new abstract visual editor.
	 */
	public AbstractVisualEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	public DefaultEditDomain getEditDomain() {
		return super.getEditDomain();
	}

	public void setPartImage(Image partImage) {
		this.partImage = partImage;
	}

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
		// if (model != null)
		getGraphicalViewer().setContents(model);
		if (outlinePage != null)
			outlinePage.setContents(model);
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

	/** The ruler comp. */
	private JDRulerComposite rulerComp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.java2d.J2DGraphicalEditorWithFlyoutPalette#createGraphicalViewer(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected void createGraphicalViewer(Composite parent) {
		rulerComp = new JDRulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComp);
		rulerComp.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
	 */
	@Override
	protected PaletteRoot getPaletteRoot() {
		return JDPaletteFactory.createPalette(getIgnorePalleteElements());
	}

	protected abstract List<String> getIgnorePalleteElements();

	// FIXME: something wrong, I should not do that, order in initialisation is
	// wrong

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getGraphicalControl()
	 */
	@Override
	protected Control getGraphicalControl() {
		if (rulerComp != null)
			return rulerComp;
		return super.getGraphicalControl();
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
			sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0),
					getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
		}
		return sharedKeyHandler;
	}

	/**
	 * Creates the additional actions.
	 */
	protected void createAdditionalActions() {
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		// Show Grid Action
		IPreferenceStore prefs = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		Boolean isGridVisible = prefs.getBoolean(PreferenceConstants.P_PAGE_RULERGRID_SHOWGRID);
		Boolean isSnapToGuides = prefs.getBoolean(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGUIDES);
		Boolean isSnapToGrid = prefs.getBoolean(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGRID);
		Boolean isSnapToGeometry = prefs.getBoolean(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGEOMETRY);

		int gspaceX = prefs.getInt(PreferenceConstants.P_PAGE_RULERGRID_GRIDSPACEX);
		int gspaceY = prefs.getInt(PreferenceConstants.P_PAGE_RULERGRID_GRIDSPACEY);

		graphicalViewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, isSnapToGrid.booleanValue());
		graphicalViewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, isGridVisible.booleanValue());
		graphicalViewer.setProperty(SnapToGrid.PROPERTY_GRID_ORIGIN, new Point(30, 30));
		graphicalViewer.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(gspaceX, gspaceY));
		graphicalViewer.setProperty(SnapToGuidesAction.ID, isSnapToGuides);
		graphicalViewer.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, isSnapToGeometry.booleanValue());

		IAction showGrid = new ShowGridAction(graphicalViewer);
		getActionRegistry().registerAction(showGrid);

		SnapToGridAction snapGridAction = new SnapToGridAction(graphicalViewer);
		getActionRegistry().registerAction(snapGridAction);

		SizeGridAction sizeGridAction = new SizeGridAction(graphicalViewer);
		getActionRegistry().registerAction(sizeGridAction);

		// snap to geometry
		IAction snapAction = new ToggleSnapToGeometryAction(graphicalViewer);
		getActionRegistry().registerAction(snapAction);

		snapAction = new SnapToGuidesAction(graphicalViewer);
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

		IFile file = ((IFileEditorInput) getEditorInput()).getFile();

		SimpleFileResolver fileResolver = SelectionHelper.getFileResolver(file);

		graphicalViewer.setProperty("FILERESOLVER", fileResolver);
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
	protected JDReportOutlineView outlinePage;
	private EditorContributor editorContributor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class)
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		if (type == IContentOutlinePage.class) {
			return getOutlineView();
		}
		if (type == EditorContributor.class) {
			if (editorContributor == null)
				editorContributor = new EditorContributor(getEditDomain());
			return editorContributor;
		}
		return super.getAdapter(type);
	}

	protected JDReportOutlineView getOutlineView() {
		if (outlinePage == null) {
			TreeViewer viewer = new TreeViewer();
			outlinePage = new JDReportOutlineView(this, viewer);
		}
		return outlinePage;
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
		super.initializeGraphicalViewer();
		initializeEditor();
	}

	protected void initializeEditor() {
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.addDropTargetListener(new TemplateTransferDropTargetListener(graphicalViewer) {
			@Override
			protected CreationFactory getFactory(Object template) {
				return new JDPaletteCreationFactory(template);
			}

		});
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
		createEditorActions(registry);

		// ------------
		action = new DirectEditAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------
		action = new BringForwardAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new BringToFrontAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new BringToBackAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new BringBackwardAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------
		action = new AlignmentAction(this, PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction(this, PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction(this, PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction(this, PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction(this, PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new AlignmentAction(this, PositionConstants.MIDDLE);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------
		action = new Align2BorderAction(this, PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.MIDDLE);
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

		// ---------------------

		action = new Size2BorderAction(this, Size2BorderAction.WIDTH);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Size2BorderAction(this, Size2BorderAction.HEIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new Size2BorderAction(this, Size2BorderAction.BOTH);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------------

		action = new ShowPropertyViewAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

	}

	protected void createEditorActions(ActionRegistry registry) {

	}
}
