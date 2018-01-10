/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.renderers.Renderable;
import net.sf.jasperreports.renderers.RenderersCache;
import net.sf.jasperreports.renderers.ResourceRenderer;

/**
 * {@link RenderersCache} used inside JSS, when a renderable is requested is looks first in the 
 * JSS cache map, and try to resolve it trough that. If it is not possible use the superclass method
 * to resolve it
 * 
 * @author Orlandin Marco
 *
 */
public class JSSRenderersCache extends RenderersCache {

	/**
	 * The context of the current report
	 */
	private JasperReportsContext context;
	
	public JSSRenderersCache(JasperReportsContext jasperReportsContext) {
		super(jasperReportsContext);
		this.context = jasperReportsContext;
	}
	
	/**
	 * Load a renderable using the JSS {@link LazyImageConverter}
	 */
	public Renderable getLoadedRenderer(ResourceRenderer resourceRenderer) throws JRException
	{
		Renderable renderable = LazyImageConverter.getInstance().getLazyRenderable(context, resourceRenderer.getResourceLocation());
		if (renderable != null){
			return renderable;
		}
		return super.getLoadedRenderer(resourceRenderer);
	}

}
