package com.jaspersoft.studio.data.sql.action;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.model.ANode;

public abstract class AMultiSelectionAction extends AAction {

	public AMultiSelectionAction(String text, SQLQueryDesigner designer) {
		super(text, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		if (selection == null)
			return false;
		else {
			for (Object s : selection) {
				s = convertObject(s);
				if (s == null)
					return false;
				if (!isGoodNode((ANode) s))
					return false;
			}
		}
		return true;
	}

	protected ANode convertObject(Object obj) {
		if (obj instanceof ANode)
			return (ANode) obj;
		return null;
	}

	protected abstract boolean isGoodNode(ANode element);
}
