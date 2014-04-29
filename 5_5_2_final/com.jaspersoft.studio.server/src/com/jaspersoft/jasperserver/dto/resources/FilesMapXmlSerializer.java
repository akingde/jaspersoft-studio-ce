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
