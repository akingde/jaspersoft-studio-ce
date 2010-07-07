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
import org.eclipse.swt.widgets.Display;

// TODO: Auto-generated Javadoc
/**
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