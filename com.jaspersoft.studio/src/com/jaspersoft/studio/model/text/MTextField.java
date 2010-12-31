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
package com.jaspersoft.studio.model.text;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MTextField.
 */
public class MTextField extends MTextElement {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("textfield"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m text field.
	 */
	public MTextField() {
		super();
	}

	/**
	 * Instantiates a new m text field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrStaticText
	 *          the jr static text
	 * @param newIndex
	 *          the new index
	 */
	public MTextField(ANode parent, JRTextField jrStaticText, int newIndex) {
		super(parent, newIndex);
		setValue(jrStaticText);
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				JRDesignTextField.PROPERTY_EVALUATION_TIME, Messages.MTextField_evaluation_time, EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NULL));
		evaluationTimeD.setDescription(Messages.MTextField_evaluation_time_description);
		desc.add(evaluationTimeD);

		CheckBoxPropertyDescriptor blankWhenNullD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,
				Messages.MTextField_blank_when_null, NullEnum.INHERITED);
		blankWhenNullD.setDescription(Messages.MTextField_blank_when_null_description);
		desc.add(blankWhenNullD);

		CheckBoxPropertyDescriptor stretchOverflowD = new CheckBoxPropertyDescriptor(
				JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW, Messages.MTextField_stretch_with_overflow, NullEnum.NOTNULL);
		stretchOverflowD.setDescription(Messages.MTextField_stretch_with_overflow_description);
		desc.add(stretchOverflowD);

		JRExpressionPropertyDescriptor exprD = new JRExpressionPropertyDescriptor(JRDesignTextField.PROPERTY_EXPRESSION,
				Messages.MTextField_expression);
		exprD.setDescription(Messages.MTextField_expression_description);
		desc.add(exprD);

		PatternPropertyDescriptor patternD = new PatternPropertyDescriptor(JRDesignStyle.PROPERTY_PATTERN,
				Messages.MTextField_pattern);
		patternD.setDescription(Messages.MTextField_pattern_description);
		desc.add(patternD);

		if (mHyperLink == null)
			mHyperLink = new MHyperLink(null);
		mHyperLink.createPropertyDescriptors(desc, defaultsMap);

		patternD.setCategory(Messages.MTextField_textfield_category);
		exprD.setCategory(Messages.MTextField_textfield_category);
		evaluationTimeD.setCategory(Messages.MTextField_textfield_category);
		blankWhenNullD.setCategory(Messages.MTextField_textfield_category);
		stretchOverflowD.setCategory(Messages.MTextField_textfield_category);

		defaultsMap.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL, Boolean.FALSE);
		defaultsMap.put(JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW, Boolean.FALSE);
	}

	private MExpression mExpression;
	private MHyperLink mHyperLink;

	private MExpression mAnchorExpression;
	private MExpression mPageExpression;
	private MExpression mReferenceExpression;
	private MExpression mToolTipExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTextField jrElement = (JRDesignTextField) getValue();
		if (id.equals(JRDesignTextField.PROPERTY_EXPRESSION)) {
			if (mExpression == null) {
				mExpression = new MExpression(jrElement.getExpression());
				setChildListener(mExpression);
			}
			return mExpression;
		}
		if (id.equals(JRDesignTextField.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue());
		if (id.equals(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL))
			return jrElement.isOwnBlankWhenNull();
		if (id.equals(JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW))
			return new Boolean(jrElement.isStretchWithOverflow());
		if (id.equals(JRDesignStyle.PROPERTY_PATTERN))
			return jrElement.getOwnPattern();

		// hyperlink --------------------------------------
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			return jrElement.getLinkTarget();
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			return EnumHelper.getValue(jrElement.getHyperlinkTypeValue(), 0, false);
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			if (mAnchorExpression == null) {
				mAnchorExpression = new MExpression(jrElement.getHyperlinkAnchorExpression());
				setChildListener(mAnchorExpression);
			}
			return mAnchorExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			if (mPageExpression == null) {
				mPageExpression = new MExpression(jrElement.getHyperlinkPageExpression());
				setChildListener(mPageExpression);
			}
			return mPageExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			if (mReferenceExpression == null) {
				mReferenceExpression = new MExpression(jrElement.getHyperlinkReferenceExpression());
				setChildListener(mReferenceExpression);
			}
			return mReferenceExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			if (mToolTipExpression == null) {
				mToolTipExpression = new MExpression(jrElement.getHyperlinkTooltipExpression());
				setChildListener(mToolTipExpression);
			}
			return mToolTipExpression;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignTextField jrElement = (JRDesignTextField) getValue();

		if (id.equals(JRDesignTextField.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime(EvaluationTimeEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRDesignTextField.PROPERTY_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				setChildListener(mExpression);
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrElement.setExpression(expression);
			}
		} else if (id.equals(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL))
			jrElement.setBlankWhenNull((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_PATTERN))
			jrElement.setPattern((String) value);
		else if (id.equals(JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW))
			jrElement.setStretchWithOverflow(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement
					.setHyperlinkType((HyperlinkTypeEnum) EnumHelper.getSetValue(HyperlinkTypeEnum.values(), value, 0, false));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			if (value instanceof MExpression) {
				mAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mAnchorExpression.getValue();
				jrElement.setHyperlinkAnchorExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mPageExpression = (MExpression) value;
				setChildListener(mPageExpression);
				JRExpression expression = (JRExpression) mPageExpression.getValue();
				jrElement.setHyperlinkPageExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mReferenceExpression = (MExpression) value;
				setChildListener(mReferenceExpression);
				JRExpression expression = (JRExpression) mReferenceExpression.getValue();
				jrElement.setHyperlinkReferenceExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			if (value instanceof MExpression) {
				mToolTipExpression = (MExpression) value;
				setChildListener(mToolTipExpression);
				JRExpression expression = (JRExpression) mToolTipExpression.getValue();
				jrElement.setHyperlinkTooltipExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 30;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 70;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return new JRDesignTextField();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		if (getValue() != null) {
			JRTextField jrTextField = (JRTextField) getValue();
			if (jrTextField.getExpression() != null)
				return jrTextField.getExpression().getText();
		}
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
