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
package com.jaspersoft.studio.components.table.model.dialog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.editor.style.ApplyStyleAction;
import com.jaspersoft.studio.model.style.command.CreateStyleCommand;
import com.jaspersoft.studio.utils.ModelUtils;

public class ApplyTableStyleAction extends ApplyStyleAction {
	
	public ApplyTableStyleAction(TableStyle style, MTable table){
		super(style, table);
	}

	@Override
	public void applayStyle(JasperDesign design) 
	{	
		List<JRDesignStyle> styleList = createStyles(design); 
		StandardTable table = TableManager.getTable(getElement());
		List<BaseColumn> columns = TableUtil.getAllColumns(table);
		for(BaseColumn col : columns){
			// Set the cel color
			DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
			if (colHeadCell != null)
				colHeadCell.setStyle(styleList.get(2));
			DesignCell tblHeadCell = (DesignCell) col.getTableHeader();
			if (tblHeadCell != null)
				tblHeadCell.setStyle(styleList.get(1));
			DesignCell tblFooterCell = (DesignCell) col.getTableFooter();
			if (tblFooterCell != null)
				tblFooterCell.setStyle(styleList.get(1));
			DesignCell colFooterCell = (DesignCell) col.getColumnFooter();
			if (colFooterCell != null)
				colFooterCell.setStyle(styleList.get(2));
			if (col instanceof StandardColumn){
				DesignCell detCell = (DesignCell) ((StandardColumn)col).getDetailCell();
				if (detCell != null) detCell.setStyle(styleList.get(3));
			}
			// Color setted
		}
	}

	/**
	 * Starting from a TableStyle it generate a list of styles that will be applied to the table.
	 * For every style generated will be executed an addCommand to add them to the report
	 * 
	 * @param jd the jasperdesign
	 * @param style the TableStyle from where all the styles for the table will be generated
	 * @return a list of style that can be applied to the table
	 */
	@Override
	public List<JRDesignStyle> createStyles(JasperDesign jd) 
	{
    	String baseName = "Table";
    	TableStyle style = (TableStyle)getStyle();
    	CompoundCommand commands = new CompoundCommand();
		for (int i = 0;; i++) {
			String name = baseName;
			if (i > 0) {
				name = baseName + " " + i;
			}

			if (!(jd.getStylesMap().containsKey(name))) {
				baseName = name;
				break;
			}
		}
    	
        List<JRDesignStyle> styles = new ArrayList<JRDesignStyle>();

        JRDesignStyle tableStyle=  new JRDesignStyle();
        tableStyle.setName(baseName);

        if (style.getBorderStyle() == BorderStyleEnum.FULL || style.getBorderStyle() == BorderStyleEnum.PARTIAL_VERTICAL)
        {
            setBorderColor(tableStyle, style.getBorderColor());
            setBorderWidth(tableStyle, 1.0f);
        }
        else
        {
            tableStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            tableStyle.getLineBox().getTopPen().setLineWidth(1.0f);
            tableStyle.getLineBox().getBottomPen().setLineColor(style.getBorderColor());
            tableStyle.getLineBox().getBottomPen().setLineWidth(1.0f);
        }

        commands.add(new CreateStyleCommand(jd, tableStyle));
        styles.add(tableStyle);

        JRDesignStyle tableHeaderStyle=  new JRDesignStyle();
        tableHeaderStyle.setName(baseName + "_TH");

        if (style.getBorderStyle() == BorderStyleEnum.FULL)
        {
            setBorderColor(tableHeaderStyle, style.getBorderColor());
            setBorderWidth(tableHeaderStyle, 0.5f);
        }
        else
        {
            tableHeaderStyle.getLineBox().getBottomPen().setLineColor(style.getBorderColor());
            tableHeaderStyle.getLineBox().getBottomPen().setLineWidth(0.5f);
            tableHeaderStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            tableHeaderStyle.getLineBox().getTopPen().setLineWidth(0.5f);
        }

        tableHeaderStyle.setMode(ModeEnum.OPAQUE);
        tableHeaderStyle.setBackcolor(style.getColorValue(TableStyle.COLOR_TABLE_HEADER));

        commands.add(new CreateStyleCommand(jd, tableHeaderStyle));
        styles.add(tableHeaderStyle);

        JRDesignStyle columnHeaderStyle=  new JRDesignStyle();
        columnHeaderStyle.setName(baseName + "_CH");

        if (style.getBorderStyle() == BorderStyleEnum.FULL)
        {
            setBorderColor(columnHeaderStyle, style.getBorderColor());
            setBorderWidth(columnHeaderStyle, 0.5f);
        }
        else
        {
            columnHeaderStyle.getLineBox().getBottomPen().setLineColor(style.getBorderColor());
            columnHeaderStyle.getLineBox().getBottomPen().setLineWidth(0.5f);
            columnHeaderStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            columnHeaderStyle.getLineBox().getTopPen().setLineWidth(0.5f);
        }

        columnHeaderStyle.setMode(ModeEnum.OPAQUE);
        columnHeaderStyle.setBackcolor(style.getColorValue(TableStyle.COLOR_COL_HEADER));

        commands.add(new CreateStyleCommand(jd, columnHeaderStyle));
        styles.add(columnHeaderStyle);

        JRDesignStyle cellStyle=  new JRDesignStyle();
        cellStyle.setName(baseName + "_TD");

        if (style.getBorderStyle() == BorderStyleEnum.FULL)
        {
            setBorderColor(cellStyle, style.getBorderColor());
            setBorderWidth(cellStyle, 0.5f);
        }
        else
        {
            cellStyle.getLineBox().getBottomPen().setLineColor(style.getBorderColor());
            cellStyle.getLineBox().getBottomPen().setLineWidth(0.5f);
            cellStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            cellStyle.getLineBox().getTopPen().setLineWidth(0.5f);
        }

        cellStyle.setMode(ModeEnum.OPAQUE);
        cellStyle.setBackcolor(Color.WHITE);


        if (style.hasAlternateColor())
        {
            JRDesignConditionalStyle condStyle = new JRDesignConditionalStyle();
            condStyle.setConditionExpression(ModelUtils.createExpression("new Boolean($V{REPORT_COUNT}.intValue()%2==0)"));
            condStyle.setBackcolor(style.getColorValue(TableStyle.COLOR_DETAIL));
            cellStyle.addConditionalStyle(condStyle);
        }

        commands.add(new CreateStyleCommand(jd, cellStyle));
        styles.add(cellStyle);
        commands.execute();

        return styles;
	}

}
