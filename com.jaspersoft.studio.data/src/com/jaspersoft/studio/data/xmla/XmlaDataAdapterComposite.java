/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.xmla;

import java.net.Authenticator;

import net.sf.jasperreports.data.xmla.XmlaDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import rex.graphics.datasourcetree.elements.CatalogElement;
import rex.graphics.datasourcetree.elements.CubeElement;
import rex.graphics.datasourcetree.elements.DataSourceTreeElement;
import rex.metadata.ServerMetadata;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.utils.Misc;

public class XmlaDataAdapterComposite extends Composite {

	private XmlaDataAdapterDescriptor hbmDataAdapterDesc = null;
	private Text xmlaUri;
	private Text textUsername;
	private Text textPassword;
	private Combo cube;
	private Combo catalog;
	private Combo datasource;
	private DataSourceTreeElement[] dstes;
	private DataSourceTreeElement[] catalogs;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public XmlaDataAdapterComposite(Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("XML/A Server URL");

		xmlaUri = new Text(this, SWT.BORDER);
		xmlaUri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bGetMetadata = new Button(this, SWT.PUSH);
		bGetMetadata.setText("Get Metadata");

		new Label(this, SWT.NONE).setText("Datasource");
		datasource = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		datasource.setItems(new String[] {});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		datasource.setLayoutData(gd);

		new Label(this, SWT.NONE).setText("Catalog");
		catalog = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		catalog.setItems(new String[] {});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		catalog.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		new Label(this, SWT.NONE).setText("Cube");

		cube = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		cube.setItems(new String[] {});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cube.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setText(Messages.JDBCDataAdapterComposite_username);

		textUsername = new Text(this, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		textUsername.setLayoutData(gd);

		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setText(Messages.JDBCDataAdapterComposite_password);

		textPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		textPassword.setLayoutData(gd);

		bGetMetadata.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = xmlaUri.getText();
				ServerMetadata smd = new ServerMetadata(url);

				handleMetaDataChanged(smd);

			}
		});

		datasource.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				handleDatasourceChanged();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		catalog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				handleCatalogChanged();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(XmlaDataAdapterDescriptor dataAdapter) {
		this.hbmDataAdapterDesc = dataAdapter;

		XmlaDataAdapter xmlDataAdapter = hbmDataAdapterDesc.getDataAdapter();

		xmlaUri.setText(Misc.nvl(xmlDataAdapter.getXmlaUrl(), ""));
		datasource.setText(Misc.nvl(xmlDataAdapter.getDatasource(), ""));

		catalog.setText(Misc.nvl(xmlDataAdapter.getCatalog(), ""));
		cube.setText(Misc.nvl(xmlDataAdapter.getCube(), ""));

		textUsername.setText(Misc.nvl(xmlDataAdapter.getUsername(), ""));
		textPassword.setText(Misc.nvl(xmlDataAdapter.getPassword(), ""));
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (hbmDataAdapterDesc == null)
			hbmDataAdapterDesc = new XmlaDataAdapterDescriptor();

		XmlaDataAdapter dataAdapter = hbmDataAdapterDesc.getDataAdapter();

		dataAdapter.setXmlaUrl(xmlaUri.getText());
		dataAdapter.setDatasource(datasource.getText());
		dataAdapter.setCatalog(catalog.getText());
		dataAdapter.setCube(cube.getText());

		dataAdapter.setUsername(textUsername.getText());
		dataAdapter.setPassword(textPassword.getText());

		return hbmDataAdapterDesc;
	}

	public String getHelpContextId() {
		return "";
	}

	protected void handleDatasourceChanged() {
		catalog.removeAll();
		cube.removeAll();
		int ind = datasource.getSelectionIndex();
		catalogs = dstes[ind].getChildren();
		if (catalogs == null || catalogs.length == 0)
			return;

		String[] scat = new String[catalogs.length];
		for (int i = 0; i < catalogs.length; i++)
			scat[i] = ((CatalogElement) catalogs[i]).toString();
		catalog.setItems(scat);
		catalog.select(0);
		handleCatalogChanged();
	}

	protected void handleCatalogChanged() {
		cube.removeAll();
		int ind = catalog.getSelectionIndex();
		DataSourceTreeElement[] cubes = catalogs[ind].getChildren();
		if (cubes == null || cubes.length == 0)
			return;

		String[] scubes = new String[cubes.length];
		for (int i = 0; i < cubes.length; i++)
			scubes[i] = ((CubeElement) cubes[i]).toString();
		cube.setItems(scubes);
		cube.select(0);
	}

	protected void handleMetaDataChanged(ServerMetadata smd) {
		datasource.removeAll();
		catalog.removeAll();
		cube.removeAll();
		dstes = smd.discoverDataSources();
		if (dstes == null || dstes.length == 0)
			return;

		String[] dsources = new String[dstes.length];
		for (int i = 0; i < dstes.length; i++)
			dsources[i] = dstes[i].getDataSourceInfo();
		datasource.setItems(dsources);
		datasource.select(0);
	}
}
