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
package com.jaspersoft.studio.property.section.report;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class PageMarginSection extends AbstractSection {
	private Spinner bottomMargin;
	private Spinner topMargin;
	private Spinner leftMargin;
	private Spinner rightMargin;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(10, false);
		composite.setLayout(layout);

		CLabel label = getWidgetFactory().createCLabel(composite, "Margin:", SWT.RIGHT);
		GridData gd = new GridData();
		gd.widthHint = 100;
		label.setLayoutData(gd);

		CLabel l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_top.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		topMargin = new Spinner(composite, SWT.BORDER);
		topMargin.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		topMargin.setToolTipText("top margin");
		topMargin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_TOP_MARGIN, new Integer(topMargin.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_bottom.png"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		bottomMargin = new Spinner(composite, SWT.BORDER);
		bottomMargin.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		bottomMargin.setToolTipText("bottom margin");
		bottomMargin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_BOTTOM_MARGIN, new Integer(bottomMargin.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_left.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		leftMargin = new Spinner(composite, SWT.BORDER);
		leftMargin.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		leftMargin.setToolTipText("left margin");
		leftMargin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_LEFT_MARGIN, new Integer(leftMargin.getSelection()));
			}
		});

		l = new CLabel(composite, SWT.RIGHT);
		l.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/border_right.gif"));
		l.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		rightMargin = new Spinner(composite, SWT.BORDER);
		rightMargin.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		rightMargin.setToolTipText("right margin");
		rightMargin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JasperDesign.PROPERTY_RIGHT_MARGIN, new Integer(rightMargin.getSelection()));
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
			bottomMargin.setSelection(((Integer) element.getPropertyValue(JasperDesign.PROPERTY_BOTTOM_MARGIN)).intValue());
			topMargin.setSelection(((Integer) element.getPropertyValue(JasperDesign.PROPERTY_TOP_MARGIN)).intValue());
			leftMargin.setSelection(((Integer) element.getPropertyValue(JasperDesign.PROPERTY_LEFT_MARGIN)).intValue());
			rightMargin.setSelection(((Integer) element.getPropertyValue(JasperDesign.PROPERTY_RIGHT_MARGIN)).intValue());
		}
		isRefreshing = false;
	}
}
