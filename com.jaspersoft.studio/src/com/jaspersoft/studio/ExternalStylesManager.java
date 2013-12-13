/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRExpressionUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;

import com.jaspersoft.studio.model.style.StyleTemplateFactory;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Class that caches the external styles to improove the performance when resolving their names
 * 
 * @author Orlandin Marco
 *
 */
public class ExternalStylesManager {

	private static HashMap<String, List<JRStyle>> externalStylesCache = new HashMap<String, List<JRStyle>>();
	
	private static HashMap<String, ExpressionInterpreter> interpreterMaps = new HashMap<String, ExpressionInterpreter>();
	
	static{
		IResourceChangeListener resourceChangeListenr = new IResourceChangeListener(){
			@Override
			public void resourceChanged(IResourceChangeEvent event) {
				removeUpdateElements(event.getDelta().getAffectedChildren());
			}	
		};
		
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListenr,
				IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.POST_CHANGE);
	}
	
	private static void removeUpdateElements(IResourceDelta[] editedResources){
		for(IResourceDelta resource : editedResources){
			if (resource.getAffectedChildren().length>0) removeUpdateElements(resource.getAffectedChildren());
			IPath rawLocation = resource.getResource().getRawLocation();
			if (rawLocation != null){
				String key = rawLocation.toOSString();
				externalStylesCache.remove(key);
			}
		}
	}
	
	public static List<JRStyle> getStyles(JRExpression styleExpression, IFile project, JasperReportsConfiguration jConfig){	
		String evaluatedExpression = JRExpressionUtil.getSimpleExpressionText(styleExpression);
		if (evaluatedExpression == null){
			//Unable to interpret the expression, lets try with a more advanced (and slow, so its cached) interpreter
			String interpreterKey = project.getLocation().toPortableString();
			ExpressionInterpreter interpreter = interpreterMaps.get(interpreterKey);
			if (interpreter == null){
				JasperDesign jd = jConfig.getJasperDesign();
				JRDesignDataset jrd = jd.getMainDesignDataset();
				if (styleExpression != null && jrd != null && jd != null){
					interpreter = new ExpressionInterpreter((JRDesignDataset) jrd, jd, jConfig);
					interpreterMaps.put(interpreterKey, interpreter);
				}
			}
			if (interpreter != null){
				Object expressionValue = interpreter.interpretExpression(styleExpression.getText());
				if (expressionValue != null) evaluatedExpression = expressionValue.toString();
			}
		}
		
		if (evaluatedExpression != null){
			String key = StyleTemplateFactory.getFile(evaluatedExpression, project).getAbsolutePath();
			List<JRStyle> cachedStyles = externalStylesCache.get(key);
			if (cachedStyles == null){
				cachedStyles = new ArrayList<JRStyle>();
				StyleTemplateFactory.getStylesReference(project, evaluatedExpression, cachedStyles, new HashSet<File>());
				externalStylesCache.put(key, cachedStyles);
			}
			return cachedStyles;
		} else return new ArrayList<JRStyle>();
	}
	
}
