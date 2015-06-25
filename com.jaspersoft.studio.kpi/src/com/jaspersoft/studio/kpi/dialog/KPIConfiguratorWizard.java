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
package com.jaspersoft.studio.kpi.dialog;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.kpi.KPIUtils;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.statistics.UsageStatisticsIDs;

/**
 * Wizard used to configure the KPI JasperDesign. It consists of a single
 * page that show inside all the configurator pages to access various sections of the
 * KPI
 * 
 * @author Orlandin Marco
 *
 */
public class KPIConfiguratorWizard extends Wizard {

	/**
	 * Page where the KPI is configured
	 */
	private KPIConfiguratorPage kpiDefinitionPanel = null;
	
	public KPIConfiguratorWizard(IConnection client, ResourceDescriptor parentReportUnit, ResourceDescriptor kpiReportUnit, MServerProfile serverProfile)
	{
		kpiDefinitionPanel = new KPIConfiguratorPage();
		kpiDefinitionPanel.setWSClient(client);
		kpiDefinitionPanel.setParentReportUnit(parentReportUnit);
		kpiDefinitionPanel.setKpiReportUnit(kpiReportUnit);
		kpiDefinitionPanel.setMServerProfile(serverProfile);
		kpiDefinitionPanel.loadJasperDesign();
	}
	
	/**
	 * When finish is click it upload the KPI on the server
	 */
	@Override
	public boolean performFinish() {
		try {
			final String jrxmlFile = kpiDefinitionPanel.getJrxmlFile();
			final String datasetUri = kpiDefinitionPanel.getDatasourceUri();
			final ResourceDescriptor parentReportUnit = kpiDefinitionPanel.getParentReportUnit();
			final IConnection client = kpiDefinitionPanel.getWSClient();	
			
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Create/Update KPI",
							IProgressMonitor.UNKNOWN);
					try {
						
						//Check if it is a new KPI or an editing
						boolean newKPI = KPIUtils.getReportUnitKPI(client,  parentReportUnit.getUriString()) == null;
						
						// 1. If exists, remove the old KPI...
						
						if (!KPIUtils.deleteReportUnitKPI(client, parentReportUnit.getUriString()))
						{
							UIUtils.showInformation(":-( I'm unable to delete the existing KPI.");
							throw new InvocationTargetException(new Exception());
						}
						
						
						// 2. Check that the file exist
						if ( !(new File(jrxmlFile)).exists() )
						{
							UIUtils.showInformation("The file does not exist");
						}
						
						// 3. Ready to deploy the KPI
						if (!KPIUtils.createReportUnitKPI(client, parentReportUnit.getUriString(), jrxmlFile, datasetUri))
						{
							UIUtils.showInformation(":-( I'm not able to publish this KPI...an error has occurred while publishing it.");
							throw new InvocationTargetException(new Exception());
						} else if (newKPI) {
							//if the kpi was deployed correctly and was a new kpi then log the deployment
							JaspersoftStudioPlugin.getInstance().getUsageManager().audit(UsageStatisticsIDs.SERVER_KPI_CREATION, UsageStatisticsIDs.CATEGORY_SERVER);
						}
						
					} finally {
						monitor.done();
					}
				}

				
			});
		} catch (InvocationTargetException e) {
			return false;
		} catch (InterruptedException e) {
			UIUtils.showError(e);
			return false;
		} 
		
		return true;
		
	}
	
	@Override
	public void addPages() {
		super.addPages();
		addPage(kpiDefinitionPanel);
	}
};
