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
package com.jaspersoft.jasperserver.api.common.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

import java.util.List;

/**
 * Class which implements AttributedObject interface can keep a list of attributes.
 *
 * @author swood
 * @version $Id: AttributedObject.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0.1
 */
@JasperServerAPI
public interface AttributedObject {

	/**
     * Returns a list of attributes for the object.
     *
	 * @return a list of attributes for the object
	 */
	public List getAttributes();

    /**
     * Sets a list of attributes for the object.
     *
     * @param attrs a list of attributes for the object.
     */
    public void setAttributes(List attrs);
}
