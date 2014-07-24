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

import com.jaspersoft.jasperserver.api.JasperServerAPI;

import java.util.Locale;
import java.util.TimeZone;


/**
 * Context passed from calling code to the JasperServer APIs.
 * 
 * <p>
 * The context contains general attributes that are used by several
 * JasperServer API methods.
 * </p>
 * 
 * @author swood
 * @author Lucian Chirita
 * @author Ionut Nedelcu
 * @version $Id: ExecutionContext.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public interface ExecutionContext extends AttributedObject {

	/**
	 * Specifies the locale used in the calling code.
	 * 
	 * <p>
	 * The locale is used when localized messages are constructed in API methods,
	 * and when numerical and date values are formatted to text.
	 * </p>
	 * 
	 * @return the locale used in the calling code
	 */
	Locale getLocale();
	
	/**
	 * Specifies the timezone used in the calling code.
	 * 
	 * <p>
	 * The timezone is used when displaying date/time values as texts.
	 * </p>
	 * 
	 * @return the timezone used in the calling code
	 */
	TimeZone getTimeZone();

}
