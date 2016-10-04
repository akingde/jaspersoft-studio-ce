/*
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 */
package com.jaspersoft.ji.jaxrs.query;

import javax.ws.rs.core.MediaType;

/**
 * @author Vasyl Spachynskyi
 * @version $Id: QueryResultDataMediaType.java 58175 2016-03-25 19:57:33Z nthapa $
 * @since 11.02.2016
 */
public class QueryResultDataMediaType {
    public final static String FLAT_DATA = "flatData";
    public final static String MULTI_LEVEL_DATA = "multiLevelData";
    public final static String MULTI_AXES_DATA = "multiAxesData";

    public final static String APPLICATION = "application";
    public final static String JSON = "+json";
    public final static String XML = "+xml";

    public final static String FLAT_DATA_JSON = APPLICATION + "/" + FLAT_DATA + JSON;
    public final static String MULTI_LEVEL_DATA_JSON = APPLICATION + "/" + MULTI_LEVEL_DATA + JSON;
    public final static String MULTI_AXES_DATA_JSON = APPLICATION + "/" + MULTI_AXES_DATA + JSON;

    public final static String FLAT_DATA_XML = APPLICATION + "/" + FLAT_DATA + XML;
    public final static String MULTI_LEVEL_DATA_XML = APPLICATION + "/" + MULTI_LEVEL_DATA + XML;
    public final static String MULTI_AXES_DATA_XML = APPLICATION + "/" + MULTI_AXES_DATA + XML;

    public final static MediaType FLAT_DATA_JSON_TYPE = new MediaType(APPLICATION, FLAT_DATA + JSON);
    public final static MediaType MULTI_LEVEL_DATA_JSON_TYPE = new MediaType(APPLICATION, MULTI_LEVEL_DATA + JSON);
    public final static MediaType MULTI_AXES_DATA_JSON_TYPE = new MediaType(APPLICATION, MULTI_AXES_DATA + JSON);

    public final static MediaType FLAT_DATA_XML_TYPE = new MediaType(APPLICATION, FLAT_DATA + XML);
    public final static MediaType MULTI_LEVEL_DATA_XML_TYPE = new MediaType(APPLICATION, MULTI_LEVEL_DATA + XML);
    public final static MediaType MULTI_AXES_DATA_XML_TYPE = new MediaType(APPLICATION, MULTI_AXES_DATA + XML);
 

}