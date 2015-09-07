/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
			editB.setEnabled(false);
			try {
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
				afterElementModified(s.getFirstElement(), inlist, index);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(inlist.get(index)));
				tableViewer.reveal(s.getFirstElement());
			} finally {
				editB.setEnabled(true);
			}
		}

	}

	public void createEditButtons(Composite composite, final TableViewer tableViewer, IEditElement<T> editElement) {
		editB = new Button(composite, SWT.PUSH);
		editB.setText(Messages.common_edit);
		editB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		listener = new EditListener(tableViewer, editElement);
		editB.addSelectionListener(listener);

		editB.setEnabled(!tableViewer.getSelection().isEmpty());
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				editB.setEnabled(!tableViewer.getSelection().isEmpty());
			}
		});
	}

	public void editOnDoubleClick() {
		listener.tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				push();
			}
		});
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

	/**
	 * Additional operations to be performed once the element has been modified.
	 * 
	 * @param object
	 * @param inlist
	 *          list of elements from the table
	 * @param ind
	 *          index of the changed element in the table
	 */
	protected void afterElementModified(Object element, List<T> inlist, int ind) {
		// empty...
	}
}
