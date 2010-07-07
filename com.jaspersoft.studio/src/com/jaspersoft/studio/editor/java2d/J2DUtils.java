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

import java.awt.BasicStroke;
import java.awt.Stroke;

// TODO: Auto-generated Javadoc
/**
 * The Class J2DUtils.
 */
public class J2DUtils {

	  /**
		 * This function provides a way to cancel the effects of the zoom on a particular Stroke. All the stroke values (as
		 * line width, dashes, etc...) are divided by the zoom factor. This allow to have essentially a fixed Stroke
		 * independent by the zoom. The returned Stroke is a copy. Remember to restore the right stroke in the graphics when
		 * done.
		 * 
		 * It works only with instances of BasicStroke
		 * 
		 * zoom is the zoom factor.
		 * 
		 * @param stroke
		 *          the stroke
		 * @param zoom
		 *          the zoom
		 * @return the inverted zoomed stroke
		 */
	  public static Stroke getInvertedZoomedStroke(Stroke stroke, double zoom)
	  {
	            if (stroke == null || !(stroke instanceof BasicStroke )) return stroke;
	            
	            BasicStroke bs = (BasicStroke)stroke;
	            float[] dashArray = bs.getDashArray();
	            
	            float[] newDashArray = null;
	            if (dashArray != null)
	            {
	                newDashArray = new float[dashArray.length];
	                for (int i=0; i<newDashArray.length; ++i)
	                {
	                    newDashArray[i] = (float)(dashArray[i] / zoom);
	                }
	            }
	            
	            BasicStroke newStroke = new BasicStroke(       
	                            (float)(bs.getLineWidth() / zoom),
	                            bs.getEndCap(),
	                            bs.getLineJoin(),
	                            bs.getMiterLimit(),
	                            //(float)(bs.getMiterLimit() / zoom),
	                            newDashArray,
	                            (float)(bs.getDashPhase() / zoom)
	                    );
	            return newStroke;
	  }
}
