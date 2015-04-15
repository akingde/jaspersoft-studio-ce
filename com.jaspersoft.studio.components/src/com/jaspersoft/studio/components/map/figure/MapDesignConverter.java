/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.map.figure;

import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.type.MapImageTypeEnum;
import net.sf.jasperreports.components.map.type.MapScaleEnum;
import net.sf.jasperreports.components.map.type.MapTypeEnum;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElementDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.Renderable;
import net.sf.jasperreports.engine.RenderableUtil;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.util.JRImageLoader;

import com.jaspersoft.studio.jasper.AComponentDesignConverter;
import com.jaspersoft.studio.model.util.KeyValue;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: MapDesignConverter.java 5877 2013-01-07 19:51:14Z teodord $
 */
public class MapDesignConverter extends AComponentDesignConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.jasper.AComponentDesignConverter#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return ComponentsExtensionsRegistryFactory.MAP_COMPONENT_NAME;
	}

	public static final Float DEFAULT_LONGITUDE = new Float(12.337967);
	public static final Float DEFAULT_LATITUDE = new Float(45.433967);

	/**
	 *
	 */
	public MapDesignConverter() {
		super(JRImageLoader.COMPONENT_IMAGE_RESOURCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.jasper.AComponentDesignConverter#getOnErrorType()
	 */
	@Override
	protected OnErrorTypeEnum getOnErrorType(Component cmp) {
		return ((MapComponent) cmp).getOnErrorType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.jasper.AComponentDesignConverter#getExpression(
	 * net.sf.jasperreports.engine.component.Component)
	 */
	@Override
	protected String getEKey(JRComponentElement element) {
		MapComponent map = (MapComponent) element.getComponent();
		String ekey = "" + element.getWidth() + element.getHeight();
		if (map.getLongitudeExpression() != null)
			ekey += map.getLongitudeExpression().getText();
		if (map.getLatitudeExpression() != null)
			ekey += map.getLatitudeExpression().getText();
		if (map.getLanguageExpression() != null)
			ekey += map.getLanguageExpression().getText();
		if (map.getZoomExpression() != null)
			ekey += map.getZoomExpression().getText();
		if (map.getMapType() != null)
			ekey += map.getMapType().getName();
		if (map.getMapScale() != null)
			ekey += map.getMapScale().getName();
		return ekey;
	}

	@Override
	protected Renderable doRenderable(ReportConverter reportConverter,
			final JRComponentElement element, final Component cmp,
			final String ekey, final JasperReportsConfiguration jrContext,
			final KeyValue<String, Long> kv) throws JRException {
		MapComponent map = (MapComponent) cmp;
		System.out.println("loading map");

		JasperDesign jd = jrContext.getJasperDesign();
		JRDataset jrd = jd.getMainDataset();
		JRElementDataset dataset = null;
		if (ModelUtils.getSingleMarkerData(map) != null) {
			dataset = ModelUtils.getSingleMarkerData(map).getDataset();
		}
		if (dataset != null && dataset.getDatasetRun() != null) {
			String dname = dataset.getDatasetRun().getDatasetName();
			if (dname != null)
				jrd = jd.getDatasetMap().get(dname);
		}
		Float latitude = evaluate(map.getLatitudeExpression(), jrd, jrContext,
				DEFAULT_LATITUDE);
		Float longitude = evaluate(map.getLongitudeExpression(), jrd,
				jrContext, DEFAULT_LONGITUDE);

		Integer zoom = evaluate(map.getZoomExpression(), jrd, jrContext,
				MapComponent.DEFAULT_ZOOM);

		String mapType = map.getMapType() != null ? map.getMapType().getName()
				: MapTypeEnum.ROADMAP.getName();
		String mapScale = map.getMapScale() != null ? map.getMapScale()
				.getName() : MapScaleEnum.ONE.getName();
		String mapFormat = MapImageTypeEnum.PNG.getName();
		String language = evaluate(map.getLanguageExpression(), jrd, jrContext,
				"");
		String markers = "";

		String imageLocation = "http://maps.google.com/maps/api/staticmap?center="
				+ (latitude % 90)
				+ ","
				+ (longitude % 180)
				+ "&size="
				+ element.getWidth()
				+ "x"
				+ element.getHeight()
				+ "&zoom="
				+ zoom
				+ (mapType == null ? "" : "&maptype=" + mapType)
				+ (mapFormat == null ? "" : "&format=" + mapFormat)
				+ (mapScale == null ? "" : "&scale=" + mapScale)
				+ markers
				+ "&sensor=false"
				+ (language == null ? "" : "&language=" + language);
		kv.key = ekey;
		return RenderableUtil.getInstance(jrContext).getRenderable(
				imageLocation, OnErrorTypeEnum.ERROR, false);
	}
}
