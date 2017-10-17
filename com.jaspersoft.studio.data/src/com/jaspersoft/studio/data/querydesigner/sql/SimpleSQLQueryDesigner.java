/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.sql;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.designer.QueryDesigner;
import com.jaspersoft.studio.data.querydesigner.sql.text.SQLLineStyler;

/**
 * Simple SQL query designer that simply provides syntax coloring support.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class SimpleSQLQueryDesigner extends QueryDesigner {

	// Line style for SQL code
	private SQLLineStyler sqlLineStyler;

	public SimpleSQLQueryDesigner() {
		super();
		sqlLineStyler = getSQLBasedLineStyler();
	}

	@Override
	public Control createControl(Composite parent) {
		super.createControl(parent);
		createLineStyler();
		return control;
	}

	protected void createLineStyler() {
		control.addLineStyleListener(sqlLineStyler);
		control.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				sqlLineStyler.parseBlockComments(control.getText());
			}
		});
	}

	/**
	 * @return the SQL line styler
	 */
	protected SQLLineStyler getSQLBasedLineStyler() {
		return new SQLLineStyler();
	}

}
