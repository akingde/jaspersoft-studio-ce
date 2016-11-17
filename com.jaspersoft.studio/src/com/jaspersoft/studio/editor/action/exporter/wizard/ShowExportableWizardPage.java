/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page where all the exportable resources are listed and the user can define
 * which one should be exported in the final file
 * 
 * @author Orlandin Marco
 *
 */
public class ShowExportableWizardPage extends JSSWizardPage {

	private CheckboxTreeAndListGroup resourceSelectionList;
	
	/**
	 * Create the page
	 */
	protected ShowExportableWizardPage() {
		super("showExportablePage"); //$NON-NLS-1$
		setTitle(Messages.DestinationPage_exportWizardTitle);
		setDescription(Messages.ShowExportableWizardPage_pageDescription);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
	
		resourceSelectionList = new CheckboxTreeAndListGroup(container, getRootObject(), treeContentProvider, labelProvider, listContentProvider, labelProvider, SWT.NONE, 500, 400);
		resourceSelectionList.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				validate();
			}
		});
		
		setControl(container);
	  validate();
	}
	
	protected List<IExportedResourceHandler> getRootObject(){
		List<IExportedResourceHandler> result = new ArrayList<IExportedResourceHandler>();
		for(IExportedResourceHandler definition : ExtensionManager.getContributedExporters()){
			if (definition.getExportableResources().size() > 0){
				result.add(definition);
			}
		}
		return result;
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	public Map<IExportedResourceHandler, List<IResourceDefinition>> getSelectedResources(){
		Map<IExportedResourceHandler, List<IResourceDefinition>> result = new HashMap<IExportedResourceHandler, List<IResourceDefinition>>();
		for(Object selectedTreeItem : resourceSelectionList.getAllCheckedTreeItems()){
			IExportedResourceHandler exportedType = (IExportedResourceHandler)selectedTreeItem;
			List<Object> selectedListItems = resourceSelectionList.getCheckedListEntryForTreeItem(exportedType);
			if (selectedListItems != null && !selectedListItems.isEmpty()){
				List<IResourceDefinition> selectedResources = new ArrayList<IResourceDefinition>();
				for(Object selectedItem : selectedListItems){
					selectedResources.add((IResourceDefinition)selectedItem);
				}
				result.put(exportedType, selectedResources);
			}
		}
		return result;
	}
	
	/**
	 * Set the page complete if at least an exporter is selected
	 */
	protected void validate(){
		setPageComplete(!getSelectedResources().keySet().isEmpty());
	}
	
	
	//PROVIDER FOR THE TREE-LIST
	
	
	private ITreeContentProvider treeContentProvider = new ITreeContentProvider() {
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
		@Override
		public void dispose() {
		}
		
		@Override
		public boolean hasChildren(Object element) {
			return element instanceof List<?>;
		}
		
		@Override
		public Object getParent(Object element) {
			return null;
		}
		
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List<?>){
				return ((List<?>)inputElement).toArray();
			} else {
				return new Object[0];
			}
		}
		
		@Override
		public Object[] getChildren(Object parentElement) {
			return getElements(parentElement);
		}
	};
	
	private IStructuredContentProvider listContentProvider = new IStructuredContentProvider() {
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {		
		}
		
		@Override
		public void dispose() {	
		}
		
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof IExportedResourceHandler){
				return ((IExportedResourceHandler)inputElement).getExportableResources().toArray();
			}
			return new Object[0];
		}
	};
	
	private ILabelProvider labelProvider = new ILabelProvider() {
		
		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
		
		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}
		
		@Override
		public void dispose() {
		}
		
		@Override
		public void addListener(ILabelProviderListener listener) {	
		}
		
		@Override
		public String getText(Object element) {
			if (element instanceof IExportedResourceHandler){
				return ((IExportedResourceHandler)element).getResourceNameExport();
			} else if (element instanceof IResourceDefinition){
				return ((IResourceDefinition)element).getName();
			}
			return null;
		}
		
		@Override
		public Image getImage(Object element) {
			return null;
		}
	};

}
