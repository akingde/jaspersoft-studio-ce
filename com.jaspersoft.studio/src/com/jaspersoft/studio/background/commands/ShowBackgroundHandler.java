/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
		BackgroundImageFigure figure = getBackgroundFigure();
		if (figure != null){
			figure.setBackgroundImageVisible(!figure.isBackgroundImageVisible());
			ReportContainer currentContainer = getReportContainer();
			if (currentContainer != null){
				if (!figure.isBackgroundImageVisible())	{
					currentContainer.setBackgroundImageEditable(false);
					SelectionHelper.deselectBackground();
				}
				Object value = currentContainer.getModel().getValue();
				if (value instanceof JasperDesign){
					JasperDesign jd = (JasperDesign)value;
					jd.getEventSupport().firePropertyChange(JRPropertiesMap.PROPERTY_VALUE, !figure.isBackgroundImageVisible(), figure.isBackgroundImageVisible());
				}
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
		BackgroundImageFigure figure = getBackgroundFigure();
		if (figure != null){
			element.setChecked(figure.isBackgroundImageVisible());
		}
	}
	
	/**
	 * Return the background figure of the current editor. If the current 
	 * editor dosen't contains a valid figure or there isn't a valid
	 * editor opened it return null
	 * 
	 * @return the background figure or null if it can't be found
	 */
	protected BackgroundImageFigure getBackgroundFigure(){
		EditPart backgroundPart = SelectionHelper.getBackgroundEditPart();
		if (backgroundPart != null){
			return (BackgroundImageFigure)((AbstractGraphicalEditPart)backgroundPart).getFigure();
		}
		return null;
	}
	
	/**
	 * Return the report container of the current editor. If the current 
	 * editor dosen't contains a report container or there isn't a valid
	 * editor opened it return null
	 * 
	 * @return the current report container or null if it can't be found
	 */
	protected ReportContainer getReportContainer(){
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			JrxmlEditor editor = (JrxmlEditor) currentEditor;
			ReportContainer currentContainer =  editor.getReportContainer();
			return currentContainer;
		}
		return null;
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
