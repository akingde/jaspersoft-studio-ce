package com.jaspersoft.studio.kpi;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.restv2.RestV2ConnectionJersey;

public class KPIUtils {

	

	/**
	 * Convenient method to get the KPI ResourceDescriptor (if exists)
	 * 
	 * @param client
	 * @param string
	 */
	public static ResourceDescriptor getReportUnitKPI(IConnection client, String reportUnitUri) {
		// TODO Auto-generated method stub
		
		String kpiUri = reportUnitUri + "_files/KPI";
		
		ResourceDescriptor kpiResourceDescriptor = new ResourceDescriptor();
		kpiResourceDescriptor.setUriString(kpiUri);
		kpiResourceDescriptor.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
		
		try {
			return client.get(null, kpiResourceDescriptor, null);
		} catch (Exception ex)
		{
		}
		
		return null;
	}
	
	
	/**
	 * Convenient method to delete a KPI inside a report unit
	 * 
	 * @param client
	 * @param string
	 * @return boolean (true if the kpi report unit has been successfully deleted
	 */
	public static boolean deleteReportUnitKPI(IConnection client, String reportUnitUri) {
		// TODO Auto-generated method stub
		
		ResourceDescriptor kpiResourceDescriptor = getReportUnitKPI(client, reportUnitUri);
		
		if (kpiResourceDescriptor != null)
		{
			try {
				client.delete(null, kpiResourceDescriptor);
			} catch (Exception e) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/**
	 * Convenient method to delete a KPI inside a report unit
	 * 
	 * @param client
	 * @param string
	 * @return boolean (true if the kpi report unit has been successfully deleted
	 */
	public static boolean createReportUnitKPI(IConnection client, String reportUnitUri, String file, String dsUri) {

		
		//((RestV2ConnectionJersey)client).getTarget();
		
		
		ResourceDescriptor jrxmlResourceDescriptor = new ResourceDescriptor();
		
		jrxmlResourceDescriptor.setLabel("main_jrxml");
		jrxmlResourceDescriptor.setName("main_jrxml");
		jrxmlResourceDescriptor.setUriString(reportUnitUri + "_files/KPI_files/main_jrxml");
		jrxmlResourceDescriptor.setWsType(ResourceDescriptor.TYPE_JRXML);
		jrxmlResourceDescriptor.setIsNew(true);
		jrxmlResourceDescriptor.setIsReference(false);
		jrxmlResourceDescriptor.setHasData(true);
		jrxmlResourceDescriptor.setMainReport(true);
		
		try {
			jrxmlResourceDescriptor.setData(Base64
					.encodeBase64(net.sf.jasperreports.eclipse.util.FileUtils
							.getBytes(new File(file))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		
		ResourceDescriptor kpiReportUnit = new ResourceDescriptor();
		
		kpiReportUnit.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
		kpiReportUnit.setLabel("KPI");
		kpiReportUnit.setName("KPI");
		kpiReportUnit.setUriString(reportUnitUri + "_files/KPI");
		kpiReportUnit.getChildren().add( jrxmlResourceDescriptor );
		kpiReportUnit.setIsNew(true);
		
		if (dsUri != null && dsUri.length() > 0)
		{
			// Get the resource descriptor for it...
			ResourceDescriptor dsRd = new ResourceDescriptor();
			dsRd.setUriString(dsUri);
			
			try {
				dsRd = client.get(new NullProgressMonitor(), dsRd, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
//			ResourceDescriptor referenceDsRd = new ResourceDescriptor();		
//			referenceDsRd.setName("datasource");
//			referenceDsRd.setLabel("datasource");
//			referenceDsRd.setUriString(dsRd.getUriString());
//			referenceDsRd.setWsType(dsRd.getWsType());
////			referenceDsRd.setReferenceType(dsRd.getWsType());
////			referenceDsRd.setReferenceUri(dsRd.getUriString());
			dsRd.setIsNew(true);
			
			kpiReportUnit.getChildren().add( dsRd );
		}
		
		
		try {
			client.addOrModifyResource(new NullProgressMonitor(), kpiReportUnit, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
}
