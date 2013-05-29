package com.jaspersoft.studio.data.sql.action;

import org.eclipse.jface.action.Action;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;

public abstract class AAction extends Action {
	protected IXtextDocument xtextDocument;
	protected Object[] selection;
	protected SQLQueryDesigner designer;

	public AAction(String text, IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super(text);
		this.xtextDocument = xtextDocument;
		this.designer = designer;
	}

	public boolean calculateEnabled(Object[] selection) {
		this.selection = selection;
		return true;
	}
}
