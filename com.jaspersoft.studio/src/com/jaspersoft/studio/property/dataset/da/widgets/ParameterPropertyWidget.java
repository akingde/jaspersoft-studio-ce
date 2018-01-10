/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.da.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.utils.UIUtil;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.type.ParameterEvaluationTimeEnum;
import net.sf.jasperreports.engine.util.JRClassLoader;

public class ParameterPropertyWidget implements IWidget {
	private TColumn c;
	private String daValue;
	private JRDesignDataset dataset;
	private Text tvalue;
	private Label icon;
	private Image daicon;
	private ToolItem button;
	private Label lbl;

	public ParameterPropertyWidget(TColumn c, final Composite parent, Image daicon, String daValue,
			JRDesignDataset dataset) {
		this.c = c;
		this.daValue = daValue;
		this.dataset = dataset;
		this.daicon = daicon;

		lbl = new Label(parent, SWT.NONE);
		lbl.setText(c.getLabel());

		Composite cmp = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		cmp.setLayout(layout);

		icon = new Label(cmp, SWT.NONE);
		icon.setImage(JaspersoftStudioPlugin.getInstance().getImage(MParameter.getIconDescriptor().getIcon16()));
		icon.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		tvalue = new Text(cmp, SWT.NONE | SWT.READ_ONLY);
		tvalue.setLayoutData(new GridData(GridData.FILL_BOTH));

		final ToolBar buttons = new ToolBar(cmp, SWT.FLAT);
		button = new ToolItem(buttons, SWT.PUSH);
		button.setText("...");

		Listener l = event -> {
			Menu menu = createMenu(parent);

			Rectangle bounds = tvalue.getBounds();
			Point point = tvalue.toDisplay(bounds.x, bounds.y + bounds.height + 5);
			menu.setLocation(point);
			menu.setVisible(true);
		};
		button.addListener(SWT.Selection, l);
		tvalue.addListener(SWT.MouseDown, l);
		icon.addListener(SWT.MouseDown, l);

		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		GridData gd = new GridData();
		if (c.getWeight() == -2)
			gd = new GridData(GridData.FILL_HORIZONTAL);
		if (c.getWeight() == -3)
			gd = new GridData(GridData.FILL_BOTH);
		else if (c.getWeight() > 0)
			gd.widthHint = c.getWeight();
		cmp.setLayoutData(gd);

		refresh();
	}

	private boolean refresh = false;

	public void refresh() {
		if (refresh || tvalue.isDisposed())
			return;
		setupToolTip(null);

		for (JRParameter p : dataset.getParameters())
			if (!p.isSystemDefined() && p.getPropertiesMap().containsProperty(c.getPropertyName())) {
				tvalue.setText(p.getName());
				icon.setImage(
						JaspersoftStudioPlugin.getInstance().getImage(MParameter.getIconDescriptor().getIcon16()));
				return;
			}
		tvalue.setText(Misc.nvl(daValue, "< NULL >"));
		icon.setImage(daicon);
	}

	private void setupToolTip(String def) {
		String tt = tvalue.getText();
		if (Misc.isNullOrEmpty(def))
			tt += "\nDefault: " + def;
		if (!Misc.isNullOrEmpty(c.getDescription()))
			tt += "\n\n" + c.getDescription();

		button.setToolTipText(tt);
		tvalue.setToolTipText(tt);
		icon.setToolTipText(tt);
		lbl.setToolTipText(tt);
	}

	private Menu createMenu(final Composite parent) {
		final Menu menu = new Menu(parent.getShell(), SWT.POP_UP);
		final MenuItem mid = new MenuItem(menu, SWT.PUSH);
		mid.setText(Misc.nvl(daValue, "< NULL >"));
		mid.setImage(daicon);
		mid.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tvalue.setText(mid.getText());
				icon.setImage(mid.getImage());
				setupToolTip(null);
				try {
					refresh = true;
					DatasetUtil.removeProperty(dataset, c.getPropertyName());
				} finally {
					refresh = false;
				}
			}
		});

		new MenuItem(menu, SWT.SEPARATOR);

		String cn = JRClassLoader.getClassRealName(c.getPropertyType());
		try {
			Class<?> cl = JRClassLoader.loadClassForName(cn);
			for (JRParameter p : dataset.getParameters()) {
				if (p.isSystemDefined() || !(p.getValueClass().isAssignableFrom(cl)
						|| p.getValueClass().isAssignableFrom(String.class)))
					continue;
				final MenuItem mi = new MenuItem(menu, SWT.PUSH);
				mi.setText(p.getName());
				mi.setImage(JaspersoftStudioPlugin.getInstance().getImage(MParameter.getIconDescriptor().getIcon16()));
				final String def = p.getDefaultValueExpression() != null ? p.getDefaultValueExpression().getText()
						: null;
				String tt = "Default: " + (def == null ? "" : "null");
				if (!Misc.isNullOrEmpty(p.getDescription()))
					tt += "\n\n" + p.getDescription();
				UIUtil.safeApplyMenuItemTooltip(mi, tt);
				final JRPropertiesMap pmap = p.getPropertiesMap();
				pmap.getEventSupport();
				mi.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						tvalue.setText(mi.getText());
						icon.setImage(mi.getImage());
						setupToolTip(def);
						try {
							refresh = true;
							// create property on parameter
							DatasetUtil.removeProperty(dataset, c.getPropertyName());

							pmap.setProperty(c.getPropertyName(), null);
							((JRDesignParameter) p).setEvaluationTime(ParameterEvaluationTimeEnum.EARLY);
						} finally {
							refresh = false;
						}
					}
				});
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (menu.getItemCount() == 2)
			mid.dispose();

		return menu;
	}

}
