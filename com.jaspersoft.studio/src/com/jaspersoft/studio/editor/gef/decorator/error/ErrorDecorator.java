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
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.ResourceManager;

public class ErrorDecorator implements IDecorator {

	/**
	 * Warning icon
	 */
	private static ImageIcon warningIcon = null;
	
	
	/**
	 * Standard constructor, load the warningIcon
	 */
	public ErrorDecorator(){
    if (warningIcon == null)
    {
        warningIcon = new javax.swing.ImageIcon(ErrorDecorator.class.getResource("/icons/resources/warning.png")); //$NON-NLS-1$
    }
	}
	
	/**
	 * Print the warning icon when an element is out of bound and set the tooltip text.
	 */
	@Override
	public void paint(Graphics graphics, ComponentFigure fig) {
  		if (fig.getJrElement() instanceof JRDesignElement)
      {
      			Rectangle r = fig.getBounds();
      			Graphics2D g = ((J2DGraphics)graphics).getGraphics2D();
      			Stroke oldStroke = g.getStroke();
      			g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));
            g.setColor(new Color(255,0,0,128));
            g.setStroke(new BasicStroke(2.0f));
            g.drawRect(r.x, r.y, r.width - 2, r.height - 2);
            g.drawImage(warningIcon.getImage(), r.x + r.width - warningIcon.getIconWidth()  , r.y, null);
            fig.setToolTip(new org.eclipse.draw2d.Label(Messages.ErrorDecorator_PositionErrorToolTip,  ResourceManager.getPluginImage(JaspersoftStudioPlugin.PLUGIN_ID, "icons/resources/warning.png"))); //$NON-NLS-2$
      }
  } 	
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	};
	

}
	

