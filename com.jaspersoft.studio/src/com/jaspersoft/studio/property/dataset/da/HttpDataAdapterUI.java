/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.da;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.FileDataAdapter;
import net.sf.jasperreports.data.http.HttpDataLocation;
import net.sf.jasperreports.data.http.HttpDataService;
import net.sf.jasperreports.data.http.RequestMethod;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public class HttpDataAdapterUI extends ADataAdapterQueryEditorUI {
	private JasperReportsConfiguration jConfig;

	@Override
	public Composite create(Composite parent, DataAdapter da, final JRDesignDataset dataset,
			JasperReportsConfiguration jConfig) {
		super.dataset = dataset;
		this.jConfig = jConfig;
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		DataAdapterFactory f = DataAdapterManager.findFactoryByDataAdapterClass(da.getClass().getName());
		icon = f != null ? f.getIcon(16)
				: JaspersoftStudioPlugin.getInstance().getImage(MDataAdapter.getIconDescriptor().getIcon16());

		dloc = (HttpDataLocation) ((FileDataAdapter) da).getDataFile();
		createPropertyWidget(HttpDataService.PROPERTY_URL, cmp, dloc.getUrl(), -2, jConfig);
		createPropertyWidget(HttpDataService.PROPERTY_USERNAME, cmp, dloc.getUsername(), 200, jConfig);
		createPropertyWidget(HttpDataService.PROPERTY_PASSWORD, cmp, dloc.getPassword(), 200, jConfig);

		String m = RequestMethod.GET.name();
		if (dloc.getMethod() != null)
			m = dloc.getMethod().name();
		createPropertyWidget(HttpDataService.PROPERTY_METHOD, cmp, m, 200, jConfig);

		CTabFolder tFolder = new CTabFolder(cmp, SWT.FLAT | SWT.TOP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 250;
		gd.horizontalSpan = 2;
		tFolder.setLayoutData(gd);

		CTabItem bptab = new CTabItem(tFolder, SWT.NONE);
		bptab.setText("URL Parameters");
		bptab.setControl(
				createPropertiesTable(tFolder, dloc.getUrlParameters(), HttpDataService.PROPERTY_URL_PARAMETER));

		bptab = new CTabItem(tFolder, SWT.NONE);
		bptab.setText("POST/PUT Parameters");
		bptab.setControl(
				createPropertiesTable(tFolder, dloc.getPostParameters(), HttpDataService.PROPERTY_POST_PARAMETER));

		createSectionBody(tFolder);

		bptab = new CTabItem(tFolder, SWT.NONE);
		bptab.setText("Headers");
		bptab.setControl(createPropertiesTable(tFolder, dloc.getHeaders(), HttpDataService.PROPERTY_HEADER));

		tFolder.setSelection(0);

		listenDataset(dataset);
		return cmp;
	}

	private void createSectionBody(CTabFolder tFolder) {
		CTabItem bptab = new CTabItem(tFolder, SWT.NONE);
		bptab.setText("POST/PUT Body");

		Composite sectionClient = new Composite(tFolder, SWT.NONE);
		sectionClient.setLayout(new GridLayout());
		sectionClient.setBackgroundMode(SWT.INHERIT_FORCE);
		bptab.setControl(sectionClient);

		createPropertyWidget(HttpDataService.PROPERTY_BODY, sectionClient, dloc.getBody(), -3, jConfig);
	}

	@Override
	public boolean isForDataAdapter(DataAdapter da) {
		if (da instanceof FileDataAdapter && ((FileDataAdapter) da).getDataFile() instanceof HttpDataLocation)
			return true;
		return false;
	}

}
