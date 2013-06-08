package com.jaspersoft.studio.data.sql.dialogs;

import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.sql.model.query.MExpression;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.widgets.Factory;

public class OperandDialog extends ATitledDialog {
	private MExpression mexpression;
	private int index;
	private List<AOperand> operands;

	protected OperandDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Operand Dialog");
		setDescription("You can change the operand type using context menu. Right click on the operand.");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout());

		Control w = Factory.createWidget(cmp, operands, index, mexpression, true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		w.setLayoutData(gd);

		return cmp;
	}

	public AOperand getOperand() {
		return operands.get(index);
	}

	public void setValues(MExpression mexpression, List<AOperand> operands, int index) {
		this.mexpression = mexpression;
		this.operands = operands;
		this.index = index;
	}
}
