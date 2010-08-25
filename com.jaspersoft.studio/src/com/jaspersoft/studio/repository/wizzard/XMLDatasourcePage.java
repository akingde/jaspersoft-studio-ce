package com.jaspersoft.studio.repository.wizzard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;

public class XMLDatasourcePage extends AFileDatasourcePage {

	private Text xpathselectTXT;

	protected XMLDatasourcePage() {
		super("xmldatasourceeditor");
		setTitle("XML Datasource");
		setDescription("Creates a XML datasource.");
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT, xpathselectTXT.getText());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		super.createMoreControls(parent);

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("XPath Select:");

		xpathselectTXT = new Text(parent, SWT.BORDER);
		xpathselectTXT.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT);
			if (dsName == null)
				dsName = "";
			xpathselectTXT.setText(dsName);

		}
	}

	@Override
	protected String[] getFilterExt() {
		return new String[] { "*.xml", "*.*" };
	}

}
