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
package com.jaspersoft.studio.editor.report;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.CopyTemplateAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.gef.ui.actions.MatchWidthAction;
import org.eclipse.gef.ui.actions.ToggleRulerVisibilityAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.IGraphicalEditor;
import com.jaspersoft.studio.editor.action.ShowPropertyViewAction;
import com.jaspersoft.studio.editor.action.align.Align2BorderAction;
import com.jaspersoft.studio.editor.action.align.Align2Element;
import com.jaspersoft.studio.editor.action.band.MaximizeContainerAction;
import com.jaspersoft.studio.editor.action.band.StretchToContentAction;
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
import com.jaspersoft.studio.editor.action.text.BoldAction;
import com.jaspersoft.studio.editor.action.text.ItalicAction;
import com.jaspersoft.studio.editor.action.text.StrikethroughAction;
import com.jaspersoft.studio.editor.action.text.UnderlineAction;
import com.jaspersoft.studio.editor.dnd.JSSTemplateTransferDropTargetListener;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerComposite;
import com.jaspersoft.studio.editor.gef.ui.actions.RZoomComboContributionItem;
import com.jaspersoft.studio.editor.gef.ui.actions.ViewSettingsDropDownAction;
import com.jaspersoft.studio.editor.java2d.J2DGraphicalEditorWithFlyoutPalette;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.menu.AppContextMenuProvider;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.palette.JDPaletteFactory;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class AbstractVisualEditor.
 * 
 * @author Chicu Veaceslav
 */
public abstract class AbstractVisualEditor extends J2DGraphicalEditorWithFlyoutPalette implements IAdaptable,
		IGraphicalEditor {
	private Image partImage = JaspersoftStudioPlugin.getInstance().getImage(MReport.getIconDescriptor().getIcon16());
	private FlyoutPreferences palettePreferences;
	private JasperReportsConfiguration jrContext;

	/**
	 * Class that extend a default domain and give the possibility to check if a keyboard key is held down
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	public static class KeyPressedEventDomain extends DefaultEditDomain {

		/**
		 * Map to keeping track if a key is held down
		 */
		private HashMap<Integer, Boolean> keyMap;

		/**
		 * 
		 * @param editorPart
		 */
		public KeyPressedEventDomain(IEditorPart editorPart) {
			super(editorPart);
			keyMap = new HashMap<Integer, Boolean>();
		}

		/**
		 * Register in the map that a key is pressed, marking it as hold down
		 */
		@Override
		public void keyDown(KeyEvent keyEvent, EditPartViewer viewer) {
			super.keyDown(keyEvent, viewer);
			keyMap.put(keyEvent.keyCode, true);
		}

		/**
		 * Register in the map that a key is released, marking it as not hold down
		 */
		@Override
		public void keyUp(KeyEvent keyEvent, EditPartViewer viewer) {
			super.keyDown(keyEvent, viewer);
			keyMap.put(keyEvent.keyCode, false);
		}

		/**
		 * Check if a key is held down or not
		 * 
		 * @param keyCode
		 *          an SWT keycode
		 * @return true if the key is held down, otherwise false
		 */
		public Boolean isPressed(Integer keyCode) {
			Boolean value = keyMap.get(keyCode);
			return value != null ? value : false;
		}
	}

	public Image getPartImage() {
		return partImage;
	}

	/**
	 * Instantiates a new abstract visual editor.
	 */
	public AbstractVisualEditor(JasperReportsConfiguration jrContext) {
		setEditDomain(new KeyPressedEventDomain(this));
		this.jrContext = jrContext;
	}

	@Override
	public DefaultEditDomain getEditDomain() {
		return super.getEditDomain();
	}

	@Override
	public void setEditDomain(DefaultEditDomain ed) {
		super.setEditDomain(ed);
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
		Boolean isGridVisible = prefs.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWGRID);
		Boolean isSnapToGuides = prefs.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGUIDES);
		Boolean isSnapToGrid = prefs.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGRID);
		Boolean isSnapToGeometry = prefs.getBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGEOMETRY);

		int gspaceX = prefs.getInt(RulersGridPreferencePage.P_PAGE_RULERGRID_GRIDSPACEX);
		int gspaceY = prefs.getInt(RulersGridPreferencePage.P_PAGE_RULERGRID_GRIDSPACEY);

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

		graphicalViewer.setProperty("JRCONTEXT", jrContext);

		LayoutManager.addActions(getActionRegistry(), this, getSelectionActions());

		JaspersoftStudioPlugin.getDecoratorManager().registerActions(getActionRegistry(), getSelectionActions(),
				getGraphicalViewer(), this);
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
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
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
		// if (outlinePage == null) {
		TreeViewer viewer = new TreeViewer();
		outlinePage = new JDReportOutlineView(this, viewer);
		outlinePage.setContents(getModel());
		// }
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

	@Override
	protected FlyoutPreferences getPalettePreferences() {
		// We cache the palette preferences for the open editor
		// Default implementation returns a new FlyoutPreferences object
		// every time the getPalettePreferences method is invoked.
		if (palettePreferences == null) {
			palettePreferences = super.getPalettePreferences();
			// Palette always opened
			palettePreferences.setPaletteState(FlyoutPaletteComposite.STATE_PINNED_OPEN);
		}
		return palettePreferences;
	}

	protected void initializeEditor() {
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.addDropTargetListener(new JSSTemplateTransferDropTargetListener(graphicalViewer));
		getEditorSite().getActionBarContributor();
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
			@Override
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

			@Override
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
				// Uncomment these lines if you want to set as default a palette
				// with column layout and large icons.
				// // TODO: we should replace these default suggestions not using the GEF preference
				// // store explicitly. It would be better override the PaletteViewer creation in order
				// // to have a custom PaletteViewerPreferences (#viewer.getPaletteViewerPreferences()).
				// // This way we could store the preferences in our preference store (maybe the JaspersoftStudio plugin one).
				// // For now we'll stay with this solution avoiding the user to lose previous saved preferences
				// // regarding the palette.
				// InternalGEFPlugin.getDefault().getPreferenceStore().setDefault(
				// PaletteViewerPreferences.PREFERENCE_LAYOUT, PaletteViewerPreferences.LAYOUT_COLUMNS);
				// InternalGEFPlugin.getDefault().getPreferenceStore().setDefault(
				// PaletteViewerPreferences.PREFERENCE_COLUMNS_ICON_SIZE,true);
			}

			@Override
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
		IAction action = new CutAction(this);
		registry.registerAction(action);
		List<String> selectionActions = getSelectionActions();
		selectionActions.add(action.getId());

		action = new CopyAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PasteAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new MatchWidthAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new MatchHeightAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		// create actions
		createEditorActions(registry);

		// ------------
		action = new DirectEditAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		// ------------
		action = new BringForwardAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new BringToFrontAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new BringToBackAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new BringBackwardAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		// ------------
		action = new Align2Element(this.getSite().getPart(), PositionConstants.LEFT);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2Element(this.getSite().getPart(), PositionConstants.RIGHT);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2Element(this.getSite().getPart(), PositionConstants.TOP);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2Element(this.getSite().getPart(), PositionConstants.BOTTOM);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2Element(this.getSite().getPart(), PositionConstants.CENTER);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2Element(this.getSite().getPart(), PositionConstants.MIDDLE);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		// ------------
		action = new Align2BorderAction(this, PositionConstants.LEFT);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.RIGHT);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.TOP);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.BOTTOM);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.CENTER);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Align2BorderAction(this, PositionConstants.MIDDLE);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		// ---------------------

		action = new MatchWidthAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new MatchHeightAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new MatchSizeAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		// ---------------------

		action = new Size2BorderAction(this, Size2BorderAction.WIDTH);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Size2BorderAction(this, Size2BorderAction.HEIGHT);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new Size2BorderAction(this, Size2BorderAction.BOTH);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new MaximizeContainerAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new StretchToContentAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		// ------------------

		action = new ShowPropertyViewAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new BoldAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new ItalicAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new UnderlineAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new StrikethroughAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	protected void createEditorActions(ActionRegistry registry) {

	}

	/**
	 * Contributes items to the specified toolbar that is supposed to be put on the top right of the current visual editor
	 * (i.e: ReportEditor, CrosstabEditor, TableEditor, ListEditor).
	 * <p>
	 * 
	 * Default behavior contributes the following items:
	 * <ul>
	 * <li>Zoom In</li>
	 * <li>Zoom Out</li>
	 * <li>Zoom Combo</li>
	 * <li>Global "View" settings drop down menu</li>
	 * </ul>
	 * 
	 * Sub-classes may want to override this method to modify the toolbar.
	 * 
	 * @param toolbarManager
	 *          the toolbar manager to be enriched
	 */
	public void contributeItemsToEditorTopToolbar(IToolBarManager toolbarManager) {
		toolbarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		toolbarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		RZoomComboContributionItem zoomItem = new RZoomComboContributionItem(getEditorSite().getPage());
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		ZoomManager property = (ZoomManager) graphicalViewer.getProperty(ZoomManager.class.toString());
		if (property != null)
			zoomItem.setZoomManager(property);
		zoomItem.setEnabled(true);
		toolbarManager.add(zoomItem);
		toolbarManager.add(new Separator());
		// Global "View" menu items
		toolbarManager.add(new ViewSettingsDropDownAction(getActionRegistry()));
	}
}
