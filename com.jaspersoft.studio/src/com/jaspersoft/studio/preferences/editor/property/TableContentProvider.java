package com.jaspersoft.studio.preferences.editor.property;

import org.eclipse.jface.viewers.ArrayContentProvider;

import com.jaspersoft.studio.preferences.editor.sorttable.model.ITableContentProvider;

public class TableContentProvider extends ArrayContentProvider implements ITableContentProvider {

	public Object getColumnValue(Object element, int columnIndex) {

		switch (columnIndex) {
		case 0: {
			return "BCDa";
		}
		case 1: {
			return "ABCD";
		}

		default: {
			return (element != null ? element.toString() : "");
		}
		}
	}
}
