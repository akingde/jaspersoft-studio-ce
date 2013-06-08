package com.jaspersoft.studio.data.sql.action.expression;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.action.AAction;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MExpressionGroup;
import com.jaspersoft.studio.model.ANode;

public class ChangeOperator extends AAction {

	public ChangeOperator(IXtextDocument xtextDocument, SQLQueryDesigner designer) {
		super("Change to" + AMKeyword.OR_OPERATOR, xtextDocument, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		return selection != null && selection.length == 1 && isColumn((ANode) selection[0]);
	}

	protected boolean isColumn(ANode element) {
		boolean b = element instanceof MExpressionGroup && !element.isFirst();
		if (b)
			setMenuText((MExpressionGroup) element);
		return b;
	}

	protected void setMenuText(MExpressionGroup msel) {
		if (msel.getValue().equals(AMKeyword.AND_OPERATOR))
			setText("Change to " + AMKeyword.OR_OPERATOR);
		else
			setText("Change to " + AMKeyword.AND_OPERATOR);
	}

	@Override
	public void run() {
		for (Object obj : selection) {
			if (obj instanceof MExpressionGroup) {
				MExpressionGroup msel = (MExpressionGroup) obj;
				if (msel.getValue().equals(AMKeyword.AND_OPERATOR))
					msel.setValue(AMKeyword.OR_OPERATOR);
				else
					msel.setValue(AMKeyword.AND_OPERATOR);
				setMenuText(msel);
				selectInTree(obj);
				break;
			}
		}
	}
}