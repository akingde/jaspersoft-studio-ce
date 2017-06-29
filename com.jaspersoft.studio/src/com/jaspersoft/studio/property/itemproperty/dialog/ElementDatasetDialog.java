/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.jface.dialogs.EditableDatasetBaseComposite;
import com.jaspersoft.studio.model.dataset.ComponentElementDatasetAdapter;
import com.jaspersoft.studio.model.dataset.ComponentElementDatasetRunAdapter;
import com.jaspersoft.studio.model.dataset.IEditableDatasetRun;
import com.jaspersoft.studio.property.dataset.DatasetRunSelectionListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Dialog that allows editing the information associated to a element.
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class ElementDatasetDialog extends TitleAreaDialog implements IExpressionContextSetter {

	private String title;
	private String message;
	private JRDesignElementDataset dataset;
	private JasperReportsConfiguration jConfig;
	protected EditableDatasetBaseComposite compositeDatasetInfo;

	public ElementDatasetDialog(Shell parentShell, String title, String message, JRElementDataset dataset,
			JasperReportsConfiguration jConfig) {
		super(parentShell);
		this.title = title;
		this.message = message;
		this.dataset = (JRDesignElementDataset) dataset;
		if (this.dataset == null)
			this.dataset = new JRDesignElementDataset();
		this.jConfig = jConfig;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.ElementDatasetDialog_0);
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		createElementDatasetArea(area);

		return area;
	}

	protected void createElementDatasetArea(Composite area) {
		preElementDataset(area);

		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout());
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		compositeDatasetInfo = new EditableDatasetBaseComposite(new ComponentElementDatasetAdapter(dataset, jConfig),
				container, SWT.NONE) {
			@Override
			protected IEditableDatasetRun getEditableDatesetRun() {
				return new ComponentElementDatasetRunAdapter(this.getEditableDataset());
			}
		};
		compositeDatasetInfo.addDatasetRunSelectionListener(new DatasetRunSelectionListener() {
			public void selectionChanged() {
				ExpressionContext contextFromDSRun = getExpressionContextFromDSRun();
				compositeDatasetInfo.setExpressionContext(contextFromDSRun);
			}
		});
		compositeDatasetInfo.setExpressionContext(getExpressionContextFromDSRun());
		compositeDatasetInfo.setDefaultExpressionContext(expContext);

		setTitle(this.title);
		setMessage(this.message);
	}

	protected void preElementDataset(Composite parent) {

	}

	private ExpressionContext expContext;

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	private ExpressionContext getExpressionContextFromDSRun() {
		if (dataset != null) {
			JRDesignDataset ds = ModelUtils.getDesignDatasetForDatasetRun(jConfig.getJasperDesign(), dataset.getDatasetRun());
			return new ExpressionContext(ds, jConfig);
		}
		return null;
	}

	public JRElementDataset getDataset() {
		return dataset;
	}
}
