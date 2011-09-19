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
package com.jaspersoft.studio.swt.widgets.table;

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

public class DeleteButton {

	private final class DeleteListener extends SelectionAdapter {

		private TableViewer tableViewer;

		private DeleteListener(TableViewer tableViewer) {
			this.tableViewer = tableViewer;
		}

		// Remove the selection and refresh the view
		public void widgetSelected(SelectionEvent e) {
			StructuredSelection s = (StructuredSelection) tableViewer.getSelection();
			Object selement = null;
			if (!s.isEmpty()) {
				List<?> inlist = (List<?>) tableViewer.getInput();
				for (Object obj : s.toArray()) {
					int ind = inlist.indexOf(obj);
					inlist.remove(obj);
					if (ind < inlist.size())
						selement = inlist.get(ind);
				}
				tableViewer.refresh();
				if (selement != null)
					tableViewer.setSelection(new StructuredSelection(selement));
			}
		}
	}

	public void createDeleteButton(Composite composite, TableViewer tableViewer) {
		Button delB = new Button(composite, SWT.PUSH);
		delB.setText(Messages.common_delete);
		delB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		delB.addSelectionListener(new DeleteListener(tableViewer));
	}
}
