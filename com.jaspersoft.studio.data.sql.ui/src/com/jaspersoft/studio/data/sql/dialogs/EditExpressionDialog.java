package com.jaspersoft.studio.data.sql.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.sql.model.query.MExpression;

public class EditExpressionDialog extends Dialog {
	private MExpression value;

	public EditExpressionDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setValue(MExpression value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Expression Dialog");
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(3, false));

		// Label lbl = new Label(cmp, SWT.NONE);
		// lbl.setText(value.getValue().toSQLString());
		// UIUtil.setBold(lbl);
		//
		// Combo keyword = new Combo(cmp, SWT.READ_ONLY);
		// keyword.setItems(AMKeyword.ALIAS_KEYWORDS);
		//
		// Text alias = new Text(cmp, SWT.BORDER);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.minimumWidth = 200;
		// alias.setLayoutData(gd);
		//
		// DataBindingContext bindingContext = new DataBindingContext();
		//		bindingContext.bindValue(SWTObservables.observeText(alias, SWT.Modify), PojoObservables.observeValue(this, "alias")); //$NON-NLS-1$
		//		bindingContext.bindValue(SWTObservables.observeSelection(keyword), PojoObservables.observeValue(this, "aliasKeyword")); //$NON-NLS-1$
		return cmp;
	}
}
