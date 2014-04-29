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
package com.jaspersoft.jasperserver.api.metadata.common.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;


/**
 * DataType is the interface which represents the base type of JasperServer input control
 * {@link com.jaspersoft.jasperserver.api.metadata.common.domain.InputControl}:
 * text, number, date and datetime. It extends {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource}
 *
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: InputControl.java 2332 2006-03-09 02:23:53Z tony $
 * @see com.jaspersoft.jasperserver.api.metadata.common.domain.client.DataTypeImpl
 */
@JasperServerAPI
public interface DataType extends Resource
{

    /**
	 *
	 */
    public static final byte TYPE_TEXT = 1;
	public static final byte TYPE_NUMBER = 2;
	public static final byte TYPE_DATE = 3;
	public static final byte TYPE_DATE_TIME = 4;
	public static final byte TYPE_TIME = 5;

    /**
     * Returns the type number of input control
     *
     * @return type
     */
	public byte getType();

    /**
     * Sets the type number of input control
     *
     * @param type
     */
	public void setType(byte type);

    /**
     * Returns the maximal value length of text input control
     *
     * @return type
     */
	public Integer getMaxLength();

    /**
     * Sets the maximal value length for text input control
     *
     * @param maxLength
     */
	public void setMaxLength(Integer maxLength);

	/**
	 *
	 */
	public Integer getDecimals();

	/**
	 *
	 */
	public void setDecimals(Integer decimals);

    /**
     * Returns the regular expression of text input control
     *
     * @return regular expression
     */
	public String getRegularExpr();

    /**
     * Sets the regular expression to text input control
     *
     * @param regExp regular expression string
     */
	public void setRegularExpr(String regExp);

    /**
     * Returns the minimal value of numeric input control
     *
     * @return minimal value
     */
	public Comparable getMinValue();

    /**
     * Sets the minimal value for numeric input control
     *
     * @param minValue
     */
	public void setMinValue(Comparable minValue);

    /**
     * Returns the maximal value of numeric input control
     *
     * @return maximal value
     */
	public Comparable getMaxValue();

    /**
     * Sets the maximal value for numeric input control
     *
     * @param maxValue
     */
	public void setMaxValue(Comparable maxValue);

    /**
     * Returns <code>true</code> if the minimal value is included to the possible values of input control.
     *
     * @return <code>true</code> if the minimal value is strict
     */
	public boolean isStrictMin();

    /**
     * Includes the minimal value to the possible values of input control.
     *
     * @param isStrictMin <code>true</code> if minimal value should be strict
     */
	public void setStrictMin(boolean isStrictMin);

    /**
     * Returns <code>true</code> if the maximal value is included to the possible values of input control.
     *
     * @return <code>true</code> if the maximal value is strict
     */
	public boolean isStrictMax();

    /**
     * Includes the maximal value to the possible values of input control.
     *
     * @param isStrictMax <code>true</code> if maximal value should be strict
     */
	public void setStrictMax(boolean isStrictMax);

}
