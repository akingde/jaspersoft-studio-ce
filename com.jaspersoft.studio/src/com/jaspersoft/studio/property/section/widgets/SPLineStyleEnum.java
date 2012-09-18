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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPLineStyleEnum {
	
	ComboMenuViewer combo;

	public SPLineStyleEnum(Composite parent, AbstractSection section, String property) {
		createComponent(parent, section, property);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		List<ComboItem> itemsList = new ArrayList<ComboItem>();
		itemsList.add(new ComboItem("Inherited", true,  JaspersoftStudioPlugin.getImageDescriptor("icons/resources/inherited.png"),0, NullEnum.INHERITED, null));
		itemsList.add(new ComboItem("Solid line", true,  JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-solid.png"),1, LineStyleEnum.SOLID, new Integer(LineStyleEnum.SOLID.getValue() + 1)));
		itemsList.add(new ComboItem("Dashed line", true,  JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-dashed.png"),2, LineStyleEnum.DASHED, new Integer(LineStyleEnum.DASHED.getValue() + 1)));
		itemsList.add(new ComboItem("Dotted line", true,  JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-dotted.png"),3, LineStyleEnum.DOTTED, new Integer(LineStyleEnum.DOTTED.getValue() + 1)));
		itemsList.add(new ComboItem("Double line", true,  JaspersoftStudioPlugin.getImageDescriptor("icons/resources/line-double.png"),4, LineStyleEnum.DOUBLE, new Integer(LineStyleEnum.DOUBLE.getValue() + 1)));
		combo = new ComboMenuViewer(composite, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		
		combo.setItems(itemsList);
		combo.addSelectionListener(new ComboItemAction() {
				@Override
				public void exec() {
						propertyChange(section,JRBasePen.PROPERTY_LINE_STYLE, combo.getSelectionValue() != null ? (Integer)combo.getSelectionValue() : null);			
				}
		});
		
		/*
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
		});*/
	}

	public void propertyChange(AbstractSection section, String property, Integer value) {
		section.changeProperty(property, value);
	}

	public void setData(Integer b) {
		//lsSolid.setSelection(b != null && b.intValue() == LineStyleEnum.SOLID.getValue() + 1);
		//lsDashed.setSelection(b != null && b.intValue() == LineStyleEnum.DASHED.getValue() + 1);
		//lsDotted.setSelection(b != null && b.intValue() == LineStyleEnum.DOTTED.getValue() + 1);
		//lsDouble.setSelection(b != null && b.intValue() == LineStyleEnum.DOUBLE.getValue() + 1);
		combo.select(b);
	}
}
