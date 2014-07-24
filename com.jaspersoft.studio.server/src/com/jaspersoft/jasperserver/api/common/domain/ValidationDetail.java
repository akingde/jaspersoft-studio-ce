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

package com.jaspersoft.jasperserver.api.common.domain;

import com.jaspersoft.jasperserver.api.common.domain.Id;

/**
 * 
 * @author tkavanagh
 * @version $Id: ValidationDetail.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 *
 */
public interface ValidationDetail {

	public Id getId();
	
	/**
	 * 
	 * @return Class - Class of Resource being validated
	 */
	public Class getValidationClass();
	
	public String getName();
	
	public String getLabel();
	
	/**
	 * 
	 * @return String - such as VALID, VALID_STATIC, VALID_DYNAMIC, ERROR
	 */
	public String getResult();
	
	/**
	 * 
	 * @return String - holds value if result in non-valid
	 */
	public String getMessage();
	
	/**
	 * 
	 * @return Exception - potentially holds value if result if non-valid
	 */
	public Exception getException();
	
}
