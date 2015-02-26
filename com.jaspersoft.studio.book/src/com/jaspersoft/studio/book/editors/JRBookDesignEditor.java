/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.wb.swt.ResourceCache;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.book.dnd.ResourceTransferDropTargetListener;
import com.jaspersoft.studio.book.editors.actions.BookCompileAction;
import com.jaspersoft.studio.book.editors.actions.BookDatasetAction;
import com.jaspersoft.studio.book.editors.actions.CreateNewBookPartAction;
import com.jaspersoft.studio.book.editors.actions.CreateNewGroupAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookPartAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookSectionAction;
import com.jaspersoft.studio.book.model.MBookReport;
import com.jaspersoft.studio.editor.AGraphicEditor;
import com.jaspersoft.studio.editor.gef.parts.JSSGraphicalViewerKeyHandler;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.java2d.JSSScrollingGraphicalViewer;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterSetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.repository.actions.Separator;
import com.jaspersoft.studio.utils.AContributorAction;
import com.jaspersoft.studio.utils.ImageUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JRBookDesignEditor extends AGraphicEditor {

	/**
	 * Listener to model change, used to set the dirty flag on the reprot
	 * when something changes
	 */
	private PropertyChangeListener modelChangesListener;
	
	/**
	 * The extension manager for the plugin
	 */
	private ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
	
	/**
	 * The toolbar inside the book editor
	 */
	private ToolBar additionalToolbar;
	
	/**
	 * Local swt resources cache for the toolbar images
	 */
	private ResourceCache imagesResource = new ResourceCache();
	
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
	

	/**
	 * Get the image from the action and if it is tool small return a 
	 * resized version of that image to a fixed height
	 * 
	 * @param action the action, it must have an image
	 * @return an image if the one from the icon was too small or the image
	 * of the action itself
	 */
	private Image getResizedImage(IAction action){
		Image loadedImage = imagesResource.getImage(action.getImageDescriptor());
		int suggestedHeight = 25;
		//Resize the image if it is too big
		int width = loadedImage.getImageData().width;
		int height = loadedImage.getImageData().height;
		if (height < suggestedHeight){
			height = suggestedHeight;		
		}
		if (width != loadedImage.getImageData().width || height != loadedImage.getImageData().height){
			Image resizedImage = loadedImage;
			loadedImage = ImageUtils.padImage(loadedImage, width, height, additionalToolbar.getBackground().getRGB());
			resizedImage.dispose();
		}
		return loadedImage;
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
		
		//Create the toolbar
		createToolBar(container);
		
		//Create the viewer
		createGraphicalViewer(container);
		
		//Initialize the toolabar
		initializedToolBar();
	}
	
	/**
	 * Create the editor toolbar control
	 * 
	 * @param container container where the toolbar is placed
	 */
	private void createToolBar(Composite container){
		additionalToolbar = new ToolBar(container, SWT.HORIZONTAL | SWT.FLAT);
		//When the toolbar it's disposed discard also the images created for it
		additionalToolbar.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				imagesResource.dispose();
			}
		});
		GridData additionalToolbarGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
		additionalToolbar.setLayoutData(additionalToolbarGD);
	}
	
	/**
	 * Insert the content inside the toolbar, it must be created beofre trough
	 * the createToolBar method
	 */
	private void initializedToolBar(){	
		ActionRegistry registry = getActionRegistry();	
		createToolBarButton(registry.getAction(BookCompileAction.ID));
		createToolBarButton(registry.getAction(BookDatasetAction.ID));
		createToolBarButton(new Separator());
		for(AContributorAction contAction : m.getActions()){
			createToolBarButton(contAction);
			contAction.setJrConfig((JasperReportsConfiguration) getGraphicalViewer().getProperty("JRCONTEXT"));
		}
		GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).grab(true, false).applyTo(additionalToolbar);
	}
	
	/**
	 * Create a toolbar button to execute an action
	 * 
	 * @param action the action to execute, must be not null
	 */
	private void createToolBarButton(final IAction action){
		if (action instanceof Separator){
			 new ToolItem(additionalToolbar, SWT.SEPARATOR);
		} else {
			ToolItem toolItem = new ToolItem(additionalToolbar, SWT.PUSH | SWT.FLAT);
			Image img = ResourceManager.getImage(action.getImageDescriptor());
			if (img !=null){
				Image resizedImg = imagesResource.getImage(action.getId());
				if (resizedImg == null){
					resizedImg = getResizedImage(action);
					imagesResource.storeImage(action.getId(), resizedImg);
				}
				toolItem.setImage(resizedImg);
			} else {
				toolItem.setText(action.getText());
			}
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
	protected ContextMenuProvider createContextMenuProvider(
			EditPartViewer graphicalViewer) {
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

		action = new CreateSortFieldAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateSortFieldAction.ID);

		action = new CreateVariableAction(this);
		registry.registerAction(action);
		selectionActions.add(CreateVariableAction.ID);

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
		getGraphicalViewer().getControl().setBackground(ColorConstants.white);

		GraphicalViewer graphicalViewer = getGraphicalViewer();
		graphicalViewer.setEditPartFactory(createEditParFactory());
		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();
		graphicalViewer.setRootEditPart(rootEditPart);

		// set rulers providers
		graphicalViewer.setKeyHandler(new JSSGraphicalViewerKeyHandler(graphicalViewer));
		
		getGraphicalViewer().addDropTargetListener(new ResourceTransferDropTargetListener(getGraphicalViewer()));
		getGraphicalViewer().setContextMenu(createContextMenuProvider(getGraphicalViewer()));
		graphicalViewer.setProperty("JRCONTEXT", jrContext);
	}
	
	@Override
	public void setModel(INode model) {
		INode currModel = getModel();
		if(currModel!=null) {
			((MBookReport)currModel.getChildren().get(0)).getPropertyChangeSupport().removePropertyChangeListener(modelChangesListener);
		}
		super.setModel(model);
		if(model!=null) {
			((MBookReport)model.getChildren().get(0)).getPropertyChangeSupport().addPropertyChangeListener(modelChangesListener);
		}
	}
	
}
