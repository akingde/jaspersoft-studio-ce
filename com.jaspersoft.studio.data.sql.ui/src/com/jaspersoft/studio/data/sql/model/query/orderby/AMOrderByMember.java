/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.orderby;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.AMKeyword;
import com.jaspersoft.studio.data.sql.model.query.AMQueryObject;
import com.jaspersoft.studio.model.ANode;

public class AMOrderByMember<T> extends AMQueryObject<T> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMOrderByMember(ANode parent, T value, String image, int index) {
		super(parent, value, image, index);
	}

	public AMOrderByMember(ANode parent, T value, String image) {
		super(parent, value, image);
	}

	public String addDirection() {
		return isDesc ? AMKeyword.DESCENDING_KEYWORD : AMKeyword.ASCENDING_KEYWORD;
	}

	private boolean isDesc = true;

	public boolean isDesc() {
		return isDesc;
	}

	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}

}
