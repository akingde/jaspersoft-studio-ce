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
package com.jaspersoft.studio.editor.preview.view;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class APreview {
	private Control control;
	protected JasperReportsConfiguration jContext;

	public APreview(Composite parent, JasperReportsConfiguration jContext) {
		this.jContext = jContext;
		control = createControl(parent);
	}

	protected abstract Control createControl(Composite parent);

	public Control getControl() {
		return control;
	}

	public void contribute2ToolBar(IToolBarManager tmanager) {

	}

	public void dispose() {

	}

	public void setEnabled(boolean enabled) {

	}

}
