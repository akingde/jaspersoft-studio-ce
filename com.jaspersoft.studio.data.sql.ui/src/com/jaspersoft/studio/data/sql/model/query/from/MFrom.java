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
package com.jaspersoft.studio.data.sql.model.query.from;

import org.eclipse.draw2d.geometry.Point;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.model.ANode;

public class MFrom extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFrom(ANode parent) {
		super(parent, "FROM", null);
		noSqlIfEmpty = true;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(MFromTable.PROP_X) && value instanceof Point) {
			setNoEvents(true);
			super.setPropertyValue(MFromTable.PROP_X, ((Point) value).x);
			setNoEvents(false);
			super.setPropertyValue(MFromTable.PROP_Y, ((Point) value).y);
			return;
		}
		super.setPropertyValue(id, value);
	}
}
