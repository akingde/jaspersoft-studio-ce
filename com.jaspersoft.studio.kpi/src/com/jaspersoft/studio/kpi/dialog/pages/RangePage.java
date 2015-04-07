package com.jaspersoft.studio.kpi.dialog.pages;

import groovy.lang.GroovyShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;

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

public class RangePage extends AbstractKPIConfigurationPage {

	public static final String RANGE_PARAMETER = "tresholds"; //$NON-NLS-1$
	
	public static final String TYPE_PARAMETER = "tresholdtype"; //$NON-NLS-1$
	
	private static final String KEY_MIN = "min"; //$NON-NLS-1$
	
	private static final String KEY_MAX = "max"; //$NON-NLS-1$
	
	private static final String KEY_NAME = "color"; //$NON-NLS-1$
	
	private static final String ENTRY = "[\"min\":{0}, \"max\": {1}, \"color\": \"{2}\"]"; //$NON-NLS-1$
	
	private Table table;
	
	private TableViewer tableViewer;
	
	private List<RangeDefinition> ranges = new ArrayList<RangeDefinition>();
	
	private Button deleteButton;
	
	private Button editButton;
	
	private class TLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {
		

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			RangeDefinition dto = (RangeDefinition) element;
			switch (columnIndex) {
				case 0: return String.valueOf(dto.getMin()); 
				case 1: return String.valueOf(dto.getMax()); 
				case 2: return MessagesByKeys.getString(dto.getType());
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
	
	@Override
	public String getName() {
		return Messages.RangePage_pageName;
	}
	
	private boolean isPercentage(){
		JRDesignParameter parameter = getParameter(TYPE_PARAMETER);
		if (parameter.getDefaultValueExpression() != null){
			String text = parameter.getDefaultValueExpression().getText();
			return text.equals("\"percentage\""); //$NON-NLS-1$
		}
		return false;
	}
	
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
	
	private void fillTable() {
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
		tableViewer.setInput(ranges);
		if (!ranges.isEmpty())
			table.select(0);
	}

	@Override
	public String getTitle() {
		return Messages.RangePage_pageTitle;
	}
}
