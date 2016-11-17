/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.json;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider.CustomStyleStatus;
import com.jaspersoft.studio.data.messages.Messages;

/**
 * Enumeration for custom states of the treeviewer containing
 * the Json document representation.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum JsonTreeCustomStatus implements CustomStyleStatus{
	LOADING_JSON(Messages.JsonTreeCustomStatus_LoadingJsonMessage,"icons/waiting.gif"), //$NON-NLS-2$
	ERROR_LOADING_JSON(Messages.JsonTreeCustomStatus_ErrorLoadingMessage, "icons/error.gif"), //$NON-NLS-2$
	FILE_NOT_FOUND(Messages.JsonTreeCustomStatus_NoJsonMessage, "icons/warning.gif"); //$NON-NLS-2$
	
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
