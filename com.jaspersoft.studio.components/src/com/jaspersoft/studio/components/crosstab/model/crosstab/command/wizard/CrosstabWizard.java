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
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.components.crosstab.CrosstabManager;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.measure.command.CreateMeasureCommand;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.command.CreateRowGroupCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.MElementDataset;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

public class CrosstabWizard extends JSSWizard {
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
		JRDesignCrosstab jrCrosstab = (JRDesignCrosstab) new MCrosstab()
				.createJRElement(getConfig().getJasperDesign());
		crosstab = new MCrosstab(null, jrCrosstab, 1, new CrosstabManager(
				jrCrosstab));
		crosstab.setJasperConfiguration(getConfig());

		step1 = new WizardDatasetPage(getConfig());
		addPage(step1);
		MElementDataset dataset = (MElementDataset) crosstab
				.getPropertyValue(JRDesignCrosstab.PROPERTY_DATASET);
		MDatasetRun mdataset = (MDatasetRun) dataset
				.getPropertyValue(JRDesignElementDataset.PROPERTY_DATASET_RUN);
		mdataset.setPropertyValue(
				JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION,
				"$P{REPORT_CONNECTION}");
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

	private JRDesignDataset getDataset() {
		JasperDesign jd = getConfig().getJasperDesign();
		List<JRDataset> datasetsList = jd.getDatasetsList();
		MDatasetRun dataSetRun = step1.getDataSetRun();
		JRDesignDataset ds = null;
		if (dataSetRun == null) {
			MDataset mds = (MDataset) getConfig().get(DatasetWizard.DATASET);
			if (mds != null)
				ds = mds.getValue();
		} else {
			String dsname = (String) dataSetRun
					.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
			for (JRDataset d : datasetsList)
				if (d.getName().equals(dsname)) {
					ds = (JRDesignDataset) d;
					break;
				}
		}
		return ds;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		JasperDesign jd = getConfig().getJasperDesign();
		List<JRDataset> datasetsList = jd.getDatasetsList();
		JRDesignDataset ds = getDataset();
		if (page == step1) {
			if (datasetsList.size() == 0)
				return step2;
		}
		JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
		if (page == step2) {
			if (ds != null) {
				List<Object> m = new ArrayList<Object>();
				if (colGroups == null
						|| !colGroups.getDsname().equals(ds.getName())) {
					colGroups = new ReportObjects(
							ModelUtils.getReportObjects4Datasource(ds),
							ds.getName());
					for (Object f : colGroups.getReportObects())
						m.add(createColumnGroups(jdc, f));
					step3.setFields(m);
				}
				setupColumns();
				setupRows();
			} else
				page = step3;
		}
		if (page == step3) {
			if (ds != null) {
				List<Object> m = new ArrayList<Object>();
				if (rowGroups == null
						|| !rowGroups.getDsname().equals(ds.getName())) {
					rowGroups = new ReportObjects(
							ModelUtils.getReportObjects4Datasource(ds),
							ds.getName());
					for (Object f : colGroups.getReportObects())
						m.add(createRowGroups(jdc, f));
					step4.setFields(m);
				}
				setupColumns();
				setupRows();
			} else
				page = step4;
		}
		if (page == step4) {
			if (ds != null) {

				if (mesures == null
						|| !mesures.getDsname().equals(ds.getName())) {
					List<Object> m = new ArrayList<Object>();
					mesures = new ReportObjects(
							ModelUtils.getReportObjects4Datasource(ds),
							ds.getName());
					for (Object f : mesures.getReportObects())
						m.add(createMesures(jdc, f));
					step5.setFields(m);
				}
			} else
				page = step5;
		}
		return super.getNextPage(page);
	}

	private void setupColumns() {
		List<Object> m;
		if (step4.getFields() != null && step3.getInFields() != null) {
			m = new ArrayList<Object>();
			for (Object f : step3.getInFields()) {
				JRDesignCrosstabColumnGroup cg = (JRDesignCrosstabColumnGroup) f;
				boolean skip = false;
				for (Object obj : step4.getFields()) {
					JRDesignCrosstabRowGroup rg = (JRDesignCrosstabRowGroup) obj;
					if (cg.getBucket().getExpression().getText()
							.equals(rg.getBucket().getExpression().getText())) {
						skip = true;
						break;
					}
				}
				if (!skip)
					m.add(cg);
			}
			step3.setFields(m);
		}
	}

	private void setupRows() {
		List<Object> m;
		if (step3.getFields() != null && step4.getInFields() != null) {
			m = new ArrayList<Object>();
			for (Object f : step4.getInFields()) {
				JRDesignCrosstabRowGroup cg = (JRDesignCrosstabRowGroup) f;
				boolean skip = false;
				for (Object obj : step3.getFields()) {
					JRDesignCrosstabColumnGroup rg = (JRDesignCrosstabColumnGroup) obj;
					if (cg.getBucket().getExpression().getText()
							.equals(rg.getBucket().getExpression().getText())) {
						skip = true;
						break;
					}
				}
				if (!skip)
					m.add(cg);
			}
			step4.setFields(m);
		}
	}

	public MCrosstab getCrosstab() {
		JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
		JRDesignCrosstabDataset jrDataSet = (JRDesignCrosstabDataset) jdc
				.getDataset();
		if (jrDataSet.getDatasetRun().getDatasetName() == null)
			jrDataSet.setDatasetRun(null);

		if (step5.getFields() != null)
			for (Object f : step5.getFields()) {
				try {
					jdc.addMeasure((JRDesignCrosstabMeasure) f);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		if (step3.getFields() != null)
			for (Object f : step3.getFields()) {
				try {
					CreateColumnGroupCommand.addColumnGroup(jdc,
							(JRDesignCrosstabColumnGroup) f, -1);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		if (step4.getFields() != null)
			for (Object f : step4.getFields()) {
				try {
					CreateRowGroupCommand.addRowGroup(jdc,
							(JRDesignCrosstabRowGroup) f, -1);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		setupColumnGroups(jdc);
		setupRowGroups(jdc);
		createDetailCells(jdc);

		return crosstab;
	}

	private void createDetailCells(JRDesignCrosstab jdc) {
		List<JRCrosstabCell> cells = jdc.getCellsList();
		JRCrosstabMeasure[] measures = jdc.getMeasures();
		if (measures != null && cells != null)
			for (JRCrosstabCell c : cells) {
				int y = 0;
				if (c.getHeight() != null && measures.length > 0) {
					int h = c.getHeight() / measures.length;
					for (int i = 0; i < measures.length; i++) {
						JRDesignExpression exp = new JRDesignExpression();
						exp.setText("$V{" + measures[i].getName() + "}"); //$NON-NLS-1$ //$NON-NLS-2$

						JRDesignTextField tf = (JRDesignTextField) new MTextField()
								.createJRElement(getConfig().getJasperDesign());
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
	}

	private void setupRowGroups(JRDesignCrosstab jdc) {
		List<JRCrosstabRowGroup> rows = jdc.getRowGroupsList();
		for (JRCrosstabRowGroup colGroup : rows) {
			for (JRElement e : colGroup.getHeader().getElements()) {
				e.setWidth(colGroup.getHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getHeader()
						.getHeight());
			}
			for (JRElement e : colGroup.getTotalHeader().getElements()) {
				e.setWidth(colGroup.getTotalHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getTotalHeader()
						.getHeight());
			}
		}
	}

	private void setupColumnGroups(JRDesignCrosstab jdc) {
		List<JRCrosstabColumnGroup> columns = jdc.getColumnGroupsList();
		for (JRCrosstabColumnGroup colGroup : columns) {
			for (JRElement e : colGroup.getHeader().getElements()) {
				e.setWidth(colGroup.getHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getHeader()
						.getHeight());
			}
			for (JRElement e : colGroup.getTotalHeader().getElements()) {
				e.setWidth(colGroup.getTotalHeader().getWidth());
				((JRDesignElement) e).setHeight(colGroup.getTotalHeader()
						.getHeight());
			}
		}
	}

	private JRDesignCrosstabRowGroup createRowGroups(JRDesignCrosstab jdc,
			Object f) {
		String name = "";
		String txt = "";
		if (f instanceof JRField) {
			JRField fi = (JRField) f;
			name = fi.getName();
			txt = "$F{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (f instanceof JRParameter) {
			JRParameter fi = (JRParameter) f;
			name = fi.getName();
			txt = "$P{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (f instanceof JRVariable) {
			JRVariable fi = (JRVariable) f;
			name = fi.getName();
			txt = "$V{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		JRDesignCrosstabRowGroup rowGroup = CreateRowGroupCommand
				.createRowGroup(getConfig().getJasperDesign(), jdc, name);

		((JRDesignExpression) rowGroup.getBucket().getExpression())
				.setText(txt);
		return rowGroup;
	}

	private JRDesignCrosstabColumnGroup createColumnGroups(
			JRDesignCrosstab jdc, Object f) {
		String name = "";
		String txt = "";
		if (f instanceof JRField) {
			JRField fi = (JRField) f;
			name = fi.getName();
			txt = "$F{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (f instanceof JRParameter) {
			JRParameter fi = (JRParameter) f;
			name = fi.getName();
			txt = "$P{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (f instanceof JRVariable) {
			JRVariable fi = (JRVariable) f;
			name = fi.getName();
			txt = "$V{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		JRDesignCrosstabColumnGroup colGroup = CreateColumnGroupCommand
				.createColumnGroup(getConfig().getJasperDesign(), jdc, name);
		((JRDesignExpression) colGroup.getBucket().getExpression())
				.setText(txt);

		return colGroup;
	}

	private JRDesignCrosstabMeasure createMesures(JRDesignCrosstab jdc, Object f) {

		JRDesignExpression jre = new JRDesignExpression();
		String name = "";
		String classname = "";
		if (f instanceof JRField) {
			JRField fi = (JRField) f;
			name = fi.getName();
			classname = fi.getValueClassName();
			jre.setText("$F{" + name + "}"); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (f instanceof JRParameter) {
			JRParameter fi = (JRParameter) f;
			name = fi.getName();
			classname = fi.getValueClassName();
			jre.setText("$P{" + name + "}"); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (f instanceof JRVariable) {
			JRVariable fi = (JRVariable) f;
			classname = fi.getValueClassName();
			name = fi.getName();
			jre.setText("$V{" + name + "}"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		JRDesignCrosstabMeasure m = CreateMeasureCommand.createMesure(jdc, name
				+ "_MEASURE"); //$NON-NLS-1$		
		m.setValueExpression(jre);
		m.setCalculation(CalculationEnum.COUNT);

		m.setPercentageType(CrosstabPercentageEnum.NONE);

		if (m.getCalculationValue().equals(CalculationEnum.COUNT)
				|| m.getCalculationValue().equals(
						CalculationEnum.DISTINCT_COUNT))
			m.setValueClassName(Integer.class.getName());
		else
			m.setValueClassName(classname);
		return m;
	}

	private ReportObjects colGroups;
	private ReportObjects rowGroups;
	private ReportObjects mesures;

	private class ReportObjects {
		private List<Object> reportObects;
		private String dsname;

		public List<Object> getReportObects() {
			return reportObects;
		}

		public String getDsname() {
			return dsname;
		}

		public ReportObjects(List<Object> reportObects, String dsname) {
			super();
			this.reportObects = reportObects;
			this.dsname = dsname;
		}

	}

	@Override
	public void init(JasperReportsConfiguration jConfig) {
		super.init(jConfig);
		if (crosstab != null)
			crosstab.setJasperConfiguration(jConfig);
	}

}
