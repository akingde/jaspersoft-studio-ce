/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.wizard.fragments.data;

import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.chart.figure.ChartFigure;
import com.jaspersoft.studio.components.chart.wizard.fragments.expr.ExpressionWidget;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;

public abstract class ADSComponent {
	private Control control;
	protected Label imgLabel;
	private ChartFigure chartFigure;
	private Canvas canvasChart;

	public ADSComponent(Composite composite) {
		createControl(composite);
	}

	public void setData(DrawVisitor drawVisitor, JRDesignChart jrChart,
			SimpleFileResolver fResolver) {
		jrChart.setWidth(500);
		jrChart.setHeight(325);
		chartFigure.setJRElement(jrChart, drawVisitor, fResolver);
		canvasChart.redraw();
	}

	public Control getControl() {
		return control;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		new Label(composite, SWT.NONE).setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		createChartTop(composite);
		new Label(composite, SWT.NONE).setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		createChartLeft(composite);

		createChartPreview(composite);

		createChartRight(composite);

		new Label(composite, SWT.NONE).setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		createChartBottom(composite);

		this.control = composite;
	}

	protected abstract Control createChartTop(Composite parent);

	protected abstract Control createChartLeft(Composite parent);

	protected abstract Control createChartRight(Composite parent);

	protected abstract Control createChartBottom(Composite parent);

	protected Control createChartPreview(Composite composite) {
		canvasChart = new Canvas(composite, SWT.BORDER | SWT.NO_REDRAW_RESIZE
				| SWT.NO_BACKGROUND);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 500;
		gd.heightHint = 325;
		canvasChart.setLayoutData(gd);

		LightweightSystem lws = new J2DLightweightSystem();
		lws.setControl(canvasChart);

		chartFigure = new ChartFigure();
		lws.setContents(chartFigure);
		return canvasChart;
	}
}
