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
 * Filter that matches a single resource based on its repository path/URI.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: URIFilter.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 2.0.0
 */
@JasperServerAPI
public class URIFilter implements FilterElement {
	
	private String uri;
	
	/**
	 * Creates an empty URI filter.
	 * 
	 * @see #setURI(String)
	 */
	public URIFilter() {
	}
	
	/**
	 * Creates a URI filter for a specific resource.
	 * 
	 * @param uri the repository path of the resource to find
	 */
	public URIFilter(String uri) {
		this.uri = uri;
	}

	/**
	 * @see Filter#applyURIFilter(URIFilter)
	 */
	public void apply(Filter filter) {
		filter.applyURIFilter(this);
	}

	/**
	 * Returns the repository path of the resource to find.
	 * 
	 * @return the repository path of the resource to find
	 */
	public String getURI() {
		return uri;
	}

	/**
	 * Sets the repository path of the resource that this filter will match.
	 * 
	 * @param uri the repository path of the resource to find
	 */
	public void setURI(String uri) {
		this.uri = uri;
	}

	/**
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		return new URIFilter(uri);
	}

}
