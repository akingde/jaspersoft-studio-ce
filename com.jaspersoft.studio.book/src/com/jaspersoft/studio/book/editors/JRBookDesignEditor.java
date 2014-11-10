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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISaveablePart;

import com.jaspersoft.studio.book.dnd.ResourceTransferDropTargetListener;
import com.jaspersoft.studio.book.editors.actions.CreateNewBookPartAction;
import com.jaspersoft.studio.book.editors.actions.CreateNewGroupAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookPartAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookSectionAction;
import com.jaspersoft.studio.book.model.MBookReport;
import com.jaspersoft.studio.editor.AGraphicEditor;
import com.jaspersoft.studio.editor.gef.parts.JSSGraphicalViewerKeyHandler;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterSetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JRBookDesignEditor extends AGraphicEditor {

	private PropertyChangeListener modelChangesListener;
	
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
		ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
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

		IAction action = new CreateNewGroupAction(this);
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
