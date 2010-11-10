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
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

/**
 * The Class GenericImageRenderer.
 */
public final class GenericImageRenderer implements ImageRenderer
{
  
  /** The Constant PALETTE_DATA. */
  private static final PaletteData PALETTE_DATA = new PaletteData(16711680, 65280, 255);

  /* (non-Javadoc)
   * @see com.jaspersoft.studio.editor.java2d.ImageRenderer#render(org.eclipse.swt.widgets.Display, org.eclipse.swt.graphics.GC, int[], int, int, int, int, int, int, int, int)
   */
  public final void render(Display paramDisplay, GC paramGC, int[] data, int xSrc, int ySrc, int width, int height, int xDest, int yDest, int imgWidth, int imgHeight)
  {
	  	ImageData imageData = new ImageData(width, height, 32, PALETTE_DATA);
    	imageData.setPixels(0, 0, data.length, data, 0);
    	Image image = new Image(paramDisplay, imageData);
	    try {
	      paramGC.drawImage(image, 0, 0, width, height, xDest, yDest, imgWidth, imgHeight);
	      return;
	    }
	    finally
	    {
	    	image.dispose();
	    }
  }
}
