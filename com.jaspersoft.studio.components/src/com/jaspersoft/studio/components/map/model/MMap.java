/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.MapNodeIconDescriptor;
import com.jaspersoft.studio.components.map.figure.MapDesignConverter;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.property.MarkersPropertyDescriptor;
import com.jaspersoft.studio.components.map.property.PathPropertyDescriptor;
import com.jaspersoft.studio.components.map.property.StylePropertyDescriptor;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.components.map.type.MapImageTypeEnum;
import net.sf.jasperreports.components.map.type.MapScaleEnum;
import net.sf.jasperreports.components.map.type.MapTypeEnum;
import net.sf.jasperreports.eclipse.util.BasicMapInfoData;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;

/**
 * 
 * @author sanda zaharia
 * 
 */
public class MMap extends MGraphicElement implements IDatasetContainer {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private IPropertyDescriptor[] descriptors;

	private NamedEnumPropertyDescriptor<OnErrorTypeEnum> onErrorTypeD;

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

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		NamedEnumPropertyDescriptor<EvaluationTimeEnum> evaluationTimeD = new NamedEnumPropertyDescriptor<EvaluationTimeEnum>(
				StandardMapComponent.PROPERTY_EVALUATION_TIME, Messages.MMap_evaluation_time, EvaluationTimeEnum.NOW,
				NullEnum.NOTNULL);
		evaluationTimeD.setDescription(Messages.MMap_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(StandardMapComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MMap_evaluation_group, new String[] { "" }); //$NON-NLS-1$ //$NON-NLS-2$
		evaluationGroupNameD.setDescription(Messages.MMap_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		onErrorTypeD = new NamedEnumPropertyDescriptor<OnErrorTypeEnum>(StandardMapComponent.PROPERTY_ON_ERROR_TYPE,
				Messages.MMap_OnErrorType, OnErrorTypeEnum.BLANK, NullEnum.NULL);
		onErrorTypeD.setDescription(Messages.MMap_OnErrorTypeDescription);
		desc.add(onErrorTypeD);

		MarkersPropertyDescriptor markerD = new MarkersPropertyDescriptor(
				StandardMapComponent.PROPERTY_MARKER_DATA_LIST, Messages.MMap_markersDescription, this);
		markerD.setDescription(Messages.MMap_markersDescription);
		markerD.setCategory(Messages.MMap_common_map_properties); // $NON-NLS-1$
		desc.add(markerD);

		PathPropertyDescriptor mapPathsD = new PathPropertyDescriptor(StandardMapComponent.PROPERTY_PATH_DATA_LIST,
				Messages.MMap_MapPaths, this);
		mapPathsD.setDescription(Messages.MMap_MapPaths);
		mapPathsD.setCategory(Messages.MMap_common_map_properties); // $NON-NLS-1$
		desc.add(mapPathsD);

		StylePropertyDescriptor mapPathStylesD = new StylePropertyDescriptor(
				StandardMapComponent.PROPERTY_PATH_STYLE_LIST, Messages.MMap_MapStyles, this);
		mapPathStylesD.setDescription(Messages.MMap_MapStyles);
		mapPathStylesD.setCategory(Messages.MMap_common_map_properties); // $NON-NLS-1$
		desc.add(mapPathStylesD);

		JRExpressionPropertyDescriptor latitudeExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION, Messages.MMap_latitude);
		latitudeExprD.setDescription(Messages.MMap_latitude_description);
		desc.add(latitudeExprD);
		latitudeExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#latitudeExpression")); //$NON-NLS-1$

		JRExpressionPropertyDescriptor longitudeExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION, Messages.MMap_longitude);
		longitudeExprD.setDescription(Messages.MMap_longitude_description);
		desc.add(longitudeExprD);
		longitudeExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#longitudeExpression")); //$NON-NLS-1$

		JRExpressionPropertyDescriptor addressExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, Messages.MMap_0);
		addressExprD.setDescription(Messages.MMap_1);
		desc.add(addressExprD);
		addressExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#addressExpression")); //$NON-NLS-1$

		JRExpressionPropertyDescriptor zoomExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, Messages.MMap_zoom);
		zoomExprD.setDescription(Messages.MMap_zoom_description);
		desc.add(zoomExprD);
		zoomExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#zoomExpression")); //$NON-NLS-1$

		JRExpressionPropertyDescriptor langExprD = new JRExpressionPropertyDescriptor(
				StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION, Messages.MMap_languageExpressionTitle);
		langExprD.setDescription(Messages.MMap_languageExpressionDescription);
		desc.add(langExprD);
		langExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#languageExpression")); //$NON-NLS-1$

		mapTypeD = getMapTypeD();
		desc.add(mapTypeD);

		mapScaleD = new NamedEnumPropertyDescriptor<MapScaleEnum>(StandardMapComponent.PROPERTY_MAP_SCALE,
				Messages.MMap_mapScaleTitle, MapScaleEnum.ONE, NullEnum.NOTNULL);
		mapScaleD.setDescription(Messages.MMap_mapScaleDescription);
		desc.add(mapScaleD);

		imageTypeD = new NamedEnumPropertyDescriptor<MapImageTypeEnum>(StandardMapComponent.PROPERTY_IMAGE_TYPE,
				Messages.MMap_imageTypeTitle, MapImageTypeEnum.GIF, NullEnum.NOTNULL);
		imageTypeD.setDescription(Messages.MMap_imageTypeDescription);
		desc.add(imageTypeD);

		NTextPropertyDescriptor mapKeyD = new NTextPropertyDescriptor(MapComponent.PROPERTY_KEY,
				Messages.MMap_ApiKeyText);
		mapKeyD.setDescription(Messages.MMap_ApiKeyDescription);
		desc.add(mapKeyD);

		NTextPropertyDescriptor mapClientIdD = new NTextPropertyDescriptor(MapComponent.PROPERTY_CLIENT_ID,
				Messages.MMap_ClientIdText);
		mapClientIdD.setDescription(Messages.MMap_ClientIdDescription);
		desc.add(mapClientIdD);

		NTextPropertyDescriptor mapClientSignatureD = new NTextPropertyDescriptor(MapComponent.PROPERTY_SIGNATURE,
				Messages.MMap_SignatureText);
		mapClientSignatureD.setDescription(Messages.MMap_SignatureDescription);
		desc.add(mapClientSignatureD);

		NTextPropertyDescriptor mapVersionD = new NTextPropertyDescriptor(MapComponent.PROPERTY_VERSION,
				Messages.MMap_VersionText);
		mapVersionD.setDescription(Messages.MMap_VersionDescription);
		desc.add(mapVersionD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/components.schema.reference.html#"); //$NON-NLS-1$

		langExprD.setCategory(Messages.MMap_common_map_properties);
		mapTypeD.setCategory(Messages.MMap_common_map_properties);
		mapScaleD.setCategory(Messages.MMap_common_map_properties);
		imageTypeD.setCategory(Messages.MMap_common_map_properties);
		onErrorTypeD.setCategory(Messages.MMap_common_map_properties);
		evaluationTimeD.setCategory(Messages.MMap_common_map_properties);
		evaluationGroupNameD.setCategory(Messages.MMap_common_map_properties);
		latitudeExprD.setCategory(Messages.MMap_common_map_properties);
		longitudeExprD.setCategory(Messages.MMap_common_map_properties);
		addressExprD.setCategory(Messages.MMap_common_map_properties);
		zoomExprD.setCategory(Messages.MMap_common_map_properties);

		mapKeyD.setCategory(Messages.MMap_Category_Authentication);
		mapClientIdD.setCategory(Messages.MMap_Category_Authentication);
		mapClientSignatureD.setCategory(Messages.MMap_Category_Authentication);
		mapVersionD.setCategory(Messages.MMap_Category_Authentication);

		mapPathStylesD.setCategory(Messages.MMap_PathsStylesCategory);
		mapPathsD.setCategory(Messages.MMap_PathsStylesCategoryDesc);
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		int roadMapValue = NamedEnumPropertyDescriptor.getIntValue(MapTypeEnum.ROADMAP, NullEnum.NOTNULL,
				MapTypeEnum.ROADMAP);
		defaultsMap.put(StandardMapComponent.PROPERTY_MAP_TYPE, new DefaultValue(roadMapValue, false));

		int mapTypeValue = NamedEnumPropertyDescriptor.getIntValue(MapScaleEnum.ONE, NullEnum.NOTNULL,
				MapScaleEnum.ONE);
		defaultsMap.put(StandardMapComponent.PROPERTY_MAP_TYPE, new DefaultValue(mapTypeValue, false));

		int imageTypeValue = NamedEnumPropertyDescriptor.getIntValue(MapImageTypeEnum.PNG, NullEnum.NOTNULL,
				MapImageTypeEnum.PNG);
		defaultsMap.put(StandardMapComponent.PROPERTY_IMAGE_TYPE, new DefaultValue(imageTypeValue, false));

		int onErrorValue = NamedEnumPropertyDescriptor.getIntValue(OnErrorTypeEnum.ERROR, NullEnum.NULL,
				OnErrorTypeEnum.ERROR);
		defaultsMap.put(StandardMapComponent.PROPERTY_ON_ERROR_TYPE, new DefaultValue(onErrorValue, true));

		defaultsMap.put(StandardMapComponent.PROPERTY_EVALUATION_TIME, new DefaultValue(EvaluationTimeEnum.NOW, false));
		defaultsMap.put(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION,
				new DefaultValue(MapComponent.DEFAULT_ZOOM, false));

		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		StandardMapComponent component = getMapComponent();
		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_TIME))
			return component.getEvaluationTime();
		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_GROUP))
			return component.getEvaluationGroup();
		if (id.equals(StandardMapComponent.PROPERTY_ON_ERROR_TYPE))
			return onErrorTypeD.getIntValue(component.getOnErrorType());

		if (id.equals(StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION))
			return ExprUtil.getExpression(component.getLanguageExpression());
		if (id.equals(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION))
			return ExprUtil.getExpression(component.getLongitudeExpression());
		if (id.equals(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION))
			return ExprUtil.getExpression(component.getLatitudeExpression());
		if (id.equals(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION))
			return ExprUtil.getExpression(component.getAddressExpression());
		if (id.equals(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION))
			return ExprUtil.getExpression(component.getZoomExpression());

		if (id.equals(StandardMapComponent.PROPERTY_MAP_TYPE))
			return mapTypeD.getIntValue(component.getMapType());
		if (id.equals(StandardMapComponent.PROPERTY_MAP_SCALE))
			return mapScaleD.getIntValue(component.getMapScale());
		if (id.equals(StandardMapComponent.PROPERTY_IMAGE_TYPE))
			return imageTypeD.getIntValue(component.getImageType());

		// map authentication info
		if (id.equals(MapComponent.PROPERTY_KEY)) {
			return getJasperDesign().getProperty(MapComponent.PROPERTY_KEY);
		} else if (id.equals(MapComponent.PROPERTY_CLIENT_ID)) {
			return getJasperDesign().getProperty(MapComponent.PROPERTY_CLIENT_ID);
		} else if (id.equals(MapComponent.PROPERTY_SIGNATURE)) {
			return getJasperDesign().getProperty(MapComponent.PROPERTY_SIGNATURE);
		} else if (id.equals(MapComponent.PROPERTY_VERSION)) {
			return getJasperDesign().getProperty(MapComponent.PROPERTY_VERSION);
		}

		if (id.equals(StandardMapComponent.PROPERTY_MARKER_DATA_LIST)) {
			List<ItemData> ids = component.getMarkerDataList();
			if (ids == null)
				return new ArrayList<ItemData>();
			return new ArrayList<ItemData>(ids);
		}
		if (id.equals(StandardMapComponent.PROPERTY_PATH_DATA_LIST)) {
			List<ItemData> ids = component.getPathDataList();
			if (ids == null)
				return new ArrayList<ItemData>();
			return new ArrayList<ItemData>(ids);
		}

		if (id.equals(StandardMapComponent.PROPERTY_PATH_STYLE_LIST)) {
			List<ItemData> ids = component.getPathStyleList();
			if (ids == null)
				return new ArrayList<ItemData>();
			return new ArrayList<ItemData>(ids);
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		StandardMapComponent component = getMapComponent();

		if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_TIME)) {
			EvaluationTimeEnum evalTime = EnumHelper.getEnumByObjectValue(EvaluationTimeEnum.values(), value);
			component.setEvaluationTime(evalTime);
			if (evalTime != null && !evalTime.equals(EvaluationTimeEnum.GROUP)) {
				component.setEvaluationGroup(null);
			}
		} else if (id.equals(StandardMapComponent.PROPERTY_EVALUATION_GROUP)) {
			component.setEvaluationGroup(ModelUtils.getGroupNameForProperty(value));
		} else if (id.equals(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION)) {
			component.setLongitudeExpression(ExprUtil.setValues(component.getLongitudeExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION)) {
			component.setLatitudeExpression(ExprUtil.setValues(component.getLatitudeExpression(), value, null));
		} else if (id.equals(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION)) {
			component.setAddressExpression(ExprUtil.setValues(component.getAddressExpression(), value, null));
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
		else if (id.equals(StandardMapComponent.PROPERTY_ON_ERROR_TYPE)) {
			component.setOnErrorType(onErrorTypeD.getEnumValue(value));
		} else if (id.equals(MapComponent.PROPERTY_KEY)) {
			if (value instanceof String && !Misc.isNullOrEmptyString(value)) {
				getJasperDesign().setProperty(MapComponent.PROPERTY_KEY, (String) value);
			} else {
				getJasperDesign().removeProperty(MapComponent.PROPERTY_KEY);
			}
		} else if (id.equals(MapComponent.PROPERTY_CLIENT_ID)) {
			if (value instanceof String && !Misc.isNullOrEmptyString(value)) {
				getJasperDesign().setProperty(MapComponent.PROPERTY_CLIENT_ID, (String) value);
			} else {
				getJasperDesign().removeProperty(MapComponent.PROPERTY_CLIENT_ID);
			}
		} else if (id.equals(MapComponent.PROPERTY_SIGNATURE)) {
			if (value instanceof String && !Misc.isNullOrEmptyString(value)) {
				getJasperDesign().setProperty(MapComponent.PROPERTY_SIGNATURE, (String) value);
			} else {
				getJasperDesign().removeProperty(MapComponent.PROPERTY_SIGNATURE);
			}
		} else if (id.equals(MapComponent.PROPERTY_VERSION)) {
			if (value instanceof String && !Misc.isNullOrEmptyString(value)) {
				getJasperDesign().setProperty(MapComponent.PROPERTY_VERSION, (String) value);
			} else {
				getJasperDesign().removeProperty(MapComponent.PROPERTY_VERSION);
			}
		} else if (id.equals(StandardMapComponent.PROPERTY_MARKER_DATA_LIST)) {
			if (value instanceof List<?>) {
				@SuppressWarnings("unchecked")
				List<ItemData> itemDatas = new ArrayList<ItemData>((List<ItemData>) value);
				Object[] existing = component.getMarkerDataList().toArray();
				for (Object p : existing)
					component.removeMarkerData((ItemData) p);
				for (ItemData n : itemDatas)
					component.addMarkerData(n);
			}
		} else if (id.equals(StandardMapComponent.PROPERTY_PATH_DATA_LIST)) {
			if (value instanceof List<?>) {
				@SuppressWarnings("unchecked")
				List<ItemData> itemDatas = (List<ItemData>) value;
				Object[] existing = component.getPathDataList().toArray();
				for (Object p : existing)
					component.removePathData((ItemData) p);
				for (ItemData n : itemDatas)
					component.addPathData(n);
			}
		} else if (id.equals(StandardMapComponent.PROPERTY_PATH_STYLE_LIST)) {
			if (value instanceof List<?>) {
				@SuppressWarnings("unchecked")
				List<ItemData> itemDatas = (List<ItemData>) value;
				Object[] existing = component.getPathStyleList().toArray();
				for (Object p : existing)
					component.removePathStyle((ItemData) p);
				for (ItemData n : itemDatas)
					component.addPathStyle(n);
			}
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	protected List<ValidationError> doValidation() {
		List<ValidationError> errors = super.doValidation();
		if (errors == null)
			errors = new ArrayList<ValidationError>();

		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardMapComponent tm = (StandardMapComponent) jrElement.getComponent();

		JRExpression lonExp = tm.getLongitudeExpression();
		JRExpression latExp = tm.getLatitudeExpression();
		JRExpression adrExp = tm.getAddressExpression();
		if ((lonExp == null || latExp == null) && adrExp == null) {
			List<String> ids = new ArrayList<String>();
			if (lonExp == null)
				ids.add(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION);
			if (latExp == null)
				ids.add(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION);
			ids.add(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION);
			errors.add(new ValidationError(ids, Messages.MarkersDescriptor_76, true));
		}
		// this validation is too slow :(
		// if (lonExp != null) {
		// Object obj = ExpressionUtil.cachedExpressionEvaluation(lonExp,
		// getJasperConfiguration());
		// if (obj != null && obj instanceof Number) {
		// double v = ((Number) obj).doubleValue();
		// if (v < -122.4167)
		// errors.add(new
		// ValidationError(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
		// "Min value -122.4167", true));
		// if (v > 180)
		// errors.add(new
		// ValidationError(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
		// "Max value 180",
		// true));
		// }
		// }
		// if (latExp != null) {
		// Object obj = ExpressionUtil.cachedExpressionEvaluation(latExp,
		// getJasperConfiguration());
		// if (obj != null && obj instanceof Number) {
		// double v = ((Number) obj).doubleValue();
		// if (v < -85)
		// errors.add(new
		// ValidationError(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
		// "Min value -85",
		// true));
		// if (v > 85)
		// errors.add(new
		// ValidationError(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
		// "Max value 85",
		// true));
		// }
		// }
		return errors;
	}

	public StandardMapComponent getMapComponent() {
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
	private static NamedEnumPropertyDescriptor<MapTypeEnum> mapTypeD;
	private static NamedEnumPropertyDescriptor<MapImageTypeEnum> imageTypeD;
	private static NamedEnumPropertyDescriptor<MapScaleEnum> mapScaleD;

	/**
	 * @return the mapTypeD
	 */
	public NamedEnumPropertyDescriptor<MapTypeEnum> getMapTypeD() {
		if (mapTypeD == null) {
			mapTypeD = new NamedEnumPropertyDescriptor<MapTypeEnum>(StandardMapComponent.PROPERTY_MAP_TYPE,
					Messages.MMap_mapTypeTitle, MapTypeEnum.HYBRID, NullEnum.NOTNULL);
		}
		return mapTypeD;
	}

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport().removePropertyChangeListener(this);
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport().addPropertyChangeListener(this);
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
		JRDesignComponentElement designMap = new JRDesignComponentElement(jasperDesign);
		StandardMapComponent component = new StandardMapComponent();
		JRDesignExpression exp1 = new JRDesignExpression();
		exp1.setText(MapDesignConverter.DEFAULT_LATITUDE.toString() + "f"); //$NON-NLS-1$
		JRDesignExpression exp2 = new JRDesignExpression();
		exp2.setText(MapDesignConverter.DEFAULT_LONGITUDE.toString() + "f"); //$NON-NLS-1$
		component.setLatitudeExpression(exp1);
		component.setLongitudeExpression(exp2);
		JRDesignExpression exp3 = new JRDesignExpression();
		exp3.setText("8");
		component.setZoomExpression(exp3);
		designMap.setComponent(component);
		designMap.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "c", //$NON-NLS-1$ //$NON-NLS-2$
				"map")); //$NON-NLS-1$

		DefaultManager.INSTANCE.applyDefault(this.getClass(), designMap);

		return designMap;
	}

	@Override
	public int getDefaultHeight() {
		return 150;
	}

	@Override
	public int getDefaultWidth() {
		return 400;
	}

	@Override
	public List<MDatasetRun> getDatasetRunList() {
		Set<JRDatasetRun> dsr = new HashSet<JRDatasetRun>();
		for (ItemData id : getMapComponent().getMarkerDataList()) {
			if (id.getDataset() != null && id.getDataset().getDatasetRun() != null)
				dsr.add(id.getDataset().getDatasetRun());
		}
		for (ItemData id : getMapComponent().getPathDataList()) {
			if (id.getDataset() != null && id.getDataset().getDatasetRun() != null)
				dsr.add(id.getDataset().getDatasetRun());
		}
		for (ItemData id : getMapComponent().getPathStyleList()) {
			if (id.getDataset() != null && id.getDataset().getDatasetRun() != null)
				dsr.add(id.getDataset().getDatasetRun());
		}
		JasperDesign jd = getJasperDesign();
		List<MDatasetRun> datasetList = new ArrayList<MDatasetRun>();
		for (JRDatasetRun dr : dsr)
			datasetList.add(new MDatasetRun(dr, jd));
		return datasetList;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		StandardMapComponent jrSourceMap = getMapComponent();
		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		StandardMapComponent jrTargetMap = (StandardMapComponent) jrTargetElement.getComponent();

		jrTargetMap.setMapType(jrSourceMap.getMapType());
		jrTargetMap.setMapScale(jrSourceMap.getMapScale());
		jrTargetMap.setImageType(jrSourceMap.getImageType());
		jrTargetMap.setOnErrorType(jrSourceMap.getOnErrorType());

	}

	/*
	 * Gets the basic information: map center, zoom and type.
	 */
	public BasicMapInfoData getBasicMapInformation() {
		BasicMapInfoData info = new BasicMapInfoData();
		JRDesignDataset dataset = ModelUtils.getDataset(this);
		JasperDesign jd = getJasperDesign();
		if (dataset == null)
			dataset = (JRDesignDataset) jd.getMainDataset();
		ExpressionInterpreter expIntr = new ExpressionInterpreter(dataset, jd, getJasperConfiguration());
		// Center
		JRDesignExpression latitudeExpr = (JRDesignExpression) getPropertyValue(
				StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION);
		JRDesignExpression longitudeExpr = (JRDesignExpression) getPropertyValue(
				StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION);
		if (latitudeExpr != null && longitudeExpr != null) {
			Object latObj = expIntr.interpretExpression(Misc.nvl(latitudeExpr.getText()));
			Object lngObj = expIntr.interpretExpression(Misc.nvl(longitudeExpr.getText()));
			if (latObj instanceof Number && lngObj instanceof Number) {
				info.setLatitude(((Number) latObj).doubleValue());
				info.setLongitude(((Number) lngObj).doubleValue());
			}
		}
		// Address
		JRDesignExpression adrExpr = (JRDesignExpression) getPropertyValue(
				StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION);
		if (adrExpr != null) {
			Object adrObj = expIntr.interpretExpression(adrExpr.getText());
			if (adrObj instanceof String)
				info.setAddress((String) adrObj);
		}
		// Zoom
		JRDesignExpression zoomExpr = (JRDesignExpression) getPropertyValue(
				StandardMapComponent.PROPERTY_ZOOM_EXPRESSION);
		if (zoomExpr != null) {
			Object zoomObj = expIntr.interpretExpression(zoomExpr.getText());
			if (zoomObj instanceof Number)
				info.setZoom(((Number) zoomObj).intValue());
		}
		// Map Type
		Integer type = (Integer) getPropertyValue(StandardMapComponent.PROPERTY_MAP_TYPE);
		if (type != null) {
			MapTypeEnum typeVal = getMapTypeD().getEnumValue(type);
			info.setMapType(typeVal);
		}
		return info;
	}
}
