/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import org.eclipse.jface.viewers.ISelection;

/**
 * Interface to implement to define that an element is 
 * compatible with the copy and paste
 */
public interface ICopyable {
	
	/**
	 * Result of the check to see if the element implementing the
	 * interface can be copied on the parent. The result are 
	 * COPYABLE if the element is copyable in the target. CHECK_PARENT
	 * if the element is not copyable in the current node but maybe it could
	 * be on a parent of the target element, so the ancestor could be searched 
	 * for a valid candidate. NOT_COPYABLE if the element is not
	 * copyable on the current target and its ancestor should not be check to 
	 * search a candidate.
	 */
	public enum RESULT{COPYABLE, CHECK_PARENT, NOT_COPYABLE}
	
	/**
	 * Define if the copied element can be copied to a specific parent
	 * 
	 * @param target the target of the copy
	 * @return the result that define if the element is copyable or not on 
	 * the target
	 */
	public RESULT isCopyable2(Object target);
	
	/**
	 * Check if the current element is cuttable
	 * 
	 * @param currentSelection the current editor selection
	 * @return true if the element can be cut, false otherwise
	 */
	public boolean isCuttable(ISelection currentSelection);
}
