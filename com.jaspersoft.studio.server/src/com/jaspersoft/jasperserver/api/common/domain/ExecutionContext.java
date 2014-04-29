/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
