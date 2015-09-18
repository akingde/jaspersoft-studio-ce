/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItemProperty;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.property.itemproperty.WItemProperty;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedListener;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class FormItemDialog extends AItemDialog {

	public FormItemDialog(Shell parentShell, ADescriptor descriptor,
			JasperReportsConfiguration jrConfig) {
		super(parentShell, descriptor, jrConfig);
	}

	@Override
	protected abstract void createValue(CTabFolder tabFolder);

	@Override
	protected void fillData() {
		for (String key : map.keySet()) {
			WItemProperty expr = map.get(key);
			expr.setExpressionContext(currentExpContext);
			boolean createNew = true;
			for (ItemProperty ip : item.getProperties()) {
				if (ip.getName().equals(key)) {
					expr.setValue((StandardItemProperty) ip);
					createNew = false;
					break;
				}
			}
			if (createNew) {
				StandardItemProperty p = new StandardItemProperty();
				p.setName(key);
				expr.setValue(p);
				item.addItemProperty(p);
			}
		}
		super.fillData();
	}

	protected Map<String, WItemProperty> map = new HashMap<String, WItemProperty>();

	protected void createItemProperty(Composite cmp, String key) {
		ItemPropertyDescription<?> ipd = descriptor.getDescription(key);

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(ipd.getLabel());
		lbl.setToolTipText(ipd.getDescription());

		final WItemProperty expr = new WItemProperty(cmp, SWT.NONE, 1,
				descriptor, ipd);
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expr.setToolTipText(ipd.getDescription());
		expr.addModifyListener(new ItemPropertyModifiedListener() {

			@Override
			public void itemModified(ItemPropertyModifiedEvent event) {
				validateForm();
			}
		});
		map.put(key, expr);
	}

	@Override
	protected void setupExpressionContext() {
		super.setupExpressionContext();

		for (WItemProperty w : map.values())
			w.setExpressionContext(currentExpContext);
	}
}
