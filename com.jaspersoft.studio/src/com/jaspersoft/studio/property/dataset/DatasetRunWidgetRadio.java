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
package com.jaspersoft.studio.property.dataset;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

public class DatasetRunWidgetRadio implements IExpressionContextSetter{
	private JRDesignDatasetRun datasetrun;

	public DatasetRunWidgetRadio(Composite parent) {
		createControl(parent);
	}

	public void setData(JRDesignDatasetRun datasetrun) {
		this.datasetrun = datasetrun;
		if (datasetrun != null) {
			dsRunExpr.setEnabled(false);
			otherExpr.setEnabled(false);

			if (datasetrun.getConnectionExpression() == null && datasetrun.getDataSourceExpression() == null) {
				noCon.setSelection(true);
			} else if (datasetrun.getConnectionExpression() != null
					&& datasetrun.getConnectionExpression().getText().equals("$P{REPORT_CONNECTION}")) {
				sameCon.setSelection(true);
			} else if (datasetrun.getConnectionExpression() != null) {
				otherExpr.setEnabled(true);
				otherExpr.setExpression((JRDesignExpression) datasetrun.getConnectionExpression());
				otherCon.setSelection(true);
			} else if (datasetrun.getDataSourceExpression() != null
					&& datasetrun.getDataSourceExpression().getText()
							.equals("new net.sf.jasperreports.engine.JREmptyDataSource()")) {
				emptyCon.setSelection(true);
			} else if (datasetrun.getDataSourceExpression() != null) {
				dsRunExpr.setEnabled(true);
				dsRunExpr.setExpression((JRDesignExpression) datasetrun.getDataSourceExpression());
				jrdCon.setSelection(true);
			} else {
				noCon.setSelection(true);
			}
		}
	}

	public Control getControl() {
		return composite;
	}

	private Composite composite;
	private WTextExpression dsRunExpr;
	private WTextExpression otherExpr;
	private Button sameCon;
	private Button otherCon;
	private Button jrdCon;
	private Button emptyCon;
	private Button noCon;

	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		noCon = new Button(composite, SWT.RADIO);
		noCon.setText(Messages.WizardConnectionPage_noconnection_text);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		noCon.setLayoutData(gd);

		otherCon = new Button(composite, SWT.RADIO);
		otherCon.setText(Messages.WizardConnectionPage_connection_text);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		otherCon.setLayoutData(gd);

		otherExpr = new WTextExpression(composite, SWT.NONE);
		otherExpr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		jrdCon = new Button(composite, SWT.RADIO);
		jrdCon.setText(Messages.WizardConnectionPage_datasource_text);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		jrdCon.setLayoutData(gd);

		dsRunExpr = new WTextExpression(composite, SWT.NONE);
		dsRunExpr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		sameCon = new Button(composite, SWT.RADIO);
		sameCon.setText(Messages.WizardConnectionPage_mainreport_text);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		sameCon.setLayoutData(gd);

		emptyCon = new Button(composite, SWT.RADIO);
		emptyCon.setText(Messages.WizardConnectionPage_empty_connection_text);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		emptyCon.setLayoutData(gd);

		SelectionListener listener = new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (noCon.getSelection())
					setNoConnection();
				else if (otherCon.getSelection())
					setConnection(""); //$NON-NLS-1$
				else if (jrdCon.getSelection())
					setDatasource("");//$NON-NLS-1$
				else if (sameCon.getSelection())
					setConnection("$P{REPORT_CONNECTION}"); //$NON-NLS-1$
				else if (emptyCon.getSelection())
					setDatasource("new net.sf.jasperreports.engine.JREmptyDataSource()");//$NON-NLS-1$
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};
		sameCon.addSelectionListener(listener);
		otherCon.addSelectionListener(listener);
		jrdCon.addSelectionListener(listener);
		emptyCon.addSelectionListener(listener);
		noCon.addSelectionListener(listener);
	}

	private void setNoConnection() {
		if (datasetrun != null) {
			datasetrun.setConnectionExpression(null);
			datasetrun.setDataSourceExpression(null);
			setData(datasetrun);
		}
	}

	private void setDatasource(String exTxt) {
		if (datasetrun != null) {
			JRDesignExpression jde = (JRDesignExpression) datasetrun.getDataSourceExpression();
			if (jde == null)
				jde = new JRDesignExpression();
			jde.setValueClass(JRDataSource.class);
			jde.setText(exTxt);
			datasetrun.setConnectionExpression(null);
			datasetrun.setDataSourceExpression(jde);
			setData(datasetrun);
		}
	}

	private void setConnection(String exTxt) {
		if (datasetrun != null) {
			JRDesignExpression jde = (JRDesignExpression) datasetrun.getConnectionExpression();
			if (jde == null)
				jde = new JRDesignExpression();
			jde.setValueClass(Connection.class);
			jde.setText(exTxt);
			datasetrun.setConnectionExpression(jde);
			datasetrun.setDataSourceExpression(null);
			setData(datasetrun);
		}
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.dsRunExpr.setExpressionContext(expContext);
		this.otherExpr.setExpressionContext(expContext);
	}
}
