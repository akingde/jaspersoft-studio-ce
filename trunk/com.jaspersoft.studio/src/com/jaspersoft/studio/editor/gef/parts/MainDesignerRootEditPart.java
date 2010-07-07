/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;

import com.jaspersoft.studio.editor.java2d.J2DScalableFreeformLayeredPane;

// TODO: Auto-generated Javadoc
/**
 * The Class MainDesignerRootEditPart.
 * 
 * @author Chicu Veaceslav
 */
public class MainDesignerRootEditPart extends ScalableFreeformRootEditPart {

	/** The Constant REPORT_LAYER. */
	public static final String REPORT_LAYER = "REPORT_LAYER";
	
	/** The Constant SECTIONS_LAYER. */
	public static final String SECTIONS_LAYER = "SECTIONS_LAYER";
	
	/** The Constant ELEMENTS_LAYER. */
	public static final String ELEMENTS_LAYER = "ELEMENTS_LAYER";

	/**
	 * Instantiates a new main designer root edit part.
	 */
	public MainDesignerRootEditPart() {
		super();
		// set zoom manager
		ZoomManager zoomManager = getZoomManager();
		zoomManager.setZoomAnimationStyle(ZoomManager.ANIMATE_ZOOM_IN_OUT);
		zoomManager.setZoomLevels(new double[] { 0.25, 0.35, 0.45, 0.5, 0.6, 0.7, 0.75, 0.8, 0.85, 0.95, 1.0, 1.1, 1.2,
				1.25, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0 });
		List<String> zoomLevels = new ArrayList<String>(3);
		zoomLevels.add(ZoomManager.FIT_ALL);
		zoomLevels.add(ZoomManager.FIT_WIDTH);
		zoomLevels.add(ZoomManager.FIT_HEIGHT);
		zoomManager.setZoomLevelContributions(zoomLevels);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.ScalableFreeformRootEditPart#createScaledLayers()
	 */
	@Override
	protected ScalableFreeformLayeredPane createScaledLayers() {
		ScalableFreeformLayeredPane pane = super.createScaledLayers();

		ScalableFreeformLayeredPane layers = new J2DScalableFreeformLayeredPane();

		layers.add(getPrintableLayers(), PRINTABLE_LAYERS);
		layers.add(pane.getLayer(SCALED_FEEDBACK_LAYER), SCALED_FEEDBACK_LAYER);

		// set layers
		FreeformLayer elementsLayer = new FreeformLayer();
		layers.addLayerBefore(elementsLayer, ELEMENTS_LAYER, FEEDBACK_LAYER);

		FreeformLayer sectionsLayer = new FreeformLayer();
		layers.addLayerBefore(sectionsLayer, SECTIONS_LAYER, ELEMENTS_LAYER);

		GridLayer gridLayer = new GridLayer() {
			@Override
			protected void paintGrid(Graphics g) {
				Rectangle clip = g.getClip(Rectangle.SINGLETON);

				if (gridX > 0) {
					if (origin.x >= clip.x)
						while (origin.x - gridX >= clip.x)
							origin.x -= gridX;
					else
						while (origin.x < clip.x)
							origin.x += gridX;

					if (gridX > 0) {
						if (origin.y >= clip.y)
							while (origin.y - gridX >= clip.y)
								origin.y -= gridX;
						else
							while (origin.y < clip.y)
								origin.y += gridX;
					}

					for (int j = origin.y; j < clip.y + clip.height; j += gridX)
						for (int i = origin.x; i < clip.x + clip.width; i += gridX)
							g.drawPoint(i, j);
					// ((J2DGraphics)g).getGraphics2D().drawRect(i, j,1,1);

				}
			}
		};
		gridLayer.setSpacing(new Dimension(20, 20));
		gridLayer.setForegroundColor(ColorConstants.black);

		layers.add(gridLayer, GRID_LAYER);

		FreeformLayer reportLayer = new FreeformLayer();
		layers.addLayerBefore(reportLayer, REPORT_LAYER, SECTIONS_LAYER);

		return layers;
	}

}
