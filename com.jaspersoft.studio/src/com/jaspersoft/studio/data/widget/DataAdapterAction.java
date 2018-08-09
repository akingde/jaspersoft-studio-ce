/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.widget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
import com.jaspersoft.studio.preferences.execution.ReportExecutionPreferencePage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.viewer.IReportViewerListener;
import net.sf.jasperreports.eclipse.viewer.ReportViewerEvent;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * 
 * Action used to show a submenu with all the data adapter and allowing to
 * select them
 * 
 */
public class DataAdapterAction extends Action implements IMenuCreator, PropertyChangeListener, IReportViewerListener {

	public static final String ID = "DATAADAPTERACTION"; //$NON-NLS-1$

	private IDataAdapterRunnable editor;

	private ADataAdapterStorage[] dastorages;

	private Menu listMenu;

	private Control parent;

	private DataAdapterDescriptor selectedDA;

	private String language;

	/**
	 * Create the action
	 * 
	 * @param editor
	 *            the current runnable editor
	 * @param dastorages
	 *            the data adapter storages
	 * @param dataset
	 *            Some data adapter are available or not depending from the
	 *            properties of the current dataset
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
	}

	/**
	 * Create the action, as dataset to get the dataset relative adapters it uses
	 * the main one
	 * 
	 * @param editor
	 *            the current runnable editor
	 * @param dastorages
	 *            the data adapter storages
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
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public JRDesignDataset getCurrentDataset() {
		if (editor.getConfiguration().getJasperDesign() != null)
			return editor.getConfiguration().getJasperDesign().getMainDesignDataset();
		return null;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	/**
	 * Run the report with a specific dataset, selected in the menu. If the dataset
	 * selected is the JR default one run the report with null as dataset, an
	 * JasperReports will automatically fallback on the default one
	 */
	@Override
	public void run() {
		String runonchange = editor.getConfiguration()
				.getProperty(ReportExecutionPreferencePage.JSS_RUNREPORTONDACHANGE, "true");
		if (runonchange.equals("false"))
			return;
		JRDesignDataset currentDataset = getCurrentDataset();
		if (currentDataset == null) {
			editor.runReport(null);
			return;
		}
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(editor.getConfiguration());
		DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
		if (defaultDA == selectedDA) {
			editor.runReport(null);
		} else {
			if (!editor.runReport(selectedDA, false)) {
				if (currentDataset != null) {
					defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
					if (defaultDA != null && defaultDA != selectedDA)
						setSelected(defaultDA);
				}
			}
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
		if (parent.isDisposed())
			UIUtils.showError(new Exception("he is disposed"));
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
			JRDesignDataset currentDataset = getCurrentDataset();
			if (currentDataset != null) {
				for (int i = 0; i < dastorages.length; i++) {
					final ADataAdapterStorage s = dastorages[i];
					if (!editor.getConfiguration().getEditorContext().isDataAdapterStorage(s))
						continue;
					for (DataAdapterDescriptor d : s.getDataAdapterDescriptors(currentDataset)) {
						if (language != null) {
							String[] langs = d.getLanguages();
							if (langs != null) {
								boolean exists = false;
								for (String l : langs)
									if (language.equalsIgnoreCase(l) || l.equals("*")) {
										exists = true;
										break;
									}
								if (!exists)
									continue;
							}
						}
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
		}
		return listMenu;
	}

	private void refresh() {
		// nothing, a placeholder for refresh
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
		if (d != null && dastorages != null)
			for (ADataAdapterStorage das : dastorages) {
				if (!editor.getConfiguration().getEditorContext().isDataAdapterStorage(das))
					continue;
				for (DataAdapterDescriptor dad : das.getDataAdapterDescriptors())
					if (dad.getName().equals(d)) {
						setSelected(dad);
						return;
					}
			}
		// Else check if there is the default data adapter available
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(editor.getConfiguration());
		JRDesignDataset currentDataset = getCurrentDataset();
		if (currentDataset != null) {
			DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
			if (defaultDA != null)
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
	 * Check if the selected is the default one
	 * 
	 * @return true if the selected is the default one (and the default one is set),
	 *         false otherwise
	 */
	public boolean isDefaultDASelected() {
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(editor.getConfiguration());
		JRDesignDataset currentDataset = getCurrentDataset();
		if (currentDataset != null) {
			DataAdapterDescriptor defaultDA = defaultStorage.getDefaultJRDataAdapter(currentDataset);
			return (defaultDA != null && defaultDA == selectedDA);
		}
		return false;
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
