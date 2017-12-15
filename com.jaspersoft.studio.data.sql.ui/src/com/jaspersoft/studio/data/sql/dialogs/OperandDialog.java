/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.dialogs;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpressionX;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.widgets.Factory;

public class OperandDialog extends ATitledDialog {
	private AMExpression<?> mexpression;
	private int index;
	private List<AOperand> operands;
	private AQueryDesigner designer;

	protected OperandDialog(Shell parentShell, AQueryDesigner designer) {
		super(parentShell);
		this.designer = designer;
		setTitle(Messages.OperandDialog_0);
		setDescription(Messages.OperandDialog_1);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout());

		Control w = null;
		if (mexpression instanceof MExpressionX)
			w = Factory.createWidget(cmp, operands.get(index), designer);
		else if (mexpression instanceof MExpression)
			w = Factory.createWidget(cmp, operands, index, (MExpression) mexpression, true, designer);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		w.setLayoutData(gd);

		return cmp;
	}

	public AOperand getOperand() {
		return operands.get(index);
	}

	public void setValues(AMExpression<?> mexpression, List<AOperand> operands, int index) {
		this.mexpression = mexpression;
		this.operands = operands;
		this.index = index;
	}
}
