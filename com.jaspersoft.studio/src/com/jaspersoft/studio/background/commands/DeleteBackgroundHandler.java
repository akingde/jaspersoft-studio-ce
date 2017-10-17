/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.background.BackgroundImageFigure;
import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Action to delete the background and all its properties
 * 
 * @author Orlandin Marco
 *
 */
public class DeleteBackgroundHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = SelectionHelper.getActiveJRXMLEditor();
		if (editor instanceof JrxmlEditor){
			JrxmlEditor jrxmlEditor = (JrxmlEditor)editor;
			Object value = jrxmlEditor.getReportContainer().getModel().getValue();
			if (value instanceof JasperDesign){
				JasperDesign jd = (JasperDesign)value;
				jd.removeProperty(MBackgrounImage.PROPERTY_PATH);
				
				jd.removeProperty(MBackgrounImage.PROPERTY_ALPHA);
				jd.removeProperty(MBackgrounImage.PROPERTY_HEIGHT);
				jd.removeProperty(MBackgrounImage.PROPERTY_WIDTH);
				jd.removeProperty(MBackgrounImage.PROPERTY_X);
				jd.removeProperty(MBackgrounImage.PROPERTY_Y);
				jd.removeProperty(MBackgrounImage.PROPERTY_KEEP_RATIO);
				jd.getPropertiesMap().getProperty(MBackgrounImage.PROPERTY_PATH);
				AbstractVisualEditor currentEditor = (AbstractVisualEditor)jrxmlEditor.getReportContainer().getActiveEditor();
				currentEditor.getGraphicalViewer().deselectAll();
				EditPart backgroundPart = SelectionHelper.getBackgroundEditPart();
				if (backgroundPart != null){
					BackgroundImageFigure figure = (BackgroundImageFigure)((AbstractGraphicalEditPart)backgroundPart).getFigure();
					//set the image to 0,0 to resize the viewport
					figure.setLocation(new Point(0,0));
				}
			}
		}
		return null;
	}
	
	/**
	 * The action is enabled if the current editor is a jrxml editor and 
	 * if there is a visible background
	 */
	@Override
	public boolean isEnabled() {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			EditPart part = SelectionHelper.getBackgroundEditPart();
			if (part != null && ((AbstractGraphicalEditPart)part).getFigure().isVisible()) return true;
		}
		return false;
	}


}
