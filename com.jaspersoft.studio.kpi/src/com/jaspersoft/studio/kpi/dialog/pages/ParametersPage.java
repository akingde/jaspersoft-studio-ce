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
import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

public class ParametersPage extends AbstractKPIConfigurationPage {
	
	private final static HashSet<String> defaultParameters = new HashSet<String>();
	
	static{
		defaultParameters.add(RangePage.RANGE_PARAMETER);
		defaultParameters.add(RangePage.TYPE_PARAMETER);
		defaultParameters.add(WidgetPage.WIDGET_PARAMETER);
		defaultParameters.add(TitlePage.TITLE_PARAMETER);
	}
	
	private Table table;
	
	private TableViewer tableViewer;
	
	private List<ParameterDefinition> parameters = new ArrayList<ParameterDefinition>();
	
	private Button deleteButton;
	
	private Button editButton;
	
	private class TLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {
		

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			ParameterDefinition dto = (ParameterDefinition) element;
			switch (columnIndex) {
				case 0: return String.valueOf(dto.getName()); 
				case 1: return String.valueOf(MessagesByKeys.getString(dto.getType())); 
				case 2: return MessagesByKeys.getString(dto.getExpression());
			}
			return ""; //$NON-NLS-1$
		}

		@Override
		public org.eclipse.swt.graphics.Color getForeground(Object element,int columnIndex) {
			return null;
		}

		@Override
		public org.eclipse.swt.graphics.Color getBackground(Object element,int columnIndex) {
			return null;
		}
	}
	
	@Override
	public String getName() {
		return "Parameters";
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
				parameters.remove(index);
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
		ParameterWizard wizard = new ParameterWizard(parameters.get(index), jd);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		if (dialog.open() == Dialog.OK){
			parameters.set(index, wizard.getParameter());
			tableViewer.refresh();
			updateVariable();
		}
	}
	
	private void updateVariable(){
		JRDesignDataset seriesDataset = getSeriesDataset();
		JRDesignDataset mainDataset = (JRDesignDataset)jd.getMainDataset();
		JRParameter[] jrParameters = mainDataset.getParameters();
		for(JRParameter jrParameter : jrParameters){
			String name = jrParameter.getName();
			if (!jrParameter.isSystemDefined() && !defaultParameters.contains(name)){
				mainDataset.removeParameter(jrParameter.getName());
				seriesDataset.removeParameter(jrParameter.getName());
			}
		}
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
			try{
				seriesDataset.addParameter(mainParameter);
				mainDataset.addParameter(seriesParameter);
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
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
		tlayout.addColumnData(new ColumnWeightData(25));
		tlayout.addColumnData(new ColumnWeightData(15));
		tlayout.addColumnData(new ColumnWeightData(60));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[3];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name");
		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Type");
		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText("Expression");
		
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}
	
	private void fillTable() {
		JRParameter[] jrParameters = jd.getMainDataset().getParameters();
		parameters.clear();
		if (jrParameters != null){
			for(JRParameter jrParameter : jrParameters){
				String name = jrParameter.getName();
				if (!jrParameter.isSystemDefined() && !defaultParameters.contains(name)){
					String type = ParameterDefinition.getParameterType(jrParameter.getValueClassName());
					String expression = "";
					if (jrParameter.getDefaultValueExpression() != null && jrParameter.getDefaultValueExpression().getText() != null){
						expression = jrParameter.getDefaultValueExpression().getText();
					}
					parameters.add(new ParameterDefinition(name, type, expression));
				}
			}
		}
		tableViewer.setInput(parameters);
		if (!parameters.isEmpty())
			table.select(0);
	}
	
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
}
