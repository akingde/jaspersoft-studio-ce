/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
