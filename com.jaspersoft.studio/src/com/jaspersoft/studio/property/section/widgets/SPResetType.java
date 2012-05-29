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
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.Misc;

public class SPResetType extends ASPropertyWidget {
	private Combo evalTime;
	private IPropertyDescriptor gDescriptor;

	public SPResetType(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor,
			IPropertyDescriptor gDescriptor) {
		super(parent, section, pDescriptor);
		this.gDescriptor = gDescriptor;
	}

	@Override
	public Control getControl() {
		return evalTime;
	}

	public void createComponent(Composite parent) {
		evalTime = section.getWidgetFactory().createCombo(parent, SWT.FLAT | SWT.READ_ONLY);
		evalTime.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String group = null;
				Integer et = new Integer(1);

				String str = evalTime.getItem(evalTime.getSelectionIndex());
				if (str.startsWith(GROUPPREFIX)) {
					group = str.substring(GROUPPREFIX.length());
					et = EnumHelper.getValue(ResetTypeEnum.GROUP, 1, false);
				} else {
					et = EnumHelper.getValue(ResetTypeEnum.getByName(str), 1, false);
				}

				section.changeProperty(pDescriptor.getId(), et);
				section.changeProperty(gDescriptor.getId(), Misc.nvl(group));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		evalTime.setToolTipText(pDescriptor.getDescription());
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		JasperDesign jasperDesign = pnode.getJasperDesign();
		JRDataset dataset = null;
		MDatasetRun mdataset = (MDatasetRun) pnode.getPropertyValue(JRDesignElementDataset.PROPERTY_DATASET_RUN);
		if (mdataset != null) {
			JRDesignDatasetRun datasetRun = mdataset.getValue();
			if (datasetRun != null) {
				String dsname = datasetRun.getDatasetName();
				dataset = jasperDesign.getDatasetMap().get(dsname);
			}
		}
		if (dataset == null)
			dataset = jasperDesign.getMainDataset();

		setData((Integer) pnode.getPropertyValue(pDescriptor.getId()),
				(String) pnode.getPropertyValue(gDescriptor.getId()), SPIncrementType.getItems(dataset));

	}

	public void setData(Integer et, String group, String[] items) {
		evalTime.setItems(items);
		int selection = 0;
		ResetTypeEnum sel = (ResetTypeEnum) EnumHelper.getSetValue(ResetTypeEnum.values(), et, 1, false);

		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(sel.getName())) {
				selection = i;
				break;
			}
			if (items[i].startsWith(GROUPPREFIX) && sel.equals(ResetTypeEnum.GROUP)) {
				if (items[i].substring(GROUPPREFIX.length()).equals(group)) {
					selection = i;
					break;
				}
			}
		}
		evalTime.select(selection);
	}

	public static String[] getItems(JRDataset dataset) {
		List<String> lsIncs = new ArrayList<String>();
		for (ResetTypeEnum en : ResetTypeEnum.values()) {
			if (en.equals(ResetTypeEnum.GROUP)) {
				if (dataset != null)
					for (JRGroup gr : dataset.getGroups())
						lsIncs.add(GROUPPREFIX + gr.getName());
			} else {
				lsIncs.add(en.getName());
			}
		}
		return lsIncs.toArray(new String[lsIncs.size()]);
	}

	private static final String GROUPPREFIX = "[Group] ";
}
