package com.jaspersoft.studio.repository.wizzard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDataSource;

public class EmptyDatasourcePage extends ADatasourcePage {

	private Spinner sizeTxt;

	protected EmptyDatasourcePage() {
		super("emptydatasourceeditor");
		setTitle("Empty Datasource");
		setDescription("Creates an empty datasource.");
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(MEmptyDataSource.PROPERTY_SIZE, new Integer(sizeTxt.getSelection()));
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Size:");

		sizeTxt = new Spinner(parent, SWT.BORDER);
		sizeTxt.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		sizeTxt.setToolTipText("Number of empty source records");
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			Integer dsName = (Integer) value.getPropertyValue(MEmptyDataSource.PROPERTY_SIZE);
			if (dsName == null)
				dsName = new Integer(1);
			sizeTxt.setSelection(dsName);

		}
	}
}
