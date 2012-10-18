/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.section.report;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav & Orlandin Marco
 */
public class ReportSection extends AbstractSection {

		/**
		 * The report Model
		 */
		private MReport report;
		
		/**
		 * The container of the page format components (need to easily refresh when the format is changed)
		 */
		private Composite pageFormatPanel;
		
		/**
		 * The page format preview widget
		 */
		private PageFormatWidget previewWidget;
		
		/**
		 * The informative label
		 */
		private StyledText valuesLabel;
	
		/**
		 * Show the page format info, textual and visual, with the updated value
		 */
		private void setPreviewWidgetData(){
			//Read the data from the report
			Integer pageWidth = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_PAGE_WIDTH);
			Integer pageHeight = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_PAGE_HEIGHT);
			Integer colNumber = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_COLUMN_COUNT);
			Integer colWidth = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_COLUMN_WIDTH); 
			Integer colSpace = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_COLUMN_SPACING);
			Integer leftMargin = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_LEFT_MARGIN);
			Integer rightMargin = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_RIGHT_MARGIN);
			Integer topMargin = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_TOP_MARGIN);
			Integer bottomMargin = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_BOTTOM_MARGIN);
			Integer orientationValue = (Integer)report.getPropertyValue(JasperDesign.PROPERTY_ORIENTATION);
			//Updating the page format preview widget
			previewWidget.setPwidth(pageWidth);
			previewWidget.setPheight(pageHeight);
			previewWidget.setCols(colNumber);
			previewWidget.setCwidth(colWidth);
			previewWidget.setSpace(colSpace);
			previewWidget.setLmargin(leftMargin);
			previewWidget.setRmargin(rightMargin);
			previewWidget.setTmargin(topMargin);
			previewWidget.setBmargin(bottomMargin);
			//Building the tooltip message
			String lineSeparator = System.getProperty("line.separator");
			String toolTipWidth = Messages.PageFormatDialog_23.concat(": ").concat(pageWidth.toString()).concat(lineSeparator);
			String toolTipHeight = Messages.PageFormatDialog_25.concat(": ").concat(pageHeight.toString()).concat(lineSeparator);
			String toolTipColNumber = Messages.PageFormatDialog_3.concat(": ").concat(colNumber.toString()).concat(lineSeparator);
			String toolTipColWidth = Messages.PageFormatDialog_5.concat(": ").concat(colWidth.toString()).concat(lineSeparator);
			String toolTipColSpace = Messages.PageFormatDialog_8.concat(": ").concat(colSpace.toString()).concat(lineSeparator);
			String toolTipLeftMargin = Messages.PageMarginSection_left_margin_tool_tip.concat(": ").concat(leftMargin.toString()).concat(lineSeparator);
			String toolTipRightMargin = Messages.PageMarginSection_right_margin_tool_tip.concat(": ").concat(rightMargin.toString()).concat(lineSeparator);
			String toolTipTopMargin = Messages.PageMarginSection_top_margin_tool_tip.concat(": ").concat(topMargin.toString()).concat(lineSeparator);
			String toolTipBottomMargin = Messages.PageMarginSection_bottom_margin_tool_tip.concat(": ").concat(bottomMargin.toString());
		   previewWidget.getCanvas().setToolTipText((toolTipWidth.concat(toolTipHeight).concat(toolTipColNumber).concat(toolTipColWidth).concat(toolTipColSpace)
					.concat(toolTipLeftMargin).concat(toolTipRightMargin).concat(toolTipTopMargin).concat(toolTipBottomMargin)));
			GridData gd = new GridData (GridData.FILL_BOTH);
			String orientation;
			if (orientationValue == 0) {
				orientation = Messages.PageFormatDialog_19;
				gd.heightHint = 150;
				gd.widthHint = 100;
			} else {
				orientation = Messages.PageFormatDialog_20;
				gd.heightHint = 100;
				gd.widthHint = 150;
			}
			valuesLabel.setText(pageWidth.toString().concat("x").concat(pageHeight.toString()).concat(lineSeparator).concat(orientation));
			previewWidget.setLayoutData(gd);
			pageFormatPanel.layout();
			previewWidget.setTBounds();
			previewWidget.getCanvas().redraw();
	}
	
	/**
	 * Open the dialog to edit the page format, the if closed with the Ok button the preview will be refreshed
	 */
	private void openEditDialog(){
		PageFormatDialog dlg = new PageFormatDialog(Display.getCurrent().getActiveShell(), report);
		if (dlg.open() == Window.OK) {
			getEditDomain().getCommandStack().execute(dlg.getCommand());
			setPreviewWidgetData();
		}	
	}
	
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(1, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		Composite firstSection = getWidgetFactory().createComposite(parent);
		firstSection.setLayout(new GridLayout(3, false));
		firstSection.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_NAME).getControl().setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_LANGUAGE).getControl().setLayoutData(gd);

		createWidget4Property(firstSection, JasperDesign.PROPERTY_IMPORTS);

		createWidget4Property(firstSection, JasperDesign.PROPERTY_FORMAT_FACTORY_CLASS);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_WHEN_NO_DATA_TYPE).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_TITLE_NEW_PAGE, false).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_SUMMARY_NEW_PAGE, false).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_SUMMARY_WITH_PAGE_HEADER_AND_FOOTER, false).getControl()
				.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_FLOAT_COLUMN_FOOTER, false).getControl()
				.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		createWidget4Property(firstSection, JasperDesign.PROPERTY_IGNORE_PAGINATION, false).getControl().setLayoutData(gd);

		//Adding the dataset section
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		Composite group = getWidgetFactory().createSection(parent, Messages.ReportSection_Dataset_Label, true, 3);
		group.setLayoutData(gd);

		MDataset mDataset = (MDataset) getElement().getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
		if (mDataset != null) {
			gd = new GridData();
			gd.horizontalSpan = 2;
			createWidget4Property(mDataset, group, JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE, true).getControl()
					.setLayoutData(gd);
			createWidget4Property(mDataset, group, JRDesignDataset.PROPERTY_SCRIPTLET_CLASS, true);

			gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = SWT.CENTER;
			createWidget4Property(mDataset, group, JRDesignDataset.PROPERTY_QUERY, false).getControl().setLayoutData(gd);
			
		}
		//Adding the page format section
		pageFormatPanel = getWidgetFactory().createSection(parent, "Page Format", true, 1);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = SWT.CENTER;
		pageFormatPanel.setLayoutData(gd);
		report = (MReport) getElement();
		Composite previewInfo = new Composite(pageFormatPanel,SWT.NONE);
		previewInfo.setLayout(new GridLayout(2,false));
		previewInfo.setLayoutData(gd);
		//Adding the previw widget
		previewWidget = new PageFormatWidget(previewInfo, SWT.NONE);
		previewWidget.getCanvas().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				openEditDialog();
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//Adding the textual info
		Composite textualInfo = new Composite(previewInfo, SWT.NONE);
		gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		textualInfo.setLayout(new GridLayout(2,false));
		textualInfo.setLayoutData(gd);
		Label infoLabel = new Label(textualInfo, SWT.NONE);
		infoLabel.setText("Page:");
		valuesLabel = new StyledText(textualInfo, SWT.READ_ONLY);
		valuesLabel.setEnabled(false);
		//Add the button
		Button editLayoutButton = getWidgetFactory().createButton(pageFormatPanel, "Edit Page Format", SWT.PUSH);
		gd = new GridData();
		gd.horizontalAlignment = SWT.CENTER;
		editLayoutButton.setLayoutData(gd);
		editLayoutButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openEditDialog();
			}
		});
		//Set the datafiels
		setPreviewWidgetData();
	}

}
