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
package com.jaspersoft.studio.editor.action.snap;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SnapToGeometryAction extends ACheckResourcePrefAction {
	public static final String ID = "showPublishDialogOnSave";

	public SnapToGeometryAction(JasperReportsConfiguration jrConfig) {
		super("Show Publish Dialog On Save", jrConfig);
		setText("Show Publish Dialog On Save");
		setToolTipText("Show Publish Dialog On Save");
		setId(ID);
	}

	@Override
	protected String getProperty() {
		return ID;
	}
}
