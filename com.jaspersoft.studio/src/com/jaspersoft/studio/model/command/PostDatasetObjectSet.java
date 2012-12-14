package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostDatasetObjectSet implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		if (target instanceof MField && prop.equals(JRDesignField.PROPERTY_NAME))
			return new RenameDatasetObjectNameCommand((MField) target, (String) oldValue);
		if (target instanceof MVariable && prop.equals(JRDesignVariable.PROPERTY_NAME))
			return new RenameDatasetObjectNameCommand((MVariable) target, (String) oldValue);
		if (target instanceof MParameter && prop.equals(JRDesignParameter.PROPERTY_NAME))
			return new RenameDatasetObjectNameCommand((MParameter) target, (String) oldValue);
		return null;
	}

}
