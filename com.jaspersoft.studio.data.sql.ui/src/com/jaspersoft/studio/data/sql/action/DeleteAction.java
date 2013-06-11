package com.jaspersoft.studio.data.sql.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.model.ANode;

public class DeleteAction<T extends ANode> extends AAction {
	protected String name;
	protected Class<T> type;

	public DeleteAction(SQLQueryDesigner designer, String name, Class<T> type) {
		super("&Delete " + name, designer);
		this.name = name;
		this.type = type;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		if (selection == null)
			return false;
		else {
			for (Object s : selection) {
				if (!isColumn((ANode) s))
					return false;
			}
		}
		return true;
	}

	protected boolean isColumn(ANode element) {
		return type.isAssignableFrom(element.getClass());
	}

	@Override
	public void run() {
		final List<T> lst = new ArrayList<T>();
		for (Object obj : selection) {
			if (type.isAssignableFrom(obj.getClass()))
				lst.add((T) obj);
		}
		if (UIUtils.showConfirmation("Delete " + name, "Are you sure you want to delete the " + name.toLowerCase() + "(s)?"))
			doDelete(lst);
	}

	protected void doDelete(final List<T> lst) {
		ANode mfrom = null;
		int indx = 0;
		for (T ftbl : lst) {
			if (mfrom == null)
				mfrom = (ANode) ftbl.getParent();
			indx = mfrom.getChildren().indexOf(ftbl);
			mfrom.removeChild(ftbl);
			doDeleteMore(mfrom, ftbl);
		}
		ANode toSelect = mfrom;
		if (indx - 1 > 0)
			toSelect = (ANode) mfrom.getChildren().get(Math.min(mfrom.getChildren().size() - 1, indx));
		selectInTree(toSelect);
	}

	protected void doDeleteMore(ANode parent, T todel) {
	}
}