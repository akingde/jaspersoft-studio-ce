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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.html.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.html.HtmlComponent;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.html.HtmlNodeIconDescriptor;
import com.jaspersoft.studio.html.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MHtml extends MGraphicElement {

	private IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;
	private static IIconDescriptor iconDescriptor;
	private RComboBoxPropertyDescriptor evaluationGroupNameD;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new HtmlNodeIconDescriptor("html"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MHtml() {
		super();
	}

	public MHtml(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MHtml(ANode parent, JRDesignComponentElement jrHtml, int newIndex) {
		this(parent, newIndex);
		setValue(jrHtml);
	}

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		HtmlComponent component = new HtmlComponent();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setValueClassName("java.lang.String"); //$NON-NLS-1$
		exp.setText("\"<p style='background-color:yellow;'>HTML paragraph</p>\""); //$NON-NLS-1$
		component.setHtmlContentExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey(
				"http://jasperreports.sourceforge.net/htmlcomponent", "hc", "html")); //$NON-NLS-1$
		return el;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		JRExpressionPropertyDescriptor contentExprD = new JRExpressionPropertyDescriptor(
				HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION,
				Messages.MHtml_content_expression);
		contentExprD
				.setDescription(Messages.MHtml_content_expression_description);
		desc.add(contentExprD);

		ComboBoxPropertyDescriptor scaleTypeD = new ComboBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_SCALE_TYPE, Messages.MHtml_scaletype,
				EnumHelper.getEnumNames(ScaleImageEnum.values(),
						NullEnum.NOTNULL));
		scaleTypeD.setDescription(Messages.MHtml_scaletype_description);
		desc.add(scaleTypeD);

		ComboBoxPropertyDescriptor hAlignD = new ComboBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_HORIZONTAL_ALIGN,
				Messages.MHtml_horizontalalign, EnumHelper.getEnumNames(
						HorizontalAlignEnum.values(), NullEnum.NOTNULL, 3));
		hAlignD.setDescription(Messages.MHtml_horizontalalign_description);
		desc.add(hAlignD);

		ComboBoxPropertyDescriptor vAlignD = new ComboBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_VERTICAL_ALIGN,
				Messages.MHtml_verticalalign, EnumHelper.getEnumNames(
						VerticalAlignEnum.values(), NullEnum.NOTNULL, 3));
		vAlignD.setDescription(Messages.MHtml_verticalalign_description);
		desc.add(vAlignD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_EVALUATION_TIME,
				Messages.MHtml_evaluation_time, EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD
				.setDescription(Messages.MHtml_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(
				HtmlComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MHtml_evaluation_group, new String[] { "" }); //$NON-NLS-1$
		evaluationGroupNameD
				.setDescription(Messages.MHtml_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		contentExprD.setCategory(Messages.common_properties_category);
		scaleTypeD.setCategory(Messages.common_properties_category);
		hAlignD.setCategory(Messages.common_properties_category);
		vAlignD.setCategory(Messages.common_properties_category);
		evaluationTimeD.setCategory(Messages.common_properties_category);
		evaluationGroupNameD.setCategory(Messages.common_properties_category);

		defaultsMap.put(HtmlComponent.PROPERTY_EVALUATION_TIME,
				EvaluationTimeEnum.NOW);
		defaultsMap.put(HtmlComponent.PROPERTY_SCALE_TYPE,
				ScaleImageEnum.RETAIN_SHAPE);
		defaultsMap.put(HtmlComponent.PROPERTY_HORIZONTAL_ALIGN,
				HorizontalAlignEnum.LEFT);
		defaultsMap.put(HtmlComponent.PROPERTY_VERTICAL_ALIGN,
				VerticalAlignEnum.MIDDLE);
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.removePropertyChangeListener(this);
		} else if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.addPropertyChangeListener(this);
			super.setValue(value);
			return;
		}
		super.setValue(value);
	}

	private Object getComponent() {
		return getComponent(getValue());
	}

	private Object getComponent(Object value) {
		if (value != null) {
			JRDesignComponentElement jrElement = (JRDesignComponentElement) value;
			return jrElement.getComponent();
		}
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		HtmlComponent htmlComp = (HtmlComponent) jrElement.getComponent();

		if (id.equals(HtmlComponent.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(htmlComp.getEvaluationTime(), 1, false);
		if (id.equals(HtmlComponent.PROPERTY_EVALUATION_GROUP))
			return htmlComp.getEvaluationGroup();
		if (id.equals(HtmlComponent.PROPERTY_SCALE_TYPE)) {
			return EnumHelper.getValue(htmlComp.getScaleType(), 1, false);
		}
		if (id.equals(HtmlComponent.PROPERTY_HORIZONTAL_ALIGN))
			return EnumHelper.getValue(htmlComp.getHorizontalAlign(), 1, false);
		if (id.equals(HtmlComponent.PROPERTY_VERTICAL_ALIGN))
			return EnumHelper.getValue(htmlComp.getVerticalAlign(), 1, false);
		if (id.equals(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION))
			return ExprUtil.getExpression(htmlComp.getHtmlContentExpression());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		HtmlComponent htmlComp = (HtmlComponent) jrElement.getComponent();

		if (id.equals(HtmlComponent.PROPERTY_EVALUATION_TIME))
			htmlComp.setEvaluationTime((EvaluationTimeEnum) EnumHelper
					.getSetValue(EvaluationTimeEnum.values(), value, 1, false));
		else if (id.equals(HtmlComponent.PROPERTY_EVALUATION_GROUP))
			htmlComp.setEvaluationGroup((String) value);
		else if (id.equals(HtmlComponent.PROPERTY_SCALE_TYPE)) {
			htmlComp.setScaleType((ScaleImageEnum) EnumHelper.getSetValue(
					ScaleImageEnum.values(), value, 1, false));
		} else if (id.equals(HtmlComponent.PROPERTY_HORIZONTAL_ALIGN))
			htmlComp.setHorizontalAlign((HorizontalAlignEnum) EnumHelper
					.getSetValue(HorizontalAlignEnum.values(), value, 1, false));
		else if (id.equals(HtmlComponent.PROPERTY_VERTICAL_ALIGN))
			htmlComp.setVerticalAlign((VerticalAlignEnum) EnumHelper
					.getSetValue(VerticalAlignEnum.values(), value, 1, false));
		else if (id.equals(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION)) {
			htmlComp.setHtmlContentExpression(ExprUtil.setValues(
					htmlComp.getHtmlContentExpression(), value));
		} else
			super.setPropertyValue(id, value);
	}
}
