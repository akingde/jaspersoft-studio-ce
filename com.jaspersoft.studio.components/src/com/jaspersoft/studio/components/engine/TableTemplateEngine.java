package com.jaspersoft.studio.components.engine;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.components.table.model.dialog.TableStyle;
import com.jaspersoft.studio.components.table.model.table.command.wizard.TableSections;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateBundle;
import com.jaspersoft.templates.TemplateEngineException;

/**
 * Template engine to build a report with a table
 * 
 * @author Orlandin Marco
 *
 */
public class TableTemplateEngine extends DefaultTemplateEngine {
	
	/**
	 * Key to retrieve the list of JRFields, that are the column that will be created on the table
	 */
	final static public String TABLE_FIELDS = "table_fields";
	
	/**
	 * Key used to retrieve a TableStyle, where are saved the style informations of the table
	 */
	final static public String TABLE_STYLE = "table_style";
	
	/**
	 * Key used to retireve a TableSections, where are saved the
	 */
	final static public String TABLE_SECTIONS = "table_sections";

	private TableStyle style;
	
	private TableSections sections;
	
	private List<Object> tableFields;
	
	private JRDesignElement getTable(JasperDesign jd, JRDesignDatasetRun datasetRun) {
				
		JRDesignComponentElement jrElement = new JRDesignComponentElement();
		StandardTable tbl = new StandardTable();
		((JRDesignComponentElement) jrElement).setComponent(tbl);
		((JRDesignComponentElement) jrElement)
				.setComponentKey(new ComponentKey(
						"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		tbl.setDatasetRun(datasetRun);
		
		
		if (tbl != null && tableFields != null) {
			int colWidth = 40;
			int tableWidth=200;
			if (tableFields.size() > 0)
				colWidth = tableWidth / tableFields.size();
			for (Object f : tableFields) {
				StandardColumn col = CreateColumnCommand.addColumn(jd, tbl,
						sections.isTableHeader(), sections.isTableFooter(),
						sections.isColumnHeader(), sections.isColumnFooter(),
						sections.isGroupHeader(), sections.isGroupFooter(), -1);
				col.setWidth(colWidth);
				DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
				DesignCell detCell = (DesignCell) col.getDetailCell();
				if (sections.isColumnHeader()) {
					JRDesignStaticText sText = (JRDesignStaticText) new MStaticText()
							.createJRElement(jd);
					sText.setWidth(col.getWidth());
					sText.setHeight(colHeadCell.getHeight());
					sText.setText(((JRField) f).getName());
					colHeadCell.addElement(sText);
				}
				JRDesignTextField fText = (JRDesignTextField) new MTextField()
						.createJRElement(jd);
				fText.setWidth(col.getWidth());
				fText.setHeight(detCell.getHeight());
				JRDesignExpression jre = new JRDesignExpression();
				jre.setText("$F{" + ((JRField) f).getName() + "}");//$NON-NLS-1$ //$NON-NLS-2$
				fText.setExpression(jre);
				detCell.addElement(fText);
				tbl.addColumn(col);
			}
		}
		ApplyTableStyleAction applyAction = new ApplyTableStyleAction(style, jrElement);
		applyAction.applayStyle(jd);
		return jrElement;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ReportBundle generateReportBundle(TemplateBundle template, Map<String, Object> settings)
			throws TemplateEngineException {
		ReportBundle reportBundle = super.generateReportBundle(template, settings);
		tableFields = (List<Object>)settings.get(TABLE_FIELDS);
		style = (TableStyle)settings.get(TABLE_STYLE);
		sections = (TableSections)settings.get(TABLE_SECTIONS);

		//Create the dataset
		// By default we set the dataset run to use the report connection...
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
		
		
		JRDesignBand detilBand =  (JRDesignBand)reportBundle.getJasperDesign().getSummary();
		
		JRDesignElement table = getTable(reportBundle.getJasperDesign(),datasetRun);
		
		JRDesignElementGroup jrdgroup = (JRDesignElementGroup) detilBand;
		JasperDesign jd = reportBundle.getJasperDesign();
		detilBand.setHeight(200);
		table.setWidth(jd.getPageWidth()-jd.getLeftMargin()-jd.getRightMargin());
		table.setHeight(detilBand.getHeight());
		jrdgroup.addElement(table);
		return reportBundle;
	}
	
}