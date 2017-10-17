/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import net.sf.jasperreports.engine.JRElementGroup;

/**
 * Interface that represent a container node that can provide a JRElementGroup 
 * where the children are. Note that not all the container are JRElementGroup, the
 * frame for example are container but the don't have an ElemElement
 */
public interface IGroupElement {
	
	/**
	 * Return the element group of the current element
	 * 
	 * @return the element group
	 */
	public JRElementGroup getJRElementGroup();
}
