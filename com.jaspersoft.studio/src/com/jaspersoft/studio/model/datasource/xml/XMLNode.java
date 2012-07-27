/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.datasource.xml;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * ANode representing an XML document node.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XMLNode extends ANode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String nodeName;
	private String xpathExpression;

	public ImageDescriptor getImagePath() {
		return ResourceManager.getPluginImageDescriptor(
				JaspersoftStudioPlugin.PLUGIN_ID,"icons/resources/element_obj.gif");
	}

	public String getDisplayText() {
		return this.nodeName;
	}

	public String getName(){
		return this.nodeName;
	}
	
	public void setName(String nodeName){
		this.nodeName=nodeName;
	}
	
	public String getXPathExpression(){
		return this.xpathExpression;
	}
	
	public void setXPathExpression(String xpathExp){
		this.xpathExpression=xpathExp;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if(adapter==JRDesignField.class || adapter==JRField.class){
			JRDesignField field=new JRDesignField();
			field.setName(nodeName);
			field.setDescription(xpathExpression);
			field.setValueClass(String.class);
			return field;
		}
		return super.getAdapter(adapter);
	}
	
}
