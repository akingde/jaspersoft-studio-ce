/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
/*/*
 * The Class J2DLightweightSystem.
 */
public final class J2DLightweightSystem extends LightweightSystem
{
  
  /**
	 * Instantiates a new j2 d lightweight system.
	 */
  public J2DLightweightSystem()
  {
	 setUpdateManager(new J2DUpdateManager());
  }

  /* (non-Javadoc)
   * @see org.eclipse.draw2d.LightweightSystem#setControl(org.eclipse.swt.widgets.Canvas)
   */
  public final void setControl(Canvas canvas)
  {
  	super.setControl(canvas);
	  if (canvas != null) {
		  getUpdateManager().setGraphicsSource(new J2DGraphicsSource(canvas));
	  }
	  
  }

  /* (non-Javadoc)
   * @see org.eclipse.draw2d.LightweightSystem#paint(org.eclipse.swt.graphics.GC)
   */
  public final void paint(GC gc) {
    
	  ((J2DUpdateManager)getUpdateManager()).paintAll(gc);
  }
}
