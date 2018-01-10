/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import com.jaspersoft.studio.jasper.JSSReportConverter;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRStyleContainer;
import net.sf.jasperreports.engine.util.StyleResolver;

/**
 * A custom style resolver used in JSS to resolve correctly the properties show 
 * in the property view. The {@link StyleResolver} from JR is designed for the print
 * element that doesn't use the concept of style reference (since when a print element
 * is created from a design element the external style is merged like an internal one). 
 * But since the properties uses the design element they need a special style resolver
 * that consider also the style references when resolving the properties
 * 
 * 
 * @author Orlandin Marco
 */
public class JSSStyleResolver extends StyleResolver {
	
	/**
	 * A default instance based on the default configuration. It works but it can't resolve
	 * the external styles, so it is almost the same thing of a standard {@link StyleResolver}
	 */
	public static final JSSStyleResolver DEFAULT_INSTANCE = new JSSStyleResolver(JasperReportsConfiguration.getDefaultInstance());
	
	private JasperReportsConfiguration jConfig;
	
	public JSSStyleResolver(JasperReportsConfiguration jasperReportsContext) {
		super(jasperReportsContext);
		jConfig = jasperReportsContext;
	}

	/**
	 * Return the style of an element, first look if it has an internal style, then it 
	 * search for an external and in the end it check for default styles
	 */
	protected JRStyle getBaseStyleFromStyleContainerRef(JRStyleContainer styleContainer) {
		if (styleContainer != null)
		{
			JRStyle style = styleContainer.getStyle();
			if (style != null)
			{
				return style;
			}
			String referance = styleContainer.getStyleNameReference();
			if (referance != null){
				JSSReportConverter converter = jConfig.getReportConverter();
				if (converter != null){
					JRStyle resolvedStyle = converter.getStylesMap().get(referance);
					if (resolvedStyle != null){
						return resolvedStyle;
					}
				}
			}
			JRDefaultStyleProvider defaultStyleProvider = styleContainer.getDefaultStyleProvider();
			if (defaultStyleProvider != null)
			{
				return defaultStyleProvider.getDefaultStyle();
			}
		}
		return null;
	}

	@Override
	public JRStyle getBaseStyle(JRStyleContainer styleContainer)
	{
		if (styleContainer != null)
		{
			if (styleContainer instanceof JRStyle)
			{
				return getBaseStyle((JRStyle)styleContainer);
			}
			return getBaseStyleFromStyleContainerRef(styleContainer);
		}
		return null;
	}
}
