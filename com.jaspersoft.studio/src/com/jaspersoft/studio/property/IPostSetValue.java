package com.jaspersoft.studio.property;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

public interface IPostSetValue {
	public Command postSetValue(IPropertySource target, Object prop, Object value);
}
