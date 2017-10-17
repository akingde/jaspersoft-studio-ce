/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.mongodb.querydesigner;

import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.designer.QueryDesigner;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

/**
 * Simple query designer for MongoDB query language that provides syntax
 * highlighting.
 * 
 */
public class MongoDBQueryDesigner extends QueryDesigner {
	/* Text area where enter the query */
	protected StyledText queryTextArea;
	private MongoDBLineStyler lineStyler = new MongoDBLineStyler();

	public Control createControl(Composite parent) {
		control = (StyledText) super.createControl(parent);
		control.addLineStyleListener(lineStyler);
		return control;
	}

	protected void queryTextAreaModified() {
		// keep the query info updated
		((JRDesignQuery) jDataset.getQuery()).setText(queryTextArea.getText());
	}

	@Override
	public String getContextHelpId() {
		return ContextHelpIDs.PREFIX.concat("query_mongo");
	}
}
