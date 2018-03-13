/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.gef.EditPart;

import com.jaspersoft.studio.editor.gef.selection.JSelectionManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
/*
 * The Class J2DScrollingGraphicalViewer.
 */
public class J2DScrollingGraphicalViewer extends JSSScrollingGraphicalViewer {
	
	/**
	 * This counter is used to disable the update of the selection when
	 * the edit part are refreshing
	 */
	private int lockCounter = 0;
	
	public J2DScrollingGraphicalViewer(){
		super();
		setSelectionManager(new JSelectionManager());
	}
	
	/**
	 * Internally creates a J2DLightweightSystem.
	 * 
	 * @return the lightweight system
	 * @see org.eclipse.gef.ui.parts.GraphicalViewerImpl#createLightweightSystem()
	 */
	protected LightweightSystem createLightweightSystem() {
		return new J2DLightweightSystem();
	}
	
	/**
	 * This override avoid to autocenter the element on selection when the option
	 * is disable by the preferences
	 */
	@Override
	public void reveal(EditPart part){
		JasperReportsConfiguration jrContext = null;
		if (getContents() != null && getContents().getModel() instanceof ANode){
			jrContext = ((ANode)getContents().getModel()).getJasperConfiguration();
		}
		//Unable to get the JasperConfiguration, uses the default fallback
		if (jrContext == null){
			jrContext = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		}
		if (jrContext.getPropertyBoolean(DesignerPreferencePage.P_CENTER_SELECTION, Boolean.TRUE)) {
			super.reveal(part);
		}
	}
	
	/**
	 * Allow to mark the editparts as in refreshing state, this will 
	 * disable the fire of the selection change. This is done because
	 * when deleting (ie) many parts each one update its selection status
	 * triggering the selection refresh, with a big impact on the performance.
	 * With this method is possible to disable this before the first refreshed part
	 * and re-nable at the end.
	 * When the method is called and it change the status of the counter it will 
	 * fire the selection change automatically, but only if the counter is to 0,
	 * that means that there are no part still refreshing the children
	 * 
	 * @param value true to allow the fire of the selection change, false otherwise
	 */
	public void setRefreshingParts(boolean isRefreshing, Object caller) {
		if (isRefreshing) {
			lockCounter++;
		} else {
			lockCounter --;
		}
		fireSelectionChanged();
	}
	
	/**
	 * Method used to call the super fireSelectionChange from anonymous class
	 */
	protected void fireParentSelectionChange(){
		super.fireSelectionChanged();
	}
	
	/**
	 * Override to avoid the update of the selection when the parts
	 * are still refrshing and improve the performances
	 */
	@Override
	protected void fireSelectionChanged() {
		if (lockCounter <= 0) {
			UIUtils.getDisplay().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					fireParentSelectionChange();
				}
			});
		
		}
	}
}
