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
package com.jaspersoft.studio.property.section;

import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.TextChangeHelper;

/**
 * The location section on the location tab.
 * 
 * @author Anthony Hunter
 */
public class LocationSection extends AbstractSection {
	private Text xText;
	private Text yText;

	/**
	 * A helper to listen for events that indicate that a text field has been changed.
	 */
	private TextChangeHelper listener = new TextChangeHelper() {
		public void textChanged(Control control) {
			try {
				Integer x = Integer.parseInt(xText.getText());
				Integer y = Integer.parseInt(yText.getText());

				CommandStack cs = getEditDomain().getCommandStack();

				CompoundCommand cc = new CompoundCommand();

				Object oldX = getElement().getPropertyValue(JRBaseElement.PROPERTY_X);
				if (!x.equals(oldX)) {
					SetValueCommand setCommand = new SetValueCommand(getElement().getDisplayText());
					setCommand.setTarget(getElement());
					setCommand.setPropertyId(JRBaseElement.PROPERTY_X);
					setCommand.setPropertyValue(x);
					cc.add(setCommand);
				}
				if (!y.equals(getElement().getPropertyValue(JRDesignElement.PROPERTY_Y))) {
					SetValueCommand setCommand = new SetValueCommand(getElement().getDisplayText());
					setCommand.setTarget(getElement());
					setCommand.setPropertyId(JRDesignElement.PROPERTY_Y);
					setCommand.setPropertyValue(y);
					cc.add(setCommand);
				}
				cs.execute(cc);
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

		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		xText = getWidgetFactory().createText(composite, ""); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		xText.setLayoutData(data);

		CLabel xLabel = getWidgetFactory().createCLabel(composite, "X:"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(xText, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(xText, 0, SWT.CENTER);
		xLabel.setLayoutData(data);

		yText = getWidgetFactory().createText(composite, ""); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(xText, 0, SWT.LEFT);
		data.right = new FormAttachment(xText, 0, SWT.RIGHT);
		data.top = new FormAttachment(xText, ITabbedPropertyConstants.VSPACE, SWT.BOTTOM);
		yText.setLayoutData(data);

		CLabel yLabel = getWidgetFactory().createCLabel(composite, "Y:"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(yText, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(yText, 0, SWT.CENTER);
		yLabel.setLayoutData(data);

		listener.startListeningForEnter(xText);
		listener.startListeningTo(xText);
		listener.startListeningForEnter(yText);
		listener.startListeningTo(yText);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		listener.startNonUserChange();
		try {
			xText.setText(((Integer) getElement().getPropertyValue(JRBaseElement.PROPERTY_X)).toString());
			yText.setText(getElement().getPropertyValue(JRDesignElement.PROPERTY_Y).toString());
		} finally {
			listener.finishNonUserChange();
		}
	}
}
