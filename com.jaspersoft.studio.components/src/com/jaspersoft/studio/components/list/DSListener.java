/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.list;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.model.ANode;

public class DSListener implements PropertyChangeListener {
	private ANode parent;
	private JasperDesign jd;
	private StandardListComponent st;

	public DSListener(ANode parent, JasperDesign jd,
			StandardListComponent st) {
		this.parent = parent;
		this.jd = jd;
		this.st = st;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		ListComponentFactory.setDataset(parent, jd, st, this);
	}
};
