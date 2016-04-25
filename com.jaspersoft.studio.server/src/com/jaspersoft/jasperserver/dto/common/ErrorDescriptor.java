/*
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
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
package com.jaspersoft.jasperserver.dto.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: ErrorDescriptor.java 61059 2016-02-19 16:10:30Z vspachyn $
 */
@XmlRootElement
public class ErrorDescriptor {
    public static final String ERROR_CODE_UNEXPECTED_ERROR = "unexpected.error";
    private String message;
    private String errorCode;
    private String[] parameters;
	private String errorUid;

    private Throwable exception;

	public ErrorDescriptor() {}

	public ErrorDescriptor(ErrorDescriptor ed) {
        message = ed.getMessage();
        errorCode = ed.getErrorCode();
        parameters = ed.getParameters();
        errorUid = ed.getErrorUid();
        exception = ed.getException();
    }

    public String getMessage() {
        return message;
    }

    public ErrorDescriptor setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorDescriptor setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @XmlElementWrapper(name = "parameters")
    @XmlElement(name = "parameter")
    public String[] getParameters() {
        return parameters;
    }

    public ErrorDescriptor setParameters(String... parameters) {
        this.parameters = parameters;
        return this;
    }

    public ErrorDescriptor setParameters(Object... args) {
        if (args != null && args.length > 0) {
            List<String> values = new LinkedList<String>();
            for (Object arg : args) {
                if (arg != null) {
                    values.add(arg.toString());
                }
            }
            if (!values.isEmpty()) {
                parameters = values.toArray(new String[values.size()]);
            }
        }

        return this;
    }

	public String getErrorUid() {
		return errorUid;
	}

	public ErrorDescriptor setErrorUid(String errorUid) {
		this.errorUid = errorUid;
        return this;
	}

	public ErrorDescriptor setException(Throwable exception) {
		this.exception = exception;
        return this;
	}

    @XmlTransient
	public Throwable getException() {
		return exception;
	}

    @Override
    public String toString() {
        return "ErrorDescriptor{" +
                "message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", errorUid='" + errorUid + '\'' +
                ", exception=" + exception +
                '}';
    }

}
