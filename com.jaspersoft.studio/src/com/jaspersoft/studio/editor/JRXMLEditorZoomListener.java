/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wb.swt.Keyboard;

import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Listener to handle the Zoom changes in JRXML-like editors.
 */
public class JRXMLEditorZoomListener implements Listener {

	/**
	 * Since the action is triggered more times (once for every control) the trigger time is used to repeat many times and
	 * actions that was actually requested once. So from the action with the same trigger time only one is executed. (it
	 * would me more correct consider a time interval, but essentially the trigger is so fast that they have the same
	 * trigger time).
	 */
	private int lastTime = -1;

	/**
	 * Execute the zoom in action if they can be retrieved, if the preview page is visible and if the executed action is
	 * enabled, otherwise it dosen't do nothing
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.time != lastTime && JasperReportsPlugin.isPressed(Keyboard.getCtrlKey())) {
			IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
			if (currentEditor != null && currentEditor instanceof AbstractJRXMLEditor) {
				AbstractJRXMLEditor jrxmlEditor = (AbstractJRXMLEditor) currentEditor;
				if (!jrxmlEditor.isPartActivated())
					return;
				if (jrxmlEditor.getActivePage() == AbstractJRXMLEditor.PAGE_PREVIEW) {
					lastTime = event.time;
				} else {
					IEditorPart pageDesigner = jrxmlEditor.getEditor(AbstractJRXMLEditor.PAGE_DESIGNER);
					if (jrxmlEditor.getActivePage() == AbstractJRXMLEditor.PAGE_DESIGNER
							&& (event.character == '0' || event.keyCode == 48)) {
						if (pageDesigner instanceof ReportContainer) {
							IEditorPart editor = ((ReportContainer) pageDesigner).getActiveEditor();
							if (editor instanceof AbstractVisualEditor) {
								IAction action = ((AbstractVisualEditor) editor).getActionRegistry().getAction(ZoomActualAction.ID);
								if (action != null)
									action.run();
							}
						}
					} else if (jrxmlEditor.getActivePage() == AbstractJRXMLEditor.PAGE_DESIGNER
							&& (event.keyCode == '=' || event.keyCode == SWT.KEYPAD_ADD)) {
						if (pageDesigner instanceof ReportContainer) {
							IEditorPart editor = ((ReportContainer) pageDesigner).getActiveEditor();
							if (editor instanceof AbstractVisualEditor) {
								IAction action = ((AbstractVisualEditor) editor).getActionRegistry().getAction(
										GEFActionConstants.ZOOM_IN);
								if (action != null)
									action.run();
							}
						}
					}
				}
			}
		}
	}
}
