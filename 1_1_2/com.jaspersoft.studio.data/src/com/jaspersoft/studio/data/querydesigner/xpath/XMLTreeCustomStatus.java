package com.jaspersoft.studio.data.querydesigner.xpath;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider.CustomStyleStatus;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * Enumeration for custom states of the treeviewer containing
 * the XML document representation.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
enum XMLTreeCustomStatus implements CustomStyleStatus {
	LOADING_XML("Loading XML data...","icons/waiting.gif"),
	ERROR_LOADING_XML("Error loading the XML file.", "icons/error.gif"),
	FILE_NOT_FOUND("No file found.", "icons/warning.gif");
	
	private String message;
	private String imagePath;
	
	private XMLTreeCustomStatus(String message, String imagePath) {
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