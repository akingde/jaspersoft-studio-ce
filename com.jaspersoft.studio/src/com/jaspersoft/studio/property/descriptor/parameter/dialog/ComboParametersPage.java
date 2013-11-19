/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;

/**
 * Page used to add a parameter to a dataset run. The parameter can only be choosed between
 * the parameters already defined for dataset referenced by the dataset run. This avoid to have
 * a dataset run parameter valorization of a not existing parameter 
 * 
 * @author Orlandin Marco
 *
 */
public class ComboParametersPage extends WizardPage implements IExpressionContextSetter {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((JRDatasetParameter) element).getName();
			case 1:
				JRDatasetParameter value2 = (JRDatasetParameter) element;
				if (value2 != null && value2.getExpression() != null)
					return value2.getExpression().getText();
			}
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Parameters of the current dataset run
	 */
	private ParameterDTO value;
	
	/**
	 * Table where the user can select the parameter of the dataset run and their expression
	 */
	private Table table;
	
	/**
	 * Viewer of the table
	 */
	private TableViewer tableViewer;
	
	/**
	 * Actual expression context
	 */
	private ExpressionContext expContext;
	
	/**
	 * Viewer for the combo where the user can select the value of a single datasetrun parameter.
	 * The combo is filled with the names of the parameters already present in the original dataset (the 
	 * one referenced by the datasetrun), excluded the ones that has been already added into this dataset run
	 */
	private ComboBoxViewerCellEditor nameCellEditor ;
	
	/**
	 * Parameters inside the original dataset referenced by the dataset run
	 */
	private JRParameter[] datasetParameters;

	/**
	 * Create an instance of the pace
	 * @param pageName
	 */
	protected ComboParametersPage(String pageName) {
		super(pageName);
		setTitle(Messages.ParameterPage_dataset_parameters);
		setDescription(Messages.ParameterPage_description);
	}
	
	/**
	 * Return all the parameters that should be inside the dataset run
	 * 
	 * @return a parametersDTO containing ALL the parameters that should be inside 
	 * the dataset run
	 */
	public ParameterDTO getValue() {
		return value;
	}

	/**
	 * When the dialog is disposed the return value is update
	 */
	@Override
	public void dispose() {
		value = new ParameterDTO();
		value.setJasperDesign(value.getJasperDesign());
		List<JRDatasetParameter> list = (List<JRDatasetParameter>) tableViewer.getInput();
		List<JRDatasetParameter> returnValues = new ArrayList<JRDatasetParameter>();
		for(JRDatasetParameter param : list){
			if (param.getName() != null && !param.getName().isEmpty()) returnValues.add(param);
		}
		value.setValue(returnValues.toArray(new JRDatasetParameter[returnValues.size()]));
		super.dispose();
	}

	/**
	 * Set the valued edited by this dialog
	 * 
	 * @param value a ParametersDTO (not null) of the parameters list that the user manipulate  
	 * @param datasetRun the (not null) dataset run from where the value is extracted
	 */
	public void setValue(ParameterDTO value, MDatasetRun datasetRun) {
		this.value = value;
		if (value == null) {
			value = new ParameterDTO();
		}
		//get the dataset referenced by the dataset run
		JRDataset dataset = datasetRun.getJasperDesign().getDatasetMap().get(datasetRun.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME));
		if (dataset != null) {
			List<JRParameter> userParameters = new ArrayList<JRParameter>();
			for(JRParameter param : dataset.getParameters()){
				if (!param.isSystemDefined()) userParameters.add(param);
			}
			//the original dataset parameters are cached
			datasetParameters = userParameters.toArray(new JRParameter[userParameters.size()]);
		}
		else datasetParameters = new JRDesignParameter[0];
		if (table != null)
			fillTable(table);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalSpan = 2;
		gd.heightHint = 400;
		gd.widthHint = 600;
		table.setLayoutData(gd);
	}

	/**
	 * Create the table control and add to it its viewer
	 * @param composite container of the table
	 */
	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		table.setToolTipText(""); //$NON-NLS-1$
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);

		setColumnToolTip();

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.ParameterPage_parameter);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.common_expression);

		fillTable(table);
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}
		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof TableItem) {
					setMessage(getDescription(((TableItem) e.item)));
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	/**
	 * @param tableViewer
	 * @param cursor
	 */
	/*static void editCell(final TableViewer tableViewer, final TableCursor cursor) {
		tableViewer.editElement(cursor.getRow().getData(), cursor.getColumn());
		// hide cursor only f there is an editor active on the cell
		cursor.setVisible(!tableViewer.isCellEditorActive());
	}*/

	/**
	 * Add to the table a content provider that simply convert a list of elements to an array
	 * 
	 * @param viewer the table viewer
	 */
	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				return ((List<?>) inputElement).toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			}
		});
	}
	
	/**
	 * Add to the table a label provider, that assuming the table input is a JRDatasetParameter, 
	 * since the table has two columns return for the first one the name of the parameter and 
	 * for the second one its expression
	 * 
	 * @param viewer
	 */
	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TLabelProvider());
	}

	/**
	 * Add the cell editor to the table, a combo cell editor for the column where the user can
	 * select the parameter name, and and expression cell editor where the user can specify the expression
	 * 
	 * @param viewer viewer of the table
	 * @param parent the table
	 */
	//deprecation due to the eclipse 3.6 support
	@SuppressWarnings("deprecation")
	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("VALUE")) //$NON-NLS-1$
					return true;
				if (property.equals("NAME")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRDatasetParameter prop = (JRDatasetParameter) element;
				if ("VALUE".equals(property)) //$NON-NLS-1$
					if (prop.getExpression() != null)
						return prop.getExpression();
				if ("NAME".equals(property)) { //$NON-NLS-1$
					return prop.getName();
				}
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setMessage(getDescription(tableItem));
				JRDesignDatasetParameter data = (JRDesignDatasetParameter) tableItem.getData();
				if ("VALUE".equals(property)) { //$NON-NLS-1$
					if (value instanceof JRExpression) {
						data.setExpression((JRExpression) value);
					}
				}
				if ("NAME".equals(property) && value != null) { //$NON-NLS-1$
					data.setName((String) value);
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
				//When a parameter value change the combo viewer need to update its available value
				nameCellEditor.setInput(createNameComboInput());
			}
		});

		JRExpressionCellEditor exprCellEditor = new JRExpressionCellEditor(parent, expContext);
		nameCellEditor = new ComboBoxViewerCellEditor(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		nameCellEditor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
		//set a default label provider that take as content an array of string and simply print them
		nameCellEditor.setLabelProvider( new LabelProvider() );
		nameCellEditor.setContenProvider(new ComboContentProvider());
		viewer.setCellEditors(new CellEditor[] { nameCellEditor, exprCellEditor });
		viewer.setColumnProperties(new String[] { "NAME", "VALUE" }); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * Simple content provider for the combo, assume that the input is an array of 
	 * string so return it converted
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ComboContentProvider implements IStructuredContentProvider
	{
		@Override
		public Object[] getElements(Object inputElement)
		{
			Object[] result = null;
			if( inputElement instanceof String[] )
			{
				result = (String[])inputElement; 
			}
			return result;
		}

		@Override
		public void dispose()
		{
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
		}
	}
	
	/**
	 * Return the input of the combo, a list of the parameter name of the original dataset
	 * not already used by other parameters of the dataset run, plus a void element on the top
	 * of the list to remove a parameter from a dataset run
	 * 
	 * @return the list of string displayed in the combo
	 */
	private String[] createNameComboInput(){
		List<String> result = new ArrayList<String>();
		List<JRDatasetParameter> list = (List<JRDatasetParameter>) tableViewer.getInput();
		HashSet<String> usedParams = new HashSet<String>();
		if (list != null){
			for(JRDatasetParameter param : list)
				usedParams.add(param.getName());
		}
		for (JRParameter param : datasetParameters){
			if (!usedParams.contains(param.getName())){
					result.add(param.getName());
			}
		}
		Collections.sort(result);
		result.add(0, "");
		return result.toArray(new String[result.size()]);
	}

	/**
	 * Set the input  of the table and of the combo viewer. On the table will be available a number of 
	 * rows equals to the number of parameters that the user can select
	 * 
	 * @param table the table 
	 */
	private void fillTable(Table table) {
		List<JRDatasetParameter> lst = new ArrayList<JRDatasetParameter>(Arrays.asList(value.getValue()));
		List<JRDatasetParameter> input = new ArrayList<JRDatasetParameter>();
		for(JRDatasetParameter param : lst){
			JRDesignDatasetParameter newParam = new JRDesignDatasetParameter();
			newParam.setExpression(param.getExpression() != null ? (JRExpression)param.getExpression().clone() : null);
			newParam.setName(param.getName());
			input.add(newParam);
		}

		int missingItems =datasetParameters.length-input.size();
		for(int i=0; i<missingItems;i++){
			JRDesignDatasetParameter newParam = new JRDesignDatasetParameter();
			newParam.setExpression(new JRDesignExpression());
			newParam.setName("");
			input.add(newParam);
		}
		Collections.sort(input, new Comparator<JRDatasetParameter>() {

			@Override
			public int compare(JRDatasetParameter o1, JRDatasetParameter o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		tableViewer.setInput(input);
		nameCellEditor.setInput(createNameComboInput());
	}

	private void setColumnToolTip() {
		final Listener labelListener = new Listener() {
			public void handleEvent(Event event) {
				Label label = (Label) event.widget;
				Shell shell = label.getShell();
				switch (event.type) {
				case SWT.MouseDown:
					Event e = new Event();
					e.item = (TableItem) label.getData("_TABLEITEM"); //$NON-NLS-1$
					// Assuming table is single select, set the selection as if
					// the mouse down event went through to the table
					table.setSelection(new TableItem[] { (TableItem) e.item });
					table.notifyListeners(SWT.Selection, e);
					// fall through
				case SWT.MouseExit:
					shell.dispose();
					break;
				}
			}
		};

		Listener tableListener = new Listener() {
			Shell tip = null;

			Label label = null;

			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Dispose:
				case SWT.KeyDown:
				case SWT.MouseMove: {
					if (tip == null)
						break;
					tip.dispose();
					tip = null;
					label = null;
					break;
				}
				case SWT.MouseHover: {
					TableItem item = table.getItem(new Point(event.x, event.y));
					String description = getDescription(item);
					if (item != null && !description.equals("")) { //$NON-NLS-1$

						if (tip != null && !tip.isDisposed())
							tip.dispose();
						tip = new Shell(table.getShell(), SWT.ON_TOP | SWT.TOOL);
						tip.setLayout(new FillLayout());
						label = new Label(tip, SWT.NONE);
						label.setForeground(table.getShell().getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
						label.setBackground(table.getShell().getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
						label.setData("_TABLEITEM", item); //$NON-NLS-1$

						label.setText(description);
						label.addListener(SWT.MouseExit, labelListener);
						label.addListener(SWT.MouseDown, labelListener);
						Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
						Rectangle rect = item.getBounds(0);
						Point pt = table.toDisplay(rect.x, rect.y);
						tip.setBounds(pt.x, pt.y, size.x, size.y);
						tip.setVisible(true);
					}
				}
				}
			}
		};
		table.addListener(SWT.Dispose, tableListener);
		table.addListener(SWT.KeyDown, tableListener);
		table.addListener(SWT.MouseMove, tableListener);
		table.addListener(SWT.MouseHover, tableListener);
	}

	private String getDescription(TableItem item) {
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Set the expression context
	 */
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
}
