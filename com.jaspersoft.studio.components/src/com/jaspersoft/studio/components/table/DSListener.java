/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JasperDesign;


/**
 * Listener to update the dataset inside the table designer when the dataset
 * run is changed
 * 
 * @author Orlandin Marco
 *
 */
public class DSListener implements PropertyChangeListener {
	
	public static final String REFRESH_DATASET = "refreshSubeditorDataset";
	
	private ANode parent;
	private JasperDesign jd;
	private StandardTable st;

	public DSListener(ANode parent, JasperDesign jd, StandardTable st) {
		this.parent = parent;
		this.jd = jd;
		this.st = st;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (StandardTable.PROPERTY_DATASET_RUN.equals(evt.getPropertyName()) || REFRESH_DATASET.equals(evt.getPropertyName()) ){
				TableComponentFactory.setDataset(parent, jd, st);
		}
	}
};
