/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

/**
 * Represents a section descriptor provider for tabbed property sections.
 * 
 * @author Anthony Hunter
 */
public interface ISectionDescriptorProvider {

	/**
	 * Returns all section descriptors for the contributor.
	 * @return all section descriptors.
	 */
	public ISectionDescriptor[] getSectionDescriptors();
}
