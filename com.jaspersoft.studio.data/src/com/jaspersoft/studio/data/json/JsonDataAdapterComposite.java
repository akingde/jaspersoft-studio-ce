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
package com.jaspersoft.studio.data.json;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.json.JsonDataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.AFileDataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DateNumberFormatWidget;
import com.jaspersoft.studio.data.messages.Messages;

public class JsonDataAdapterComposite extends AFileDataAdapterComposite {

	private Button btnRadioButtonUseXpath = null;
	private Button btnRadioButtonCreateDataAdapter = null;
	private Text textSelectExpression;

	private boolean useConnection = false;

	private DateNumberFormatWidget dnf;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JsonDataAdapterComposite(Composite parent, int style,
			JasperReportsContext jrContext) {
		super(parent, style, jrContext);
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		createFileNameWidgets(composite);

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

		dnf = new DateNumberFormatWidget(this);

		// UI elements listener
		btnRadioButtonUseXpath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textSelectExpression.setEnabled(false);
				useConnection = true;
				pchangesuport.firePropertyChange(
						"createdataadapter", false, true); //$NON-NLS-1$
			}
		});

		btnRadioButtonCreateDataAdapter
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						textSelectExpression.setEnabled(true);
						useConnection = false;
						pchangesuport.firePropertyChange(
								"createdataadapter", false, true); //$NON-NLS-1$
					}
				});

	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		JsonDataAdapter xmlDataAdapter = (JsonDataAdapter) dataAdapter;

		doBindFileNameWidget(xmlDataAdapter);
		bindingContext.bindValue(
				SWTObservables.observeText(textSelectExpression, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "selectExpression")); //$NON-NLS-1$

		dnf.bindWidgets(xmlDataAdapter, bindingContext,
				xmlDataAdapter.getLocale(), xmlDataAdapter.getTimeZone());

		bindingContext.bindValue(SWTObservables
				.observeSelection(btnRadioButtonCreateDataAdapter),
				PojoObservables.observeValue(dataAdapter, "useConnection")); //$NON-NLS-1$

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
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new JsonDataAdapterDescriptor();

		JsonDataAdapter dataAdapter = (JsonDataAdapter) dataAdapterDesc
				.getDataAdapter();

		dataAdapter.setUseConnection(useConnection);
		dataAdapter.setSelectExpression(textSelectExpression.getText());

		dataAdapter.setDatePattern(dnf.getTextDatePattern());
		dataAdapter.setNumberPattern(dnf.getTextNumberPattern());
		dataAdapter.setLocale(dnf.getLocale());
		dataAdapter.setTimeZone(dnf.getTimeZone());

		return dataAdapterDesc;
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_json"); //$NON-NLS-1$
	}

	@Override
	protected String[] getFileExtensions() {
		return new String[] { "*.json", "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
