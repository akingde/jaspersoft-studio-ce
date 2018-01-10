/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.jrQuery;

import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.property.descriptor.NullEnum;
/*
 * @author Chicu Veaceslav
 * 
 */
public class JRQueryLabelProvider extends LabelProvider {
	private NullEnum canBeNull;

	public JRQueryLabelProvider(NullEnum canBeNull) {
		super();
		this.canBeNull = canBeNull;
	}

	@Override
	public String getText(Object element) {
		if (element != null && element instanceof MQuery) {
			MQuery mQuery = (MQuery) element;
			String lang = (String) mQuery.getPropertyValue(JRDesignQuery.PROPERTY_LANGUAGE);
			if (lang == null)
				lang = "";
			else
				lang = "<" + lang + ">";
			String txt = (String) mQuery.getPropertyValue(JRDesignQuery.PROPERTY_TEXT);
			if (txt == null)
				txt = "";
			return lang + txt; //$NON-NLS-1$
		}
		if (element == null || !(element instanceof JRDesignQuery))
			return canBeNull.getName();
		JRDesignQuery query = (JRDesignQuery) element;
		return query.getText();
	}

}
