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
package com.jaspersoft.studio.data.querydesigner.sql;

import com.jaspersoft.studio.eclipse.editorinput.StringEditorInput;

public class QDSQLEditorInput extends StringEditorInput
// implements
// ISQLEditorInput
{
	public QDSQLEditorInput(String inputString) {
		super(inputString);
		// TODO Auto-generated constructor stub
	}

	// org.eclipse.ui.views.PropertySheetng inputString) {
	// super(inputString);
	// }

	// private ISQLEditorConnectionInfo connInfo;
	//
	// public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
	// this.connInfo = connInfo;
	// }
	//
	// public ISQLEditorConnectionInfo getConnectionInfo() {
	// return connInfo;
	// }

	public boolean isConnectionRequired() {
		return false;
	}

	public String getId() {
		return getClass().getName() + "(fname)";
	}

}
