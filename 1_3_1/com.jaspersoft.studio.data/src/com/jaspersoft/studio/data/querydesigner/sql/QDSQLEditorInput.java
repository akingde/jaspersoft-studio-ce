/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
