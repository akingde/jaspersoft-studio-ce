/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.json;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.data.designer.tree.NodeBoldStyledLabelProvider.CustomStyleStatus;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.utils.ResourceManager;

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
