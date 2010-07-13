package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

public class MQuery extends APropertyNode implements IPropertySource {

	public MQuery(JRQuery jrQuery) {
		super();
		setValue(jrQuery);
	}

	@Override
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// pen
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(JRDesignQuery.PROPERTY_TEXT, "Text");
		textD.setDescription("Query text");
		desc.add(textD);

		RWComboBoxPropertyDescriptor languageD = new RWComboBoxPropertyDescriptor(JRDesignQuery.PROPERTY_LANGUAGE,
				"Language", ModelUtils.getQueryLanguages(), NullEnum.NOTNULL);
		languageD.setDescription("Specifies the query language. ");
		languageD.setCategory("Report");
		desc.add(languageD);

		defaultsMap.put(JRDesignQuery.PROPERTY_LANGUAGE, "SQL");
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
		JRQuery jrQuery = (JRQuery) getValue();
		if (jrQuery != null) {
			if (id.equals(JRDesignQuery.PROPERTY_TEXT))
				return jrQuery.getText();
			if (id.equals(JRDesignQuery.PROPERTY_LANGUAGE))
				return jrQuery.getLanguage();
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
		JRDesignQuery jrQuery = (JRDesignQuery) getValue();
		if (jrQuery != null) {
			if (id.equals(JRDesignQuery.PROPERTY_TEXT))
				jrQuery.setText((String) value);
			else if (id.equals(JRDesignQuery.PROPERTY_LANGUAGE))
				jrQuery.setLanguage(value == null ? null : ((String) value));
		}
	}

	@Override
	public String getDisplayText() {
		return null;
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

}
