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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPColor;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ColorsSection extends AbstractSection {
	private SPColor backButton;
	private Composite composite;
	private SPColor foreButton;
	private ToolItem transparentButton;
	private ToolItem opaqueButton;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		composite = createNewRow(parent);

		CLabel lbl = getWidgetFactory().createCLabel(composite, Messages.common_forecolor + ":", SWT.RIGHT); //$NON-NLS-1$
		RowData rd = new RowData();
		rd.width = 100;
		lbl.setLayoutData(rd);

		foreButton = new SPColor(composite, this, JRBaseStyle.PROPERTY_FORECOLOR,
				Messages.ColorsSection_element_forecolor_tool_tip);

		getWidgetFactory().createCLabel(composite, Messages.common_backcolor + ":", SWT.RIGHT); //$NON-NLS-1$

		backButton = new SPColor(composite, this, JRBaseStyle.PROPERTY_BACKCOLOR,
				Messages.ColorsSection_element_backcolor_tool_tip);

		lbl = getWidgetFactory().createCLabel(composite, Messages.ColorsSection_transparency + ":"); //$NON-NLS-1$
		lbl.setToolTipText(Messages.ColorsSection_transparency_tool_tip);

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(composite.getBackground());

		transparentButton = new ToolItem(toolBar, SWT.CHECK);
		transparentButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/color-transparent.png"));
		transparentButton.setToolTipText("Transparent");

		transparentButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_MODE, transparentButton.getSelection() ? new Integer(2) : null);
			}
		});

		opaqueButton = new ToolItem(toolBar, SWT.CHECK);
		opaqueButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/color-opaque.png"));
		opaqueButton.setToolTipText("Opaque");

		opaqueButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_MODE, opaqueButton.getSelection() ? new Integer(1) : null);
			}
		});

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			RGB backcolor = (RGB) element.getPropertyValue(JRBaseStyle.PROPERTY_BACKCOLOR);
			backButton.setData(backcolor);

			RGB foreColor = (RGB) element.getPropertyValue(JRBaseStyle.PROPERTY_FORECOLOR);
			foreButton.setData(foreColor);

			transparentButton.setSelection(false);
			opaqueButton.setSelection(false);

			int intmode = ((Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_MODE)).intValue();
			switch (intmode) {
			case 2:
				transparentButton.setSelection(true);
				break;
			case 1:
				opaqueButton.setSelection(true);
				break;
			}
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}