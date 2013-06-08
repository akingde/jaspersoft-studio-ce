package com.jaspersoft.studio.data.sql.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.dialogs.UsedColumnsDialog;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;

public class FieldWidget extends AOperandWidget<FieldOperand> {

	private Text txt;

	public FieldWidget(Composite parent, FieldOperand operand) {
		super(parent, SWT.NONE, operand);
	}

	@Override
	protected void createWidget(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 3;
		setLayout(layout);

		final FieldOperand v = getValue();

		txt = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		txt.setText(v.toSQLString());
		txt.setToolTipText(v.toSQLString());
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button b = new Button(this, SWT.PUSH);
		b.setText("...");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UsedColumnsDialog dialog = new UsedColumnsDialog(Display.getDefault().getActiveShell());
				dialog.setRoot(Util.getRoot(v.getValue(), v.getExpression()));
				dialog.setSelection(v.getExpression());
				if (dialog.open() == Dialog.OK)
					v.setValue(dialog.getColumns().get(0));
				txt.setText(v.toSQLString());
				txt.setToolTipText(v.toSQLString());
			}
		});
	}

}
