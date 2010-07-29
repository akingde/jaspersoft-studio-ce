/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.band;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.type.SplitTypeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class BandSection extends AbstractSection {
	private Spinner height;
	private CCombo splitType;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(5, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, "Height:", SWT.RIGHT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);

		height = new Spinner(composite, SWT.BORDER);
		height.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		height.setToolTipText("height");

		height.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				changeProperty(JRDesignBand.PROPERTY_HEIGHT, new Integer(height.getSelection()));
			}
		});

		getWidgetFactory().createCLabel(composite, "Split Type:", SWT.RIGHT);

		splitType = new CCombo(composite, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		splitType.setItems(EnumHelper.getEnumNames(SplitTypeEnum.values(), NullEnum.NULL));
		splitType.setToolTipText("Split type.");
		splitType.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRDesignBand.PROPERTY_SPLIT_TYPE, new Integer(splitType.getSelectionIndex()));
			}

			@Override
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
		height.setSelection(((Integer) element.getPropertyValue(JRDesignBand.PROPERTY_HEIGHT)).intValue());
		splitType.select(((Integer) element.getPropertyValue(JRDesignBand.PROPERTY_SPLIT_TYPE)).intValue());
		isRefreshing = false;
	}
}
