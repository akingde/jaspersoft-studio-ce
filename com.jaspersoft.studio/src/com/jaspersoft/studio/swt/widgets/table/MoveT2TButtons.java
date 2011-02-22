/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.swt.widgets.table;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRSortField;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MoveT2TButtons {
	private final class MoveListener implements SelectionListener {
		private final TableViewer leftTView;
		private final TableViewer rightTView;

		private MoveListener(TableViewer leftTView, TableViewer rightTView) {
			this.leftTView = leftTView;
			this.rightTView = rightTView;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void widgetSelected(SelectionEvent e) {
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
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {

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
	}

}
