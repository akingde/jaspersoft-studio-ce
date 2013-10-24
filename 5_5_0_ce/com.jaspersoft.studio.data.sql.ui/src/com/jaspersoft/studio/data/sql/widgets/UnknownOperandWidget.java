package com.jaspersoft.studio.data.sql.widgets;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.operand.UnknownOperand;

public class UnknownOperandWidget extends AOperandWidget<UnknownOperand> {
	private Text txt;

	public UnknownOperandWidget(Composite parent, UnknownOperand operand) {
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

		txt = new Text(this, SWT.BORDER);
		txt.setText(getValue().toSQLString());
		txt.setToolTipText(getValue().toSQLString());
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		DataBindingContext bindingContext = new DataBindingContext();
		bindingContext.bindValue(SWTObservables.observeText(txt, SWT.Modify), PojoObservables.observeValue(getValue(), "value")); //$NON-NLS-1$
	}

}
