package com.jaspersoft.studio.data.sql.action.table;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Point;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.Table;
import com.jaspersoft.studio.data.sql.TableAlias;
import com.jaspersoft.studio.data.sql.TableFull;
import com.jaspersoft.studio.data.sql.TableOrAlias;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.ui.outline.JSSEObjectNode;

public class DeleteTable extends AAction {
	private IXtextDocument xtextDocument;
	private Object[] selection;

	public DeleteTable(IXtextDocument xtextDocument) {
		super("&Delete Table");
		this.xtextDocument = xtextDocument;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		this.selection = selection;
		for (Object obj : selection) {
			if (obj instanceof JSSEObjectNode) {
				EObject element = ((JSSEObjectNode) obj).getEObject();
				if (isTable(element))
					return true;
			}
		}
		return false;
	}

	protected boolean isTable(EObject element) {
		return element instanceof Table || element instanceof TableFull || element instanceof TableOrAlias || element instanceof TableAlias;
	}

	@Override
	public void run() {
		final List<EObject> lst = new ArrayList<EObject>();
		for (Object obj : selection) {
			if (obj instanceof JSSEObjectNode) {
				EObject element = ((JSSEObjectNode) obj).getEObject();
				if (isTable(element))
					lst.add(element);
			}
		}
		if (UIUtils.showConfirmation("Delete Tables", "Are you sure you want to delete the tables?"))
			xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
				@Override
				public void process(XtextResource state) throws Exception {
					Model m = (Model) state.getContents().get(0);
					for (EObject eo : lst) {
						int s = 0;
						int e = 0;
						if (eo instanceof TableFull) {
							TableFull tfi = (TableFull) eo;
							Point p = Util.getPosition(tfi.getTblAlias());
							s = p.x;
							e = p.y;
							p = Util.getPosition(tfi.getTbl());
							s = Math.min(s, p.x);
							e = Math.max(e, p.y);
						} else if (eo instanceof Table) {
							Table t = (Table) eo;
							Point p = Util.getPosition(t);
							s = p.x;
							e = p.y;
						} else if (eo instanceof TableOrAlias) {
							TableOrAlias toa = (TableOrAlias) eo;
							Point p = Util.getPosition(toa);
							s = p.x;
							e = p.y;
						} else if (eo instanceof TableAlias) {
							TableAlias toa = (TableAlias) eo;
							Point p = Util.getPosition(toa);
							s = p.x;
							e = p.y;

						}
						for (EObject obj : eo.eContents()) {
							System.out.println(obj);
						}
						state.update(s, e - s, "");
					}

					xtextDocument.set(state.getParseResult().getRootNode().getText());

				}
			});

	}
}