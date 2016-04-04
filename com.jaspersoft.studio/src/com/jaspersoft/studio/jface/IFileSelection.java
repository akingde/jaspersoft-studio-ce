/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.jface;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.jface.dialogs.ImageSelectionDialog;

import net.sf.jasperreports.engine.design.JasperDesign;

public interface IFileSelection {
	public void createRadioButton(Composite parent, ImageSelectionDialog d, JasperDesign jd);

	public void changeSelectionMode(Control newTopControl);

	public void createFileSelectionContainer(Composite parent);
}
