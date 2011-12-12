/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.section.AbstractSection;

public class SPText {
	private Text ftext;

	public SPText(Composite parent, AbstractSection section, String property, String tooltip) {
		createComponent(parent, section, property, tooltip);
	}

	public void createComponent(Composite parent, final AbstractSection section, final String property, String tooltip) {
		ftext = new Text(parent, SWT.BORDER);

		ftext.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				handleTextChanged(section, property);
			}

		});
		ftext.setToolTipText(tooltip);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = 100;
			ftext.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 200;
			ftext.setLayoutData(gd);
		}
	}

	protected void handleTextChanged(final AbstractSection section, final String property) {
		section.changeProperty(property, ftext.getText());
	}

	public void setData(String f) {
		if (f != null) {
			int oldpos = ftext.getCaretPosition();
			ftext.setText(f.toString());
			if (f.toString().length() >= oldpos)
				ftext.setSelection(oldpos, oldpos);
		} else
			ftext.setText("");
	}

	public String getText() {
		return ftext.getText();
	}
}
