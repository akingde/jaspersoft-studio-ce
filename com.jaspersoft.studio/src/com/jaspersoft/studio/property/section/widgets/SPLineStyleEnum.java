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

import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPLineStyleEnum {
	private ToolItem lsSolid;
	private ToolItem lsDashed;
	private ToolItem lsDotted;
	private ToolItem lsDouble;

	public SPLineStyleEnum(Composite parent, AbstractSection section, String property) {
		createComponent(parent, section, property);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(composite.getBackground());

		lsSolid = new ToolItem(toolBar, SWT.CHECK);
		lsSolid.setImage(JaspersoftStudioPlugin.getImage("icons/resources/line-solid.png"));
		lsSolid.setToolTipText("Solid line");
		lsSolid.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				propertyChange(section, JRBasePen.PROPERTY_LINE_STYLE,
						lsSolid.getSelection() ? new Integer(LineStyleEnum.SOLID.getValue() + 1) : null);
			}
		});

		lsDashed = new ToolItem(toolBar, SWT.CHECK);
		lsDashed.setImage(JaspersoftStudioPlugin.getImage("icons/resources/line-dashed.png"));
		lsDashed.setToolTipText("Dashed line");
		lsDashed.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				propertyChange(section, JRBasePen.PROPERTY_LINE_STYLE, lsDashed.getSelection() ? new Integer(
						LineStyleEnum.DASHED.getValue() + 1) : null);
			}
		});

		lsDotted = new ToolItem(toolBar, SWT.CHECK);
		lsDotted.setImage(JaspersoftStudioPlugin.getImage("icons/resources/line-dotted.png"));
		lsDotted.setToolTipText("Dotted line");
		lsDotted.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				propertyChange(section, JRBasePen.PROPERTY_LINE_STYLE, lsDotted.getSelection() ? new Integer(
						LineStyleEnum.DOTTED.getValue() + 1) : null);
			}
		});

		lsDouble = new ToolItem(toolBar, SWT.CHECK);
		lsDouble.setImage(JaspersoftStudioPlugin.getImage("icons/resources/line-double.png"));
		lsDouble.setToolTipText("Double line");
		lsDouble.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				propertyChange(section, JRBasePen.PROPERTY_LINE_STYLE, lsDouble.getSelection() ? new Integer(
						LineStyleEnum.DOUBLE.getValue() + 1) : null);
			}
		});
	}

	public void propertyChange(AbstractSection section, String property, Integer value) {
		section.changeProperty(property, value);
	}

	public void setData(Integer b) {
		lsSolid.setSelection(b != null && b.intValue() == LineStyleEnum.SOLID.getValue() + 1);
		lsDashed.setSelection(b != null && b.intValue() == LineStyleEnum.DASHED.getValue() + 1);
		lsDotted.setSelection(b != null && b.intValue() == LineStyleEnum.DOTTED.getValue() + 1);
		lsDouble.setSelection(b != null && b.intValue() == LineStyleEnum.DOUBLE.getValue() + 1);
	}
}
