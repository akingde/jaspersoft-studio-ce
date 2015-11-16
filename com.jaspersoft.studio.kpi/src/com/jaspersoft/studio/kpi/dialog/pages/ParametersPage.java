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
package com.jaspersoft.studio.kpi.dialog.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
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

import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.dialog.pages.parameters.ParameterDefinition;
import com.jaspersoft.studio.kpi.dialog.pages.parameters.ParameterWizard;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

/**
 * Configuration page to define the parameters of a KPI. The parameters
 * are keep in sync between the main dataset and the series dataset. Predefined
 * and system parameters can not be edited or accessed trough this page
 * 
 * @author Orlandin Marco
 *
 */
public class ParametersPage extends AbstractKPIConfigurationPage {
	
	/**
	 * List of the reserved parameters name
	 */
	private final static HashSet<String> defaultParameters = new HashSet<String>();
	
	static{
		defaultParameters.add(RangePage.RANGE_PARAMETER);
		defaultParameters.add(RangePage.TYPE_PARAMETER);
		defaultParameters.add(WidgetPage.WIDGET_PARAMETER);
		defaultParameters.add(TitlePage.TITLE_PARAMETER);
		defaultParameters.add(ValuePage.TARGET_FORMATTED_PARAMETER);
		defaultParameters.add(ValuePage.VALUE_FORMATTED_PARAMETER);
	}
	
	/**
	 * Table where the defined parameters are shown
	 */
	private Table table;
	
	/**
	 * Viewer of the table
	 */
	private TableViewer tableViewer;
	
	/**
	 * List of the parameters currently managed
	 */
	private List<ParameterDefinition> parameters = new ArrayList<ParameterDefinition>();
	
	/**
	 * Button to delete the selected parameter on the table
	 */
	private Button deleteButton;
	
	/**
	 * Button to edit the selected parameter on the table
	 */
	private Button editButton;
	
	/**
	 * Label provider for the parameters, convert a parameter definition into some
	 * strings
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class TLabelProvider extends LabelProvider implements ITableLabelProvider {	

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			ParameterDefinition parameter = (ParameterDefinition) element;
			switch (columnIndex) {
				case 0: return String.valueOf(parameter.getName()); 
				case 1: return String.valueOf(MessagesByKeys.getString(parameter.getType())); 
				case 2: return String.valueOf(parameter.isForPrompt());
				case 3: return MessagesByKeys.getString(parameter.getExpression());
			}
			return ""; //$NON-NLS-1$
		}
	}
	
	/**
	 * Create the page calling the super constructor
	 * 
	 * @param jd a not null JasperDesign
	 */
	public ParametersPage(JasperDesign jd) {
		super(jd);
		Assert.isNotNull(jd);
	}
	
	/**
	 * Update the parameters on the main dataset and on the series dataset to synch them
	 * with the ones stored in the table
	 */
	private void updateParameters(){
		JRDesignDataset seriesDataset = getSeriesDataset();
		JRDesignDataset mainDataset = (JRDesignDataset)jd.getMainDataset();
		JRParameter[] jrParameters = mainDataset.getParameters();
		//Delete the all old parameters, except system and default
		for(JRParameter jrParameter : jrParameters){
			String name = jrParameter.getName();
			if (!jrParameter.isSystemDefined() && !defaultParameters.contains(name)){
				mainDataset.removeParameter(jrParameter.getName());
				seriesDataset.removeParameter(jrParameter.getName());
			}
		}
		//Add the new ones
		for(ParameterDefinition def : parameters){
			JRDesignParameter mainParameter = new JRDesignParameter();
			mainParameter.setName(def.getName());
			mainParameter.setValueClassName(ParameterDefinition.getParameterJavaType(def.getType()));
			if (def.getExpression() != null && !def.getExpression().trim().isEmpty()){
				JRDesignExpression expression = new JRDesignExpression(def.getExpression());
				mainParameter.setDefaultValueExpression(expression);
			}
			JRDesignParameter seriesParameter = new JRDesignParameter();
			seriesParameter.setName(def.getName());
			seriesParameter.setValueClassName(ParameterDefinition.getParameterJavaType(def.getType()));
			seriesParameter.setForPrompting(def.isForPrompt());
			try{
				seriesDataset.addParameter(mainParameter);
				mainDataset.addParameter(seriesParameter);
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Create the table control and its viewer
	 * 
	 * @param composite parent of the table
	 */
	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 300;
		gd.widthHint = 700;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				int selectionIndex = table.getSelectionIndex();
				deleteButton.setEnabled(selectionIndex != -1);
				editButton.setEnabled(selectionIndex != -1);
			}
		});
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex!=-1){
					editAction();
				}
			}
		});

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(23));
		tlayout.addColumnData(new ColumnWeightData(15));
		tlayout.addColumnData(new ColumnWeightData(15));
		tlayout.addColumnData(new ColumnWeightData(47));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[4];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.ParametersPage_nameLabel);
		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.ParametersPage_typeLabel);
		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText(Messages.ParametersPage_promptLabel);
		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText(Messages.ParametersPage_valueLabel);
		
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}
	
	/**
	 * Set the content of the table by reading all the non-default non-system parameters
	 * inside the main dataset
	 */
	private void fillTable() {
		JRParameter[] jrParameters = jd.getMainDataset().getParameters();
		parameters.clear();
		if (jrParameters != null){
			for(JRParameter jrParameter : jrParameters){
				String name = jrParameter.getName();
				if (!jrParameter.isSystemDefined() && !defaultParameters.contains(name)){
					String type = ParameterDefinition.getParameterType(jrParameter.getValueClassName());
					String expression = ""; //$NON-NLS-1$
					boolean isFroPrompt = jrParameter.isForPrompting();
					if (jrParameter.getDefaultValueExpression() != null && jrParameter.getDefaultValueExpression().getText() != null){
						expression = jrParameter.getDefaultValueExpression().getText();
					}
					parameters.add(new ParameterDefinition(name, type, expression, isFroPrompt));
				}
			}
		}
		tableViewer.setInput(parameters);
		if (!parameters.isEmpty())
			table.select(0);
	}
	
	/**
	 * Action called to edit a parameter, open the dialog to edit the parameter
	 * selected in the table
	 */
	private void editAction(){
		int index = table.getSelectionIndex();
		ParameterWizard wizard = new ParameterWizard(parameters.get(index), jd);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		if (dialog.open() == Dialog.OK){
			parameters.set(index, wizard.getParameter());
			tableViewer.refresh();
			updateParameters();
		}
	}
	
	/**
	 * Return the series dataset. If not available it is created and returned
	 * 
	 * @return a not null JRDesignDataset
	 */
	private JRDesignDataset getSeriesDataset(){
		JRDataset seriesDataset = jd.getDatasetMap().get(SeriesPage.SERIES_DATASET_NAME);
		if (seriesDataset == null){
			JRDesignDataset newDataset = new JRDesignDataset(false);
			newDataset.setName(SeriesPage.SERIES_DATASET_NAME);
			try {
				jd.addDataset(newDataset);
			} catch (JRException e) {
				e.printStackTrace();
			}
			return newDataset;
		}
		return (JRDesignDataset)seriesDataset;	
	}
	
	@Override
	public String getTitle() {
		return Messages.ParametersPage_pageTitle;
	}
	
	@Override
	public String getName() {
		return Messages.ParametersPage_pageName;
	}

	@Override
	protected Composite createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		GridLayout mainLayout  = new GridLayout(2, false);
		mainLayout.marginHeight = 0;
		composite.setLayout(mainLayout);

		//Container for the table
		
		Composite tGroup = new Composite(composite, SWT.NONE);
		GridLayout tGroupLayout = new GridLayout(1,false);
		tGroupLayout.marginWidth = 0;
		tGroupLayout.marginHeight = 0;
		tGroupLayout.verticalSpacing = 10;
		tGroup.setLayout(tGroupLayout);
		tGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//Build the table
		
		buildTable(tGroup);
		
		//Build the buttons
		
		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(composite.getBackground());
		
		Button addButton = new Button(bGroup, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParameterWizard wizard = new ParameterWizard(jd);
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				if (dialog.open() == Dialog.OK){
					parameters.add(wizard.getParameter());
					tableViewer.refresh();
					updateParameters();
				}
			}
		});
		
		editButton = new Button(bGroup, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				editAction();
			}
		});
		editButton.setEnabled(false);
		
		deleteButton = new Button(bGroup, SWT.PUSH);
		deleteButton.setText(Messages.common_delete);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = table.getSelectionIndex();
				parameters.remove(index);
				tableViewer.refresh();
				updateParameters();
			}
		});
		deleteButton.setEnabled(false);
		
		fillTable();
		return composite;
	}
}
