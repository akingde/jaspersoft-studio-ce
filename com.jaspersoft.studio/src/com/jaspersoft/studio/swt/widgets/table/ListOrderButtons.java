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
		Button upField = new Button(composite, SWT.PUSH);
		upField.setText(Messages.common_up);
		upField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		upField.addSelectionListener(new ElementOrderChanger(tableViewer, true));

		Button downFields = new Button(composite, SWT.PUSH);
		downFields.setText(Messages.common_down);
		downFields.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		downFields.addSelectionListener(new ElementOrderChanger(tableViewer, false));
	}
}
