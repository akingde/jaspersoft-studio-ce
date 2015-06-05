/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.studio.data;

import java.util.Locale;
import java.util.TimeZone;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.jface.dialogs.LocaleDialog;
import com.jaspersoft.studio.jface.dialogs.TimeZoneDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.swt.binding.LocaleToStringConverter;
import com.jaspersoft.studio.swt.binding.String2LocaleConverter;
import com.jaspersoft.studio.swt.binding.String2TimeZoneConverter;
import com.jaspersoft.studio.swt.binding.TimeZone2StringConverter;

public class DateNumberFormatWidget extends Composite {
	private Text textDatePattern;
	private Text textNumberPattern;
	private Text textLocale;
	private Text textTimeZone;
	private Locale locale = null;
	private TimeZone timeZone = null;

	public DateNumberFormatWidget(Composite parent) {
		super(parent, SWT.NONE);
		setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_3 = new GridLayout(3, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		gl_composite_3.verticalSpacing = 2;
		setLayout(gl_composite_3);

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setText(Messages.JsonDataAdapterComposite_5);

		textDatePattern = new Text(this, SWT.BORDER);
		textDatePattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button btnCreateDatePattern = new Button(this, SWT.NONE);
		GridData gd = new GridData();
		gd.widthHint = 100;
		btnCreateDatePattern.setLayoutData(gd);
		btnCreateDatePattern.setText(Messages.JsonDataAdapterComposite_6);

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setText(Messages.JsonDataAdapterComposite_7);

		textNumberPattern = new Text(this, SWT.BORDER);
		textNumberPattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button btnCreateNumberPattern = new Button(this, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		btnCreateNumberPattern.setLayoutData(gd);
		btnCreateNumberPattern.setText(Messages.JsonDataAdapterComposite_8);

		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setText(Messages.JsonDataAdapterComposite_10);

		textLocale = new Text(this, SWT.BORDER);
		textLocale.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textLocale.setEnabled(false);

		Button btnSelectLocale = new Button(this, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		btnSelectLocale.setLayoutData(gd);
		btnSelectLocale.setText(Messages.JsonDataAdapterComposite_11);

		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setText(Messages.JsonDataAdapterComposite_12);

		textTimeZone = new Text(this, SWT.BORDER);
		textTimeZone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textTimeZone.setEnabled(false);

		Button btnSelectTimeZone = new Button(this, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		btnSelectTimeZone.setLayoutData(gd);
		btnSelectTimeZone.setText(Messages.JsonDataAdapterComposite_13);

		btnCreateDatePattern.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setNumberPatterns(false);
				wizard.setValue(textDatePattern.getText());
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(),
						wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textDatePattern.setText(val);
				}
			}
		});

		btnCreateNumberPattern.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setDatePatterns(false);
				wizard.setValue(textNumberPattern.getText());
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(),
						wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textNumberPattern.setText(val);
				}
			}
		});

		btnSelectLocale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LocaleDialog localeDialog = new LocaleDialog(
						UIUtils.getShell(), locale);
				localeDialog.create();

				if (localeDialog.open() == Dialog.OK) {
					locale = localeDialog.getLocale();
					textLocale.setText(locale.getDisplayName());
				}
			}
		});

		btnSelectTimeZone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TimeZoneDialog timeZoneDialog = new TimeZoneDialog(UIUtils
						.getShell(), timeZone);
				timeZoneDialog.create();

				if (timeZoneDialog.open() == Dialog.OK) {
					timeZone = timeZoneDialog.getTimeZone();
					if (timeZone != null) {
						textTimeZone.setText(timeZone.getID());
					}
				}
			}
		});
	}

	public void bindWidgets(DataAdapter dataAdapter,
			DataBindingContext bindingContext, Locale l, TimeZone t) {
		bindingContext.bindValue(
				SWTObservables.observeText(textDatePattern, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "datePattern")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textNumberPattern, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "numberPattern")); //$NON-NLS-1$

		bindingContext
				.bindValue(
						SWTObservables.observeText(textLocale, SWT.Modify),
						PojoObservables.observeValue(dataAdapter, "locale"), new UpdateValueStrategy() //$NON-NLS-1$
								.setConverter(new String2LocaleConverter()),
						new UpdateValueStrategy()
								.setConverter(new LocaleToStringConverter()));

		bindingContext
				.bindValue(
						SWTObservables.observeText(textTimeZone, SWT.Modify),
						PojoObservables.observeValue(dataAdapter, "timeZone"), new UpdateValueStrategy() //$NON-NLS-1$
								.setConverter(new String2TimeZoneConverter()),
						new UpdateValueStrategy()
								.setConverter(new TimeZone2StringConverter()));

		locale = l;
		if (locale != null)
			textLocale.setText(locale.getDisplayName());
		else {
			textLocale.setText(Messages.RemoteXMLDataAdapterComposite_15
					+ Locale.getDefault().getDisplayName()
					+ Messages.RemoteXMLDataAdapterComposite_16);
			locale = Locale.getDefault();
		}

		timeZone = t;
		if (timeZone != null)
			textTimeZone.setText(timeZone.getID());
		else {
			textTimeZone.setText(Messages.RemoteXMLDataAdapterComposite_15
					+ TimeZone.getDefault().getID()
					+ Messages.RemoteXMLDataAdapterComposite_16);
			timeZone = TimeZone.getDefault();
		}
	}

	public String getTextDatePattern() {
		String txt = textDatePattern.getText();
		if (txt != null && txt.trim().isEmpty())
			return null;
		return txt;
	}

	public String getTextNumberPattern() {
		String txt = textNumberPattern.getText();
		if (txt != null && txt.trim().isEmpty())
			return null;
		return txt;
	}

	public Locale getLocale() {
		return locale;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}
}
