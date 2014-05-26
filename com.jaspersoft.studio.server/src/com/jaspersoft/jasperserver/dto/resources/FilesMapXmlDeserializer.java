package com.jaspersoft.jasperserver.dto.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class FilesMapXmlDeserializer extends JsonDeserializer<Map<String, ClientReferenceableFile>> {

	@Override
	public Map<String, ClientReferenceableFile> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		Map<String, ClientReferenceableFile> map = new HashMap<String, ClientReferenceableFile>();
		ObjectCodec oc = jsonParser.getCodec();

		Iterator<ClientFile> it = oc.readValues(jsonParser, ClientFile.class);
		for (; it.hasNext();) {
			ClientFile n = it.next();
			if (n.getLabel() != null)
				map.put(n.getLabel(), n);
		}

		// JsonNode node = oc.readTree(jsonParser);

		// for (Iterator<JsonNode> it = node.elements(); it.hasNext();) {
		// JsonNode n = it.next();n.fields()
		// map.put(n.asText(), (ClientReferenceableFile) n.get("fileResource"));
		// }
		//
		// ObjectCodec oc = jsonParser.getCodec();
		// JsonNode node = oc.readTree(jsonParser);
		// return new User(null, node.get("username").getTextValue(),
		// node.get("password").getTextValue());
		// List<ClientReportUnitResource> result = null;
		// if (files != null) {
		// result = new ArrayList<ClientReportUnitResource>();
		// for (String name : files.keySet()) {
		// result.add(new ClientReportUnitResource(name, files.get(name)));
		// }
		// }
		return map;
	}
}