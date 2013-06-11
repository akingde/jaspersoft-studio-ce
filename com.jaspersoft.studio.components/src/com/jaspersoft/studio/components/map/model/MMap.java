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
package com.jaspersoft.studio.components.map.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.components.map.type.MapImageTypeEnum;
import net.sf.jasperreports.components.map.type.MapScaleEnum;
import net.sf.jasperreports.components.map.type.MapTypeEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.util.JRCloneUtils;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.MapNodeIconDescriptor;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.marker.MarkerDescriptor;
import com.jaspersoft.studio.components.map.model.marker.MarkersDTO;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.jasper.MapDesignConverter;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetRunPropertyDescriptor;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * 
 * @author sanda zaharia
 * 
 */
public class MMap extends MGraphicElement {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MMap() {
		super();
	}

	public MMap(ANode parent, JRDesignComponentElement jrObject, int newIndex) {
		super(parent, jrObject, newIndex);
		listenMap();
	}

	@Override
	public void setParent(ANode parent, int newIndex) {
		super.setParent(parent, newIndex);
		if (parent == null) {
			unlistenMap();
		}
	}

	private void listenMap() {
		StandardMapComponent m = getMapComponent();
		if (m != null && m.getMarkerData() != null) {
			((StandardItemData) m.getMarkerData()).getEventSupport().addPropertyChangeListener(this);
		}
	}

	private void unlistenMap() {
		StandardMapComponent m = getMapComponent();
		if (m != null && m.getMarkerData() != null) {
			((StandardItemData) m.getMarkerData()).getEventSupport().removePropertyChangeListener(this);
		}
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
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		JRExpressionPropertyDescriptor latitudeExprD = new JRExpressionPropertyDescriptor(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION, Messages.MMap_latitude);
		latitudeExprD.setDescription(Messages.MMap_latitude_description);
		desc.add(latitudeExprD);
		latitudeExprD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/components.schema.reference.html#latitudeExpression"));

		JRExpressionPropertyDescriptor longitudeExprD = new JRExpressionPropertyDescriptor(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION, Messages.MMap_longitude);
		longitudeExprD.setDescription(Messages.MMap_longitude_description);
		desc.add(longitudeExprD);
		longitudeExprD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/components.schema.reference.html#longitudeExpression"));

		JRExpressionPropertyDescriptor zoomExprD = new JRExpressionPropertyDescriptor(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, Messages.MMap_zoom);
		zoomExprD.setDescription(Messages.MMap_zoom_description);
		desc.add(zoomExprD);
		zoomExprD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/components.schema.reference.html#zoomExpression"));

		JRExpressionPropertyDescriptor langExprD = new JRExpressionPropertyDescriptor(StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION, "Language Expression");
		langExprD.setDescription("Language Expression");
		desc.add(langExprD);
		langExprD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/components.schema.reference.html#languageExpression"));

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(StandardMapComponent.PROPERTY_EVALUATION_TIME, Messages.MMap_evaluation_time, EnumHelper.getEnumNames(
				EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD.setDescription(Messages.MMap_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(StandardMapComponent.PROPERTY_EVALUATION_GROUP, Messages.MMap_evaluation_group, new String[] { "" }); //$NON-NLS-2$
		evaluationGroupNameD.setDescription(Messages.MMap_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		mapTypeD = new JSSEnumPropertyDescriptor(StandardMapComponent.PROPERTY_MAP_TYPE, "Map Type", MapTypeEnum.class, NullEnum.NOTNULL);
		mapTypeD.setDescription("Map type.");
		desc.add(mapTypeD);

		mapScaleD = new JSSEnumPropertyDescriptor(StandardMapComponent.PROPERTY_MAP_SCALE, "Map Scale", MapScaleEnum.class, NullEnum.NOTNULL);
		mapScaleD.setDescription("Scale.");
		desc.add(mapScaleD);

		imageTypeD = new JSSEnumPropertyDescriptor(StandardMapComponent.PROPERTY_IMAGE_TYPE, "Image Type", MapImageTypeEnum.class, NullEnum.NOTNULL);
		imageTypeD.setDescription("Image type.");
		desc.add(imageTypeD);

		DatasetRunPropertyDescriptor datasetRunD = new DatasetRunPropertyDescriptor(JRDesignElementDataset.PROPERTY_DATASET_RUN, "Marker Dataset Run", true);
		datasetRunD.setDescription("Marker Dataset Run");
		desc.add(datasetRunD);

		MarkerDescriptor markersD = new MarkerDescriptor(StandardItemData.PROPERTY_ITEMS, "Markers");
		markersD.setDescription("Markers");
		desc.add(markersD);
		markersD.setHelpRefBuilder(new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/components.schema.reference.html#markerDataset"));

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/components.schema.reference.html#");

		markersD.setCategory(Messages.MMap_common_map_properties);
		langExprD.setCategory(Messages.MMap_common_map_properties);
		datasetRunD.setCategory(Messages.MMap_common_map_properties);
		mapTypeD.setCategory(Messages.MMap_common_map_properties);
		mapScaleD.setCategory(Messages.MMap_common_map_properties);
		imageTypeD.setCategory(Messages.MMap_common_map_properties);
		evaluationTimeD.setCategory(Messages.MMap_common_map_properties);
		evaluationGroupNameD.setCategory(Messages.MMap_common_map_properties);
		latitudeExprD.setCategory(Messages.MMap_common_map_properties);
		longitudeExprD.setCategory(Messages.MMap_common_map_properties);
		zoomExprD.setCategory(Messages.MMap_common_map_properties);

		defaultsMap.put(StandardMapComponent.PROPERTY_MAP_TYPE, MapTypeEnum.ROADMAP);
		defaultsMap.put(StandardMapComponent.PROPERTY_MAP_TYPE, MapScaleEnum.ONE);
		defaultsMap.put(StandardMapComponent.PROPERTY_IMAGE_TYPE, MapImageTypeEnum.PNG);
		defaultsMap.put(StandardMapComponent.PROPERTY_EVALUATION_TIME, EvaluationTimeEnum.NOW);
		defaultsMap.put(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, MapComponent.DEFAULT_ZOOM);
	}

	private MDatasetRun mDatasetRun;

	@Override
	public Object getPropertyValue(Object id) {
		StandardMapComponent component = getMapComponent();

		if (id.equals(StandardItemData.PROPERTY_ITEMS)) {
			List<Item> markers = ModelUtils.safeGetMarkerData(component, false).getItems();
			if (markers == null)
				markers = new ArrayList<Item>();
			else {
				markers = JRCloneUtils.cloneList(markers);
			}
			return new MarkersDTO(markers, this);
		}
		if (id.equals(StandardItemData.PROPERTY_DATASET)) {
			if(component.getMarkerData()!=null) {
				return component.getMarkerData().getDataset();
			}
			return null;
		}
		if (id.equals(JRDesignElementDataset.PROPERTY_DATASET_RUN)) {
			JRElementDataset markerdataset = ModelUtils.safeGetMarkerData(component, false).getDataset();
			JRDatasetRun j = null;
			if (markerdataset != null)
				j = markerdataset.getDatasetRun();
			if (j == null)
				j = new JRDesignDatasetRun();
			else
				j = (JRDatasetRun) j.clone();
			if (mDatasetRun != null)
				mDatasetRun.setValue(j);
			else {
				mDatasetRun = new MDatasetRun(j, getJasperDesign());
				setChildListener(mDatasetRun);
			}
			return mDatasetRun;
		}

		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(component.getEvaluationTime(), 1, false);
		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_GROUP))
			return component.getEvaluationGroup();

		if (id.equals(StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION))
			return ExprUtil.getExpression(component.getLanguageExpression());
		if (id.equals(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION))
			return ExprUtil.getExpression(component.getLongitudeExpression());
		if (id.equals(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION))
			return ExprUtil.getExpression(component.getLatitudeExpression());
		if (id.equals(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION))
			return ExprUtil.getExpression(component.getZoomExpression());

		if (id.equals(StandardMapComponent.PROPERTY_MAP_TYPE))
			return mapTypeD.getEnumValue(component.getMapType());
		if (id.equals(StandardMapComponent.PROPERTY_MAP_SCALE))
			return mapScaleD.getEnumValue(component.getMapScale());
		if (id.equals(StandardMapComponent.PROPERTY_IMAGE_TYPE))
			return imageTypeD.getEnumValue(component.getImageType());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		StandardMapComponent component = getMapComponent();

		StandardItemData markerdata = (StandardItemData) component.getMarkerData();
		if (id.equals(StandardItemData.PROPERTY_ITEMS)) {
			if (value instanceof MarkersDTO) {
				markerdata = safeGetMarkerData(component, markerdata);
				List<Item> markers = markerdata.getItems();
				if (!markers.isEmpty()) {
					Item[] marray = markers.toArray(new Item[markers.size()]);
					for (Item m : marray)
						markerdata.removeItem(m);
				}
				MarkersDTO mdto = (MarkersDTO) value;
				if (mdto.getMarkers() != null)
					for (Item m : mdto.getMarkers())
						markerdata.addItem(m);
			}
		} else if (id.equals(StandardItemData.PROPERTY_DATASET)){
			markerdata = safeGetMarkerData(component, markerdata);
			if(value instanceof JRElementDataset) {
				markerdata.setDataset((JRElementDataset)value);
			}
			else {
				markerdata.setDataset(null);
			}
		} else if (id.equals(JRDesignElementDataset.PROPERTY_DATASET_RUN)) {
			MDatasetRun mdr = (MDatasetRun) value;
			JRDesignDatasetRun dr = (JRDesignDatasetRun) mdr.getValue();
			markerdata = safeGetMarkerData(component, markerdata);
			if(markerdata.getDataset()==null){
				markerdata.setDataset(new JRDesignElementDataset());
			}
			if (dr.getDatasetName() != null) {
				((JRDesignElementDataset) markerdata.getDataset()).setDatasetRun(dr);
			}
			else {
				((JRDesignElementDataset) markerdata.getDataset()).setDatasetRun(null);				
			}
		} else if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_TIME))
			component.setEvaluationTime((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1, false));
		else if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_GROUP))
			component.setEvaluationGroup((String) value);
		else if (id.equals(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION)) {
			component.setLongitudeExpression(ExprUtil.setValues(component.getLongitudeExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION)) {
			component.setLatitudeExpression(ExprUtil.setValues(component.getLatitudeExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION)) {
			component.setLanguageExpression(ExprUtil.setValues(component.getLanguageExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION)) {
			component.setZoomExpression(ExprUtil.setValues(component.getZoomExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_MAP_TYPE))
			component.setMapType((MapTypeEnum) mapTypeD.getEnumValue(value));
		else if (id.equals(StandardMapComponent.PROPERTY_MAP_SCALE))
			component.setMapScale((MapScaleEnum) mapScaleD.getEnumValue(value));
		else if (id.equals(StandardMapComponent.PROPERTY_IMAGE_TYPE))
			component.setImageType((MapImageTypeEnum) imageTypeD.getEnumValue(value));
		else
			super.setPropertyValue(id, value);
	}

	private StandardItemData safeGetMarkerData(StandardMapComponent component, StandardItemData markerdata) {
		if (markerdata == null) {
			markerdata = new StandardItemData();
			component.setMarkerData(markerdata);
			listenMap();
		}
		return markerdata;
	}

	private StandardMapComponent getMapComponent() {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		if (jrElement == null)
			return null;
		return (StandardMapComponent) jrElement.getComponent();
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	private RComboBoxPropertyDescriptor evaluationGroupNameD;
	private static JSSEnumPropertyDescriptor mapTypeD;
	private static JSSEnumPropertyDescriptor imageTypeD;
	private static JSSEnumPropertyDescriptor mapScaleD;

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport().removePropertyChangeListener(this);
			unlistenMap();
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport().addPropertyChangeListener(this);
			listenMap();
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
		exp1.setText(MapDesignConverter.DEFAULT_LATITUDE.toString() + "f"); //$NON-NLS-1$
		JRDesignExpression exp2 = new JRDesignExpression();
		exp2.setText(MapDesignConverter.DEFAULT_LONGITUDE.toString() + "f"); //$NON-NLS-1$
		component.setLatitudeExpression(exp1);
		component.setLongitudeExpression(exp2);
		JRDesignExpression exp3 = new JRDesignExpression();
		exp3.setText(String.valueOf(MapComponent.DEFAULT_ZOOM));
		component.setZoomExpression(exp3);
		designMap.setComponent(component);
		designMap.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "c", //$NON-NLS-1$ //$NON-NLS-2$
				"map")); //$NON-NLS-1$
		return designMap;
	}

}
