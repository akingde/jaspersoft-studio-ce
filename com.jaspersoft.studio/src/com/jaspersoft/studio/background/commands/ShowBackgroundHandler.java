/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.background.commands;

import java.util.Map;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

import com.jaspersoft.studio.background.BackgroundImageFigure;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Action to enable or disable the show background flag, used to hide
 * the background without remove it
 * 
 * @author Orlandin Marco
 *
 */
public class ShowBackgroundHandler extends AbstractHandler implements IElementUpdater {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			JrxmlEditor editor = (JrxmlEditor) currentEditor;
			ReportContainer currentContainer =  editor.getReportContainer();
			currentContainer.setBackgroundImageVisible(!currentContainer.isBackgroundImageVisible());
			if (!currentContainer.isBackgroundImageVisible())	{
				currentContainer.setBackgroundImageEditable(false);
				SelectionHelper.deselectBackground();
			}
			Object value = editor.getReportContainer().getModel().getValue();
			if (value instanceof JasperDesign){
				JasperDesign jd = (JasperDesign)value;
				jd.getEventSupport().firePropertyChange(JRPropertiesMap.PROPERTY_VALUE, !currentContainer.isBackgroundImageVisible(), currentContainer.isBackgroundImageVisible());
			}
		}
		return null;
	}

	/**
	 * Since this is a check action this method show the action checked or not
	 * when the flag is true or false
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateElement(UIElement element, Map parameters) {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			JrxmlEditor editor = (JrxmlEditor) currentEditor;
			ReportContainer currentContainer =  editor.getReportContainer();
			element.setChecked(currentContainer.isBackgroundImageVisible());
		}
	}
	
	/**
	 * Enable if the current editor is a jrxml editor and if the current background
	 * image is defined
	 */
	@Override
	public boolean isEnabled() {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			EditPart part = SelectionHelper.getBackgroundEditPart();
			if (part != null){
				BackgroundImageFigure figure = (BackgroundImageFigure)((AbstractGraphicalEditPart)part).getFigure();
				return (figure.hasImage());
			}
		}
		return false;
	}

}
