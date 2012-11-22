/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
