/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.path;

import net.sf.jasperreports.components.map.ItemProperty;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.model.itemdata.ElementDataHelper;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementDTO;
import com.jaspersoft.studio.components.map.model.itemdata.dto.MapDataElementItemDTO;

/**
 * Label Provider for a viewer exposing the Path Datas of the map.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class PathsLabelProvider extends LabelProvider {
	private static final String LATITUDE_ATTRIBUTE = "latitude";
	private static final String LONGITUDE_ATTRIBUTE = "longitude";
	
	@Override
	public String getText(Object element) {
		if(element instanceof MapDataElementDTO) {
			ItemProperty name = ((MapDataElementDTO) element).getName();
			if(name!=null){
				return "Path " + ElementDataHelper.getItemPropertyValueAsString(name);
			}
			else {
				return "Path " + ElementDataHelper.DEFAULT_ELEMENT_NAME;
			}
		}
		else if(element instanceof MapDataElementItemDTO) {
			// Extract coordinates: latitude and longitude are mandatory attributes
			String lat=ElementDataHelper.getItemPropertyValueAsString(((MapDataElementItemDTO) element).getItem(), LATITUDE_ATTRIBUTE);
			String lon=ElementDataHelper.getItemPropertyValueAsString(((MapDataElementItemDTO) element).getItem(), LONGITUDE_ATTRIBUTE);
			return NLS.bind("Item at <{0},{1}>",lat,lon);
		}
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof MapDataElementDTO){
			return Activator.getDefault().getImage("/icons/path-icon-16.png");
		}
		else if(element instanceof MapDataElementItemDTO){
			return Activator.getDefault().getImage("/icons/pathitem-icon-16.png");
		}
		return super.getImage(element);
	}
}
