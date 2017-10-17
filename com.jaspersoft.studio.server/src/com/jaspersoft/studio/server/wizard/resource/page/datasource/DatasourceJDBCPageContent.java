/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.util.SecretsUtil;

public class DatasourceJDBCPageContent extends APageContent {

	private Text tdriver;
	private Text turl;
	private Text tuser;
	private Text tpass;
	private ClasspathComponent cpath;

	public DatasourceJDBCPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DatasourceJDBCPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.datasource.jdbc";
	}

	@Override
	public String getName() {
		return Messages.RDDatasourceJDBCPage_DatasourceTabItem;
	}

	public Control createContent(Composite parent) {
		return createContent(parent, true, true);
	}

	public Control createContent(Composite parent, boolean rebind, boolean importButton) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		UIUtil.createLabel(composite, Messages.RDDatasourceJDBCPage_Driver);

		tdriver = new Text(composite, SWT.BORDER);
		tdriver.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.RDDatasourceJDBCPage_URL);

		turl = new Text(composite, SWT.BORDER);
		turl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.RDDatasourceJDBCPage_User);

		tuser = new Text(composite, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.RDDatasourceJDBCPage_Password);

		tpass = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		TimeZoneProperty.addTimeZone(res, composite);
		if (importButton)
			createImportButton(composite, tdriver, turl, tuser, tpass);
		// try {
		// if (res.getWsClient().isSupported(Feature.SEARCHREPOSITORY)) {
		// Label lbl = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.horizontalSpan = 2;
		// lbl.setLayoutData(gd);
		//
		// new Label(composite, SWT.NONE);
		//
		// lbl = new Label(composite, SWT.NONE | SWT.WRAP);
		// lbl.setText(
		// "You can upload jdbc drivers to the server. Warning, drivers are
		// global on the server, be careful.");
		// gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.widthHint = 300;
		// lbl.setLayoutData(gd);
		//
		// new Label(composite, SWT.NONE);
		//
		// cpath = new ClasspathComponent(composite) {
		// @Override
		// protected void handleClasspathChanged() {
		// ResourceDescriptor rd = res.getValue();
		// rd.setValue(getClasspaths());
		// }
		// };
		// cpath.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		if (rebind)
			rebind();
		return composite;
	}

	@Override
	protected void rebind() {
		ResourceDescriptor rd = res.getValue();
		bindingContext.bindValue(SWTObservables.observeText(tdriver, SWT.Modify),
				PojoObservables.observeValue(rd, "driverClass")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(turl, SWT.Modify),
				PojoObservables.observeValue(rd, "connectionUrl")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(tuser, SWT.Modify),
				PojoObservables.observeValue(rd, "username")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(tpass, SWT.Modify),
				PojoObservables.observeValue(rd, "password")); //$NON-NLS-1$
		if (cpath != null && res.getValue() != null && res.getValue() instanceof List)
			cpath.setClasspaths((List<String>) res.getValue());
	}

	protected void createImportButton(Composite composite, final Text tdriver, final Text turl, final Text tuser,
			final Text tpass) {
		Button importDA = new Button(composite, SWT.NONE);
		importDA.setText(Messages.RDDatasourceJDBCPage_ImportButton);
		importDA.setToolTipText(Messages.RDDatasourceJDBCPage_ImportButtonTooltip);
		importDA.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false, 2, 1));
		importDA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportDataSourceInfoFromDA<JdbcDataAdapter> dialog = new ImportDataSourceInfoFromDA<JdbcDataAdapter>(
						UIUtils.getShell(), "JDBC", JdbcDataAdapter.class); //$NON-NLS-1$
				if (dialog.open() == Window.OK) {
					// get information from the selected DA
					JdbcDataAdapter da = dialog.getSelectedDataAdapter();
					if (da != null) {
						tdriver.setText(da.getDriver());
						turl.setText(da.getUrl());
						tuser.setText(da.getUsername());
						tpass.setText(getDecodedPassword(da.getPassword()));
					} else {
						tdriver.setText(""); //$NON-NLS-1$
						turl.setText(""); //$NON-NLS-1$
						tuser.setText(""); //$NON-NLS-1$
						tpass.setText(""); //$NON-NLS-1$
					}
				}
			}
		});
	}

	/*
	 * Password could be stored in the secure preferences, therefore it tries to
	 * decode it. Fallback case will return the same password, ensuring
	 * back-compatibility.
	 */
	private String getDecodedPassword(String encodedPasswd) {
		JasperReportsConfiguration jrconfig = res.getJasperConfiguration();
		if (jrconfig == null)
			jrconfig = JasperReportsConfiguration.getDefaultInstance();
		SecretsUtil secretsUtil = SecretsUtil.getInstance(jrconfig);
		return secretsUtil.getSecret(AbstractDataAdapterService.SECRETS_CATEGORY, encodedPasswd);
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.adapter_JDBC";
	}
}
