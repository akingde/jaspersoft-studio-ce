/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.from;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;

public class MFromTable extends AMQueryAliased<MSqlTable> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFromTable(ANode parent, MSqlTable value) {
		super(parent, value, null);
	}

	public MFromTable(ANode parent, MSqlTable value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public String toSQLString() {
		String sql = super.toSQLString();
		if (getValue() instanceof MQueryTable)
			return "(";
		return sql;
	}

	@Override
	public String getToolTip() {
		MSqlTable mc = getValue();
		String tooltip = mc.toSQLString();
		tooltip += addAlias();
		if (getValue().getRemarks() != null)
			tooltip += "\n" + mc.getRemarks();
		return tooltip;
	}

	private Rectangle bounds;

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	private List<TableJoin> tableJoins;

	public void addTableJoin(TableJoin tjoin) {
		if (tableJoins == null)
			tableJoins = new ArrayList<TableJoin>();
		tableJoins.add(tjoin);
	}

	public void removeTableJoin(TableJoin tjoin) {
		if (tableJoins != null)
			tableJoins.remove(tjoin);
	}

	public List<TableJoin> getTableJoins() {
		return tableJoins;
	}
}
