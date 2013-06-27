/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseStyle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class FontSection extends AbstractRealValueSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = createFontSection(parent);

		createWidget4Property(group, JRBaseStyle.PROPERTY_FONT_NAME, false);

		Composite testLayout = new Composite(group, SWT.NONE);
		GridData fontSizeData = new GridData();
		fontSizeData.widthHint = 65;
		fontSizeData.minimumWidth = 65;
		testLayout.setLayout(new GridLayout(1, false));
		testLayout.setLayoutData(fontSizeData);
		createWidget4Property(testLayout, JRBaseStyle.PROPERTY_FONT_SIZE, false).getControl().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));

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

	protected Composite createFontSection(Composite parent) {
		return getWidgetFactory().createSection(parent, Messages.common_font, true, 4);
	}

}
