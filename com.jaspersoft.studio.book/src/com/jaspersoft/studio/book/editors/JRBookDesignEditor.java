/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISaveablePart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.book.dnd.ResourceTransferDropTargetListener;
import com.jaspersoft.studio.book.editors.actions.BookCompileAction;
import com.jaspersoft.studio.book.editors.actions.BookDatasetAction;
import com.jaspersoft.studio.book.editors.actions.CreateNewBookPartAction;
import com.jaspersoft.studio.book.editors.actions.CreateNewGroupAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookPartAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookSectionAction;
import com.jaspersoft.studio.book.editparts.BookSectionEditPart;
import com.jaspersoft.studio.book.model.MBookReport;
import com.jaspersoft.studio.editor.AGraphicEditor;
import com.jaspersoft.studio.editor.ZoomActualAction;
import com.jaspersoft.studio.editor.gef.parts.JSSGraphicalViewerKeyHandler;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.gef.ui.actions.RZoomComboContributionItem;
import com.jaspersoft.studio.editor.java2d.JSSScrollingGraphicalViewer;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterSetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultVariablesAction;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultsParametersAction;
import com.jaspersoft.studio.editor.outline.actions.SortParametersAction;
import com.jaspersoft.studio.editor.outline.actions.SortVariablesAction;
import com.jaspersoft.studio.editor.outline.actions.field.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.field.CreateFieldsContainerAction;
import com.jaspersoft.studio.editor.outline.actions.field.DeleteFieldsAllGroupAction;
import com.jaspersoft.studio.editor.outline.actions.field.DeleteFieldsGroupAction;
import com.jaspersoft.studio.editor.outline.actions.field.ShowFieldsTreeAction;
import com.jaspersoft.studio.editor.outline.actions.field.SortFieldsAction;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.repository.actions.Separator;
import com.jaspersoft.studio.swt.widgets.ResizableToolItem;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JRBookDesignEditor extends AGraphicEditor {

	/**
	 * Listener to model change, used to set the dirty flag on the report when
	 * something changes
	 */
	private PropertyChangeListener modelChangesListener;

	/**
	 * The extension manager for the plugin
	 */
	private ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
	
	/**
	 * The zoom toolbar control
	 */
	protected RZoomComboContributionItem zoomItem = null;

	/**
	 * The toolbar inside the book editor
	 */
	private ToolBar additionalToolbar;

	public JRBookDesignEditor(JasperReportsConfiguration jrContext) {
		super(jrContext);
		modelChangesListener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				getSite().getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						firePropertyChange(ISaveablePart.PROP_DIRTY);
					}
				});
			}
		};

	}

	@Override
	protected void createGraphicalViewer(Composite parent) {
		ScrollingGraphicalViewer viewer = new JSSScrollingGraphicalViewer();
		viewer.createControl(parent).setLayoutData(new GridData(GridData.FILL_BOTH));
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
	}

	/**
	 * Create the editor part, so the toolbar and graphical viewer
	 */
	@Override
	public void createPartControl(Composite parent) {
		GridLayout containerLayout = new GridLayout(1, false);
		containerLayout.verticalSpacing = 0;
		containerLayout.horizontalSpacing = 0;
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(containerLayout);

		// Create the toolbar
		createToolBar(container);

		// Create the viewer
		createGraphicalViewer(container);

		// Initialize the toolabar
		initializedToolBar();
	}

	/**
	 * Create the editor toolbar control
	 * 
	 * @param container
	 *            container where the toolbar is placed
	 */
	private void createToolBar(Composite container) {
		additionalToolbar = new ToolBar(container, SWT.HORIZONTAL | SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		GridData additionalToolbarGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
		additionalToolbar.setLayoutData(additionalToolbarGD);
		/*additionalToolbar.setLayout(new Layout() {
			
			@Override
			protected void layout(Composite composite, boolean flushCache) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected org.eclipse.swt.graphics.Point computeSize(Composite composite, int wHint, int hHint,
					boolean flushCache) {
				
				int height = 0;
				int width = 0;
				for(ToolItem item : additionalToolbar.getItems()) {
					if (item.getImage() != null) {
						ImageData data = item.getImage().getImageData();
						width += data.width;
						height = Math.max(height, data.height);
					} else if (item.getControl() != null) {
						org.eclipse.swt.graphics.Point size = item.getControl().computeSize(wHint, hHint);
						width += size.x;
						height = Math.max(height, size.y);
					}
				}
				return new org.eclipse.swt.graphics.Point(width, height);
			}
		});*/
	}

	/**
	 * Insert the content inside the toolbar, it must be created beofre trough the
	 * createToolBar method
	 */
	private void initializedToolBar() {
		ActionRegistry registry = getActionRegistry();
		// FIXME: the toolbars in SWT take the height from the highest element, padding
		// the image
		// at runtime brings some graphical glitches, so for the first action an image
		// of a specific size is
		// used to allow to have the right size of the toolbar
		createToolBarButton(registry.getAction(BookCompileAction.ID));
		createToolBarButton(registry.getAction(BookDatasetAction.ID));
		createToolBarButton(new Separator());
		
		if (zoomItem != null) {
			zoomItem.dispose();
			zoomItem = null;
		}
		ZoomManager property = (ZoomManager) getGraphicalViewer().getProperty(ZoomManager.class.toString());
		if (property != null) {

			createToolBarButton(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
			createToolBarButton(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
			
			zoomItem = new RZoomComboContributionItem(property);
			
			zoomItem.fill(additionalToolbar, 5);
		}
		
		createToolBarButton(new Separator());
		for (AContributorAction contAction : m.getActions()) {
			createToolBarButton(contAction);
			contAction.setJrConfig((JasperReportsConfiguration) getGraphicalViewer().getProperty("JRCONTEXT"));
		}
		additionalToolbar.pack();
		GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).grab(true, false).applyTo(additionalToolbar);
	}

	/**
	 * Create a toolbar button to execute an action
	 * 
	 * @param action
	 *            the action to execute, must be not null
	 */
	private void createToolBarButton(final IAction action) {
		if (action instanceof Separator) {
			new ToolItem(additionalToolbar, SWT.SEPARATOR);
		} else {
			ResizableToolItem toolItem = new ResizableToolItem(additionalToolbar, SWT.PUSH | SWT.FLAT, action, 25);
			toolItem.setToolTipText(action.getToolTipText());
			toolItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					action.run();
				}
			});
		}
	}

	@Override
	protected ContextMenuProvider createContextMenuProvider(EditPartViewer graphicalViewer) {
		return new BookEditorContextMenuProvider(graphicalViewer, getActionRegistry());
	}

	@Override
	protected EditPartFactory createEditParFactory() {
		return new BookEditPartFactory();
	}

	@Override
	protected JDReportOutlineView createOutline(TreeViewer viewer) {
		outlinePage = new JDReportOutlineView(this, viewer) {
			protected void initActions(ActionRegistry registry, IActionBars bars) {
			}

			protected ContextMenuProvider getMenuContentProvider() {
				return createContextMenuProvider(getViewer());
			}
		};
		return outlinePage;
	}

	@Override
	protected void createActions() {
		super.createActions();
		createBookRelatedActions();
		createDatasetRelatedActions();
	}

	private void createBookRelatedActions() {
		@SuppressWarnings("unchecked")
		List<String> selectionActions = getSelectionActions();
		ActionRegistry registry = getActionRegistry();

		IAction action = new CreateNewBookPartAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new DeleteBookPartAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new DeleteBookSectionAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void createDatasetRelatedActions() {
		@SuppressWarnings("unchecked")
		List<String> selectionActions = getSelectionActions();
		ActionRegistry registry = getActionRegistry();

		IAction action = new BookCompileAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new BookDatasetAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new CreateNewGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new CreateFieldAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateFieldAction.ID);

		action = new CreateFieldsContainerAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateFieldsContainerAction.ID);

		action = new CreateSortFieldAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateSortFieldAction.ID);

		action = new CreateVariableAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateVariableAction.ID);

		action = new SortVariablesAction(this);
		registry.registerAction(action);
		selectionActions.add(SortVariablesAction.ID);

		action = new SortParametersAction(this);
		registry.registerAction(action);
		selectionActions.add(SortParametersAction.ID);

		action = new SortFieldsAction(this);
		registry.registerAction(action);
		selectionActions.add(SortFieldsAction.ID);

		action = new ShowFieldsTreeAction(this);
		registry.registerAction(action);
		selectionActions.add(ShowFieldsTreeAction.ID);

		action = new DeleteFieldsGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(DeleteFieldsGroupAction.ID);

		action = new DeleteFieldsAllGroupAction(this);
		registry.registerAction(action);
		selectionActions.add(DeleteFieldsAllGroupAction.ID);

		action = new HideDefaultsParametersAction(this);
		registry.registerAction(action);
		selectionActions.add(HideDefaultsParametersAction.ID);

		action = new HideDefaultVariablesAction(this);
		registry.registerAction(action);
		selectionActions.add(HideDefaultVariablesAction.ID);

		action = new CreateScriptletAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateScriptletAction.ID);

		action = new CreateParameterAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateParameterAction.ID);

		action = new CreateParameterSetAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateParameterSetAction.ID);
	}

	@Override
	protected void configureGraphicalViewer() {
		JSSScrollingGraphicalViewer viewer = (JSSScrollingGraphicalViewer)getGraphicalViewer();
		viewer.getControl().setBackground(ColorConstants.white);
		//This custom selection tool allow to select the book section even when clicking on an x outsize id but 
		//on and y inside 
		viewer.getEditDomain().setDefaultTool(new SelectionTool() {
			
			@Override
			protected boolean updateTargetUnderMouse() {
				if (!isTargetLocked()) {
					EditPart editPart = null;
					if (getCurrentViewer() != null) {
						editPart = getCurrentViewer().findObjectAtExcluding(getLocation(), getExclusionSet(),getTargetingConditional());
						if (editPart instanceof MainDesignerRootEditPart) {
							getCurrentViewer().getEditPartRegistry();
							EditPart bookSectionPart = getCurrentViewer().findObjectAtExcluding(
									new Point(6,getLocation().y), getExclusionSet(),
									getTargetingConditional());
							if (bookSectionPart != null && bookSectionPart instanceof BookSectionEditPart) {
								editPart = bookSectionPart;
							}
							boolean changed = getTargetEditPart() != editPart;
							setTargetEditPart(editPart);
							return changed;
						} else return super.updateTargetUnderMouse();
					} else return super.updateTargetUnderMouse();
					
				} else
					return false;
			}
			
		});
		viewer.getEditDomain().loadDefaultTool();
		
		GraphicalViewer graphicalViewer = getGraphicalViewer();
		((FigureCanvas)graphicalViewer.getControl()).setHorizontalScrollBarVisibility(FigureCanvas.NEVER);
		graphicalViewer.setEditPartFactory(createEditParFactory());
		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();
		graphicalViewer.setRootEditPart(rootEditPart);

		// set rulers providers
		graphicalViewer.setKeyHandler(new JSSGraphicalViewerKeyHandler(graphicalViewer));

		graphicalViewer.addDropTargetListener(new ResourceTransferDropTargetListener(getGraphicalViewer()));
		graphicalViewer.setContextMenu(createContextMenuProvider(getGraphicalViewer()));
		graphicalViewer.setProperty("JRCONTEXT", jrContext);
		
		ZoomManager zoomManager = (ZoomManager) graphicalViewer.getProperty(ZoomManager.class.toString());

		getActionRegistry().registerAction(new ZoomInAction(zoomManager));
		getActionRegistry().registerAction(new ZoomOutAction(zoomManager));
		getActionRegistry().registerAction(new ZoomActualAction(zoomManager));
		graphicalViewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);
	}

	@Override
	public void setModel(INode model) {
		INode currModel = getModel();
		if (currModel != null) {
			((MBookReport) currModel.getChildren().get(0)).getPropertyChangeSupport()
					.removePropertyChangeListener(modelChangesListener);
		}
		super.setModel(model);
		if (model != null) {
			((MBookReport) model.getChildren().get(0)).getPropertyChangeSupport()
					.addPropertyChangeListener(modelChangesListener);
		}
	}

}
