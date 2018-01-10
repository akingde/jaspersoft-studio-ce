/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.adapter;

import org.w3c.dom.Document;

import com.jaspersoft.studio.data.DataAdapterDescriptor;

/**
 * 
 * This class define how should appear a converter that take an ireport 
 * data adapter definition, as an xml, and convert it into a JSS data 
 * adapter
 * 
 * 
 * @author Orlandin Marco
 */
public interface IDataAdapterCreator {

	/**
	 * Take the XML definition of an ireport data adapter 
	 * and return a JSS data adapter descriptor
	 */
	public DataAdapterDescriptor buildFromXML(Document docXML);
	
	/**
	 * Return the unique id of this data adapter converter, must be the same 
	 * value of the connectionClass used inside an iReport data adapter definition.
	 * This bind the definition of a particular type of the data adapter to the coverter
	 * that can handle that type
	 */
	public String getID();
}
