/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// pen
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(JRDesignQuery.PROPERTY_TEXT, Messages.MQuery_text);
		textD.setDescription(Messages.MQuery_text_description);
		desc.add(textD);

		RWComboBoxPropertyDescriptor languageD = new RWComboBoxPropertyDescriptor(JRDesignQuery.PROPERTY_LANGUAGE,
				Messages.MQuery_language, ModelUtils.getQueryLanguages(), NullEnum.NOTNULL);
		languageD.setDescription(Messages.MQuery_language_description);
		languageD.setCategory(Messages.MQuery_report_category);
		desc.add(languageD);

		defaultsMap.put(JRDesignQuery.PROPERTY_LANGUAGE, "SQL"); //$NON-NLS-1$
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
	public void setPropertyValue(Object id, Object value) {
		JRDesignQuery jrQuery = (JRDesignQuery) getValue();
		if (jrQuery != null) {
			if (id.equals(JRDesignQuery.PROPERTY_TEXT))
				jrQuery.setText((String) value);
			else if (id.equals(JRDesignQuery.PROPERTY_LANGUAGE))
				jrQuery.setLanguage(value == null ? null : ((String) value));
		}
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
