/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItemProperty;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.property.itemproperty.WItemProperty;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedListener;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class FormItemDialog extends AItemDialog {

	public FormItemDialog(Shell parentShell, ADescriptor descriptor, JasperReportsConfiguration jrConfig,
			boolean showDataset) {
		super(parentShell, descriptor, jrConfig, showDataset);
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

		if (ipd == null)
			return;

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(ipd.getLabel());
		lbl.setToolTipText(ipd.getDescription());

		final WItemProperty expr = new WItemProperty(cmp, SWT.NONE, 1, descriptor, ipd);
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

	protected ScrolledComposite sc;

	protected Composite createScrolledComposite(CTabFolder tabFolder, CTabItem bptab) {
		Composite cmp = createScrolledComposite(tabFolder);
		bptab.setControl(sc);
		return cmp;
	}

	protected Composite createScrolledComposite(Composite parent) {
		sc = new ScrolledComposite(parent, SWT.V_SCROLL);

		final Composite cmp = new Composite(sc, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		sc.setContent(cmp);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setAlwaysShowScrollBars(true);
		return cmp;
	}

	protected void configScrolledComposite(final Composite cmp) {
		sc.setMinSize(cmp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sc.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				sc.setMinSize(cmp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});
	}

	protected Composite createSection(Composite parent, String text) {
		Section ec = new Section(parent, Section.TREE_NODE);
		ec.setText(text);
		ec.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				sc.setMinSize(sc.getContent().computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		Composite c = new Composite(ec, SWT.WRAP);
		c.setLayout(new GridLayout(2, false));
		ec.setClient(c);
		return c;
	}

	protected Composite createGroup(Composite parent, String text) {
		Group ec = new Group(parent, Section.TREE_NODE);
		ec.setText(text);
		ec.setLayout(new GridLayout());

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		Composite c = new Composite(ec, SWT.WRAP);
		c.setLayout(new GridLayout(2, false));
		c.setLayoutData(new GridData(GridData.FILL_BOTH));

		return c;
	}

	public static void createSeparator(Composite cmp) {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		new Label(cmp, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);
	}
}
