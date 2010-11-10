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
package com.jaspersoft.studio.property.descriptor.classname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.ICellEditorValidator;

/**
 * The Class IntegerCellEditorValidator.
 * 
 * @author Chicu Veaceslav
 */
public class ImportDeclarationCellEditorValidator implements ICellEditorValidator {
	public static String regexp = "^(([A-Za-z0-9_])+.)+[A-Z]([A-Za-z0-9])+$";
	/** The instance. */
	private static ImportDeclarationCellEditorValidator instance;

	/**
	 * Instance.
	 * 
	 * @return the integer cell editor validator
	 */
	public static ImportDeclarationCellEditorValidator instance() {
		if (instance == null)
			instance = new ImportDeclarationCellEditorValidator();
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	public String isValid(Object value) {
		if (value instanceof String) {
			if (value != null) {
				Pattern pattern = Pattern.compile(regexp);
				Matcher matcher = pattern.matcher((String) value);
				if (matcher.matches())
					return null;
			}
			return "Value should be not null";
		}
		return "This is a not correct java Class";
	}

}
