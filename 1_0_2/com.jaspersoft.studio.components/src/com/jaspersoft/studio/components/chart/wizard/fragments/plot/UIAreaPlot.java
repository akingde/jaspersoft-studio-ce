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
package com.jaspersoft.studio.components.chart.wizard.fragments.plot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.dataset.ExpressionWidget;

public class UIAreaPlot extends APlotComponent {

	public UIAreaPlot(Composite composite) {
		super(composite);
	}

	@Override
	protected Control createChartLeft(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yCompo.setLayout(new GridLayout(3, false));

		new Label(yCompo, SWT.NONE).setText("Line");

		Button btnLineColor = new Button(yCompo, SWT.NONE);
		btnLineColor.setText("Color");

		new Label(yCompo, SWT.NONE);

		// ---------------------
		Label yLabel = new Label(yCompo, SWT.NONE);
		yLabel.setText("Label");

		Composite cmp = new Composite(yCompo, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		Button btnColor = new Button(cmp, SWT.NONE);
		btnColor.setText("Color");

		Button btnFont = new Button(cmp, SWT.NONE);
		btnFont.setText("Font");

		// -----------------------------

		yLabel = new Label(yCompo, SWT.NONE);
		yLabel.setText("Tick Label");

		cmp = new Composite(yCompo, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		Button btnShowTickColor = new Button(cmp, SWT.CHECK);
		btnShowTickColor.setText("Show Vertical Tick Labels");
		gd = new GridData();
		gd.horizontalSpan = 2;
		btnShowTickColor.setLayoutData(gd);

		Button btnTickColor = new Button(cmp, SWT.NONE);
		btnTickColor.setText("Color");

		Button btnTickFont = new Button(cmp, SWT.NONE);
		btnTickFont.setText("Font");

		new Label(cmp, SWT.NONE).setText("Mask");

		Text txtMask = new Text(cmp, SWT.BORDER);

		// -----------------------------

		yLabel = new Label(yCompo, SWT.NONE);
		yLabel.setText("Range");

		cmp = new Composite(yCompo, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		ExpressionWidget domMinWidget = new ExpressionWidget(cmp, "Min ");
		ExpressionWidget domMaxWidget = new ExpressionWidget(cmp, "Max ");

		return yCompo;
	}

	@Override
	protected Control createChartBottom(Composite parent) {
		Composite xCompo = new Composite(parent, SWT.NONE);
		xCompo.setLayout(new GridLayout(3, false));
		xCompo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

		new Label(xCompo, SWT.NONE).setText("Category Label");

		Composite cmp = new Composite(xCompo, SWT.NONE);
		cmp.setLayout(new GridLayout(5, false));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		Button clFontBtn = new Button(cmp, SWT.PUSH);
		clFontBtn.setText("Font");

		Button clColorBtn = new Button(cmp, SWT.PUSH);
		clColorBtn.setText("Color");

		ExpressionWidget categLabel1Widget = new ExpressionWidget(cmp,
				"Expression");

		ExpressionWidget categValWidget = new ExpressionWidget(cmp,
				"Value Label");
		// -----------------------------

		new Label(xCompo, SWT.NONE).setText("Tick Label");

		cmp = new Composite(xCompo, SWT.NONE);
		cmp.setLayout(new GridLayout(6, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		Button btnTickColor = new Button(cmp, SWT.NONE);
		btnTickColor.setText("Color");

		Button btnTickFont = new Button(cmp, SWT.NONE);
		btnTickFont.setText("Font");

		new Label(cmp, SWT.NONE).setText("Mask");

		Text txtMask = new Text(cmp, SWT.BORDER);

		new Label(cmp, SWT.NONE).setText("Rotation");
		Text txtRotation = new Text(cmp, SWT.BORDER);

		Button btnShowTickColor = new Button(cmp, SWT.CHECK);
		btnShowTickColor.setText("Show Vertical Tick Labels");
		gd = new GridData();
		gd.horizontalSpan = 6;
		btnShowTickColor.setLayoutData(gd);

		// ---------------------
		new Label(xCompo, SWT.NONE).setText("Axis Line");

		Button btnLineColor = new Button(xCompo, SWT.NONE);
		btnLineColor.setText("Color");
		gd = new GridData();
		gd.horizontalSpan = 2;
		btnLineColor.setLayoutData(gd);

		// ------------------------------

		new Label(xCompo, SWT.NONE).setText("Domain axis");

		cmp = new Composite(xCompo, SWT.NONE);
		cmp.setLayout(new GridLayout(10, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		ExpressionWidget domMinWidget = new ExpressionWidget(cmp, "Min");
		ExpressionWidget domMaxWidget = new ExpressionWidget(cmp, "Max");

		return xCompo;
	}

	@Override
	protected Control createChartRight(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		yCompo.setLayout(new GridLayout(3, false));

		Label yLabel = new Label(yCompo, SWT.NONE);
		yLabel.setText("some x plot configuration");

		return yCompo;
	}

	@Override
	protected Control createChartTop(Composite parent) {
		Composite yCompo = new Composite(parent, SWT.NONE);
		yCompo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		yCompo.setLayout(new GridLayout(3, false));

		Label yLabel = new Label(yCompo, SWT.NONE);
		yLabel.setText("some x plot configuration");

		return yCompo;
	}

}
