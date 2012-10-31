/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset.descriptor;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Section used to display the control regarding the main dataset in the report tab
 * @author Orlandin Marco
 *
 */
public class MainDatasetSection extends AbstractSection {
	
	/**
	 * The main dataset of the report
	 */
	MDataset mDataset;
	
	/**
	 * Override the original change property operating on the report dataset insted of 
	 * the report
	 */
	public boolean changeProperty(Object property, Object newValue) {
		if (!isRefreshing && mDataset != null) {
			CommandStack cs = getEditDomain().getCommandStack();
			CompoundCommand cc = new CompoundCommand("Set " + property);
			Command c = changeProperty(property, newValue, mDataset);
			if (c != null)
					cc.add(c);
			if (!cc.getCommands().isEmpty()) {
				cs.execute(cc);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Override the original change property operating on the report dataset insted of 
	 * the report
	 */
	public void refresh() {
		isRefreshing = true;
		if (mDataset != null) {
			mDataset.getPropertyDescriptors();
			for (Object key : widgets.keySet()) {
				widgets.get(key).setData(mDataset, mDataset.getPropertyValue(key));
			}
		}
		isRefreshing = false;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		Composite group = getWidgetFactory().createSection(parent, Messages.ReportSection_Dataset_Label, true, 3);
		group.setLayoutData(gd);

		mDataset = (MDataset) getElement().getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
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
	}
}
