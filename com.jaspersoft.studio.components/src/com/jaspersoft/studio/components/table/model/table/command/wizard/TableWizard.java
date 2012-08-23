/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.table.model.table.command.wizard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

public class TableWizard extends JSSWizard {
	private WizardDatasetPage step1;
	private WizardFieldsPage step3;
	private WizardConnectionPage step2;
	private TableWizardLayoutPage step4;
	private MTable table;
	private List<JRDesignStyle> styleList;
	float baseColor = new Float(Math.tan(Math.toRadians(208.0)));

	public TableWizard() {
		super();
		setWindowTitle(Messages.common_table_wizard);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		table = new MTable();
		table.setValue(table.createJRElement(getConfig().getJasperDesign()));
		table.setJasperConfiguration(getConfig());

		MDatasetRun mdataset = (MDatasetRun) table
				.getPropertyValue(StandardTable.PROPERTY_DATASET_RUN);
		if (mdataset == null)
			mdataset = new MDatasetRun(new JRDesignDatasetRun(), getConfig()
					.getJasperDesign());
		mdataset.setPropertyValue(
				JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION,
				"$P{REPORT_CONNECTION}");

		step1 = new WizardDatasetPage(getConfig(), false, "Table");
		addPage(step1);
		step1.setDataSetRun(mdataset);

		step2 = new WizardConnectionPage();
		addPage(step2);
		step2.setDataSetRun(mdataset);
		step2.setExpressionContext(
				ModelUtils.getElementExpressionContext((JRDesignElement)table.getValue(), table));

		step3 = new WizardFieldsPage();
		addPage(step3);

		step4 = new TableWizardLayoutPage();
		addPage(step4);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == step1) {
			if (getConfig().getJasperDesign().getDatasetsList().size() == 0)
				return step2;
		}
		if (page == step2) {
			MDatasetRun dataSetRun = step1.getDataSetRun();
			if (dataSetRun == null) {
				MDataset ds = (MDataset) getConfig().get(DatasetWizard.DATASET);
				if (ds != null) {
					step3.setFields(new ArrayList<JRField>(ds.getValue()
							.getFieldsList()));
				} else
					page = step3;
			} else {
				String dsname = (String) dataSetRun
						.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
				if (dsname != null && !dsname.isEmpty()) {
					step3.setFields(new ArrayList<Object>(ModelUtils
							.getFields4Datasource(
									getConfig().getJasperDesign(), dsname)));
				} else
					page = step3;
			}
		}
		return super.getNextPage(page);
	}

	public MTable getTable() {
		return table;
	}
	
	public void InitTable(){
		List<Object> lst = step3.getFields();
		StandardTable tbl = TableManager.getTable(table);
		MDataset ds = (MDataset) getConfig().get(DatasetWizard.DATASET);
		MDatasetRun dataSetRun = step1.getDataSetRun();
		if (dataSetRun != null) {
			JRDesignDatasetRun dsrun = dataSetRun.getValue();
			dsrun.setDatasetName((String) ds
					.getPropertyValue(JRDesignDataset.PROPERTY_NAME));
			tbl.setDatasetRun(dsrun);
		}
		JasperDesign jd = getConfig().getJasperDesign();
		CreateDeafultStyles(jd);
		if (tbl != null && lst != null){
			int colWidth = 40;
			if (lst.size()>0)
				colWidth = table.getBounds().width / lst.size();
			for (Object f : lst) {
				StandardColumn col = CreateColumnCommand.addColumn(jd, tbl,
				step4.isTableHeader(), step4.isTableFooter(),
				step4.isColumnHeader(), step4.isColumnFooter(),
				step4.isGroupHeader(), step4.isGroupFooter());
				col.setWidth(colWidth);
				//Set the cel color
				DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
				colHeadCell.setStyle(styleList.get(2));
				DesignCell detCell = (DesignCell) col.getDetailCell();
				detCell.setStyle(styleList.get(3));
				DesignCell tblHeadCell = (DesignCell) col.getTableHeader();
				tblHeadCell.setStyle(styleList.get(1));
				DesignCell tblFooterCell = (DesignCell) col.getTableFooter();
				tblFooterCell.setStyle(styleList.get(1));
				DesignCell colFooterCell = (DesignCell) col.getColumnFooter();
				colFooterCell.setStyle(styleList.get(2));
				//Color setted
				if (step4.isColumnHeader()) {
					JRDesignStaticText sText = (JRDesignStaticText) new MStaticText()
							.createJRElement(jd);
					sText.setWidth(col.getWidth());
					sText.setHeight(colHeadCell.getHeight());
					sText.setText(((JRField) f).getName());
					colHeadCell.addElement(sText);
				}
				JRDesignTextField fText = (JRDesignTextField) new MTextField().createJRElement(jd);
				fText.setWidth(col.getWidth());
				fText.setHeight(detCell.getHeight());
				JRDesignExpression jre = new JRDesignExpression();
				jre.setText("$F{" + ((JRField) f).getName() + "}");//$NON-NLS-1$ //$NON-NLS-2$
				fText.setExpression(jre);
				detCell.addElement(fText);
				tbl.addColumn(col);
			}
		}
	}
	
	public List<JRDesignStyle> GetStylesList(){
		return styleList;
	}
	
	/**
	 * Create a color array for border and gradient
	 * @param baseColor value from 0 to 1 that represent the base color H in HSB system
	 * @return an array of colors, different version of the base color
	 */
	private Color[] CreateColor(){
		Color[] result = {Color.getHSBColor(baseColor, 0.25f, 1.0f),Color.getHSBColor(baseColor, 0.06f, 1.0f)};
		return result;
	}
	
	private void CreateDeafultStyles(JasperDesign jd){
		JRDesignStyle newStyle = new JRDesignStyle();
        //Check the first available basename...
        String basename = "Table";
        styleList = new ArrayList<JRDesignStyle>();
        for (int i=0; ; i++)
        {
            String name = basename;
            if (i > 0)
            {
                name = basename + " "+ i;
            }
            
            if (!(jd.getStylesMap().containsKey(name)))
            {
                basename = name;
                break;
            }

        }
		try {
			Color[] colors = CreateColor();
			newStyle.setName(basename);
			newStyle.setMode(ModeEnum.OPAQUE);
			jd.addStyle(newStyle);
			styleList.add(newStyle);
			newStyle = new JRDesignStyle();
			newStyle.setName(basename+"_TH");
			newStyle.setMode(ModeEnum.OPAQUE);
			newStyle.setBackcolor(colors[1]);
			jd.addStyle(newStyle);
			styleList.add(newStyle);
			newStyle = new JRDesignStyle();
			newStyle.setName(basename+"_CH");
			newStyle.setMode(ModeEnum.OPAQUE);
			newStyle.setBackcolor(colors[0]);
			jd.addStyle(newStyle);
			styleList.add(newStyle);
			newStyle = new JRDesignStyle();
			newStyle.setName(basename+"_TD");
			newStyle.setMode(ModeEnum.OPAQUE);
			newStyle.setBackcolor(Color.white);
			jd.addStyle(newStyle);
			styleList.add(newStyle);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(JasperReportsConfiguration jConfig) {
		super.init(jConfig);
		if (table != null)
			table.setJasperConfiguration(jConfig);
	}
}
