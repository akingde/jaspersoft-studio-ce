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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TableViewerSorter extends ViewerSorter {

	private int sortingColumn;

	private boolean ascending = true;

	private Viewer viewer;

	private ITableContentProvider contentProvider;

	public TableViewerSorter(Viewer viewer, ITableContentProvider contentProvider) {
		this.viewer = viewer;
		this.contentProvider = contentProvider;
	}

	public int getSortingColumn() {
		return (this.sortingColumn);
	}

	public void setSortingColumn(int columnIndex) {
		this.sortingColumn = columnIndex;
	}

	public boolean isAscending() {
		return (this.ascending);
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public void sort() {
		this.viewer.refresh();
	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public int compare(Viewer viewer, Object e1, Object e2) {
		int category1 = this.category(e1);
		int category2 = this.category(e2);
		if (category1 != category2) {
			return (category1 - category2);
		}

		Object value1 = this.contentProvider.getColumnValue(e1, this.getSortingColumn());

		Object value2 = this.contentProvider.getColumnValue(e2, this.getSortingColumn());

		if (value1 instanceof String && value2 instanceof String) {

			if (value1 == null) {
				value1 = "";
			}
			if (value2 == null) {
				value2 = "";
			}

			return (this.isAscending() ? this.getComparator().compare(value1, value2) : (-this.getComparator().compare(
					value1, value2)));
		} else {
			if (value1 == null && value2 == null) {

				return (0);
			} else if (value1 != null && value2 == null) {

				return (-1);
			} else if (value1 == null && value2 != null) {

				return (1);
			} else if (value1 instanceof Comparable && value2 instanceof Comparable) {
				return (this.isAscending() ? ((Comparable) value1).compareTo(value2) : -((Comparable) value1).compareTo(value2));
			} else {

				return (this.isAscending() ? this.getComparator().compare(value1, value2) : (-this.getComparator().compare(
						value1, value2)));
			}
		}
	}
}
