/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
		return "This is a not correct resource";
	}

}
