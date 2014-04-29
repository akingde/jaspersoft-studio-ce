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
 * This exception should be thrown to indicate that some
 * feature is not implemented yet.
 * On UI side user should see friendly message based on this exception.
 *
 * @author Sergey Prilukin
 * @version $Id: JSNotImplementedException.java 20440 2011-05-03 22:54:35Z asokolnikov $
 */
@JasperServerAPI
public class JSNotImplementedException extends RuntimeException {

    public JSNotImplementedException() {
        super("Feature is not implemented.");
    }

    public JSNotImplementedException(String message) {
        super(message);
    }
}
