/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.jaspersoft.studio.swt.events.ChangeEvent;
import com.jaspersoft.studio.swt.events.ChangeListener;

public class ListOrderButtons {
	private Button upField;
	private Button downFields;

	private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0

	/**
	 * Add a change listener to listen for changes on the selected fields
	 * 
	 * @param ChangeListener
	 *          a listener
	 */
	public final void addChangeListener(ChangeListener l) {
		synchronized (listeners) {
			listeners.add(l);
		}
	}

	public final void removeChangeListener(ChangeListener l) {
		synchronized (listeners) {
			listeners.remove(l);
		}
	}

	/**
	 * Method to invoke when the out fields set changes.
	 */
	protected final void fireChangeEvent() {
		Iterator<ChangeListener> it;
		synchronized (listeners) {
			it = new HashSet<ChangeListener>(listeners).iterator();
		}
		ChangeEvent ev = new ChangeEvent(this);
		while (it.hasNext()) {
			it.next().changed(ev);
		}
	}

	private final class ElementOrderChanger extends SelectionAdapter {
		private final TableViewer tableViewer;
		private boolean up;

		private ElementOrderChanger(TableViewer tableViewer, boolean up) {
			this.tableViewer = tableViewer;
			this.up = up;
		}

		@SuppressWarnings({ "rawtypes" })
		@Override
		public void widgetSelected(SelectionEvent e) {
			StructuredSelection s = (StructuredSelection) tableViewer.getSelection();
			if (!s.isEmpty()) {
				List lst = (List) tableViewer.getInput();
				int[] indxs = moveDown(lst, s);
				tableViewer.refresh();
				tableViewer.getTable().setSelection(indxs);

				fireChangeEvent();
			}
		}

		private int[] moveDown(List lst, StructuredSelection s) {
			int[] indxs = tableViewer.getTable().getSelectionIndices();
			Object[] selected = new Object[indxs.length];
			for (int i = 0; i < indxs.length; i++)
				selected[i] = lst.get(indxs[i]);
			List nlst = new ArrayList();
			for (int i = 0; i < lst.size(); i++) {
				if (Arrays.binarySearch(indxs, i) > -1)
					continue;
				nlst.add(lst.get(i));
			}
			int[] nindxs = new int[indxs.length];
			for (int i = 0; i < indxs.length; i++) {
				int index = up ? indxs[i] - 1 : indxs[i] + 1;
				if (index < 0)
					index = 0;
				if (index >= 0 && index < lst.size()) {
					nlst.add(index, selected[i]);
					nindxs[i] = index;
				} else {
					nlst.add(selected[i]);
					nindxs[i] = nlst.size() - 1;
				}
			}
			lst.clear();
			lst.addAll(nlst);
			return nindxs;
		}

	}

	public void createOrderButtons(Composite composite, final TableViewer tableViewer) {
		upField = new Button(composite, SWT.PUSH);
		upField.setText(Messages.common_up);
		upField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		upField.addSelectionListener(new ElementOrderChanger(tableViewer, true));

		downFields = new Button(composite, SWT.PUSH);
		downFields.setText(Messages.common_down);
		downFields.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		downFields.addSelectionListener(new ElementOrderChanger(tableViewer, false));
		Object obj = tableViewer.getInput();
		setEnabled(obj != null && obj instanceof Collection<?> && !((Collection<?>) obj).isEmpty());
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) event.getSelection();
				setEnabled(sel != null && sel.size() > 0 && tableViewer.getTable().getItemCount() > 1);
			}
		});
	}

	public void setEnabled(boolean enabled) {
		upField.setEnabled(enabled);
		downFields.setEnabled(enabled);
	}
}
