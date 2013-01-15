/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

/*
 * The Class MStyleTemplate.
 * 
 * @author Chicu Veaceslav
 */
public class MStyleTemplate extends APropertyNode implements IPropertySource, ICopyable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
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
		JRDesignReportTemplate jt = (JRDesignReportTemplate) getValue();
		if (jt != null && jt.getSourceExpression() != null && jt.getSourceExpression().getText() != null)
			return iconDescriptor.getTitle() + "(" + jt.getSourceExpression().getText() + ")";//$NON-NLS-1$ //$NON-NLS-2$
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
				JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION, Messages.MStyleTemplate_source_expression);
		sourceExpression.setDescription(Messages.MStyleTemplate_source_expression_description);
		desc.add(sourceExpression);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();
		if (id.equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION))
			return ExprUtil.getExpression(jrTemplate.getSourceExpression());

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) getValue();
		if (id.equals(JRDesignReportTemplate.PROPERTY_SOURCE_EXPRESSION))
			jrTemplate.setSourceExpression(ExprUtil.setValues(jrTemplate.getSourceExpression(), value));
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
