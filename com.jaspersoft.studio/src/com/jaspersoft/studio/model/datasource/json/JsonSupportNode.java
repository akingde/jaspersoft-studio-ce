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
package com.jaspersoft.studio.model.datasource.json;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ResourceManager;

public class JsonSupportNode extends ANode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String nodeText;
	private String expression;

	public ImageDescriptor getImagePath() {
		return ResourceManager.getPluginImageDescriptor(
				JaspersoftStudioPlugin.PLUGIN_ID,"icons/resources/element_obj.gif");
	}

	public String getDisplayText() {
		return this.nodeText;
	}

	public Image getImage(){
		return ResourceManager.getImage(getImagePath());
	}
	
	public String getNodeText() {
		return nodeText;
	}

	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}

	public String getExpression(){
		return this.expression;
	}
	
	public void setExpression(String expression){
		this.expression=expression;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if(adapter==JRDesignField.class || adapter==JRField.class){
			JRDesignField field=new JRDesignField();
			field.setName(nodeText);
			field.setDescription(expression);
			field.setValueClass(String.class);
			return field;
		}
		return super.getAdapter(adapter);
	}
}
