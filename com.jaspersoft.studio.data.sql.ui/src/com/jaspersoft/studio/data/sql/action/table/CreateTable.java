package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.impl.OrTableImpl;

public class CreateTable extends AAction {
	private IXtextDocument xtextDocument;

	public CreateTable(IXtextDocument xtextDocument) {
		super("&Add Table");
		this.xtextDocument = xtextDocument;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		return true;
	}

	@Override
	public void run() {
		xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
				Model m = (Model) state.getContents().get(0);
				OrTableImpl tables = (OrTableImpl) m.getTbl();
				if (tables != null)
					state.update(Util.getFirstOffsetOfKeyword(tables), 0, ", db.tbl.mynewtable ");
				else {

					// TODO add to from
				}

				xtextDocument.set(state.getParseResult().getRootNode().getText());

			}
		});

	}

}