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
 * A conjunction of two or more filter elements.
 * 
 * <p>
 * The composing filter elements will be applied using AND as logical operation.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FilterElementConjunction.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public class FilterElementConjunction extends FilterElementCollection implements
		FilterElement {

	/**
	 * Creates an empty element conjunction.
	 * 
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 */
	public FilterElementConjunction() {
		super();
	}

	/**
	 * @see Filter#applyConjunction(java.util.List)
	 */
	public void apply(Filter filter) {
		filter.applyConjunction(getFilterElements());
	}

	/**
	 * Performs a deep clone of the filter elements that compose this 
	 * conjunction.
	 * 
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		FilterElementConjunction clone = new FilterElementConjunction();
		clone.addClonedElements(getFilterElements());
		return clone;
	}
}
