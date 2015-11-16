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

import groovy.lang.GroovyShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.ibm.icu.text.MessageFormat;
import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.dialog.pages.range.RangeDefinition;
import com.jaspersoft.studio.kpi.dialog.pages.range.RangeWizard;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

/**
 * Page where the ranges of the KPI can be defined. All the ranges are stored
 * inside a single parameter expression, like a static list of hashmap. Each map
 * represent a range and it contains three keys for the lower bound, upper bound and 
 * its style (bad, good, normal)
 * 
 * @author Orlandin Marco
 *
 */
public class RangePage extends AbstractKPIConfigurationPage {

	/**
	 * Parameter where the defined ranges are stored as default value expression
	 */
	public static final String RANGE_PARAMETER = "tresholds"; //$NON-NLS-1$
	
	/**
	 * Parameter where the type of the ranges are stored as default value expression. The 
	 * types are defined by statics identifier string, like absolute, percentage...
	 */
	public static final String TYPE_PARAMETER = "tresholdtype"; //$NON-NLS-1$
	
	/**
	 * Key inside of a range map for the lower bound
	 */
	private static final String KEY_MIN = "min"; //$NON-NLS-1$
	
	/**
	 * Key inside of a range map for the upper bound
	 */
	private static final String KEY_MAX = "max"; //$NON-NLS-1$
	
	/**
	 * Key inside of a range map for the type
	 */
	private static final String KEY_NAME = "color"; //$NON-NLS-1$
	
	/**
	 * Groove code to generate a range hashmap. The value of each key is easily substituted
	 * Trough the MessageFormat library
	 */
	private static final String ENTRY = "[\""+KEY_MIN+"\":{0}, \""+KEY_MAX+"\": {1}, \""+KEY_NAME+"\": \"{2}\"]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	/**
	 * The table where the defined ranges are shown
	 */
	private Table table;
	
	/**
	 * The viewer for the table
	 */
	private TableViewer tableViewer;
	
	/**
	 * All the ranges defined in the current KPI
	 */
	private List<RangeDefinition> ranges = new ArrayList<RangeDefinition>();
	
	/**
	 * Button to delete a range
	 */
	private Button deleteButton;
	
	/**
	 * Button to edit a range
	 */
	private Button editButton;
	
	/**
	 * Label provider for the table, to convert a range definition into a set of strings
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class TLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {
		

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			RangeDefinition range = (RangeDefinition) element;
			switch (columnIndex) {
				case 0: return String.valueOf(range.getMin()); 
				case 1: return String.valueOf(range.getMax()); 
				case 2: return MessagesByKeys.getString(range.getType());
			}
			return ""; //$NON-NLS-1$
		}

		@Override
		public Color getForeground(Object element,int columnIndex) {
			/*if (columnIndex == 2){
				RangeDefinition dto = (RangeDefinition) element;
				Color awtColor = Color.decode(dto.getColor());
				return ResourceManager.getColor(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
			}*/
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}
	}
	
	//CODE FOR THE PERCACHING OF RANGES: since the read of ranges can require some times
	//a job is started as soon is possible to preload them
	
	/**
	 * Enumeration to represent the status of the ranges preload job
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private enum JOB_STATE {NOT_STARTED, RUNNING, ENDED};
	
	/**
	 * The current status of the ranges preload job
	 */
	private JOB_STATE state = JOB_STATE.NOT_STARTED;
	
	/**
	 * Job to preload the ranges
	 */
	private Job fetchRangesJob = new Job("Preload Ranges") { //$NON-NLS-1$
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			try{
				synchronized (RangePage.this) {
					state = JOB_STATE.RUNNING;
				}
				monitor.setTaskName("Preload Ranges"); //$NON-NLS-1$
				loadData();
				monitor.done();
			} catch(Exception ex){
				ex.printStackTrace();
			} finally {
				synchronized (RangePage.this) {
					state = JOB_STATE.ENDED;
				}
			}
			return Status.OK_STATUS;
		}
	};
	
	/**
	 * Create the page calling the super constructor
	 * 
	 * @param jd a not null JasperDesign
	 */
	public RangePage(JasperDesign jd) {
		super(jd);
		Assert.isNotNull(jd);
		fetchRangesJob.setPriority(Job.SHORT);
		fetchRangesJob.schedule();
	}
	
	/**
	 * Read all the ranges inside the the parameter and decode them.
	 * They are stored as groovy code that can be executed to generate a list
	 * of hashmaps. Each hashmap is a range. After getting this maps they are converted
	 * into a more usable list of range definition
	 */
	private void loadData(){
		JRExpression exp = getParameter(RANGE_PARAMETER).getDefaultValueExpression();
		ranges.clear();
		if (exp != null && exp.getText() != null){
			List<?> readValues = null;
			try{
				GroovyShell interpreter = new GroovyShell(); 
				readValues = (List<?>)interpreter.evaluate(exp.getText());
			}catch(Exception ex){
				ex.printStackTrace();
				readValues = new ArrayList<Object>();
			}
			for(Object obj : readValues){
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String,Object>)obj;
				Integer min = (Integer)map.get(KEY_MIN);
				Integer max = (Integer)map.get(KEY_MAX);
				String name = (String)map.get(KEY_NAME);
				ranges.add(new RangeDefinition(min, max, name));
			}
			
		}
	}
	
	/**
	 * Check if the type of the ranges is percentage
	 * 
	 * @return true if the ranges must be handled as percentages, false otherwise
	 */
	private boolean isPercentage(){
		JRDesignParameter parameter = getParameter(TYPE_PARAMETER);
		if (parameter.getDefaultValueExpression() != null){
			String text = parameter.getDefaultValueExpression().getText();
			return text.equals("\"percentage\""); //$NON-NLS-1$
		}
		return false;
	}
	
	/**
	 * Set the type of the ranges between absolute or percentage
	 * 
	 * @param isPercentage true if the ranges are percentage or absolute values
	 */
	private void setType(boolean isPercentage){
		JRDesignParameter parameter = getParameter(TYPE_PARAMETER);
		JRDesignExpression exp = null;
		if (isPercentage){
			exp = new JRDesignExpression("\"percentage\""); //$NON-NLS-1$
		} else {
			exp = new JRDesignExpression("\"absolute\""); //$NON-NLS-1$
		}
		parameter.setDefaultValueExpression(exp);
	}
	
	/**
	 * Action called to edit a range. It take the selected range from the table
	 * and open a dialog to modify it
	 */
	private void editAction(){
		int index = table.getSelectionIndex();
		RangeWizard wizard = new RangeWizard(ranges.get(index));
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		if (dialog.open() == Dialog.OK){
			ranges.set(index, wizard.getRange());
			tableViewer.refresh();
			updateVariable();
		}
	}
	
	/**
	 * Rebuild the parameters value of the range, and saving into it all the defined ranges
	 */
	private void updateVariable(){
		StringBuilder builder = new StringBuilder();
		builder.append("["); //$NON-NLS-1$
		int index = 0;
		for(RangeDefinition def : ranges){
			builder.append(MessageFormat.format(ENTRY, new Object[]{def.getMin(), def.getMax(), def.getType()}));
			index++;
			if (index < ranges.size()) builder.append(","); //$NON-NLS-1$
		}
		builder.append("]"); //$NON-NLS-1$
		JRDesignParameter parameter = getParameter(RANGE_PARAMETER);
		JRDesignExpression expression = new JRDesignExpression(builder.toString());
		parameter.setDefaultValueExpression(expression);
	}
	
	/**
	 * Create the table controls and it's viewer
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
		tlayout.addColumnData(new ColumnWeightData(30));
		tlayout.addColumnData(new ColumnWeightData(30));
		tlayout.addColumnData(new ColumnWeightData(40));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[3];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.RangePage_fromLabel);
		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.RangePage_toLabel);
		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText(Messages.RangePage_typeLabel);
		
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}
	
	/**
	 * Wait until the job to load the ranges has finished, then set 
	 * the result as input of the table
	 */
	private void fillTable() {
		synchronized (this) {
			while(!state.equals(JOB_STATE.ENDED)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		tableViewer.setInput(ranges);
		if (!ranges.isEmpty())
			table.select(0);
	}

	@Override
	public String getTitle() {
		return Messages.RangePage_pageTitle;
	}
	
	@Override
	public String getName() {
		return Messages.RangePage_pageName;
	}
	
	@Override
	protected Composite createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		GridLayout mainLayout  = new GridLayout(2, false);
		mainLayout.marginHeight = 0;
		composite.setLayout(mainLayout);

		//Container for the table and checkbox
		
		Composite tGroup = new Composite(composite, SWT.NONE);
		GridLayout tGroupLayout = new GridLayout(1,false);
		tGroupLayout.marginWidth = 0;
		tGroupLayout.marginHeight = 0;
		tGroupLayout.verticalSpacing = 10;
		tGroup.setLayout(tGroupLayout);
		tGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//Build the table
		
		buildTable(tGroup);

		//Build the checkbox
		
		Button percentageButton = new Button(tGroup, SWT.CHECK);
		percentageButton.setText(Messages.RangePage_percentageButton);
		percentageButton.setSelection(isPercentage());
		percentageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setType(((Button)e.widget).getSelection());
			}
		});
		
		//Create the new, edit and delete buttons
		
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
				RangeWizard wizard = new RangeWizard();
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				if (dialog.open() == Dialog.OK){
					ranges.add(wizard.getRange());
					tableViewer.refresh();
					updateVariable();
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
				ranges.remove(index);
				tableViewer.refresh();
				updateVariable();
			}
		});
		deleteButton.setEnabled(false);
		
		fillTable();
		return composite;
	}
}
