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
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MHyperLink extends APropertyNode implements IPropertySource {

	public MHyperLink(JRHyperlink hyperLink) {
		super();
		setValue(hyperLink);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JRExpressionPropertyDescriptor anchorExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION, "Hyperlink Anchor Expression");
		anchorExpressionD
				.setDescription("The current hyperlink points to a local anchor specified by the corresponding <hyperlinkAnchorExpression> element.");
		desc.add(anchorExpressionD);

		JRExpressionPropertyDescriptor pageExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION, "Hyperlink Page Expression");
		pageExpressionD
				.setDescription("The current hyperlink points to a 1 based page index within the current document specified by the corresponding <hyperlinkPageExpression> element.");
		desc.add(pageExpressionD);

		JRExpressionPropertyDescriptor referenceExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION, "Hyperlink Reference Expression");
		referenceExpressionD
				.setDescription("The current hyperlink points to an external resource specified by the corresponding <hyperlinkReferenceExpression> element, usually an URL.");
		desc.add(referenceExpressionD);

		JRExpressionPropertyDescriptor toolTipExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION, "Hyperlink Tooltip Expression");
		toolTipExpressionD
				.setDescription("Used in hyperlink elements to generate a tooltip for the hyperlink. The type of the expression should be java.lang.String.");
		desc.add(toolTipExpressionD);

		NTextPropertyDescriptor linkTargetD = new NTextPropertyDescriptor(JRDesignHyperlink.PROPERTY_LINK_TARGET,
				"Link Target");
		linkTargetD.setDescription("Indicates the target of the hyperlink.");
		desc.add(linkTargetD);

		ComboBoxPropertyDescriptor linkTypeD = new ComboBoxPropertyDescriptor(JRDesignHyperlink.PROPERTY_LINK_TYPE,
				"Link Type", EnumHelper.getEnumNames(HyperlinkTypeEnum.values(), NullEnum.NOTNULL));
		linkTypeD.setDescription("Indicates the type of the hyperlink element. ");
		desc.add(linkTypeD);

		anchorExpressionD.setCategory("Hyperlink");
		pageExpressionD.setCategory("Hyperlink");
		referenceExpressionD.setCategory("Hyperlink");
		toolTipExpressionD.setCategory("Hyperlink");

		linkTargetD.setCategory("Hyperlink");
		linkTypeD.setCategory("Hyperlink");
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
	@Override
	public Object getPropertyValue(Object id) {
		// pen
		JRHyperlink hyperLink = (JRHyperlink) getValue();
		if (hyperLink != null) {
			if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
				return hyperLink.getLinkTarget();
			if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
				return EnumHelper.getValue(hyperLink.getHyperlinkTypeValue(), 0, false);
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
				if (mAnchorExpression == null)
					mAnchorExpression = new MExpression(hyperLink.getHyperlinkAnchorExpression());
				return mAnchorExpression;
			}
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
				if (mPageExpression == null)
					mPageExpression = new MExpression(hyperLink.getHyperlinkPageExpression());
				return mPageExpression;
			}
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
				if (mReferenceExpression == null)
					mReferenceExpression = new MExpression(hyperLink.getHyperlinkReferenceExpression());
				return mReferenceExpression;
			}
			if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
				if (mToolTipExpression == null)
					mToolTipExpression = new MExpression(hyperLink.getHyperlinkTooltipExpression());
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
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignHyperlink hyperLink = (JRDesignHyperlink) getValue();
		if (hyperLink != null) {
			if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
				hyperLink.setLinkTarget((String) value);
			else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
				hyperLink.setHyperlinkType((HyperlinkTypeEnum) EnumHelper.getSetValue(HyperlinkTypeEnum.values(), value, 0,
						false));
			else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
				if (value instanceof MExpression) {
					mAnchorExpression = (MExpression) value;
					JRExpression expression = (JRExpression) mAnchorExpression.getValue();
					hyperLink.setHyperlinkAnchorExpression(expression);
				}
			} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
				if (value instanceof MExpression) {
					mPageExpression = (MExpression) value;
					JRExpression expression = (JRExpression) mPageExpression.getValue();
					hyperLink.setHyperlinkPageExpression(expression);
				}
			} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
				if (value instanceof MExpression) {
					mReferenceExpression = (MExpression) value;
					JRExpression expression = (JRExpression) mReferenceExpression.getValue();
					hyperLink.setHyperlinkReferenceExpression(expression);
				}
			} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
				if (value instanceof MExpression) {
					mToolTipExpression = (MExpression) value;
					JRExpression expression = (JRExpression) mToolTipExpression.getValue();
					hyperLink.setHyperlinkTooltipExpression(expression);
				}
			}
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
