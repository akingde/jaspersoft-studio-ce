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
package com.jaspersoft.studio.server.protocol.restv2;

import javax.ws.rs.core.Response;

public class ClassSelector {
	protected Class<?> clazz;

	public ClassSelector(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> checkClazz(Response res) {
		if (clazz == null) {
			String type = res.getHeaderString("Content-Type");
			if (type.equals("application/xml"))
				return String.class;
			if (type.equals("text/plain"))
				return String.class;
			int sind = type.indexOf(".");
			int eind = type.indexOf("+");
			if (sind >= 0 && eind >= 0) {
				type = type.substring(sind + 1, eind);
				clazz = WsTypes.INST().getType(type);
			}
		}
		return clazz;
	}
}
