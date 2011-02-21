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

public class NewButton {

	private final class NewListener extends SelectionAdapter {

		private TableViewer tableViewer;
		private INewElement newElement;

		private NewListener(TableViewer tableViewer, INewElement newElement) {
			this.tableViewer = tableViewer;
			this.newElement = newElement;
		}

		// Remove the selection and refresh the view
		public void widgetSelected(SelectionEvent e) {
			StructuredSelection s = (StructuredSelection) tableViewer.getSelection();

			List inlist = (List) tableViewer.getInput();
			Object selement = newElement.newElement(inlist);
			if (selement != null) {
				int index = -1;
				if (!s.isEmpty())
					index = inlist.indexOf(s.getFirstElement());
				if (index >= 0 && index < inlist.size())
					inlist.add(index, selement);
				else
					inlist.add(selement);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(selement));
			}
		}
	}

	public void createOrderButtons(Composite composite, TableViewer tableViewer, INewElement newElement) {
		Button delB = new Button(composite, SWT.PUSH | SWT.CENTER);
		delB.setText(Messages.common_add);
		delB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		delB.addSelectionListener(new NewListener(tableViewer, newElement));
	}
}
