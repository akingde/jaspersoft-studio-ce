/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.data.json;

import java.util.Locale;
import java.util.TimeZone;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.json.JsonDataAdapter;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.jface.dialogs.LocaleDialog;
import com.jaspersoft.studio.jface.dialogs.TimeZoneDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.swt.binding.LocaleToStringConverter;
import com.jaspersoft.studio.swt.binding.String2LocaleConverter;
import com.jaspersoft.studio.swt.binding.String2TimeZoneConverter;
import com.jaspersoft.studio.swt.binding.TimeZone2StringConverter;

public class JsonDataAdapterComposite extends ADataAdapterComposite {

	private Button btnRadioButtonUseXpath = null;
	private Button btnRadioButtonCreateDataAdapter = null;
	private Text textFileName;
	private Text textSelectExpression;
	private Text textDatePattern;
	private Text textNumberPattern;
	private Text textLocale;
	private Text textTimeZone;
	private boolean useConnection = false;
	private Locale locale = null;
	private TimeZone timeZone = null;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JsonDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText(Messages.JsonDataAdapterComposite_0);

		textFileName = new Text(composite, SWT.BORDER);
		textFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnBrowse = new Button(composite, SWT.NONE);
		GridData gd_btnBrowse = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnBrowse.widthHint = 100;
		btnBrowse.setLayoutData(gd_btnBrowse);
		btnBrowse.setText(Messages.JsonDataAdapterComposite_1);

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		composite_1.setLayout(new FillLayout(SWT.VERTICAL));

		btnRadioButtonUseXpath = new Button(composite_1, SWT.RADIO);
		btnRadioButtonUseXpath.setText(Messages.JsonDataAdapterComposite_2);

		btnRadioButtonCreateDataAdapter = new Button(composite_1, SWT.RADIO);
		btnRadioButtonCreateDataAdapter
				.setText(Messages.JsonDataAdapterComposite_3);
		btnRadioButtonCreateDataAdapter.setSelection(true);

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);

		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText(Messages.JsonDataAdapterComposite_4);

		textSelectExpression = new Text(composite_2, SWT.BORDER);
		textSelectExpression.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		new Label(composite_1, SWT.SEPARATOR | SWT.HORIZONTAL);

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		GridLayout gl_composite_3 = new GridLayout(3, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);

		Label lblNewLabel_2 = new Label(composite_3, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_2.setText(Messages.JsonDataAdapterComposite_5);

		textDatePattern = new Text(composite_3, SWT.BORDER);
		textDatePattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnCreateDatePattern = new Button(composite_3, SWT.NONE);
		GridData gd_btnCreateDatePattern = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_btnCreateDatePattern.widthHint = 100;
		btnCreateDatePattern.setLayoutData(gd_btnCreateDatePattern);
		btnCreateDatePattern.setText(Messages.JsonDataAdapterComposite_6);

		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_3.setText(Messages.JsonDataAdapterComposite_7);

		textNumberPattern = new Text(composite_3, SWT.BORDER);
		textNumberPattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Button btnCreateNumberPattern = new Button(composite_3, SWT.NONE);
		GridData gd_btnCreateNumberPattern = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_btnCreateNumberPattern.widthHint = 100;
		btnCreateNumberPattern.setLayoutData(gd_btnCreateNumberPattern);
		btnCreateNumberPattern.setText(Messages.JsonDataAdapterComposite_8);

		Group grpLocaleTime = new Group(this, SWT.SHADOW_NONE);
		grpLocaleTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		grpLocaleTime.setText(Messages.JsonDataAdapterComposite_9);
		GridLayout gl_grpLocaleTime = new GridLayout(3, false);
		gl_grpLocaleTime.marginWidth = 0;
		gl_grpLocaleTime.marginHeight = 0;
		grpLocaleTime.setLayout(gl_grpLocaleTime);

		Label lblNewLabel_4 = new Label(grpLocaleTime, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_4.setText(Messages.JsonDataAdapterComposite_10);

		textLocale = new Text(grpLocaleTime, SWT.BORDER);
		textLocale.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		textLocale.setEnabled(false);

		Button btnSelectLocale = new Button(grpLocaleTime, SWT.NONE);
		GridData gd_btnSelectLocale = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnSelectLocale.widthHint = 100;
		btnSelectLocale.setLayoutData(gd_btnSelectLocale);
		btnSelectLocale.setText(Messages.JsonDataAdapterComposite_11);

		Label lblNewLabel_5 = new Label(grpLocaleTime, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_5.setText(Messages.JsonDataAdapterComposite_12);

		textTimeZone = new Text(grpLocaleTime, SWT.BORDER);
		textTimeZone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		textTimeZone.setEnabled(false);

		Button btnSelectTimeZone = new Button(grpLocaleTime, SWT.NONE);
		GridData gd_btnSelectTimeZone = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_btnSelectTimeZone.widthHint = 100;
		btnSelectTimeZone.setLayoutData(gd_btnSelectTimeZone);
		btnSelectTimeZone.setText(Messages.JsonDataAdapterComposite_13);

		// UI elements listener
		btnRadioButtonUseXpath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textSelectExpression.setEnabled(false);
				useConnection = true;
				pchangesuport.firePropertyChange("createdataadapter", false,
						true);
			}
		});

		btnRadioButtonCreateDataAdapter
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						textSelectExpression.setEnabled(true);
						useConnection = false;
						pchangesuport.firePropertyChange("createdataadapter",
								false, true);
					}
				});

		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell());
				fd.setFileName(textFileName.getText());
				fd.setFilterExtensions(new String[] { "*.json", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String selection = fd.open();
				if (selection != null)
					textFileName.setText(selection);
			}
		});

		btnCreateDatePattern.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PatternEditor wizard = new PatternEditor();
				wizard.setNumberPatterns(false);
				wizard.setValue(textDatePattern.getText());
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
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
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
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
				LocaleDialog localeDialog = new LocaleDialog(getShell(), locale);
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
				TimeZoneDialog timeZoneDialog = new TimeZoneDialog(getShell(),
						timeZone);
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

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		JsonDataAdapter xmlDataAdapter = (JsonDataAdapter) dataAdapter;

		bindingContext.bindValue(
				SWTObservables.observeText(textFileName, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "fileName")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textSelectExpression, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "selectExpression")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textDatePattern, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "datePattern")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textNumberPattern, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "numberPattern")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables
				.observeSelection(btnRadioButtonCreateDataAdapter),
				PojoObservables.observeValue(dataAdapter, "useConnection")); //$NON-NLS-1$

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

		if (xmlDataAdapter.isUseConnection()) {
			useConnection = true;
			btnRadioButtonCreateDataAdapter.setSelection(false);
			textSelectExpression.setEnabled(false);
			btnRadioButtonUseXpath.setSelection(true);
		} else {
			useConnection = false;
			btnRadioButtonUseXpath.setSelection(false);
			btnRadioButtonCreateDataAdapter.setSelection(true);
			textSelectExpression.setEnabled(true);
		}

		locale = xmlDataAdapter.getLocale();
		if (locale != null) {
			textLocale.setText(locale.getDisplayName());
		} else {
			textLocale.setText(Messages.RemoteXMLDataAdapterComposite_15
					+ Locale.getDefault().getDisplayName()
					+ Messages.RemoteXMLDataAdapterComposite_16);
			locale = Locale.getDefault();
		}

		timeZone = xmlDataAdapter.getTimeZone();
		if (timeZone != null) {
			textTimeZone.setText(timeZone.getID());
		} else {
			textTimeZone.setText(Messages.RemoteXMLDataAdapterComposite_15
					+ TimeZone.getDefault().getID()
					+ Messages.RemoteXMLDataAdapterComposite_16);
			timeZone = TimeZone.getDefault();
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new JsonDataAdapterDescriptor();

		JsonDataAdapter dataAdapter = (JsonDataAdapter) dataAdapterDesc
				.getDataAdapter();

		dataAdapter.setFileName(textFileName.getText());
		dataAdapter.setUseConnection(useConnection);
		dataAdapter.setSelectExpression(textSelectExpression.getText());

		dataAdapter.setDatePattern(textDatePattern.getText());
		dataAdapter.setNumberPattern(textNumberPattern.getText());
		dataAdapter.setLocale(locale);
		dataAdapter.setTimeZone(timeZone);

		return dataAdapterDesc;
	}

}
