/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.util.JRImageLoader;
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
		//Check for the special case where the requested renderable is not a resource but a subreport image
		if (!JRImageLoader.SUBREPORT_IMAGE_RESOURCE.equals(resourceRenderer.getResourceLocation())) {
			Renderable renderable = LazyImageConverter.getInstance().getNonLazyRenderable(context, resourceRenderer.getResourceLocation());
			if (renderable != null){
				return renderable;
			}
		}
		return super.getLoadedRenderer(resourceRenderer);
	}

}
