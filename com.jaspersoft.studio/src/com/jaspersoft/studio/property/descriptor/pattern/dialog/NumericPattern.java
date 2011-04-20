/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
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

import com.jaspersoft.studio.messages.Messages;

public class NumericPattern extends APattern {

	public NumericPattern(Composite parent) {
		this(parent, NumberFormat.getNumberInstance());
	}

	public NumericPattern(Composite parent, Format formatter) {
		super(parent, formatter, new BigDecimal("-10023.1234567654")); //$NON-NLS-1$
		setDescription(Messages.NumericPattern_description);
	}

	@Override
	public Control createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, true));

		Label lab = new Label(container, SWT.NONE | SWT.CENTER);
		lab.setText(Messages.NumericPattern_leading_zeroes);

		lab = new Label(container, SWT.NONE | SWT.CENTER);
		lab.setText(Messages.NumericPattern_decimal_places + ":");

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
		sep.setText(Messages.NumericPattern_use_1000_sperator);
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

			public void widgetSelected(SelectionEvent e) {
				DecimalFormat d = (DecimalFormat) getFormatter();
				state = !state;
				d.setGroupingUsed(state);
				d.setGroupingSize(3);
				setPattern(d.toPattern());
				formatChanged();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		decimals.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				DecimalFormat d = (DecimalFormat) getFormatter();
				d.setMinimumFractionDigits(decimals.getSelection());
				setPattern(d.toPattern());
				formatChanged();
			}
		});
		zeroes.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				DecimalFormat d = (DecimalFormat) getFormatter();
				d.setMinimumIntegerDigits(zeroes.getSelection());
				setPattern(d.toPattern());
				formatChanged();
			}
		});
		list.addSelectionListener(new SelectionListener() {

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
			dList.add("#,##0.###;#,-##0.###"); //$NON-NLS-1$
			dList.add("#,##0.###;#,##0.###-"); //$NON-NLS-1$
			dList.add("#,##0.###;(#,##0.###)"); //$NON-NLS-1$
			dList.add("#,##0.###;(-#,##0.###)"); //$NON-NLS-1$
			dList.add("#,##0.###;(#,##0.###-)"); //$NON-NLS-1$
			setPattern(dList.get(0));
		}
		return dList;
	}

}
