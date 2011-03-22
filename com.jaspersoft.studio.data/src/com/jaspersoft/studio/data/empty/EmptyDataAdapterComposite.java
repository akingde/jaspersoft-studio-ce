package com.jaspersoft.studio.data.empty;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.GridData;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.utils.UIUtils;

public class EmptyDataAdapterComposite extends Composite {

	private Spinner spinnerRecords;
	private EmptyDataAdapter emptyDataAdapter = null;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EmptyDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Number of empty records" + " :");
		
		spinnerRecords = new Spinner(this, SWT.BORDER);
		spinnerRecords.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Set the DataAdapter to edit.
	 * The UI will be updated with the content of this adapter
	 * @param dataAdapter
	 */
	public void setDataAdapter(EmptyDataAdapter dataAdapter) {
		emptyDataAdapter = dataAdapter;
		Integer records = emptyDataAdapter.getRecords();
		if (records != null)
		{
			UIUtils.setSpinnerSelection(spinnerRecords, records);
		}
	}
	
	public DataAdapter getDataAdapter() {
		if(emptyDataAdapter == null){
			emptyDataAdapter = new EmptyDataAdapter();
		}
		emptyDataAdapter.setRecords(spinnerRecords.getSelection());
		return emptyDataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}
}
