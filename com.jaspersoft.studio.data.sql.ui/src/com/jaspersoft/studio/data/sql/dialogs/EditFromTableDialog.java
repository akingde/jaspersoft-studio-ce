package com.jaspersoft.studio.data.sql.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;
import com.jaspersoft.studio.utils.UIUtil;

public class EditFromTableDialog extends Dialog {
	private MFromTable value;
	private String alias;
	private String aliasKeyword;
	private String join;

	public EditFromTableDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setValue(MFromTable value) {
		this.value = value;
		setAlias(value.getAlias());
		setAliasKeyword(value.getAliasKeyword());
		setJoin(value.getJoin());
	}

	public void setAliasKeyword(String aliasKeyword) {
		this.aliasKeyword = aliasKeyword;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAliasKeyword() {
		return aliasKeyword;
	}

	public String getAlias() {
		return alias;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Table Dialog");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(3, false));

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(value.getValue().toSQLString());
		UIUtil.setBold(lbl);

		Combo keyword = new Combo(cmp, SWT.READ_ONLY);
		keyword.setItems(AMKeyword.ALIAS_KEYWORDS);

		Text alias = new Text(cmp, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.minimumWidth = 200;
		alias.setLayoutData(gd);

		Composite jcmp = new Composite(cmp, SWT.NONE);
		jcmp.setLayout(new GridLayout(4, false));
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		jcmp.setLayoutData(gd);

		Combo join = new Combo(jcmp, SWT.READ_ONLY);
		join.setItems(AMKeyword.JOIN_KEYWORDS);

		Button stbl = new Button(jcmp, SWT.PUSH);
		stbl.setText("Select table");

		new Label(jcmp, SWT.NONE).setText("ON");

		new Text(jcmp, SWT.BORDER);

		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeText(alias, SWT.Modify), PojoObservables.observeValue(this, "alias")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(keyword), PojoObservables.observeValue(this, "aliasKeyword")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(join), PojoObservables.observeValue(this, "join")); //$NON-NLS-1$
		return cmp;
	}
}
