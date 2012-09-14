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
package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseStyle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class FontSection extends AbstractSection {

	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			element.getPropertyDescriptors();
			for (Object key : widgets.keySet()) {
				widgets.get(key).setData(element, element.getPropertyActualValue(key));
			}
		}
		isRefreshing = false;
	}

	
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, Messages.common_font, true, 4);

		createWidget4Property(group, JRBaseStyle.PROPERTY_FONT_NAME, false);

		createWidget4Property(group, JRBaseStyle.PROPERTY_FONT_SIZE, false);
		
		createWidget4Property(group, MFont.FONT_INCREMENT, false);
		
		createWidget4Property(group, MFont.FONT_DECREMENT, false);

		ToolBar toolBar = new ToolBar(group, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		toolBar.setLayoutData(gd);
		createWidget4Property(toolBar, JRBaseStyle.PROPERTY_BOLD, false);
		createWidget4Property(toolBar, JRBaseStyle.PROPERTY_ITALIC, false);
		createWidget4Property(toolBar, JRBaseStyle.PROPERTY_UNDERLINE, false);
		createWidget4Property(toolBar, JRBaseStyle.PROPERTY_STRIKE_THROUGH, false);
	}

}
