package com.jaspersoft.studio.data.adapters.ui;


import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.jaspersoft.studio.data.adapters.DataAdapterProperty;
import org.eclipse.swt.layout.GridData;


public class BasicDataAdapterComposite extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;
	
	private List<DataAdapterProperty> properties;
  
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BasicDataAdapterComposite(Composite parent, int style, List<DataAdapterProperty> properties) {
		super(parent, SWT.NONE);
		
	 
		this.properties = properties;
		
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});

		
   	toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new GridLayout(1, false));
		
		Label lblDataAdapterProperties = new Label(this, SWT.NONE);
		toolkit.adapt(lblDataAdapterProperties, true, true);
		lblDataAdapterProperties.setText("Data Adapter properties");
		
		TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		
		// Bind data...
		tableViewer.setContentProvider( new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		tableViewer.setInput(this.properties);
		
		// set columns
	  // First column is for the first name
		TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DataAdapterProperty p = (DataAdapterProperty) element;
				return p.getName();
			}
		});
		nameColumn.getColumn().setResizable(true);
		nameColumn.getColumn().setText("Name");
		

		// Second column is for the last name
		TableViewerColumn valueColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DataAdapterProperty p = (DataAdapterProperty) element;
				return p.getValue();
			}
		});
		valueColumn.getColumn().setResizable(true);
		valueColumn.getColumn().setText("Value");

		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(50));
		tableLayout.addColumnData(new ColumnWeightData(50));
    table.setLayout(tableLayout);
    
		toolkit.paintBordersFor(table);
		
		Composite composite = new Composite(this, SWT.NONE);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		this.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		this.setSize(500, 500);
  }
	
}
