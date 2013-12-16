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
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.style.StyleTemplateFactory;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Class that caches the external styles to improve the performance when resolving their names
 * 
 * @author Orlandin Marco
 *
 */
public class ExternalStylesManager {

	/**
	 * Map of the cached styles, the key is the absolute path to the template file
	 */
	private static HashMap<String, List<JRStyle>> externalStylesCache = new HashMap<String, List<JRStyle>>();
	
	/**
	 * Cache of the expression interpreter for a report, the key is the absolute path of the report
	 */
	private static HashMap<String, ExpressionInterpreter> interpreterMaps = new HashMap<String, ExpressionInterpreter>();

	/**
	 * Listener called when a file is saved
	 */
	private static IResourceChangeListener resourceChangeListenr = new IResourceChangeListener(){
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			HashSet<String> removedStyles = new HashSet<String>();
			removeStyle(event.getDelta().getAffectedChildren(), removedStyles);
			if (event.getType() == IResourceChangeEvent.PRE_CLOSE) removeReport(event.getDelta().getAffectedChildren());
			refreshStyles(removedStyles);
		}	
	};
	
	/**
	 * Listener used to know when an editor is closed and if it has an interpreter saved than it can be removed
	 * 
	 */
	private static IPartListener documentClosedListener = new IPartListener() {
		
		@Override
		public void partClosed(IWorkbenchPart part) {
			if (part instanceof JrxmlEditor){
				JrxmlEditor editor = (JrxmlEditor)part;
				String key = ((FileEditorInput)editor.getEditorInput()).getFile().getLocation().toPortableString();
				interpreterMaps.remove(key);
			}	
		}
		
		@Override
		public void partOpened(IWorkbenchPart part) {}
		
		@Override
		public void partDeactivated(IWorkbenchPart part) {}
		
		@Override
		public void partBroughtToTop(IWorkbenchPart part) {}
		
		@Override
		public void partActivated(IWorkbenchPart part) {}
	};
	
	/**
	 * Initialize the appropriate listener
	 */
	public static void initListeners(){
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListenr,
				IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.POST_CHANGE);
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(documentClosedListener);
	}
	
	/**
	 * Notify to the opened jrxml editors to refresh the styles
	 * 
	 * @param removedStyles name of the Styles that were updated in the cache
	 */
	private static void refreshStyles(HashSet<String> changedStyles){
		IEditorReference[] openEditors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for(IEditorReference editor : openEditors){
			IWorkbenchPart part = editor.getPart(false);
			if (part instanceof JrxmlEditor){
				JrxmlEditor jrxmlEditor = (JrxmlEditor)part;
				jrxmlEditor.refreshExternalStyles(changedStyles);
			}
		}
	}
	
	/**
	 * When a jrtx file is saved search if it is in the cache and in case it is update
	 * 
	 * @param editedResources resources saved
	 * @param removedStylesName Map where the name of the update jrstyle are stored
	 */
	private static void removeStyle(IResourceDelta[] editedResources, HashSet<String> removedStylesName){
		for(IResourceDelta resource : editedResources){
			if (resource.getAffectedChildren().length>0) {
				removeStyle(resource.getAffectedChildren(), removedStylesName);
			}
			IPath rawLocation = resource.getResource().getRawLocation();
			if (rawLocation != null){
				String key = rawLocation.toOSString();
				List<JRStyle> removedElement = externalStylesCache.remove(key);
				if (removedElement != null){
					ArrayList<JRStyle> cachedStyles = new ArrayList<JRStyle>();
					StyleTemplateFactory.getStylesReference(key, cachedStyles, new HashSet<File>());
					externalStylesCache.put(key, cachedStyles);
					for(JRStyle style : removedElement)
						removedStylesName.add(style.getName());
				}
			}
		}
	}
	
	/**
	 * When a close action  is done, if it was of a report remove eventually its interpreter 
	 * from the cache
	 * 
	 */
	private static void removeReport(IResourceDelta[] editedResources){
		for(IResourceDelta resource : editedResources){
			if (resource.getAffectedChildren().length>0) {
				removeReport(resource.getAffectedChildren());
			}
			IPath rawLocation = resource.getResource().getRawLocation();
			if (rawLocation != null){
				String key = rawLocation.toOSString();
				interpreterMaps.remove(key);
			}
		}
	}
	
	/**
	 * If the expression of an external style can be resolved then return all the jrstyle defined inside
	 * otherwise return an empty list
	 * 
	 * @param styleExpression expression of the external style
	 * @param project project of the report
	 * @param jConfig Configuration of the report to evaluate the expression
	 * @return Not null list of styles inside the external style associated with the project and expression
	 */
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
