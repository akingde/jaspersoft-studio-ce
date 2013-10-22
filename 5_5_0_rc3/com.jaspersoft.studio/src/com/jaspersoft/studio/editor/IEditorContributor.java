/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.utils.AContributorAction;

public interface IEditorContributor {

	public void onLoad(JasperDesign jd, EditorPart editor);

	public void onSave(JasperReportsContext jrConfig, IProgressMonitor monitor);

	public void onRun();

	public AContributorAction[] getActions();

	public String getTitleToolTip(JasperReportsContext jrConfig, String toolTip);
}
