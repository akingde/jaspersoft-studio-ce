/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.ZoomManager;

import com.jaspersoft.studio.editor.gef.parts.JSSScalableFreeformRootEditPart;

/*
 * 
 * @author Chicu Veaceslav
 */
public class SQLDesignerRootEditPart extends JSSScalableFreeformRootEditPart {

	/**
	 * Instantiates a new main designer root edit part.
	 */
	public SQLDesignerRootEditPart() {
		super();
	}

	@Override
	protected GridLayer createGridLayer() {
		return new com.jaspersoft.studio.editor.gef.figures.layers.GridLayer();
	}

	@Override
	protected ZoomManager buildZoomManager() {
		// set zoom manager
		ZoomManager zoomManager = super.buildZoomManager();
		zoomManager.setZoomAnimationStyle(ZoomManager.ANIMATE_ZOOM_IN_OUT);
		zoomManager.setZoomLevels(new double[] { 0.25, 0.35, 0.45, 0.5, 0.6, 0.7, 0.75, 0.8, 0.85, 0.95, 1.0, 1.1, 1.2,
				1.25, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0 });
		List<String> zoomLevels = new ArrayList<>(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		zoomManager.setZoomLevelContributions(zoomLevels);
		return zoomManager;
	}

}
