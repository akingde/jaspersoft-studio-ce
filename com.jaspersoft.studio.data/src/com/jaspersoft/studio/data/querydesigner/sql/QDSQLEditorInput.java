/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.querydesigner.sql;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;

import com.jaspersoft.studio.eclipse.editorinput.StringEditorInput;

public class QDSQLEditorInput extends StringEditorInput implements
		ISQLEditorInput {

	public QDSQLEditorInput(String inputString) {
		super(inputString);
	}

	private ISQLEditorConnectionInfo connInfo;

	public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
		this.connInfo = connInfo;
	}

	public ISQLEditorConnectionInfo getConnectionInfo() {
		return connInfo;
	}

	public boolean isConnectionRequired() {
		return false;
	}

	public String getId() {
		return getClass().getName() + "(fname)";
	}

}
