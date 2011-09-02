package com.jaspersoft.studio.property.dataset;

import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class DatasetRunWidget {
	private JRDesignDatasetRun datasetrun;

	public DatasetRunWidget(Composite parent) {
		createControl(parent);
	}

	private boolean isRefreshing = false;

	public void setData(JRDesignDatasetRun datasetrun) {
		isRefreshing = true;
		this.datasetrun = datasetrun;
		if (datasetrun != null) {
			btnConnect.setSelection(false);
			btnDataSource.setSelection(false);
			btnNoConnection.setSelection(false);
			enableConnection(false);
			enableDatasource(false);

			if (datasetrun.getConnectionExpression() == null && datasetrun.getDataSourceExpression() == null) {
				btnNoConnection.setSelection(true);
			} else if (datasetrun.getConnectionExpression() != null) {
				enableConnection(true);
			} else if (datasetrun.getDataSourceExpression() != null) {
				enableDatasource(true);
			}
		}
		isRefreshing = false;
	}

	private void enableDatasource(boolean enable) {
		btnDataSource.setSelection(enable);
		dsExpr.setText(datasetrun.getDataSourceExpression() != null ? datasetrun.getDataSourceExpression().getText() : "");
		dsExpr.setEnabled(enable);
		dsExprDialog.setEnabled(enable);
	}

	private void enableConnection(boolean enable) {
		btnConnect.setSelection(enable);
		cnExpr.setText(datasetrun.getConnectionExpression() != null ? datasetrun.getConnectionExpression().getText() : "");
		cnExpr.setEnabled(enable);
		connExprDialog.setEnabled(enable);
	}

	public void setEnabled(boolean enabled) {
		if (enabled)
			layout.topControl = dsRunComposite;
		else
			layout.topControl = emptyComposite;
		control.layout();
	}

	public Control getControl() {
		return control;
	}

	private Composite control;
	private StackLayout layout;
	private Composite emptyComposite;
	private Composite dsRunComposite;

	private Button btnConnect;
	private Button btnDataSource;
	private Button btnNoConnection;
	private Text cnExpr;
	private Text dsExpr;
	private Button connExprDialog;
	private Button dsExprDialog;

	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		control = cmp;
		layout = new StackLayout();
		cmp.setLayout(layout);

		emptyComposite = new Composite(cmp, SWT.NONE);

		dsRunComposite = new Composite(cmp, SWT.NONE);
		dsRunComposite.setLayout(new GridLayout(3, false));

		// -----------------------------------------------------------------------

		btnNoConnection = new Button(dsRunComposite, SWT.RADIO);
		btnNoConnection.setText(Messages.WizardConnectionPage_noconnection_text);
		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		btnNoConnection.setLayoutData(gd);
		btnNoConnection.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (btnNoConnection.getSelection()) {
					setNoConnection();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		btnConnect = new Button(dsRunComposite, SWT.RADIO);
		btnConnect.setText(Messages.WizardConnectionPage_connection_text);

		cnExpr = new Text(dsRunComposite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 50;
		cnExpr.setLayoutData(gd);
		cnExpr.setEnabled(false);

		connExprDialog = new Button(dsRunComposite, SWT.PUSH);
		connExprDialog.setText("..."); //$NON-NLS-1$
		connExprDialog.setEnabled(false);
		connExprDialog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();

				JRDesignExpression mexp = (JRDesignExpression) datasetrun.getConnectionExpression();

				wizard.setValue(mexp);
				WizardDialog dialog = new WizardDialog(connExprDialog.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mexp = wizard.getValue();
					datasetrun.setConnectionExpression(mexp);
					datasetrun.setDataSourceExpression(null);

					cnExpr.setText(mexp.getText());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		cnExpr.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (!isRefreshing) {
					datasetrun.setConnectionExpression(ExprUtil.setValues(new JRDesignExpression(), cnExpr.getText()));
				}
			}
		});

		btnConnect.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				boolean selection = btnConnect.getSelection();
				cnExpr.setEnabled(selection);
				connExprDialog.setEnabled(selection);

				datasetrun.setConnectionExpression(new JRDesignExpression());
				datasetrun.setDataSourceExpression(null);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		// ---------------------------------------------------------------------------

		btnDataSource = new Button(dsRunComposite, SWT.RADIO);
		btnDataSource.setText(Messages.WizardConnectionPage_datasource_text);

		dsExpr = new Text(dsRunComposite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 50;
		dsExpr.setLayoutData(gd);
		dsExpr.setEnabled(false);

		dsExprDialog = new Button(dsRunComposite, SWT.PUSH);
		dsExprDialog.setText("..."); //$NON-NLS-1$
		dsExprDialog.setEnabled(false);

		btnDataSource.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				boolean selection = btnDataSource.getSelection();
				dsExpr.setEnabled(selection);
				dsExprDialog.setEnabled(selection);
				datasetrun.setConnectionExpression(null);
				datasetrun.setDataSourceExpression(new JRDesignExpression());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		dsExprDialog.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();

				JRDesignExpression mexp = (JRDesignExpression) datasetrun.getDataSourceExpression();

				wizard.setValue(mexp);
				WizardDialog dialog = new WizardDialog(dsExprDialog.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mexp = wizard.getValue();
					datasetrun.setDataSourceExpression(mexp);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		dsExpr.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (!isRefreshing)
					datasetrun.setDataSourceExpression(ExprUtil.setValues(new JRDesignExpression(), cnExpr.getText()));
			}
		});

		// ------------------------------------------------------------------------------

		Button btnMainReport = new Button(dsRunComposite, SWT.PUSH);
		btnMainReport.setText(Messages.WizardConnectionPage_mainreport_text);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		btnMainReport.setLayoutData(gd);
		btnMainReport.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setMainReportConnection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Button btnEmptyConn = new Button(dsRunComposite, SWT.PUSH);
		btnEmptyConn.setText(Messages.WizardConnectionPage_empty_connection_text);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		btnEmptyConn.setLayoutData(gd);
		btnEmptyConn.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setEmptyDataSource();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		layout.topControl = dsRunComposite;
	}

	private void setNoConnection() {
		if (datasetrun != null) {
			datasetrun.setConnectionExpression(null);
			datasetrun.setDataSourceExpression(null);
			setData(datasetrun);
		}
	}

	private void setEmptyDataSource() {
		if (datasetrun != null) {
			JRDesignExpression jde = (JRDesignExpression) datasetrun.getDataSourceExpression();
			if (jde == null)
				jde = new JRDesignExpression();
			jde.setText("new net.sf.jasperreports.engine.JREmptyDataSource()"); //$NON-NLS-1$

			datasetrun.setConnectionExpression(null);
			datasetrun.setDataSourceExpression(jde);
			setData(datasetrun);
		}
	}

	private void setMainReportConnection() {
		if (datasetrun != null) {
			JRDesignExpression jde = (JRDesignExpression) datasetrun.getConnectionExpression();
			if (jde == null)
				jde = new JRDesignExpression();
			jde.setText("$P{REPORT_CONNECTION}"); //$NON-NLS-1$

			datasetrun.setConnectionExpression(jde);
			datasetrun.setDataSourceExpression(null);
			setData(datasetrun);
		}
	}
}
