/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.model;

import java.beans.PropertyChangeEvent;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MCollection;
import com.jaspersoft.studio.table.util.TableColumnNumerator;

public abstract class AMCollection extends MCollection {

	public AMCollection(ANode parent, JRDesignComponentElement jrDataset, String property) {
		super(parent, jrDataset, property);
	}

	@Override
	public void setValue(Object value) {
		JRDesignComponentElement oldObject = (JRDesignComponentElement) getValue();
		JRDesignComponentElement newObject = (JRDesignComponentElement) value;

		if (oldObject != null) {
			StandardTable table = ((StandardTable) oldObject.getComponent());
			if (table != null)
				table.getEventSupport().removePropertyChangeListener(this);
		}
		if (newObject != null) {
			StandardTable table = ((StandardTable) newObject.getComponent());
			if (table != null)
				table.getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(StandardTable.PROPERTY_COLUMNS)) {
			if (evt.getSource() instanceof StandardTable) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
					}
					StandardBaseColumn bc = (StandardBaseColumn) evt.getNewValue();

					createColumn(this, bc, 122, newIndex);

				} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
					// delete
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue()) {
							removeChild((ANode) n);
							break;
						}
					}
				} else {
					// changed
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue())
							n.setValue(evt.getNewValue());
					}
				}
				MTable mTable = (MTable) getParent();
				mTable.getTableManager().refresh();
				TableColumnNumerator.renumerateColumnNames(mTable);
			}
		}
		super.propertyChange(evt);
	}

	public abstract String getCellEvent();

	public abstract void createColumn(ANode mth, BaseColumn bc, int i, int index);

	private boolean flagRefreshCells = false;

}
