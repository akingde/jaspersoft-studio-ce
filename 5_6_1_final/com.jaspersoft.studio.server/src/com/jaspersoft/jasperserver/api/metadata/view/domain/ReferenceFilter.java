/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.api.metadata.view.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * Filter which matches resources that reference other specified resource.
 * 
 * <p>
 * The filter is applied by locating the specified resource, and filtering
 * the results to include resources that reference via a given field the
 * located resource.
 * For instance, one could filter report units that reference a particular
 * data source resource.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReferenceFilter.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 2.0.0
 */
@JasperServerAPI
public class ReferenceFilter implements FilterElement {

	private String property;
	private Class referenceClass;
	private String referredURI;
	
	/**
	 * Creates an empty reference filter.
	 */
	public ReferenceFilter() {
	}
	
	/**
	 * Creates a reference filter.
	 * 
	 * @param property the name of the reference field on which to filter resources
	 * @param referenceClass the type of the referenced resource
	 * @param referredURI the repository path of the referenced resource
	 */
	public ReferenceFilter(String property, Class referenceClass, String referredURI) {
		this.property = property;
		this.referenceClass = referenceClass;
		this.referredURI = referredURI;
	}

	/**
	 * @see Filter#applyReferenceFilter(ReferenceFilter)
	 */
	public void apply(Filter filter) {
		filter.applyReferenceFilter(this);
	}

	/**
	 * Returns the name of the reference field on which to filter resources.
	 * 
	 * @return the name of the reference field
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Sets the reference field on which to filter resources.
	 * 
	 * @param property the name of the reference field
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Returns the repository path of the referenced resource.
	 * 
	 * @return the path of the referenced resource
	 */
	public String getReferredURI() {
		return referredURI;
	}

	/**
	 * Sets the path of the resource to use as reference filter.
	 * 
	 * @param referredURI the path of the referenced resource
	 */
	public void setReferredURI(String referredURI) {
		this.referredURI = referredURI;
	}

	/**
	 * Returns the type of the referenced resource.
	 * 
	 * @return the type of the referenced resource
	 */
	public Class getReferenceClass() {
		return referenceClass;
	}

	/**
	 * Sets the type of the resource to use as reference filter.
	 * 
	 * @param referenceClass the type of the referenced resource
	 */
	public void setReferenceClass(Class referenceClass) {
		this.referenceClass = referenceClass;
	}

	/**
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		return new ReferenceFilter(property, referenceClass, referredURI);
	}
}
