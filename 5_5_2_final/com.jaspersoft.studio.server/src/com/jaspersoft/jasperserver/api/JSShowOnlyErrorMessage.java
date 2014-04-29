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

package com.jaspersoft.jasperserver.api;

/**
 * This is general approach for display error message without full stackTrace
 * On UI(errorPage.jsp) side user should see friendly message based on this exception.
 *
 * @author Roman Kuziv
 * @version $Id: JSShowOnlyErrorMessage.java 20327 2011-04-08 10:05:27Z roman.kuziv $
 */

@JasperServerAPI
public class JSShowOnlyErrorMessage extends JSException {
    public JSShowOnlyErrorMessage(String message) {
        super(message);
    }

    public JSShowOnlyErrorMessage(String message, Object[] args) {
		super(message);
		this.args = args;
	}
}
