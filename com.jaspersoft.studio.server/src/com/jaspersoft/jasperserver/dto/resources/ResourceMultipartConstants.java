/*
* Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights  reserved.
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
* along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
*/
package com.jaspersoft.jasperserver.dto.resources;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ResourceMultipartConstants.java 35226 2013-08-09 07:08:53Z inesterenko $
 */
public interface ResourceMultipartConstants {
    public static final String RESOURCE_PART_NAME = "resource";
    public static final String SCHEMA_PART_NAME = "schema";
    public static final String MONDRIAN_ACCESS_GRANT_PART_NAME_PREFIX = "accessGrantSchemas.accessGrantSchema";
    public static final String REPORT_JRXML_PART_NAME = "jrxml";
    public static final String REPORT_FILE_PART_NAME_PREFIX = "files.";
    public static final String SEMANTIC_LAYER_DATA_SOURCE_SECURITY_FILE_PART_NAME = "securityFile";
    public static final String SEMANTIC_LAYER_DATA_SOURCE_BUNDLE_PART_NAME_PREFIX = "bundles.bundle";
}
