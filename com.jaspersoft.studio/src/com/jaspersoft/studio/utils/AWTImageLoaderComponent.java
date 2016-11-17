/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Component class to use a MediaTracker and wait for an AWT image loading.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class AWTImageLoaderComponent extends Component {
	private static final long serialVersionUID = 1L;

	/**
	 * Uses the default {@link Toolkit} to load an image from the specified location. A {@link MediaTracker} instance is
	 * created to wait the loading of the AWT image.
	 * 
	 * @param imgLocation
	 *          the image location
	 * @return the AWT image loaded
	 * @throws IOException
	 *           if something goes wrong during image loading
	 */
	public Image loadAndWaitForImage(String imgLocation) throws IOException {
		Image image = Toolkit.getDefaultToolkit().createImage(imgLocation);
		MediaTracker m = new MediaTracker(this);
		/* Add the loading image to the MediaTracker, with an ID of 1 */
		m.addImage(image, 1);
		/* Explicitly wait for the image to load */
		try {
			m.waitForAll();
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}

		return image;
	}
}
