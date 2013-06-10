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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;

public class EditSelectExpressionDialog extends ATitledDialog {
	private MSelectExpression value;
	private String alias;
	private String aliasKeyword;
	private String expression;

	public EditSelectExpressionDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Column Expression Dialog");
	}

	public void setValue(MSelectExpression value) {
		this.value = value;
		setAlias(value.getAlias());
		setAliasKeyword(value.getAliasKeyword());
		setExpression(value.getValue());
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
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

		Text expr = new Text(cmp, SWT.BORDER);
		expr.setText(value.getValue());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.minimumWidth = 300;
		expr.setLayoutData(gd);

		Combo keyword = new Combo(cmp, SWT.READ_ONLY);
		keyword.setItems(AMKeyword.ALIAS_KEYWORDS);

		Text alias = new Text(cmp, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.minimumWidth = 100;
		alias.setLayoutData(gd);

		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeText(expr, SWT.Modify), PojoObservables.observeValue(this, "expression")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(alias, SWT.Modify), PojoObservables.observeValue(this, "alias")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(keyword), PojoObservables.observeValue(this, "aliasKeyword")); //$NON-NLS-1$
		return cmp;
	}
}
