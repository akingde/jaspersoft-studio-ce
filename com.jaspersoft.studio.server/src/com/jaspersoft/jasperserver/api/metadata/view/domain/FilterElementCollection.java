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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Base class for filter criteria object that are composed of filter elements 
 * lists. 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @author Sherman Wood
 * @version $Id: FilterElementCollection.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public abstract class FilterElementCollection {
	private final List criteria;
	
	protected FilterElementCollection() {
		criteria = new ArrayList();
	}
	
	/**
	 * Returns the list of filter elements that make up this criteria.
	 * 
	 * @return a list of {@link FilterElement}
	 */
	public List getFilterElements() {
		return criteria;
	}
	
	/**
	 * Adds a filter element to the list of criteria.
	 * 
	 * @param element the element to add
	 */
	public void addFilterElement(FilterElement element) {
		criteria.add(element);
	}

	/**
	 * Adds a filter element in negated form to the list of criteria.
	 * 
	 * @param element the element to add in negated form
	 * @see NegatedFilterElement
	 */
	public void addNegatedFilterElement(FilterElement element) {
		NegatedFilterElement negated = new NegatedFilterElement();
		negated.setElement(element);
		criteria.add(negated);
	}
	
	/**
	 * Creates an empty conjunction of filter criteria and adds it as an filter 
	 * element to the list of criteria wrapped by this object.
	 * 
	 * <p>
	 * The filter conjunction is returned and filter elements should be added 
	 * to it.
	 * </p>
	 * 
	 * @return the empty filter conjunction that was created
	 * @see FilterElementConjunction 
	 */
	public FilterElementConjunction addConjunction() {
		FilterElementConjunction conjunction = new FilterElementConjunction();
		addFilterElement(conjunction);
		return conjunction;
	}
	
	/**
	 * Creates an empty disjunction of filter criteria and adds it as an filter 
	 * element to the list of criteria wrapped by this object.
	 * 
	 * <p>
	 * The filter disjunction is returned and filter elements should be added 
	 * to it.
	 * </p>
	 * 
	 * @return the empty filter disjunction that was created 
	 * @see FilterElementDisjunction 
	 */
	public FilterElementDisjunction addDisjunction() {
		FilterElementDisjunction disjunction = new FilterElementDisjunction();
		addFilterElement(disjunction);
		return disjunction;
	}
	
	/**
	 * Creates an empty disjunction of two filter criteria and adds it as a
	 * filter element to the list of criteria wrapped by this object.
	 * 
	 * <p>
	 * The filter disjunction is returned and the two filter elements should be
	 * set to it.
	 * </p>
	 * 
	 * @return the empty filter disjunction that was created
	 * @see FilterElementOr#setLeftHandSide(FilterElement)
	 * @see FilterElementOr#setRightHandSide(FilterElement)
	 * @since 1.2.0
	 */
	public FilterElementOr addOr() {
		FilterElementOr or = new FilterElementOr();
		addFilterElement(or);
		return or;
	}
	
	protected void addClonedElements(List filterElements) {
		for (Iterator it = filterElements.iterator(); it.hasNext();) {
			FilterElement element = (FilterElement) it.next();
			FilterElement elementClone = element.cloneFilterElement();
			addFilterElement(elementClone);
		}
	}
}
