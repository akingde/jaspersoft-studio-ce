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

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MExpression extends APropertyNode implements IPropertySource {

	public MExpression(JRExpression jrExpression) {
		super();
		setValue(jrExpression);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		// pen
		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(JRDesignExpression.PROPERTY_TEXT, Messages.common_text);
		textD.setDescription(Messages.MExpression_text_description);
		desc.add(textD);

		RComboBoxPropertyDescriptor languageD = new RComboBoxPropertyDescriptor(
				JRDesignExpression.PROPERTY_VALUE_CLASS_NAME, Messages.common_value_class_name, new String[] {
						"java.lang.Boolean", //$NON-NLS-1$
						"java.lang.Byte", "java.util.Date", "java.sql.Timestamp", "java.sql.Time", "java.lang.Double", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						"java.lang.Float", "java.lang.Integer", "java.lang.Long", "java.lang.Short", "java.math.BigDecimal", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						"java.lang.Number", "java.lang.String" }); //$NON-NLS-1$ //$NON-NLS-2$
		languageD.setDescription(Messages.MExpression_value_class_name_description);
		languageD.setCategory(Messages.common_report);
		desc.add(languageD);

		defaultsMap.put(JRDesignExpression.PROPERTY_VALUE_CLASS_NAME, "java.lang.String"); //$NON-NLS-1$
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

	public String getDisplayText() {
		return ((JRDesignExpression) getValue()).getText();
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
