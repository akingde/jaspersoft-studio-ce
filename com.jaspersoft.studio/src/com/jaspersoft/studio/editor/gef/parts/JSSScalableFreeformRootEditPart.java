/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.editparts.GuideLayer;
import org.eclipse.gef.editparts.ZoomManager;

/**
 * Adds Zoom support to the standard FreeformGraphicalRootEditPart. This root is
 * just like its superclass, except it inserts a new <code>LayeredPane</code>
 * above the printable layers. This pane is identified with the
 * {@link org.eclipse.gef.LayerConstants#SCALABLE_LAYERS} ID. This root also
 * provides a ZoomManager, for optional use with the
 * {@link org.eclipse.gef.ui.actions.ZoomComboContributionItem}
 *
 * This is essentially a refactoring of ScalableFreeformRootEditPart, to allow to easily
 * customize the ZoomManager, that in the original version can not be overridden since
 * it was instantiated in the constructor
 * 
 * @author Orlandin Marco
 */
public class JSSScalableFreeformRootEditPart extends FreeformGraphicalRootEditPart {

	private ScalableFreeformLayeredPane scaledLayers;
	
	protected ZoomManager zoomManager;

	/**
	 * Constructor for JSSScalableFreeformRootEditPart, call the method
	 * to build the zoom manager: buildZoomManager()
	 */
	public JSSScalableFreeformRootEditPart() {
		zoomManager = buildZoomManager();
	}

	/**
	 * @see FreeformGraphicalRootEditPart#createLayers(LayeredPane)
	 */
	@Override
	protected void createLayers(LayeredPane layeredPane) {
		layeredPane.add(getScaledLayers(), SCALABLE_LAYERS);
		layeredPane.add(new FreeformLayer(), HANDLE_LAYER);
		layeredPane.add(new FeedbackLayer(), FEEDBACK_LAYER);
		layeredPane.add(new GuideLayer(), GUIDE_LAYER);
	}

	/**
	 * Creates a layered pane and the layers that should be scaled.
	 * 
	 * @return a new freeform layered pane containing the scalable layers
	 */
	protected ScalableFreeformLayeredPane createScaledLayers() {
		ScalableFreeformLayeredPane layers = new ScalableFreeformLayeredPane();
		layers.add(createGridLayer(), GRID_LAYER);
		layers.add(getPrintableLayers(), PRINTABLE_LAYERS);
		layers.add(new FeedbackLayer(), SCALED_FEEDBACK_LAYER);
		return layers;
	}

	/**
	 * @see FreeformGraphicalRootEditPart#getLayer(Object)
	 */
	@Override
	public IFigure getLayer(Object key) {
		IFigure layer = scaledLayers.getLayer(key);
		if (layer != null)
			return layer;
		return super.getLayer(key);
	}

	/**
	 * Returns the scalable layers of this EditPart
	 * 
	 * @return LayeredPane
	 */
	protected LayeredPane getScaledLayers() {
		if (scaledLayers == null)
			scaledLayers = createScaledLayers();
		return scaledLayers;
	}

	/**
	 * Returns the zoomManager.
	 * 
	 * @return ZoomManager
	 */
	public ZoomManager getZoomManager() {
		return zoomManager;
	}
	
	/**
	 * Create the zoom manager used by this edit part
	 * 
	 * @return a not null zoom manager
	 */
	protected ZoomManager buildZoomManager(){
		return new ZoomManager((ScalableFigure) getScaledLayers(),((Viewport) getFigure()));
	}

	/**
	 * As additional operation save the zoom manager in the viewer
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#register()
	 */
	@Override
	protected void register() {
		super.register();
		getViewer().setProperty(ZoomManager.class.toString(), getZoomManager());
	}

	/**
	 * As additional operation remove the zoom manager in the viewer
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#unregister()
	 */
	@Override
	protected void unregister() {
		super.unregister();
		getViewer().setProperty(ZoomManager.class.toString(), null);
	}


	protected class FeedbackLayer extends FreeformLayer {
		FeedbackLayer() {
			setEnabled(false);
		}
	}
}
