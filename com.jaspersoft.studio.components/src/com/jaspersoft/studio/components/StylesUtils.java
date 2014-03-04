/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components;

import java.util.List;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.crosstabs.design.JRCrosstabOrigin;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import com.jaspersoft.studio.utils.ModelUtils;

/**
 * 
 * Utility classis to extract the styles used by a table or a crosstab
 * 
 * @author Orlandin Marco
 *
 */
public class StylesUtils {

	/**
	 * Extract the list of styles actually used on the table 
	 * 
	 * @return the list of styles actually used in the cells of the table in this order 
	 * null (for retrocompatibility), Table Header, Column Header and Detail.
	 */
	public static JRDesignStyle[] getStylesFromTable(JRDesignComponentElement table){
		StandardTable jrTable = getStandardTable(table);
		List<BaseColumn> columns = TableUtil.getAllColumns(jrTable);
    	JRDesignStyle[] stylesArray = new JRDesignStyle[4];
    	if (columns.size()>0){
    		BaseColumn standardCol = columns.get(0);
    		if (standardCol.getColumnFooter() != null) stylesArray[2] = (JRDesignStyle)standardCol.getColumnFooter().getStyle();
    		if (standardCol.getColumnHeader() != null) stylesArray[2] = (JRDesignStyle)standardCol.getColumnFooter().getStyle();
    		if (standardCol.getTableHeader() != null) stylesArray[1] = (JRDesignStyle)standardCol.getTableHeader().getStyle();
    		if (standardCol.getTableFooter() != null) stylesArray[1] = (JRDesignStyle)standardCol.getTableFooter().getStyle();
    		if (standardCol instanceof StandardColumn){
				DesignCell detCell = (DesignCell) ((StandardColumn)standardCol).getDetailCell();
				if (detCell != null)  stylesArray[3] = (JRDesignStyle)detCell.getStyle();
    		}
    	}
	    return stylesArray;
	}
	
    /**
     * Extract the standard table from a JRDesignComponentElement
     * 
     * @param element an element with static type JRElement and dynamic type JRDesignComponentElement
     * @return a StandardTable
     */
	private static StandardTable getStandardTable(JRElement element) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) element;
		StandardTable jrTable = (StandardTable) jrElement.getComponent();
		return jrTable;
	}
	
	/**
	 * Extract the list of styles actually used on the crosstab 
	 * 
	 * @return the list of styles actually used in the cells of the crosstab in this order 
	 * crosstab header, group, total and detail
	 */
	public static JRDesignStyle[] getStylesFromCrosstab(JRDesignCrosstab crosstab){
    	List<JRDesignCellContents> contents = ModelUtils.getAllCells(crosstab);
    	JRDesignStyle[] stylesArray = new JRDesignStyle[4];
	    for (JRDesignCellContents content : contents)
	    {
	        if (content == null) continue;
	        JRStyle actualStyle = content.getStyle();
	        if (actualStyle != null && actualStyle instanceof JRDesignStyle){
	        	int index = getBackgroundIndex(crosstab, content.getOrigin());
	        	if (index != -1) stylesArray[index] = (JRDesignStyle)actualStyle;
	        }
	    }
	    return stylesArray;
	}
	
	/**
     * Return the index of the style that will be applied to a cell of the crosstab
     * 
     * @param crosstab the crosstab
     * @param origin the origin
     * @return the index of the style for the cell where 0=crosstab header, 1=group, 2=total and 3=detail
     */
    private static int getBackgroundIndex(JRDesignCrosstab crosstab, JRCrosstabOrigin origin) {
        
        int c_index = -1;
        int r_index = -1;
        
        if (origin.getColumnGroupName() != null)
        {
            c_index = (Integer)crosstab.getColumnGroupIndicesMap().get(origin.getColumnGroupName());
            //c_index = (crosstab.getColumnGroupsList().size()-1) - c_index;
        }
        if (origin.getRowGroupName() != null)
        {
            r_index = (Integer)crosstab.getRowGroupIndicesMap().get(origin.getRowGroupName());
            //r_index = (crosstab.getRowGroupsList().size()-1) - r_index;
        }
        
        int groupIndex = Math.max(c_index, r_index);
        
        //groupRowName and groupColName are both null, so it is a detail cell
        if (groupIndex < 0) 
        	return 3;
        //groupIndex = Math.min(groupIndex, 1);
        groupIndex = (c_index == 0) || (r_index == 0) ? 1 : 0;
        switch (origin.getType())
        {
        	
            case JRCrosstabOrigin.TYPE_DATA_CELL:
            {
                return groupIndex+1;
            }
            case JRCrosstabOrigin.TYPE_ROW_GROUP_HEADER:
            case JRCrosstabOrigin.TYPE_COLUMN_GROUP_HEADER:
            {
                return 0;
            }
            case JRCrosstabOrigin.TYPE_ROW_GROUP_TOTAL_HEADER:
            case JRCrosstabOrigin.TYPE_COLUMN_GROUP_TOTAL_HEADER:
            {
                return groupIndex+1;
            }
            
        }    
        return -1;
    }
	
}
