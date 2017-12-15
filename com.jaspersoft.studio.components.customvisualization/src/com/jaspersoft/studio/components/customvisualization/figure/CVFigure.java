/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.figure;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

import com.jaspersoft.studio.components.customvisualization.model.MCustomVisualization;
import com.jaspersoft.studio.components.customvisualization.ui.UIManager;
import com.jaspersoft.studio.components.customvisualization.ui.framework.CVCWidgetsDescriptor;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.JRComponentFigure;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.ImageUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Figure representing the Custom Visualization component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class CVFigure extends JRComponentFigure {

	public CVFigure(MGraphicElement node) {
		super(node);
	}

	@Override
	public void paint(Graphics graphics) {
		try {
			Image image = getPreviewImage();
			if (image != null) {
				Rectangle figureRect = (this instanceof HandleBounds) ? ((HandleBounds) this).getHandleBounds()
						: this.getBounds();

				int imgWidth = image.getWidth(null);
				int imgHeight = image.getHeight(null);
				// Delegate the painting to the AWT Graphics2D
				Graphics2D g = ComponentFigure.getG2D(graphics);
				if (g != null) {
					g.setColor(Color.WHITE);
					g.fillRect(figureRect.x, figureRect.y, figureRect.x + figureRect.width,
							figureRect.y + figureRect.height);
					g.drawImage(image, figureRect.x, figureRect.y, figureRect.x + figureRect.width,
							figureRect.y + figureRect.height, 0, 0, imgWidth, imgHeight, null);
				}
				paintBorder(graphics);
				paintDecorators(graphics);
			} else
				super.paint(graphics);
		} catch (Exception ex) {
			UIUtils.showError(ex);
		}
	}

	private Image cache;

	/*
	 * Tries to get a preview image using the specified file location.
	 */
	private Image getPreviewImage() throws IOException {
		if (cache != null)
			return cache;
		CVCWidgetsDescriptor cd = UIManager.getComponentDescriptor((MCustomVisualization) model);
		if (cd != null) {
			org.eclipse.swt.graphics.Image img = UIManager.getThumbnail(cd);
			if (img != null) {
				cache = ImageUtils.convertToAWT(img.getImageData());
				return cache;
			}
		}
		return null;
	}

	@Override
	protected boolean allowsFigureDrawCache() {
		return true;
	}
}
