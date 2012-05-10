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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.IWizardPage;

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
		List<Object> lst = step3.getFields();
		StandardTable tbl = CreateColumnCommand.getTable(table);
		JasperDesign jd = getConfig().getJasperDesign();
		if (tbl != null && lst != null)
			for (Object f : lst) {
				StandardColumn col = CreateColumnCommand.addColumn(jd, tbl,
						step4.isTableHeader(), step4.isTableFooter(),
						step4.isColumnHeader(), step4.isColumnFooter(),
						step4.isGroupHeader(), step4.isGroupFooter());

				DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
				DesignCell detCell = (DesignCell) col.getDetailCell();

				if (step4.isColumnHeader()) {
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

		return table;
	}

	@Override
	public void init(JasperReportsConfiguration jConfig) {
		super.init(jConfig);
		if (table != null)
			table.setJasperConfiguration(jConfig);
	}
}
