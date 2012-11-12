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
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.engine.JRDatasetRun;
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
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.command.CreateDatasetCommand;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.command.CreateStyleCommand;
import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
import com.jaspersoft.studio.property.dataset.wizard.WizardConnectionPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetPage;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSWizard;

public class TableWizard extends JSSWizard {
	private WizardDatasetPage step1;
	private TableWizardFieldsPage step3;
	private WizardConnectionPage step2;
	private TableWizardLayoutPage step4;
	private MTable table = null;;
	
	/**
	 * The set of styles that will be created as the table is added to the report
	 */
	private List<JRDesignStyle> styleList;
	
	
	float baseColor = new Float(Math.tan(Math.toRadians(208.0)));

	public TableWizard() {
		super();
		setWindowTitle(Messages.common_table_wizard);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		
		step1 = new WizardDatasetPage(false, "Table");
		addPage(step1);
		
		step2 = new WizardConnectionPage();
		addPage(step2);
		
		
		// Setting up the expressions context. This is not really useful, since
		// we still don't know where the element will be added, so this call will fall back to the default dataset.
		// FIXME: pass a proper ANode to the wizard to let the code to lookup for a more appropriate dataset.
		step2.setExpressionContext(ModelUtils.getElementExpressionContext(null, null));

		step3 = new TableWizardFieldsPage();
		addPage(step3);

		step4 = new TableWizardLayoutPage();
		addPage(step4);
	}

	/**
	 * The getNextPage implementations does nothing, since all the logic has
	 * been moved inside each page, specifically extended for
	 * this wizard
	 * 
	 * @see com.jaspersoft.studio.wizards.JSSWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 *
	 * @param the current page.
	 *
	 * @return the next page
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		
		// Nothing to do. If you change this method, please update the
		// comment.
		
		return super.getNextPage(page);
	}
	
	/**
	 * This method returns a dataset object
	 * based on what has been selected in the first step
	 * of the wizard (existing dataset, main dataset, new dataset, etc...)
	 * 
	 *  @return JRDesignDataset
	 */
	public JRDesignDataset getDataset() {
		return step1.getSelectedDataset();
	}
		

	/* ************************************************************** */
	// Table generation code...
	
	
	
	/**
	 * 
	 * Generates the table created by this wizard.
	 * This method will generate the table only the first time it is called, then
	 * a cached version will be returned, this because the creation of the table
	 * involved the creation of a set of commands, and we don't want to create
	 * commands twice. The second time the call is made, the cached table will be
	 * returned.</br>
	 * </br>
	 * Please note that if this method is invoked before the end of the wizard, the final table may
	 * result incomplete.
	 * 
	 * 
	 * @param tableWidth
	 * 				An optional width to be used as size of the table to create. This will help
	 *              to calculate the columns width.
	 *
	 *  @return MTable
	 *  			An MTable object with a JasperReports configuration attached.
	 */
	public MTable getTable(int tableWidth) {
		
		if (table != null) return table;
		
		table = new MTable();
		table.setValue(table.createJRElement(getConfig().getJasperDesign()));
		table.setJasperConfiguration(getConfig());
	
		List<Object> lst = step3.getSelectedFields();
		
		StandardTable tbl = TableManager.getTable(table);

		// Configure a proper dataset run...
		JRDesignDataset dataset = getDataset();
		
		JRDesignDatasetRun datasetRun = step2.getJRDesignDatasetRun();
		if (datasetRun == null)
		{
			datasetRun = new JRDesignDatasetRun();
			
		}
		datasetRun.setDatasetName( dataset.isMainDataset() ? null : dataset.getName() );
		tbl.setDatasetRun(datasetRun);
		
		
		// Create a command to add the styles...
		
		
		// Get the connection/datasource expression from the proper wizard step...
		JasperDesign jd = getConfig().getJasperDesign();
		
		createDeafultStyles(jd);
		
		if (tbl != null && lst != null) {
			int colWidth = 40;
			if (tableWidth < 0)
				tableWidth = table.getDefaultWidth();
			if (lst.size() > 0)
				colWidth = tableWidth / lst.size();
			for (Object f : lst) {
				StandardColumn col = CreateColumnCommand.addColumn(jd, tbl,
						step4.isTableHeader(), step4.isTableFooter(),
						step4.isColumnHeader(), step4.isColumnFooter(),
						step4.isGroupHeader(), step4.isGroupFooter());
				col.setWidth(colWidth);
				// Set the cel color
				DesignCell colHeadCell = (DesignCell) col.getColumnHeader();
				if (colHeadCell != null)
					colHeadCell.setStyle(styleList.get(2));
				DesignCell detCell = (DesignCell) col.getDetailCell();
				if (detCell != null)
					detCell.setStyle(styleList.get(3));
				DesignCell tblHeadCell = (DesignCell) col.getTableHeader();
				if (tblHeadCell != null)
					tblHeadCell.setStyle(styleList.get(1));
				DesignCell tblFooterCell = (DesignCell) col.getTableFooter();
				if (tblFooterCell != null)
					tblFooterCell.setStyle(styleList.get(1));
				DesignCell colFooterCell = (DesignCell) col.getColumnFooter();
				if (colFooterCell != null)
					colFooterCell.setStyle(styleList.get(2));
				// Color setted
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
		}
		String dsname = (String) tbl.getDatasetRun().getDatasetName();
		if (dsname == null || dsname.trim().isEmpty()) {
			// create an empty dataset
			JRDesignDataset jrDataset = new JRDesignDataset(false);
			jrDataset.setName(ModelUtils.getDefaultName(jd.getDatasetMap(),
					"Empty Dataset"));
			addCommand(new CreateDatasetCommand(getConfig(), jrDataset));
			((JRDesignDatasetRun) tbl.getDatasetRun()).setDatasetName(jrDataset
					.getName());
		}

		return table;
	}
	
	

	public List<JRDesignStyle> getStylesList() {
		return styleList;
	}

	/**
	 * Create a color array for border and gradient
	 * 
	 * @param baseColor
	 *            value from 0 to 1 that represent the base color H in HSB
	 *            system
	 * @return an array of colors, different version of the base color
	 */
	private Color[] createColor() {
		Color[] result = { Color.getHSBColor(baseColor, 0.25f, 1.0f),
				Color.getHSBColor(baseColor, 0.06f, 1.0f) };
		return result;
	}

	private void createDeafultStyles(JasperDesign jd) {
		
		
		
		// Check the first available basename...
		String basename = "Table";
		styleList = new ArrayList<JRDesignStyle>();
		
		for (int i = 0;; i++) {
			String name = basename;
			if (i > 0) {
				name = basename + " " + i;
			}

			if (!(jd.getStylesMap().containsKey(name))) {
				basename = name;
				break;
			}
		}
		
		JRDesignStyle newStyle = null;
		Color[] colors = createColor();
		
		newStyle = new JRDesignStyle();
		newStyle.setName(basename);
		newStyle.setMode(ModeEnum.OPAQUE);
		addCommand( new CreateStyleCommand(jd, newStyle));
		styleList.add(newStyle);

		newStyle = new JRDesignStyle();
		newStyle.setName(basename + "_TH");
		newStyle.setMode(ModeEnum.OPAQUE);
		newStyle.setBackcolor(colors[1]);
		addCommand( new CreateStyleCommand(jd, newStyle));
		styleList.add(newStyle);
		
		newStyle = new JRDesignStyle();
		newStyle.setName(basename + "_CH");
		newStyle.setMode(ModeEnum.OPAQUE);
		newStyle.setBackcolor(colors[0]);
		addCommand( new CreateStyleCommand(jd, newStyle));
		styleList.add(newStyle);
		
		newStyle = new JRDesignStyle();
		newStyle.setName(basename + "_TD");
		newStyle.setMode(ModeEnum.OPAQUE);
		newStyle.setBackcolor(Color.white);
		addCommand( new CreateStyleCommand(jd, newStyle));
		styleList.add(newStyle);

	}

	
	
}
