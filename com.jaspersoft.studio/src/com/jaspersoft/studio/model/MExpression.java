package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MExpression extends APropertyNode implements IPropertySource {

	public MExpression(JRExpression jrExpression) {
		super();
		setValue(jrExpression);
	}

	@Override
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// pen
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(JRDesignExpression.PROPERTY_TEXT, "Text");
		textD.setDescription("Expression text");
		desc.add(textD);

		ClassTypePropertyDescriptor formatFactoryClassD = new ClassTypePropertyDescriptor(
				JRDesignExpression.PROPERTY_VALUE_CLASS_NAME, "Value Class Name");
		formatFactoryClassD.setDescription("Value class name.");
		desc.add(formatFactoryClassD);

		defaultsMap.put(JRDesignExpression.PROPERTY_VALUE_CLASS_NAME, "java.lang.Boolean");
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignExpression jrExpression = (JRDesignExpression) getValue();
		if (jrExpression != null) {
			if (id.equals(JRDesignExpression.PROPERTY_TEXT))
				return jrExpression.getText();
			if (id.equals(JRDesignExpression.PROPERTY_VALUE_CLASS_NAME))
				return jrExpression.getValueClassName();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignExpression jrExpression = (JRDesignExpression) getValue();
		if (jrExpression != null) {
			if (id.equals(JRDesignExpression.PROPERTY_TEXT))
				jrExpression.setText((String) value);
			else if (id.equals(JRDesignExpression.PROPERTY_VALUE_CLASS_NAME))
				jrExpression.setValueClassName((String) value);
		}
	}

	@Override
	public String toString() {
		return getDisplayText();
	}

	@Override
	public String getDisplayText() {
		return ((JRDesignExpression) getValue()).getText();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

}
