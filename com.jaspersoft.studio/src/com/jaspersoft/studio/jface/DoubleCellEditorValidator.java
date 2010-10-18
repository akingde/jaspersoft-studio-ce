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
package com.jaspersoft.studio.jface;

import org.eclipse.jface.viewers.ICellEditorValidator;

/**
 * The Class FloatCellEditorValidator.
 * 
 * @author Chicu Veaceslav
 */
public class DoubleCellEditorValidator implements ICellEditorValidator {

	/** The instance. */
	private static DoubleCellEditorValidator instance;

	/**
	 * Instance.
	 * 
	 * @return the float cell editor validator
	 */
	public static DoubleCellEditorValidator instance() {
		if (instance == null)
			instance = new DoubleCellEditorValidator();
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	public String isValid(Object value) {
		try {
			if (value instanceof Double)
				return null;
			if (value instanceof String)
				new Double((String) value);
			return null;
		} catch (NumberFormatException exc) {
			return "This is not a Double number";
		}
	}

}
