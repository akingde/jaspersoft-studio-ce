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

package com.jaspersoft.jasperserver.api.metadata.common.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * ResourceReference is the class which references any JasperServer resource
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ResourceReference.java 35612 2013-08-16 23:20:54Z schubar $
 */
@JasperServerAPI
@XmlRootElement(name = "resourceReference")
public class ResourceReference implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean local;
	private String referenceURI;
	private ResourceLookup referenceLookup;
	private Resource localResource;

	/**
	 * Creates a new ResourceReference which references the specified URI
	 * 
	 * @param referenceURI
	 *          reference URI
	 * @return resource reference
	 */
	public ResourceReference(String referenceURI) {
		setReference(referenceURI);
	}

	/**
	 * Creates a new ResourceReference which references the specified
	 * ResourceLookup
	 * 
	 * @param referenceLookup
	 *          reference to ResourceLookup
	 * @return resource reference
	 */
	public ResourceReference(ResourceLookup referenceLookup) {
		setReference(referenceLookup);
	}

	/**
	 * Creates a new ResourceReference which contains specified resource
	 * 
	 * @param localResource
	 *          resource
	 * @return resource reference
	 */
	public ResourceReference(Resource localResource) {
		setLocalResource(localResource);
	}

	/**
	 * Shows if ResourceReference contains its own local resource
	 * 
	 * @return <code>true</code> if the resource is local
	 */
	public boolean isLocal() {
		return local;
	}

	/**
	 * Returns the local resource
	 * 
	 * @return resource
	 */
	public Resource getLocalResource() {
		return localResource;
	}

	/**
	 * Returns resource reference URI string
	 * 
	 * @return URI
	 */
	public String getReferenceURI() {
		return referenceURI;
	}

	/**
	 * Returns the ResourceLookup reference
	 * 
	 * @return ResourceLookup
	 */
	public ResourceLookup getReferenceLookup() {
		return referenceLookup;
	}

	/**
	 * Returns the actual URI address irrespective of reference type
	 * 
	 * @return URI
	 */
	public String getTargetURI() {
		String uri;
		if (isLocal()) {
			uri = localResource == null ? null : localResource.getURIString();
		} else {
			uri = referenceURI;
		}
		return uri;
	}

	/**
	 * Sets the resource to local container;
	 * 
	 * @param localResource
	 */
	public void setLocalResource(Resource localResource) {
		this.local = true;
		this.referenceURI = null;
		this.referenceLookup = null;
		this.localResource = localResource;
	}

	/**
	 * Sets the reference URI to resource
	 * 
	 * @param referenceURI
	 */
	public void setReference(String referenceURI) {
		this.local = false;
		this.referenceURI = referenceURI;
		this.referenceLookup = null;
		this.localResource = null;
	}

	/**
	 * Sets the reference lookup to resource
	 * 
	 * @param referenceLookup
	 */
	@com.fasterxml.jackson.annotation.JsonIgnore
	public void setReference(ResourceLookup referenceLookup) {
		this.local = false;
		this.referenceURI = referenceLookup.getURIString();
		this.referenceLookup = referenceLookup;
		this.localResource = null;
	}

}
