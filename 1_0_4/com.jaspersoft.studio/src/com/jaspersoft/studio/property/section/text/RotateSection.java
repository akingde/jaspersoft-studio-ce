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
import net.sf.jasperreports.engine.type.RotationEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class RotateSection extends AbstractSection {

	private ToolItem noneButton;
	private ToolItem leftButton;
	private ToolItem rightButton;
	private ToolItem upDownButton;
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

		CLabel label = getWidgetFactory().createCLabel(composite, "Rotation" + ":", SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 100;
		label.setLayoutData(rd);
		label.setToolTipText(Messages.TextSection_rotation_tool_tip);

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		toolBar.setBackground(composite.getBackground());

		noneButton = new ToolItem(toolBar, SWT.CHECK);
		noneButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/text-direction-none.png"));
		noneButton.setToolTipText("Do not rotate");

		noneButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_ROTATION, new Integer(noneButton.getSelection() ? new Integer(
						RotationEnum.NONE.getValue() + 1) : 0));
			}
		});

		leftButton = new ToolItem(toolBar, SWT.CHECK);
		leftButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_ROTATION, new Integer(leftButton.getSelection() ? new Integer(
						RotationEnum.LEFT.getValue() + 1) : 0));
			}
		});
		leftButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/text-direction-left.png")); //$NON-NLS-1$
		leftButton.setToolTipText("Rotate left");

		rightButton = new ToolItem(toolBar, SWT.CHECK);
		rightButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_ROTATION, new Integer(rightButton.getSelection() ? new Integer(
						RotationEnum.RIGHT.getValue() + 1) : 0));
			}
		});
		rightButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/text-direction-right.png")); //$NON-NLS-1$
		rightButton.setToolTipText("Rotate right");

		upDownButton = new ToolItem(toolBar, SWT.CHECK);
		upDownButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				changeProperty(JRBaseStyle.PROPERTY_ROTATION, new Integer(upDownButton.getSelection() ? new Integer(
						RotationEnum.UPSIDE_DOWN.getValue() + 1) : 0));
			}
		});
		upDownButton.setImage(JaspersoftStudioPlugin.getImage("icons/resources/text-direction-updown.png")); //$NON-NLS-1$
		upDownButton.setToolTipText("Rotate upside down");

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			noneButton.setSelection(false);
			leftButton.setSelection(false);
			rightButton.setSelection(false);
			upDownButton.setSelection(false);

			int introt = ((Integer) element.getPropertyValue(JRBaseStyle.PROPERTY_ROTATION)).intValue();
			switch (introt) {
			case 1:
				noneButton.setSelection(true);
				break;
			case 2:
				leftButton.setSelection(true);
				break;
			case 3:
				rightButton.setSelection(true);
				break;
			case 4:
				upDownButton.setSelection(true);
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
