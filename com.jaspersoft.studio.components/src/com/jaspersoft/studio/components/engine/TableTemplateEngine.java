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
package com.jaspersoft.studio.components.engine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.GroupCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

import org.eclipse.draw2d.ColorConstants;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableSections;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableWizardLayoutPage;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateBundle;
import com.jaspersoft.templates.TemplateEngineException;

/**
 * Template engine to build a report with a table in the summary, from a TableTemplate
 * 
 * @author Orlandin Marco
 *
 */
public class TableTemplateEngine extends DefaultTemplateEngine {

	/**
	 * The list of styles that will applied to the table, the styles order is important, and it should be
	 * Table Style, Table Header, Column Header and detail
	 */
	private List<JRDesignStyle> stylesList;
	
	/**
	 * The list of fields of the table
	 */
	private List<Object> tableFields;
	
	/**
	 * The list of group of the table
	 */
	private List<Object> groupFields;
	
	/**
	 * Sample of the Static Text element that should be used inside the column header\footer cells
	 */
	private JRDesignStaticText colHeaderLabel;
	
	/**
	 * Sample of the text element that should be used inside the table header\footer cells
	 */
	//private JRDesignTextField tableHeaderField;
	
	/**
	 * Sample of the text element that should be used inside the table group cells
	 */
	private JRDesignTextField tableGroupField;
	
	/**
	 * Sample of the text element that should be used inside the detail cells
	 */
	private JRDesignTextField cellField;
	
	/**
	 * Width of the table
	 */
	private int tableWidth = 200;
	
	/**
	 * Height of the table
	 */
	private int tableHeight = 200;
	
	/**
	 * X position of the table
	 */
	private int tableX = 0;
	
	/**
	 * Y position of the table
	 */
	private int tableY = 0;
	
	/**
	 * Section to display of the table
	 */
	private TableSections sections;
	
	/**
	 * The height of the summary
	 */
	private int summaryHeight;
	
	/**
	 * Create a column 
	 *  
	 * @param tbl the table 
	 * @param jd the jasper design
	 * @param fieldName the column header
	 * @param fieldValue the field value
	 * @param colWidth the column width
	 */
	private StandardColumn generateColumn(StandardTable tbl, JasperDesign jd, String fieldName, String fieldValue, int colWidth){
		StandardColumn col = CreateColumnCommand.addColumn(jd, tbl,
				sections.isTableHeader(), sections.isTableFooter(),
				sections.isColumnHeader(), sections.isColumnFooter(),
				sections.isGroupHeader(), sections.isGroupFooter(), -1);
		col.setWidth(colWidth);
		DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
		DesignCell detCell = (DesignCell) col.getDetailCell();
		
		//Create the column header
		if (sections.isColumnHeader()) {
			JRDesignStaticText sText = null;
			//Create the cell detail by duplicating the sample content if it exist
			if (colHeaderLabel != null) sText = (JRDesignStaticText)colHeaderLabel.clone();
			else sText = (JRDesignStaticText) new MStaticText().createJRElement(jd);
			sText.setWidth(col.getWidth());
			sText.setHeight(colHeadCell.getHeight());
			sText.setX(0);
			sText.setY(0);
			sText.setText(fieldName);
			colHeadCell.addElement(sText);
		}
		
		JRDesignTextField fText = null;
		//Create the cell detail by duplicating the sample content if it exist
		if (cellField != null) fText = (JRDesignTextField) cellField.clone();
		else fText = (JRDesignTextField) new MTextField().createJRElement(jd);
		fText.setWidth(col.getWidth());
		fText.setHeight(detCell.getHeight());
		fText.setX(0);
		fText.setY(0);
		JRDesignExpression jre = new JRDesignExpression();
		jre.setText(fieldValue);
		fText.setExpression(jre);
		detCell.addElement(fText);
			
		return col;
	}
	
	/**
	 * Request the creation of a column and add it to a Column Group
	 *  
	 * @param tbl the table 
	 * @param jd the jasper design
	 * @param fieldName the column header
	 * @param fieldValue the field value
	 * @param colWidth the column width
	 * @param parentCol the group where the column will be add
	 */
	protected void createGroupColumn(StandardTable tbl, JasperDesign jd, String fieldName, String fieldValue, int colWidth, StandardColumnGroup parentCol){
		parentCol.addColumn(generateColumn(tbl, jd, fieldName, fieldValue, colWidth));
	}
	
	/**
	 * Request the creation of a column and add it to a table
	 *  
	 * @param tbl the table 
	 * @param jd the jasper design
	 * @param fieldName the column header
	 * @param fieldValue the field value
	 * @param colWidth the column width
	 */
	private void createColumn(StandardTable tbl, JasperDesign jd, String fieldName, String fieldValue, int colWidth){
		tbl.addColumn(generateColumn(tbl, jd, fieldName, fieldValue, colWidth));
	}
	
	
	/**
	 * Return the real column height, necessary to show all the cells
	 * 
	 * @param col a column, used to calculate the height
	 * @return the table height
	 */
	private int getTableHeight(BaseColumn col){
		int height = 0;
		
		if (col.getTableHeader() != null) height += col.getTableHeader().getHeight();
		if (col.getTableFooter() != null) height += col.getTableFooter().getHeight();
		if (col.getColumnHeader()!= null) height += col.getColumnHeader().getHeight();
		if (col.getColumnFooter() != null) height += col.getColumnFooter().getHeight();
		for(GroupCell cell : col.getGroupFooters()){
			height += cell.getCell().getHeight();
		}
		for(GroupCell cell : col.getGroupHeaders()){
			height += cell.getCell().getHeight();
		}
		
		if (col instanceof StandardColumnGroup){
			StandardColumnGroup groupCol = (StandardColumnGroup)col;
			height += getTableHeight(groupCol.getColumns().get(0));
		}
		
		if (col instanceof StandardColumn){
			StandardColumn standardCol = (StandardColumn)col;
			if (standardCol.getDetailCell() != null) height += standardCol.getDetailCell().getHeight();
		}
		return height;
	}
	
	/**
	 * Build the table JRElement and return it
	 * 
	 * @param jd The JasperDesign of the report where the table will be placed
	 * @param datasetRun the dataset of the table
	 * @return a JRDesignComponentElement that contains a StandardTable
	 */
	private JRDesignElement getTable(JasperDesign jd, JRDesignDatasetRun datasetRun) {
		JRDesignComponentElement jrElement = new JRDesignComponentElement();
		StandardTable tbl = new StandardTable();
		((JRDesignComponentElement) jrElement).setComponent(tbl);
		((JRDesignComponentElement) jrElement)
				.setComponentKey(new ComponentKey(
						"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		tbl.setDatasetRun(datasetRun);
		
		
		if (tableFields != null && tableFields.size()>0) {
			int colWidth = 40;
			if (tableFields.size() > 0)	colWidth = tableWidth / tableFields.size();
			if (sections == null) sections = TableWizardLayoutPage.getDefaultSection();
			
			//If there are at least one group then a Group column will be build
			if (groupFields!= null && groupFields.size()>0)
			{	
				//The group col is wide like all the other cells of a row together
				int groupColWidth = colWidth*tableFields.size();
				StandardColumnGroup parentCol = new StandardColumnGroup();
				parentCol.setWidth(groupColWidth);
				//Create the column for the group column
				for (Object f : tableFields) {
					createGroupColumn(tbl,jd,((JRField) f).getName(),"$F{" + ((JRField) f).getName() + "}",colWidth,parentCol); //$NON-NLS-1$ //$NON-NLS-2$
				}
				//Use col header as sample for the group cells
				int height = parentCol.getColumns().get(0).getColumnHeader().getHeight();
				//Create a spanned cell inside the column group, that take the field with the name of the group
				for(Object field : groupFields){
					JRDesignField groupField = (JRDesignField) field;
					DesignCell cell = new DesignCell();
					cell.setHeight(height);
					JRDesignTextField sText = (JRDesignTextField)tableGroupField.clone();
					sText.setWidth(parentCol.getWidth());
					sText.setHeight(cell.getHeight());
					sText.setX(0);
					sText.setY(0);
					JRDesignExpression groupExpression = ExprUtil.setValues(new JRDesignExpression(), "$F{" + groupField.getName() + "}", groupField.getValueClassName()); //$NON-NLS-1$ //$NON-NLS-2$
					sText.setExpression(groupExpression);
					cell.addElement(sText);
					parentCol.setGroupHeader(groupField.getName(), cell);
				}
				tbl.addColumn(parentCol);
				int minimumHeight = getTableHeight(parentCol);
				if (tableHeight < minimumHeight) tableHeight = minimumHeight;
			} else {
				//There are no groups, so will not be created group columns
				for (Object f : tableFields) {
					createColumn(tbl,jd,((JRField) f).getName(),"$F{" + ((JRField) f).getName() + "}",colWidth); //$NON-NLS-1$ //$NON-NLS-2$
				}
				int minimumHeight = getTableHeight((StandardColumn)tbl.getColumns().get(0));
				if (tableHeight < minimumHeight) tableHeight = minimumHeight;
			}
		} else {
			//If there are no fields defined create an empty column
			createColumn(tbl,jd,"","\"\"",160); //$NON-NLS-1$ //$NON-NLS-2$
		}
				
		//Create and apply the styles to the table. The styles should be read from the template report
		//if for some reason this styles are not present then a default set of styles will be used
		ApplyTableStyleAction applyAction;
		if (stylesList != null) applyAction = new ApplyTableStyleAction(stylesList, jrElement);
		else  applyAction = new ApplyTableStyleAction(TableWizardLayoutPage.getDefaultStyle(), jrElement);
		applyAction.applayStyle(jd);
		
		//Recalculate the real table height
		return jrElement;
	}
	
	/**
	 * From an array of JRDesignStyle try to find the style to apply to the table. The styles searched must have 
	 * a specific name: Table, Table_TH, Table_CH, Table_TD. If all this four styles are found then a List with 
	 * their references is returned, otherwise some default styles will be generated and returned
	 * 
	 * @param styleArray the array of JRDesignStyle
	 * @return a list of style that will be applied to the table
	 */
	private List<JRDesignStyle> buildStylesList(JasperDesign jd){
		JRStyle[] styleArray = jd.getStyles();
		JRDesignStyle[] result = new JRDesignStyle[4];
		for(JRStyle style : styleArray){
			if(style instanceof JRDesignStyle){
				//if (style.getName().equals("Table")) result[0] = (JRDesignStyle)style; else
				if (style.getName().equals("Table_TH")) result[1] = (JRDesignStyle)style; //$NON-NLS-1$
				else if (style.getName().equals("Table_CH")) result[2] = (JRDesignStyle)style; //$NON-NLS-1$
				else if (style.getName().equals("Table_TD")) result[3] = (JRDesignStyle)style; //$NON-NLS-1$
			}
		}
		if (result[1] == null || result[2] == null || result[3] == null ) {
			//Styles missing, generating default styles
			TableStyle defaultPattern = new TableStyle(ColorConstants.white.getRGB(), ColorSchemaGenerator.SCHEMAS.PALE, 
															BorderStyleEnum.FULL, ColorConstants.black.getRGB(), false);
			ApplyTableStyleAction stylesGenerator = new ApplyTableStyleAction(defaultPattern, null);
			List<JRDesignStyle> defaultStyles = stylesGenerator.createStyles(jd);
			defaultStyles.get(1).setBackcolor(Color.white);
			defaultStyles.get(2).setBackcolor(Color.white);
			defaultStyles.get(3).setBackcolor(Color.white);
			if (result[1] == null) result[1] = defaultStyles.get(1);
			if (result[2] == null) result[2] = defaultStyles.get(2);
			if (result[3] == null) result[3] = defaultStyles.get(3);
		}
		return new ArrayList<JRDesignStyle>(Arrays.asList(result));
	}
	
	
	/**
	 * Search the template table inside a JasperDesign, looking in the children of the summary band
	 * 
	 * @param jd the jasperdesign that contains the sample table
	 * @return a JRDesignComponentElement that contains a StandardTable, or null if it isn't found
	 */
	private static JRDesignComponentElement getTable(JasperDesign jd){
		if (jd.getSummary() == null) return null;
		for(JRChild child : jd.getSummary().getChildren()){
			if (child instanceof JRDesignComponentElement){
				JRDesignComponentElement component = (JRDesignComponentElement)child;
				if (component.getComponent() instanceof StandardTable) return component;
			}
		}
		//No table found, create a default one
		return null;
	}

	/**
	 * Initialize the fields needed to build the style of the report
	 */
	@Override
	protected void processTemplate(JasperDesign jd, List<Object> fields, List<Object> groupFields) {
		//Initialize the styles list
		stylesList = buildStylesList(jd);
		JRDesignComponentElement tableComponent = getTable(jd);
		summaryHeight = jd.getSummary().getHeight();
		/**
		 * If the template table is found it will be used to create the style of the real table and of its 
		 * content
		 */
		if (tableComponent != null){
			StandardTable table = (StandardTable)tableComponent.getComponent();
			
			colHeaderLabel = findStaticTextElement(table,"label"); //$NON-NLS-1$
			cellField = findTextFieldElement(table,"DetailField"); //$NON-NLS-1$
			tableGroupField = new MTextField().createJRElement(jd);
			//tableHeaderField = findTextFieldElement(table,"Header");
			
			tableWidth = tableComponent.getWidth();
			tableHeight = tableComponent.getHeight();
			tableX = tableComponent.getX();
			tableY = tableComponent.getY();
			if (table.getColumns().size()>0){
				StandardColumn col = (StandardColumn) table.getColumns().get(0);
				boolean tableHeader = col.getTableHeader() != null;
				boolean tableFooter = col.getTableFooter() != null;
				boolean columnHeader = col.getColumnHeader() != null;
				boolean columnFooter = col.getColumnFooter() != null;
				boolean groupHeader = col.getGroupHeaders() != null && col.getGroupHeaders().size()>0; 
				boolean groupFooter = col.getGroupFooters() != null && col.getGroupFooters().size()>0; 
				sections = new TableSections(tableHeader, tableFooter, columnHeader, columnFooter, groupHeader, groupFooter);
			}
			removeElement(jd.getSummary(), tableComponent);
		} else {
			//If the table is not found try to build the template with some default values
			colHeaderLabel = DefaultTemplateEngine.findStaticTextElement(jd.getColumnHeader(), "Label"); //$NON-NLS-1$
			cellField = DefaultTemplateEngine.findTextFieldElement(jd.getDetailSection().getBands()[0], "Field"); //$NON-NLS-1$
			
			JRDesignGroup group = (JRDesignGroup) jd.getGroupsList().get(0);
			if (group.getGroupHeaderSection() != null && group.getGroupHeaderSection().getBands().length > 0) {
				JRBand groupHeaderSection = group.getGroupHeaderSection().getBands()[0];
				tableGroupField = DefaultTemplateEngine.findTextFieldElement(groupHeaderSection, "GroupField"); //$NON-NLS-1$
			} else tableGroupField = new MTextField().createJRElement(jd);
			
			tableWidth = jd.getPageWidth()-jd.getLeftMargin()-jd.getRightMargin();
			
			if (jd.getSummary() == null){
				//I need to create the summary where place the table
				jd.setSummary(MBand.createJRBand());
				((JRDesignBand)jd.getSummary()).setHeight(jd.getDetailSection().getBands()[0].getHeight());
			}
			tableHeight = jd.getSummary().getHeight();
			tableX = 0;
			tableY = 0;
			sections = new TableSections(false, false, true, true, false, false);
			JRDesignElementGroup summaryBand = (JRDesignElementGroup)jd.getSummary();
			for(JRChild child : summaryBand.getChildren())
				summaryBand.removeElement((JRDesignElement)child);
		}
		
		removeUnwantedBand(jd);
		jd.removeDataset("tableDataset"); //$NON-NLS-1$
	}
	
	private void removeUnwantedBand(JasperDesign jd){
		/**
		 * Remove unwanted band and the placeholder dataset of the table
		 */
		jd.setColumnHeader(null);
		jd.setColumnFooter(null);
		JRDesignSection bandSection = (JRDesignSection)jd.getDetailSection();
		for(JRBand actualDetail : jd.getDetailSection().getBands())
			bandSection.removeBand(actualDetail);
		//Delete the groups
		while (jd.getGroupsList().size()>0)
			jd.getGroupsList().remove(0);
	}
	
	/**
	 * Find a JRDesignStaticText inside a table element having exp as text.
	 * 
	 * @param parent table where to search
	 * @param exp the text of the element
	 * @return the first matching element or null.
	 */
	public static JRDesignStaticText findStaticTextElement(StandardTable parent, String exp) {
		StandardColumn col = (StandardColumn)parent.getColumns().get(0);
		if (col != null){
			JRDesignStaticText result = null;
			if (col.getTableHeader() != null) result = DefaultTemplateEngine.findStaticTextElement(col.getTableHeader(), exp);
			if (col.getColumnHeader() != null && result == null) result = DefaultTemplateEngine.findStaticTextElement(col.getColumnHeader(), exp);
			if (col.getDetailCell() != null && result == null) result = DefaultTemplateEngine.findStaticTextElement(col.getDetailCell(), exp);
			return result;
		}
		return null;
	}
	
	/**
	 * Find a JRDesignTextField inside a table element having exp as expression.
	 * 
	 * @param parent table where to search
	 * @param exp the expression of the element
	 * @return the first matching element or null.
	 */
	public static JRDesignTextField findTextFieldElement(StandardTable parent, String exp) {
		StandardColumn col = (StandardColumn)parent.getColumns().get(0);
		if (col != null){
			JRDesignTextField result = null;
			if (col.getTableHeader() != null) DefaultTemplateEngine.findTextFieldElement(col.getTableHeader(), exp);
			if (col.getColumnHeader() != null && result == null) result = DefaultTemplateEngine.findTextFieldElement(col.getColumnHeader(), exp);
			if (col.getDetailCell() != null && result == null) result = DefaultTemplateEngine.findTextFieldElement(col.getDetailCell(), exp);
			return result;
		}
		return null;
	}
	

	

	/**
	 * Create the report with the table inside
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReportBundle generateReportBundle(TemplateBundle template, Map<String, Object> settings)
			throws TemplateEngineException {
		//Generate the base report bundle
		ReportBundle reportBundle = super.generateReportBundle(template, settings);
		//The fields that will be added to the table
		tableFields = (List<Object>)settings.get(DefaultTemplateEngine.FIELDS);
		//Create the dataset, by default we set the dataset run to use the report connection...
		JRDesignDataset tableDataset = new JRDesignDataset(false);
		tableDataset.setName("tableDataset"); //$NON-NLS-1$
		//Create the dataset query
		JRDesignDataset dataset = (JRDesignDataset)settings.get(DefaultTemplateEngine.DATASET);
		JRDesignQuery query = new JRDesignQuery();
		if (dataset != null){
			query.setLanguage(dataset.getQuery().getLanguage());
			query.setText(dataset.getQuery().getText());
		}
		tableDataset.setQuery(query);
		//Add the fields to the dataset, check if i have an empty dataset
		if (tableFields != null){
			for(Object field : tableFields){
				try {
					tableDataset.addField((JRDesignField)field);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}
		
		//Create the groups into the dataset
		groupFields = (List<Object>) settings.get(DefaultTemplateEngine.GROUP_FIELDS);
		if (groupFields != null){
			for(Object field : groupFields){
				try {
					JRDesignGroup newGroup = new JRDesignGroup();
					JRDesignField groupField = (JRDesignField)field;
					newGroup.setName(groupField.getName());
					JRDesignExpression groupExpression = ExprUtil.setValues(new JRDesignExpression(), "$F{" +groupField.getName() + "}", groupField.getValueClassName()); //$NON-NLS-1$ //$NON-NLS-2$
					newGroup.setExpression(groupExpression);
					tableDataset.addGroup(newGroup);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}
		
		JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("$P{REPORT_CONNECTION}"); //$NON-NLS-1$
		datasetRun.setConnectionExpression( exp );
		datasetRun.setDatasetName("tableDataset"); //$NON-NLS-1$
		try {
			reportBundle.getJasperDesign().addDataset(tableDataset);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		JasperDesign jd = reportBundle.getJasperDesign();
		jd.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL); 
		
		//Build the table and recalculate the table height
		JRDesignElement table = getTable(jd,datasetRun);
		
		//If the summary doesn't exist it will be created
		JRDesignBand summaryBand =  (JRDesignBand)jd.getSummary();
		if (summaryBand == null) {
			summaryBand = MBand.createJRBand();
			jd.setSummary(summaryBand);
		}
		
		//Set the summary and table height and width according to the new value, and add the table to the report
		//summaryBand.setHeight(tableHeight);
		summaryBand.setHeight(summaryHeight);
		table.setWidth(tableWidth);
		table.setHeight(tableHeight);
		table.setX(tableX);
		table.setY(tableY);
		summaryBand.addElement(table);
		
		return reportBundle;
	}
	
	
	/**
	 * Get a JasperDesign and check if that JasperDesign can be used as Template and processed
	 * by this engine. 
	 * 
	 * @param design the design to check
	 * @return a List of founded error, the list is void if no error are found
	 */
	public static List<String> validateJasperDesig(JasperDesign design){
		
		List<String> errorsList = new ArrayList<String>();
		
		boolean[] foundedStyles = {false, false,false};
		
		JRStyle[] styleArray = design.getStyles();
		for(JRStyle style : styleArray){
			if(style instanceof JRDesignStyle){
				if (style.getName().equals("Table_TH")) foundedStyles[0] = true;  //$NON-NLS-1$
				else if (style.getName().equals("Table_CH")) foundedStyles[1] = true; //$NON-NLS-1$
				else if (style.getName().equals("Table_TD")) foundedStyles[2] = true; //$NON-NLS-1$
			}
		}
		if (!foundedStyles[0]) errorsList.add(Messages.TableTemplateEngine_missingStyleTH);
		if (!foundedStyles[1]) errorsList.add(Messages.TableTemplateEngine_missingStyleCH);
		if (!foundedStyles[2]) errorsList.add(Messages.TableTemplateEngine_missingStyleD);
		
		JRDesignComponentElement tableComponent = getTable(design);
		if (tableComponent == null) errorsList.add(Messages.TableTemplateEngine_missingTable);
		else{
			StandardTable table = (StandardTable)tableComponent.getComponent();
			if (findStaticTextElement(table,"label") == null) errorsList.add(Messages.TableTemplateEngine_missingStaticText); //$NON-NLS-1$
			if (findTextFieldElement(table,"DetailField") == null) errorsList.add(Messages.TableTemplateEngine_missingTextField); //$NON-NLS-1$
		}
		
		return errorsList;
	}
	
}