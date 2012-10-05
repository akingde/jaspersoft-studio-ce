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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.events.ChangeEvent;
import com.jaspersoft.studio.swt.events.ChangeListener;

public class MoveT2TButtons {

	
	private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0

	/**
	 * Add a change listener to listen for changes on the selected fields
	 * 
	 * @param ChangeListener a listener
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
	
	
	private final class MoveListener implements SelectionListener, IDoubleClickListener {

		private final TableViewer leftTView;
		private final TableViewer rightTView;

		private MoveListener(TableViewer leftTView, TableViewer rightTView) {
			this.leftTView = leftTView;
			this.rightTView = rightTView;
		}

		public void widgetSelected(SelectionEvent e) {
			handleSelection();
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			handleSelection();
		}

		public void doubleClick(DoubleClickEvent event) {
			handleSelection();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void handleSelection() {
			StructuredSelection s = (StructuredSelection) leftTView.getSelection();
			if (!s.isEmpty()) {
				List left = (List) leftTView.getInput();
				List right = (List) rightTView.getInput();
				for (Object obj : s.toArray()) {
					left.remove(obj);
					right.add(obj);

				}
				leftTView.refresh();
				rightTView.refresh();
				fireChangeEvent();
			}
		}
	}

	private final class MoveAllListener implements SelectionListener {
		private final TableViewer leftTView;
		private final TableViewer rightTView;

		private MoveAllListener(TableViewer leftTView, TableViewer rightTView) {
			this.leftTView = leftTView;
			this.rightTView = rightTView;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void widgetSelected(SelectionEvent e) {
			List left = (List) leftTView.getInput();
			List right = (List) rightTView.getInput();
			for (Object obj : left)
				right.add(obj);

			left.clear();

			leftTView.refresh();
			rightTView.refresh();
			fireChangeEvent();
		}

		public void widgetDefaultSelected(SelectionEvent e) {

		}
	}

	public void createButtons(Composite composite, TableViewer leftTView, TableViewer rightTView) {
		Button addField = new Button(composite, SWT.PUSH);
		addField.setText(">"); //$NON-NLS-1$
		addField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addField.addSelectionListener(new MoveListener(leftTView, rightTView));

		Button addFields = new Button(composite, SWT.PUSH);
		addFields.setText(">>"); //$NON-NLS-1$
		addFields.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addFields.addSelectionListener(new MoveAllListener(leftTView, rightTView));

		Button delField = new Button(composite, SWT.PUSH);
		delField.setText("<"); //$NON-NLS-1$
		delField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		delField.addSelectionListener(new MoveListener(rightTView, leftTView));

		Button delFields = new Button(composite, SWT.PUSH);
		delFields.setText("<<"); //$NON-NLS-1$
		delFields.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		delFields.addSelectionListener(new MoveAllListener(rightTView, leftTView));

		// Add the doubleclick selection to the table viewers
		leftTView.addDoubleClickListener(new MoveListener(leftTView, rightTView));
		rightTView.addDoubleClickListener(new MoveListener(rightTView, leftTView));
	}

}
