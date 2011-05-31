package com.jaspersoft.studio.data.querydesigner.sql;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;

import com.jaspersoft.studio.eclipse.editorinput.StringEditorInput;

public class QDSQLEditorInput extends StringEditorInput implements
		ISQLEditorInput {

	public QDSQLEditorInput(String inputString) {
		super(inputString);
		// TODO Auto-generated constructor stub
	}

	private ISQLEditorConnectionInfo connInfo;

	@Override
	public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
		this.connInfo = connInfo;
	}

	@Override
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return connInfo;
	}

	@Override
	public boolean isConnectionRequired() {
		return false;
	}

	@Override
	public String getId() {
		return getClass().getName() + "(fname)";
	}

}
