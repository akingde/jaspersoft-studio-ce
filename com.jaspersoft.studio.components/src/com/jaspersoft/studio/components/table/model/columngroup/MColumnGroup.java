/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.columngroup;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.table.TableNodeIconDescriptor;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

public class MColumnGroup extends MColumn {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	public static int DEFAULT_CELL_HEIGHT = 30;

	/** The descriptors. */
	protected static IPropertyDescriptor[] descriptors;

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

	public MColumnGroup(ANode parent, StandardColumnGroup jrDataset, String name, int index) {
		super(parent, jrDataset, name, index);
	}

	@Override
	public StandardColumnGroup getValue() {
		return (StandardColumnGroup) super.getValue();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip() + ": " + getDisplayText();
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
			if (mTable == null) {
				// its a removed group, delete the listener
				if (evt.getSource() instanceof JRChangeEventsSupport) {
					((JRChangeEventsSupport) evt.getSource()).getEventSupport().removePropertyChangeListener(this);
				}
			} else
				mTable.getTableManager().refresh();
		}
		super.propertyChange(evt);
	}

	@Override
	public Map<String, List<ANode>> getUsedStyles() {
		Map<String, List<ANode>> result = super.getUsedStyles();
		StandardColumnGroup group = getValue();
		if (group != null && containerSection != null) {
			@SuppressWarnings("unchecked")
			Class<AMCollection> classType = (Class<AMCollection>) containerSection.getClass();
			int sectionType = TableColumnSize.getType(classType);
			if (sectionType == TableUtil.COLUMN_FOOTER && group.getColumnFooter() != null) {
				addElementStyle(group.getColumnFooter().getStyle(), result);
			}
			if (sectionType == TableUtil.COLUMN_HEADER && group.getColumnHeader() != null) {
				addElementStyle(group.getColumnHeader().getStyle(), result);
			}
			if (sectionType == TableUtil.TABLE_FOOTER && group.getTableFooter() != null) {
				addElementStyle(group.getTableFooter().getStyle(), result);
			}
			if (sectionType == TableUtil.TABLE_HEADER && group.getTableHeader() != null) {
				addElementStyle(group.getTableHeader().getStyle(), result);
			}
		}
		for (INode child : getChildren()) {
			if (child instanceof ANode) {
				mergeElementStyle(result, ((ANode) child).getUsedStyles());
			}
		}
		return result;
	}

}
