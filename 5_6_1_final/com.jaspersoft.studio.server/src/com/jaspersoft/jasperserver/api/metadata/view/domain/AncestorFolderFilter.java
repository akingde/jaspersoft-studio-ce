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
 * Filter that matches resources located under a specific folder at any level.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: AncestorFolderFilter.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 3.5.0
 */
@JasperServerAPI
public class AncestorFolderFilter implements FilterElement {
	
	private String folderURI;
	
	/**
	 * Creates an empty ancestor filter.
	 * 
	 * @see #setFolderURI(String)
	 */
	public AncestorFolderFilter() {
	}
	
	/**
	 * Creates an ancestor filter for a specific folder.
	 * 
	 * @param folderURI the repository path of the folder
	 */
	public AncestorFolderFilter(String folderURI) {
		this.folderURI = folderURI;
	}

	/**
	 * @see Filter#applyAncestorFolderFilter(AncestorFolderFilter)
	 */
	public void apply(Filter filter) {
		filter.applyAncestorFolderFilter(this);
	}

	/**
	 * Returns the repository path of the folder to use for this filter.
	 * 
	 * @return the repository path of the folder to filter on
	 */
	public String getFolderURI() {
		return folderURI;
	}

	/**
	 * Sets the folder whose child resources should be matched by this filter.
	 * 
	 * @param folderURI the repository path of the folder to filter on
	 */
	public void setFolderURI(String folderURI) {
		this.folderURI = folderURI;
	}

	public FilterElement cloneFilterElement() {
		return new AncestorFolderFilter(folderURI);
	}

}
