package com.jaspersoft.studio.data.sql.action;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class LayoutAction extends Action {
	private SQLQueryDesigner designer;

	public LayoutAction(SQLQueryDesigner designer) {
		super("&Layout Tables");
		this.designer = designer;
	}

	@Override
	public void run() {
		new ModelVisitor<Object>(designer.getRoot()) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MFromTable) {
					((MFromTable) n).setPropertyValue(MFromTable.PROP_X, null);
					((MFromTable) n).setPropertyValue(MFromTable.PROP_Y, null);
				}
				return true;
			}

		};
		designer.getDiagram().scheduleRefresh();
	}

}
