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
package com.jaspersoft.studio.property.descriptor.subreport.parameter;

import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;

/*
 * @author Chicu Veaceslav
 */
public class SubreportPropertiesLabelProvider extends LabelProvider {

	public SubreportPropertiesLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof JRPropertiesMap)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters
					+ ((JRPropertiesMap) element).getPropertyNames().length;
		if (element instanceof Collection)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ((Collection<?>) element).size();
		if (element instanceof Map)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ((Map<?, ?>) element).size();
		if (element.getClass().isArray())
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ((Object[]) element).length;
		return element.toString();
	}

}
