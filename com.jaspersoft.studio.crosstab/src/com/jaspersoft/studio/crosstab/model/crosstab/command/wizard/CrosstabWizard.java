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
package com.jaspersoft.studio.crosstab.model.crosstab.command.wizard;

import java.util.List;

import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.crosstab.CrosstabManager;
import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.crosstab.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.crosstab.model.measure.command.CreateMeasureCommand;
import com.jaspersoft.studio.crosstab.model.rowgroup.command.CreateRowGroupCommand;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.MElementDataset;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.wizards.dataset.WizardConnectionPage;
import com.jaspersoft.studio.wizards.dataset.WizardDatasetPage;
import com.jaspersoft.studio.wizards.dataset.WizardFieldsPage;

public class CrosstabWizard extends Wizard {
	private WizardDatasetPage step1;
	private WizardFieldsPage step3;
	private WizardFieldsPage step4;
	private CrosstabWizardMeasurePage step5;
	private CrosstabWizardLayoutPage step6;
	private WizardConnectionPage step2;
	private MCrosstab crosstab;

	public CrosstabWizard() {
		super();
		setWindowTitle(Messages.common_crosstab_wizard);
	}

	@Override
	public void addPages() {
		JRDesignCrosstab jrCrosstab = (JRDesignCrosstab) new MCrosstab().createJRElement(jasperDesign);
		this.crosstab = new MCrosstab(null, jrCrosstab, 1, new CrosstabManager(jrCrosstab));

		step1 = new WizardDatasetPage(jasperDesign);
		addPage(step1);
		MElementDataset dataset = (MElementDataset) crosstab.getPropertyValue(JRDesignCrosstab.PROPERTY_DATASET);
		MDatasetRun mdataset = (MDatasetRun) dataset.getPropertyValue(JRDesignElementDataset.PROPERTY_DATASET_RUN);
		step1.setDataSetRun(mdataset);

		step2 = new WizardConnectionPage();
		addPage(step2);
		step2.setDataSetRun(mdataset);

		step3 = new CrosstabWizardColumnPage();
		addPage(step3);

		step4 = new CrosstabWizardRowPage();
		addPage(step4);

		step5 = new CrosstabWizardMeasurePage();
		addPage(step5);

		step6 = new CrosstabWizardLayoutPage();
		addPage(step6);
		step6.setCrosstab(crosstab);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		String dsname = (String) step1.getDataSetRun().getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
		if (page == step1 && (dsname == null || dsname.equals(""))) //$NON-NLS-1$
			page = step2;
		if (page == step2) {
			if (dsname != null && !dsname.equals("")) { //$NON-NLS-1$
				List<JRDesignField> flist = ModelUtils.getFields4Datasource(jasperDesign, dsname);
				if (step3.getFields() != null)
					flist.removeAll(step3.getFields());
				step4.setFields(flist);

				flist = ModelUtils.getFields4Datasource(jasperDesign, dsname);
				if (step4.getFields() != null)
					flist.removeAll(step4.getFields());
				step3.setFields(flist);
			} else
				page = step3;
		}
		if (page == step3) {
			if (dsname != null && !dsname.equals("")) { //$NON-NLS-1$
				// exclude step3 fields
				List<JRDesignField> flist = ModelUtils.getFields4Datasource(jasperDesign, dsname);
				if (step3.getFields() != null)
					flist.removeAll(step3.getFields());
				step4.setFields(flist);

				flist = ModelUtils.getFields4Datasource(jasperDesign, dsname);
				if (step4.getFields() != null)
					flist.removeAll(step4.getFields());
				step3.setFields(flist);

			} else
				page = step4;
		}
		if (page == step4) {
			if (dsname != null && !dsname.equals("")) //$NON-NLS-1$
				step5.setFields(ModelUtils.getFields4Datasource(jasperDesign, dsname));
			else
				page = step5;
		}
		return super.getNextPage(page);
	}

	public MCrosstab getCrosstab() {
		JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
		JRDesignCrosstabDataset jrDataSet = (JRDesignCrosstabDataset) jdc.getDataset();
		if (jrDataSet.getDatasetRun().getDatasetName() == null)
			jrDataSet.setDatasetRun(null);

		if (step5.getFields() != null)
			for (JRDesignField f : step5.getFields()) {
				try {
					JRDesignCrosstabMeasure m = CreateMeasureCommand.createMesure(jdc, f.getName() + "_MEASURE"); //$NON-NLS-1$
					JRDesignExpression jre = new JRDesignExpression();
					jre.setValueClassName(f.getValueClassName());
					jre.setText("$F{" + f.getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$
					m.setValueExpression(jre);
					m.setCalculation(CalculationEnum.COUNT);

					m.setPercentageType(CrosstabPercentageEnum.NONE);

					if (m.getCalculationValue().equals(CalculationEnum.COUNT))
						m.setValueClassName(Integer.class.getName());
					else
						m.setValueClassName(m.getValueExpression().getValueClassName());

					jdc.addMeasure(m);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		if (step3.getFields() != null)
			for (JRDesignField f : step3.getFields()) {
				try {
					JRDesignCrosstabColumnGroup colGroup = CreateColumnGroupCommand.createColumnGroup(jasperDesign, jdc,
							f.getName());
					if (step6.isAddColTotal())
						colGroup.setTotalPosition(CrosstabTotalPositionEnum.END);
					else
						colGroup.setTotalPosition(CrosstabTotalPositionEnum.NONE);
					((JRDesignExpression) colGroup.getBucket().getExpression()).setText("$F{" + f.getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$

					CreateColumnGroupCommand.addColumnGroup(jdc, colGroup, -1);

				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		if (step4.getFields() != null)
			for (JRDesignField f : step4.getFields()) {
				try {
					JRDesignCrosstabRowGroup colGroup = CreateRowGroupCommand.createRowGroup(jasperDesign, jdc, f.getName());
					if (step6.isAddRowTotal())
						colGroup.setTotalPosition(CrosstabTotalPositionEnum.END);
					else
						colGroup.setTotalPosition(CrosstabTotalPositionEnum.NONE);

					((JRDesignExpression) colGroup.getBucket().getExpression()).setText("$F{" + f.getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$

					CreateRowGroupCommand.addRowGroup(jdc, colGroup, -1, step6.isAddRowTotal());

				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		List<JRDesignCrosstabColumnGroup> columns = jdc.getColumnGroupsList();
		for (JRDesignCrosstabColumnGroup colGroup : columns) {
			for (JRElement e : colGroup.getHeader().getElements()) {
				e.setWidth(colGroup.getHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getHeader().getHeight());
			}
			for (JRElement e : colGroup.getTotalHeader().getElements()) {
				e.setWidth(colGroup.getTotalHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getTotalHeader().getHeight());
			}
		}
		List<JRDesignCrosstabRowGroup> rows = jdc.getRowGroupsList();
		for (JRDesignCrosstabRowGroup colGroup : rows) {
			for (JRElement e : colGroup.getHeader().getElements()) {
				e.setWidth(colGroup.getHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getHeader().getHeight());
			}
			for (JRElement e : colGroup.getTotalHeader().getElements()) {
				e.setWidth(colGroup.getTotalHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getTotalHeader().getHeight());
			}
		}
		List<JRDesignCrosstabCell> cells = jdc.getCellsList();
		JRCrosstabMeasure[] measures = jdc.getMeasures();
		if (measures != null && cells != null)
			for (JRDesignCrosstabCell c : cells) {
				int y = 0;
				if (c.getHeight() != null) {
					int h = c.getHeight() / measures.length;
					for (int i = 0; i < measures.length; i++) {
						JRDesignExpression exp = new JRDesignExpression();
						exp.setValueClassName(measures[i].getValueClassName()); //$NON-NLS-1$
						exp.setText("$V{" + measures[i].getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$

						JRDesignTextField tf = (JRDesignTextField) new MTextField().createJRElement(jasperDesign);
						tf.setX(0);
						tf.setY(y);
						tf.setWidth(c.getWidth());
						tf.setHeight(h);
						//			if ("Crosstab Data Text" != null && jasperDesign.getStylesMap().containsKey("Crosstab Data Text")) { //$NON-NLS-1$ //$NON-NLS-2$
						//				tf.setStyle((JRStyle) jasperDesign.getStylesMap().get("Crosstab Data Text")); //$NON-NLS-1$
						// }
						tf.setExpression(exp);
						((JRDesignCellContents) c.getContents()).addElement(tf);
						y += h;
					}
				}
			}

		return crosstab;
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
