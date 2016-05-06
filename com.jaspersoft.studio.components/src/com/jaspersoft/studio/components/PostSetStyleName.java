/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.property.IPostSetValue;
import com.jaspersoft.studio.property.SetPropertyValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/**
 * Class used when a property is changed. Check if the changed property is a name 
 * of a style and in this search for table using that style as default and update their name
 * if found
 * 
 * @author Orlandin Marco
 */
public class PostSetStyleName implements IPostSetValue {
	
	/**
	 * Recursively iterate the current element and all its children to search a table with a property
	 * of a default style that referenced the changed style. If found update the property value to
	 * reference the correct name
	 * 
	 * @param currentNode the current node of the iteration
	 * @param cc container where the commands to update the tables properties are added
	 * @param oldName the old style name
	 * @param newName the new style name
	 */
	private void searchTable(INode currentNode, JSSCompoundCommand cc, String oldName, String newName){
		if (currentNode == null) return;
		if (currentNode instanceof MTable){
			JRPropertiesMap tableMap = ((MTable)currentNode).getPropertiesMap();
			
			String styleName = tableMap.getProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY);
			if (ModelUtils.safeEquals(styleName, oldName)) {
				cc.add(new SetPropertyValueCommand(tableMap, ApplyTableStyleAction.TABLE_HEADER_PROPERTY, newName));
			}

			styleName = tableMap.getProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY);
			if (ModelUtils.safeEquals(styleName, oldName)) {
				cc.add(new SetPropertyValueCommand(tableMap, ApplyTableStyleAction.COLUMN_HEADER_PROPERTY, newName));
			}
			
			styleName = tableMap.getProperty(ApplyTableStyleAction.DETAIL_PROPERTY);
			if (ModelUtils.safeEquals(styleName, oldName)) {
				cc.add(new SetPropertyValueCommand(tableMap, ApplyTableStyleAction.DETAIL_PROPERTY, newName));
			}
		}

		for(INode child : currentNode.getChildren()){
			searchTable(child, cc, oldName, newName);
		}
	}
	
	/**
	 * Generate a series of command to update the property of tables that reference the style
	 * whose name was changed
	 */
	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		JSSCompoundCommand cc = new JSSCompoundCommand(null);
		cc.setReferenceNodeIfNull(target);
		//Check if the updated element is a dataset and the updated property is the name
		if (target instanceof MStyle && prop.equals(JRDesignStyle.PROPERTY_NAME)) {
			searchTable(((MStyle)target).getRoot(), cc, oldValue.toString(), newValue.toString());
		}
		return cc;
	}

}
