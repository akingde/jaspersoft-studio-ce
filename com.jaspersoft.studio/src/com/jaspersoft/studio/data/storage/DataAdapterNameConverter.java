package com.jaspersoft.studio.data.storage;

import java.io.File;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.IConversionFilenameProvider;

public class DataAdapterNameConverter implements IConversionFilenameProvider {

	public String iterateForUniqueName(String baseName){
		String fileName = baseName.replaceAll(" ", "_") + ".xml";
		File storage = ConfigurationManager.getStorage(PreferencesDataAdapterStorage.PREF_KEYS_DATA_ADAPTERS);
		File testName = new File(storage, fileName);
		int counter = 0;
		while(testName.exists()){
			fileName = baseName + "_" + String.valueOf(counter) + ".xml";
			counter++;
			testName = new File(storage, fileName);
		}
		return fileName;
	}
	
	@Override
	public String getFileName(Node configurationElementNode) {
		NodeList adapterNode = configurationElementNode.getChildNodes();
		for (int i = 0; i < adapterNode.getLength(); i++){
			Node adapterChild = adapterNode.item(i);
			if (adapterChild.getNodeName().equals("name") && !adapterChild.getTextContent().isEmpty()){
				return iterateForUniqueName(adapterChild.getTextContent());
			}
		}
		//The data adapter has not a name tag (use the type as base name)
		return iterateForUniqueName(configurationElementNode.getNodeName());
	}
	

}
