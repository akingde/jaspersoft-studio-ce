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
package com.jaspersoft.studio.data.sql.action;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.property.SetValueCommand;

public class TableModeShowAction extends AAction {
	private SQLQueryDesigner designer;
	private String key;

	public TableModeShowAction(SQLQueryDesigner designer, String key,
			String title) {
		super(title, designer.getOutline().getTreeViewer());
		this.designer = designer;
		this.key = key;
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		if (selection == null)
			return false;
		if (selection.length == 1 && isInFrom(selection[0])) {
			MFromTable mft = (MFromTable) selection[0];
			Object v = mft.getPropertyValue(MFromTable.SHOW_MODE_PROPERTY);
			if (v == null && key == null)
				return false;
			if (v != null && key != null && v.equals(key))
				return false;
			return true;
		}
		return false;
	}

	public static boolean isInFrom(Object element) {
		if (element instanceof MFromTable
				&& ((MFromTable) element).getValue() instanceof MQueryTable)
			return false;
		return element instanceof MFromTable;
	}

	@Override
	public void run() {
		SetValueCommand c = new SetValueCommand();
		MFromTable tbl = (MFromTable) selection[0];
		c.setTarget(tbl);
		c.setPropertyId(MFromTable.SHOW_MODE_PROPERTY);
		c.setPropertyValue(key);

		designer.getDiagram().getViewer().getEditDomain().getCommandStack()
				.execute(c);
	}

}