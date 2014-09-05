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
import com.jaspersoft.jasperserver.api.JSExceptionWrapper;

/**
 * Filter that applies an operation to a resource field against a given value.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: PropertyFilter.java 28947 2013-02-26 15:02:08Z vsabadosh $
 * @since 1.0
 */
@JasperServerAPI
public class PropertyFilter implements FilterElement, Cloneable {
	
	/**
	 * Operation that compares the field to the given value.
	 * 
	 * @see #getOp()
	 */
	public static final byte EQ = 0;
	
	/**
	 * Operation that matches the field to the given value using SQL LIKE
	 * semantics.
	 * 
	 * @see #getOp()
	 */
	public static final byte LIKE = 1;
	
	/**
	 * Operation that compares the field to be greater than the given value.
	 * 
	 * @see #getOp()
	 */
	public static final byte GT = 2;
	
	/**
	 * Operation that compares the field to be less than the given value.
	 * 
	 * @see #getOp()
	 */
	public static final byte LT = 3;
	
	/**
	 * Operation that checks the field to be between the low and high values.
	 * 
	 * @see #getOp()
	 * @see #getLowValue()
	 * @see #getHighValue()
	 */
	public static final byte BETWEEN = 4;

    /**
     * Operation that checks if the property value is in the list
     *
     * @see #getOp()
     */
    public static final byte IN = 5;

	private String property;
	private Object value;
	private Object value1;
    private Object[] values;
	private byte op;
	
	/**
	 * Creates an empty property filter.
	 */
	public PropertyFilter() {
	}

	/**
	 * @see Filter#applyPropertyFilter(PropertyFilter)
	 */
	public void apply(Filter filter) {
		filter.applyPropertyFilter(this);
	}

	/**
	 * Returns the operation that will be used to match the resource field
	 * against the value(s).
	 * 
	 * @return one of the supported operations
	 * @see #EQ
	 * @see #LIKE
	 * @see #GT
	 * @see #LT
	 * @see #BETWEEN
	 */
	public byte getOp() {
		return op;
	}

	/**
	 * Sets the operation used to match the resource field against the 
	 * provided value(s).
	 * 
	 * @param op one of the supported operations 
	 * @see #EQ
	 * @see #LIKE
	 * @see #GT
	 * @see #LT
	 * @see #BETWEEN
	 */
	public void setOp(byte op) {
		this.op = op;
	}

	/**
	 * Returns the name of the resource field that will be matched by this
	 * filter.
	 * 
	 * @return the resource field name
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Sets the resource field that will be used to by this filter to match
	 * resources.
	 * 
	 * @param property the resource field name
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Returns the value used to match against the resource field.
	 * 
	 * @return the value to use for the filter
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value to be matched against the resource field.
	 * 
	 * @param value the value used by this filter
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Returns the low value used for a BETWEEN condition.
	 * 
	 * @return the low value for a BETWEEN condition
	 * @see #BETWEEN
	 */
	public Object getLowValue() {
		return value;
	}

	/**
	 * Sets the low value used for a BETWEEN condition.
	 * 
	 * @param loValue the low value for a BETWEEN condition
	 * @see #BETWEEN
	 */
	public void setLowValue(Object loValue) {
		this.value = loValue;
	}
	
	/**
	 * Returns the high value used for a BETWEEN condition.
	 * 
	 * @return the high value for a BETWEEN condition
	 * @see #BETWEEN
	 */
	public Object getHighValue() {		
		return value1;
	}

	/**
	 * Sets the high value used for a BETWEEN condition.
	 * 
	 * @param hiValue the high value for a BETWEEN condition
	 * @see #BETWEEN
	 */
	public void setHighValue(Object hiValue) {
		this.value1 = hiValue;
	}

    /**
     * Gets the list of values for IN condition
     * @return
     */
    public Object[] getValues() {
        return values;
    }

    /**
     * Sets the list of values for IN condition
     * @param values
     */
    public void setValues(Object[] values) {
        this.values = values;
    }

	/**
	 * @since 3.5.0
	 */
	public FilterElement cloneFilterElement() {
		try {
			return (FilterElement) clone();
		} catch (CloneNotSupportedException e) {
			// never
			throw new JSExceptionWrapper(e);
		}
	}
}
