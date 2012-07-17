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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPNumber extends ASPropertyWidget {
	protected Text ftext;

	public SPNumber(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return ftext;
	}

	private Number min;
	private Number max;

	public void setBorders(Number min, Number max) {
		this.min = min;
		this.max = max;
	}

	protected void createComponent(Composite parent) {
		ftext = section.getWidgetFactory().createText(parent, "", SWT.RIGHT);
		ftext.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				try {
					if (!isRefresh) {
						Number newValue = null;
						String tmp = ftext.getText();
						if (tmp != null && !tmp.trim().isEmpty()) {
							if (numType == Double.class)
								newValue = new Double(tmp);
							else if (numType == Integer.class)
								newValue = new Integer(tmp);
							else if (numType == Float.class)
								newValue = new Float(tmp);
						}
						if (newValue != null) {
							if (min != null) {
								if (min.doubleValue() > newValue.doubleValue())
									newValue = min;
							}
							if (max != null) {
								if (max.doubleValue() < newValue.doubleValue())
									newValue = max;
							}
						}

						if (!section.changeProperty(pDescriptor.getId(), newValue)) {
							setData(section.getElement(), newValue);
						}
					}
				} catch (NumberFormatException nfe) {
				}

			}
		});
		ftext.setToolTipText(pDescriptor.getDescription());
		setWidth(parent, 6);
	}

	protected void setWidth(Composite parent, int chars) {
		GC gc = new GC(ftext);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int w = fontMetrics.getAverageCharWidth() * chars;
		gc.dispose();
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = w;
			ftext.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData rd = new GridData();
			rd.widthHint = w;
			ftext.setLayoutData(rd);
		}
	}

	boolean isRefresh = false;
	protected Class<? extends Number> numType;

	public void setNumType(Class<? extends Number> numType) {
		this.numType = numType;
	}

	public void setData(APropertyNode pnode, Object b) {
		Number n = (Number) b;
		isRefresh = true;
		setDataNumber(n);
		isRefresh = false;
		if (n != null)
			numType = n.getClass();
	}

	public void setDataNumber(Number f) {
		if (f != null) {
			int oldpos = ftext.getCaretPosition();
			ftext.setText(f.toString());
			if (f.toString().length() >= oldpos)
				ftext.setSelection(oldpos, oldpos);
		} else
			ftext.setText("");
	}

}
