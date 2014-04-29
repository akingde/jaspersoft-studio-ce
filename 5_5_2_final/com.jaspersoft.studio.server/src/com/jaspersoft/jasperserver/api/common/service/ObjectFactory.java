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
import java.util.Map;

/**
 * @author swood
 *
 */
public interface ObjectFactory {

	public Class getImplementationClass(Map classMappings, Class itfClass);

	public Class getImplementationClass(Map classMappings, String id);
	
	public String getImplementationClassName(Map classMappings, Class itfClass);

	public String getImplementationClassName(Map classMappings, String id);

	public Class getInterface(Map classMappings, Class _class);

	public String getIdForClass(Map classMappings, Class _class);

	public String getInterfaceName(Map classMappings, Class _class);

	public Object newObject(Map classMappings, Class itfClass);

	public Object newObject(Map classMappings, String id);
	
	public List getKeys(Map classMappings);
	
}
