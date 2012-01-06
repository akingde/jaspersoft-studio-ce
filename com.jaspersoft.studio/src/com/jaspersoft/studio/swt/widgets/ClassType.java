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
package com.jaspersoft.studio.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.utils.Misc;

public class ClassType {
	private Text factoryText;
	private Button btnNewButton;

	public ClassType(Composite parent, String tooltip) {
		createComponent(parent, tooltip);
	}

	public void createComponent(Composite parent, String tooltip) {
		factoryText = new Text(parent, SWT.BORDER);
		factoryText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		factoryText.setToolTipText(tooltip);

		btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String classname = ClassTypeCellEditor.getJavaClassDialog(factoryText.getShell(), null);
				if (classname != null)
					factoryText.setText(classname);
			}
		});
		btnNewButton.setText("...");
	}

	public Text getControl() {
		return factoryText;
	}

	public void setClassType(String classtype) {
		factoryText.setText(Misc.nvl(classtype, ""));
	}

	public String getClassType() {
		return factoryText.getText().trim();
	}

	public void addListener(ModifyListener listener) {
		factoryText.addModifyListener(listener);
	}

	public void removeListener(ModifyListener listener) {
		factoryText.removeModifyListener(listener);
	}
}
