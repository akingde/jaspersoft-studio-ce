/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Point;

public abstract class AWizardNode implements IWizardNode {
	protected IWizard wizard = null;

	public void dispose() {
	}

	public Point getExtent() {
		return new Point(-1, -1);
	}

	public final IWizard getWizard() {
		if (wizard == null)
			wizard = createWizard();
		return wizard;
	}

	protected abstract IWizard createWizard();

	public boolean isContentCreated() {
		return wizard != null;
	}

}
