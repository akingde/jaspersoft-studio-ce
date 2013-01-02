/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
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
		l.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/border_top.gif")); //$NON-NLS-1$

		createWidget4Property(group, JasperDesign.PROPERTY_TOP_MARGIN, false);

		l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/border_bottom.png")); //$NON-NLS-1$ 

		createWidget4Property(group, JasperDesign.PROPERTY_BOTTOM_MARGIN, false);

		l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/border_left.gif")); //$NON-NLS-1$ 

		createWidget4Property(group, JasperDesign.PROPERTY_LEFT_MARGIN, false);

		l = getWidgetFactory().createCLabel(group, "", SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/border_right.gif")); //$NON-NLS-1$ 

		createWidget4Property(group, JasperDesign.PROPERTY_RIGHT_MARGIN, false);
	}

}
