/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.dataset.wizard;

import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class WizardConnectionPage extends WizardPage {
	private MDatasetRun datasetrun;

	public void setDataSetRun(MDatasetRun datasetrun) {
		this.datasetrun = datasetrun;
		JRDesignDatasetRun ct = (JRDesignDatasetRun) datasetrun.getValue();
		if (ct == null)
			datasetrun.setValue(new JRDesignDatasetRun());
	}

	public MDatasetRun getDataSetRun() {
		return datasetrun;
	}

	public WizardConnectionPage() {
		super("connectionpage"); //$NON-NLS-1$
		setTitle(Messages.common_connection);
		setDescription(Messages.WizardConnectionPage_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		final Button mainReport = new Button(composite, SWT.RADIO);
		mainReport.setText(Messages.WizardConnectionPage_mainreport_text);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		mainReport.setLayoutData(gd);
		mainReport.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (mainReport.getSelection())
					mainReportConnection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		mainReport.setSelection(true);
		mainReportConnection();

		// -----------------------------------------------------------------------

		final Button connection = new Button(composite, SWT.RADIO);
		connection.setText(Messages.WizardConnectionPage_connection_text);
		gd = new GridData();
		gd.horizontalSpan = 2;
		connection.setLayoutData(gd);

		final Text connExpr = new Text(composite, SWT.BORDER);
		gd = new GridData();
		gd.widthHint = 500;
		connExpr.setLayoutData(gd);
		connExpr.setEnabled(false);

		final Button connExprDialog = new Button(composite, SWT.PUSH);
		connExprDialog.setText("..."); //$NON-NLS-1$
		connExprDialog.setEnabled(false);
		connExprDialog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();

				JRDesignExpression mexp = (JRDesignExpression) datasetrun
						.getPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION);

				wizard.setValue(mexp);
				WizardDialog dialog = new WizardDialog(connExprDialog.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mexp = wizard.getValue();
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, mexp);
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, null);

					connExpr.setText(mexp.getText());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		connExpr.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, connExpr.getText());
				datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, null);
			}
		});

		connection.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				boolean selection = connection.getSelection();
				connExpr.setEnabled(selection);
				connExprDialog.setEnabled(selection);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// ---------------------------------------------------------------------------

		final Button dataSource = new Button(composite, SWT.RADIO);
		dataSource.setText(Messages.WizardConnectionPage_datasource_text);
		gd = new GridData();
		gd.horizontalSpan = 2;
		dataSource.setLayoutData(gd);

		final Text dsExpr = new Text(composite, SWT.BORDER);
		gd = new GridData();
		gd.widthHint = 500;
		dsExpr.setLayoutData(gd);
		dsExpr.setEnabled(false);

		final Button dsExprDialog = new Button(composite, SWT.PUSH);
		dsExprDialog.setText("..."); //$NON-NLS-1$
		dsExprDialog.setEnabled(false);

		dataSource.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				boolean selection = dataSource.getSelection();
				dsExpr.setEnabled(selection);
				dsExprDialog.setEnabled(selection);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		dsExprDialog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();

				JRDesignExpression mexp = (JRDesignExpression) datasetrun
						.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION);

				wizard.setValue(mexp);
				WizardDialog dialog = new WizardDialog(dsExprDialog.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mexp = wizard.getValue();
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, null);
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, mexp);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		dsExpr.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, null);
				datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, connExpr.getText());
			}
		});

		// ------------------------------------------------------------------------------

		final Button emptyConnection = new Button(composite, SWT.RADIO);
		emptyConnection.setText(Messages.WizardConnectionPage_empty_connection_text);
		gd = new GridData();
		gd.horizontalSpan = 2;
		emptyConnection.setLayoutData(gd);
		emptyConnection.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (emptyConnection.getSelection()) {
					JRDesignExpression jde = new JRDesignExpression();
					jde.setText("new net.sf.jasperreports.engine.JREmptyDataSource()"); //$NON-NLS-1$

					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, null);
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, jde);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		final Button noConnection = new Button(composite, SWT.RADIO);
		noConnection.setText(Messages.WizardConnectionPage_noconnection_text);
		gd = new GridData();
		gd.horizontalSpan = 2;
		noConnection.setLayoutData(gd);
		noConnection.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (noConnection.getSelection()) {
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, null);
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, null);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");
	}

	private void mainReportConnection() {
		JRDesignExpression jde = new JRDesignExpression();
		jde.setText("$P{REPORT_CONNECTION}"); //$NON-NLS-1$

		datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, jde);
		datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, null);
	}

}
