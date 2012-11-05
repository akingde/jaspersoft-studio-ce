package com.jaspersoft.studio.components.map.model.marker.dialog;

import java.util.List;

import net.sf.jasperreports.components.map.Marker;
import net.sf.jasperreports.components.map.MarkerProperty;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TPropertyLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		Marker dto = (Marker) element;
		List<MarkerProperty> prp = dto.getProperties();
		switch (columnIndex) {
		case 0:
			for (MarkerProperty mp : prp)
				if (mp.getName().equals(Marker.PROPERTY_title))
					return getValue(mp);
			return "No Title";
		case 1:
			for (MarkerProperty mp : prp)
				if (mp.getName().equals(Marker.PROPERTY_longitude))
					return getValue(mp);
			return "null";
		case 2:
			for (MarkerProperty mp : prp)
				if (mp.getName().equals(Marker.PROPERTY_latitude))
					return getValue(mp);
			return "null";
		}
		return ""; //$NON-NLS-1$
	}

	private String getValue(MarkerProperty mp) {
		if (mp.getValue() != null)
			return mp.getValue();
		if (mp.getValueExpression() != null)
			return mp.getValueExpression().getText();
		return "null";
	}
}
