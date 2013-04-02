/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker.dialog;

import java.util.List;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemProperty;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.map.model.marker.MarkerCoordinatesType;

public class TMarkerLabelProvider extends LabelProvider implements ITableLabelProvider {

	private MarkerCoordinatesType coordinatesType = MarkerCoordinatesType.LATITUDE_LONGITUDE;

	public TMarkerLabelProvider() {
		super();
	}

	public TMarkerLabelProvider(MarkerCoordinatesType coordinatesType) {
		super();
		this.coordinatesType = coordinatesType;
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		Item dto = (Item) element;
		List<ItemProperty> prp = dto.getProperties();
		switch (columnIndex) {
		case 2:
			for (ItemProperty mp : prp) {
				if (coordinatesType == MarkerCoordinatesType.LATITUDE_LONGITUDE && mp.getName().equals("longitude")) {
					return getValue(mp);
				}
				if (coordinatesType == MarkerCoordinatesType.XY && mp.getName().equals("y")) {
					return getValue(mp);
				}
			}
			return "null";
		case 1:
			for (ItemProperty mp : prp) {
				if (coordinatesType == MarkerCoordinatesType.LATITUDE_LONGITUDE && mp.getName().equals("latitude")) {
					return getValue(mp);
				}
				if (coordinatesType == MarkerCoordinatesType.XY && mp.getName().equals("x")) {
					return getValue(mp);
				}
			}
			return "null";
		case 0:
			for (ItemProperty mp : prp) {
				if (mp.getName().equals("id")) {
					return getValue(mp);
				}
			}
			return "null";
		}
		return ""; //$NON-NLS-1$
	}

	public static String getValue(ItemProperty mp) {
		if (mp.getValueExpression() != null)
			return mp.getValueExpression().getText();
		if (mp.getValue() != null)
			return mp.getValue();

		return "null";
	}
}
