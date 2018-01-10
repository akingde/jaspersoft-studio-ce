/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ZoomInAction;

import com.jaspersoft.studio.messages.Messages;

/**
 * Set the zoom to 100% on the inner zoom manager
 */
public class ZoomActualAction extends ZoomInAction {

	public static final String ID = Messages.ZoomActualAction_0;
	
	/**
	 * Constructor for ZoomOutAction.
	 * 
	 * @param zoomManager
	 *            the zoom manager
	 */
	public ZoomActualAction(ZoomManager zoomManager) {
		super(zoomManager);
		setId(ID);
		setText(Messages.ZoomActualAction_1);
		setImageDescriptor(JasperReportsPlugin.getDefault().getImageDescriptor("icons/zoomactual.gif")); //$NON-NLS-1$
		setToolTipText(Messages.ZoomActualAction_3);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		zoomManager.setZoom(1.0d);
	}

	/**
	 * @see org.eclipse.gef.editparts.ZoomListener#zoomChanged(double)
	 */
	public void zoomChanged(double zoom) {
		Double zoomLevel = 1.0d;
		setEnabled(zoomLevel.equals(zoomManager.getZoom()));
	}

}
