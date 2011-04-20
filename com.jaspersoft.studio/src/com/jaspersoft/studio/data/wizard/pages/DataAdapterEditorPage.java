/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.wizard.pages;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.utils.Misc;

public class DataAdapterEditorPage extends WizardPage {

	private DataAdapterEditor dataAdapterEditor = null;
	private String subTitle = "";
	private Composite mainContainer = null;
	private Composite staticContainer = null;
	private Composite customContainer = null;
	private Composite editorComposite = null;
	private Text textName;
	private boolean editMode = false;

	/**
	 * Create the wizard.
	 */
	public DataAdapterEditorPage() {
		super("dataAdaptereditorpage");
		setTitle("Data Adapter");
		setDescription("Edit your Data Adapter");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {

		mainContainer = new Composite(parent, SWT.NONE);
		setControl(mainContainer);
		mainContainer.setLayout(new GridLayout(1, false));
		
		staticContainer = new Composite(mainContainer, SWT.NONE);
		staticContainer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_staticContainer = new GridLayout(2, false);
		gl_staticContainer.marginHeight = 0;
		gl_staticContainer.marginWidth = 0;
		staticContainer.setLayout(gl_staticContainer);
		
		Label lblName = new Label(staticContainer, SWT.NONE);
		lblName.setText("Name:");
		
		textName = new Text(staticContainer, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label = new Label(staticContainer, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		
		customContainer = new Composite(mainContainer, SWT.NONE);
		customContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		customContainer.setLayout(new GridLayout(1, false));
		
		textName.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				
				String name = textName.getText().trim();
				boolean b;
				
				if (isEditMode()) { // Edit Data Adapter Mode
					b = isDataAdapterNameValid(name);
				} else { // Creating New Data Adapter Mode
					b = DataAdapterManager.isDataAdapterNameValid(name);
				}
				
				setPageComplete(b);
				
				if (b) {
					setDescription( getSubTitle() );
					setMessage( getSubTitle() );
				} else {
					
					if (name.length() > 0) {
						setMessage("Data Adapter \"" + name + "\" already exists. Please specify another name.", ERROR);
					} else {
						setMessage("Please specify a name for this Data Adapter.", ERROR);
					}
				}
			}
		});
		
		setPageComplete(false);
		setMessage("Please specify a name for this Data Adapter.", ERROR);
	}

	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem().displayHelp(dataAdapterEditor.getHelpContextId());
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	/**
	 * This method guesses the UI to use to edit the data adapter specified
	 * @param newDataAdapter
	 */
	public void setDataAdapter(DataAdapter newDataAdapter) {

		
		// ?
		if (newDataAdapter.getEditor() == dataAdapterEditor) return;
		
		setSubTitle(DataAdapterManager.findFactoryByDataAdapterClass(newDataAdapter.getClass().getName()).getDescription() );
		// 1. get the DataAdapterEditor
		dataAdapterEditor = newDataAdapter.getEditor();

	  // 2. add the composite from the DataAdapterEditor to the wizard page
		if (editorComposite != null)
		{
			editorComposite.dispose();
		}
		
		editorComposite = dataAdapterEditor.getComposite(customContainer, SWT.NULL, this);
		editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// 4. set the new dataAdapter to the DataAdapterEditor
		dataAdapterEditor.setDataAdapter(newDataAdapter);
		
		// 5. fill the name if the new data adapter has one
		textName.setText(Misc.nvl(newDataAdapter.getName(),""));
		
		// 6. resize the dialog properly
		customContainer.layout();

		if (getShell().isVisible()) // If the shell is not yet visible, it will layout the content by itself when displayed.
		{
			Point currentSize = customContainer.getSize();
			Point preferredSize = customContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		
			Point windowSize = getShell().getSize();
			getShell().layout();
			
			getShell()
						.setSize(
								new Point(windowSize.x, windowSize.y + Math.max(0, preferredSize.y - currentSize.y)));
		}
	}

	public DataAdapter getDataAdapter() {
		DataAdapter da = getDataAdapterEditor().getDataAdapter();
		da.setName(textName.getText());
		return da;
	}
	
	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}
	
	/**
	 * Returns the data adapter editor
	 * @return dataAdapterEditor
	 */
	public DataAdapterEditor getDataAdapterEditor() {
		return dataAdapterEditor;
	}

	/**
	 * Set the DataAdapterEditorPage mode.<br>
	 * True if modifying an existing Data Adapter.<br>
	 * False if creating an new Data Adapter.
	 * @param editMode boolean true or false
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	/**
	 * Return the DataAdapterEditorPage mode.<br>
	 * True if modifying an existing Data Adapter.<br>
	 * False if creating an new Data Adapter.
	 * @return true or false
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * This method is similar as <b>isDataAdapterNameValid()</b> method from {@link DataAdapterManager}.
	 * The only difference is the data adapter currently being edited is excluded from this check.
	 * @param dataAdapterName
	 * @return true or false
	 */
	private boolean isDataAdapterNameValid(String dataAdapterName) {
		
		if (dataAdapterName == null || "".equals(dataAdapterName)) return false;
		
		// remove the currently edited data adapter from the list
		List<DataAdapter> dataAdapters = DataAdapterManager.getDataAdapters();
		int index = -1;
		for (int i = 0; i < dataAdapters.size(); i++) {
			if (dataAdapterEditor.getDataAdapter().getName().equals(dataAdapters.get(i).getName())) {
				index = i;
			}
		}
		
		if (index < 0) {
			return false;
		} else {
			dataAdapters.remove(index);
		}
		
		for (DataAdapter dataAdapter : dataAdapters) {
			if (dataAdapter.getName().equals(dataAdapterName)) return false;
		}
		return true;
	}
}
