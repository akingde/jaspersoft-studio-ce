/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.text;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRHyperlinkParameter;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.hyperlink.parameter.dialog.ParameterDTO;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/*
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

	private IPropertyDescriptor[] descriptors;
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

	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evalGroupD != null)
			evalGroupD.setItems(items);
	}

	private RComboBoxPropertyDescriptor evalGroupD;

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				JRDesignTextField.PROPERTY_EVALUATION_TIME, Messages.common_evaluation_time, EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD.setDescription(Messages.MTextField_evaluation_time_description);
		desc.add(evaluationTimeD);

		evalGroupD = new RComboBoxPropertyDescriptor(JRDesignTextField.PROPERTY_EVALUATION_GROUP,
				Messages.MTextField_evaluation_group, new String[] { "" }); //$NON-NLS-1$
		evalGroupD.setDescription(Messages.MTextField_evaluation_group_description);
		desc.add(evalGroupD);

		CheckBoxPropertyDescriptor blankWhenNullD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,
				Messages.common_blank_when_null, NullEnum.INHERITED);
		blankWhenNullD.setDescription(Messages.MTextField_blank_when_null_description);
		desc.add(blankWhenNullD);

		CheckBoxPropertyDescriptor stretchOverflowD = new CheckBoxPropertyDescriptor(
				JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW, Messages.MTextField_stretch_with_overflow, NullEnum.NOTNULL);
		stretchOverflowD.setDescription(Messages.MTextField_stretch_with_overflow_description);
		desc.add(stretchOverflowD);

		JRExpressionPropertyDescriptor exprD = new JRExpressionPropertyDescriptor(JRDesignTextField.PROPERTY_EXPRESSION,
				Messages.common_expression);
		exprD.setDescription(Messages.MTextField_expression_description);
		desc.add(exprD);

		PatternPropertyDescriptor patternD = new PatternPropertyDescriptor(JRDesignStyle.PROPERTY_PATTERN,
				Messages.common_pattern);
		patternD.setDescription(Messages.MTextField_pattern_description);
		desc.add(patternD);

		JRExpressionPropertyDescriptor pexprD = new JRExpressionPropertyDescriptor(
				JRDesignTextField.PROPERTY_PATTERN_EXPRESSION, "Pattern Expression");
		pexprD.setDescription("Pattern expression");
		desc.add(pexprD);

		if (mHyperLink == null)
			mHyperLink = new MHyperLink(null);
		mHyperLink.createPropertyDescriptors(desc, defaultsMap);

		patternD.setCategory(Messages.MTextField_textfield_category);
		exprD.setCategory(Messages.MTextField_textfield_category);
		evaluationTimeD.setCategory(Messages.MTextField_textfield_category);
		evalGroupD.setCategory(Messages.MTextField_textfield_category);
		blankWhenNullD.setCategory(Messages.MTextField_textfield_category);
		stretchOverflowD.setCategory(Messages.MTextField_textfield_category);
		pexprD.setCategory(Messages.MTextField_textfield_category);

		defaultsMap.put(JRDesignTextField.PROPERTY_EVALUATION_TIME, EvaluationTimeEnum.NOW);
		defaultsMap.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL, Boolean.FALSE);
		defaultsMap.put(JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW, Boolean.FALSE);
	}

	private ParameterDTO propertyDTO;
	private MHyperLink mHyperLink;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignTextField jrElement = (JRDesignTextField) getValue();
		if (id.equals(JRDesignTextField.PROPERTY_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getExpression());
		if (id.equals(JRDesignTextField.PROPERTY_PATTERN_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getPatternExpression());

		if (id.equals(JRDesignTextField.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue(), 1, false);
		if (id.equals(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL))
			return jrElement.isOwnBlankWhenNull();
		if (id.equals(JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW))
			return new Boolean(jrElement.isStretchWithOverflow());
		if (id.equals(JRDesignStyle.PROPERTY_PATTERN))
			return jrElement.getOwnPattern();

		if (id.equals(JRDesignTextField.PROPERTY_EVALUATION_GROUP)) {
			if (jrElement.getEvaluationGroup() != null)
				return jrElement.getEvaluationGroup().getName();
			return ""; //$NON-NLS-1$
		}

		// hyperlink --------------------------------------
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			return jrElement.getLinkTarget();
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			return jrElement.getLinkType();
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			if (propertyDTO == null) {
				propertyDTO = new ParameterDTO();
				propertyDTO.setJasperDesign(getJasperDesign());
				propertyDTO.setValue(jrElement.getHyperlinkParameters());
			}
			return propertyDTO;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getHyperlinkAnchorExpression());
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getHyperlinkPageExpression());

		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getHyperlinkReferenceExpression());
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getHyperlinkTooltipExpression());
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignTextField jrElement = (JRDesignTextField) getValue();

		if (id.equals(JRDesignTextField.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1,
					false));
		else if (id.equals(JRDesignTextField.PROPERTY_EVALUATION_GROUP)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setEvaluationGroup(group);
			}
		} else if (id.equals(JRDesignTextField.PROPERTY_EXPRESSION))
			jrElement.setExpression(ExprUtil.setValues(jrElement.getExpression(), value));
		else if (id.equals(JRDesignTextField.PROPERTY_PATTERN_EXPRESSION))
			jrElement.setPatternExpression(ExprUtil.setValues(jrElement.getPatternExpression(), value));
		else if (id.equals(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL))
			jrElement.setBlankWhenNull((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_PATTERN))
			jrElement.setPattern((String) value);
		else if (id.equals(JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW))
			jrElement.setStretchWithOverflow(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement.setLinkType((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION))
			jrElement.setHyperlinkAnchorExpression(ExprUtil.setValues(jrElement.getHyperlinkAnchorExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION))
			jrElement.setHyperlinkPageExpression(ExprUtil.setValues(jrElement.getHyperlinkPageExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION))
			jrElement.setHyperlinkReferenceExpression(ExprUtil.setValues(jrElement.getHyperlinkReferenceExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION))
			jrElement.setHyperlinkTooltipExpression(ExprUtil.setValues(jrElement.getHyperlinkTooltipExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			if (value instanceof ParameterDTO) {
				ParameterDTO v = (ParameterDTO) value;

				JRHyperlinkParameter[] hyperlinkParameters = jrElement.getHyperlinkParameters();
				if (hyperlinkParameters != null)
					for (JRHyperlinkParameter prm : hyperlinkParameters)
						jrElement.removeHyperlinkParameter(prm);

				for (JRHyperlinkParameter param : v.getValue())
					jrElement.addHyperlinkParameter(param);

				propertyDTO = v;
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
