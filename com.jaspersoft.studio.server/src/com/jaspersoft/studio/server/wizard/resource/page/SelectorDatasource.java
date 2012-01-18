/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.wizard.resource.AddResourceWizard;
import com.jaspersoft.studio.server.wizard.resource.ResourceWizard;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;

public class SelectorDatasource {
	private Button brRepo;
	private Text jsRefDS;
	private Button brLocal;
	private Text jsLocDS;
	private Button brNone;
	private Button bLoc;
	private Button bRef;
	private MResource res;

	public void createDatasource(TabFolder tabFolder, final ANode parent,
			final MResource res) {
		this.res = res;
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("Data Source");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		brRepo = new Button(composite, SWT.RADIO);
		brRepo.setText("Data source from Repository");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brRepo.setLayoutData(gd);
		brRepo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(0);
			}
		});

		jsRefDS = new Text(composite, SWT.BORDER);
		jsRefDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bRef = new Button(composite, SWT.ARROW | SWT.DOWN);
		bRef.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RepositoryDialog rd = new RepositoryDialog(Display.getDefault()
						.getActiveShell(), parent.getRoot()) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return isDatasource(r.getValue());
					}
				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						ResourceDescriptor runit = res.getValue();
						try {
							ResourceDescriptor ref = rs.getValue();
							ref = WSClientHelper.getResource(parent, ref);
							ref.setIsReference(true);
							ref.setReferenceUri(ref.getUriString());
							ref.setParentFolder(runit.getUriString() + "_files");
							ref.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
							ref.setUriString(ref.getParentFolder() + "/"
									+ ref.getName());
							replaceDatasource(res, ref);

							jsRefDS.setText(ref.getUriString());
						} catch (Exception e1) {
							UIUtils.showError(e1);
						}
					}
				}
			}
		});

		brLocal = new Button(composite, SWT.RADIO);
		brLocal.setText("Local data source");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brLocal.setLayoutData(gd);
		brLocal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(1);
			}
		});

		jsLocDS = new Text(composite, SWT.BORDER);
		jsLocDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bLoc = new Button(composite, SWT.ARROW | SWT.DOWN);
		bLoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ResourceDescriptor runit = res.getValue();
				ResourceDescriptor ref = getDatasource(runit);
				if (ref != null && ref.getIsReference())
					ref = null;

				Shell shell = Display.getDefault().getActiveShell();
				if (ref == null) {
					AddResourceWizard wizard = new AddResourceWizard(res);
					wizard.setOnlyDatasource(true);
					WizardDialog dialog = new WizardDialog(shell, wizard);
					dialog.create();
					if (dialog.open() != Dialog.OK)
						return;
					ref = wizard.getResource().getValue();

					ref.setIsReference(false);
					ref.setParentFolder(runit.getUriString() + "_files");
					// ref.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
					ref.setUriString(ref.getParentFolder() + "/"
							+ ref.getName());

					replaceDatasource(res, ref);
				} else {
					MResource r = ResourceFactory.getResource(null,
							cloneResource(ref), -1);
					ResourceWizard wizard = new ResourceWizard(parent, r);
					WizardDialog dialog = new WizardDialog(shell, wizard);
					dialog.create();
					if (dialog.open() != Dialog.OK)
						return;
					copyDSFields(r.getValue(), ref);
				}
				jsLocDS.setText(Misc.nvl(ref.getUriString()));
			}
		});

		brNone = new Button(composite, SWT.RADIO);
		brNone.setText("Don't use any data source");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brNone.setLayoutData(gd);
		brNone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeDatasource(res);
				setEnabled(2);
			}

		});

		ResourceDescriptor r = getDatasource(res.getValue());
		if (r != null) {
			if (r.getIsReference()) {
				setEnabled(0);
			} else {
				setEnabled(1);
			}
		} else {
			setEnabled(2);
		}

	}

	private void setEnabled(int pos) {
		bRef.setEnabled(false);
		jsRefDS.setEnabled(false);

		bLoc.setEnabled(false);
		jsLocDS.setEnabled(false);

		brRepo.setSelection(false);
		brLocal.setSelection(false);
		brNone.setSelection(false);

		jsRefDS.setText("");
		jsLocDS.setText("");
		ResourceDescriptor r = getDatasource(res.getValue());
		switch (pos) {
		case 0:
			bRef.setEnabled(true);
			brRepo.setSelection(true);
			jsRefDS.setEnabled(true);
			if (r != null)
				jsRefDS.setText(Misc.nvl(r.getReferenceUri()));
			break;
		case 1:
			brLocal.setSelection(true);
			bLoc.setEnabled(true);
			jsLocDS.setEnabled(true);
			if (r != null)
				jsLocDS.setText(Misc.nvl(r.getName()));
			break;
		case 2:
			brNone.setSelection(true);
			break;
		}

	}

	protected void replaceDatasource(final MResource res, ResourceDescriptor rd) {
		ResourceDescriptor rdel = getDatasource(res.getValue());
		if (rdel != null) {
			int index = res.getValue().getChildren().indexOf(rdel);
			if (index >= 0)
				res.getValue().getChildren().remove(index);
		}
		res.getValue().getChildren().add(0, rd);
	}

	protected void removeDatasource(final MResource res) {
		ResourceDescriptor rdel = getDatasource(res.getValue());
		if (rdel != null)
			res.getValue().getChildren().remove(rdel);
	}

	private static ResourceDescriptor getDatasource(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (isDatasource(r)) {
				return r;
			}
		}
		return null;
	}

	public static ResourceDescriptor cloneResource(ResourceDescriptor rd) {
		ResourceDescriptor rnew = new ResourceDescriptor();
		rnew.setIsNew(rd.getIsNew());
		rnew.setIsReference(rd.getIsReference());
		rnew.setName(rd.getName());
		rnew.setLabel(rd.getLabel());
		rnew.setDescription(rd.getDescription());
		rnew.setUriString(rd.getUriString());
		rnew.setParentFolder(rd.getParentFolder());
		rnew.setDataSourceType(rd.getDataSourceType());
		rnew.setWsType(rd.getWsType());

		rnew.setJndiName(rd.getJndiName());

		rnew.setBeanMethod(rd.getBeanMethod());
		rnew.setBeanName(rd.getBeanName());

		rnew.setDriverClass(rd.getDriverClass());
		rnew.setUsername(rd.getUsername());
		rnew.setPassword(rd.getPassword());
		rnew.setConnectionUrl(rd.getConnectionUrl());
		return rnew;
	}

	public static void copyDSFields(ResourceDescriptor rd,
			ResourceDescriptor rnew) {
		rnew.setJndiName(rd.getJndiName());

		rnew.setBeanMethod(rd.getBeanMethod());
		rnew.setBeanName(rd.getBeanName());

		rnew.setDriverClass(rd.getDriverClass());
		rnew.setUsername(rd.getUsername());
		rnew.setPassword(rd.getPassword());
		rnew.setConnectionUrl(rd.getConnectionUrl());
	}

	public static boolean isDatasource(ResourceDescriptor r) {
		return r.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN)
				|| r.getWsType().equals(
						ResourceDescriptor.TYPE_DATASOURCE_CUSTOM)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC)
				|| r.getWsType()
						.equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI)
				|| r.getWsType().equals("Domain")
				|| (r.getWsType().equals("custom")
						&& r.getResourcePropertyValue("PROP_RESOURCE_TYPE") != null && r
						.getResourcePropertyValue("PROP_RESOURCE_TYPE")
						.equals("com.jaspersoft.jasperserver.api.metadata.jasperreports.domain.CustomReportDataSource"));
	}
}
