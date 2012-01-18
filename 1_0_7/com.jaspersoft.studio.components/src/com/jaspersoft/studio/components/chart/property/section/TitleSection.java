/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.property.section;

import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPEdgeEnum;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.property.section.widgets.SPFont;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class TitleSection extends AbstractSection {
	private SPColor foreButton;
	private SPExpression expr;
	private SPEdgeEnum btnEdgeEnum;
	private SPFont btnFont;
	private Composite composite;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));

		composite = createNewRow(parent);

		CLabel lbl = getWidgetFactory().createCLabel(composite,
				"Title:", SWT.RIGHT); //$NON-NLS-1$
		RowData rd = new RowData();
		rd.width = 100;
		lbl.setLayoutData(rd);

		Composite cmp = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());

		expr = new SPExpression(cmp, this,
				JRDesignChart.PROPERTY_TITLE_EXPRESSION);

		btnFont = new SPFont(parent, this, JRDesignChart.PROPERTY_TITLE_FONT);

		composite = createNewRow(parent);

		lbl = getWidgetFactory().createCLabel(composite,
				Messages.common_forecolor + ":", SWT.RIGHT); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 100;
		lbl.setLayoutData(rd);

		foreButton = new SPColor(composite, this,
				JRBaseChart.PROPERTY_TITLE_COLOR,
				Messages.ColorsSection_element_forecolor_tool_tip);

		btnEdgeEnum = new SPEdgeEnum(parent, this,
				JRBaseChart.PROPERTY_TITLE_POSITION);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			foreButton.setData((RGB) element
					.getPropertyValue(JRBaseChart.PROPERTY_TITLE_COLOR));

			btnEdgeEnum.setData((Integer) element
					.getPropertyValue(JRBaseChart.PROPERTY_TITLE_POSITION));

			btnFont.setData(element, (MFont) element
					.getPropertyValue(JRDesignChart.PROPERTY_TITLE_FONT));

			expr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignChart.PROPERTY_TITLE_EXPRESSION));
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}
