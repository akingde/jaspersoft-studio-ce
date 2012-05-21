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
package com.jaspersoft.studio.property.section.widgets;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.mihalis.opal.angles.AngleSlider;

import com.jaspersoft.studio.property.section.AbstractSection;

public class SPDegree extends SPNumber {
	private AngleSlider angleSlider;

	public SPDegree(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return composite;
	}

	protected void createComponent(Composite parent) {
		composite = section.getWidgetFactory().createComposite(parent);
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.wrap = true;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.center = true;
		composite.setLayout(layout);

		angleSlider = new AngleSlider(composite, SWT.NONE);
		angleSlider.setToolTipText(pDescriptor.getDescription());
		angleSlider.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isRefresh)
					ftext.setText("" + angleSlider.getSelection());
			}
		});

		super.createComponent(composite);
	}

	boolean isRefresh = false;
	private Composite composite;

	public void setDataNumber(Number f) {
		isRefresh = true;
		super.setDataNumber(f);

		if (f != null) {
			int degree = Math.abs(f.intValue());
			if (degree > 360)
				degree = BigDecimal.valueOf(degree).remainder(BigDecimal.valueOf(360)).intValue();

			angleSlider.setSelection(degree);
		} else
			angleSlider.setSelection(0);
		isRefresh = false;
	}

}
