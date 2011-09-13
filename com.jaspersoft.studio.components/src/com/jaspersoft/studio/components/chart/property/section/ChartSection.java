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

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.property.widget.BtnClassType;
import com.jaspersoft.studio.components.chart.property.widget.BtnEvaluationTime;
import com.jaspersoft.studio.components.chart.property.widget.BtnRWCombo;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ChartSection extends AbstractSection {
	private BtnEvaluationTime evaluationTime;
	private BtnRWCombo rendererType;
	private Composite composite;
	private BtnRWCombo theme;
	private BtnClassType classtype;

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

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = getWidgetFactory().createCLabel(composite,
				Messages.MChart_customizer_class, SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 130;
		lbl.setLayoutData(rd);

		classtype = new BtnClassType(composite, this,
				JRDesignChart.PROPERTY_CUSTOMIZER_CLASS,
				Messages.MChart_customizer_class_description);

		// ------------------

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite,
				Messages.MChart_renderer_type);
		rd = new RowData();
		rd.width = 130;
		lbl.setLayoutData(rd);

		rendererType = new BtnRWCombo(composite, this,
				JRBaseChart.PROPERTY_RENDER_TYPE,
				Messages.MChart_renderer_type_description, new String[] { "",
						"draw", "image", "svg" });
		// ------------

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, Messages.MChart_theme); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 130;
		lbl.setLayoutData(rd);

		theme = new BtnRWCombo(composite, this, JRBaseChart.PROPERTY_THEME,
				Messages.MChart_theme_description, new String[] { "", "aegian",
						"default", "default.spring", "generic",
						"eye.candy.sixties" });

		// --------------------
		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite,
				Messages.MChart_evaluation_time);
		rd = new RowData();
		rd.width = 130;
		lbl.setLayoutData(rd);

		evaluationTime = new BtnEvaluationTime(
				composite,
				this,
				JRDesignChart.PROPERTY_EVALUATION_TIME,
				com.jaspersoft.studio.components.chart.messages.Messages.MChart_evaluation_time_description);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			classtype.setData((String) element
					.getPropertyValue(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS));

			rendererType.setData((String) element
					.getPropertyValue(JRBaseChart.PROPERTY_RENDER_TYPE));

			theme.setData((String) element
					.getPropertyValue(JRBaseChart.PROPERTY_THEME));

			JRDesignChart chart = (JRDesignChart) element.getValue();
			JasperDesign jasperDesign = element.getJasperDesign();
			JRDataset dataset = null;
			if (chart.getDataset().getDatasetRun() != null) {
				String dsname = chart.getDataset().getDatasetRun()
						.getDatasetName();
				dataset = jasperDesign.getDatasetMap().get(dsname);
			}
			if (dataset == null)
				dataset = jasperDesign.getMainDataset();

			evaluationTime
					.setData(
							(Integer) element
									.getPropertyValue(JRDesignChart.PROPERTY_EVALUATION_TIME),
							(String) element
									.getPropertyValue(JRDesignChart.PROPERTY_EVALUATION_GROUP),
							BtnEvaluationTime.getItems(dataset));
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}
