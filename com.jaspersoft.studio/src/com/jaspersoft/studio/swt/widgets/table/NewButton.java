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

public class NewButton {

	private Button newB;

	private final class NewListener extends SelectionAdapter {

		private TableViewer tableViewer;
		private INewElement newElement;

		private NewListener(TableViewer tableViewer, INewElement newElement) {
			this.tableViewer = tableViewer;
			this.newElement = newElement;
		}

		// Remove the selection and refresh the view
		@SuppressWarnings("rawtypes")
		@Override
		public void widgetSelected(SelectionEvent e) {
			StructuredSelection s = (StructuredSelection) tableViewer.getSelection();

			List inlist = (List) tableViewer.getInput();
			if (inlist == null) {
				inlist = new ArrayList();
				tableViewer.setInput(inlist);
			}
			int index = -1;
			if (!s.isEmpty())
				index = inlist.indexOf(s.getFirstElement());
			Object selement = newElement.newElement(inlist, index);
			if (selement != null) {
				if (selement instanceof Object[]) {
					for (Object se : (Object[]) selement) {
						addElement(index, inlist, se);
					}
				} else
					addElement(index, inlist, selement);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(selement));
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void addElement(int index, List inlist, Object selement) {
			if (index >= 0 && index < inlist.size())
				inlist.add(index, selement);
			else
				inlist.add(selement);
		}
	}

	public void createNewButtons(Composite composite, TableViewer tableViewer, INewElement newElement) {
		newB = new Button(composite, SWT.PUSH);
		newB.setText(Messages.common_add);
		newB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newB.addSelectionListener(new NewListener(tableViewer, newElement));
	}

	public void setButtonText(String text){
		newB.setText(text);
	}
	public void setEnabled(boolean enable) {
		newB.setEnabled(enable);
	}
}
