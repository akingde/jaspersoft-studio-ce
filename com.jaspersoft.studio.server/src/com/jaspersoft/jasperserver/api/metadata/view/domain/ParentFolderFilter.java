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
 * Filter criteria which matches resources that are direct children of a 
 * specific repository folder.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ParentFolderFilter.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public class ParentFolderFilter implements FilterElement {
	
	private String folderURI;
	
	/**
	 * Creates an empty filter.
	 * 
	 * @see #setFolderURI(String)
	 */
	public ParentFolderFilter() {
	}
	
	/**
	 * Creates a filter for a specific parent folder.
	 * 
	 * @param folderURI the repository path of the parent folder
	 */
	public ParentFolderFilter(String folderURI) {
		this.folderURI = folderURI;
	}

	/**
	 * @see Filter#applyParentFolderFilter(ParentFolderFilter)
	 */
	public void apply(Filter filter) {
		filter.applyParentFolderFilter(this);
	}

	/**
	 * Returns the repository path of the parent folder used by this filter.
	 * 
	 * @return the parent folder used by this filter
	 */
	public String getFolderURI() {
		return folderURI;
	}

	/**
	 * Sets the folder whose child resources are to be matched by this filter.
	 * 
	 * @param folderURI the repository path of the folder
	 */
	public void setFolderURI(String folderURI) {
		this.folderURI = folderURI;
	}

	/**
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		return new ParentFolderFilter(folderURI);
	}

}
