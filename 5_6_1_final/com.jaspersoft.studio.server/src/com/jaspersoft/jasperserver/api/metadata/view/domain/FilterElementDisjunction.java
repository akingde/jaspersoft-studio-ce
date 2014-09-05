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
 * A disjunction of two or more filter elements.
 * 
 * <p>
 * The composing filter elements will be applied using OR as logical operation.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FilterElementDisjunction.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public class FilterElementDisjunction extends FilterElementCollection implements
		FilterElement {

	/**
	 * Creates an empty element disjunction.
	 * 
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 */
	public FilterElementDisjunction() {
		super();
	}

	/**
	 * @see Filter#applyDisjunction(java.util.List)
	 */
	public void apply(Filter filter) {
		filter.applyDisjunction(getFilterElements());
	}

	/**
	 * Performs a deep clone of the filter elements that compose this 
	 * disjunction.
	 * 
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		FilterElementDisjunction clone = new FilterElementDisjunction();
		clone.addClonedElements(getFilterElements());
		return clone;
	}
}
