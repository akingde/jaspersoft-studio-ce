/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets.table;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class DeleteButton {
	private boolean confirm = false;
	private Button delB;

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
				boolean c = confirm;
				try {
					for (Object obj : s.toArray()) {
						boolean confirmDelete = confirmDelete(obj);
						confirm = false;
						if (!confirmDelete)
							return;
						int ind = inlist.indexOf(obj);
						inlist.remove(obj);
						afterElementDeleted(obj);
						if (ind < inlist.size()) {
							selement = inlist.get(ind);
						}
					}
				} finally {
					confirm = c;
				}
				tableViewer.refresh();
				if (selement != null)
					tableViewer.setSelection(new StructuredSelection(selement));
			}
		}
	}

	protected boolean confirmDelete(Object obj) {
		if (confirm && !UIUtils.showDeleteConfirmation(delB.getShell(),
				net.sf.jasperreports.eclipse.messages.Messages.UIUtils_3))
			return false;
		return canRemove(obj);
	}

	protected boolean canRemove(Object obj) {
		return true;
	}

	public void createDeleteButton(Composite composite, final TableViewer tableViewer, boolean confirm) {
		this.confirm = confirm;
		createDeleteButton(composite, tableViewer);
	}

	public void createDeleteButton(Composite composite, final TableViewer tableViewer) {
		delB = new Button(composite, SWT.PUSH);
		delB.setText(Messages.common_delete);
		delB.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		final DeleteListener listener = new DeleteListener(tableViewer);
		delB.addSelectionListener(listener);
		setEnabledState(tableViewer);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				setEnabledState(tableViewer);
			}
		});
		tableViewer.getTable().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (delB.isEnabled() && (e.keyCode == SWT.DEL || e.keyCode == SWT.BS))
					listener.widgetSelected(null);
			}
		});
	}

	public void setEnabled(boolean enabled) {
		delB.setEnabled(enabled);
	}

	private int minSize = 0;

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	private void setEnabledState(final TableViewer tableViewer) {
		boolean enable = true;
		StructuredSelection s = (StructuredSelection) tableViewer.getSelection();
		List<?> inlist = (List<?>) tableViewer.getInput();
		if (inlist != null && inlist.size() > minSize && !s.isEmpty()) {
			for (Object obj : s.toArray()) {
				if (!canRemove(obj)) {
					enable = false;
					break;
				}
			}
		} else
			enable = false;
		delB.setEnabled(enable);
	}

	/**
	 * Additional operations to be performed once the element has been deleted.
	 * 
	 * @param element
	 */
	protected void afterElementDeleted(Object element) {
		// empty...
	}
}
