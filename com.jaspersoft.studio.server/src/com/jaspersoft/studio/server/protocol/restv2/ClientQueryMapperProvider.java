/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
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

	private static ObjectMapper mapper;

	public ClientQueryMapperProvider() {
		super(getObjectMapper(), DEFAULT_ANNOTATIONS);
	}

	public ClientQueryMapperProvider(Annotations... annotationsToUse) {
		super(getObjectMapper(), annotationsToUse);
	}

	public ClientQueryMapperProvider(ObjectMapper mapper, Annotations[] annotationsToUse) {
		super(mapper, annotationsToUse);
	}

	public static ObjectMapper getObjectMapper() {
		if (mapper == null) {
			synchronized (ClientQueryMapperProvider.class) {
				if (mapper == null) {
					mapper = new ObjectMapper();
					AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
					AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
					AnnotationIntrospector pair = AnnotationIntrospector.pair(primary, secondary);
					mapper.setAnnotationIntrospector(pair);
					// Serialize dates using ISO8601 format
					// Jackson uses timestamps by default, so use StdDateFormat to get ISO8601
					mapper.setDateFormat(new StdDateFormat());
					// Prevent exceptions from being thrown for unknown properties
					mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					// Use XML wrapper name as JSON property name
					mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
					mapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true);
					// ignore fields with null values
					mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				}
			}
		}
		return mapper;
	}

	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return getObjectMapper();
	}

}
