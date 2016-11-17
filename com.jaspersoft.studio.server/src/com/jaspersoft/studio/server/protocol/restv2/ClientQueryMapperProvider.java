/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import static com.jaspersoft.ji.jaxrs.query.QueryExecutionsMediaType.EXECUTION_MULTI_AXES_QUERY_JSON;
import static com.jaspersoft.ji.jaxrs.query.QueryExecutionsMediaType.EXECUTION_MULTI_AXES_QUERY_XML;
import static com.jaspersoft.ji.jaxrs.query.QueryExecutionsMediaType.EXECUTION_MULTI_LEVEL_QUERY_JSON;
import static com.jaspersoft.ji.jaxrs.query.QueryExecutionsMediaType.EXECUTION_MULTI_LEVEL_QUERY_XML;
import static com.jaspersoft.ji.jaxrs.query.QueryExecutionsMediaType.EXECUTION_PROVIDED_QUERY_JSON;
import static com.jaspersoft.ji.jaxrs.query.QueryExecutionsMediaType.EXECUTION_PROVIDED_QUERY_XML;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * @author Andriy Godovanets
 */
@Provider 
@Consumes({EXECUTION_MULTI_AXES_QUERY_JSON,
        EXECUTION_MULTI_LEVEL_QUERY_JSON,
        EXECUTION_PROVIDED_QUERY_JSON,

        EXECUTION_MULTI_LEVEL_QUERY_XML,
        EXECUTION_MULTI_AXES_QUERY_XML,
        EXECUTION_PROVIDED_QUERY_XML
})
@Produces(MediaType.WILDCARD)
public class ClientQueryMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ClientQueryMapperProvider() {
        mapper = getObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        
        JaxbAnnotationModule jaxbModule = new JaxbAnnotationModule();
        mapper.registerModule(jaxbModule);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
        mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }
}
