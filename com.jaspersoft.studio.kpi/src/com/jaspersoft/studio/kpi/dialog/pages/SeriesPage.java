package com.jaspersoft.studio.kpi.dialog.pages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignVariable;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SeriesPage extends AbstractKPIConfigurationPage{
	
	public static final String SERIES_DATASET_NAME = "ValueSeries";
	
	public static final String SERIES_X_VARIABLE = "x_value";
	
	public static final String SERIES_Y_VARIABLE = "y_value";
	
	@Override
	public String getName() {
		return "Value Series";
	}

	@Override
	protected Composite createComposite(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		
		Button queryDialogButton = new Button(c, SWT.PUSH);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		queryDialogButton.setLayoutData(gd);
		queryDialogButton.setText("Edit Query");
		queryDialogButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
				jConfig.setJasperDesign(jd);
				MDataset model = createDatasetModel(jConfig, getSeriesDataset());
				new DatasetDialog(UIUtils.getShell(), model, jConfig, new CommandStack()).open();
			}
		});
		
		Label paddingLabel = new Label(c, SWT.NONE);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		gd.heightHint = 10;
		paddingLabel.setLayoutData(gd);
		
		ExpressionContext context = getExpressionContext();
		
		new Label(c,SWT.NONE).setText("X Value");
		final WTextExpression expr_x = new WTextExpression(c, SWT.NONE, 3);
		expr_x.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRExpression exp = getVariable(SERIES_X_VARIABLE).getExpression();
		expr_x.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr_x.setExpressionContext(context);
		expr_x.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr_x.getExpression();
				getVariable(SERIES_X_VARIABLE).setExpression(exp != null ? (JRExpression)exp.clone() : null);
			}
		});
		
		new Label(c,SWT.NONE).setText("Y Value (Numeric)");
		final WTextExpression expr_y = new WTextExpression(c, SWT.NONE, 3);
		expr_y.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		exp = getVariable(SERIES_Y_VARIABLE).getExpression();
		expr_y.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr_y.setExpressionContext(context);
		expr_y.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr_y.getExpression();
				getVariable(SERIES_Y_VARIABLE).setExpression(exp != null ? (JRExpression)exp.clone() : null);
			}
		});
		

		
		return c;
	}
	
	private MDataset createDatasetModel(JasperReportsConfiguration jConfig, JRDesignDataset dataset){
		MRoot root = new MRoot(null, jd);
		MReport report = new MReport(root, jConfig);
		report.setValue(jd);
		MDataset model = new MDataset(report, dataset);
		model.setJasperConfiguration(jConfig);
		report.addChild(model);
		ReportFactory.createDataset(model, dataset, false);
		return model;
	}
	
	private JRDesignDataset getSeriesDataset(){
		JRDataset seriesDataset = jd.getDatasetMap().get(SERIES_DATASET_NAME);
		if (seriesDataset == null){
			JRDesignDataset newDataset = new JRDesignDataset(false);
			newDataset.setName(SERIES_DATASET_NAME);
			try {
				jd.addDataset(newDataset);
			} catch (JRException e) {
				e.printStackTrace();
			}
			return newDataset;
		}
		return (JRDesignDataset)seriesDataset;	
	}
	
	private ExpressionContext getExpressionContext() {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(getSeriesDataset(), jConfig);
	}
	
	private JRDesignVariable getVariable(String variableName){
		JRVariable variable = jd.getVariablesMap().get(variableName);
		if (variable == null){
			JRDesignVariable newVariable = new JRDesignVariable();
			newVariable.setName(variableName);
			try {
				jd.addVariable(newVariable);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newVariable;
		}
		return ((JRDesignVariable)variable);
	}

}
