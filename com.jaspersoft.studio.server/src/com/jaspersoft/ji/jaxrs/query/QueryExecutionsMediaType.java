/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.ji.jaxrs.query;

/**
 * @author Vasyl Spachynskyi
 * @version $Id: QueryExecutionsMediaType.java 57869 2016-03-15 14:23:21Z vspachyn $
 * @since 16.01.2016
 */
public class QueryExecutionsMediaType {
    public final static String EXECUTION_MULTI_LEVEL_QUERY_JSON = "application/execution.multiLevelQuery+json";
    public final static String EXECUTION_MULTI_AXES_QUERY_JSON = "application/execution.multiAxesQuery+json";
    public final static String EXECUTION_PROVIDED_QUERY_JSON = "application/execution.providedQuery+json";

    public final static String EXECUTION_MULTI_LEVEL_QUERY_XML = "application/execution.multiLevelQuery+xml";
    public final static String EXECUTION_MULTI_AXES_QUERY_XML = "application/execution.multiAxesQuery+xml";
    public final static String EXECUTION_PROVIDED_QUERY_XML = "application/execution.providedQuery+xml";
}
