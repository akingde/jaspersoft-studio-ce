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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ReportSection extends AbstractSection {

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

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		Composite group = getWidgetFactory().createSection(parent, Messages.ReportSection_Dataset_Label, true, 3);
		group.setLayoutData(gd);

		MDataset mDataset = (MDataset) getElement().getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
		if (mDataset != null) {
			gd = new GridData();
			gd.horizontalSpan = 2;
			// gd = new GridData();
			// gd.horizontalSpan = 3;
			// Composite rowComposite = new Composite(group, SWT.NONE);
			// rowComposite.setLayout(new RowLayout());
			// rowComposite.setLayoutData(gd);
			createWidget4Property(mDataset, group, JRDesignDataset.PROPERTY_WHEN_RESOURCE_MISSING_TYPE, true).getControl()
					.setLayoutData(gd);
			createWidget4Property(mDataset, group, JRDesignDataset.PROPERTY_SCRIPTLET_CLASS, true);

			gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = SWT.CENTER;
			createWidget4Property(mDataset, group, JRDesignDataset.PROPERTY_QUERY, false).getControl().setLayoutData(gd);
		}

	}

}
