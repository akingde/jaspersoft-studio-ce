package com.jaspersoft.studio.property;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
 * Type mapper for the logic example. We want to get the GEF model
 * object from the selected element in the outline view and the diagram.
 * We can then filter on the model object type.
 * 
 * @author Anthony Hunter 
 */
public class ElementTypeMapper
	implements ITypeMapper {

	/**
	 * @inheritDoc 
	 */
	public Class mapType(Object object) {
		Class<?> type = object.getClass();
		if (object instanceof EditPart) {
			type = ((EditPart) object).getModel().getClass();
		}
		return type;
	}
}