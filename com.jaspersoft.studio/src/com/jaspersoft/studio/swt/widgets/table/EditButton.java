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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

public class EditButton<T> {

	private Button editB;
	private EditListener listener;

	private final class EditListener extends SelectionAdapter {

		private TableViewer tableViewer;
		private IEditElement<T> editElement;

		private EditListener(TableViewer tableViewer, IEditElement<T> newElement) {
			this.tableViewer = tableViewer;
			this.editElement = newElement;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			StructuredSelection s = (StructuredSelection) tableViewer.getSelection();

			List<T> inlist = (List<T>) tableViewer.getInput();
			if (inlist == null) {
				inlist = new ArrayList<T>();
				tableViewer.setInput(inlist);
			}
			int index = -1;
			if (!s.isEmpty())
				index = inlist.indexOf(s.getFirstElement());
			else
				return;
			editElement.editElement(inlist, index);

			tableViewer.refresh();
			tableViewer.setSelection(s);
			tableViewer.reveal(s.getFirstElement());
		}

	}

	public void createEditButtons(Composite composite, TableViewer tableViewer, IEditElement<T> editElement) {
		editB = new Button(composite, SWT.PUSH);
		editB.setText(Messages.common_edit);
		editB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		listener = new EditListener(tableViewer, editElement);
		editB.addSelectionListener(listener);
	}

	public void push() {
		listener.widgetSelected(null);
	}

	public void setButtonText(String text) {
		editB.setText(text);
	}

	public void setEnabled(boolean enable) {
		editB.setEnabled(enable);
	}
}
