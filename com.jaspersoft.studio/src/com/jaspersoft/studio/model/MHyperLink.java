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
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

public class MHyperLink extends APropertyNode {

	public MHyperLink(JRHyperlink hyperLink) {
		super();
		setValue(hyperLink);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JRExpressionPropertyDescriptor anchorExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION, Messages.MHyperLink_hyperlink_anchor_expression);
		anchorExpressionD.setDescription(Messages.MHyperLink_hyperlink_anchor_expression_description);
		desc.add(anchorExpressionD);

		JRExpressionPropertyDescriptor pageExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION, Messages.MHyperLink_hyperlink_page_expression);
		pageExpressionD.setDescription(Messages.MHyperLink_hyperlink_page_expression_description);
		desc.add(pageExpressionD);

		JRExpressionPropertyDescriptor referenceExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION, Messages.MHyperLink_hyperlink_reference_expression);
		referenceExpressionD.setDescription(Messages.MHyperLink_hyperlink_reference_expression_description);
		desc.add(referenceExpressionD);

		JRExpressionPropertyDescriptor toolTipExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION, Messages.MHyperLink_hyperlink_tooltip_expression);
		toolTipExpressionD.setDescription(Messages.MHyperLink_hyperlink_tooltip_expression_description);
		desc.add(toolTipExpressionD);

		NTextPropertyDescriptor linkTargetD = new NTextPropertyDescriptor(JRDesignHyperlink.PROPERTY_LINK_TARGET,
				Messages.MHyperLink_link_target);
		linkTargetD.setDescription(Messages.MHyperLink_link_target_description);
		desc.add(linkTargetD);

		RWComboBoxPropertyDescriptor linkTypeD = new RWComboBoxPropertyDescriptor(JRDesignHyperlink.PROPERTY_LINK_TYPE,
				Messages.MHyperLink_link_type, ModelUtils.getHyperLinkType(), NullEnum.NULL);
		linkTypeD.setDescription(Messages.MHyperLink_link_type_description);
		desc.add(linkTypeD);

		anchorExpressionD.setCategory(Messages.MHyperLink_hyperlink_category);
		pageExpressionD.setCategory(Messages.MHyperLink_hyperlink_category);
		referenceExpressionD.setCategory(Messages.MHyperLink_hyperlink_category);
		toolTipExpressionD.setCategory(Messages.MHyperLink_hyperlink_category);

		linkTargetD.setCategory(Messages.MHyperLink_hyperlink_category);
		linkTypeD.setCategory(Messages.MHyperLink_hyperlink_category);
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

	private MExpression mAnchorExpression;
	private MExpression mPageExpression;
	private MExpression mReferenceExpression;
	private MExpression mToolTipExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		// pen
		JRHyperlink hyperLink = (JRHyperlink) getValue();
		if (hyperLink != null) {
			if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
				return hyperLink.getLinkTarget();
			if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
				return hyperLink.getLinkType();
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
				if (mAnchorExpression == null) {
					mAnchorExpression = new MExpression(hyperLink.getHyperlinkAnchorExpression());
					setChildListener(mAnchorExpression);
				}
				return mAnchorExpression;
			}
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
				if (mPageExpression == null) {
					mPageExpression = new MExpression(hyperLink.getHyperlinkPageExpression());
					setChildListener(mPageExpression);
				}
				return mPageExpression;
			}
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
				if (mReferenceExpression == null) {
					mReferenceExpression = new MExpression(hyperLink.getHyperlinkReferenceExpression());
					setChildListener(mReferenceExpression);
				}
				return mReferenceExpression;
			}
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
				if (mToolTipExpression == null) {
					mToolTipExpression = new MExpression(hyperLink.getHyperlinkTooltipExpression());
					setChildListener(mToolTipExpression);
				}
				return mToolTipExpression;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignHyperlink hyperLink = (JRDesignHyperlink) getValue();
		if (hyperLink != null) {
			if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
				hyperLink.setLinkTarget((String) value);
			else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
				hyperLink.setLinkType((String) value);
			else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
				if (value instanceof MExpression) {
					mAnchorExpression = (MExpression) value;
					setChildListener(mAnchorExpression);
					JRExpression expression = (JRExpression) mAnchorExpression.getValue();
					hyperLink.setHyperlinkAnchorExpression(expression);
				}
			} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
				if (value instanceof MExpression) {
					mPageExpression = (MExpression) value;
					setChildListener(mPageExpression);
					JRExpression expression = (JRExpression) mPageExpression.getValue();
					hyperLink.setHyperlinkPageExpression(expression);
				}
			} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
				if (value instanceof MExpression) {
					mReferenceExpression = (MExpression) value;
					setChildListener(mReferenceExpression);
					JRExpression expression = (JRExpression) mReferenceExpression.getValue();
					hyperLink.setHyperlinkReferenceExpression(expression);
				}
			} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
				if (value instanceof MExpression) {
					mToolTipExpression = (MExpression) value;
					setChildListener(mToolTipExpression);
					JRExpression expression = (JRExpression) mToolTipExpression.getValue();
					hyperLink.setHyperlinkTooltipExpression(expression);
				}
			}
		}
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
