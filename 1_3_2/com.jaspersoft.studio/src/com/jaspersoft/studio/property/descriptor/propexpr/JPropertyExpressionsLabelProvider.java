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
package com.jaspersoft.studio.property.descriptor.propexpr;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.messages.Messages;

/*
 * @author Chicu Veaceslav
 */
public class JPropertyExpressionsLabelProvider extends LabelProvider {

	public JPropertyExpressionsLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return ""; //$NON-NLS-1$
		if (element instanceof PropertyExpressionsDTO) {
			PropertyExpressionsDTO dto = (PropertyExpressionsDTO) element;
			int expsize = 0;
			if (dto.getPropExpressions() != null)
				expsize += dto.getPropExpressions().length;
			int pmapsize = 0;
			if (dto.getPropMap() != null && dto.getPropMap().getPropertyNames() != null)
				pmapsize += dto.getPropMap().getPropertyNames().length;
			return "[" + Messages.common_properties + ": " + (expsize + pmapsize) + "]";
		} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return element.toString();
	}

}
