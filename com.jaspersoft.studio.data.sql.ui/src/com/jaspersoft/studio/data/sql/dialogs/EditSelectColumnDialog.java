package com.jaspersoft.studio.data.sql.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MSelectColumn;
import com.jaspersoft.studio.utils.UIUtil;

public class EditSelectColumnDialog extends ATitledDialog {
	private MSelectColumn value;
	private String alias;
	private String aliasKeyword;

	public EditSelectColumnDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Column Dialog");
	}

	public void setValue(MSelectColumn value) {
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

		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeText(alias, SWT.Modify), PojoObservables.observeValue(this, "alias")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(keyword), PojoObservables.observeValue(this, "aliasKeyword")); //$NON-NLS-1$
		return cmp;
	}
}
