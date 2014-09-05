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
 * A set of criteria used to filter repository resources.
 * 
 * <p>
 * A filter consists of a set of filter elements, which are applied using
 * AND as logical operation, that is the filtered resources need to match
 * every filter element in the set.
 * </p>
 * 
 * <p>
 * A special filter attribute is the resource type.
 * A filter can be associated with a specific resource type, and it will only
 * match resources of that type.
 * If a resource type is not set, resources of any type will be matched.
 * </p>
 * 
 * @author Sherman Wood
 * @author Lucian Chirita
 * @version $Id: FilterCriteria.java 28947 2013-02-26 15:02:08Z vsabadosh $
 * @since 1.0
 * @see com.jaspersoft.jasperserver.api.metadata.common.service.RepositoryService#findResource(com.jaspersoft.jasperserver.api.common.domain.ExecutionContext, FilterCriteria)
 * @see FilterElementCollection#addFilterElement(FilterElement)
 */
@JasperServerAPI
public class FilterCriteria extends FilterElementCollection
{
	/**
	 * Creates a parent folder filter for a specific folder.
	 * 
	 * @param folderURI the repository path of the parent folder
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 */
	public static ParentFolderFilter createParentFolderFilter(String folderURI) {
		ParentFolderFilter filter = new ParentFolderFilter();
		filter.setFolderURI(folderURI);
		return filter;
	}	

	/**
	 * Creates an ancestor filter for a specific folder.
	 * 
	 * @param folderURI the repository path of the folder
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @since 3.5.0
	 */
	public static AncestorFolderFilter createAncestorFolderFilter(String folderURI) {
		return new AncestorFolderFilter(folderURI);
	}	

	/**
	 * Creates a filter that compares a resource field to a specified value.
	 * 
	 * @param property the name of the resource field
	 * @param value the value to compare the field against
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @see PropertyFilter#EQ
	 */
	public static PropertyFilter createPropertyEqualsFilter(String property, Object value) {
		return createPropertyFilter(property, value, PropertyFilter.EQ);
	}

	/**
	 * Creates a filter that matches a resource field to a given value using 
	 * SQL LIKE semantics.
	 * 
	 * @param property the name of the resource field
	 * @param value the value to compare the field against
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @see PropertyFilter#LIKE
	 */
	public static PropertyFilter createPropertyLikeFilter(String property, Object value) {
		return createPropertyFilter(property, value, PropertyFilter.LIKE);
	}

    public static PropertyFilter createPropertyInFilter(String property, Object[] values) {
        return createPropertyFilter(property, values, PropertyFilter.IN);
    }

	/**
	 * Creates a filter that compares a resource field to be greater than the 
	 * given value.
	 * 
	 * @param property the name of the resource field
	 * @param value the value to compare the field against
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @see PropertyFilter#GT
	 */
	public static PropertyFilter createPropertyGreaterThanFilter(String property, Object value) {
		return createPropertyFilter(property, value, PropertyFilter.GT);
	}

	/**
	 * Creates a filter that compares a resource field to be less than the 
	 * given value.
	 * 
	 * @param property the name of the resource field
	 * @param value the value to compare the field against
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @see PropertyFilter#LT
	 */
	public static PropertyFilter createPropertyLessThanFilter(String property, Object value) {
		return createPropertyFilter(property, value, PropertyFilter.LT);
	}

	/**
	 * Creates a filter that checks a resource field to be between the low and 
	 * high values.
	 * 
	 * @param property the name of the resource field
	 * @param loValue the low value
	 * @param hiValue the high value
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @see PropertyFilter#BETWEEN
	 */
	public static PropertyFilter createPropertyBetweenFilter(String property, Object loValue, Object hiValue) {
		return createPropertyFilter(property, loValue, hiValue, PropertyFilter.BETWEEN);
	}
	
	protected static PropertyFilter createPropertyFilter(String property, Object value, byte op) {
		PropertyFilter filter = new PropertyFilter();
		filter.setOp(op);
		filter.setProperty(property);
		filter.setValue(value);
		return filter;
	}

	protected static PropertyFilter createPropertyFilter(String property, Object loValue, Object hiValue, byte op) {
		PropertyFilter filter = new PropertyFilter();
		filter.setOp(op);
		filter.setProperty(property);
		filter.setLowValue(loValue);
		filter.setHighValue(hiValue);
		return filter;
	}

    protected static PropertyFilter createPropertyFilter(String property, Object[] values, byte op) {
        PropertyFilter filter = new PropertyFilter();
        filter.setOp(op);
        filter.setProperty(property);
        filter.setValues(values);
        return filter;
    }

    /**
	 * Creates a resource reference filter.
	 * 
	 * @param property the name of the reference field on which to filter resources
	 * @param referenceType the type of the referenced resource
	 * @param referredURI the repository path of the referenced resource
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @since 2.0.0
	 */
	public static ReferenceFilter createReferenceFilter(String property, Class referenceType, String referredURI) {
		ReferenceFilter filter = new ReferenceFilter();
		filter.setProperty(property);
		filter.setReferenceClass(referenceType);
		filter.setReferredURI(referredURI);
		return filter;
	}
	
	/**
	 * Creates a filter that matches a single resource based on its repository
	 * path.
	 * 
	 * @param uri the repository path of the resource to find
	 * @return the created filter
	 * @see FilterElementCollection#addFilterElement(FilterElement)
	 * @since 2.0.0
	 */
	public static URIFilter createResourceURIFilter(String uri) {
		URIFilter filter = new URIFilter();
		filter.setURI(uri);
		return filter;
	}	
	
	private Class _class;

	/**
	 * Creates an empty filter.
	 * 
	 * <p>
	 * The criteria is not associated with a resource type.
	 * </p>
	 * 
	 * @return the created filter
	 */
	public static FilterCriteria createFilter() {
		return new FilterCriteria();
	}

	/**
	 * Creates an empty filter for a specific resource type.
	 * 
	 * @param _class the resource type defined as the resource interface
	 * @return the created filter
	 */
	public static FilterCriteria createFilter(Class _class) {
		return new FilterCriteria(_class);
	}
	
	/**
	 * Creates an empty filter.
	 * 
	 * <p>
	 * The criteria is not associated with a resource type.
	 * </p>
	 */
	public FilterCriteria () {
		this(null);
	}
	
	/**
	 * Creates an empty filter for a specific resource type.
	 * 
	 * @param _class the resource type defined as the resource interface
	 */
	public FilterCriteria (Class _class) {
		super();
		this._class = _class;
	}

	/**
	 * Returns the resource type for which this filter has been created,
	 * or <code>null</code> if the filter is not associated to a particular
	 * resource type.
	 * 
	 * @return the resource type of this filter, if set
	 */
	public Class getFilterClass() {
		return _class;
	}

	/**
	 * Associates this filter with a resource type.
	 * 
	 * <p>
	 * The filter will only match resources of the specified type.
	 * </p>
	 * 
	 * @param _class the resource type defined as the resource interface
	 */
	public void setFilterClass(Class _class) {
		this._class = _class;
	}
	
	/**
	 * Clones a filter by cloning each of the filter elements that it contains.
	 * 
	 * @return a deep clone of the filter
	 * @since 3.5.0
	 * @see FilterElementCollection#getFilterElements()
	 */
	public FilterCriteria cloneFilter() {
		FilterCriteria clone = new FilterCriteria(_class);
		clone.addClonedElements(getFilterElements());
		return clone;
	}
}
