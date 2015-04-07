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
package com.jaspersoft.studio.kpi.dialog.pages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;

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
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Page where the series of a KPI can be configured
 * 
 * @author Orlandin Marco
 *
 */
public class SeriesPage extends AbstractKPIConfigurationPage{
	
	/**
	 * Dataset that will return the X and Y values of the series
	 */
	public static final String SERIES_DATASET_NAME = "ValueSeries"; //$NON-NLS-1$
	
	/**
	 * Variable on the series dataset that store the X value
	 */
	public static final String SERIES_X_VARIABLE = "x_value"; //$NON-NLS-1$
	
	/**
	 * Variable on the series dataset that store the Y value
	 */
	public static final String SERIES_Y_VARIABLE = "y_value"; //$NON-NLS-1$
	
	/**
	 * Create a model node for a specific JRDesignDataset. It will have also a report and
	 * root parents. Used to open the dataset and query dialog to edit the dataset
	 * 
	 * @param jConfig a JasperReportsConfiguration, must be not null
	 * @param dataset a dataset, must be not null
	 * @return a not null model containing the dataset
	 */
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
	
	/**
	 * Return the dataset used to store the series variables. If the dataset
	 * is not found inside the report then it is created
	 * 
	 * @return a not null dataset
	 */
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

	@Override
	public String getName() {
		return Messages.SeriesPage_pageName;
	}
	
	@Override
	public String getTitle() {
		return Messages.SeriesPage_pageTitle;
	}
	
	@Override
	protected Composite createComposite(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		
		//Get the series dataset and its context
		final JRDesignDataset seriesDataset = getSeriesDataset();
		ExpressionContext context = getExpressionContext(seriesDataset);
		
		//Create the button to open the dataset and query dialog on the series dataset
		Button queryDialogButton = new Button(c, SWT.PUSH);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		queryDialogButton.setLayoutData(gd);
		queryDialogButton.setText(Messages.SeriesPage_editButton);
		queryDialogButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
				jConfig.setJasperDesign(jd);
				MDataset model = createDatasetModel(jConfig, seriesDataset);
				new DatasetDialog(UIUtils.getShell(), model, jConfig, new CommandStack()).open();
			}
		});
		
		//Label used to have some space between the button and the following expression widgets
		Label paddingLabel = new Label(c, SWT.NONE);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 2;
		gd.heightHint = 10;
		paddingLabel.setLayoutData(gd);
		
		//Widget to define the x expression
		new Label(c,SWT.NONE).setText(Messages.SeriesPage_xLabel);
		final WTextExpression expr_x = new WTextExpression(c, SWT.NONE, 3);
		expr_x.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		JRExpression exp = getVariable(SERIES_X_VARIABLE, seriesDataset).getExpression();
		expr_x.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr_x.setExpressionContext(context);
		expr_x.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr_x.getExpression();
				getVariable(SERIES_X_VARIABLE, seriesDataset).setExpression(exp != null ? (JRExpression)exp.clone() : null);
			}
		});
		
		//Widget to define the Y expression
		new Label(c,SWT.NONE).setText(Messages.SeriesPage_yLabel);
		final WTextExpression expr_y = new WTextExpression(c, SWT.NONE, 3);
		expr_y.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		exp = getVariable(SERIES_Y_VARIABLE, seriesDataset).getExpression();
		expr_y.setExpression(exp != null ? (JRDesignExpression)exp : null);
		expr_y.setExpressionContext(context);
		expr_y.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr_y.getExpression();
				getVariable(SERIES_Y_VARIABLE, seriesDataset).setExpression(exp != null ? (JRExpression)exp.clone() : null);
			}
		});
		return c;
	}
}
