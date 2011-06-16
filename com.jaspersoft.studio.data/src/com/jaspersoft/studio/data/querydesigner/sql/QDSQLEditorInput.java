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
