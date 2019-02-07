/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.editor.style.ApplyStyleAction;
import com.jaspersoft.studio.model.style.command.CreateConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.CreateStyleCommand;
import com.jaspersoft.studio.model.style.command.DeleteConditionalStyleCommand;
import com.jaspersoft.studio.model.style.command.UpdateStyleCommand;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.GroupCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

/**
 * class to apply a TemplateStyle (with type TableStyle) to a Table or to update it 
 * 
 * @author Orlandin Marco
 *
 */
public class ApplyTableStyleAction extends ApplyStyleAction {
	
	/**
	 * Property store in the properties map of the table where the value is the default style
	 * name of the style to be used in the table header/ footer
	 */
	public static final String TABLE_HEADER_PROPERTY = "com.jaspersoft.studio.table.style.table_header";

	/**
	 * Property store in the properties map of the table where the value is the default style
	 * name of the style to be used in the column header/ footer
	 */
	public static final String COLUMN_HEADER_PROPERTY = "com.jaspersoft.studio.table.style.column_header";
	
	/**
	 * Property store in the properties map of the table where the value is the default style
	 * name of the style to be used in the detail
	 */
	public static final String DETAIL_PROPERTY = "com.jaspersoft.studio.table.style.detail";
	
	public static final String ALT_ROW_EXP = "new Boolean($V{REPORT_COUNT}.intValue()%2==0)";
	
	/**
	 * Styles that will be applied to the table
	 */
	private List<JRDesignStyle> styles = null;
	
	/**
	 * Build the class
	 * 
	 * @param style the table style used to generate the styles
	 * @param table the table to witch the styles will be applied
	 */
	public ApplyTableStyleAction(TableStyle style, JRElement table){
		super(style, table);
	}
	
	/**
	 * Build the class, instead to build the styles from a TableStyle
	 * it receive directly the list of styles to apply the table
	 * 
	 * @param styles list of styles that will be applied on the table, the order is important
	 * and it should be: Table Style, Table Header, Column Header and Detail.
	 * @param table the table to witch the styles will be applied
	 */
	public ApplyTableStyleAction(List<JRDesignStyle> styles, JRElement table){
		super(null, table);
		this.styles = styles;
	}

	/**
	 * Generate the styles and apply them to the cells of the table
	 * 
	 * @param design the jasperdesign
	 */
	@Override
	public void applayStyle(JasperDesign design) 
	{	
		List<JRDesignStyle> styleList = createStyles(design); 
		setCellStyles(styleList);
	}
	
	/**
	 * 
	 * Apply the list of styles to the cell of the table. The styles are first set to null and then at
	 * the style value, to force a graphical update (the style are not update if the name is the same)
	 * 
	 * @param styleList list of styles that will be applied on the table, the order is important
	 * and it should be: Table Style (unused), Table Header, Column Header and Detail. 
	 */
	private void setCellStyles(List<JRDesignStyle> styleList){
		StandardTable table = getStandardTable(getElement());
		
		//Bind the styles to the table properties
		JRPropertiesMap tableMap = getElement().getPropertiesMap();
		if (styleList.get(1) != null) tableMap.setProperty(TABLE_HEADER_PROPERTY, styleList.get(1).getName());
		if (styleList.get(2) != null) tableMap.setProperty(COLUMN_HEADER_PROPERTY, styleList.get(2).getName());
		if (styleList.get(3) != null) tableMap.setProperty(DETAIL_PROPERTY, styleList.get(3).getName());
		
		List<BaseColumn> columns = TableUtil.getAllColumns(table);
		for(BaseColumn col : columns){
			setColumnStyles(col, styleList);
		}
		
		for(BaseColumn baseCol : table.getColumns()){
			if (baseCol instanceof StandardColumnGroup){
				StandardColumnGroup columnGroup = (StandardColumnGroup)baseCol;
				setColumnStyles(columnGroup,styleList);
			}
		}
		
	}
	
	/**
	 * Set the styles on a set of columns
	 * 
	 * @param col column where the style should be set
	 * @param styleList list of styles that will be applied on the table, the order is important
	 * and it should be: Table Style (unused), Table Header, Column Header and Detail. 
	 */
	private void setColumnStyles(BaseColumn col, List<JRDesignStyle> styleList)
	{
		// Seting the cell style
		DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
		if (colHeadCell != null){
			colHeadCell.setStyle(null);
			colHeadCell.setStyle(styleList.get(2));
		}
		DesignCell tblHeadCell = (DesignCell) col.getTableHeader();
		if (tblHeadCell != null){
			tblHeadCell.setStyle(null);
			tblHeadCell.setStyle(styleList.get(1));
		}
		DesignCell tblFooterCell = (DesignCell) col.getTableFooter();
		if (tblFooterCell != null){
			tblFooterCell.setStyle(null);
			tblFooterCell.setStyle(styleList.get(1));
		}
		DesignCell colFooterCell = (DesignCell) col.getColumnFooter();
		if (colFooterCell != null){
			colFooterCell.setStyle(null);
			colFooterCell.setStyle(styleList.get(2));
		}
		if (col instanceof StandardColumn){
			DesignCell detCell = (DesignCell) ((StandardColumn)col).getDetailCell();
			if (detCell != null) {
				detCell.setStyle(null);
				detCell.setStyle(styleList.get(3));
			}
		}
		
		for(GroupCell cell : col.getGroupHeaders()){
			DesignCell grCell = (DesignCell) cell.getCell();
			if (grCell != null) {
				grCell.setStyle(null);
				grCell.setStyle(styleList.get(2));
			}
		}
		
		for(GroupCell cell : col.getGroupFooters()){
			DesignCell grCell = (DesignCell) cell.getCell();
			if (grCell != null) {
				grCell.setStyle(null);
				grCell.setStyle(styleList.get(2));
			}
		}
		
		// Style set
	}
	
	/**
	 * Extract the list of styles actually used on the table 
	 * 
	 * @return the list of styles actually used in the cells of the table in this order 
	 * null (for retrocompatibility), Table Header, Column Header and Detail.
	 */
	public JRDesignStyle[] getStylesFromTable(JasperDesign jd){
		StandardTable jrTable = getStandardTable(getElement());
		return getStylesFromTable(jrTable, getElement().getPropertiesMap(), jd);
	}
	
	
	/**
	 * Check if the passed map has one of the properties that bind the table to
	 * its default styles
	 * 
	 * @param tableMap
	 *            the properties map of the table
	 * @return true if the table map has one of the properties that reference
	 *         the default style, false otherwise
	 */
	protected static boolean hasStyleProperties(JRPropertiesMap tableMap) {
		return (tableMap.containsProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY)
				|| tableMap.containsProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY)
				|| tableMap.containsProperty(ApplyTableStyleAction.DETAIL_PROPERTY));
	}
	
	/**
	 * Extract the list of styles actually used on the table 
	 * 
	 * @param jrTable a not null table from where the styles are extracted
	 * @param tableMap the properties map of the current table
	 * @param the JasperDesign of the current report
	 * @return the list of styles actually used in the cells of the table in this order 
	 * null (for retrocompatibility), Table Header, Column Header and Detail.
	 * 
	 */
	public static JRDesignStyle[] getStylesFromTable(StandardTable jrTable, JRPropertiesMap tableMap, JasperDesign jd){
		JRDesignStyle[] stylesArray = new JRDesignStyle[4];
		if (hasStyleProperties(tableMap)){
			String tableHeaderStyle = tableMap.getProperty(TABLE_HEADER_PROPERTY);
			stylesArray[1] = (JRDesignStyle)jd.getStylesMap().get(tableHeaderStyle);
			String columnHeaderStyle = tableMap.getProperty(COLUMN_HEADER_PROPERTY);
			stylesArray[2] = (JRDesignStyle)jd.getStylesMap().get(columnHeaderStyle);
			String detailStyle = tableMap.getProperty(DETAIL_PROPERTY);
			stylesArray[3] = (JRDesignStyle)jd.getStylesMap().get(detailStyle);
		} else {
			List<BaseColumn> columns = TableUtil.getAllColumns(jrTable);
	    	if (columns.size()>0){
	    		BaseColumn standardCol = columns.get(0);
	    		if (standardCol.getColumnFooter() != null) stylesArray[2] = (JRDesignStyle)standardCol.getColumnFooter().getStyle();
	    		if (standardCol.getColumnHeader() != null) stylesArray[2] = (JRDesignStyle)standardCol.getColumnHeader().getStyle();
	    		if (standardCol.getTableHeader() != null) stylesArray[1] = (JRDesignStyle)standardCol.getTableHeader().getStyle();
	    		if (standardCol.getTableFooter() != null) stylesArray[1] = (JRDesignStyle)standardCol.getTableFooter().getStyle();
	    		if (standardCol instanceof StandardColumn){
					DesignCell detCell = (DesignCell) ((StandardColumn)standardCol).getDetailCell();
					if (detCell != null)  stylesArray[3] = (JRDesignStyle)detCell.getStyle();
	    		}
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
	private StandardTable getStandardTable(JRElement element) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) element;
		StandardTable jrTable = (StandardTable) jrElement.getComponent();
		return jrTable;
	}
	
	/**
	 * Update the style of the table with a new TableStyle
	 * 
	 * @param design the JasperDesign of the report
	 * @param newStyles the new style template for the table
	 * @param updateOldStyles true if the new styles will overwrite the old ones, false if the old ones will keep and 
	 * the new ones will have a different name and will be applied to the table with the different name
	 * @param removeOldStyles if updateOldStyles is false, after the new styles are created the old one are deleted. 
	 * if updateOldStyles is true this attribute is ignored
	 */
	public void updateStyle(JasperDesign design, TableStyle newStyles, boolean updatOldStyles, boolean removeOldStyles){
		updateStyle(design, createStyles(design, false), updatOldStyles, removeOldStyles);
	}
	

	/**
	 * Update the style of the table with a new list of styles
	 * 
	 * @param design the JasperDesign of the report
	 * @param newStyles list of styles that will be applied on the table, the order is important
	 * and it should be: Table Style, Table Header, Column Header and Detail.
	 * @param updateOldStyles true if the new styles will overwrite the old ones, false if the old ones will keep and 
	 * the new ones will have a different name and will be applied to the table with the different name
	 * @param removeOldStyles if updateOldStyles is false, after the new styles are created the old one are deleted
	 */
	public void updateStyle(JasperDesign design, List<JRDesignStyle> newStyles, boolean updatOldStyles, boolean removeOldStyles){
		if (updatOldStyles){
			JSSCompoundCommand commands = new JSSCompoundCommand(null);
			List<JRDesignStyle> stylesToApply = new ArrayList<JRDesignStyle>(newStyles);
			JRDesignStyle[] actualStyles = getStylesFromTable(design);
			for(int i=0; i<actualStyles.length; i++){
				JRDesignStyle style = actualStyles[i];
				if (style != null){
					JRDesignStyle updatedStyle = stylesToApply.get(i);
					updatedStyle.setName(style.getName());
					JRDesignStyle styleToUpdate = (JRDesignStyle)design.getStylesMap().get(style.getName());
					stylesToApply.set(i, styleToUpdate);
					if (styleToUpdate != null){
						commands.add(new UpdateStyleCommand(updatedStyle, styleToUpdate));
						if (i == 3) {
							for (JRConditionalStyle oldCondStyle : styleToUpdate.getConditionalStyleList()) {
								if (oldCondStyle.getConditionExpression() != null && ALT_ROW_EXP.equals(oldCondStyle.getConditionExpression().getText())) {
									commands.add(new DeleteConditionalStyleCommand(styleToUpdate, oldCondStyle));
									break;
								}
							}
							if (!updatedStyle.getConditionalStyleList().isEmpty()) {
								for (JRConditionalStyle newCondStyle : new ArrayList<JRConditionalStyle>(updatedStyle.getConditionalStyleList())) {
									updatedStyle.removeConditionalStyle(newCondStyle);
									commands.add(new CreateConditionalStyleCommand(styleToUpdate, newCondStyle));
								}
							}
						}
					} else {
						stylesToApply.set(i, null);
					}
				} else {
					stylesToApply.set(i, null);
				}
			}
			commands.execute();
			setCellStyles(stylesToApply);
		} else {
			JSSCompoundCommand commands = new JSSCompoundCommand(null);
			List<JRDesignStyle> stylesToApply = new ArrayList<JRDesignStyle>(newStyles);
			styles = stylesToApply;
			Map<String,JRStyle> stylesMap = design.getStylesMap();
			if (removeOldStyles){
				JRDesignStyle[] oldStyles = getStylesFromTable(design);
				for(JRDesignStyle style : oldStyles){
					if (style != null) design.removeStyle(style);
				}
			}
			for(JRDesignStyle style : stylesToApply){
				if (style != null && !stylesMap.containsKey(style.getName())) commands.add(new CreateStyleCommand(design, style));
			}
			commands.execute();
			setCellStyles(stylesToApply);
		}
	}
		
	/**
	 * Get a base name and check if  one the composed names of the single styles (basename + _TH or _CD or _TD) are already used
	 * 
	 * @param styleMap the style map
	 * @param baseName the base name
	 * @return true if all the composed names are available, false otherwise
	 */
	private boolean stylePresent(Map<String,JRStyle> styleMap, String baseName){
		return (styleMap.containsKey(baseName+"_TH") || styleMap.containsKey(baseName+"_CH") || styleMap.containsKey(baseName+"_TD"));
	}

	/**
	 * Starting from a TableStyle it generate a list of styles that will be applied to the table.
	 * It can also add them to the report
	 * 
	 * @param jd the jasperdesign
	 * @param addStylesToReport true if the generated styles will also be added to the report, otherwise false
	 * @return a list of style that can be applied to the table
	 */
	private List<JRDesignStyle> createStyles(JasperDesign jd, boolean addStylesToReport) 
	{
    	String baseName = "Table";
    	TableStyle style = (TableStyle)getStyle();
    	JSSCompoundCommand commands = new JSSCompoundCommand(null);
		for (int i = 0;; i++) {
			String name = baseName;
			if (i > 0) {
				name = baseName + " " + i;
			}

			if (!(stylePresent(jd.getStylesMap(),name))) {
				baseName = name;
				break;
			}
		}
    	
		ArrayList<JRDesignStyle> result = new ArrayList<JRDesignStyle>();

		result.add(null);

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
            tableHeaderStyle.getLineBox().getBottomPen().setLineWidth((Float) 0.5f);
            tableHeaderStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            tableHeaderStyle.getLineBox().getTopPen().setLineWidth((Float)0.5f);
        }

        tableHeaderStyle.setMode(ModeEnum.OPAQUE);
        tableHeaderStyle.setBackcolor(style.getColorValue(TableStyle.COLOR_TABLE_HEADER));

        commands.add(new CreateStyleCommand(jd, tableHeaderStyle));
        result.add(tableHeaderStyle);

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
            columnHeaderStyle.getLineBox().getBottomPen().setLineWidth((Float)0.5f);
            columnHeaderStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            columnHeaderStyle.getLineBox().getTopPen().setLineWidth((Float)0.5f);
        }

        columnHeaderStyle.setMode(ModeEnum.OPAQUE);
        columnHeaderStyle.setBackcolor(style.getColorValue(TableStyle.COLOR_COL_HEADER));

        commands.add(new CreateStyleCommand(jd, columnHeaderStyle));
        result.add(columnHeaderStyle);

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
            cellStyle.getLineBox().getBottomPen().setLineWidth((Float)0.5f);
            cellStyle.getLineBox().getTopPen().setLineColor(style.getBorderColor());
            cellStyle.getLineBox().getTopPen().setLineWidth((Float)0.5f);
        }

        cellStyle.setMode(ModeEnum.OPAQUE);
        cellStyle.setBackcolor(style.getColorValue(TableStyle.STANDARD_COLOR_DETAIL));


        if (style.hasAlternateColor())
        {
            JRDesignConditionalStyle condStyle = new JRDesignConditionalStyle();
            condStyle.setConditionExpression(ModelUtils.createExpression(ALT_ROW_EXP));
            condStyle.setBackcolor(style.getColorValue(TableStyle.COLOR_DETAIL));
            cellStyle.addConditionalStyle(condStyle);
        }

        commands.add(new CreateStyleCommand(jd, cellStyle));
        result.add(cellStyle);
        if (addStylesToReport) commands.execute();
        return result;
	}

	/**
	 * Starting from a TableStyle it generate a list of styles that will be applied to the table.
	 * For every style generated will be executed an addCommand to add them to the report
	 * 
	 * @param jd the jasperdesign
	 * @return a list of style that can be applied to the table
	 */
	@Override
	public List<JRDesignStyle> createStyles(JasperDesign jd) {
		if (styles == null) return createStyles(jd, true);
		return styles;
	}

}
