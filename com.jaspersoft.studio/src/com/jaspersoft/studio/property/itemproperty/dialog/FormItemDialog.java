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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.itemproperty.WItemProperty;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedListener;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;

public abstract class FormItemDialog extends AItemDialog {
	private boolean showAddDatasetButton = true;

	public FormItemDialog(Shell parentShell, ADescriptor descriptor, JasperReportsConfiguration jrConfig,
			boolean showDataset) {
		super(parentShell, descriptor, jrConfig, showDataset);
	}

	public FormItemDialog(Shell parentShell, ADescriptor descriptor, JasperReportsConfiguration jrConfig,
			boolean showDataset, boolean showAddDatasetButton) {
		super(parentShell, descriptor, jrConfig, showDataset);
		this.showAddDatasetButton = showAddDatasetButton;
	}

	@Override
	public boolean close() {
		item.getProperties().clear();
		for (String key : map.keySet()) {
			item.addItemProperty(map.get(key).getValue());
		}
		return super.close();
	}

	@Override
	protected void createAddItemDataButton(Composite cmp) {
		if (showAddDatasetButton)
			super.createAddItemDataButton(cmp);
	}

	@Override
	protected void createValue(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ItemDialog_0);

		Composite cmp = createScrolledComposite(tabFolder, bptab);
		createValues(cmp);
		configScrolledComposite(cmp);
	}

	@Override
	protected void createValue(Composite parent) {
		Composite cmp = createScrolledComposite(parent);

		createValues(cmp);
		configScrolledComposite(cmp);
	}

	protected abstract void createValues(final Composite cmp);

	@Override
	protected void fillData() {
		refresh = true;
		try {
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
		} finally {
			refresh = false;
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
		lbl.setToolTipText(ipd.getToolTip());

		final WItemProperty expr = new WItemProperty(cmp, SWT.NONE, 1, descriptor, ipd);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		expr.setLayoutData(gd);
		expr.addModifyListener(new ItemPropertyModifiedListener() {

			@Override
			public void itemModified(ItemPropertyModifiedEvent event) {
				item.getProperties().clear();
				for (String key : map.keySet())
					item.addItemProperty(map.get(key).getValue());
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
		if (parent.getLayout() != null && parent.getLayout() instanceof GridLayout) {
			GridLayout l = (GridLayout) parent.getLayout();
			l.marginHeight = 0;
			l.marginWidth = 0;
			l.marginTop = 0;
		}
		sc = new ScrolledComposite(parent, SWT.V_SCROLL);
		sc.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite cmp = new Composite(sc, SWT.NONE);
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
		return createSection(parent, text, true);
	}

	protected Composite createSection(Composite parent, String text, boolean expanded) {
		Section ec = new Section(parent, Section.TREE_NODE);
		ec.setText(Misc.nvl(text));
		ec.setExpanded(expanded);
		ec.setFont(ResourceManager.getBoldFont(ec.getFont()));

		Label lbl = new Label(ec, SWT.SEPARATOR | SWT.HORIZONTAL);
		ec.setSeparatorControl(lbl);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		ec.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				sc.setMinSize(sc.getContent().computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec.setLayoutData(gd);

		Composite c = new Composite(ec, SWT.WRAP);
		c.setLayout(new GridLayout(2, false));
		ec.setClient(c);
		return c;
	}

	protected Composite createGroup(Composite parent, String text) {
		Composite ec = new Composite(parent, Section.TREE_NODE);
		ec.setLayout(new GridLayout());
		if (!Misc.isNullOrEmpty(text)) {
			Label lbl = new Label(ec, SWT.NONE);
			lbl.setText(text);
			lbl.setFont(ResourceManager.getBoldFont(lbl.getFont()));
		}
		new Label(ec, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

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

	public Label createCenteredLabel(final Composite cmp) {
		Label lbl = new Label(cmp, SWT.WRAP);
		final GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		gd.widthHint = 300;
		lbl.setLayoutData(gd);

		if (sc != null)
			sc.addControlListener(new ControlAdapter() {
				@Override
				public void controlResized(ControlEvent e) {
					gd.widthHint = Math.max(200, sc.getSize().x - 20);
				}
			});
		else
			cmp.addControlListener(new ControlAdapter() {
				@Override
				public void controlResized(ControlEvent e) {
					gd.widthHint = Math.max(200, cmp.getSize().x - 10);
					if (sc != null)
						gd.widthHint = Math.min(gd.widthHint, sc.getSize().x);
				}
			});
		return lbl;
	}
}
