package com.jaspersoft.studio.data.cassandra.cql3.messages;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.translation.resources.AbstractResourceDefinition;
import com.jaspersoft.translation.resources.PackageResourceDefinition;

/**
 * 
 * @author Raul Gallegos
 * 
 */
public class ResourcePublisher extends com.jaspersoft.studio.messages.ResourcePublisher{

	@Override
	public String getPluginName() {
		return "com.jaspersoft.studio.data.cassandra.cql3";
	}

	protected List<AbstractResourceDefinition> initializeProperties(){
		List<AbstractResourceDefinition> result = new ArrayList<AbstractResourceDefinition>();
		result.add(new PackageResourceDefinition("en_EN", 
												 "com.jaspersoft.studio.data.cassandra.cql3.messages", 
												 "messages.properties", 
												 "In this file there are the strings used by the CassandraCQL3 data adapter",
												 getClassLoader(),
												 "com/jaspersoft/studio/data/cassandra/cql3/messages/messages.properties", this));
		
		propertiesCache.put(getPluginName(), result);
		return result;
	}


}
