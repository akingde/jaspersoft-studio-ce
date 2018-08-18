/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.widgets.Factory;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

public class EditExpressionDialog extends ATitledDialog {
	private MExpression value;
	private AQueryDesigner designer;

	public EditExpressionDialog(Shell parentShell, AQueryDesigner designer) {
		super(parentShell);
		this.designer = designer;
		setTitle(Messages.EditExpressionDialog_0);
		setDescription(
				Messages.EditExpressionDialog_1 + Messages.EditExpressionDialog_2 + Messages.EditExpressionDialog_3);
	}

	public void setValue(MExpression value) {
		this.value = value;
		setOperator(value.getOperator().getSqlname());
		setPrevcond(value.getPrevCond());
		operands = new ArrayList<>(value.getOperands());
	}

	private java.util.List<AOperand> operands;
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

			new Label(c, SWT.NONE).setText(Messages.EditExpressionDialog_4);

			Combo prevoperator = new Combo(c, SWT.READ_ONLY);
			prevoperator.setItems(AMKeyword.CONDITIONS);

			new Label(c, SWT.NONE).setText(Messages.EditExpressionDialog_5);

			bindingContext.bindValue(SWTObservables.observeSelection(prevoperator),
					PojoObservables.observeValue(this, "prevcond")); //$NON-NLS-1$
		} else {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 5;
			new Label(cmp, SWT.NONE).setLayoutData(gd);
		}

		Control w = Factory.createWidget(cmp, operands, 0, value, designer);
		GridData gd = new GridData(
				GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 250;
		w.setLayoutData(gd);

		Combo optr = new Combo(cmp, SWT.READ_ONLY);
		optr.setItems(Operator.operators);
		optr.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		optr.addSelectionListener(new SelectionAdapter() {
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
		gd.widthHint = 400;
		gd.verticalSpan = 2;
		gd.horizontalSpan = 3;
		rcmp.setLayoutData(gd);

		showRight();
		bindingContext.bindValue(SWTObservables.observeSelection(optr),
				PojoObservables.observeValue(this, "operator")); //$NON-NLS-1$
		return cmp;
	}

	private Map<String, Composite> map = new HashMap<>();
	private Composite rcmp;
	private StackLayout stackLayout;
	private Button opDel;
	private Button opEdit;
	private List inlist;

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

				Control w = Factory.createWidget(cmp, operands, 1, value, designer);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER);
				gd.widthHint = 250;
				w.setLayoutData(gd);
			} else if (op.getNrOperands() == 3 && (op == Operator.BETWEEN || op == Operator.NOTBETWEEN)) {
				cmp = new Composite(rcmp, SWT.NONE);
				GridLayout layout = new GridLayout(3, false);
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				cmp.setLayout(layout);

				Control w = Factory.createWidget(cmp, operands, 1, value, designer);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER);
				gd.widthHint = 250;
				w.setLayoutData(gd);

				new Label(cmp, SWT.NONE).setText(Messages.EditExpressionDialog_6);

				w = Factory.createWidget(cmp, operands, 2, value, designer);
				gd = new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER);
				gd.widthHint = 250;
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
		inlist = new List(cmp, SWT.MULTI | SWT.READ_ONLY | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		gd.widthHint = 250;
		inlist.setLayoutData(gd);

		Button op3 = new Button(cmp, SWT.PUSH);
		op3.setText(Messages.EditExpressionDialog_7);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		op3.setLayoutData(gd);
		op3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleAddInList(inlist);
			}
		});

		opEdit = new Button(cmp, SWT.PUSH);
		opEdit.setText(Messages.EditExpressionDialog_8);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		opEdit.setLayoutData(gd);
		opEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleEditInList(inlist);
			}
		});

		opDel = new Button(cmp, SWT.PUSH);
		opDel.setText(Messages.EditExpressionDialog_9);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		opDel.setLayoutData(gd);
		opDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = inlist.getSelectionIndex() + 1;
				if (index >= 0 && index < operands.size()) {
					operands.remove(index);
					showInList(inlist);
				}
			}
		});
		inlist.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setupListButtons();
			}
		});
		inlist.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				handleEditInList(inlist);
			}
		});

		showInList(inlist);
		setupListButtons();
	}

	private void setupListButtons() {
		boolean canEdit = inlist.getSelectionCount() > 0;
		opEdit.setEnabled(canEdit);
		opDel.setEnabled(canEdit);
	}

	private void handleAddInList(List inlist) {
		int index = Math.max(0, inlist.getSelectionIndex());
		OperandDialog dialog = new OperandDialog(getShell(), designer);
		ArrayList<AOperand> ops = new ArrayList<>(operands);
		if (index < ops.size())
			ops.add(index, Factory.getDefaultOperand(value));
		else
			ops.add(Factory.getDefaultOperand(value));
		dialog.setValues(value, ops, index);
		if (dialog.open() == Dialog.OK) {
			AOperand op = dialog.getOperand();
			if (index < ops.size())
				operands.add(index + 1, op);
			else
				ops.add(op);
			showInList(inlist);
		}
	}

	private void handleEditInList(List inlist) {
		int index = inlist.getSelectionIndex() + 1;
		if (index >= 0 && index < operands.size()) {
			OperandDialog dialog = new OperandDialog(getShell(), designer);
			dialog.setValues(value, new ArrayList<AOperand>(operands), index);
			if (dialog.open() == Dialog.OK) {
				operands.set(index, dialog.getOperand());
				showInList(inlist);
			}
		}
	}

	private void showInList(List inlist) {
		String[] ilarray = new String[Math.max(operands.size() - 1, 0)];
		if (!operands.isEmpty())
			for (int i = 1; i < operands.size(); i++)
				ilarray[i - 1] = operands.get(i).toSQLString();
		inlist.setItems(ilarray);
	}

}
