/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.style;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.itemdata.MapDataElementsConfigurationLabelProvider;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Descriptor for the <code>StandardMapComponent.PROPERTY_PATH_STYLE_LIST</code> property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapStylesDescriptor extends NTextPropertyDescriptor {

	public MapStylesDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
//		return new MapStylesCellEditor(parent);
		return null;
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return new MapDataElementsConfigurationLabelProvider(Messages.MapStylesDescriptor_Styles);
	}
	
	@Override
	public SPMapStylesList createWidget(Composite parent, AbstractSection section) {
		return new SPMapStylesList(parent, section, this);
	}
}
