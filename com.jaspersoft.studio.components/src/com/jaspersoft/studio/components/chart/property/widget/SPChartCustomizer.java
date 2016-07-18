/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import java.io.InputStream;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

import com.jaspersoft.jasperreports.chartcustomizers.ProxyChartCustomizer;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPClassType;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.repo.RepositoryUtil;

public class SPChartCustomizer extends SPClassType<RWComboBoxPropertyDescriptor> {

	public SPChartCustomizer(Composite parent, AbstractSection section, RWComboBoxPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		super.setData(pnode, b);
		if (b == null)
			return;
		Composite p = ftext.getParent();
		try {
			for (Control c : p.getChildren()) {
				if (c == ftext || c == btn || c == getLabel())
					continue;
				c.dispose();
			}
			if (b.equals(ProxyChartCustomizer.class.getName()))
				buildProxyUI();
			else
				buildUI(((String) b).replaceAll("\\.", "/") + ".json");
		} finally {
			p.getParent().layout(true);
		}
	}

	private void buildProxyUI() {
		// here we should build a table with add/remove/up/down buttons
		// when chart customizer is selected from the table, we should call
		// buildUI if there is a json for it

		Composite cmp = section.getWidgetFactory().createComposite(ftext.getParent());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		cmp.setLayoutData(gd);
		cmp.setLayout(new GridLayout(2, false));

		Table tbl = section.getWidgetFactory().createTable(cmp, SWT.BORDER | SWT.SINGLE);
		tbl.setLinesVisible(false);
		tbl.setHeaderVisible(false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.widthHint = 150;
		gd.heightHint = 150;
		// gd.verticalSpan = 4;
		tbl.setLayoutData(gd);

		TableViewer tviewer = new TableViewer(tbl);
		tviewer.setContentProvider(new ListContentProvider());

		Composite bcmp = section.getWidgetFactory().createComposite(cmp);
		bcmp.setLayout(new GridLayout());
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 100;
		bcmp.setLayoutData(gd);

		new NewButton().createNewButtons(bcmp, tviewer, new INewElement() {

			@Override
			public Object newElement(List<?> input, int pos) {
				// TODO add a chart customizer here
				return "ABCD";
			}
		});
		new DeleteButton().createDeleteButton(bcmp, tviewer);
		new ListOrderButtons().createOrderButtons(bcmp, tviewer);

		// read properties to get the list, populate table
		((JRDesignElement) pnode.getValue()).getPropertiesMap();
		tviewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO for selected chart customizer build ui if any
			}
		});
	}

	private void buildUI(String path) {
		InputStream in = null;
		try {
			in = RepositoryUtil.getInstance(section.getJasperReportsContext()).getInputStreamFromLocation(path);
			if (in != null) {
				GridData gd = new GridData();
				gd.horizontalSpan = 3;
				section.getWidgetFactory().createCLabel(ftext.getParent(), "here we'll have an UI for\n" + path)
						.setLayoutData(gd);
				// build ui here
			}
		} catch (

		JRException e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(in);
		}
	}
}
