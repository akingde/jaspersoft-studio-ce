package com.jaspersoft.studio.data.sql.action.table;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.data.sql.Columns;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.OrTable;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Table;
import com.jaspersoft.studio.data.sql.TableAlias;
import com.jaspersoft.studio.data.sql.TableFull;
import com.jaspersoft.studio.data.sql.TableOrAlias;
import com.jaspersoft.studio.data.sql.Tables;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.impl.OrTableImpl;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.ui.outline.JSSEObjectNode;

public class CreateTable extends AAction {

	public CreateTable(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Add Table", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection == null || (selection != null && selection.length == 1);
	}

	public static boolean isInFrom(EObject element) {
		return element instanceof Table || element instanceof TableFull || element instanceof TableOrAlias || element instanceof TableAlias || element instanceof OrTableImpl;
	}

	@Override
	public void run() {
		TablesDialog dialog = new TablesDialog(Display.getDefault().getActiveShell());
		dialog.setRoot(designer.getDbMetadata().getRoot());
		if (dialog.open() == Window.OK) {
			EObject eobj = null;
			if (selection != null)
				eobj = ((JSSEObjectNode) selection[0]).getEObject();
			for (MSqlTable t : dialog.getTable())
				run(t, eobj);
		}
	}

	public void run(final MSqlTable node, EObject eobj) {
		xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
				System.out.println(state.getParseResult().getRootNode().getText());

				String t = " " + node.toSQLString() + " ";
				Model m = (Model) state.getContents().get(0);
				Tables tables = m.getTbl();
				if (tables != null) {
					if (tables instanceof OrTable && !((OrTable) tables).getEntries().isEmpty())
						t = "," + t;
					state.update(Util.getTotalEndOffsetOfKeyword(tables), 0, t);
				} else {
					t = "\nFROM" + t;
					int start = 0;
					int end = 0;
					Columns c = m.getCol();
					if (c != null) {
						start = Util.getTotalEndOffsetOfKeyword(c);
						end = start;
					}
					if (m.getWhereEntry() != null)
						end = Util.getStartOffsetOfKeyword(m.getWhereEntry()) - "WHERE".length();
					else if (m.getGroupByEntry() != null)
						end = Util.getStartOffsetOfKeyword(m.getGroupByEntry()) - "GROUP BY".length();
					else if (m.getHavingEntry() != null)
						end = Util.getStartOffsetOfKeyword(m.getHavingEntry()) - "HAVING".length();
					else if (m.getOrderByEntry() != null)
						end = Util.getStartOffsetOfKeyword(m.getOrderByEntry()) - "ORDER BY".length();

					state.update(start, end - start, "");
					state.update(start, start, t);
					System.out.println(state.getParseResult().getRootNode().getText());

					// TODO add to from
				}

				xtextDocument.set(state.getParseResult().getRootNode().getText());

			}
		});
	}
}