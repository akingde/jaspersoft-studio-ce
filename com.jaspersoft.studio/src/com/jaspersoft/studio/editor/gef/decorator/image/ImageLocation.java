/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.decorator.chainable.AbstractPainter;

/**
 * Define a image that need to be painted on the figure as a decorator
 * @author Orlandin Marco
 *
 */
public class ImageLocation extends AbstractPainter {	
	
	/**
	 * The string to paint, it can have various attributes
	 */
	private String path;
	
	/**
	 * Cache of the loaded AWT image
	 */
	private Image loadedImage;
	
	/**
	 * Flag to keep track if there were already an attempt to load
	 * the image
	 */
	private boolean attemptLoading = false;
	
	/**
	 * 
	 * @param loc location of the string
	 * @param value value of the string to print
	 */
	public ImageLocation(Location loc, String path){
		super(loc);
		this.path = path;
	}
	
	/**
	 * Return the image, if it was already loaded return the image 
	 * on the cache, otherwise try to load it, but only if there were
	 * not other loading attempts
	 * 
	 * @return an AWT image, can be null
	 */
	protected Image getImage() {
		if (loadedImage == null && !attemptLoading) {
			try {
				URL resource = JaspersoftStudioPlugin.getInstance().getBundle().getResource(path);
				loadedImage = ImageIO.read(resource);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				attemptLoading = true;
			}
		}
		return loadedImage;
	}
	
	/**
	 * Return true if there is a image to print with size not 0
	 * 
	 * @return true if there is a non empty image to print
	 */
	public boolean hasValue(){
		if (getImage() != null) {
			return  getImage().getWidth(null) != 0;
		}
		return false;
	}

	/**
	 * Print the image on the graphics
	 */
	@Override
	public void paint(Graphics2D g, int x, int y) {
		if (hasValue()) {
			g.drawImage(getImage(), x, y, null);
		} 
	}

	/**
	 * Return the size of the image
	 */
	@Override
	public Point getElementSize(Graphics2D g) {
		if (hasValue()) {
			return new Point(getImage().getHeight(null), getImage().getHeight(null) + 4);
		}
		return new Point(0,0);
	}
}
