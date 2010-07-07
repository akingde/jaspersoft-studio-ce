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
package com.jaspersoft.studio.editor.gef.figures.util;


import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
 
// TODO: Auto-generated Javadoc
/**
 * The Class RoundGradientContext.
 * 
 * @author gtoffoli
 */
public class RoundGradientContext
    implements PaintContext {
  
  /** The m point. */
  protected Point2D mPoint;
  
  /** The m radius. */
  protected Point2D mRadius;
  
  /** The m c2. */
  protected Color mC1, mC2;
  
  /**
	 * Instantiates a new round gradient context.
	 * 
	 * @param p
	 *          the p
	 * @param c1
	 *          the c1
	 * @param r
	 *          the r
	 * @param c2
	 *          the c2
	 */
  public RoundGradientContext(Point2D p, Color c1, Point2D r, Color c2) {
    mPoint = p;
    mC1 = c1;
    mRadius = r;
    mC2 = c2;
  }
  
  /* (non-Javadoc)
   * @see java.awt.PaintContext#dispose()
   */
  public void dispose() {}
  
  /* (non-Javadoc)
   * @see java.awt.PaintContext#getColorModel()
   */
  public ColorModel getColorModel() { return ColorModel.getRGBdefault(); }
  
  /* (non-Javadoc)
   * @see java.awt.PaintContext#getRaster(int, int, int, int)
   */
  public Raster getRaster(int x, int y, int w, int h) {
    WritableRaster raster =
        getColorModel().createCompatibleWritableRaster(w, h);
    
    int[] data = new int[w * h * 4];
    for (int j = 0; j < h; j++) {
      for (int i = 0; i < w; i++) {
        double distance = mPoint.distance(x + i, y + j);
        double radius = mRadius.distance(0, 0);
        double ratio = distance / radius;
        if (ratio > 1.0)
          ratio = 1.0;
      
        int base = (j * w + i) * 4;
        data[base + 0] = (int)(mC1.getRed() + ratio *
            (mC2.getRed() - mC1.getRed()));
        data[base + 1] = (int)(mC1.getGreen() + ratio *
            (mC2.getGreen() - mC1.getGreen()));
        data[base + 2] = (int)(mC1.getBlue() + ratio *
            (mC2.getBlue() - mC1.getBlue()));
        data[base + 3] = (int)(mC1.getAlpha() + ratio *
            (mC2.getAlpha() - mC1.getAlpha()));
      }
    }
    raster.setPixels(0, 0, w, h, data);
    
    return raster;
  }
}
