/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public class MFieldsContainer extends MFields implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private Map<String, String> pLabels;
	private String key;

	public MFieldsContainer() {
		super();
	}

	public MFieldsContainer(ANode parent, JRDesignDataset jrDataset, Map<String, String> pLabels, String key) {
		super(parent, jrDataset);
		this.pLabels = pLabels;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String getToolTip() {
		return super.getToolTip() + "\n" + key;
	}

	@Override
	public String getDisplayText() {
		try {
			String k = key;
			int indx = k.lastIndexOf(".");
			if (indx >= 0)
				k = k.substring(indx + 1);

			return URLDecoder.decode(Misc.nvl(pLabels.get(key), Misc.nvl(k, super.getDisplayText())), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return Misc.nvl(pLabels.get(key), super.getDisplayText());
		}
	}

	public MFields getParentMFields() {
		ANode p = getParent();
		while (p != null) {
			if (p instanceof MFields && !(p instanceof MFieldsContainer))
				return (MFields) p;
		}
		return null;
	}

	// @Override
	// public boolean equals(Object obj) {
	// return obj instanceof MFieldsContainer && key.equals(((MFieldsContainer)
	// obj).getKey());
	// }
	//
	// @Override
	// public int hashCode() {
	// return key.hashCode();
	// }
}