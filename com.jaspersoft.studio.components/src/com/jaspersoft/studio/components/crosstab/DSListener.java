/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.model.ANode;

public class DSListener implements PropertyChangeListener {
	private ANode parent;
	private JasperDesign jd;
	private JRDesignCrosstab st;

	public DSListener(ANode parent, JasperDesign jd, JRDesignCrosstab st) {
		this.parent = parent;
		this.jd = jd;
		this.st = st;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		CrosstabComponentFactory.setDataset(parent, jd, st, this);
	}
};
