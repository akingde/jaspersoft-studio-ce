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
 * The interface represents the property of
 * {@link com.jaspersoft.jasperserver.api.metadata.common.domain.InputControl}
 * which type is list of defined by user values.
 * It extends {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource}
 *
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ListOfValues.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @see com.jaspersoft.jasperserver.api.metadata.common.domain.client.ListOfValuesImpl
 */
@JasperServerAPI
public interface ListOfValues extends Resource
{

    /**
     * Returns the list items as array
     *
     * @return array of values
     */
    public ListOfValuesItem[] getValues();

    /**
     * Inserts one more item to the list
     *
     * @param item
     */
	public void addValue(ListOfValuesItem item);
    /**
     * Removes specified item from the list
     *
     * @param item
     */
	public void removeValue(ListOfValuesItem item);
}
