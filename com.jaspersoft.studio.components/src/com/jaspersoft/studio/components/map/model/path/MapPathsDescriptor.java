/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.path;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.itemdata.MapDataElementsConfigurationLabelProvider;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Descriptor for the <code>StandardMapComponent.PROPERTY_PATH_DATA_LIST</code> property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapPathsDescriptor extends NTextPropertyDescriptor {

	public MapPathsDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
//		return new MapPathsCellEditor(parent);
		return null;
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return new MapDataElementsConfigurationLabelProvider(Messages.MapPathsDescriptor_Paths);
	}
	
	@Override
	public SPMapPathsList createWidget(Composite parent, AbstractSection section) {
		return new SPMapPathsList(parent, section, this);
	}
}
