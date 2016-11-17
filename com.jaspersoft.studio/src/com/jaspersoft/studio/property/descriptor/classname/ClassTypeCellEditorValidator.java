/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.classname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ICellEditorValidator;
/*
 * The Class IntegerCellEditorValidator.
 * 
 * @author Chicu Veaceslav
 */
public class ClassTypeCellEditorValidator implements ICellEditorValidator {
	/** Regular expression for identifying a fully qualified class name */
	public static String regexp = "([a-zA-Z_$][a-zA-Z\\d_$]*\\.)*[a-zA-Z_$][a-zA-Z\\d_$]*"; //$NON-NLS-1$
	
	/** The instance. */
	private static ClassTypeCellEditorValidator instance;

	/**
	 * Instance.
	 * 
	 * @return the integer cell editor validator
	 */
	public static ClassTypeCellEditorValidator instance() {
		if (instance == null)
			instance = new ClassTypeCellEditorValidator();
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	public String isValid(Object value) {
		if (value == null) {
			return null;
		} else if (value instanceof String) {
			if (StringUtils.isEmpty((String) value)) {
				return null;
			} else {
				Pattern pattern = Pattern.compile(ClassTypeCellEditorValidator.regexp);
				Matcher matcher = pattern.matcher((String) value);
				if (matcher.matches()) {
					return null;
				}
			}
		}
		return "This is a not correct java Class";
	}

}
