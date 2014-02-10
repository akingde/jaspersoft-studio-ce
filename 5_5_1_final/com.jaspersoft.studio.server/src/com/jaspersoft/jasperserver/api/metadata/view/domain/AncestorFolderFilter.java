/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
