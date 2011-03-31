package com.jaspersoft.studio.data.xls;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.eclipse.jface.dialogs.Dialog;
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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.jface.dialogs.DataAdapterErrorDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.utils.Misc;

public class XLSDataAdapterComposite extends Composite {

	private XLSDataAdapter xlsDataAdapter = null;
	private Text textExcelFileName;
	private Text textDatePattern;
	private Text textNumberPattern;
	private TableViewer tableViewer;
	private Table table;
	private TableViewerColumn tableViewerColumnName;
	private TableViewerColumn tableViewerColumnIndex;
	private Button btnAdd;
	private Button btnDelete;
	private Button btnCheckUseDatePattern;
	private Button btnCreateDatePattern;
	private Button btnCheckUseNumberPattern;
	private Button btnCreateNumberPattern;
	private Button btnCheckSkipFirstLine;

	// The data model
	private java.util.List<String[]> rows;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public XLSDataAdapterComposite(Composite parent, int style) {
		
		/*
		 * UI ELEMENTS
		 */
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		// data model init
		rows = new ArrayList<String[]>();
		
		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Excel file:");
		
		textExcelFileName = new Text(composite, SWT.BORDER);
		textExcelFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBrowse = new Button(composite, SWT.NONE);
		GridData gd_btnBrowse = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnBrowse.widthHint = 100;
		btnBrowse.setLayoutData(gd_btnBrowse);
		btnBrowse.setText("Browse");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.VERTICAL));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Group grpColumnNames = new Group(composite_1, SWT.NONE);
		grpColumnNames.setText("Column names");
		GridLayout gl_grpColumnNames = new GridLayout(1, false);
		grpColumnNames.setLayout(gl_grpColumnNames);
		
		Button btnGetExcelColumnsName = new Button(grpColumnNames, SWT.NONE);
		btnGetExcelColumnsName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnGetExcelColumnsName.setText("Get columns name from the first row of the file");
		
		Composite composite_3 = new Composite(grpColumnNames, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tableViewer = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer.setContentProvider(new XLSContentProvider());
		tableViewer.setInput(rows);
		
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		tableViewerColumnName = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnColumnName = tableViewerColumnName.getColumn();
		tblclmnColumnName.setMoveable(true);
		tblclmnColumnName.setWidth(100);
		tblclmnColumnName.setText("Column Name");
		tableViewerColumnName.setLabelProvider(new ColumnNameLabelProvider());
		tableViewerColumnName.setEditingSupport(new NameEditingSupport(tableViewer));
		
		tableViewerColumnIndex = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnColumnIndex = tableViewerColumnIndex.getColumn();
		tblclmnColumnIndex.setMoveable(true);
		tblclmnColumnIndex.setWidth(100);
		tblclmnColumnIndex.setText("Column Index");
		tableViewerColumnIndex.setLabelProvider(new ColumnIndexLabelProvider());
		tableViewerColumnIndex.setEditingSupport(new IndexEditingSupport(tableViewer));
		
		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
		      table.getColumn(i).pack();
		}
		
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
		btnAdd.setText("Add");
		
		btnDelete = new Button(composite_4, SWT.NONE);
		GridData gd_btnDelete = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnDelete.widthHint = 100;
		btnDelete.setLayoutData(gd_btnDelete);
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpOther = new Group(composite_2, SWT.NONE);
		grpOther.setText("Other");
		GridLayout gl_grpOther = new GridLayout(3, false);
		grpOther.setLayout(gl_grpOther);
		
		btnCheckUseDatePattern = new Button(grpOther, SWT.CHECK);
		btnCheckUseDatePattern.setBounds(0, 0, 93, 16);
		btnCheckUseDatePattern.setText("Use custom date pattern");
		
		textDatePattern = new Text(grpOther, SWT.BORDER);
		textDatePattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textDatePattern.setEnabled(false);
		
		btnCreateDatePattern = new Button(grpOther, SWT.NONE);
		GridData gd_btnCreateDatePattern = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnCreateDatePattern.widthHint = 100;
		btnCreateDatePattern.setLayoutData(gd_btnCreateDatePattern);
		btnCreateDatePattern.setText("Create");
		btnCreateDatePattern.setEnabled(false);
		
		btnCheckUseNumberPattern = new Button(grpOther, SWT.CHECK);
		btnCheckUseNumberPattern.setText("Use custom number pattern");
		
		textNumberPattern = new Text(grpOther, SWT.BORDER);
		textNumberPattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textNumberPattern.setEnabled(false);
		
		btnCreateNumberPattern = new Button(grpOther, SWT.NONE);
		GridData gd_btnCreateNumberPattern = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnCreateNumberPattern.widthHint = 100;
		btnCreateNumberPattern.setLayoutData(gd_btnCreateNumberPattern);
		btnCreateNumberPattern.setText("Create");
		btnCreateNumberPattern.setEnabled(false);
		
		btnCheckSkipFirstLine = new Button(grpOther, SWT.CHECK);
		btnCheckSkipFirstLine.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		btnCheckSkipFirstLine.setText("Skip the first line (the column names will be read from the first line)");
		
		/*
		 * UI ELEMENTS LISTENERS
		 */
		// browse and select the Excel file
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell());
				fd.setFileName(textExcelFileName.getText());
				fd.setFilterExtensions(new String[]{"*.xls","*.*"});
				String selection = fd.open();
				if (selection != null)
					textExcelFileName.setText(selection);
			}
		});
		
		// get Excel file columns
		btnGetExcelColumnsName.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e)  {
				try {
					getExcelColumns();
				} catch (Exception e1) {
					e1.printStackTrace();

			        DataAdapterErrorDialog.showErrorDialog(
			        		Display.getCurrent().getActiveShell(), 
			        		"Excel errors",
			        		e1);
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
				setLastTableItemSelection();
			}
		});
		
		// delete selected entries and set selection on last table item
		btnDelete.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				int[] indices = table.getSelectionIndices();
				int removedItems = 0;
				
				for (int i : indices) {	
					// To prevent an IndexOutOfBoundsException
					// we need to subtract number of removed items
					// from the removed item index.
					rows.remove(i - removedItems);
					removedItems++;
				}
				tableViewer.refresh();
				setLastTableItemSelection();
			}
		});
		
		// When no table items,
		// turns disabled the delete button
		// and set unchecked the skip first line button
		tableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (rows.size() <= 0) {
					btnDelete.setEnabled(false);
					btnCheckSkipFirstLine.setSelection(false);
				} else {
					btnDelete.setEnabled(true);
				}
			}
		});
		
		btnCheckUseDatePattern.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				boolean bool = ((Button)e.widget).getSelection();
				if (!bool) {
					textDatePattern.setText(new SimpleDateFormat().toLocalizedPattern());
				}
				textDatePattern.setEnabled(bool);
				btnCreateDatePattern.setEnabled(bool);
			}
		});
		
		btnCreateDatePattern.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				PatternEditor wizard = new PatternEditor();
				wizard.setNumberPatterns(false);
				wizard.setValue(textDatePattern.getText());
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textDatePattern.setText(val);
				}
			}
		});
		
		btnCheckUseNumberPattern.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				boolean bool = ((Button)e.widget).getSelection();
				if (!bool) {
					textNumberPattern.setText(new DecimalFormat().toLocalizedPattern());
				}
				textNumberPattern.setEnabled(bool);
				btnCreateNumberPattern.setEnabled(bool);
			}
		});
		
		btnCreateNumberPattern.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				PatternEditor wizard = new PatternEditor();
				wizard.setDatePatterns(false);
				wizard.setValue(textNumberPattern.getText());
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textNumberPattern.setText(val);
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Set the XLS DataAdapter and initial values for UI elements.
	 * @param dataAdapter
	 */
	public void setDataAdapter(XLSDataAdapter dataAdapter) {
		
		this.xlsDataAdapter = dataAdapter;
		
		textExcelFileName.setText( Misc.nvl( this.xlsDataAdapter.getFileName(),"") );
		
		List<String> listColumnNames = this.xlsDataAdapter.getColumnNames();
		List<Integer> listColumnIndexes = this.xlsDataAdapter.getColumnIndexes();
		if (    (listColumnNames != null && listColumnNames.size() > 0)
		     && (listColumnIndexes != null && listColumnIndexes.size() > 0)
		     && (listColumnNames.size() == listColumnIndexes.size() )) {
			
			for (int i = 0; i < listColumnNames.size(); i++) {
				rows.add( new String[]{listColumnNames.get(i), listColumnIndexes.get(i).toString()} );
				tableViewer.refresh();
				setLastTableItemSelection();
				btnDelete.setEnabled(true);
			}
		}
		
		String customDatePattern = this.xlsDataAdapter.getCustomDatePattern();
		if (customDatePattern != null && customDatePattern.length() > 0) {
			btnCheckUseDatePattern.setSelection(true);
			textDatePattern.setText(customDatePattern);
			textDatePattern.setEnabled(true);
			btnCreateDatePattern.setEnabled(true);
		} else {
			textDatePattern.setText(new SimpleDateFormat().toLocalizedPattern());
		}
		
		String customNumberPattern = this.xlsDataAdapter.getCustomNumberPattern();
		if (customNumberPattern != null && customNumberPattern.length() > 0) {
			btnCheckUseNumberPattern.setSelection(true);
			textNumberPattern.setText(customNumberPattern);
			textNumberPattern.setEnabled(true);
			btnCreateNumberPattern.setEnabled(true);
		} else {
			textNumberPattern.setText(new DecimalFormat().toLocalizedPattern());
		}
		
		btnCheckSkipFirstLine.setSelection(this.xlsDataAdapter.isUseFirstRowAsHeader());
	}

	/**
	 * Get the XLS DataAdapter with the values from the UI elements.
	 * @return
	 */
	public DataAdapter getDataAdapter() {
		
		if (this.xlsDataAdapter == null) this.xlsDataAdapter = new XLSDataAdapter();
		
		this.xlsDataAdapter.setFileName(textExcelFileName.getText());
		
		List<String> listColumnNames = new ArrayList<String>();
		List<Integer> listColumnIndexes = new ArrayList<Integer>();
		for (String[] row : rows) {
			listColumnNames.add(row[0]);
			listColumnIndexes.add(Integer.valueOf(row[1]));
		}
		
		this.xlsDataAdapter.setColumnNames(listColumnNames);
		this.xlsDataAdapter.setColumnIndexes(listColumnIndexes);
		
		this.xlsDataAdapter.setCustomDatePattern(textDatePattern.getText());
		this.xlsDataAdapter.setCustomNumberPattern(textNumberPattern.getText());
		this.xlsDataAdapter.setUseFirstRowAsHeader(btnCheckSkipFirstLine.getSelection());
		
		return this.xlsDataAdapter;
	}

	/**
	 * Return the HelpContextID
	 * @return String helpContextID
	 */
	public String getHelpContextId() {
		return "";
	}
	
	/**
	 * Content provider for XLSDataAdapterComposite TableViewer
	 * @author czhu
	 *
	 */
	private class XLSContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
			// nothing
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// nothing
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement != null && inputElement instanceof List)
				return ((List<?>) inputElement).toArray();
			return new Object[0];
		}
		
	}
	
	/**
	 * Extended EditingSupport for the specific column Name
	 * @author czhu
	 *
	 */
	private class NameEditingSupport extends EditingSupport {
		
		private final TableViewer viewer;

		public NameEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
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
			return ((String[])element)[0].toString();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((String[]) element)[0] = (String.valueOf(value));
			viewer.refresh();
		}
	}
	
	/**
	 * Extended EditingSupport for the specific column Index
	 * @author czhu
	 *
	 */
	private class IndexEditingSupport extends EditingSupport {
		
		private final TableViewer viewer;

		public IndexEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
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
			return ((String[])element)[1].toString();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((String[]) element)[1] = (String.valueOf(value));
			viewer.refresh();
		}
	}
	
	/**
	 * Extended ColumnLabelProvider for the specific column Name
	 * @author czhu
	 *
	 */
	private class ColumnNameLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			String[] row = (String[]) element;
			return row[0].toString();
		}
	}
	
	/**
	 * Extended ColumnLabelProvider for the specific column Index
	 * @author czhu
	 *
	 */
	private class ColumnIndexLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			String[] row = (String[]) element;
			String excelCellLabel = excelCellLabelRenderer(Integer.valueOf(row[1].toString()));
			return row[1] + " (" + excelCellLabel + ")" ;
		}
	}
	
	/**
	 * Return the Excel cell label for a given index. i.e:
	 * <br>index  0 => Excel case A
	 * <br>index 25 => Excel case Z
	 * <br>index 26 => Excel case AA
	 * <br>index 51 => Excel case AZ
	 * <br>index 52 => Excel case BA...
	 * @param Integer index
	 * @return String the Excel cell label
	 */
	private String excelCellLabelRenderer(Integer index) {
		
		String digits = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    if (index != null && index instanceof Integer)
	    {
	        int val = ((Integer)index).intValue();

	        String number = "" + digits.charAt(val%26);
	        while (val > 0)
	        {
	            val = val/26;
	            int i = (val%26)-1;

	            if (val == 0) break;
	            if (val%26 == 0)
	            {
	                // We have to remove 26 from val and print a Z...
	                val-=26;
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
	 * @return String[]{Name, Value}
	 */
	private String[] createDataModelEntry() {
		
		int i;
		// find max index
		if (rows != null) {
			i = rows.size();
		} else { // this case should never happen
			i = 0;
		}
		
		return new String[]{"COLUMN_" + i, String.valueOf(i)};
	}
	
	/**
	 * This set selection to the last table item
	 */
	private void setLastTableItemSelection() {
		
		if (rows != null && rows.size() > 0) {
			table.setSelection(rows.size() - 1);
		}
	}
	
	/**
	 * This method will populate the data model with the Excel columns
	 * This also checks the button "Skip the first line " and
	 * enables the delete button
	 * @throws Exception
	 */
	private void getExcelColumns() throws Exception {
	    if (textExcelFileName.getText().length() > 0)
	    {
	     	Workbook workbook = Workbook.getWorkbook(new FileInputStream(new File(textExcelFileName.getText())));
	       	Sheet sheet = workbook.getSheet(0);

	       	// empty data model
	        rows.clear();

	        for(int columnIndex = 0; columnIndex < sheet.getColumns(); columnIndex++)
	        {
	            Cell cell = sheet.getCell(columnIndex, 0);
	            String columnName = cell.getContents();
	            if (columnName != null && columnName.trim().length() > 0)
	            {
	                rows.add(new String[]{columnName, String.valueOf(columnIndex)});
	            }
	        }

	        tableViewer.refresh();
	        setLastTableItemSelection();
	        btnDelete.setEnabled(true);
	        btnCheckSkipFirstLine.setSelection(true);
	    }
	}
}
