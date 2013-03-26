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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
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
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableSections;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableWizardLayoutPage;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
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
	 * Sample of the Static Text element that should be used inside the column header\footer cells
	 */
	private JRDesignStaticText colHeaderLabel;
	
	/**
	 * Sample of the text element that should be used inside the table header\footer cells
	 */
	private JRDesignTextField tableHeaderField;
	
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
						"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table"));
		tbl.setDatasetRun(datasetRun);
		
		
		if (tableFields != null) {
			int colWidth = 40;
			if (tableFields.size() > 0)	colWidth = tableWidth / tableFields.size();
			TableSections sections = TableWizardLayoutPage.getDefaultSection();
			for (Object f : tableFields) {
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
					sText.setText(((JRField) f).getName());
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
				jre.setText("$F{" + ((JRField) f).getName() + "}");
				fText.setExpression(jre);
				detCell.addElement(fText);
				tbl.addColumn(col);
			}
		}
		//Create and apply the styles to the table. The styles should be read from the template report
		//if for some reason this styles are not present then a default set of styles will be used
		ApplyTableStyleAction applyAction;
		if (stylesList != null) applyAction = new ApplyTableStyleAction(stylesList, jrElement);
		else  applyAction = new ApplyTableStyleAction(TableWizardLayoutPage.getDefaultStyle(), jrElement);
		applyAction.applayStyle(jd);
		return jrElement;
	}
	
	/**
	 * From an array of JRDesignStyle try to find the style to apply to the table. The styles searched must have 
	 * a specific name: Table, Table_TH, Table_CH, Table_TD. If all this four styles are found then a List with 
	 * their references is returned, otherwise null
	 * 
	 * @param styleArray the array of JRDesignStyle
	 * @return a list of style that will be applied to the table, or null if the searched styles are not found 
	 * in the array
	 */
	private List<JRDesignStyle> buildStylesList(JRStyle[] styleArray){
		JRDesignStyle[] result = new JRDesignStyle[4];
		for(JRStyle style : styleArray){
			if(style instanceof JRDesignStyle){
				if (style.getName().equals("Table")) result[0] = (JRDesignStyle)style;
				else if (style.getName().equals("Table_TH")) result[1] = (JRDesignStyle)style;
				else if (style.getName().equals("Table_CH")) result[2] = (JRDesignStyle)style;
				else if (style.getName().equals("Table_TD")) result[3] = (JRDesignStyle)style;
			}
		}
		if (result[0] == null || result[1] == null || result[2] == null || result[3] == null ) return null;
		else return new ArrayList<JRDesignStyle>(Arrays.asList(result));
	}
	
	
	/**
	 * Search the template table inside a JasperDesign, looking in the children of the summary band
	 * 
	 * @param jd the jasperdesign that contains the sample table
	 * @return a JRDesignComponentElement that contains a StandardTable, or null if it isn't found
	 */
	private JRDesignComponentElement getTable(JasperDesign jd){
		for(JRChild child : jd.getSummary().getChildren()){
			if (child instanceof JRDesignComponentElement){
				JRDesignComponentElement component = (JRDesignComponentElement)child;
				if (component.getComponent() instanceof StandardTable) return component;
			}
		}
		return null;
	}

	/**
	 * Initialize the fields needed to build the style of the report
	 */
	@Override
	protected void processTemplate(JasperDesign jd, List<Object> fields, List<Object> groupFields) {
		//Initialize the styles list
		stylesList = buildStylesList(jd.getStyles());
		JRDesignComponentElement tableComponent = getTable(jd);
		/**
		 * If the tamplate table is found it will be used to create the style of the real table and of its 
		 * content
		 */
		if (tableComponent != null){
			StandardTable table = (StandardTable)tableComponent.getComponent();
			colHeaderLabel = findStaticTextElement(table,"label");
			cellField = findTextFieldElement(table,"DetailField");
			tableHeaderField = findTextFieldElement(table,"Header");
			tableWidth = tableComponent.getWidth();
			tableHeight = tableComponent.getHeight();
			tableX = tableComponent.getX();
			tableY = tableComponent.getY();
			removeElement(jd.getSummary(), tableComponent);
		}
		
		/**
		 * Remove unwanted band and the placeholder dataset of the table
		 */
		JRDesignSection bandSection = (JRDesignSection)jd.getDetailSection();
		for(JRBand actualDetail : jd.getDetailSection().getBands())
			bandSection.removeBand(actualDetail);
		while (jd.getGroupsList().size()>0)
			jd.getGroupsList().remove(0);
		jd.setPageHeader(null);
		jd.setColumnHeader(null);
		jd.setColumnFooter(null);
		jd.setLastPageFooter(null);
		jd.removeDataset("tableDataset");
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
			JRDesignStaticText result = DefaultTemplateEngine.findStaticTextElement(col.getTableHeader(), exp);
			if (result == null) result = DefaultTemplateEngine.findStaticTextElement(col.getColumnHeader(), exp);
			if (result == null) result = DefaultTemplateEngine.findStaticTextElement(col.getDetailCell(), exp);
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
			JRDesignTextField result = DefaultTemplateEngine.findTextFieldElement(col.getTableHeader(), exp);
			if (result == null) result = DefaultTemplateEngine.findTextFieldElement(col.getColumnHeader(), exp);
			if (result == null) result = DefaultTemplateEngine.findTextFieldElement(col.getDetailCell(), exp);
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
		tableDataset.setName("tableDataset");
		//Create the dataset query
		JRDesignDataset dataset = (JRDesignDataset)settings.get(DefaultTemplateEngine.DATASET);
		JRDesignQuery query = new JRDesignQuery();
		if (dataset != null){
			query.setLanguage(dataset.getQuery().getLanguage());
			query.setText(dataset.getQuery().getText());
		}
		tableDataset.setQuery(query);
		//Add the fields to the dataset
		for(Object field : tableFields){
			try {
				tableDataset.addField((JRDesignField)field);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("$P{REPORT_CONNECTION}");
		datasetRun.setConnectionExpression( exp );
		datasetRun.setDatasetName("tableDataset");
		try {
			reportBundle.getJasperDesign().addDataset(tableDataset);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		JasperDesign jd = reportBundle.getJasperDesign();
		JRDesignBand summaryBand =  (JRDesignBand)jd.getSummary();
		//If the summary doesn't exist it will be created
		if (summaryBand == null) {
			summaryBand = MBand.createJRBand();
			jd.setSummary(summaryBand);
			summaryBand.setHeight(tableHeight);
		}
		//Build the table and add it to the report
		JRDesignElement table = getTable(jd,datasetRun);
		table.setWidth(tableWidth);
		table.setHeight(tableHeight);
		table.setX(tableX);
		table.setY(tableY);
		summaryBand.addElement(table);
		jd.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL); 
		return reportBundle;
	}
	
}