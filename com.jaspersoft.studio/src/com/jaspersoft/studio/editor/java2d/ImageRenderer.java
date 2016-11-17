/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
/*/*
 * The Interface ImageRenderer.
 */
public interface ImageRenderer
{
  
  /**
	 * Render.
	 * 
	 * @param paramDisplay
	 *          the param display
	 * @param paramGC
	 *          the param gc
	 * @param imageData
	 *          the image data
	 * @param paramInt1
	 *          the param int1
	 * @param paramInt2
	 *          the param int2
	 * @param paramInt3
	 *          the param int3
	 * @param paramInt4
	 *          the param int4
	 * @param paramInt5
	 *          the param int5
	 * @param paramInt6
	 *          the param int6
	 * @param paramInt7
	 *          the param int7
	 * @param paramInt8
	 *          the param int8
	 */
  public void render(Display paramDisplay, GC paramGC, int[] imageData, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);
}
