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
package com.jaspersoft.studio.model.style;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

/**
 * The Class MStyleTemplate.
 * 
 * @author Chicu Veaceslav
 */
public class MStyleTemplate extends APropertyNode implements IPropertySource, ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("styletemplate"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m style template.
	 */
	public MStyleTemplate() {
		super();
	}

	/**
	 * Instantiates a new m style template.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrstyle
	 *          the jrstyle
	 * @param newIndex
	 *          the new index
	 */
	public MStyleTemplate(ANode parent, JRReportTemplate jrstyle, int newIndex) {
		super(parent, newIndex);
		setValue(jrstyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return iconDescriptor.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
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

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JRExpressionPropertyDescriptor sourceExpression = new JRExpressionPropertyDescriptor(
				JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION, "Source Expression");
		sourceExpression.setDescription("Source Expression");
		desc.add(sourceExpression);
	}

	private MExpression mExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();
		if (id.equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION)) {
			if (mExpression == null) {
				mExpression = new MExpression(jrTemplate.getSourceExpression());
				setChildListener(mExpression);
			}
			return mExpression;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();
		if (id.equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				setChildListener(mExpression);
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrTemplate.setSourceExpression(expression);
			}
		}
	}

	/**
	 * Creates the jr template.
	 * 
	 * @return the jR design report template
	 */
	public static JRDesignReportTemplate createJRTemplate() {
		JRDesignReportTemplate jrDesignReportTemplate = new JRDesignReportTemplate();
		return jrDesignReportTemplate;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MStyles)
			return true;
		return false;
	}

}
