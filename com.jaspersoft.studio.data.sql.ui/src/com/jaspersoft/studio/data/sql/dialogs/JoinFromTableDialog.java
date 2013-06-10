package com.jaspersoft.studio.data.sql.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class JoinFromTableDialog extends ATitledDialog {
	private MFromTable srcTable;
	private String fromTable;
	private String join = AMKeyword.INNER_JOIN;

	public JoinFromTableDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Join Table Dialog");
	}

	public void setValue(MFromTable value) {
		this.srcTable = value;
		if (value instanceof MFromTableJoin) {
			fromTable = ((MFromTable) value.getParent()).getDisplayText();
		} else {
			List<INode> children = value.getParent().getChildren();
			fromTable = ((MFromTable) children.get(children.indexOf(value) - 1)).getDisplayText();
		}
	}

	private String[] getFromTables() {
		ANode parent = srcTable.getParent();
		if (srcTable instanceof MFromTableJoin)
			parent = parent.getParent();
		List<String> lst = new ArrayList<String>();
		for (INode s : parent.getChildren())
			if (!s.getDisplayText().equals(srcTable.getDisplayText()))
				lst.add(s.getDisplayText());

		return lst.toArray(new String[lst.size()]);
	}

	private int getFromTablesIndex() {
		String[] fromTables = getFromTables();
		for (int i = 0; i < fromTables.length; i++)
			if (fromTables.equals(fromTable))
				return i;
		return 0;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(3, false));

		Combo ftable = new Combo(cmp, SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		ftable.setLayoutData(gd);
		ftable.setItems(getFromTables());
		ftable.select(getFromTablesIndex());

		Combo keyword = new Combo(cmp, SWT.READ_ONLY);
		keyword.setItems(AMKeyword.JOIN_KEYWORDS);

		Text lbl = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		lbl.setText(srcTable.getValue().toSQLString());
		lbl.setToolTipText(lbl.getText());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		lbl.setLayoutData(gd);

		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeSelection(keyword), PojoObservables.observeValue(this, "join")); //$NON-NLS-1$ 
		bindingContext.bindValue(SWTObservables.observeSelection(ftable), PojoObservables.observeValue(this, "fromTable")); //$NON-NLS-1$ 
		return cmp;
	}
}
