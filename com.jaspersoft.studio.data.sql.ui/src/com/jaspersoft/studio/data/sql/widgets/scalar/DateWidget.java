package com.jaspersoft.studio.data.sql.widgets.scalar;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class DateWidget extends AScalarWidget {
	private CDateTime date;

	public DateWidget(Composite parent, ScalarOperand<?> operand) {
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

		date = new CDateTime(this, getDateStyle());
		date.setToolTipText(getValue().toSQLString());
		date.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		date.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Date sdate = date.getSelection();
				((ScalarOperand<Date>) getValue()).setValue(sdate);
			}
		});

		date.setSelection((Date) getValue().getValue());
	}

	protected int getDateStyle() {
		return CDT.BORDER | CDT.DATE_SHORT | CDT.DROP_DOWN;
	}
}
