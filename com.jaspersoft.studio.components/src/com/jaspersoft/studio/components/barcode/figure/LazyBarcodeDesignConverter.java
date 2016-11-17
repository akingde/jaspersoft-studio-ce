/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.figure;

import com.jaspersoft.studio.jasper.AComponentDesignConverter;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.barcode4j.BarcodeDesignEvaluator;
import net.sf.jasperreports.components.barcode4j.QRCodeComponent;
import net.sf.jasperreports.eclipse.util.KeyValue;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.util.JRImageLoader;
import net.sf.jasperreports.renderers.Renderable;

/**
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: MapDesignConverter.java 5877 2013-01-07 19:51:14Z teodord $
 */
public class LazyBarcodeDesignConverter extends AComponentDesignConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.jasper.AComponentDesignConverter#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "QRCode";
	}

	/**
	 *
	 */
	public LazyBarcodeDesignConverter() {
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
		return OnErrorTypeEnum.BLANK;
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
		QRCodeComponent map = (QRCodeComponent) element.getComponent();
		String ekey = element.getUUID() + "" + element.getWidth()
				+ element.getHeight();
		if (map.getCodeExpression() != null)
			ekey += map.getCodeExpression().getText();
		if (map.getErrorCorrectionLevel() != null)
			ekey += map.getErrorCorrectionLevel();
		if (map.getMargin() != null)
			ekey += map.getMargin();
		return ekey;
	}

	@Override
	protected Renderable doRenderable(ReportConverter reportConverter,
			final JRComponentElement element, final Component cmp,
			final String expr, final JasperReportsConfiguration jrContext,
			final KeyValue<String, Long> kv) throws JRException {
		try {
			JasperReportsContext jcontext = reportConverter
					.getJasperReportsContext();
			BarcodeDesignEvaluator evaluator = new BarcodeDesignEvaluator(
					jcontext, element,
					reportConverter.getDefaultStyleProvider());
			return evaluator.evaluateImage();
		} catch (Exception e) {
			return null;
		}
	}

}
