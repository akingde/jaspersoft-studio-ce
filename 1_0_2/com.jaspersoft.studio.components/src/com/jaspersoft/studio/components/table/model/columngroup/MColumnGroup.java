/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.table.model.columngroup;

import java.beans.PropertyChangeEvent;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.table.TableNodeIconDescriptor;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.util.TableColumnNumerator;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;

public class MColumnGroup extends MColumn {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	public MColumnGroup() {
		super();
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new TableNodeIconDescriptor("tablecolumngroup"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/** The descriptors. */
	protected static IPropertyDescriptor[] descriptors;

	public MColumnGroup(ANode parent, StandardColumnGroup jrDataset, String name, int index) {
		super(parent, jrDataset, name, index);
	}

	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		AMCollection section = getSection();
		if (section != null) {
			if (evt.getPropertyName().equals(StandardColumnGroup.PROPERTY_COLUMNS)) {
				if (evt.getSource() instanceof StandardColumnGroup && evt.getSource() == getValue()) {
					if (evt.getOldValue() == null && evt.getNewValue() != null) {
						int newIndex = -1;
						if (evt instanceof CollectionElementAddedEvent) {
							newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
						}
						StandardBaseColumn bc = (StandardBaseColumn) evt.getNewValue();
						if (section != null) {
							section.createColumn(this, bc, 122, newIndex);
						}
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
				}
			}
			MTable mTable = (MTable) section.getParent();
			mTable.getTableManager().refresh();
			TableColumnNumerator.renumerateColumnNames(mTable);
		}
		super.propertyChange(evt);
	}

}
