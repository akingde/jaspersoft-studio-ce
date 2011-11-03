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
package com.jaspersoft.studio.swt.widgets.table;

import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

public class ListOrderButtons {
	private Button upField;
	private Button downFields;

	private final class ElementOrderChanger implements SelectionListener {
		private final TableViewer tableViewer;
		private boolean up;

		private ElementOrderChanger(TableViewer tableViewer, boolean up) {
			this.tableViewer = tableViewer;
			this.up = up;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void widgetSelected(SelectionEvent e) {
			StructuredSelection s = (StructuredSelection) tableViewer.getSelection();
			if (!s.isEmpty()) {
				List lst = (List) tableViewer.getInput();
				for (Object obj : s.toArray()) {
					int index = lst.indexOf(obj);
					lst.remove(obj);
					index = up ? index - 1 : index + 1;
					if (index >= 0 && index < lst.size())
						lst.add(index, obj);
					else
						lst.add(obj);
				}
				tableViewer.refresh();
				tableViewer.setSelection(s);
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}

	public void createOrderButtons(Composite composite, TableViewer tableViewer) {
		upField = new Button(composite, SWT.PUSH);
		upField.setText(Messages.common_up);
		upField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		upField.addSelectionListener(new ElementOrderChanger(tableViewer, true));

		downFields = new Button(composite, SWT.PUSH);
		downFields.setText(Messages.common_down);
		downFields.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		downFields.addSelectionListener(new ElementOrderChanger(tableViewer, false));
	}

	public void setEnabled(boolean enabled) {
		upField.setEnabled(enabled);
		downFields.setEnabled(enabled);
	}
}
