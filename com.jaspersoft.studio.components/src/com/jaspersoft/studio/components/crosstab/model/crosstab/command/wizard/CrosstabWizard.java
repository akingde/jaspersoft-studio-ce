/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.components.crosstab.CrosstabManager;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.dialog.ApplyCrosstabStyleAction;
import com.jaspersoft.studio.components.crosstab.model.measure.command.CreateMeasureCommand;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;
import com.jaspersoft.studio.wizards.JSSWizardPageChangeEvent;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.CalculationEnum;

public class CrosstabWizard extends JSSWizard {

	public static final String CROSSTAB_COLUMNS = "CROSSTAB_COLUMNS";
	public static final String CROSSTAB_ROWS = "CROSSTAB_ROWS";
	public static final String CROSSTAB_MEASURES = "CROSSTAB_MEASURES";

	private WizardDatasetPage step1;
	private CrosstabWizardColumnPage step2;
	private CrosstabWizardRowPage step3;
	private CrosstabWizardMeasurePage step4;
	private CrosstabWizardLayoutPage step5;

	private ReportObjects colGroups;
	private ReportObjects rowGroups;
	private ReportObjects measures;
	
	// private CrosstabWizardLayoutPage step6;
	// private WizardConnectionPage step2;

	private MCrosstab crosstab;

	public CrosstabWizard() {
		super();
		setWindowTitle(Messages.common_crosstab_wizard);
	}

	@Override
	public void addPages() {
		JasperDesign jd = getConfig().getJasperDesign();
		JRDesignCrosstab jrCrosstab = (JRDesignCrosstab) new MCrosstab().createJRElement(jd);
		crosstab = new MCrosstab(null, jrCrosstab, 1, new CrosstabManager(jrCrosstab, jd));
		crosstab.setJasperConfiguration(getConfig());

		step1 = new WizardDatasetPage("Crosstab"){
			
			protected void clearSettings(){
				Map<String, Object> settings = ((JSSWizard) getWizard()).getSettings();
				if (settings != null){
					step2.clearSelection();
					step3.clearSelection();
					step4.clearSelection();
					getContainer().updateButtons();
				}
			}
			
			protected void handleComboBoxDatasetSelected(org.eclipse.swt.widgets.Event event) {
				super.handleComboBoxDatasetSelected(event);
				clearSettings();
			};
			
			@Override
			protected void handleOptionSelected() {
				super.handleOptionSelected();
				clearSettings();
			}
			
		};
		addPage(step1);

		step2 = new CrosstabWizardColumnPage();
		addPage(step2);

		step3 = new CrosstabWizardRowPage();
		addPage(step3);

		step4 = new CrosstabWizardMeasurePage();
		addPage(step4);

		step5 = new CrosstabWizardLayoutPage();
		addPage(step5);
	}

	/**
	 * This method returns a dataset object based on what has been selected in
	 * the first step of the wizard (existing dataset, main dataset, new
	 * dataset, etc...)
	 * 
	 * @return JRDesignDataset
	 */
	public JRDesignDataset getDataset() {
		return step1.getSelectedDataset();
	}

	/**
	 * The getNextPage implementations does nothing, since all the logic has
	 * been moved inside each page, which has a special version created for this
	 * wizard
	 * 
	 * @see com.jaspersoft.studio.wizards.JSSWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 * @see com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard.CrosstabWizardColumnPage
	 * @see com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard.CrosstabWizardRowPage
	 * @see com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard.CrosstabWizardMeasurePage
	 * 
	 * @param the
	 *            current page.
	 * 
	 * @return the next page
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		// Nothing to do. If you change this method, please update the comment
		return super.getNextPage(page);
	}

	@SuppressWarnings("unchecked")
	public MCrosstab getCrosstab() {

		JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
		JRDesignDataset dataset = getDataset();

		getAvailableColumnGroups();
		getAvailableRowGroups();

		if (dataset.isMainDataset()) {
			// main dataset selected => dataset run is null
			((JRDesignCrosstabDataset) jdc.getDataset()).setDatasetRun(null);
		} else {
			JRDesignDatasetRun datasetRun = (JRDesignDatasetRun) jdc
					.getDataset().getDatasetRun();
			if (datasetRun == null) {
				datasetRun = new JRDesignDatasetRun();
				((JRDesignCrosstabDataset) jdc.getDataset())
						.setDatasetRun(datasetRun);
			}
			datasetRun.setDatasetName(dataset.getName());
			datasetRun.setConnectionExpression(ExprUtil.createExpression(
					"$P{REPORT_CONNECTION}", "java.sql.Connection"));
		}

		// Add measures...
		List<Object> measures = (List<Object>) getSettings().get(CROSSTAB_MEASURES);
		if (measures != null) {
			for (Object obj : measures) {
				try {
					JRDesignCrosstabMeasure m = (JRDesignCrosstabMeasure) obj;
					// m.setName(ModelUtils.getDefaultName(jdc, m.getName()));
					jdc.addMeasure(m);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}

		// Add column groups...
		List<Object> columnGroups = (List<Object>) getSettings().get(
				CROSSTAB_COLUMNS);
		if (columnGroups != null) {
			for (Object obj : columnGroups) {
				try {
					JRDesignCrosstabColumnGroup c = (JRDesignCrosstabColumnGroup) obj;
					// c.setName(ModelUtils.getDefaultName(jdc, c.getName()));
					CrosstabUtil.addColumnGroup(jdc, c, -1, 20 * jdc.getMeasures().length);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}

		// Add row groups...
		List<Object> rowGroups = (List<Object>) getSettings().get(CROSSTAB_ROWS);
		if (rowGroups != null) {
			for (Object obj : rowGroups) {
				try {
					JRDesignCrosstabRowGroup r = (JRDesignCrosstabRowGroup) obj;
					// r.setName(ModelUtils.getDefaultName(jdc, r.getName()));
					CrosstabUtil.addRowGroup(jdc, r, -1, 20 * jdc.getMeasures().length);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}

		setupColumnGroups(jdc);
		setupRowGroups(jdc);
		createDetailCells(jdc);
		crosstab.getValue().preprocess();
		setupMeasures(jdc);
		// Apply the template style to the crosstab
		ApplyCrosstabStyleAction applyAction = new ApplyCrosstabStyleAction(
				step5.getSelectedStyle(), crosstab.getValue());
		applyAction.applayStyle(getConfig().getJasperDesign());
		return crosstab;
	}

	private void createDetailCells(JRDesignCrosstab jdc) {
		List<JRCrosstabCell> cells = jdc.getCellsList();
		JRCrosstabMeasure[] measures = jdc.getMeasures();
		if (measures != null && cells != null)
			for (JRCrosstabCell c : cells) {
				int y = 0;
				if (c.getHeight() != null && measures.length > 0) {
					//((JRDesignCrosstabCell)c).setHeight(20 * measures.length);
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
						tf.setExpression(exp);
						((JRDesignCellContents) c.getContents()).addElement(tf);
						y += h;
					}
				}
			}
	}

	private void setupRowGroups(JRDesignCrosstab jdc) {
		List<JRCrosstabRowGroup> rows = jdc.getRowGroupsList();
		for (JRCrosstabRowGroup rowGroup : rows) {
			((JRDesignCellContents)rowGroup.getHeader()).setHeight(20 * jdc.getMeasures().length);
			for (JRElement e : rowGroup.getHeader().getElements()) {
				JRDesignElement el = (JRDesignElement) e;
				el.setWidth(rowGroup.getHeader().getWidth());
				el.setHeight(rowGroup.getHeader().getHeight());
			}

			for (JRElement e : rowGroup.getTotalHeader().getElements()) {
				JRDesignElement el = (JRDesignElement) e;
				el.setWidth(rowGroup.getTotalHeader().getWidth());
				el.setHeight(rowGroup.getTotalHeader().getHeight());
			}
		}
	}

	private void setupColumnGroups(JRDesignCrosstab jdc) {
		List<JRCrosstabColumnGroup> columns = jdc.getColumnGroupsList();
		for (JRCrosstabColumnGroup colGroup : columns) {
			for (JRElement e : colGroup.getHeader().getElements()) {
				JRDesignElement el = (JRDesignElement) e;
				el.setWidth(colGroup.getHeader().getWidth());
				el.setHeight(colGroup.getHeader().getHeight());
			}
			for (JRElement e : colGroup.getTotalHeader().getElements()) {
				JRDesignElement el = (JRDesignElement) e;
				el.setWidth(colGroup.getTotalHeader().getWidth());
				el.setHeight(colGroup.getTotalHeader().getHeight());
			}
		}
	}

	private JRDesignCrosstabRowGroup createRowGroups(JRDesignCrosstab jdc,
			Object f) {
		String name = "";
		String txt = "";
		String vclass = "";
		if (f instanceof JRField) {
			JRField fi = (JRField) f;
			name = fi.getName();
			txt = "$F{" + name + "}";
			vclass = fi.getValueClassName();
		} else if (f instanceof JRParameter) {
			JRParameter fi = (JRParameter) f;
			name = fi.getName();
			txt = "$P{" + name + "}";
			vclass = fi.getValueClassName();
		} else if (f instanceof JRVariable) {
			JRVariable fi = (JRVariable) f;
			name = fi.getName();
			txt = "$V{" + name + "}";
			vclass = fi.getValueClassName();
		}

		CrosstabTotalPositionEnum total = step5.isAddRowTotal() ? CrosstabTotalPositionEnum.END
				: CrosstabTotalPositionEnum.NONE;

		JRDesignCrosstabRowGroup rowGroup = CrosstabUtil.createRowGroup(getConfig().getJasperDesign(), jdc, name, total);

		((JRDesignExpression) rowGroup.getBucket().getExpression())
				.setText(txt);
		((JRDesignCrosstabBucket) rowGroup.getBucket())
				.setValueClassName(vclass);
		return rowGroup;
	}

	private JRDesignCrosstabColumnGroup createColumnGroups(
			JRDesignCrosstab jdc, Object f) {
		String name = ""; //$NON-NLS-1$
		String txt = ""; //$NON-NLS-1$
		String vclass = "";
		if (f instanceof JRField) {
			JRField fi = (JRField) f;
			name = fi.getName();
			txt = "$F{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
			vclass = fi.getValueClassName();
		} else if (f instanceof JRParameter) {
			JRParameter fi = (JRParameter) f;
			name = fi.getName();
			txt = "$P{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
			vclass = fi.getValueClassName();
		} else if (f instanceof JRVariable) {
			JRVariable fi = (JRVariable) f;
			name = fi.getName();
			txt = "$V{" + name + "}"; //$NON-NLS-1$ //$NON-NLS-2$
			vclass = fi.getValueClassName();
		}

		CrosstabTotalPositionEnum total = step5.isAddColTotal() ? CrosstabTotalPositionEnum.END
				: CrosstabTotalPositionEnum.NONE;

		JRDesignCrosstabColumnGroup colGroup =  CrosstabUtil.createColumnGroup(getConfig().getJasperDesign(), jdc, name, total);

		((JRDesignExpression) colGroup.getBucket().getExpression())
				.setText(txt);
		((JRDesignCrosstabBucket) colGroup.getBucket())
				.setValueClassName(vclass);

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
			jre.setText("$F{" + name + "}");
		} else if (f instanceof JRParameter) {
			JRParameter fi = (JRParameter) f;
			name = fi.getName();
			classname = fi.getValueClassName();
			jre.setText("$P{" + name + "}");
		} else if (f instanceof JRVariable) {
			JRVariable fi = (JRVariable) f;
			classname = fi.getValueClassName();
			name = fi.getName();
			jre.setText("$V{" + name + "}");
		}

		JRDesignCrosstabMeasure m = CreateMeasureCommand.createMesure(jdc, name
				+ "_MEASURE");
		m.setValueExpression(jre);
		m.setCalculation(CalculationEnum.COUNT);
		m.setValueClassName(classname);
		m.setPercentageType(CrosstabPercentageEnum.NONE);

		return m;
	}

	private void setupMeasures(JRDesignCrosstab jdc) {
		for (JRCrosstabMeasure cm : jdc.getMeasures()) {
			CalculationEnum calculationValue = cm.getCalculationValue();
			if(calculationValue == null)
				continue;
			if (calculationValue.equals(CalculationEnum.COUNT)
					|| calculationValue.equals(
							CalculationEnum.DISTINCT_COUNT))
				((JRDesignCrosstabMeasure) cm).setValueClassName(Integer.class
						.getName());
		}

	}

	/**
	 * This inner class is used to cache set of objects based in the selected
	 * data source.
	 * 
	 * @author gtoffoli
	 * 
	 */
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
	public void setConfig(JasperReportsConfiguration jConfig, boolean disposeConfig) {
		super.setConfig(jConfig, disposeConfig);
		if (crosstab != null)
			crosstab.setJasperConfiguration(jConfig);
	}

	public static void setBucketExpression(JRDesignCrosstabBucket bucket,
			String oldExpText, AgregationFunctionEnum function) {
		JRDesignExpression exp = (JRDesignExpression) bucket.getExpression();
		if (function == AgregationFunctionEnum.UNIQUE)
			exp.setText(oldExpText);
		else if (function == AgregationFunctionEnum.YEAR) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"yyyy\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.MONTH) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"MM\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.WEEK) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"ww\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.DAY) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"dd\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.DAY_OF_THE_WEEK) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"u\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.HOUR) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"HH\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.MINUTE) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"mm\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.SECOND) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"ss\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.MILLISECOND) {
			exp.setText("Integer.parseInt(new SimpleDateFormat(\"SSSS\").format(" + oldExpText + "))");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.QUARTER) {
			exp.setText("(Integer.parseInt(new SimpleDateFormat(\"MM\").format(" + oldExpText + "))/4)+1");
			bucket.setValueClassName(Integer.class.getName());
		} else if (function == AgregationFunctionEnum.SEMESTER) {
			exp.setText("(Integer.parseInt(new SimpleDateFormat(\"MM\").format(" + oldExpText + "))/6)+1");
			bucket.setValueClassName(Integer.class.getName());
		}

		bucket.setExpression(exp);
	}

	/**
	 * 
	 * Return a list of JRCrosstabColumnGroup to be used from a
	 * CrosstabWizardColumnPage
	 * 
	 * @param CrosstabWizard
	 * 
	 * @return void
	 */
	public List<Object> getAvailableColumnGroups() {
		List<Object> objects = new ArrayList<Object>();
		JRDesignDataset ds = getDataset();
		if (ds == null)
			return objects;

		String dsname = ds.getName();
		if (ds.isMainDataset())
			dsname = "_MAIN_DATASET"; //$NON-NLS-1$
		if (colGroups == null || !colGroups.getDsname().equals(dsname)) {
			colGroups = new ReportObjects(
					ModelUtils.getReportObjects4Datasource(ds), dsname);
		}

		if (colGroups != null) {
			JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
			for (Object f : colGroups.getReportObects()) {
				objects.add(createColumnGroups(jdc, f));
			}
		}
		return objects;
	}

	/**
	 * 
	 * Return a list of JRCrosstabRowGroup to be used from a
	 * CrosstabWizardColumnPage
	 * 
	 * @param CrosstabWizard
	 * 
	 * @return void
	 */
	public List<Object> getAvailableRowGroups() {
		List<Object> objects = new ArrayList<Object>();
		JRDesignDataset ds = getDataset();
		if (ds == null)
			return objects;

		String dsname = ds.getName();
		if (ds.isMainDataset())
			dsname = "_MAIN_DATASET"; //$NON-NLS-1$
		if (rowGroups == null || !rowGroups.getDsname().equals(dsname)) {
			rowGroups = new ReportObjects(
					ModelUtils.getReportObjects4Datasource(ds), dsname);
		}

		if (rowGroups != null) {
			//Get the columns selected in the step2 and avoid to propose them as rows too
			List<Wrapper> selectedColumns = step2.getSelectedFieldWrappers();
			HashSet<String> selectedColExpressions = new HashSet<String>();
			for(Wrapper wrapper : selectedColumns){
				String usedObject = wrapper.getBaseObjectExpression();
				selectedColExpressions.add(usedObject);
			}
			JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
			for (Object f : rowGroups.getReportObects()) {
				JRDesignCrosstabRowGroup row = createRowGroups(jdc, f);
				String rowExpression = row.getBucket().getExpression().getText();
				if (!selectedColExpressions.contains(rowExpression)){
					objects.add(row);
				}
			}
		}
		return objects;
	}

	/**
	 * 
	 * Return a list of JRCrosstabRowGroup to be used from a
	 * CrosstabWizardColumnPage
	 * 
	 * @param CrosstabWizard
	 * 
	 * @return void
	 */
	public List<Object> getAvailableMeasures() {
		List<Object> objects = new ArrayList<Object>();
		JRDesignDataset ds = getDataset();
		if (ds == null)
			return objects;

		String dsname = ds.getName();
		if (ds.isMainDataset())
			dsname = "_MAIN_DATASET"; //$NON-NLS-1$
		if (measures == null || !measures.getDsname().equals(dsname)) {
			measures = new ReportObjects(
					ModelUtils.getReportObjects4Datasource(ds), dsname);
		}

		if (measures != null) {
			JRDesignCrosstab jdc = (JRDesignCrosstab) crosstab.getValue();
			for (Object f : measures.getReportObects()) {
				objects.add(createMesures(jdc, f));
			}
		}
		return objects;
	}

	/**
	 * If the structure of a new dataset changes, we need to recalculate all the
	 * object to propose as column/row groups and measures.
	 * 
	 * This method is a callback from pages that call fireChangeEvent() and are
	 * attached to this wizard or a children wizard.
	 * 
	 * Pages can be recognized by page name, we are interested in listening to
	 * the "tablepage" page.
	 * 
	 * @see com.jaspersoft.studio.wizards.JSSWizard#pageChanged(com.jaspersoft.studio.wizards.JSSWizardPageChangeEvent)
	 * 
	 * @return
	 */
	@Override
	public void pageChanged(JSSWizardPageChangeEvent event) {
		super.pageChanged(event);

		if (event.getPage().getName().equals("tablepage")) //$NON-NLS-1$
		{
			colGroups = null;
			rowGroups = null;
			measures = null;
		}
	}

}
