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

import java.util.List;

/**
 * Filter object that applies filter criteria in a the context where filtering
 * is performed.
 * 
 * <p>
 * A filter implementation acts as a bridge for the various filter criteria 
 * types.
 * It includes logic to translate the filter criteria to operation that
 * achieve the expected result filtering.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @author Sherman Wood
 * @version $Id: Filter.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 * @see FilterElement#apply(Filter)
 */
@JasperServerAPI
public interface Filter {
	
	/**
	 * Applies a parent folder filter.
	 * 
	 * @param filter the filter to apply
	 * @see ParentFolderFilter
	 */
	void applyParentFolderFilter(ParentFolderFilter filter);
	
	/**
	 * Applies a property filter.
	 * 
	 * @param filter the filter to apply
	 * @see PropertyFilter
	 */
	void applyPropertyFilter(PropertyFilter filter);
	
	/**
	 * Applies a filter by negating the its criteria.
	 * 
	 * @param filter the filter to apply in negated form
	 * @see NegatedFilterElement
	 */
	void applyNegatedFilter(FilterElement element);

	/**
	 * Applies a list of filters using logical conjunction/AND operation on the
	 * filter criteria.
	 * 
	 * @param filterElements the list of filters
	 * @see FilterElementConjunction 
	 */
	void applyConjunction(List filterElements);
	
	/**
	 * Applies a list of filters using logical disjunction/OR operation on the
	 * filter criteria.
	 * 
	 * @param filterElements the list of filters
	 * @see FilterElementDisjunction 
	 */
	void applyDisjunction(List filterElements);
	
	/**
	 * Applies two filters using a logical OR operation on the criteria.
	 * 
	 * @param lhs the first filter
	 * @param rhs the second filter
	 * @see FilterElementOr
	 * @since 1.2.0
	 */
	void applyOr(FilterElement lhs, FilterElement rhs);
	
	/**
	 * Applies a resource reference filter.
	 * 
	 * @param filter the filter to apply
	 * @since 2.0.0
	 * @see ReferenceFilter
	 */
	void applyReferenceFilter(ReferenceFilter filter);
	
	/**
	 * Applies a resource URI filter.
	 * 
	 * @param filter the filter to apply
	 * @since 2.0.0
	 * @see URIFilter
	 */
	void applyURIFilter(URIFilter filter);
	
	/**
	 * Applies an ancestor folder filter.
	 * 
	 * @param filter the filter to apply
	 * @since 3.5.0
	 * @see AncestorFolderFilter
	 */
	void applyAncestorFolderFilter(AncestorFolderFilter filter);

}
