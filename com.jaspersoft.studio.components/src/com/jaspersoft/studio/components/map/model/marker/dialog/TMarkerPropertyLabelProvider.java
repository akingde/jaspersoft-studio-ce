package com.jaspersoft.studio.components.map.model.marker.dialog;

import net.sf.jasperreports.components.map.MarkerProperty;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.utils.Misc;

public class TMarkerPropertyLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		MarkerProperty dto = (MarkerProperty) element;
		switch (columnIndex) {
		case 0:
			return Misc.nvl(dto.getName());
		case 1:
			return Misc.nvl(dto.getValue());
		case 2:
			return getValue(dto);
		}
		return ""; //$NON-NLS-1$
	}

	private String getValue(MarkerProperty mp) {
		if (mp.getValueExpression() != null)
			return Misc.nvl(mp.getValueExpression().getText());
		return "";
	}
}
