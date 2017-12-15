/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.column;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnViewer;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

public class FieldNameColumnSupport extends PropertyColumnSupport {
	private JRDesignDataset dataset;

	public FieldNameColumnSupport(ColumnViewer viewer, TColumn c) {
		super(viewer, c);
		this.dataset = (JRDesignDataset) c.getValue();
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element == null)
			return;
		JRDesignField field = (JRDesignField) element;
		List<JRDesignField> list = (List<JRDesignField>) viewer.getInput();
		boolean exists = false;
		for (JRDesignField f : list) {
			exists = f.getName().equals(value);
			if (exists)
				break;
		}
		if (!exists) {
			String oldName = field.getName();
			dataset.getFieldsMap().remove(oldName);
			field.setName((String) value);
			dataset.getFieldsMap().put(field.getName(), field);

			viewer.update(element, null);

			Map<String, JRSortField> sortFields = dataset.getSortFieldsMap();
			JRSortField sf = sortFields.get(oldName + "|" + SortFieldTypeEnum.FIELD.getName());
			// If a field with the same name is not present or if it is present but with a different type then show it
			if (sf != null) {
				dataset.removeSortField(sf);
				((JRDesignSortField) sf).setName(field.getName());
				try {
					dataset.addSortField(sf);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
