/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.subreport.parameter;

import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;

/**
 * @author Chicu Veaceslav
 * 
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
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ": " + ((JRPropertiesMap) element).getPropertyNames().length; //$NON-NLS-1$
		if (element instanceof Collection)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ": " + ((Collection) element).size(); //$NON-NLS-1$
		if (element instanceof Map)
			return Messages.SubreportPropertiesLabelProvider_numbers_of_parameters + ": " + ((Map) element).size();  //$NON-NLS-1$
		return element.toString();
	}

}
