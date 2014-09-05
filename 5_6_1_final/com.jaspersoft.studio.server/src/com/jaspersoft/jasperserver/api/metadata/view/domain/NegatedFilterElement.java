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
