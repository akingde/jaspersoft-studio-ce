package com.jaspersoft.studio.repository.wizzard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.AMFileDataSource;

public abstract class AFileDatasourcePage extends ADatasourcePage {
	private Text fileNameTxt;

	protected AFileDatasourcePage(String pageName) {
		super(pageName);
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(AMFileDataSource.PROPERTY_FILENAME, fileNameTxt.getText());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("File Name:");

		Composite c = new Composite(parent, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);

		fileNameTxt = new Text(c, SWT.BORDER);
		fileNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button copyButton = new Button(c, SWT.PUSH);
		copyButton.setText("Br&owse ...");
		copyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell());
				fd.setFileName(fileNameTxt.getText());
				fd.setFilterExtensions(getFilterExt());
				String selection = fd.open();
				if (selection != null)
					fileNameTxt.setText(selection);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	protected abstract String[] getFilterExt();

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(AMFileDataSource.PROPERTY_FILENAME);
			if (dsName == null)
				dsName = "";
			fileNameTxt.setText(dsName);

		}
	}
}
