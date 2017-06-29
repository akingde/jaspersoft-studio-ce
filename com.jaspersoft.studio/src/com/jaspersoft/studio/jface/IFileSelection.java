/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.jface.dialogs.FileSelectionDialog;

import net.sf.jasperreports.engine.design.JasperDesign;

public interface IFileSelection {
	public void createRadioButton(Composite parent, FileSelectionDialog d, JasperDesign jd);

	public void changeSelectionMode(Control newTopControl);

	public void createFileSelectionContainer(Composite parent);
}
