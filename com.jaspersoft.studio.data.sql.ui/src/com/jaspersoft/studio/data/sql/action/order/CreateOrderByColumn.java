package com.jaspersoft.studio.data.sql.action.order;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.data.sql.Column;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.OrOrderByColumn;
import com.jaspersoft.studio.data.sql.OrderByColumnFull;
import com.jaspersoft.studio.data.sql.OrderByColumns;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.action.column.ColumnsDialog;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.ui.outline.JSSEObjectNode;

public class CreateOrderByColumn extends AAction {

	public CreateOrderByColumn(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("&Add Order By Column", xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && selection[0] instanceof JSSEObjectNode && isInOrderBy(((JSSEObjectNode) selection[0]).getEObject());
	}

	public static boolean isInOrderBy(EObject element) {
		if (element instanceof OrOrderByColumn)
			return true;
		if (element instanceof Column)
			return isInOrderBy(element.eContainer());
		if (element instanceof OrderByColumnFull)
			return true;
		return false;
	}

	@Override
	public void run() {
		ColumnsDialog dialog = new ColumnsDialog(Display.getDefault().getActiveShell());
		dialog.setRoot(designer.getDbMetadata().getRoot());
		if (dialog.open() == Window.OK) {
			EObject eobj = null;
			if (selection != null)
				eobj = ((JSSEObjectNode) selection[0]).getEObject();
			for (MColumn t : dialog.getColumns())
				run(t, eobj);
		}
	}

	public void run(final MColumn node, EObject eobj) {
		xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
				Model m = (Model) state.getContents().get(0);
				OrderByColumns cols = (OrderByColumns) m.getOrderByEntry();
				if (cols != null)
					state.update(Util.getTotalEndOffsetOfKeyword(cols), 0, ", " + node.toSQLString() + " ");
				else {

					// TODO add to from
				}

				xtextDocument.set(state.getParseResult().getRootNode().getText());

			}
		});
	}

}