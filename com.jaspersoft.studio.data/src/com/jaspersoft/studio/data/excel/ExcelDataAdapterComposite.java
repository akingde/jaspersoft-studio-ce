/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.AFileDataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DateNumberFormatWidget;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.events.ChangeEvent;
import com.jaspersoft.studio.swt.events.ChangeListener;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.data.excel.ExcelDataAdapter;
import net.sf.jasperreports.data.excel.ExcelDataAdapterImpl;
import net.sf.jasperreports.data.excel.ExcelFormatEnum;
import net.sf.jasperreports.data.xls.XlsDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JasperDesign;

public class ExcelDataAdapterComposite extends AFileDataAdapterComposite {

	private Text textSheetSelection;
	private TableViewer tableViewer;
	private Table table;
	private TableViewerColumn tableViewerColumnName;
	private TableViewerColumn tableViewerColumnIndex;
	private Button btnAdd;
	private Button btnDelete;
	private Button btnCheckSkipFirstLine;
	private Button btnCheckQEMode;

	private DateNumberFormatWidget dnf;

	// The data model
	private java.util.List<String[]> rows;

	/**
	 * Temp. JR configuration used only to get the fields from a fake design. It
	 * is disposed at the end
	 */
	private JasperReportsConfiguration jConfig;

	private Combo format;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ExcelDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {

		/*
		 * UI ELEMENTS
		 */
		super(parent, style, jrContext);
		setLayout(new GridLayout(1, false));

		// data model init
		rows = new ArrayList<String[]>();

		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		createFileNameWidgets(composite);

		btnCheckQEMode = new Button(this, SWT.CHECK);
		btnCheckQEMode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnCheckQEMode.setText(Messages.XLSDataAdapterComposite_2);

		Composite cmp = new Composite(this, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE).setText("Excel Format");

		format = new Combo(cmp, SWT.READ_ONLY);
		format.setItems(new String[] { ExcelFormatEnum.AUTODETECT.getName(), ExcelFormatEnum.XLS.getName(),
				ExcelFormatEnum.XLSX.getName() });

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.VERTICAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

		Group grpColumnNames = new Group(composite_1, SWT.NONE);
		grpColumnNames.setText(Messages.XLSDataAdapterComposite_3);
		GridLayout gl_grpColumnNames = new GridLayout(1, false);
		grpColumnNames.setLayout(gl_grpColumnNames);

		Button btnGetExcelColumnsName = new Button(grpColumnNames, SWT.NONE);
		btnGetExcelColumnsName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnGetExcelColumnsName.setText(Messages.XLSDataAdapterComposite_4);

		Composite composite_3 = new Composite(grpColumnNames, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		GridData gdComposite3 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gdComposite3.heightHint = 150;
		composite_3.setLayoutData(gdComposite3);

		table = new Table(composite_3, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);

		// TableLayout tlayout = new TableLayout();
		// tlayout.addColumnData(new ColumnWeightData(100, false));
		// table.setLayout(tlayout);

		tableViewer = new TableViewer(table);

		// tableViewer = new TableViewer(composite_3,
		// SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL |
		// SWT.H_SCROLL);
		tableViewer.setContentProvider(new XLSContentProvider());
		tableViewer.setInput(rows);

		// table = tableViewer.getTable();
		// table.setLayoutData(new GridData(GridData.FILL_BOTH));
		// table.setLinesVisible(true);
		// table.setHeaderVisible(true);

		tableViewerColumnName = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnColumnName = tableViewerColumnName.getColumn();
		tblclmnColumnName.setMoveable(true);
		tblclmnColumnName.setWidth(100);
		tblclmnColumnName.setText(Messages.XLSDataAdapterComposite_5);
		tableViewerColumnName.setLabelProvider(new ColumnNameIndexLabelProvider(0));
		tableViewerColumnName.setEditingSupport(new NameIndexEditingSupport(tableViewer, 0));

		tableViewerColumnIndex = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnColumnIndex = tableViewerColumnIndex.getColumn();
		tblclmnColumnIndex.setMoveable(true);
		tblclmnColumnIndex.setWidth(100);
		tblclmnColumnIndex.setText(Messages.XLSDataAdapterComposite_6);
		tableViewerColumnIndex.setLabelProvider(new ColumnNameIndexLabelProvider(1));
		tableViewerColumnIndex.setEditingSupport(new NameIndexEditingSupport(tableViewer, 1));

		// for (int i = 0, n = table.getColumnCount(); i < n; i++) {
		// table.getColumn(i).pack();
		// }

		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		GridLayout gl_composite_4 = new GridLayout(1, false);
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);

		btnAdd = new Button(composite_4, SWT.NONE);
		GridData gd_btnAdd = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.widthHint = 100;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText(Messages.XLSDataAdapterComposite_7);

		btnDelete = new Button(composite_4, SWT.NONE);
		GridData gd_btnDelete = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnDelete.widthHint = 100;
		btnDelete.setLayoutData(gd_btnDelete);
		btnDelete.setText(Messages.XLSDataAdapterComposite_8);
		btnDelete.setEnabled(false);

		ListOrderButtons listOrderButtons = new ListOrderButtons();
		listOrderButtons.createOrderButtons(composite_4, tableViewer);
		listOrderButtons.addChangeListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event) {
				pchangesuport.firePropertyChange("dirty", false, true);
			}
		});

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpOther = new Group(composite_2, SWT.NONE);
		grpOther.setText(Messages.XLSDataAdapterComposite_9);
		GridLayout gl_grpOther = new GridLayout(3, false);
		grpOther.setLayout(gl_grpOther);

		dnf = new DateNumberFormatWidget(grpOther);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		dnf.setLayoutData(gd);

		btnCheckSkipFirstLine = new Button(grpOther, SWT.CHECK);
		btnCheckSkipFirstLine.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		btnCheckSkipFirstLine.setText(Messages.XLSDataAdapterComposite_14);

		new Label(grpOther, SWT.NONE).setText("Sheet Selection");

		textSheetSelection = new Text(grpOther, SWT.BORDER);
		textSheetSelection.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		// get Excel file columns
		btnGetExcelColumnsName.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					getExcelColumns();
					pchangesuport.firePropertyChange("dirty", false, true);
				} catch (Exception e1) {
					UIUtils.showError(e1);
				}
			}
		});

		// add an entry and set selection on it
		btnAdd.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				rows.add(createDataModelEntry());

				// tableViewer.addPostSelectionChangedListener can't cover
				// the first row added, so we need to manually set delete
				// button enabled for this case.
				if (rows.size() == 1) {
					btnDelete.setEnabled(true);
				}

				tableViewer.refresh();
				setTableSelection(-1);
				pchangesuport.firePropertyChange("dirty", false, true);
			}
		});

		// delete selected entries and set selection on last table item
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeEntries();
				pchangesuport.firePropertyChange("dirty", false, true);
			}
		});

		// keys listener
		table.addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
				// nothing
			}

			public void keyPressed(KeyEvent e) {

				if (e.character == SWT.DEL) {
					removeEntries();
					pchangesuport.firePropertyChange("dirty", false, true);
				}
			}
		});

		// When no table items,
		// turns disabled the delete button
		// and set unchecked the skip first line button
		tableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if (rows.size() <= 0) {
					btnDelete.setEnabled(false);
					btnCheckSkipFirstLine.setSelection(false);
				} else {
					btnDelete.setEnabled(true);
				}
			}
		});

		format.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (dataAdapterDesc != null) {
					ExcelDataAdapterImpl da = (ExcelDataAdapterImpl) dataAdapterDesc.getDataAdapter();
					switch (format.getSelectionIndex()) {
					case 0:
						da.setFormat(ExcelFormatEnum.AUTODETECT);
						break;
					case 1:
						da.setFormat(ExcelFormatEnum.XLS);
						break;
					case 2:
						da.setFormat(ExcelFormatEnum.XLSX);
						break;
					}
					pchangesuport.firePropertyChange("dirty", false, true);
				}
			}
		});
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		ExcelDataAdapter xlsDataAdapter = (ExcelDataAdapter) dataAdapter;

		doBindFileNameWidget(xlsDataAdapter);
		bindingContext.bindValue(SWTObservables.observeSelection(btnCheckQEMode),
				PojoObservables.observeValue(dataAdapter, "queryExecuterMode")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(btnCheckSkipFirstLine),
				PojoObservables.observeValue(dataAdapter, "useFirstRowAsHeader")); //$NON-NLS-1$

		dnf.bindWidgets(xlsDataAdapter, bindingContext, xlsDataAdapter.getLocale(), xlsDataAdapter.getTimeZone());

		bindingContext.bindValue(SWTObservables.observeText(textSheetSelection, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "sheetSelection")); //$NON-NLS-1$

		List<String> listColumnNames = xlsDataAdapter.getColumnNames();
		List<Integer> listColumnIndexes = xlsDataAdapter.getColumnIndexes();
		if ((listColumnNames != null && listColumnNames.size() > 0)
				&& (listColumnIndexes != null && listColumnIndexes.size() > 0)
				&& (listColumnNames.size() == listColumnIndexes.size())) {

			for (int i = 0; i < listColumnNames.size(); i++) {
				rows.add(new String[] { listColumnNames.get(i), listColumnIndexes.get(i).toString() });
			}

			tableViewer.refresh();
			setTableSelection(-1);
			btnDelete.setEnabled(true);
		}
		if (xlsDataAdapter.getFormat() == null)
			xlsDataAdapter.setFormat(ExcelFormatEnum.AUTODETECT);
		switch (xlsDataAdapter.getFormat()) {
		case AUTODETECT:
			format.select(0);
			break;
		case XLS:
			format.select(1);
			break;
		case XLSX:
			format.select(2);
			break;
		}
	}

	/**
	 * Get the XLS DataAdapter with the values from the UI elements.
	 * 
	 * @return
	 */
	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new ExcelDataAdapterDescriptor();

		ExcelDataAdapter dataAdapter = (ExcelDataAdapter) dataAdapterDesc.getDataAdapter();

		dataAdapter.setQueryExecuterMode(btnCheckQEMode.getSelection());

		List<String> listColumnNames = new ArrayList<String>();
		List<Integer> listColumnIndexes = new ArrayList<Integer>();
		for (String[] row : rows) {
			listColumnNames.add(row[0]);
			listColumnIndexes.add(Integer.valueOf(row[1]));
		}
		switch (format.getSelectionIndex()) {
		case 0:
			dataAdapter.setFormat(ExcelFormatEnum.AUTODETECT);
			break;
		case 1:
			dataAdapter.setFormat(ExcelFormatEnum.XLS);
			break;
		case 2:
			dataAdapter.setFormat(ExcelFormatEnum.XLSX);
			break;
		}
		dataAdapter.setColumnNames(listColumnNames);
		dataAdapter.setColumnIndexes(listColumnIndexes);

		dataAdapter.setDatePattern(dnf.getTextDatePattern());
		dataAdapter.setNumberPattern(dnf.getTextNumberPattern());
		dataAdapter.setLocale(dnf.getLocale());
		dataAdapter.setTimeZone(dnf.getTimeZone());

		dataAdapter.setSheetSelection(textSheetSelection.getText());
		dataAdapter.setUseFirstRowAsHeader(btnCheckSkipFirstLine.getSelection());

		return dataAdapterDesc;
	}

	/**
	 * Content provider for XLSDataAdapterComposite TableViewer
	 * 
	 * @author czhu
	 * 
	 */
	private class XLSContentProvider implements IStructuredContentProvider {

		public void dispose() {
			// nothing
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// nothing
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement != null && inputElement instanceof List)
				return ((List<?>) inputElement).toArray();
			return new Object[0];
		}
	}

	/**
	 * Extended EditingSupport
	 * 
	 * @author czhu
	 * 
	 */
	private class NameIndexEditingSupport extends EditingSupport {

		private final TableViewer viewer;
		private int columnIndex;

		public NameIndexEditingSupport(TableViewer viewer, int columnIndex) {
			super(viewer);
			this.viewer = viewer;
			this.columnIndex = columnIndex;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(viewer.getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			return ((String[]) element)[columnIndex].toString();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((String[]) element)[columnIndex] = (String.valueOf(value));
			viewer.refresh();
		}
	}

	/**
	 * Extended ColumnLabelProvider
	 * 
	 * @author czhu
	 * 
	 */
	private class ColumnNameIndexLabelProvider extends ColumnLabelProvider {

		private int columnIndex;

		private ColumnNameIndexLabelProvider(int columnIndex) {
			this.columnIndex = columnIndex;
		}

		@Override
		public String getText(Object element) {
			String[] row = (String[]) element;
			if (columnIndex == 0) { // 0 => Name column
				return row[columnIndex].toString();
			} else { // 1 => Index column
				String excelCellLabel = excelCellLabelRenderer(Integer.valueOf(row[columnIndex].toString()));
				return row[columnIndex] + Messages.XLSDataAdapterComposite_22 + excelCellLabel
						+ Messages.XLSDataAdapterComposite_23;
			}
		}
	}

	/**
	 * Return the Excel cell label for a given index. i.e: <br>
	 * index 0 => Excel case A <br>
	 * index 25 => Excel case Z <br>
	 * index 26 => Excel case AA <br>
	 * index 51 => Excel case AZ <br>
	 * index 52 => Excel case BA...
	 * 
	 * @param Integer
	 *            index
	 * @return String the Excel cell label
	 */
	private String excelCellLabelRenderer(Integer index) {

		String digits = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //$NON-NLS-1$

		if (index != null && index instanceof Integer) {
			int val = ((Integer) index).intValue();

			String number = "" + digits.charAt(val % 26); //$NON-NLS-1$
			while (val > 0) {
				val = val / 26;
				int i = (val % 26) - 1;

				if (val == 0)
					break;
				if (val % 26 == 0) {
					// We have to remove 26 from val and print a Z...
					val -= 26;
					i = 25;
				}
				number = digits.charAt(i) + number;
			}

			return number;
		}

		return null;
	}

	/**
	 * This creates and returns a new entry for the data model
	 * 
	 * @return String[]{Name, Value}
	 */
	private String[] createDataModelEntry() {

		int i = 0;
		String column = "COLUMN_" + i; //$NON-NLS-1$

		while (!isColumnValid(column)) {
			i++;
			column = "COLUMN_" + i; //$NON-NLS-1$
		}

		return new String[] { column, String.valueOf(i) };
	}

	/**
	 * This set selection to the table's item represented by the given index.
	 * Any index out of table's range will select the last item.
	 * 
	 * @param index
	 */
	private void setTableSelection(int index) {

		if (rows != null && rows.size() > 0) {

			if (index == 0) {
				table.setSelection(index);
			} else if ((0 < index) && (index < rows.size() - 1)) {
				table.setSelection(index - 1);
			} else {
				table.setSelection(rows.size() - 1);
			}
		}
	}

	@Override
	protected void fireFileChanged(boolean showWarning) {
		try {
			if (showWarning) {
				if (UIUtils.showConfirmation(Messages.CSVDataAdapterComposite_0, Messages.CSVDataAdapterComposite_1))
					getExcelColumns();
			} else
				getExcelColumns();
		} catch (Exception e) {
			UIUtils.showError(e);
		}
	}

	/**
	 * This method will populate the data model with the Excel columns This also
	 * checks the button "Skip the first line " and enables the delete button
	 * 
	 * @throws Exception
	 */
	private void getExcelColumns() throws Exception {

		if (textFileName.getText().length() > 0) {
			DataAdapterDescriptor da = getDataAdapter();
			if (jConfig == null) {
				jConfig = JasperReportsConfiguration.getDefaultJRConfig();
			}
			DataAdapterService das = DataAdapterServiceUtil.getInstance(jConfig).getService(da.getDataAdapter());
			((AbstractDataAdapterService) das).getDataAdapter();
			JasperDesign jd = new JasperDesign();
			jd.setJasperReportsContext(jConfig);
			jConfig.setJasperDesign(jd);

			// The get fields method call once a next on the data adapter to get
			// the
			// first line and from that is read the
			// fields name. But is useFirstRowAsHeader flag is set to false than
			// the
			// next call will skip the first line
			// that is the only one read to get the fields, so it will return an
			// empty
			// set of column names. For this
			// reason this flag must be force to true if the data adapter is
			// used to
			// get the column names
			XlsDataAdapter xlsAdapter = (XlsDataAdapter) da.getDataAdapter();
			boolean useRowHeader = xlsAdapter.isUseFirstRowAsHeader();
			xlsAdapter.setUseFirstRowAsHeader(true);
			List<JRDesignField> fields = ((IFieldsProvider) da).getFields(das, jConfig,
					new JRDesignDataset(jConfig, false));
			xlsAdapter.setUseFirstRowAsHeader(useRowHeader);

			rows.clear();
			int columnIndex = 0;
			for (JRDesignField f : fields) {
				rows.add(new String[] { f.getName(), String.valueOf(columnIndex++) });
			}
			tableViewer.setInput(rows);

			tableViewer.refresh();
			setTableSelection(-1);
			btnDelete.setEnabled(true);
		}
	}

	@Override
	public void dispose() {
		if (jConfig != null) {
			// it is safe to dispose this jConfig since it was for sure created
			// internally
			jConfig.dispose();
			jConfig = null;
		}
		super.dispose();
	}

	/**
	 * Removes selected entries from the data model
	 */
	private void removeEntries() {

		int[] indices = table.getSelectionIndices();

		if (indices.length > 0) {

			Arrays.sort(indices);
			int removedItems = 0;

			for (int i : indices) {
				// To prevent an IndexOutOfBoundsException
				// we need to subtract number of removed items
				// from the removed item index.
				rows.remove(i - removedItems);
				removedItems++;
			}
			tableViewer.refresh();
			setTableSelection(indices[0]);
		}
	}

	/**
	 * Check the validity of the column name. It is valid only if it is not
	 * null, not empty and not already existed.
	 * 
	 * @param string
	 * @return true or false
	 */
	private boolean isColumnValid(String column) {

		if (column == null || "".equals(column)) //$NON-NLS-1$
			return false;

		for (String[] row : rows) {
			if (row[0].equals(column)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_excel");
	}

	@Override
	protected String[] getFileExtensions() {
		return new String[] { "*.xls;*.xlsx", "*.*" };
	}
}
