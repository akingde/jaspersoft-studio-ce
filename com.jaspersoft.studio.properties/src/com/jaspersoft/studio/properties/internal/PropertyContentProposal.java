/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
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
	
	/**
	 * Create a property proposal
	 * 
	 * @param content name of the property
	 * @param propertyId id of the property
	 */
	public PropertyContentProposal(String content, Object propertyId) {
		super(content);
		this.propertyId = propertyId;
	}
	
	/**
	 * return the id of the property
	 *
	 */
	public Object getPropertyId(){
		return propertyId;
	}

	@Override
	public int compareTo(PropertyContentProposal o) {
		return getContent().compareTo(o.getContent());
	}

}
