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
				List inlist = (List) tableViewer.getInput();
				for (Object obj : s.toArray()) {
					int ind = inlist.indexOf(obj);
					inlist.remove(obj);
					if (ind < inlist.size())
						selement = inlist.get(ind);
				}
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(selement));
			}
		}
	}

	public void createOrderButtons(Composite composite, TableViewer tableViewer) {
		Button delB = new Button(composite, SWT.PUSH | SWT.CENTER);
		delB.setText(Messages.common_delete);
		delB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		delB.addSelectionListener(new DeleteListener(tableViewer));
	}
}
