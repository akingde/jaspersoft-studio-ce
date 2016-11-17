/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
