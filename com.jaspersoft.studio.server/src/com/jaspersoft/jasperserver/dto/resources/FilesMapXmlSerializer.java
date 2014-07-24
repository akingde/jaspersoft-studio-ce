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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class FilesMapXmlSerializer extends JsonSerializer<Map<String, ClientReferenceableFile>> {
	@Override
	public void serialize(Map<String, ClientReferenceableFile> value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		List<ClientReportUnitResource> result = new ArrayList<ClientReportUnitResource>();
		for (String name : value.keySet())
			result.add(new ClientReportUnitResource(name, value.get(name)));
		provider.defaultSerializeValue(result, generator);
	}
}
