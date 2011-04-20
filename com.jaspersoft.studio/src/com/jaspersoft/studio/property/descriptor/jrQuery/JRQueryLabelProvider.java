/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
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
