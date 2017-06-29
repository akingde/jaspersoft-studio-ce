/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;

import com.jaspersoft.studio.components.map.messages.Messages;

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
			return NLS.bind(Messages.MarkerLabelProvider_MarkersNum, expsize);
		} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return element.toString();
	}

}
