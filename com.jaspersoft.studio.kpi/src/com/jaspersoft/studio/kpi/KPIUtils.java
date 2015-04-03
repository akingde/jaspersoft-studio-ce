package com.jaspersoft.studio.kpi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.protocol.IConnection;

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
				
				try {
					removeKPICacheKey(client, kpiResourceDescriptor.getUriString());
				} catch (Exception ex)
				{
					//Cache update fail should not prevent the report of a successfully completed operation.
				}
				
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
			
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put(kpiReportUnit.getUriString(), reportUnitUri);
				KPIUtils.updateKPICache(client, map, false);
			} catch (Exception ex)
			{
				// Refresh of the cache is not really necessary...
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	
	/**
	 * Remove a single cache entry
	 *
	 * @param client
	 * @param reportUnitUris - List of report unit uris to add. These are all KPI report units!!!
	 * @param clearCache - if true, all the existing entries will be removed.
	 * @return boolean - true if the urls have been successfully stored
	 */
	public static void removeKPICacheKey(IConnection client, String kpiUriToRemove) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(kpiUriToRemove, "");
		updateKPICache(client, map, false, true);
	}
	
	
	/**
	 * Add to the KPI cache a list of report units
	 *
	 * @param client
	 * @param reportUnitUris - List of report unit uris to add. These are all KPI report units!!!
	 * @param clearCache - if true, all the existing entries will be removed.
	 * @return boolean - true if the urls have been successfully stored
	 */
	public static void updateKPICache(IConnection client, Map<String, String> reportUnitUris, boolean clearCache) throws Exception {
		updateKPICache(client, reportUnitUris, clearCache, false);
	}
	
	
	/**
	 * Handle the add/remove/clear cache entries
	 *
	 * @param client
	 * @param reportUnitUris - List of report unit uris to add. These are all KPI report units!!!
	 * @param clearCache - if true, all the existing entries will be removed.
	 * @return boolean - true if the urls have been successfully stored
	 */
	private static void updateKPICache(IConnection client, Map<String, String> reportUnitUris, boolean clearCache, boolean removeKey) throws Exception {
		
		// Check if the kpicache.properties file exists..
		ResourceDescriptor kpiCacheFile = new ResourceDescriptor();
		kpiCacheFile.setUriString("/kpicache.properties");
		kpiCacheFile.setLabel("kpicache.properties");
		kpiCacheFile.setName("kpicache.properties");
		kpiCacheFile.setWsType(ResourceDescriptor.TYPE_RESOURCE_BUNDLE);
		
		File file;
		file = File.createTempFile("kpicache", ".properties");
		
		try {
		
				Properties properties = new Properties();
				
				if (!clearCache || removeKey)
				{
					try {
						
						kpiCacheFile = client.get(new NullProgressMonitor(), kpiCacheFile, file);
						if (file.exists() && file.length() > 0)
						{
							properties.load(new FileInputStream(file));
						}
					} catch (Exception ex)
					{
						// resource not found...
						kpiCacheFile.setIsNew(true);
					}
				}
				
				if (removeKey)
				{
					properties.remove(removeKey);
				}
				else
				{
					Set<String> keys = reportUnitUris.keySet();
					
					
					for (String uri : keys)
					{
						// Just put an empty value. Uris don't have "=" character, so it should be simple to strip
						// on client side...
						properties.setProperty(uri, reportUnitUris.get(uri));
					}
				}
				
				
				properties.store(new FileOutputStream(file),"");
				
				kpiCacheFile.setHasData(true);
				kpiCacheFile.setData(Base64
							.encodeBase64(net.sf.jasperreports.eclipse.util.FileUtils
									.getBytes(file)));
				
				// Save the cache back...
				client.addOrModifyResource(new NullProgressMonitor(), kpiCacheFile, file);
		} finally {
			
			// Whatever happens, delete the temporary file with the cache.
			if (file != null && file.exists())
			{
				file.delete();
			}
		}
			
	}
}
