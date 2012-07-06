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

package com.jaspersoft.studio.preferences.editor.sorttable.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class TableViewerSorterHandler extends SelectionAdapter {

	private Table table;

	private TableViewerSorter sorter;

	public TableViewerSorterHandler(Table table, TableViewerSorter sorter) {
		this.table = table;
		this.sorter = sorter;
		this.registerColumns();
	}

	public void dispose() {
		this.unregisterColumns();
	}

	public void widgetSelected(SelectionEvent event) {
		int columnIndex = this.table.indexOf((TableColumn) event.widget);
		this.sort(columnIndex);
	}

	public void sort(int columnIndex) {
		this.sort(columnIndex, !this.sorter.isAscending());
	}

	public void sort(int columnIndex, boolean ascending) {
		this.sorter.setSortingColumn(columnIndex);
		this.sorter.setAscending(ascending);
		this.sorter.sort();

		TableColumn column = this.table.getColumn(columnIndex);
		this.table.setSortColumn(column);
		this.table.setSortDirection(sorter.isAscending() ? SWT.UP : SWT.DOWN);
	}

	private void registerColumns() {
		TableColumn[] columns = this.table.getColumns();
		for (int i = 0; i < columns.length; i++) {
			columns[i].addSelectionListener(this);
		}
	}

	private void unregisterColumns() {
		TableColumn[] columns = this.table.getColumns();
		for (int i = 0; i < columns.length; i++) {
			columns[i].removeSelectionListener(this);
		}
	}
}
