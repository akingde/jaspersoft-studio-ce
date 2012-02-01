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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.style;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.editor.IGraphicalEditor;
import com.jaspersoft.studio.editor.action.ShowPropertyViewAction;
import com.jaspersoft.studio.editor.action.copy.CopyAction;
import com.jaspersoft.studio.editor.action.copy.CutAction;
import com.jaspersoft.studio.editor.action.copy.PasteAction;
import com.jaspersoft.studio.editor.gef.parts.MainDesignerRootEditPart;
import com.jaspersoft.studio.editor.java2d.J2DGraphicalEditor;
import com.jaspersoft.studio.editor.outline.JDReportOutlineView;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateReferenceAction;
import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class CrosstabEditor.
 * 
 * @author Chicu Veaceslav
 */
public class StyleTemplateEditor extends J2DGraphicalEditor implements ITabbedPropertySheetPageContributor,
		IGraphicalEditor {

	private JasperReportsConfiguration jrContext;

	public StyleTemplateEditor(JasperReportsConfiguration jrContext) {
		super();
		setEditDomain(new DefaultEditDomain(this));
		this.jrContext = jrContext;
	}

	public FigureCanvas getEditor() {
		return (FigureCanvas) getGraphicalViewer().getControl();
	}

	@Override
	public SelectionSynchronizer getSelectionSynchronizer() {
		return super.getSelectionSynchronizer();
	}

	@Override
	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}

	public String getContributorId() {
		return "com.jaspersoft.studio.editor.report.ReportContainer";// "com.jaspersoft.studio.JRtxEditor";
	}

	@Override
	public DefaultEditDomain getEditDomain() {
		return super.getEditDomain();
	}

	@Override
	public ActionRegistry getActionRegistry() {
		return super.getActionRegistry();
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
		if (outlinePage != null)
			outlinePage.setContents(model);
		if (model != null)
			getGraphicalViewer().setContents(model.getChildren().get(0));
		else
			getGraphicalViewer().setContents(null);
	}

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public INode getModel() {
		return model;
	}

	private EditorContributor editorContributor;

	@Override
	public Object getAdapter(Class type) {
		if (type == IPropertySource.class)
			return getPropertySheetPage();
		if (type == IPropertySheetPage.class)
			return getPropertySheetPage();
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

	/** The property sheet page. */
	private IPropertySheetPage propertySheetPage;

	/**
	 * Gets the property sheet page.
	 * 
	 * @return the property sheet page
	 */
	public IPropertySheetPage getPropertySheetPage() {
		propertySheetPage = new TabbedPropertySheetPage(this, true);
		return propertySheetPage;
	}

	@Override
	public void createPartControl(Composite parent) {
		createGraphicalViewer(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
	 */
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer graphicalViewer = getGraphicalViewer();

		MainDesignerRootEditPart rootEditPart = new MainDesignerRootEditPart();

		graphicalViewer.setRootEditPart(rootEditPart);
		// set EditPartFactory
		graphicalViewer.setEditPartFactory(new StyleEditPartFactory());

		graphicalViewer.setKeyHandler(new GraphicalViewerKeyHandler(graphicalViewer));

		graphicalViewer.setContextMenu(new AppStyleContextMenuProvider(graphicalViewer, getActionRegistry()));

		ZoomManager zoomManager = (ZoomManager) graphicalViewer.getProperty(ZoomManager.class.toString());

		getActionRegistry().registerAction(new ZoomInAction(zoomManager));
		getActionRegistry().registerAction(new ZoomOutAction(zoomManager));
		graphicalViewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);

		graphicalViewer.setProperty("JRCONTEXT", jrContext);
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

	protected JDReportOutlineView outlinePage;

	protected JDReportOutlineView getOutlineView() {
		if (outlinePage == null) {
			TreeViewer viewer = new TreeViewer();
			outlinePage = new JDReportOutlineView(this, viewer) {
				protected void initActions(ActionRegistry registry, IActionBars bars) {
					String id = CreateStyleTemplateReferenceAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));

					id = CreateStyleAction.ID;
					bars.setGlobalActionHandler(id, registry.getAction(id));
				}

				protected org.eclipse.gef.ContextMenuProvider getMenuContentProvider() {
					ContextMenuProvider provider = new AppStyleContextMenuProvider(getViewer(), editor.getActionRegistry());
					return provider;
				}
			};
			outlinePage.setEditPartFactory(new StyleTreeEditPartFactory());
		}
		return outlinePage;

	}

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

		action = new CreateStyleAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateStyleAction.ID);

		action = new CreateStyleTemplateReferenceAction(this);
		registry.registerAction(action);
		getSelectionActions().add(CreateStyleTemplateReferenceAction.ID);

		// ------------
		action = new DirectEditAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// ------------------

		action = new ShowPropertyViewAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		getEditDomain().getCommandStack().markSaveLocation();
	}

}
