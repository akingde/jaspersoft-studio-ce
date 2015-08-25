/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.widget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.eclipse.viewer.IReportViewerListener;
import net.sf.jasperreports.eclipse.viewer.ReportViewerEvent;
import net.sf.jasperreports.engine.design.JRDesignDataset;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.storage.JRDefaultDataAdapterStorage;
import com.jaspersoft.studio.messages.Messages;

/**
 * 
 * Action used to show a submenu with all the data adapter and allowing to select them
 * 
 */
public class DataAdapterAction extends Action implements IMenuCreator, PropertyChangeListener, IReportViewerListener {

	public static final String ID = "DATAADAPTERACTION"; //$NON-NLS-1$
	
	private IDataAdapterRunnable editor;
	
	private ADataAdapterStorage[] dastorages;
	
	private Menu listMenu;
	
	private Control parent;
	
	private DataAdapterDescriptor selectedDA;
	
	/**
	 * Some data adapter are available or not depending from the properties of the current dataset
	 */
	private JRDesignDataset currentDataset;
	
	/**
	 * Create the action
	 * 
	 * @param editor the current runnable editor
	 * @param dastorages the data adapter storages
	 * @param dataset Some data adapter are available or not depending from the properties of the current dataset
	 */
	public DataAdapterAction(IDataAdapterRunnable editor, ADataAdapterStorage[] dastorages, JRDesignDataset dataset) {
		super();
		setId(ID);
		setMenuCreator(this);

		setText(Messages.DataAdapterAction_0);
		setDescription(Messages.DataAdapterAction_1);
		setToolTipText(Messages.DataAdapterAction_2);
		this.editor = editor;
		this.dastorages = dastorages;
		this.currentDataset = dataset;
	}
	
	/**
	 * Create the action, as dataset to get the dataset relative adapters it uses the main one
	 * 
	 * @param editor the current runnable editor
	 * @param dastorages the data adapter storages
	 */
	public DataAdapterAction(IDataAdapterRunnable editor, ADataAdapterStorage[] dastorages) {
		super();
		setId(ID);
		setMenuCreator(this);

		setText(Messages.DataAdapterAction_0);
		setDescription(Messages.DataAdapterAction_1);
		setToolTipText(Messages.DataAdapterAction_2);
		this.editor = editor;
		this.dastorages = dastorages;
		this.currentDataset = editor.getConfiguration().getJasperDesign().getMainDesignDataset();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	/**
	 * Run the report with a specific dataset, selected in the menu.
	 * If the dataset selected is the JR default one run the report
	 * with null as dataset, an JasperReports will automatically fallback on 
	 * the default one
	 */
	@Override
	public void run() {
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(editor.getConfiguration());
		DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
		if (defaultDA == selectedDA){
			editor.runReport(null);
		} else {
			editor.runReport(selectedDA);
		}
	}

	public void dispose() {
		if (listMenu != null)
			listMenu.dispose();
	}

	public Menu getMenu(Control parent) {
		this.parent = parent;
		if (listMenu != null)
			listMenu.dispose();
		listMenu = new Menu(parent);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MenuItem menuItem = (MenuItem) e.getSource();
				menuItem.setSelection(true);

				setSelected((DataAdapterDescriptor) menuItem.getData("da.key")); //$NON-NLS-1$
				// do run
				run();
			}
		};
		
		if (dastorages != null) {
			for (int i = 0; i < dastorages.length; i++) {
				final ADataAdapterStorage s = dastorages[i];
				for (DataAdapterDescriptor d : s.getDataAdapterDescriptors(currentDataset)) {
					final MenuItem m1 = new MenuItem(listMenu, SWT.PUSH);
					m1.setImage(d.getIcon(16));
					m1.addSelectionListener(listener);
					m1.setData("da.key", d); //$NON-NLS-1$
					m1.setText(s.getLabel(d));
				}
				if (!s.getDataAdapterDescriptors(currentDataset).isEmpty() && i < dastorages.length - 1
						&& !dastorages[i + 1].getDataAdapterDescriptors(currentDataset).isEmpty())
					new MenuItem(listMenu, SWT.SEPARATOR);
			}
		}
		return listMenu;
	}

	private void refresh() {

	}

	public void setDataAdapterStorages(ADataAdapterStorage[] dastorages) {
		if (this.dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				das.removePropertyChangeListener(this);
		}

		this.dastorages = dastorages;
		if (dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				das.addPropertyChangeListener(this);
		}
		setSelected(Messages.DataAdapterManager_oneemptyrecord);
	}

	public Menu getMenu(Menu parent) {
		return null;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

	public void viewerStateChanged(ReportViewerEvent arg0) {
		refresh();
	}

	public void setSelected(String d) {
		if (d != null && dastorages != null) {
			for (ADataAdapterStorage das : dastorages)
				for (DataAdapterDescriptor dad : das.getDataAdapterDescriptors()) {
					if (dad.getName().equals(d)) {
						setSelected(dad);
						return;
					}
				}
		}
		//Else check if there is the default data adapter available
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(editor.getConfiguration());
		DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
		if (defaultDA != null){
			setSelected(defaultDA);
		}
	}

	public void setSelected(DataAdapterDescriptor d) {
		selectedDA = d;
		// set current
		String name = d.getTitle();
		if (name.length() > 17)
			name = name.substring(0, 17) + "..."; //$NON-NLS-1$
		setText(name);
		setDescription(d.getDescription());
		setToolTipText(d.getName());

		if (parent != null) {
			ToolBar toolBar = (ToolBar) parent;
			toolBar.pack(true);
			toolBar.getParent().getParent().layout(true);
		}
	}
	
	/**
	 * Check  if the selected is the default one 
	 * 
	 * @return true if the selected is the default one (and the default one is set), false otherwise
	 */
	public boolean isDefaultDASelected(){
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(editor.getConfiguration());
		DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
		return (defaultDA != null && defaultDA == selectedDA);
	}
	
	/**
	 * Get the selected data adapter
	 * 
	 * @return the selected data adapter
	 */
	public DataAdapterDescriptor getSelected() {
		return selectedDA;
	}
}
