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
 * A disjunction of two filter elements that are applied using OR as logical
 * operation.
 * 
 * @author swood
 * @version $Id: FilterElementOr.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.2.0
 * @see FilterElementDisjunction
 */
@JasperServerAPI
public class FilterElementOr implements	FilterElement {

	private FilterElement leftHandSide;
	private FilterElement rightHandSide;
	
	/**
	 * Creates an empty disjunction.
	 * 
	 * @see #setLeftHandSide(FilterElement)
	 * @see #setRightHandSide(FilterElement)
	 */
	public FilterElementOr() {
		super();
	}

	/**
	 * Creates a disjunction of two filter elements.
	 * 
	 * @param leftHandSide the first filter element
	 * @param rightHandSide the second filter element
	 */
	public FilterElementOr(FilterElement leftHandSide, FilterElement rightHandSide) {
		super();
		
		this.leftHandSide = leftHandSide;
		this.rightHandSide = rightHandSide;
	}

	/**
	 * @see Filter#applyOr(FilterElement, FilterElement)
	 */
	public void apply(Filter filter) {
		filter.applyOr(getLeftHandSide(), getRightHandSide());

	}

	/**
	 * Returns the first element in the disjunction.
	 * 
	 * @return the first element in the disjunction
	 */
	public FilterElement getLeftHandSide() {
		return leftHandSide;
	}

	/**
	 * Sets the first element in the disjunction.
	 * 
	 * @param leftHandSide the first element in the disjunction
	 */
	public void setLeftHandSide(FilterElement leftHandSide) {
		this.leftHandSide = leftHandSide;
	}

	/**
	 * Returns the second element in the disjunction.
	 * 
	 * @return the second element in the disjunction
	 */
	public FilterElement getRightHandSide() {
		return rightHandSide;
	}


	/**
	 * Sets the second element in the disjunction.
	 * 
	 * @param rightHandSide the second element in the disjunction
	 */
	public void setRightHandSide(FilterElement rightHandSide) {
		this.rightHandSide = rightHandSide;
	}

	/**
	 * Clones the two filter elements in the disjunction.
	 * 
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		return new FilterElementOr(
				leftHandSide.cloneFilterElement(), 
				rightHandSide.cloneFilterElement());
	}

}
