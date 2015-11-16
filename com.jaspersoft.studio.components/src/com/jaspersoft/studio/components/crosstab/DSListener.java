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