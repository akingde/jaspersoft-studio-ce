/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background.commands;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.background.BackgroundImageFigure;
import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Action to select the background file. When a new image is selected
 * the editor disable the background edit mode if it was enabled and
 * enable the background visible figure flag
 * 
 * @author Orlandin Marco
 *
 */
public class SelectBackgroundHandler extends AbstractHandler {

	/**
	 * Supported extension
	 */
	private static final String[] FILTER_EXTS = { "*.jpg", "*.png", "*.bmp", "*.*"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		FileDialog dialog = new FileDialog(UIUtils.getShell());
		dialog.setText(Messages.MBackgrounImage_imageDialogTitle);
		String homefolder = System.getProperty("user.home"); //$NON-NLS-1$
		dialog.setFilterPath(homefolder);
		dialog.setFilterExtensions(FILTER_EXTS);
		String path = dialog.open();
		if (path != null){
			IEditorPart editor = SelectionHelper.getActiveJRXMLEditor();
			if (editor instanceof JrxmlEditor){
				JrxmlEditor jrxmlEditor = (JrxmlEditor)editor;
				Object value = jrxmlEditor.getReportContainer().getModel().getValue();
				if (value instanceof JasperDesign){
					JasperDesign jd = (JasperDesign)value;
					setFigureVisible();
					jrxmlEditor.getReportContainer().setBackgroundImageEditable(false);
					jd.setProperty(MBackgrounImage.PROPERTY_PATH, path);
				}
			}
		}
		return null;
	}
	
	/**
	 * Get the background figure of the current editor and set it visible. If the current 
	 * editor dosen't contains a valid figure or there isn't a valid
	 * editor opened it dosen't do nothing
	 * 
	 */
	protected void setFigureVisible(){
		EditPart backgroundPart = SelectionHelper.getBackgroundEditPart();
		if (backgroundPart != null){
			BackgroundImageFigure figure = (BackgroundImageFigure)((AbstractGraphicalEditPart)backgroundPart).getFigure();
			figure.setBackgroundImageVisible(true);
		}
	}
	
	/**
	 * The action is available if the current editor is a jrxml editor
	 */
	@Override
	public boolean isEnabled() {
		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
		return (currentEditor instanceof JrxmlEditor);
	}


}
