/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

import org.eclipse.jface.fieldassist.ContentProposal;

/**
 * Container for a property proposal, essentially composed from the name of 
 * the property and its id
 * 
 * @author Orlandin Marco
 *
 */
public class PropertyContentProposal extends ContentProposal implements Comparable<PropertyContentProposal>{

	/**
	 * property id
	 */
	private Object propertyId;
	
	private Class<?> sectionType;
	
	/**
	 * Create a property proposal
	 * 
	 * @param content name of the property
	 * @param propertyId id of the property
	 */
	public PropertyContentProposal(String content, Object propertyId, Class<?> parentSectionType) {
		super(content);
		this.propertyId = propertyId;
		this.sectionType = parentSectionType;
	}
	
	/**
	 * return the id of the property
	 *
	 */
	public Object getPropertyId(){
		return propertyId;
	}
	
	public Class<?> getSectionType() {
		return sectionType;
	}

	@Override
	public int compareTo(PropertyContentProposal o) {
		return getContent().compareTo(o.getContent());
	}

}
