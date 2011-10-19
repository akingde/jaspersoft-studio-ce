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
import net.sf.jasperreports.engine.type.OrientationEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.UIUtils;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class PageSizeSection extends AbstractSection {
	private Spinner height;
	private Spinner width;
	private ToolItem landscapeButton;
	private ToolItem portraitButton;
	private Composite composite;

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

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.PageSizeSection_page_size + ":", SWT.RIGHT); //$NON-NLS-1$
		RowData rd = new RowData();
		rd.width = 100;
		label.setLayoutData(rd);

		width = new Spinner(composite, SWT.BORDER);
		width.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		width.setToolTipText(Messages.PageSizeSection_width_tool_tip);
		width.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_PAGE_WIDTH, new Integer(width.getSelection()));
			}
		});

		height = new Spinner(composite, SWT.BORDER);
		height.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		height.setToolTipText(Messages.PageSizeSection_height_tool_tip);
		height.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_PAGE_HEIGHT, new Integer(height.getSelection()));
			}
		});

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		toolBar.setBackground(composite.getBackground());

		portraitButton = new ToolItem(toolBar, SWT.RADIO);
		portraitButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_ORIENTATION, OrientationEnum.PORTRAIT);
			}
		});
		portraitButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/portrait16.png")); //$NON-NLS-1$
		portraitButton.setText(Messages.common_portrait);
		portraitButton.setToolTipText(Messages.PageSizeSection_portrait_tool_tip);

		landscapeButton = new ToolItem(toolBar, SWT.RADIO);
		landscapeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_ORIENTATION, OrientationEnum.LANDSCAPE);
			}
		});
		landscapeButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/landscape16.png")); //$NON-NLS-1$
		landscapeButton.setText(Messages.common_landscape);
		landscapeButton.setToolTipText(Messages.PageSizeSection_landscape_tool_tip);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {

			UIUtils.setSpinnerSelection(height, element.getPropertyValue(JasperDesign.PROPERTY_PAGE_HEIGHT), 0);
			UIUtils.setSpinnerSelection(width, element.getPropertyValue(JasperDesign.PROPERTY_PAGE_WIDTH), 0);

			Integer orientation = (Integer) element.getPropertyValue(JasperDesign.PROPERTY_ORIENTATION);
			if (orientation != null) {
				OrientationEnum val = (OrientationEnum) EnumHelper.getSetValue(OrientationEnum.values(), orientation, 1, false);
				portraitButton.setSelection(OrientationEnum.PORTRAIT.equals(val));
				landscapeButton.setSelection(OrientationEnum.LANDSCAPE.equals(val));
			} else {
				portraitButton.setSelection(false);
				landscapeButton.setSelection(false);
			}
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}
