package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Spinner;

public class NumericPattern extends APattern {

	public NumericPattern(Composite parent) {
		this(parent, NumberFormat.getNumberInstance());
	}

	public NumericPattern(Composite parent, Format formatter) {
		super(parent, formatter, new BigDecimal("-10023.1234567654"));
		setDescription("Number format is used to display numerical values.");
	}

	@Override
	public Control createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, true));

		Label lab = new Label(container, SWT.NONE | SWT.CENTER);
		lab.setText("Leading &zeroes");

		lab = new Label(container, SWT.NONE | SWT.CENTER);
		lab.setText("&Decimal places:");

		final Spinner zeroes = new Spinner(container, SWT.BORDER);
		zeroes.setMinimum(0);
		zeroes.setMaximum(100);
		zeroes.setSelection(1);
		zeroes.setIncrement(1);
		zeroes.setPageIncrement(10);
		zeroes.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Spinner decimals = new Spinner(container, SWT.BORDER);
		decimals.setMinimum(0);
		decimals.setMaximum(100);
		decimals.setSelection(2);
		decimals.setIncrement(1);
		decimals.setPageIncrement(10);
		decimals.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Button sep = new Button(container, SWT.CHECK);
		sep.setText("Use 1000 separator");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		sep.setLayoutData(gd);

		list = new List(container, SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 2;
		gd.heightHint = 200;
		gd.widthHint = 100;
		list.setLayoutData(gd);

		DecimalFormat f = (DecimalFormat) getFormatter();
		for (String s : getDefaults()) {
			f.applyPattern(s);
			list.add(f.format(getSample()));
		}
		sep.addSelectionListener(new SelectionListener() {
			private boolean state = false;

			@Override
			public void widgetSelected(SelectionEvent e) {
				DecimalFormat d = (DecimalFormat) getFormatter();
				state = !state;
				d.setGroupingUsed(state);
				d.setGroupingSize(3);
				setPattern(d.toPattern());
				formatChanged();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		decimals.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				DecimalFormat d = (DecimalFormat) getFormatter();
				d.setMinimumFractionDigits(decimals.getSelection());
				setPattern(d.toPattern());
				formatChanged();
			}
		});
		zeroes.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				DecimalFormat d = (DecimalFormat) getFormatter();
				d.setMinimumIntegerDigits(zeroes.getSelection());
				setPattern(d.toPattern());
				formatChanged();
			}
		});
		list.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sel = list.getSelectionIndex();
				if (sel > 0) {
					setPattern(dList.get(sel));
				}
				formatChanged();
				DecimalFormat d = (DecimalFormat) getFormatter();
				sep.setSelection(d.isGroupingUsed());
				zeroes.setSelection(d.getMinimumIntegerDigits());
				decimals.setSelection(d.getMinimumFractionDigits());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		return container;
	}

	protected java.util.List<String> dList;
	protected List list;

	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("#,##0.###;#,-##0.###");
			dList.add("#,##0.###;#,##0.###-");
			dList.add("#,##0.###;(#,##0.###)");
			dList.add("#,##0.###;(-#,##0.###)");
			dList.add("#,##0.###;(#,##0.###-)");
			setPattern(dList.get(0));
		}
		return dList;
	}

	@Override
	protected void formatChanged() {
		super.formatChanged();
	}
}
