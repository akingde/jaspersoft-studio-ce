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

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.type.PositionTypeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.UIUtils;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class LocationSection extends AbstractSection {
	private Spinner xText;
	private Spinner yText;
	private CCombo positionType;
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

		CLabel label = getWidgetFactory().createCLabel(composite, Messages.LocationSection_position + ":", SWT.RIGHT); //$NON-NLS-1$
		RowData rd = new RowData();
		rd.width = 100;
		label.setLayoutData(rd);

		xText = new Spinner(composite, SWT.BORDER);
		xText.setValues(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
		xText.setToolTipText(Messages.LocationSection_x_position_tool_tip);

		yText = new Spinner(composite, SWT.BORDER);
		yText.setValues(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
		yText.setToolTipText(Messages.LocationSection_y_position_tool_tip);

		label = getWidgetFactory().createCLabel(composite, Messages.common_position_type + ":"); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 100;
		label.setLayoutData(rd);

		positionType = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		positionType.setItems(EnumHelper.getEnumNames(PositionTypeEnum.values(), NullEnum.NOTNULL));
		positionType.setToolTipText(Messages.LocationSection_position_type_tool_tip);

		xText.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRDesignElement.PROPERTY_X, new Integer(xText.getSelection()));
			}
		});
		yText.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRDesignElement.PROPERTY_Y, new Integer(yText.getSelection()));
			}
		});
		positionType.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRDesignElement.PROPERTY_POSITION_TYPE, new Integer(positionType.getSelectionIndex()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
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
			UIUtils.setSpinnerSelection(xText, element.getPropertyValue(JRDesignElement.PROPERTY_X));
			UIUtils.setSpinnerSelection(yText, element.getPropertyValue(JRDesignElement.PROPERTY_Y));
			positionType.select(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_POSITION_TYPE)).intValue());
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}
