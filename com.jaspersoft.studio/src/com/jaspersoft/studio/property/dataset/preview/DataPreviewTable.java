/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.preview;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.data.IDataPreviewInfoProvider;
import com.jaspersoft.studio.data.designer.AQueryDesignerContainer;
import com.jaspersoft.studio.data.reader.DatasetReader;
import com.jaspersoft.studio.data.reader.DatasetReaderListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.dialog.CopyDataDialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

/**
 * Data preview table widget.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DataPreviewTable implements DatasetReaderListener {

	// Costants
	private static final int FILLER_THREAD_SLEEPTIME = 30;
	private static final int RECORDS_NUM_ALL = -1;
	private static final int RECORDS_NUM_1000 = 1000;
	private static final int RECORDS_NUM_500 = 500;
	private static final int RECORDS_NUM_100 = 100;
	private static final int RECORDS_NUM_10 = 10;

	// Widget stuff
	private TableViewer tviewer;
	private Table wtable;
	private Composite composite;
	private Composite tableContainer;
	private Combo recordsNumCombo;
	// private Button refreshPreviewBtn;
	private Button cancelPreviewBtn;
	private ProgressBar progressBar;
	private Label infoMsg;
	private Composite infoComposite;
	private Color background;

	// Additional support fields
	private IDataPreviewInfoProvider previewInfoProvider;
	private Job refreshPrevieDataJob;
	private DatasetReader dataReader;
	private boolean statusOK;
	private List<DataPreviewBean> previewItems;
	private TableFillerThread tableFiller;
	private int readItems = 0;
	private AQueryDesignerContainer designer;

	public DataPreviewTable(AQueryDesignerContainer designer, Composite parent,
			IDataPreviewInfoProvider previewInfoProvider, Color background) {
		this.previewInfoProvider = previewInfoProvider;
		this.designer = designer;
		this.previewItems = new ArrayList<DataPreviewTable.DataPreviewBean>(RECORDS_NUM_100);
		this.background = background;
		createControl(parent);
	}

	/*
	 * Creates the widget area.
	 */
	private void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(background);
		composite.setLayout(new GridLayout(4, false));
		composite.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				statusOK = false;
				if (refreshPrevieDataJob != null) {
					refreshPrevieDataJob.cancel();
				}
			}
		});

		toolbar = new ToolBar(composite, SWT.FLAT);
		final ToolItem itemDrop = new ToolItem(toolbar, SWT.DROP_DOWN);
		itemDrop.setText(Messages.DataPreviewTable_PreviewButton);

		itemDrop.addSelectionListener(new SelectionAdapter() {

			private Menu dropMenu = null;
			private boolean getFields = false;

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (dropMenu == null) {
					dropMenu = new Menu(composite);
					composite.setMenu(dropMenu);

					MenuItem itemRadio = new MenuItem(dropMenu, SWT.RADIO);
					itemRadio.setText(Messages.DataPreviewTable_PreviewButton);
					itemRadio.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							itemDrop.setText(Messages.DataPreviewTable_PreviewButton);
							composite.update();
							composite.layout(true);
							getFields = false;
							refresh();

						}

					});
					MenuItem itemRadio2 = new MenuItem(dropMenu, SWT.RADIO);
					itemRadio2.setText("Get Fields And Refresh Data");
					itemRadio2.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							itemDrop.setText("Get Fields And Refresh Data");
							composite.update();
							composite.layout(true);
							getFields = true;
							designer.doGetFields();
							refresh();
						}

					});
				}
				if (e.detail == SWT.ARROW) {
					// Position the menu below and vertically aligned with the the drop down tool button.
					final ToolItem toolItem = (ToolItem) e.widget;
					final ToolBar toolBar = toolItem.getParent();

					Point point = toolBar.toDisplay(new Point(e.x, e.y));
					dropMenu.setLocation(point.x, point.y);
					dropMenu.setVisible(true);
				} else {
					if (getFields)
						designer.doGetFields();
					refresh();
				}
			}

			private boolean canRefreshDataPreview() {
				// No data preview when no fields are selected.
				if (previewInfoProvider.getFieldsForPreview() == null || previewInfoProvider.getFieldsForPreview().isEmpty()) {
					MessageDialog.openError(composite.getShell(), Messages.DataPreviewTable_ErrorTitle,
							Messages.DataPreviewTable_ErrorMsgNoFields);
					return false;
				}
				// No data preview when no data adapter is selected
				if (previewInfoProvider.getDataAdapterDescriptor() == null) {
					MessageDialog.openError(composite.getShell(), Messages.DataPreviewTable_ErrorTitle,
							Messages.DataPreviewTable_ErrorMsgNoDataAdapter);
					return false;
				}
				return true;
			}

			protected void refresh() {
				if (canRefreshDataPreview()) {
					refreshDataPreview();
					toolbar.setEnabled(false);
					cancelPreviewBtn.setEnabled(true);
				}
			}

		});

		cancelPreviewBtn = new Button(composite, SWT.PUSH);
		cancelPreviewBtn.setText(Messages.DataPreviewTable_CancelButton);
		cancelPreviewBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		cancelPreviewBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancelDataPreview();
				cancelPreviewBtn.setEnabled(false);
				toolbar.setEnabled(true);
			}
		});
		cancelPreviewBtn.setEnabled(false);

		recordsNumCombo = new Combo(composite, SWT.READ_ONLY);
		recordsNumCombo.setItems(new String[] { Messages.DataPreviewTable_0, Messages.DataPreviewTable_RecordsNum100,
				Messages.DataPreviewTable_RecordsNum500, Messages.DataPreviewTable_RecordsNum1000,
				Messages.DataPreviewTable_RecordsNumAll });
		recordsNumCombo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		recordsNumCombo.select(0);

		infoComposite = new Composite(composite, SWT.NONE);
		GridLayout infoCmpGL = new GridLayout(2, false);
		infoCmpGL.marginHeight = 0;
		infoCmpGL.marginWidth = 0;
		infoComposite.setBackground(background);
		infoComposite.setLayout(infoCmpGL);
		infoComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		infoMsg = new Label(infoComposite, SWT.NONE);
		infoMsg.setBackground(background);
		infoMsg.setText(Messages.DataPreviewTable_Ready);
		infoMsg.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

		progressBar = new ProgressBar(infoComposite, SWT.INDETERMINATE | SWT.BORDER);
		GridData progBarGD = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
		progBarGD.horizontalIndent = 5;
		progBarGD.widthHint = 100;
		progBarGD.exclude = true;
		progressBar.setLayoutData(progBarGD);
		progressBar.setVisible(false);

		tableContainer = new Composite(composite, SWT.NONE);
		tableContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		tableContainer.setLayout(new TableColumnLayout());

		tviewer = new TableViewer(tableContainer, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		wtable = tviewer.getTable();
		wtable.setHeaderVisible(true);
		wtable.setLinesVisible(true);

		tviewer.setContentProvider(ArrayContentProvider.getInstance());

		Menu menu = new Menu(tviewer.getTable());
		MenuItem mitem = new MenuItem(menu, SWT.NONE);
		mitem.setText(Messages.DataPreviewTable_1);
		mitem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new CopyDataDialog(tableContainer.getShell(), DataPreviewTable.this).open();
			}
		});
		tviewer.getTable().setMenu(menu);
	}

	/**
	 * @return the main control
	 */
	public Composite getControl() {
		return this.composite;
	}

	/*
	 * Notifies the need of a table refresh due to information modification, i.e. table columns modification.
	 */
	private void refreshDataPreview() {
		// Refresh layout for the table
		updateTableLayout();
		final int recordsCountSelected = getRecordsCountSelected();
		refreshPrevieDataJob = new Job(Messages.DataPreviewTable_PreviewDataJobTitle) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				if (dataReader != null) {
					dataReader.removeDatasetReaderListener(DataPreviewTable.this);
					dataReader = null;
				}
				dataReader = new DatasetReader();
				dataReader.setColumns(getColumns());
				dataReader.setDataAdapterDescriptor(previewInfoProvider.getDataAdapterDescriptor());
				// FIXME - TEMPORARY FIX THAT SHOULD BE REMOVED!
				// Using JFace Databinding for fields list of the dataset
				// makes the internal fields map to be out of synch.
				// Modifications should occur using the JRDesignDataset#add and #remove methods.
				JRDesignDataset clonedDS = (JRDesignDataset) previewInfoProvider.getDesignDataset().clone();
				clonedDS.getFieldsList().clear();
				clonedDS.getFieldsMap().clear();
				for (JRDesignField f : previewInfoProvider.getFieldsForPreview()) {
					try {
						clonedDS.addField(f);
					} catch (JRException e) {
						// Do not care, duplication should never happen.
						e.printStackTrace();
					}
				}

				dataReader.setDesignDataset(clonedDS);
				dataReader.setMaxRecords(recordsCountSelected);
				dataReader.addDatasetReaderListener(DataPreviewTable.this);
				dataReader.start(previewInfoProvider.getJasperReportsConfig());
				return Status.OK_STATUS;
			}
		};

		tableFiller = new TableFillerThread();

		statusOK = true;
		infoMsg.setText(Messages.DataPreviewTable_GettingData);
		((GridData) progressBar.getLayoutData()).exclude = false;
		progressBar.setVisible(true);
		refreshPrevieDataJob.schedule();
		tableFiller.start();
		infoComposite.layout();
	}

	/*
	 * Cancel a pending data preview task.
	 */
	private void cancelDataPreview() {
		// Clean up
		invalidate();
		if (refreshPrevieDataJob != null) {
			refreshPrevieDataJob.cancel();
		}
		if (dataReader.isRunning()) {
			dataReader.stop();
		}
		// Remove all table items if any
//		wtable.removeAll();
//		tviewer.setInput(null);
//		readItems = 0;
		infoMsg.setText(Messages.DataPreviewTable_Ready);
		((GridData) progressBar.getLayoutData()).exclude = true;
		progressBar.setVisible(false);
	}

	/*
	 * Gets the number of max records for the output preview.
	 */
	private int getRecordsCountSelected() {
		switch (recordsNumCombo.getSelectionIndex()) {
		case 0:
			return RECORDS_NUM_10;
		case 1:
			return RECORDS_NUM_100;
		case 2:
			return RECORDS_NUM_500;
		case 3:
			return RECORDS_NUM_1000;
		case 4:
			return RECORDS_NUM_ALL;
		default:
			return RECORDS_NUM_10;
		}
	}

	/*
	 * Gets the column names.
	 */
	public List<String> getColumns() {
		List<String> columns = new ArrayList<String>();
		for (JRDesignField f : previewInfoProvider.getFieldsForPreview()) {
			columns.add(f.getName());
		}
		return columns;
	}

	public List<JRDesignField> getFields() {
		return previewInfoProvider.getFieldsForPreview();
	}

	private static SimpleDateFormat TIMESTAMP = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss.SSSS"); //$NON-NLS-1$
	private static SimpleDateFormat TIME = new SimpleDateFormat("hh:mm:ss.SSSS"); //$NON-NLS-1$
	private ToolBar toolbar;

	/*
	 * Update the table layout.
	 */
	private void updateTableLayout() {
		if (composite.isVisible()) {
			// Remove all table items if any
			wtable.removeAll();
			tviewer.setInput(null);

			// Dispose old columns if any
			for (TableColumn col : wtable.getColumns()) {
				col.dispose();
			}

			TableColumnLayout tColLayout = new TableColumnLayout();
			tableContainer.setLayout(tColLayout);

			List<JRDesignField> fields = previewInfoProvider.getFieldsForPreview();
			if (fields.size() > 0) {
				for (JRDesignField f : fields) {
					TableViewerColumn tvc = new TableViewerColumn(tviewer, SWT.NONE);
					tvc.getColumn().setText(f.getName());
					tvc.setLabelProvider(new ColumnLabelProvider());
					tColLayout.setColumnData(tvc.getColumn(), new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, true));
					tvc.setLabelProvider(new CellLabelProvider() {
						@Override
						public void update(ViewerCell cell) {
							DataPreviewBean element = (DataPreviewBean) cell.getElement();
							Object value = element.getValue(cell.getColumnIndex());
							if (value != null) {
								if (value instanceof java.sql.Time)
									cell.setText(TIME.format(value));
								else if (value instanceof java.sql.Timestamp)
									cell.setText(TIMESTAMP.format(value));
								else
									cell.setText(value.toString());
							} else {
								cell.setText(""); //$NON-NLS-1$
							}
						}
					});
				}

			}

			tableContainer.layout();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.reader.DatasetReaderListener#newRecord(java.lang.Object[])
	 */
	public void newRecord(final Object[] values) {
		previewItems.add(new DataPreviewBean(values));
		readItems++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.reader.DatasetReaderListener#finished()
	 */
	public void finished() {
		UIUtils.getDisplay().syncExec(new Runnable() {
			public void run() {
				if (progressBar.isDisposed())
					return;
				if (tableFiller != null) {
					tableFiller.done();
					tableFiller = null;
				}
				flushPreviewItems();
				progressBar.setVisible(false);
				cancelPreviewBtn.setEnabled(false);
				((GridData) progressBar.getLayoutData()).exclude = true;
				if (isValidStatus()) {
					infoMsg.setText(MessageFormat.format(Messages.DataPreviewTable_ReadyReadData, new Object[] { readItems }));
				}
				toolbar.setEnabled(true);
				infoComposite.layout();
				readItems = 0;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.reader.DatasetReaderListener#isValidStatus()
	 */
	public boolean isValidStatus() {
		return statusOK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.reader.DatasetReaderListener#invalidate()
	 */
	public void invalidate() {
		this.statusOK = false;
	}

	/*
	 * Bean to represent the read record for previewing.
	 */
	public class DataPreviewBean {
		private Object[] values;

		public DataPreviewBean(Object[] values) {
			this.values = values;
		}

		public Object getValue(int index) {
			return this.values[index];
		}
	}

	/*
	 * This thread is responsible to update the table with chunks of data read from the dataset.
	 */
	private class TableFillerThread extends Thread {
		private boolean done = false;

		public void done() {
			done = true;
		}

		@Override
		public void run() {
			while (!done) {
				flushPreviewItems();
				try {
					sleep(FILLER_THREAD_SLEEPTIME);
				} catch (InterruptedException e) {

				}
			}
		}
	}

	/*
	 * Flush buffered items.
	 */
	private void flushPreviewItems() {
		Object[] tmpItems = new Object[0];
		synchronized (previewItems) {
			tmpItems = previewItems.toArray();
			previewItems.clear();
		}
		final Object[] items = tmpItems;
		UIUtils.getDisplay().syncExec(new Runnable() {
			public void run() {
				if (!wtable.isDisposed() && statusOK) {
					tviewer.add(items);
				}
			}
		});

	}

	public List<DataPreviewBean> getPreviewItems() {
		List<DataPreviewBean> res = new ArrayList<DataPreviewTable.DataPreviewBean>();
		for (TableItem ti : tviewer.getTable().getItems())
			res.add((DataPreviewBean) ti.getData());
		return res;
	}
}
