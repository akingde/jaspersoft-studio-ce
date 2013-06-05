package com.jaspersoft.studio.data.sql.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MFromTable;

public class EditFromTableDialog extends Dialog {
	private MFromTable value;
	private String alias;
	private String aliasKeyword;

	public EditFromTableDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setValue(MFromTable value) {
		this.value = value;
		setAlias(value.getAlias());
		setAliasKeyword(value.getAliasKeyword());
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

		Text lbl = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		lbl.setText(value.getValue().toSQLString());
		lbl.setToolTipText(lbl.getText());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		lbl.setLayoutData(gd);

		Combo keyword = new Combo(cmp, SWT.READ_ONLY);
		keyword.setItems(AMKeyword.ALIAS_KEYWORDS);

		Text alias = new Text(cmp, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		alias.setLayoutData(gd);

		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeText(alias, SWT.Modify), PojoObservables.observeValue(this, "alias")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(keyword), PojoObservables.observeValue(this, "aliasKeyword")); //$NON-NLS-1$ 
		return cmp;
	}
}
