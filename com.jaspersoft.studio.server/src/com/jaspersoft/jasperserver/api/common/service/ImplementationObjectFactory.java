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
package com.jaspersoft.jasperserver.api.common.service;

import java.util.List;

/**
 * 
 * Maps from interfaces to implementations and back
 * 
 * @author swood
 *
 */
public interface ImplementationObjectFactory {
	public Class getImplementationClass(Class _class);
	public Class getImplementationClass(String id);
	public String getImplementationClassName(Class _class);
	public String getImplementationClassName(String id);
	public Class getInterface(Class _class);
	public String getInterfaceName(Class _class);
	public String getIdForClass(Class _class);
	public Object newObject(Class _class);
	public Object newObject(String id);
	public List getKeys();
}
