/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background.commands;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Action used to set the editor in edit background mode
 * 
 * @author Orlandin Marco
 *
 */
public class TransformBackgroundHandler extends AbstractHandler implements IElementUpdater {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		if (currentEditor instanceof JrxmlEditor){
			JrxmlEditor editor = (JrxmlEditor) currentEditor;
			ReportContainer currentContainer =  editor.getReportContainer();
			currentContainer.setBackgroundImageEditable(!currentContainer.isBackgroundImageEditable());
			if (currentContainer.isBackgroundImageEditable()) SelectionHelper.setBackgroundSelected();
			else SelectionHelper.deselectBackground();
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
			element.setChecked(currentContainer.isBackgroundImageEditable());
		}
	}
	
	/**
	 * The action is enabled if the editor is a jrxml editor and if there
	 * is a background figure visible
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
