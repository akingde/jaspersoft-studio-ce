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
package com.jaspersoft.studio.components.crosstab.model.dialog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.crosstabs.design.JRCrosstabOrigin;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

import org.eclipse.gef.commands.CompoundCommand;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.style.ApplyStyleAction;
import com.jaspersoft.studio.model.style.command.CreateStyleCommand;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * This class can be used to apply a TemplateStyle (must be a crosstabStyle) to
 * a crosstab.
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class ApplyCrosstabStyleAction extends ApplyStyleAction {

	public ApplyCrosstabStyleAction(CrosstabStyle style, MCrosstab element) {
		super(style, element);
	}

	
	/**
	 * Return the correct style for a cell, checking if it the detail cell, an header cell, or a total cell
	 * @param crosstab the crosstab
	 * @param origin the cell origin
	 * @param styleList the list of the available styles
	 * @return the style to apply to the cell
	 */
    private JRDesignStyle getCellBackgroundColor(JRDesignCrosstab crosstab, JRCrosstabOrigin origin, List<JRDesignStyle> styleList) {
        
        int c_index = -1;
        int r_index = -1;
        
        if (origin.getColumnGroupName() != null)
        {
            c_index = (Integer)crosstab.getColumnGroupIndicesMap().get(origin.getColumnGroupName());
            c_index = (crosstab.getColumnGroupsList().size()-1) - c_index;
        }
        if (origin.getRowGroupName() != null)
        {
            r_index = (Integer)crosstab.getRowGroupIndicesMap().get(origin.getRowGroupName());
            r_index = (crosstab.getRowGroupsList().size()-1) - r_index;
        }
        
        int groupIndex = Math.max(c_index, r_index);
        
        //groupRowName and groupColName are both null, so it is a detail cell
        if (groupIndex < 0) 
        	return styleList.get(3);
        groupIndex = Math.min(groupIndex, 1);
        switch (origin.getType())
        {
        	
            case JRCrosstabOrigin.TYPE_DATA_CELL:
            {
                return styleList.get(groupIndex+1);
            }
            case JRCrosstabOrigin.TYPE_ROW_GROUP_HEADER:
            case JRCrosstabOrigin.TYPE_COLUMN_GROUP_HEADER:
            {
                return styleList.get(0);
            }
            case JRCrosstabOrigin.TYPE_ROW_GROUP_TOTAL_HEADER:
            case JRCrosstabOrigin.TYPE_COLUMN_GROUP_TOTAL_HEADER:
            {
                return styleList.get(groupIndex+1);
            }
            
        }    
        return null;
    }
	
    /**
     * Apply the correct style to every cell in the crosstab
     * 
     * @param design the jasper design
     */
	@Override
	public void applayStyle(JasperDesign design) {
		JRDesignCrosstab crosstab = (JRDesignCrosstab)getElement().getValue();
		List<JRDesignCellContents> contents = ModelUtils.getAllCells(crosstab);
		List<JRDesignStyle> styles = createStyles(design);
	    for (JRDesignCellContents content : contents)
	    {
	        if (content == null) continue;
	        JRDesignStyle style = getCellBackgroundColor(crosstab, content.getOrigin(), styles);
            if (style != null)
            {
            	content.setStyle(style);
                content.setMode( ModeEnum.OPAQUE);
                //Set the text white if the background color its color is too similar to the background
                Color backGround = style.getBackcolor();                
                int luminance = (30*backGround.getRed() + 59*backGround.getGreen() + 11*backGround.getBlue())/255;
                if (luminance < 50 ) 
                {
                    JRElement[] elements = content.getElements();
                    for (int i=0; i<elements.length; ++i)
                    {
                        if (elements[i] instanceof JRDesignTextElement)
                        {
                            ((JRDesignTextElement)elements[i]).setForecolor(Color.WHITE);
                        }
                    }
                }
            }
	    }
	}

	/**
	 * Starting from a CrosstabStyle it generate a list of styles that will be applied to the crosstab.
	 * For every style generated will be executed an addCommand to add them to the report
	 * 
	 * @param jd the jasperdesign
	 * @return a list of style that can be applied to the crosstab
	 */
	@Override
	public List<JRDesignStyle> createStyles(JasperDesign jd) 
	{
		CompoundCommand commands = new CompoundCommand();
		
		CrosstabStyle style = (CrosstabStyle)getStyle();
		String baseName = "Crosstab";
		for (int i = 0;; i++) {
			String name = baseName;
			if (i > 0) {
				name = baseName + " " + i;
			}
	
			if (!(jd.getStylesMap().containsKey(name+"_CH"))) {
				baseName = name;
				break;
			}
		}
		
		float gridSize = style.isShowGrid() ? 0.5f : 0f;
		
	    List<JRDesignStyle> styles = new ArrayList<JRDesignStyle>();
	    JRDesignStyle tableHeaderStyle=  new JRDesignStyle();
	    tableHeaderStyle.setName(baseName + "_CH");
	    setBorderWidth(tableHeaderStyle, gridSize);
	    
	    if (style.getWhiteGrid()) setBorderColor(tableHeaderStyle,Color.white);
	    else setBorderColor(tableHeaderStyle,Color.black);
	    
	    tableHeaderStyle.setMode(ModeEnum.OPAQUE);
	    tableHeaderStyle.setBackcolor(style.getColorValue(CrosstabStyle.COLOR_MEASURES));
	
	    commands.add(new CreateStyleCommand(jd, tableHeaderStyle));
	    styles.add(tableHeaderStyle);
	    
	    JRDesignStyle groupStyle =  new JRDesignStyle();
	    groupStyle.setName(baseName + "_CG");
	    setBorderWidth(groupStyle, gridSize);
	
	    if (style.getWhiteGrid()) setBorderColor(groupStyle,Color.white);
	    else setBorderColor(groupStyle,Color.black);
	
	    groupStyle.setMode(ModeEnum.OPAQUE);
	    groupStyle.setBackcolor(style.getColorValue(CrosstabStyle.COLOR_GROUP));
	
	    commands.add(new CreateStyleCommand(jd, groupStyle));
	    styles.add(groupStyle);
	
	    JRDesignStyle columnHeaderStyle=  new JRDesignStyle();
	    columnHeaderStyle.setName(baseName + "_ColH");
	    setBorderWidth(columnHeaderStyle, gridSize);
	
	    if (style.getWhiteGrid()) setBorderColor(columnHeaderStyle,Color.white);
	    else setBorderColor(columnHeaderStyle,Color.black);
	
	    columnHeaderStyle.setMode(ModeEnum.OPAQUE);
	    columnHeaderStyle.setBackcolor(style.getColorValue(CrosstabStyle.COLOR_TOTAL));
	
	    commands.add(new CreateStyleCommand(jd, columnHeaderStyle));
	    styles.add(columnHeaderStyle);
	    
	    JRDesignStyle cellStyle=  new JRDesignStyle();
	    cellStyle.setName(baseName + "_CD");
	    setBorderWidth(cellStyle, gridSize);
	
	    if (style.getWhiteGrid()) setBorderColor(cellStyle,Color.white);
	    else setBorderColor(cellStyle,Color.black);
	
	    cellStyle.setMode(ModeEnum.OPAQUE);
	    cellStyle.setBackcolor(style.getColorValue(CrosstabStyle.COLOR_DETAIL));
	
	    commands.add(new CreateStyleCommand(jd, cellStyle));
	    styles.add(cellStyle);
	    
	    commands.execute();
	
	    return styles;
	}

}
