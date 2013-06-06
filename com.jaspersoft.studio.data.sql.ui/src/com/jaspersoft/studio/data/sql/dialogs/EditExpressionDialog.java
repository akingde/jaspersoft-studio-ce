package com.jaspersoft.studio.data.sql.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.widgets.Factory;

public class EditExpressionDialog extends Dialog {
	private MExpression value;

	public EditExpressionDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setValue(MExpression value) {
		this.value = value;
		setOperator(value.getOperator().getSqlname());
		setPrevcond(value.getPrevCond());
		operands = value.getOperands();

	}

	private java.util.List<AOperand> operands = new ArrayList<AOperand>();
	private String prevcond;
	private String operator;

	public String getPrevcond() {
		return prevcond;
	}

	public void setPrevcond(String prevcond) {
		this.prevcond = prevcond;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public java.util.List<AOperand> getOperands() {
		return operands;
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
		newShell.setText("Expression Dialog");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(5, false));

		DataBindingContext bindingContext = new DataBindingContext();

		if (!value.isFirst()) {
			Composite c = new Composite(cmp, SWT.NONE);
			GridLayout layout = new GridLayout(3, false);
			layout.marginWidth = 0;
			c.setLayout(layout);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 5;
			c.setLayoutData(gd);

			new Label(c, SWT.NONE).setText("Previous condition ");

			Combo prevoperator = new Combo(c, SWT.READ_ONLY);
			prevoperator.setItems(AMKeyword.CONDITIONS);

			new Label(c, SWT.NONE).setText(" this one.");

			bindingContext.bindValue(SWTObservables.observeSelection(prevoperator), PojoObservables.observeValue(this, "prevcond")); //$NON-NLS-1$
		} else {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 5;
			new Label(cmp, SWT.NONE).setLayoutData(gd);
		}

		Control w = Factory.createWidget(cmp, operands, 0, value);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 250;
		w.setLayoutData(gd);

		Combo operator = new Combo(cmp, SWT.READ_ONLY);
		operator.setItems(Operator.operators);
		operator.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		operator.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showRight();
			}
		});

		rcmp = new Composite(cmp, SWT.NONE);
		stackLayout = new StackLayout();
		stackLayout.marginHeight = 0;
		stackLayout.marginWidth = 0;
		rcmp.setLayout(stackLayout);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 150;
		gd.widthHint = 300;
		rcmp.setLayoutData(gd);

		showRight();
		bindingContext.bindValue(SWTObservables.observeSelection(operator), PojoObservables.observeValue(this, "operator")); //$NON-NLS-1$
		return cmp;
	}

	private Map<String, Composite> map = new HashMap<String, Composite>();
	private Composite rcmp;
	private StackLayout stackLayout;

	private void showRight() {
		Composite cmp = map.get(getOperator());
		if (cmp == null) {
			Operator op = Operator.getOperator(getOperator());
			if (op.getNrOperands() == 1) {
				cmp = new Composite(rcmp, SWT.NONE);
				cmp.setLayout(new GridLayout());
			} else if (op.getNrOperands() == 2) {
				cmp = new Composite(rcmp, SWT.NONE);
				GridLayout layout = new GridLayout(2, false);
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				cmp.setLayout(layout);

				Control w = Factory.createWidget(cmp, operands, 1, value);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.widthHint = 200;
				w.setLayoutData(gd);
			} else if (op.getNrOperands() == 3 && op == Operator.BETWEEN) {
				cmp = new Composite(rcmp, SWT.NONE);
				GridLayout layout = new GridLayout(3, false);
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				cmp.setLayout(layout);

				Control w = Factory.createWidget(cmp, operands, 1, value);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.widthHint = 200;
				w.setLayoutData(gd);

				new Label(cmp, SWT.NONE).setText("AND");

				w = Factory.createWidget(cmp, operands, 2, value);
				gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.widthHint = 200;
				w.setLayoutData(gd);
			} else {
				cmp = new Composite(rcmp, SWT.NONE);
				GridLayout layout = new GridLayout(2, false);
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				cmp.setLayout(layout);

				createInList(cmp);
			}
		}
		stackLayout.topControl = cmp;
		rcmp.layout(true);
	}

	protected void createInList(Composite cmp) {
		List inlist = new List(cmp, SWT.MULTI | SWT.READ_ONLY | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		gd.widthHint = 200;
		inlist.setLayoutData(gd);

		Button op3 = new Button(cmp, SWT.PUSH);
		op3.setText("&Add");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		op3.setLayoutData(gd);

		op3 = new Button(cmp, SWT.PUSH);
		op3.setText("&Edit");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		op3.setLayoutData(gd);

		op3 = new Button(cmp, SWT.PUSH);
		op3.setText("&Delete");
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		op3.setLayoutData(gd);

		showInList(inlist);
	}

	private void showInList(List inlist) {
		String[] ilarray = new String[Math.max(operands.size() - 1, 0)];
		if (operands.size() > 1)
			for (int i = 1; i < operands.size(); i++) {
				ilarray[i - 1] = operands.get(i).toSQLString();
			}
		inlist.setItems(ilarray);
	}

}
