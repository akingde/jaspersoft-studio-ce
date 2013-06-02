package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
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

	public boolean calculateEnabled(ISelection iselection) {
		List<Object> lst = new ArrayList<Object>();
		if (iselection instanceof TreeSelection) {
			for (TreePath tp : ((TreeSelection) iselection).getPaths())
				lst.add(tp.getLastSegment());
		}
		return calculateEnabled(lst.toArray());
	}

	public boolean calculateEnabled(Object[] selection) {
		this.selection = selection;
		return true;
	}

	protected void selectInTree(Object sel) {
		TreeViewer tv = designer.getOutline().getTreeViewer();
		tv.refresh(true);
		tv.setSelection(new TreeSelection(new TreePath(new Object[] { sel })));
		tv.reveal(sel);
	}
}
