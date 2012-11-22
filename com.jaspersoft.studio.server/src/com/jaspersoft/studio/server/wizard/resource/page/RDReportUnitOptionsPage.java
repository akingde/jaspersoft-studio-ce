/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import java.util.List;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MReportUnitOptions;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.utils.UIUtils;

public class RDReportUnitOptionsPage extends AResourcePage {

	public RDReportUnitOptionsPage(ANode parent, MReportUnitOptions resource) {
		super(Messages.RDReportUnitOptionsPage_id, parent, resource);
		setTitle(Messages.RDReportUnitOptionsPage_title);
		setDescription(Messages.RDReportUnitOptionsPage_desc);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createOptionsTab(tabFolder);
		if (!res.getValue().getIsNew())
			tabFolder.setSelection(1);
	}

	protected void createOptionsTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("Options");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, "Report Unit");

		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tname.setEnabled(false);

		ResourceProperty resprop = ResourceDescriptorUtil.getProperty(
				MReportUnitOptions.PROP_RU_URI, res.getValue().getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		Composite cmp = new Composite(composite, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		// TODO we should show input controls from the report unit, and give
		// possibility to change values
		// this is possible only with rest interface
		resprop = ResourceDescriptorUtil.getProperty("PROP_VALUES", res
				.getValue().getProperties());
		List<ResourceProperty> props = resprop.getProperties();
		for (ResourceProperty rp : props) {
			new Label(cmp, SWT.NONE).setText(rp.getName());

			new Label(cmp, SWT.NONE).setText(rp.getValue());
		}
	}
}
