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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.outline.ReportOutlineView;
import com.jaspersoft.studio.property.JRPropertySheetEntry;

/**
 * The Class ReportContainer.
 * 
 * @author Chicu Veaceslav
 */
public class ReportContainer extends MultiPageEditorPart implements ITabbedPropertySheetPageContributor {

	/** The model. */
	private INode model = null;

	/** The editors. */
	private List<AbstractVisualEditor> editors = new ArrayList<AbstractVisualEditor>();

	/** The parent. */
	private EditorPart parent;

	/**
	 * Instantiates a new report container.
	 * 
	 * @param parent
	 *          the parent
	 */
	public ReportContainer(EditorPart parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#getActiveEditor()
	 */
	@Override
	public IEditorPart getActiveEditor() {
		return super.getActiveEditor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
	 */
	@Override
	protected void createPages() {
		((CTabFolder) getContainer()).setTabPosition(SWT.TOP);
		try {
			ReportEditor reportEditor = new ReportEditor();
			int index = addPage(reportEditor, getEditorInput());
			setPageText(index, Messages.ReportContainer_main_report);
			editors.add(reportEditor);
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(), "Error creating nested visual editor", null, e.getStatus());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		for (AbstractVisualEditor editor : editors) {
			editor.doSave(monitor);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		for (AbstractVisualEditor editor : editors) {
			editor.doSaveAs();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *          the new model
	 */
	public void setModel(INode model) {
		this.model = model;
		if (propertySheetEntry != null)
			propertySheetEntry.setModel((ANode) model.getChildren().get(0));
		if (outlineView != null)
			outlineView.setModel((ANode) model);
		updateVisualView();
	}

	/**
	 * Update visual view.
	 */
	public void updateVisualView() {
		if (editors != null)
			for (AbstractVisualEditor editor : editors) {
				editor.setModel(this.model);
			}
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
	 * @see org.eclipse.ui.part.MultiPageEditorPart#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class type) {
		if (type == IPropertySource.class)
			return getPropertySheetPage();
		if (type == IPropertySheetPage.class)
			return getPropertySheetPage();
		return super.getAdapter(type);
	}

	/** The property sheet page. */
	private IPropertySheetPage propertySheetPage;

	/** The property sheet entry. */
	private JRPropertySheetEntry propertySheetEntry;

	/**
	 * Gets the property sheet page.
	 * 
	 * @return the property sheet page
	 */
	public IPropertySheetPage getPropertySheetPage() {
		// PropertySheetPage psp = new PropertySheetPage();
		// propertySheetEntry = new JRPropertySheetEntry(((ReportEditor) editors.get(0)).getEditDomain().getCommandStack(),
		// (ANode) getModel());
		// psp.setRootEntry(propertySheetEntry);
		// propertySheetPage = psp;

		TabbedPropertySheetPage tpsp = new TabbedPropertySheetPage(ReportContainer.this, true);
		propertySheetPage = tpsp;

		return propertySheetPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
	 */
	public String getContributorId() {
		return "com.jaspersoft.studio.editor.report.ReportContainer"; //$NON-NLS-1$
	}

	/** The outline view. */
	private ReportOutlineView outlineView = null;

	/**
	 * Returns the overview for the outline view.
	 * 
	 * @return the overview
	 */
	public ReportOutlineView getOutlinePage() {
		if (null == outlineView) {
			outlineView = new ReportOutlineView(getModel());

			getSite().getPage().addSelectionListener(new ISelectionListener() {

				public void selectionChanged(IWorkbenchPart part, ISelection selection) {

					if (selection instanceof StructuredSelection) {
						Object obj = ((StructuredSelection) selection).getFirstElement();
						if (obj instanceof AJDEditPart) { // from editpart
							Object[] editParts = ((StructuredSelection) selection).toArray();
							INode[] models = new INode[editParts.length];
							for (int i = 0; i < editParts.length; i++) {
								AJDEditPart pageEditPart = (AJDEditPart) editParts[i];
								models[i] = pageEditPart.getModelNode();
							}
							outlineView.setSelection(new StructuredSelection(models));
						} else if (selection instanceof TreeSelection) { // from outline
							if (getActiveEditor() instanceof AbstractVisualEditor) {
								List<EditPart> parts = new ArrayList<EditPart>();
								GraphicalViewer viewer = ((AbstractVisualEditor) getActiveEditor()).getGraphicalViewer();
								TreeSelection s = (TreeSelection) selection;
								for (Iterator<?> it = s.iterator(); it.hasNext();) {
									EditPart editPart = (EditPart) viewer.getEditPartRegistry().get(it.next());
									if (editPart != null)
										parts.add(editPart);
								}
								viewer.setSelection(new StructuredSelection(parts));
							}
						}
					}

				}
			});
		}
		return outlineView;
	}
}
