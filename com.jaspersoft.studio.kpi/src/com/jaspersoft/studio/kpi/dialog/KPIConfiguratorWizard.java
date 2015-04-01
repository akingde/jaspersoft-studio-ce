package com.jaspersoft.studio.kpi.dialog;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.kpi.KPIUtils;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;

public class KPIConfiguratorWizard extends Wizard {

	KPIConfiguratorPage kpiDefinitionPanel = null;
	
	public KPIConfiguratorWizard(IConnection client, ResourceDescriptor parentReportUnit, ResourceDescriptor kpiReportUnit, MServerProfile serverProfile)
	{
		kpiDefinitionPanel = new KPIConfiguratorPage();
		kpiDefinitionPanel.setWSClient(client);
		kpiDefinitionPanel.setParentReportUnit(parentReportUnit);
		kpiDefinitionPanel.setKpiReportUnit(kpiReportUnit);
		kpiDefinitionPanel.setMServerProfile(serverProfile);
		kpiDefinitionPanel.loadJasperDesign();
	}
	
	
	@Override
	public boolean performFinish() {
		try {
			
			final String jrxmlFile = kpiDefinitionPanel.getJrxmlFile();
			final String datasetUri = kpiDefinitionPanel.getDatasourceUri();
			final ResourceDescriptor parentReportUnit = kpiDefinitionPanel.getParentReportUnit();
			final IConnection client = kpiDefinitionPanel.getWSClient();
			final ResourceDescriptor kpiReportUnit = kpiDefinitionPanel.getKpiReportUnit();
			
			
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Create/Update KPI",
							IProgressMonitor.UNKNOWN);
					try {
						
						// 1. If exists, remove the old KPI...
						
						if (!KPIUtils.deleteReportUnitKPI(client, parentReportUnit.getUriString()))
						{
							UIUtils.showInformation(":-( I'm unable to delete the existing KPI.");
							throw new InvocationTargetException(new Exception());
						}
						
						
						// 2. Deploy the new KPI...
						// Verifichiamo che il file sia buono...
						if ( !(new File(jrxmlFile)).exists() )
						{
							UIUtils.showInformation("The file does not exist");
						}
						
						// 3. Ready to deploy the KPI
						if (!KPIUtils.createReportUnitKPI(client, parentReportUnit.getUriString(), jrxmlFile, datasetUri))
						{
							UIUtils.showInformation(":-( I'm not able to publish this KPI...an error has occurred while publishing it.");
							throw new InvocationTargetException(new Exception());
						}
						
						
					} finally {
						monitor.done();
					}
				}

				
			});
		} catch (InvocationTargetException e) {
			// Default exception... we will show an appropriate message in this case
			// directly from the job...
			//UIUtils.showError(e.getCause());
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
