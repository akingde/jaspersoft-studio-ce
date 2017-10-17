/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValuesMap;
import com.jaspersoft.studio.property.section.style.inerithance.StylesListSection;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.JREnum;
import net.sf.jasperreports.engine.type.ModeEnum;

/**
 * Section used to show the inheritance view for the crosstab and table
 * cells
 * 
 * @author Orlandin Marco
 *
 */
public class CellStylesListSection extends StylesListSection {

	/**
	 * Map of the default values
	 */
	private static HashMap<String, Object> defaultValues = null;
	
	/**
	 * Return the parent style of the selected cell
	 * 
	 * @param element an element, should be a crosstab or table cell
	 * @return the parent style of the parameter or null if it has not a parent style.
	 * It will be a String if the parent style is external or a JRStyle if it is internal.
	 */
	@Override
	protected Object getElementStyle(APropertyNode element){
		if (element instanceof MCell){
			MCell cell = (MCell)element;
			if (cell.getCell() != null) return cell.getCell().getStyle();
			else return null;
		} else if (element instanceof com.jaspersoft.studio.components.crosstab.model.cell.MCell){
			JRDesignCellContents jrElement = (JRDesignCellContents)element.getValue();
			if (jrElement != null){
				return jrElement.getStyle();
			} else return null;
		} else return super.getElementStyle(element);
	}
	
	/**
	 * Since the table cell has not the mode attribute by its own fake it to
	 * show that by default it is transparent
	 */
	@Override
	protected void printDefaultObject(String fullPropertyName, Object value, Composite parent) {
		if (fullPropertyName.equals(JRDesignStyle.PROPERTY_MODE)){
			JREnum enumValue = (JREnum) value;
			printLabels(parent, Messages.MCell_transparent, enumValue.getName(), 
						isOverridden(fullPropertyName), Messages.MCell_transparent_description);
		} else super.printDefaultObject(fullPropertyName, value, parent);
	}
	
	/**
	 * Return the default values map for the crosstab and table cells
	 */
	@Override
	protected Map<String, Object> getDefaultValues(){
		if (defaultValues == null){
			defaultValues = new LinkedHashMap<String, Object>();
			defaultValues.put(JRDesignStyle.PROPERTY_MODE, ModeEnum.TRANSPARENT);
			DefaultValuesMap.createBaseLineBox(defaultValues, MCell.LINE_BOX);
		}
		return defaultValues;
	}

}
