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
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.TextChangeHelper;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class BordersSection extends AbstractSection {
	private Spinner xText;
	private Spinner yText;
	private Spinner widthText;
	private Spinner heightText;
	private CCombo positionType;
	private CCombo stretchType;

	/**
	 * A helper to listen for events that indicate that a text field has been changed.
	 */
	private TextChangeHelper listener = new TextChangeHelper() {
		public void textChanged(Control control) {
			try {
				Integer x = Integer.parseInt(xText.getText());

				Integer oldX = (Integer) getElement().getPropertyValue(JRBaseElement.PROPERTY_X);
				if (!x.equals(oldX)) {
					CommandStack cs = getEditDomain().getCommandStack();

					SetValueCommand setCommand = new SetValueCommand(getElement().getDisplayText());
					setCommand.setTarget(getElement());
					setCommand.setPropertyId(JRBaseElement.PROPERTY_X);
					setCommand.setPropertyValue(x);
					cs.execute(setCommand);
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		getElement();

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout layout = new GridLayout(5, false);
		composite.setLayout(layout);

		getWidgetFactory().createCLabel(composite, "Position:", SWT.RIGHT);

		xText = new Spinner(composite, SWT.BORDER);
		xText.setValues(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
		xText.setToolTipText("X position");

		yText = new Spinner(composite, SWT.BORDER);
		yText.setValues(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
		yText.setToolTipText("Y position");

		getWidgetFactory().createCLabel(composite, "Position Type:");
		positionType = new CCombo(composite, SWT.BORDER | SWT.FLAT);
		positionType.setItems(EnumHelper.getEnumNames(PositionTypeEnum.values(), NullEnum.NOTNULL));

		tabbedPropertySheetPage.getSite();
		getEditDomain();

		getWidgetFactory().createCLabel(composite, "Size:", SWT.RIGHT);

		widthText = new Spinner(composite, SWT.BORDER);
		widthText.setValues(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
		widthText.setToolTipText("width");

		heightText = new Spinner(composite, SWT.BORDER);
		heightText.setValues(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 10);
		heightText.setToolTipText("height");

		getWidgetFactory().createCLabel(composite, "Stretch Type:");
		stretchType = new CCombo(composite, SWT.BORDER | SWT.FLAT);
		stretchType.setItems(EnumHelper.getEnumNames(StretchTypeEnum.values(), NullEnum.NOTNULL));
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		listener.startNonUserChange();
		try {
			APropertyNode element = getElement();
			xText.setSelection(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_X)).intValue());
			yText.setSelection(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_Y)).intValue());
			positionType.select(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_POSITION_TYPE)).intValue());

			widthText.setSelection(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_WIDTH)).intValue());
			heightText.setSelection(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT)).intValue());
			stretchType.select(((Integer) element.getPropertyValue(JRDesignElement.PROPERTY_STRETCH_TYPE)).intValue());
		} finally {
			listener.finishNonUserChange();
		}
	}
}
