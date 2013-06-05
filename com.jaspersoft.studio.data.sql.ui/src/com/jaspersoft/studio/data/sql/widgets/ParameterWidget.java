package com.jaspersoft.studio.data.sql.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.operand.ParameterOperand;

public class ParameterWidget extends AOperandWidget<ParameterOperand> {
	private Text txt;

	public ParameterWidget(Composite parent, ParameterOperand operand) {
		super(parent, SWT.NONE, operand);

	}

	@Override
	protected void createWidget(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		txt = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		txt.setText(getValue().toSQLString());
		txt.setToolTipText(getValue().toSQLString());
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button b = new Button(this, SWT.PUSH);
		b.setText("...");
	}

}
