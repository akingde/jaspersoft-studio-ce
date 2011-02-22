/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.model.table.command.wizard;

import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.table.messages.Messages;
import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;
import com.jaspersoft.studio.wizards.dataset.WizardDatasetPage;
import com.jaspersoft.studio.wizards.dataset.WizardFieldsPage;

public class TableWizard extends Wizard {
	private WizardDatasetPage step1;
	private WizardFieldsPage step3;
	private WizardConnectionPage step2;
	private TableWizardLayoutPage step4;
	private MTable table;

	public TableWizard() {
		super();
		setWindowTitle(Messages.common_table_wizard);
	}

	@Override
	public void addPages() {
		this.table = new MTable();
		table.setValue(table.createJRElement(jasperDesign));

		MDatasetRun mdataset = (MDatasetRun) table.getPropertyValue(StandardTable.PROPERTY_DATASET_RUN);
		if (mdataset == null)
			mdataset = new MDatasetRun(new JRDesignDatasetRun(), jasperDesign);

		step1 = new WizardDatasetPage(jasperDesign, false);
		addPage(step1);
		step1.setDataSetRun(mdataset);

		step2 = new WizardConnectionPage();
		addPage(step2);
		step2.setDataSetRun(mdataset);

		step3 = new WizardFieldsPage();
		addPage(step3);

		step4 = new TableWizardLayoutPage();
		addPage(step4);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		String dsname = (String) step1.getDataSetRun().getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
		if (page == step1 && jasperDesign.getDatasetsList().size() == 0)
			page = step2;
		if (page == step2) {
			if (dsname != null && !dsname.equals(""))//$NON-NLS-1$
				step3.setFields(ModelUtils.getFields4Datasource(jasperDesign, dsname));
			else
				page = step3;
		}
		return super.getNextPage(page);
	}

	public MTable getTable() {
		List<JRDesignField> lst = step3.getFields();
		StandardTable tbl = CreateColumnCommand.getTable(table);
		if (tbl != null && lst != null)
			for (JRDesignField f : lst) {
				StandardColumn col = CreateColumnCommand.addColumn(jasperDesign, tbl, step4.isTableHeader(),
						step4.isTableFooter(), step4.isColumnHeader(), step4.isColumnFooter(), step4.isGroupHeader(),
						step4.isGroupFooter());

				DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
				DesignCell detCell = (DesignCell) col.getDetailCell();

				if (step4.isColumnHeader()) {
					JRDesignStaticText sText = (JRDesignStaticText) new MStaticText().createJRElement(jasperDesign);
					sText.setWidth(col.getWidth());
					sText.setHeight(colHeadCell.getHeight());
					sText.setText(f.getName());
					colHeadCell.addElement(sText);
				}

				JRDesignTextField fText = (JRDesignTextField) new MTextField().createJRElement(jasperDesign);
				fText.setWidth(col.getWidth());
				fText.setHeight(detCell.getHeight());
				JRDesignExpression jre = new JRDesignExpression();
				jre.setValueClassName(f.getValueClassName());
				jre.setText("$F{" + f.getName() + "}");//$NON-NLS-1$ //$NON-NLS-2$
				fText.setExpression(jre);
				detCell.addElement(fText);

				tbl.addColumn(col);
			}

		return table;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	private JasperDesign jasperDesign;

	public void init(JasperDesign jd) {
		this.jasperDesign = jd;
	}
}
