/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.dataset.dialog;

import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.parts.PageEditPart;
import com.jaspersoft.studio.editor.outline.part.TreeEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.utils.ResourceManager;

public class DatasetAction extends SelectionAction {
	public static final String ID = "datasetAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public DatasetAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText("Dataset && Query ...");
		setToolTipText("DataSet and Query editor dialog");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(JaspersoftStudioPlugin.PLUGIN_ID,
				"icons/resources/dataset-16.png"));
		setId(ID);
		setEnabled(false);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		try {
			final AbstractVisualEditor part = (AbstractVisualEditor) getWorkbenchPart();
			MDataset mdataset = null;
			if(part instanceof ReportEditor){
				MReport mreport = (MReport) part.getModel().getChildren().get(0);
				// First check if any MDataset element is selected
				mdataset = getSelectedMDataset();
				if(mdataset==null){
					// if we return the main dataset element
					mdataset = (MDataset) mreport.getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
				}
			}
			else {
				// Handle custom editors for elements like table, crosstab and list
				// FIXME - Now this solution works because list/crosstab/table editors will
				// have only one child MDataset element. Once this will be no longer valid,
				// the code below must be changed
				if(part.getModel().getChildren().size()>0){
					INode firstChild = part.getModel().getChildren().get(0);
					if(firstChild instanceof MPage){
						for(INode c : firstChild.getChildren()){
							if(c instanceof MDataset){
								mdataset = (MDataset)c;
								break;
							}
						}
					}
				}
			}

			IContentOutlinePage cop = (IContentOutlinePage) part.getAdapter(IContentOutlinePage.class);
			if (cop != null) {
				IStructuredSelection sel = (IStructuredSelection) cop.getSelection();
				if (sel.getFirstElement() instanceof TreeEditPart) {
					Object obj = ((TreeEditPart) sel.getFirstElement()).getModel();
					if (obj instanceof MDataset)
						mdataset = (MDataset) obj;
				}
			}

			part.getAdapter(String.class);

			final DatasetDialog dlg = new DatasetDialog(Display.getDefault().getActiveShell(), mdataset,
					mdataset.getJasperConfiguration());
			if (dlg.open() == Window.OK) {
				Display.getCurrent().asyncExec(new Runnable() {

					public void run() {
						part.getEditDomain().getCommandStack().execute(dlg.getCommand());
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean calculateEnabled() {
		List<Object> selection = getSelectedObjects();
		if (!selection.isEmpty() && selection.size() == 1) {
			Object obj = selection.get(0);
			if(obj instanceof IDatasetDialogSupport){
				return true;
			}
			if (obj instanceof EditPart) {
				if (((EditPart) obj).getModel() instanceof MDataset || ((EditPart) obj).getModel() instanceof MBand
						|| ((EditPart) obj).getModel() instanceof MReport || ((EditPart)obj).getParent() instanceof PageEditPart)
					return true;
			}
		}

		return false;
	}
	
	/*
	 * Returns the currently selected MDataset object (i.e: in the Outline) if any.
	 */
	private MDataset getSelectedMDataset(){
		List<Object> selection = getSelectedObjects();
		if (!selection.isEmpty() && selection.size() == 1) {
			Object obj = selection.get(0);
			if (obj instanceof EditPart && 
					((EditPart) obj).getModel() instanceof MDataset){
				return (MDataset) ((EditPart) obj).getModel();
			}
		}
		return null;
	}
	
}
