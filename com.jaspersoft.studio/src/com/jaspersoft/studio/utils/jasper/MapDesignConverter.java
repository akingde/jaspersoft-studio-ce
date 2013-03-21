/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.utils.jasper;

import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.type.MapImageTypeEnum;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.Renderable;
import net.sf.jasperreports.engine.RenderableUtil;
import net.sf.jasperreports.engine.base.JRBasePrintImage;
import net.sf.jasperreports.engine.component.ComponentDesignConverter;
import net.sf.jasperreports.engine.convert.ElementIconConverter;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRImageLoader;

import com.jaspersoft.studio.utils.ExpressionUtil;

/**
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: MapDesignConverter.java 5877 2013-01-07 19:51:14Z teodord $
 */
public class MapDesignConverter extends ElementIconConverter implements ComponentDesignConverter {

	private static final Float DEFAULT_LONGITUDE = new Float(12.482944);
	private static final Float DEFAULT_LATITUDE = new Float(41.894291);
	/**
	 *
	 */
	private final static MapDesignConverter INSTANCE = new MapDesignConverter();

	/**
	 *
	 */
	private MapDesignConverter() {
		super(JRImageLoader.SUBREPORT_IMAGE_RESOURCE);
	}

	/**
	 *
	 */
	public static MapDesignConverter getInstance() {
		return INSTANCE;
	}

	private Renderable cacheRenderer;
	private long lasttime = 0;

	/**
	 *
	 */
	public JRPrintElement convert(ReportConverter reportConverter, JRComponentElement element) {
		try {
			JRBasePrintImage printImage = new JRBasePrintImage(element.getDefaultStyleProvider());

			printImage.setUUID(element.getUUID());
			printImage.setX(element.getX());
			printImage.setY(element.getY());
			printImage.setWidth(element.getWidth());
			printImage.setHeight(element.getHeight());
			printImage.setStyle(element.getStyle());
			printImage.setMode(element.getModeValue());
			printImage.setBackcolor(element.getBackcolor());
			printImage.setForecolor(element.getForecolor());
			printImage.setLazy(false);

			// FIXMEMAP there are no scale image, alignment and onError attributes
			// defined for the map element
			printImage.setScaleImage(ScaleImageEnum.CLIP);
			printImage.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
			printImage.setVerticalAlignment(VerticalAlignEnum.TOP);
			if (cacheRenderer == null && System.currentTimeMillis() - lasttime > 3000) {
				MapComponent map = (MapComponent) element.getComponent();
				JasperReportsConfiguration jConfig = (JasperReportsConfiguration) reportConverter.getJasperReportsContext();
				JasperDesign jd = jConfig.getJasperDesign();
				JRDataset jrd = jd.getMainDataset();
				String dname = map.getMarkerData().getDataset().getDatasetRun().getDatasetName();
				if (dname != null)
					jrd = jd.getDatasetMap().get(dname);
				Float latitude = evaluate(map.getLatitudeExpression(), jrd, jConfig, DEFAULT_LATITUDE);
				Float longitude = evaluate(map.getLongitudeExpression(), jrd, jConfig, DEFAULT_LONGITUDE);

				Integer zoom = evaluate(map.getZoomExpression(), jrd, jConfig, MapComponent.DEFAULT_ZOOM);

				String mapType = map.getMapType().getName();
				String mapScale = map.getMapScale().getName();
				String mapFormat = MapImageTypeEnum.PNG.getName();
				String language = evaluate(map.getLanguageExpression(), jrd, jConfig, "");
				String markers = "";

				String imageLocation = "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude
						+ "&size=" + element.getWidth() + "x" + element.getHeight() + "&zoom=" + zoom
						+ (mapType == null ? "" : "&maptype=" + mapType) + (mapFormat == null ? "" : "&format=" + mapFormat)
						+ (mapScale == null ? "" : "&scale=" + mapScale) + markers + "&sensor=false"
						+ (language == null ? "" : "&language=" + language);

				cacheRenderer = RenderableUtil.getInstance(jConfig).getRenderable(imageLocation, OnErrorTypeEnum.ERROR, false);
				cacheRenderer.getImageData(jConfig);
			}
			printImage.setRenderable(cacheRenderer);

			lasttime = System.currentTimeMillis();
			return printImage;
		} catch (JRException e) {
			return convert(reportConverter, (JRElement) element);
		}
	}

	private <T> T evaluate(JRExpression expr, JRDataset jrd, JasperReportsConfiguration jConfig, T def) {
		if (expr != null) {
			Object l = ExpressionUtil.eval(expr, jrd, jConfig);
			if (l != null)
				return (T) l;
		}
		return def;
	}
}
