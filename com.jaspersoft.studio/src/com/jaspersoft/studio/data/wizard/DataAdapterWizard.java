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
package com.jaspersoft.studio.data.wizard;

import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.eclipse.util.JavaProjectClassLoader;
import net.sf.jasperreports.engine.util.CompositeClassloader;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.wizard.pages.DataAdapterEditorPage;
import com.jaspersoft.studio.data.wizard.pages.DataAdaptersListPage;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtils;

public class DataAdapterWizard extends Wizard implements SelectionListener {

	private DataAdapterDescriptor dataAdapter = null;
	private DataAdapterWizardDialog wizardDialog = null;
	private DataAdapterFactory selectedFactory = null;
	private DataAdaptersListPage dataAdapterListPage = null;
	private DataAdapterEditorPage dataAdapterEditorPage = null;

	/**
	 * This constructor will set the data adapter wizard. The data adapters list is displayed as first page, then the edit
	 * page is shown.
	 */
	public DataAdapterWizard() {
		setWindowTitle(Messages.DataAdapterWizard_windowtitle);
	}

	/**
	 * Pass to this constructor a dataAdapter to be edited. This will set the wizard directly to edit page.
	 * 
	 * @param dataAdapter
	 */
	public DataAdapterWizard(DataAdapterDescriptor dataAdapter) {
		this();
		this.dataAdapter = dataAdapter;
	}

	// WizardDialog Setter and Getter
	public void setWizardDialog(DataAdapterWizardDialog wizardDialog) {
		this.wizardDialog = wizardDialog;
		if (this.wizardDialog != null) {
			this.wizardDialog.addTestListener(this);

			this.wizardDialog.addPageChangingListener(new IPageChangingListener() {

				public void handlePageChanging(PageChangingEvent event) {
					// System.out.println("Moving from page " + event.getCurrentPage() + " to " + event.getTargetPage());

					if (event.getCurrentPage() == dataAdapterListPage && event.getTargetPage() == dataAdapterEditorPage) {
						// Update the layout of the editor page with the proper data adapter editor
						// provided by the new data adapter
						DataAdapterFactory factory = dataAdapterListPage.getSelectedFactory();

						java.text.MessageFormat fm = new java.text.MessageFormat(Messages.DataAdapterWizard_newdataadaptername);
						// 1. instance a new dataAdapter using the factory
						DataAdapterDescriptor newDataAdapter = factory.createDataAdapter();
						for (int i = 1; i < 1000; i++) {
							String name = fm.format(new Object[] { (i > 1) ? "(" + i + ")" : "" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

							if (DataAdapterManager.isDataAdapterNameValid(name)) {

								newDataAdapter.getDataAdapter().setName(name);
								break;
							}
						}

						// 2. set in the wizard page the data adapter to edit
						if (selectedFactory != factory) {
							dataAdapterEditorPage.setDataAdapter(newDataAdapter);
							selectedFactory = factory;
						}
					}
				}
			});

			// Enable the test button when the page activated is the dataAdapterEditorPage
			this.wizardDialog.addPageChangedListener(new IPageChangedListener() {

				public void pageChanged(PageChangedEvent event) {
					getWizardDialog().setTestButtonEnabled(event.getSelectedPage() == dataAdapterEditorPage);
				}
			});
		}
	}

	public DataAdapterWizardDialog getWizardDialog() {
		return wizardDialog;
	}

	@Override
	public void addPages() {

		if (dataAdapter == null) {
			dataAdapterListPage = new DataAdaptersListPage();
			addPage(dataAdapterListPage);
		}

		dataAdapterEditorPage = new DataAdapterEditorPage();
		if (dataAdapter != null) {
			dataAdapterEditorPage.setEditMode(true);
		}
		addPage(dataAdapterEditorPage);
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);

		if (this.dataAdapter != null) {
			DataAdapterDescriptor editedDataAdapter = DataAdapterManager.cloneDataAdapter(this.dataAdapter);
			dataAdapterEditorPage.setDataAdapter(editedDataAdapter);
		}
	}

	// Save the new adapter using the manager
	@Override
	public boolean performFinish() {
		DataAdapterDescriptor editedDataAdapter = dataAdapterEditorPage.getDataAdapter();

		if (this.dataAdapter == null) {
			this.dataAdapter = editedDataAdapter;
		} else // We are modifying an existing adapter....
		{
			// ... let's update with the adapter just modified ...
			String oldName = this.dataAdapter.getName();
			dataAdapter.setDataAdapter(editedDataAdapter.getDataAdapter());
			if (!oldName.equals(editedDataAdapter.getName())) {
				if (!DataAdapterManager.isDataAdapterNameValid(editedDataAdapter.getName())) {
					dataAdapter.getDataAdapter().setName(oldName);
				}
			}
		}
		return true;
	}

	/**
	 * This method is called when the test button is pressed
	 */
	public void widgetSelected(SelectionEvent e) {
		if (getContainer().getCurrentPage() == dataAdapterEditorPage) {
			ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
			try {
				ClassLoader cl = null;
				IProject[] prjs = ResourcesPlugin.getWorkspace().getRoot().getProjects();
				for (IProject p : prjs) {
					if (p.isAccessible() && p.getNature(JavaCore.NATURE_ID) != null) {
						if (cl == null)
							cl = new JavaProjectClassLoader(JavaCore.create(p));
						else
							cl = new CompositeClassloader(cl, new JavaProjectClassLoader(JavaCore.create(p)));
					}
				}
				if (cl != null)
					Thread.currentThread().setContextClassLoader(cl);

				DataAdapterServiceUtil.getDataAdapterService(
						dataAdapterEditorPage.getDataAdapterEditor().getDataAdapter().getDataAdapter()).test();

				MessageBox mb = new MessageBox(getContainer().getShell(), SWT.ICON_INFORMATION | SWT.OK);
				mb.setText(Messages.DataAdapterWizard_testbutton);
				mb.setMessage(Messages.DataAdapterWizard_testsuccesful);
				mb.open();

			} catch (Exception e1) {
				UIUtils.showError(e1);
			} finally {
				Thread.currentThread().setContextClassLoader(oldCL);
			}
		}
	}

	/**
	 * Returns the new data adapter (or the modified data adapter in case the wizard is used to edit an existing data
	 * adapter). It returns null (or the original data adapter) if the wizard has not been completed. The returned object
	 * is the same used in the constructor in case of editing.
	 * 
	 * @return
	 */
	public DataAdapterDescriptor getDataAdapter() {
		return this.dataAdapter;
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		// nothing
	}
}
