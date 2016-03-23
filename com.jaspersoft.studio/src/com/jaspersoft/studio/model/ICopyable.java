/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model;

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
}
