package com.jaspersoft.studio.preferences.editor.property;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableLabelProvider extends LabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "fda";
		case 1:
			return "fda";

		default:
			return (element != null ? element.toString() : "");
		}
	}

	public void dispose() {
	}
}