/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.marker.MarkersDTO;

public class MarkerEditor extends Wizard {
	private MarkersDTO value;
	private MarkerPage page0;

	public MarkersDTO getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(MarkersDTO value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	public MarkerEditor() {
		super();
		setWindowTitle(Messages.MarkerEditor_Title);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		page0 = new MarkerPage("mapmarkers"); //$NON-NLS-1$
		page0.setValue(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
