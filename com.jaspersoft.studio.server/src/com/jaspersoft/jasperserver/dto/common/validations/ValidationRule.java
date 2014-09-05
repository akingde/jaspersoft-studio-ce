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

package com.jaspersoft.jasperserver.dto.common.validations;

import java.io.Serializable;

/**
 * Builder pattern requires to return concrete type of object by all it's
 * setters. Therefore generic parameter BuilderType is introduced.
 * 
 * @author Yaroslav.Kovalchyk
 * @version $Id: ValidationRule.java 33643 2013-07-08 11:29:50Z ykovalchyk $
 */
public abstract class ValidationRule<BuilderType extends ValidationRule<BuilderType>> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	// definition of subclasses assures cast safety.
	@SuppressWarnings("unchecked")
	public BuilderType setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return (BuilderType) this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ValidationRule))
			return false;

		ValidationRule that = (ValidationRule) o;

		if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return errorMessage != null ? errorMessage.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ValidationRule{" + "errorMessage='" + errorMessage + '\'' + '}';
	}
}
