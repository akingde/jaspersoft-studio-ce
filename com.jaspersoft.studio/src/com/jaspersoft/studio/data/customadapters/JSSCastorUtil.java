/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.VersionComparator;
import net.sf.jasperreports.engine.xml.JRXmlBaseWriter;
import net.sf.jasperreports.util.CastorMapping;
import net.sf.jasperreports.util.CastorUtil;


/**
 * Refactor of the JR {@link CastorUtil}, the main difference is this doesn't use the {@link ClassLoader} of the current
 * thread to resolve the mappings, but istead the one of the passed {@link JasperReportsConfiguration}. Doing this 
 * allow to serialize and deserialize data adapter that are imported as jr in the project folder of a report
 */
public class JSSCastorUtil
{
	private static final Log log = LogFactory.getLog(JSSCastorUtil.class);
	
	public static final String EXCEPTION_MESSAGE_KEY_MAPPINGS_LOADING_ERROR = "util.castor.mappings.loading.error";
	
	/**
	 * 
	 */
	private static final String CASTOR_READ_XML_CONTEXT_KEY = "net.sf.jasperreports.castor.read.xml.context";
	private static final String CASTOR_WRITE_XML_CONTEXT_KEY = "net.sf.jasperreports.castor.write.xml.context";
	
	private JasperReportsConfiguration jasperReportsContext;
	private VersionComparator versionComparator;


	/**
	 *
	 */
	private JSSCastorUtil(JasperReportsConfiguration jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
		this.versionComparator = new VersionComparator();
	}
	
	public static void clearCache(JasperReportsConfiguration jConfig) {
		jConfig.removeValue(CASTOR_READ_XML_CONTEXT_KEY);
		jConfig.removeValue(CASTOR_WRITE_XML_CONTEXT_KEY);
	}
	
	
	/**
	 *
	 */
	public static JSSCastorUtil getInstance(JasperReportsConfiguration jasperReportsContext)
	{
		return new JSSCastorUtil(jasperReportsContext);
	}
	
	private XMLContext getReadXmlContext()
	{
		return getXmlContext(CASTOR_READ_XML_CONTEXT_KEY, null);//always reading with the last version mappings
	}
	
	private XMLContext getWriteXmlContext()
	{
		String targetVersion = JRPropertiesUtil.getInstance(jasperReportsContext).getProperty(
				JRXmlBaseWriter.PROPERTY_REPORT_VERSION);
		if (log.isDebugEnabled())
		{
			log.debug("using write mappings for version " + targetVersion);
		}
		
		return getXmlContext(CASTOR_WRITE_XML_CONTEXT_KEY, targetVersion);
	}
	
	/**
	 *
	 */
	private XMLContext getXmlContext(String contextCacheKey, String version)
	{
		XMLContext xmlContext = (XMLContext)jasperReportsContext.getOwnValue(contextCacheKey);
		if (xmlContext == null){
			xmlContext = new XMLContext();
			xmlContext.setClassLoader(jasperReportsContext.getClassLoader());
			Mapping mapping = new Mapping(jasperReportsContext.getClassLoader());
			List<CastorMapping> castorMappings = getMappings(version);
			for (CastorMapping castorMapping : castorMappings)
			{
				loadMapping(mapping, castorMapping.getPath());
			}
			
			try
			{		
				xmlContext.addMapping(mapping);
			}
			catch (MappingException e)
			{
				throw 
					new JRRuntimeException(
						EXCEPTION_MESSAGE_KEY_MAPPINGS_LOADING_ERROR,
						(Object[])null,
						e);
			}
			
			jasperReportsContext.setValue(contextCacheKey, xmlContext);
		}
		return xmlContext;
	}


	protected List<CastorMapping> getMappings(String version)
	{
		List<CastorMapping> castorMappings = jasperReportsContext.getExtensions(CastorMapping.class);
		Map<String, CastorMapping> keyMappings = new HashMap<String, CastorMapping>();
		for (CastorMapping mapping : castorMappings)
		{
			String key = mapping.getKey();
			if (key == null)
			{
				continue;
			}
			
			if (!isEligversionible(mapping, version))
			{
				continue;
			}
			
			CastorMapping existingMapping = keyMappings.get(key);
			if (existingMapping == null || newerThan(mapping, existingMapping))
			{
				keyMappings.put(key, mapping);
			}
		}
		
		List<CastorMapping> activeMappings = new ArrayList<CastorMapping>(castorMappings.size());
		for (CastorMapping mapping : castorMappings)
		{
			String key = mapping.getKey();
			if (key == null // mappings with no keys are always considered active
					// checking if it's the most recent eligible mapping
					|| keyMappings.get(key).equals(mapping))
			{
				activeMappings.add(mapping);
			}
		}
		return activeMappings;
	}

	protected boolean isEligversionible(CastorMapping castorMapping, String targetVersion)
	{
		String mappingVersion = getVersion(castorMapping);
		return versionComparator.compare(targetVersion, mappingVersion) >= 0;
	}
	
	private boolean newerThan(CastorMapping mapping, CastorMapping existingMapping)
	{
		String version = getVersion(mapping);
		String existingVersion = getVersion(existingMapping);
		return versionComparator.compare(version, existingVersion) > 0;
	}

	protected String getVersion(CastorMapping castorMapping)
	{
		String mappingVersion = castorMapping.getVersion();
		if (mappingVersion == null)
		{
			// if the mapping does not specify a version we consider it the initial mapping
			// using a min version to avoid null checks
			mappingVersion = VersionComparator.LOWEST_VERSION;
		}
		return mappingVersion;
	}
	
	/**
	 *
	 */
	private void loadMapping(Mapping mapping, String mappingFile)
	{
		try
		{
			byte[] mappingFileData = JRLoader.loadBytesFromResource(mappingFile, jasperReportsContext.getClassLoader());
			InputSource mappingSource = new InputSource(new ByteArrayInputStream(mappingFileData));

			mapping.loadMapping(mappingSource);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

	/**
	 * 
	 */
	public Object read(InputStream is)
	{
		try
		{
			Unmarshaller unmarshaller = getReadXmlContext().createUnmarshaller();//FIXME initialization is not thread safe
			unmarshaller.setClassLoader(jasperReportsContext.getClassLoader());
			unmarshaller.setWhitespacePreserve(true);
			Object object = unmarshaller.unmarshal(new InputSource(is));
			return object;
		}
		catch (MarshalException e)
		{
			
			throw new JRRuntimeException(e);
		}
		catch (ValidationException e)
		{
			throw new JRRuntimeException(e);
		}
	}

	public String writeToString(Object object)
	{
		StringWriter writer = new StringWriter();
		write(object, writer);
		return writer.toString();
	}
	
	public void writeToFile(Object object, String filename)
	{
		OutputStream output = null;
		boolean closed = false;
		try
		{
			output = new BufferedOutputStream(new FileOutputStream(filename));
			write(object, output);
			output.close();
			closed = true;
		}
		catch (FileNotFoundException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (IOException e)
		{
			throw new JRRuntimeException(e);
		}
		finally
		{
			if (output != null && !closed)
			{
				try
				{
					output.close();
				}
				catch (IOException e)
				{
					//NOP
				}
			}
		}
	}
	
	public void write(Object object, OutputStream output)
	{
		try
		{
			Writer writer = new OutputStreamWriter(output, "UTF-8");//hardcoding utf8 instead of the default encoding
			write(object, writer);
		} 
		catch (UnsupportedEncodingException e)
		{
			// should not happen
			throw new JRRuntimeException(e);
		}
	}
	
	public void write(Object object, Writer writer)
	{
		Marshaller marshaller = getWriteXmlContext().createMarshaller();
		try
		{
			marshaller.setWriter(writer);
			marshaller.setMarshalAsDocument(false);
			marshaller.marshal(object);
		}
		catch (IOException e)
		{
			throw new JRRuntimeException(e);
		} 
		catch (MarshalException e)
		{
			throw new JRRuntimeException(e);
		} 
		catch (ValidationException e)
		{
			throw new JRRuntimeException(e);
		}
	}
}
