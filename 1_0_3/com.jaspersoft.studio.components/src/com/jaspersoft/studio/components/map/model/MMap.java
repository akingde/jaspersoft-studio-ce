/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.map.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.MapNodeIconDescriptor;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * 
 * @author sanda zaharia
 * 
 */
public class MMap extends MGraphicElement {

	public MMap() {
		super();
	}

	public MMap(ANode parent, JRDesignComponentElement jrObject, int newIndex) {
		super(parent, jrObject, newIndex);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new MapNodeIconDescriptor("map"); //$NON-NLS-1$
		return iconDescriptor;
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		JRExpressionPropertyDescriptor latitudeExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
				Messages.MMap_latitude);
		latitudeExprD.setDescription(Messages.MMap_latitude_description);
		desc.add(latitudeExprD);

		JRExpressionPropertyDescriptor longitudeExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
				Messages.MMap_longitude);
		longitudeExprD.setDescription(Messages.MMap_longitude_description);
		desc.add(longitudeExprD);

		JRExpressionPropertyDescriptor zoomExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_ZOOM_EXPRESSION,
				Messages.MMap_zoom);
		zoomExprD.setDescription(Messages.MMap_zoom_description);
		desc.add(zoomExprD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				StandardMapComponent.PROPERTY_EVALUATION_TIME,
				Messages.MMap_evaluation_time, EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD
				.setDescription(Messages.MMap_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(
				StandardMapComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MMap_evaluation_group, new String[] { "" }); //$NON-NLS-2$
		evaluationGroupNameD
				.setDescription(Messages.MMap_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		evaluationTimeD.setCategory(Messages.MMap_common_map_properties);
		evaluationGroupNameD.setCategory(Messages.MMap_common_map_properties);
		latitudeExprD.setCategory(Messages.MMap_common_map_properties);
		longitudeExprD.setCategory(Messages.MMap_common_map_properties);
		zoomExprD.setCategory(Messages.MMap_common_map_properties);

		defaultsMap.put(StandardMapComponent.PROPERTY_EVALUATION_TIME,
				EvaluationTimeEnum.NOW);
		defaultsMap.put(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION,
				MapComponent.DEFAULT_ZOOM);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardMapComponent component = (StandardMapComponent) jrElement
				.getComponent();

		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(component.getEvaluationTime(), 1, false);
		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_GROUP))
			return component.getEvaluationGroup();

		if (id.equals(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION))
			return ExprUtil.getExpression(component.getLongitudeExpression());
		if (id.equals(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION))
			return ExprUtil.getExpression(component.getLatitudeExpression());
		if (id.equals(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION))
			return ExprUtil.getExpression(component.getZoomExpression());
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardMapComponent jrList = (StandardMapComponent) jrElement
				.getComponent();

		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_TIME))
			jrList.setEvaluationTime((EvaluationTimeEnum) EnumHelper
					.getSetValue(EvaluationTimeEnum.values(), value, 1, false));
		else if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_GROUP))
			jrList.setEvaluationGroup((String) value);
		else if (id.equals(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION)) {
			jrList.setLongitudeExpression(ExprUtil.setValues(
					jrList.getLongitudeExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION)) {
			jrList.setLatitudeExpression(ExprUtil.setValues(
					jrList.getLatitudeExpression(), value, null));

		} else if (id.equals(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION)) {
			jrList.setZoomExpression(ExprUtil.setValues(
					jrList.getZoomExpression(), value, null));

		} else
			super.setPropertyValue(id, value);
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	private RComboBoxPropertyDescriptor evaluationGroupNameD;

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.removePropertyChangeListener(this);
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.addPropertyChangeListener(this);
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
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement designMap = new JRDesignComponentElement();
		StandardMapComponent component = new StandardMapComponent();
		JRDesignExpression exp1 = new JRDesignExpression();
		exp1.setText("0f"); //$NON-NLS-1$
		JRDesignExpression exp2 = new JRDesignExpression();
		exp2.setText("0f"); //$NON-NLS-1$
		component.setLatitudeExpression(exp1);
		component.setLongitudeExpression(exp2);
		JRDesignExpression exp3 = new JRDesignExpression();
		exp3.setText(String.valueOf(MapComponent.DEFAULT_ZOOM));
		component.setZoomExpression(exp3);
		designMap.setComponent(component);
		designMap
				.setComponentKey(new ComponentKey(
						"http://jasperreports.sourceforge.net/jasperreports/components", "jr", //$NON-NLS-1$ //$NON-NLS-2$
						"map")); //$NON-NLS-1$
		return designMap;
	}

}
