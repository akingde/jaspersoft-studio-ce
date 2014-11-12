/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.subreport.command.wizard.NewSubreportPage;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.JSSWizardPage;

public class SubreportPropertyPage extends JSSWizardPage {

	/**
	 * This variable stores the last set of parameters specified by using loadSettings. Settings will be reloaded if the
	 * new array is different from this one...
	 */
	private JRParameter[] lastParameters = null;
	
	/**
	 * The value inside the dialog when it's opened and the list of value when it's closed
	 */
	private List<JRSubreportParameter> value = new ArrayList<JRSubreportParameter>();
	
	/**
	 * The table control
	 */
	private Table table;
	
	/**
	 * The viewer for the table
	 */
	private TableViewer tableViewer;
	
	/**
	 * Button to edit an entry in the table
	 */
	private Button editButton;
	
	/**
	 * Button to delete an entry on the table
	 */
	private Button deleteButton;

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			JRSubreportParameter sp = (JRSubreportParameter) element;
			switch (columnIndex) {
			case 0:
				return sp.getName();
			case 1:
				if (sp != null && sp.getExpression() != null)
					return sp.getExpression().getText();
			}
			return ""; //$NON-NLS-1$
		}
	}

	public SubreportPropertyPage() {
		this("subreportpage"); //$NON-NLS-1$
	}

	public SubreportPropertyPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_subreport_parameters);
		setDescription(Messages.SubreportPropertyPage_description);
	}
	
	/**
	 * Return an array of all the defined parameter inside the dialog
	 * 
	 * @return a not null array of parameter
	 */
	public JRSubreportParameter[] getValue() {
		return value.toArray(new JRSubreportParameter[value.size()]);
	}

	@Override
	public void dispose() {
		getValue();
		super.dispose();
	}

	/**
	 * Set the value to show inside the table
	 * 
	 * @param value an array of value, if null it is considered as an empty list
	 */
	public void setValue(JRSubreportParameter[] value) {
		if (value == null) {
			this.value = new ArrayList<JRSubreportParameter>();
		} else {
			this.value = new ArrayList<JRSubreportParameter>(Arrays.asList(value));
		}
		if (table != null)
			fillTable();
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SUBREPORT_PROPERTIES;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 400;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		//CREATE THE ADD BUTTON
		
		Button addButton = new Button(bGroup, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperReportsConfiguration jConfig = (JasperReportsConfiguration) getSettings().get(JSSWizard.JASPERREPORTS_CONFIGURATION);
				InputParameterDialog inputDialog = new InputParameterDialog(getShell(), jConfig, value);
				if (inputDialog.open() == Dialog.OK){
					value.add(inputDialog.getValue());
					tableViewer.refresh();
				}
			}
		});
		
		//CREATE THE EDIT BUTTON
		
		editButton = new Button(bGroup, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)tableViewer.getSelection();
				if (selection.size() > 0){
					JRDesignSubreportParameter selectedValue = (JRDesignSubreportParameter)selection.getFirstElement();
					editElement(selectedValue);
				}
			}
		});
		
		editButton.setEnabled(false);
		
		//CREATE THE DELETE BUTTON
		
		deleteButton = new Button(bGroup, SWT.PUSH);
		deleteButton.setText(Messages.common_delete);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)tableViewer.getSelection();
				if (selection.size() > 0){
					JRSubreportParameter selectedValue = (JRSubreportParameter)selection.getFirstElement();
					int index = value.indexOf(selectedValue);
					value.remove(index);
					tableViewer.refresh();
				}
			}
		});
		
		deleteButton.setEnabled(false);
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);

		Button bMaster = new Button(bGroup, SWT.PUSH);
		bMaster.setText("Copy From Master");
		bMaster.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperReportsConfiguration jrConfig = (JasperReportsConfiguration) getSettings().get(JSSWizard.JASPERREPORTS_CONFIGURATION);
				if (jrConfig == null) return;
				JasperDesign jd = jrConfig.getJasperDesign();
				if (jd == null) return;
				if (value == null)
					value = new ArrayList<JRSubreportParameter>();
				for (JRParameter prm : jd.getParametersList()) {
					if (prm.isSystemDefined())
						continue;
					String name = prm.getName();
					boolean exists = false;
					for (JRSubreportParameter sp : value) {
						if (sp.getName().equals(name)) {
							exists = true;
							break;
						}
					}
					if (exists)
						return;

					JRDesignSubreportParameter param = new JRDesignSubreportParameter();
					param.setName(name);
					param.setExpression(ExprUtil.createExpression("$P{" + name + "}"));
					value.add(param);
				}
				tableViewer.refresh(true);
			}
		});
	}
	
	/**
	 * Edit an element opened a dialog to allow to modify it
	 * 
	 * @param edited the element to edit, must be not null
	 */
	private void editElement(JRSubreportParameter edited){
		JRSubreportParameter result = (JRSubreportParameter)edited.clone();
		JasperReportsConfiguration jConfig = (JasperReportsConfiguration) getSettings().get(JSSWizard.JASPERREPORTS_CONFIGURATION);
		InputParameterDialog inputDialog = new InputParameterDialog(getShell(), result, jConfig, value);
		if (inputDialog.open() == Dialog.OK){
			int index = value.indexOf(edited);
			value.set(index, result);
			tableViewer.refresh();
		}
	}

	/**
	 * Create the table control
	 * 
	 * @param composite parent of the table
	 */
	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tableViewer);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.common_expression);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		fillTable();
	}

	/**
	 * Attach the listeners and the labels to the table
	 * 
	 * @param viewer the viewer of the table
	 */
	private void attachCellEditors(final TableViewer viewer) {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1){
					JRSubreportParameter selectedElement = value.get(selectedIndex);
					editElement(selectedElement);
				}
			}
		});
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				int selectedIndex = table.getSelectionIndex();
				boolean buttonEnabled = selectedIndex != -1;
				editButton.setEnabled(buttonEnabled);
				deleteButton.setEnabled(buttonEnabled);
			}
		});
		
		viewer.setColumnProperties(new String[] { "NAME", "VALUE" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Set the input of the table to the stored value list
	 */
	private void fillTable() {
		if (value != null) {
			tableViewer.setInput(value);
		}
	}

	@Override
	public void setVisible(boolean visible) {
		loadSettings();
		super.setVisible(visible);
	}

	protected void loadSettings() {
		JRParameter[] parameters = null;
		// load settings, if available..
		if (getSettings() != null && getSettings().containsKey(NewSubreportPage.SUBREPORT_PARAMETERS)) {
			parameters = (JRParameter[]) getSettings().get(NewSubreportPage.SUBREPORT_PARAMETERS);
		}

		if (lastParameters != parameters) {
			lastParameters = parameters;
			List<JRDesignSubreportParameter> sParameters = new ArrayList<JRDesignSubreportParameter>();

			if (lastParameters != null && lastParameters.length > 0) {
				// Create an array of subreport parameters to be used in in the table model...
				for (JRParameter p : lastParameters) {
					if (!p.isSystemDefined()) {
						JRDesignSubreportParameter sp = new JRDesignSubreportParameter();
						sp.setName(p.getName());
						sp.setExpression(new JRDesignExpression());
						sParameters.add(sp);
					}
				}
			}
			setValue(sParameters.toArray(new JRDesignSubreportParameter[sParameters.size()]));
		}
	}

}
