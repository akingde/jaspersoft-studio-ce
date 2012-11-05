/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.map.model.marker;

import org.eclipse.jface.viewers.LabelProvider;

/*
 * @author Chicu Veaceslav
 */
public class MarkerLabelProvider extends LabelProvider {

	public MarkerLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof MarkersDTO) {
			MarkersDTO dto = (MarkersDTO) element;
			int expsize = 0;
			if (dto.getMarkers() != null)
				expsize += dto.getMarkers().size();
			return "[Markers: " + expsize + "]";
		} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return element.toString();
	}

}
