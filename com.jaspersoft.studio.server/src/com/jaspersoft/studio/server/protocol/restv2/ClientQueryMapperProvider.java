/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;

/**
 * @author Andriy Godovanets
 */
@Provider
@Consumes({ "application/xml", "application/collection+json", "application/collection+xml", "application/job+json",
		"application/json", "application/job+xml", ResourceMediaType.LIST_OF_VALUES_JSON,
		ResourceMediaType.LIST_OF_VALUES_XML, ResourceMediaType.ADHOC_DATA_VIEW_JSON,
		ResourceMediaType.ADHOC_DATA_VIEW_XML, ResourceMediaType.AWS_DATA_SOURCE_JSON,
		ResourceMediaType.AWS_DATA_SOURCE_XML, ResourceMediaType.BEAN_DATA_SOURCE_JSON,
		ResourceMediaType.BEAN_DATA_SOURCE_XML, ResourceMediaType.CUSTOM_DATA_SOURCE_JSON,
		ResourceMediaType.CUSTOM_DATA_SOURCE_XML, ResourceMediaType.DATA_TYPE_JSON, ResourceMediaType.DATA_TYPE_XML,
		ResourceMediaType.FILE_JSON, ResourceMediaType.FILE_XML, ResourceMediaType.FOLDER_JSON,
		ResourceMediaType.FOLDER_XML, ResourceMediaType.INPUT_CONTROL_JSON, ResourceMediaType.INPUT_CONTROL_XML,
		ResourceMediaType.JDBC_DATA_SOURCE_JSON, ResourceMediaType.JDBC_DATA_SOURCE_XML,
		ResourceMediaType.JNDI_JDBC_DATA_SOURCE_JSON, ResourceMediaType.JNDI_JDBC_DATA_SOURCE_XML,
		ResourceMediaType.MONDRIAN_CONNECTION_JSON, ResourceMediaType.MONDRIAN_CONNECTION_XML,
		ResourceMediaType.MONDRIAN_XMLA_DEFINITION_JSON, ResourceMediaType.MONDRIAN_XMLA_DEFINITION_XML,
		ResourceMediaType.OLAP_UNIT_JSON, ResourceMediaType.OLAP_UNIT_XML, ResourceMediaType.QUERY_JSON,
		ResourceMediaType.QUERY_XML, ResourceMediaType.REPORT_UNIT_JSON, ResourceMediaType.REPORT_UNIT_XML,
		ResourceMediaType.SECURE_MONDRIAN_CONNECTION_JSON, ResourceMediaType.SECURE_MONDRIAN_CONNECTION_XML,
		ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_JSON, ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_XML,
		ResourceMediaType.VIRTUAL_DATA_SOURCE_JSON, ResourceMediaType.VIRTUAL_DATA_SOURCE_XML,
		ResourceMediaType.XMLA_CONNECTION_JSON, ResourceMediaType.XMLA_CONNECTION_XML,
		ResourceMediaType.RESOURCE_LOOKUP_JSON, ResourceMediaType.RESOURCE_LOOKUP_XML,
		"application/repository.dashboard+json", "application/repository.dashboard+xml",
		"application/repository.domainTopic+json", "application/repository.domainTopic+xml",
		"application/repository.semanticlayerdatasource+json", "application/repository.semanticlayerdatasource+xml",
		"application/hal+json", "application/hal+xml", "text/json", "text/xml",
		"application/attributes.collection+json", "application/attributes.collection+xml" })
@Produces({ "application/collection+json", "application/collection+xml", "application/job+json", "application/json",
		"application/job+xml", ResourceMediaType.LIST_OF_VALUES_JSON, ResourceMediaType.LIST_OF_VALUES_XML,
		ResourceMediaType.ADHOC_DATA_VIEW_JSON, ResourceMediaType.ADHOC_DATA_VIEW_XML,
		ResourceMediaType.AWS_DATA_SOURCE_JSON, ResourceMediaType.AWS_DATA_SOURCE_XML,
		ResourceMediaType.BEAN_DATA_SOURCE_JSON, ResourceMediaType.BEAN_DATA_SOURCE_XML,
		ResourceMediaType.CUSTOM_DATA_SOURCE_JSON, ResourceMediaType.CUSTOM_DATA_SOURCE_XML,
		ResourceMediaType.DATA_TYPE_JSON, ResourceMediaType.DATA_TYPE_XML, ResourceMediaType.FILE_JSON,
		ResourceMediaType.FILE_XML, ResourceMediaType.FOLDER_JSON, ResourceMediaType.FOLDER_XML,
		ResourceMediaType.INPUT_CONTROL_JSON, ResourceMediaType.INPUT_CONTROL_XML,
		ResourceMediaType.JDBC_DATA_SOURCE_JSON, ResourceMediaType.JDBC_DATA_SOURCE_XML,
		ResourceMediaType.JNDI_JDBC_DATA_SOURCE_JSON, ResourceMediaType.JNDI_JDBC_DATA_SOURCE_XML,
		ResourceMediaType.MONDRIAN_CONNECTION_JSON, ResourceMediaType.MONDRIAN_CONNECTION_XML,
		ResourceMediaType.MONDRIAN_XMLA_DEFINITION_JSON, ResourceMediaType.MONDRIAN_XMLA_DEFINITION_XML,
		ResourceMediaType.OLAP_UNIT_JSON, ResourceMediaType.OLAP_UNIT_XML, ResourceMediaType.QUERY_JSON,
		ResourceMediaType.QUERY_XML, ResourceMediaType.REPORT_UNIT_JSON, ResourceMediaType.REPORT_UNIT_XML,
		ResourceMediaType.SECURE_MONDRIAN_CONNECTION_JSON, ResourceMediaType.SECURE_MONDRIAN_CONNECTION_XML,
		ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_JSON, ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_XML,
		ResourceMediaType.VIRTUAL_DATA_SOURCE_JSON, ResourceMediaType.VIRTUAL_DATA_SOURCE_XML,
		ResourceMediaType.XMLA_CONNECTION_JSON, ResourceMediaType.XMLA_CONNECTION_XML,
		ResourceMediaType.RESOURCE_LOOKUP_JSON, ResourceMediaType.RESOURCE_LOOKUP_XML,
		"application/repository.dashboard+json", "application/repository.dashboard+xml",
		"application/repository.domainTopic+json", "application/repository.domainTopic+xml",
		ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_JSON, ResourceMediaType.SEMANTIC_LAYER_DATA_SOURCE_XML,
		"application/xml", "application/hal+json", "application/hal+xml", "text/json", "text/xml",
		"application/attributes.collection+json", "application/attributes.collection+xml" })
public class ClientQueryMapperProvider extends JacksonJaxbJsonProvider implements ContextResolver<ObjectMapper> {

	private final ObjectMapper mapper;

	public ClientQueryMapperProvider() {
		mapper = getObjectMapper();
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}

	private ObjectMapper getObjectMapper() {
		ObjectMapper mp = new ObjectMapper();
		mp.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

		JaxbAnnotationModule jaxbModule = new JaxbAnnotationModule();
		mp.registerModule(jaxbModule);
		mp.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mp.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
		mp.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);

		mp.enable(SerializationFeature.INDENT_OUTPUT);
		mp.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mp.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

		mp.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return mp;
	}

}
