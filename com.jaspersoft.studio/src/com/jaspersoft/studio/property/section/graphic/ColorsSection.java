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
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.type.ModeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ColorsSection extends AbstractSection {
	private CCombo modeType;
	private Button backButton;
	private Composite composite;
	private Button foreButton;
	private ColorLabelProvider colorLabelProvider = new ColorLabelProvider(null);

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(6, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.common_backcolor + ":", SWT.RIGHT); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);

		backButton = new Button(composite, SWT.FLAT);
		backButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(composite.getShell());
				cd.setText(Messages.ColorsSection_element_backcolor);
				cd.setRGB((RGB) getElement().getPropertyValue(JRBaseStyle.PROPERTY_BACKCOLOR));
				RGB newColor = cd.open();
				if (newColor != null)
					changeProperty(JRBaseStyle.PROPERTY_BACKCOLOR, newColor);
			}
		});
		backButton.setToolTipText(Messages.ColorsSection_element_backcolor_tool_tip);
		gd = new GridData();
		gd.widthHint = 30;
		backButton.setLayoutData(gd);
		getWidgetFactory().createCLabel(composite, Messages.common_forecolor + ":", SWT.RIGHT); //$NON-NLS-1$

		foreButton = new Button(composite, SWT.FLAT);
		foreButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(composite.getShell());
				cd.setText(Messages.ColorsSection_element_forecolor);
				cd.setRGB((RGB) getElement().getPropertyValue(JRBaseStyle.PROPERTY_FORECOLOR));
				RGB newColor = cd.open();
				if (newColor != null)
					changeProperty(JRBaseStyle.PROPERTY_FORECOLOR, newColor);
			}
		});
		foreButton.setToolTipText(Messages.ColorsSection_element_forecolor_tool_tip);
		gd = new GridData();
		gd.widthHint = 30;
		foreButton.setLayoutData(gd);

		getWidgetFactory().createCLabel(composite, Messages.ColorsSection_transparency + ":"); //$NON-NLS-1$
		modeType = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		modeType.setItems(EnumHelper.getEnumNames(ModeEnum.values(), NullEnum.INHERITED));
		modeType.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_MODE, new Integer(modeType.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		modeType.setToolTipText(Messages.ColorsSection_transparency_tool_tip);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			RGB backcolor = (RGB) element.getPropertyValue(JRBaseStyle.PROPERTY_BACKCOLOR);

			if (backcolor != null)
				backButton.setImage(colorLabelProvider.getImage(backcolor));
			else
				backButton.setImage(null);

			RGB foreColor = (RGB) element.getPropertyValue(JRBaseStyle.PROPERTY_FORECOLOR);
			if (foreColor != null)
				foreButton.setImage(colorLabelProvider.getImage(foreColor));
			else
				foreButton.setImage(null);
			modeType.select(((Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_MODE)).intValue());
		}
		isRefreshing = false;
	}
}
