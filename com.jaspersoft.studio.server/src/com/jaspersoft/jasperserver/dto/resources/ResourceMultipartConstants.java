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
