/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jasper;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.jaspersoft.studio.editor.AMultiEditor;
import com.jaspersoft.studio.utils.CacheMap;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.KeyValue;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.base.JRBasePrintImage;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentDesignConverter;
import net.sf.jasperreports.engine.convert.ElementIconConverter;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.util.JRImageLoader;
import net.sf.jasperreports.renderers.Renderable;
import net.sf.jasperreports.renderers.util.RendererUtil;

public abstract class AComponentDesignConverter extends ElementIconConverter implements ComponentDesignConverter {
	public AComponentDesignConverter(String iconLocation) {
		super(iconLocation);
	}

	public abstract String getComponentName();

	protected CacheMap<JRComponentElement, Renderable> cache = new CacheMap<JRComponentElement, Renderable>(3000000);
	protected CacheMap<JRElement, KeyValue<String, Long>> running = new CacheMap<JRElement, KeyValue<String, Long>>(
			300000);
	protected static CacheMap<KeyValue<JasperReportsContext, String>, JRComponentElement> imgCache = new CacheMap<KeyValue<JasperReportsContext, String>, JRComponentElement>(
			300000);

	public JRPrintElement convert(final ReportConverter reportConverter, final JRComponentElement element) {
		JRBasePrintImage printImage = new JRBasePrintImage(element.getDefaultStyleProvider());
		Renderable cacheRenderer = getRenderable(reportConverter, element, element.getComponent(), printImage);

		printImage.setUUID(element.getUUID());
		printImage.setX(element.getX());
		printImage.setY(element.getY());
		printImage.setWidth(element.getWidth());
		printImage.setHeight(element.getHeight());
		printImage.setStyle(element.getStyle());
		printImage.setMode(element.getModeValue());
		printImage.setBackcolor(element.getBackcolor());
		printImage.setForecolor(element.getForecolor());

		// FIXMEMAP there are no scale image, alignment and onError attributes
		// defined for the map element
		printImage.setScaleImage(ScaleImageEnum.RETAIN_SHAPE);
		printImage.setHorizontalImageAlign(HorizontalImageAlignEnum.LEFT);
		printImage.setVerticalImageAlign(VerticalImageAlignEnum.TOP);
		printImage.setRenderer(cacheRenderer);
		return printImage;
	}

	protected Renderable getRenderable(final ReportConverter reportConverter, final JRComponentElement element,
			Component cmp, final JRBasePrintImage printImage) {
		Renderable cacheRenderer = null;
		OnErrorTypeEnum onErrorType = getOnErrorType(cmp);
		try {
			cacheRenderer = cache.get(element);
			String ekey = getEKey(element);
			if (!ekey.isEmpty()) {
				KeyValue<String, Long> last = running.get(element);
				Renderable r = null;
				if (cacheRenderer == null) {
					cacheRenderer = getRenderableNoImage(reportConverter.getJasperReportsContext(), onErrorType, printImage);
					cache.put(element, cacheRenderer);
					if (last == null)
						r = doFindImage(reportConverter, element, cmp, ekey, cacheRenderer);
				}
				if (last == null || last.key == null)
					r = doFindImage(reportConverter, element, cmp, ekey, cacheRenderer);
				else if (last != null && (!last.key.equals(ekey)))
					r = doFindImage(reportConverter, element, cmp, ekey, cacheRenderer);
				if (r != null)
					cacheRenderer = r;
			} else {
				running.remove(element);
				cacheRenderer = getRenderableNoImage(reportConverter.getJasperReportsContext(), onErrorType, printImage);
				cache.put(element, cacheRenderer);
			}
		} catch (Throwable e) {
			return getRenderableNoImage(reportConverter.getJasperReportsContext(), onErrorType, printImage);
		}
		return cacheRenderer;
	}

	protected Renderable doFindImage(final ReportConverter reportConverter, final JRComponentElement element,
			final Component cmp, final String ekey, Renderable cacheRenderer) {
		final JasperReportsConfiguration jrContext = (JasperReportsConfiguration) reportConverter.getJasperReportsContext();
		final KeyValue<JasperReportsContext, String> key = new KeyValue<JasperReportsContext, String>(jrContext, ekey);
		JRComponentElement r = imgCache.get(key);
		if (r != null) {
			// cache.put(element, r);
			return cache.get(key);
		}
		imgCache.put(key, element);

		final KeyValue<String, Long> kv = new KeyValue<String, Long>(null, null);
		running.put(element, kv);
		Job job = new Job("load map") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					final Renderable r = doRenderable(reportConverter, element, cmp, ekey, jrContext, kv);
					imgCache.put(key, element);
					cache.put(element, r);
					UIUtils.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							cache.put(element, r);
							kv.value = System.currentTimeMillis();
							AMultiEditor.refresh(jrContext);
						}

					});
					Set<KeyValue<JasperReportsContext, String>> set = new HashSet<KeyValue<JasperReportsContext, String>>();
					for (KeyValue<JasperReportsContext, String> k : set)
						imgCache.get(k);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return Status.OK_STATUS;
			}

		};
		job.setSystem(true);
		job.setPriority(Job.SHORT);
		job.schedule();
		return null;
	}

	protected abstract Renderable doRenderable(ReportConverter reportConverter, final JRComponentElement element,
			final Component cmp, final String expr, final JasperReportsConfiguration jrContext,
			final KeyValue<String, Long> kv) throws JRException;

	protected abstract String getEKey(JRComponentElement element);

	protected abstract OnErrorTypeEnum getOnErrorType(Component cmp);

	protected static <T> T evaluate(JRExpression expr, JRDataset jrd, JasperReportsConfiguration jConfig, T def) {
		if (expr != null) {
			Object l = ExpressionUtil.cachedExpressionEvaluation(expr, jConfig, (JRDesignDataset) jrd);
			if (l != null)
				try {
					return (T) l;
				} catch (Exception e) {
					return def;
				}
		}
		return def;
	}

	protected static Renderable noImage;

	protected static Renderable getRenderableNoImage(JasperReportsContext jasperReportsContext, OnErrorTypeEnum onError,
			JRPrintImage printImage) {
		try {
			printImage.setScaleImage(ScaleImageEnum.CLIP);
			if (noImage == null)
				noImage = RendererUtil.getInstance(jasperReportsContext).getNonLazyRenderable(JRImageLoader.NO_IMAGE_RESOURCE, onError);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noImage;
	}
}
