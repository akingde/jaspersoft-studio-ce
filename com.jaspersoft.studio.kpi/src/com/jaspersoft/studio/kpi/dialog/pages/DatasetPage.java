package com.jaspersoft.studio.kpi.dialog.pages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.dialog.KPIConfiguratorPage;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;
import com.jaspersoft.studio.server.wizard.find.FindResourceWizard;
import com.jaspersoft.studio.server.wizard.find.FindWizardDialog;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class DatasetPage extends AbstractKPIConfigurationPage{
	
	private KPIConfiguratorPage parentPage;
	
	public DatasetPage(KPIConfiguratorPage parentPage){
		this.parentPage = parentPage;
	}
	
	@Override
	public String getName() {
		return Messages.DatasetPage_pageName;
	}

	@Override
	protected Composite createComposite(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(3, false));
		
		Label dataSourceUriLabel = new Label(c, SWT.NONE);;
		dataSourceUriLabel.setText(Messages.DatasetPage_datasourceLabel);
		
		final Text dataSourceUri = new Text(c, SWT.BORDER);
		GridData gd = new GridData( GridData.FILL_HORIZONTAL );
		dataSourceUri.setLayoutData(gd);
		String uri = parentPage.getDatasourceUri();
		dataSourceUri.setText(uri != null ? uri : ""); //$NON-NLS-1$
		dataSourceUri.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {	
				parentPage.setDatasourceUri(((Text)e.widget).getText());
			}
		});
		
		Button dataSourceUriPickerButton = new Button(c, SWT.PUSH);
		dataSourceUriPickerButton.setText(Messages.DatasetPage_exploreLabel);
		dataSourceUriPickerButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				
				FindResourceWizard frw = new FindResourceWizard(parentPage.getMServerProfile());
				//Set<String> types  = DatasourcesAllFilter.getTypes();
				frw.setFilterTypes(WsTypes.INST().getDatasourcesArray() , null);
				
				FindWizardDialog dialog = new FindWizardDialog(UIUtils.getShell(), frw);
				
				if (dialog.open() == Dialog.OK)
				{
					ResourceDescriptor rd = frw.getValue();
					dataSourceUri.setText( rd.getUriString() );
				}
			}
		});
		
		
		Button queryDialogButton = new Button(c, SWT.PUSH);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.horizontalSpan = 3;
		queryDialogButton.setLayoutData(gd);
		queryDialogButton.setText(Messages.DatasetPage_editQueryButton);
		queryDialogButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
				jConfig.setJasperDesign(jd);
				MDataset model = createDatasetModel(jConfig);
				new DatasetDialog(UIUtils.getShell(), model, jConfig, new CommandStack()).open();
			}
		});
		
		return c;
	}
	
	private MDataset createDatasetModel(JasperReportsConfiguration jConfig){
		MRoot root = new MRoot(null, jd);
		MReport report = new MReport(root, jConfig);
		report.setValue(jd);
		MDataset model = new MDataset(report, jd.getMainDesignDataset());
		model.setJasperConfiguration(jConfig);
		ReportFactory.createDataset(model, jd.getMainDesignDataset(), false);
		report.addChild(model);
		return model;
	}
	
	@Override
	public String getTitle() {
		return Messages.DatasetPage_pageTitle;
	}
}
