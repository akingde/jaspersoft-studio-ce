/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.xmla;


/**
 * Interface for a node of the xmla metadata
 * 
 * @author Orlandin Marco
 *
 */
public interface DataSourceTreeElement {
	/**
	 * Return the children of the node
	 * 
	 * @return an array of children, can be null if there 
	 * aren't children
	 */
	public DataSourceTreeElement[] getChildren();

	/**
	 * Return the textual representation of the node
	 * 
	 * @return a not null string
	 */
	public String toString();

	/**
	 * Return the datasource name from where the node is (also indirectly, in 
	 * case of a children) was generated 
	 * 
	 * @return a not null datasource name
	 */
	public String getDataSourceInfo();
}
