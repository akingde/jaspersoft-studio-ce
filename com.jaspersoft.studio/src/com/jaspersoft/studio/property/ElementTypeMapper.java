/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import org.eclipse.gef.EditPart;

import com.jaspersoft.studio.properties.view.ITypeMapper;
/*
 * Type mapper for the logic example. We want to get the GEF model object from the selected element in the outline view
 * and the diagram. We can then filter on the model object type.
 * 
 */
public class ElementTypeMapper implements ITypeMapper {

	/**
	 * @inheritDoc
	 */
	public Class<?> mapType(Object object) {
		Class<?> type = object.getClass();
		if (object instanceof EditPart) {
			EditPart part =(EditPart) object;
			if (part.getModel() != null) type = part.getModel().getClass();
		}
		return type;
	}
}
