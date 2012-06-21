package com.jaspersoft.studio.data.querydesigner.json;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider.CustomStyleStatus;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * Enumeration for custom states of the treeviewer containing
 * the Json document representation.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
enum JsonTreeCustomStatus implements CustomStyleStatus{
	LOADING_JSON("Loading Json data...","icons/waiting.gif"),
	ERROR_LOADING_JSON("Error loading the Json file.", "icons/error.gif"),
	FILE_NOT_FOUND("No Json file found.", "icons/warning.gif");
	
	private String message;
	private String imagePath;
	
	private JsonTreeCustomStatus(String message, String imagePath) {
		this.message=message;
		this.imagePath=imagePath;
	}				
	
	public String getMessage(){
		return this.message;
	}
	
	public Image getImage() {
		return ResourceManager.getPluginImage(Activator.PLUGIN_ID,imagePath);
	}

}
