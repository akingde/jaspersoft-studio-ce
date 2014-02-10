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
 * Wraps a filter element to apply it in negated form.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: NegatedFilterElement.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public class NegatedFilterElement implements FilterElement {

	private FilterElement element;
	
	/**
	 * Creates an empty negated filter.
	 * 
	 * @see #setElement(FilterElement)
	 */
	public NegatedFilterElement() {
	}
	
	/**
	 * Wraps a filter element to apply it in negate form.
	 * 
	 * @param element the element to wrap
	 */
	public NegatedFilterElement(FilterElement element) {
		this.element = element;
	}

	/**
	 * Returns the filter element which will be applied in negated form.
	 * 
	 * @return the wrapped the filter element
	 */
	public FilterElement getElement() {
		return element;
	}

	/**
	 * Sets the filter element which is to be applied in negated form.
	 * 
	 * @param element the filter element to be negated
	 */
	public void setElement(FilterElement element) {
		this.element = element;
	}
	
	/**
	 * @see Filter#applyNegatedFilter(FilterElement)
	 */
	public void apply(Filter filter) {
		filter.applyNegatedFilter(getElement());
	}

	/**
	 * Clones the wrapped filter element.
	 * 
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		return new NegatedFilterElement(element.cloneFilterElement());
	}

}
