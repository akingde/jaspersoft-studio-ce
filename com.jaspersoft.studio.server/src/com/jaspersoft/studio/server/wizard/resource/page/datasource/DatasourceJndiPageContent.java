/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import net.sf.jasperreports.data.jndi.JndiDataAdapter;

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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.utils.UIUtil;

public class DatasourceJndiPageContent extends APageContent {

	private Text tname;

	public DatasourceJndiPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DatasourceJndiPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.datasource.jndi";
	}

	@Override
	public String getName() {
		return Messages.RDDatasourceJNDIPage_DatasourceTabItem;
	}

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		UIUtil.createLabel(composite, Messages.RDDatasourceJNDIPage_JNDIName);

		tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		TimeZoneProperty.addTimeZone(res, composite);

		Button importDA = new Button(composite, SWT.NONE);
		importDA.setText(Messages.RDDatasourceJNDIPage_ImportButton);
		importDA.setToolTipText(Messages.RDDatasourceJNDIPage_ImportButtonTooltip);
		importDA.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false, 2, 1));
		importDA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportDataSourceInfoFromDA<JndiDataAdapter> dialog = new ImportDataSourceInfoFromDA<JndiDataAdapter>(Display.getDefault().getActiveShell(), "JNDI", JndiDataAdapter.class); //$NON-NLS-1$
				if (dialog.open() == Window.OK) {
					// get information from the selected DA
					JndiDataAdapter da = dialog.getSelectedDataAdapter();
					if (da != null) {
						tname.setText(da.getDataSourceName());
					} else {
						tname.setText(""); //$NON-NLS-1$
					}
				}
			}
		});
		rebind();
		return composite;
	}

	@Override
	protected void rebind() {
		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify), PojoObservables.observeValue(res.getValue(), "jndiName")); //$NON-NLS-1$
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.adapter_jndi";
	}
}
