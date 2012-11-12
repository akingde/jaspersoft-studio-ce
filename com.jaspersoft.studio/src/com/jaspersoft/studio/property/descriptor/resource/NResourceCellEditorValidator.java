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
package com.jaspersoft.studio.property.descriptor.resource;

import org.eclipse.jface.viewers.ICellEditorValidator;
/*
 * The Class IntegerCellEditorValidator.
 * 
 * @author Chicu Veaceslav
 */
public class NResourceCellEditorValidator implements ICellEditorValidator {
	/** The instance. */
	private static NResourceCellEditorValidator instance;

	/**
	 * Instance.
	 * 
	 * @return the integer cell editor validator
	 */
	public static NResourceCellEditorValidator instance() {
		if (instance == null)
			instance = new NResourceCellEditorValidator();
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	public String isValid(Object value) {
		if (value == null)
			return null;
		if (value instanceof String) {
			// if (value != null) {
			// // Pattern pattern = Pattern.compile(ClassTypeCellEditorValidator.regexp);
			// // Matcher matcher = pattern.matcher((String) value);
			// // if (matcher.matches())
			// return null;
			// }
			return null;
		}
		return "This is a not correct java Class"; //$NON-NLS-1$
	}

}
