/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.apache.commons.beanutils.PropertyUtils;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class WProperty extends AWidget {

	public WProperty(Composite parent, TColumn c, Object element) {
		super(parent, c, element);
	}

	protected Object getValue() {
		try {
			if (element != null)
				return PropertyUtils.getProperty(element, c.getPropertyName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setValue(Object value) {
		if (element != null)
			try {
				PropertyUtils.setProperty(element, c.getPropertyName(), value);
			} catch (Exception e) {
				UIUtils.showError(e);
			}
	}

}
