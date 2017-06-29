/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard.imp;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class ImportDAPage extends JSSHelpWizardPage {

	private TreeViewer viewer;

	protected ImportDAPage() {
		super("importda"); //$NON-NLS-1$
		setTitle(Messages.ImportDAPage_1);
		setDescription(Messages.ImportDAPage_2);
	}

	private List<DataAdapterDescriptor> selection;

	public List<DataAdapterDescriptor> getDataAdapterDescriptors() {
		return selection;
	}

	private boolean overwrite = false;

	public boolean isOverwrite() {
		return overwrite;
	}

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		setControl(cmp);
		cmp.setLayout(new GridLayout());

		viewer = new TreeViewer(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());

		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection s = (IStructuredSelection) viewer.getSelection();
				setPageComplete(!s.isEmpty());
				selection = (List<DataAdapterDescriptor>) s.toList();
			}
		});

		final Button bOverwrite = new Button(cmp, SWT.CHECK);
		bOverwrite.setText(Messages.ImportDAPage_3);
		bOverwrite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				overwrite = bOverwrite.getSelection();
			}
		});

		fillData();
		setPageComplete(false);
	}

	private void fillData() {
		List<ADataAdapterStorage> das = DataAdapterManager.getProjectStorages();
		viewer.setInput(das);
		viewer.expandAll();
	}

	@Override
	protected String getContextName() {
		return null;
	}

	class ViewContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Collection)
				return ((Collection<?>) inputElement).toArray();
			return null;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof ADataAdapterStorage)
				return ((ADataAdapterStorage) parentElement).getDataAdapterDescriptors().toArray();
			return null;
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return element instanceof ADataAdapterStorage;
		}
	}

	class ViewLabelProvider extends StyledCellLabelProvider {
		@Override
		public String getToolTipText(Object element) {
			if (element instanceof ADataAdapterStorage)
				return ((ADataAdapterStorage) element).getStorageName();
			if (element instanceof DataAdapterDescriptor)
				return ((DataAdapterDescriptor) element).getDataAdapter().getName();
			return null;
		}

		@Override
		public void update(ViewerCell cell) {
			Object element = cell.getElement();
			StyledString text = new StyledString();
			if (element instanceof ADataAdapterStorage) {
				text.append(((ADataAdapterStorage) element).getStorageName());

				cell.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/prj_obj.gif"));
			} else if (element instanceof DataAdapterDescriptor) {
				DataAdapterDescriptor dad = (DataAdapterDescriptor) element;
				for (ADataAdapterStorage das : DataAdapterManager.getProjectStorages()) {
					if (das.findDataAdapter(dad.getName()) != null) {
						text.append(das.getLabel(dad));
						break;
					}
				}

				cell.setImage(((DataAdapterDescriptor) element).getIcon(16));
			}
			cell.setText(text.toString());
			cell.setStyleRanges(text.getStyleRanges());
			super.update(cell);
		}
	}
}
