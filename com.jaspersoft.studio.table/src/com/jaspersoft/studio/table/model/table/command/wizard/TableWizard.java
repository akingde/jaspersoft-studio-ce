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
	private WizardDatasetPage page0;
	private WizardFieldsPage page1;
	private WizardConnectionPage page2;
	// private TableWizardLayoutPage page3;
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

		page0 = new WizardDatasetPage(jasperDesign);
		addPage(page0);
		page0.setDataSetRun(mdataset);

		page2 = new WizardConnectionPage();
		addPage(page2);
		page2.setDataSetRun(mdataset);

		page1 = new WizardFieldsPage();
		addPage(page1);

		// page3 = new TableWizardLayoutPage();
		// addPage(page3);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof WizardFieldsPage) {
			WizardFieldsPage tpage = (WizardFieldsPage) page;
			String dataset = (String) page0.getDataSetRun().getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
			if (dataset.equals("")) //$NON-NLS-1$
				dataset = null;
			tpage.setFields(ModelUtils.getFields4Datasource(jasperDesign, dataset));
		}
		return super.getNextPage(page);
	}

	public MTable getTable() {
		List<JRDesignField> lst = page1.getFields();
		StandardTable tbl = CreateColumnCommand.getTable(table);
		for (JRDesignField f : lst) {
			StandardColumn col = CreateColumnCommand.addColumn(jasperDesign, tbl);

			DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
			DesignCell detCell = (DesignCell) col.getDetailCell();

			JRDesignStaticText sText = (JRDesignStaticText) new MStaticText().createJRElement(jasperDesign);
			sText.setWidth(col.getWidth());
			sText.setHeight(colHeadCell.getHeight());
			sText.setText(f.getName());
			colHeadCell.addElement(sText);

			JRDesignTextField fText = (JRDesignTextField) new MTextField().createJRElement(jasperDesign);
			fText.setWidth(col.getWidth());
			fText.setHeight(detCell.getHeight());
			JRDesignExpression jre = new JRDesignExpression();
			jre.setValueClassName(f.getValueClassName());
			jre.setText("$F{" + f.getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$
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
