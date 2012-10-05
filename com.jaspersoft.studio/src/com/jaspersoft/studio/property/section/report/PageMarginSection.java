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
package com.jaspersoft.studio.property.section.report;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class PageMarginSection extends AbstractSection {
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.parent,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, Messages.PageMarginSection_margin, true, 2);

		CLabel l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_top.gif")); //$NON-NLS-1$

		createWidget4Property(group, JasperDesign.PROPERTY_TOP_MARGIN, false);

		l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_bottom.png")); //$NON-NLS-1$ 

		createWidget4Property(group, JasperDesign.PROPERTY_BOTTOM_MARGIN, false);

		l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_left.gif")); //$NON-NLS-1$ 

		createWidget4Property(group, JasperDesign.PROPERTY_LEFT_MARGIN, false);

		l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_right.gif")); //$NON-NLS-1$ 

		createWidget4Property(group, JasperDesign.PROPERTY_RIGHT_MARGIN, false);
	}

}
