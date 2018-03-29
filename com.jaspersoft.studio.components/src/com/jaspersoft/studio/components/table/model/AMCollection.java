/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model;

import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MCollection;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

public abstract class AMCollection extends MCollection {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String REFRESH_COLUM_NAMES = "refreshColumnNamesRequest";

	public abstract String getCellEvent();

	public abstract void createColumn(ANode mth, BaseColumn bc, int i, int index);

	public AMCollection(ANode parent, JRDesignComponentElement jrDataset, String property) {
		super(parent, jrDataset, property);
	}

	@Override
	public void register() {
		// the table collections doesn't register them self because
		// the would use the same key of the table (the JRDesignComponentElement)
		// overriding the registration of the table, that is the real node
		// associated with the key
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#getValue()
	 */
	@Override
	public JRDesignComponentElement getValue() {
		return (JRDesignComponentElement) super.getValue();
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

	public StandardTable getStandardTable() {
		if (getValue() == null)
			return null;
		return (StandardTable) getValue().getComponent();
	}

	@Override
	public Map<String, List<ANode>> getUsedStyles() {
		Map<String, List<ANode>> result = super.getUsedStyles();
		for (INode child : getChildren()) {
			if (child instanceof ANode) {
				mergeElementStyle(result, ((ANode) child).getUsedStyles());
			}
		}
		return result;
	}

	public MTable getMTable() {
		return (MTable) getParent();
	}
}
