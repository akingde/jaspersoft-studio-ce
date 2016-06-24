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
package com.jaspersoft.studio.components.table.model;

import java.beans.PropertyChangeEvent;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

/**
 * Class implemented by the header and the footer sections of the table. This is
 * done to share the propertyChange event, common to this sections
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AMFooterHeaderCollection extends AMCollection {
	
	private static final long serialVersionUID = -1026904833107804569L;

	public AMFooterHeaderCollection(ANode parent, JRDesignComponentElement jrDataset, String property) {
		super(parent, jrDataset, property);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(StandardTable.PROPERTY_COLUMNS)) {
			if (evt.getSource() instanceof StandardTable) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt)
								.getAddedIndex();
					}
					StandardBaseColumn bc = (StandardBaseColumn) evt
							.getNewValue();

					createColumn(this, bc, -1, newIndex);

				} else if (evt.getOldValue() != null
						&& evt.getNewValue() == null) {
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
				if (mTable == null) {
					((JRChangeEventsSupport) evt.getSource()).getEventSupport()
							.removePropertyChangeListener(this);
				} else {
					mTable.getTableManager().refresh();
				}
			}
		}
		super.propertyChange(evt);
	}


}
