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
		return "com.jaspersoft.studio.data.cassandra.cql3"; //$NON-NLS-1$
	}

	protected List<AbstractResourceDefinition> initializeProperties(){
		List<AbstractResourceDefinition> result = new ArrayList<AbstractResourceDefinition>();
		result.add(new PackageResourceDefinition("en_EN", //$NON-NLS-1$
												 "com.jaspersoft.studio.data.cassandra.cql3.messages", //$NON-NLS-1$ 
												 "messages.properties", //$NON-NLS-1$
												 Messages.ResourcePublisher_Description,
												 getClassLoader(),
												 "com/jaspersoft/studio/data/cassandra/cql3/messages/messages.properties", this)); //$NON-NLS-1$
		
		propertiesCache.put(getPluginName(), result);
		return result;
	}


}
