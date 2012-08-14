/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * Design a border and a warning icon on an element that is out of bounds
 * @author Orlandin Marco
 *
 */
public class ErrorDecorator implements IDecorator {

	/**
	 * The color of the warning border
	 */
	private static Color JSS_WARNING_BORDER_COLOR = new Color(255,0,0,128);
	
	/**
	 * The size of the warning border
	 */
	private static float JSS_WARNING_BORDER_SIZE = 0.8f;
	
	/**
	 * Standard constructor, load the warningIcon
	 */
	public ErrorDecorator(){

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
      			Color oldColor = g.getColor();
            g.setColor(JSS_WARNING_BORDER_COLOR);
            g.setStroke(new BasicStroke(JSS_WARNING_BORDER_SIZE));
            
            Rectangle tempRect = new Rectangle();
            tempRect.setBounds(fig.getBounds());
      			if (JSS_WARNING_BORDER_SIZE % 2 == 1) {
      				tempRect.width--;
      				tempRect.height--;
      			}
      			tempRect.width = tempRect.width - (int)Math.ceil(JSS_WARNING_BORDER_SIZE);
      			tempRect.height = tempRect.height - (int)Math.ceil(JSS_WARNING_BORDER_SIZE);
      			tempRect.shrink(JSS_WARNING_BORDER_SIZE, JSS_WARNING_BORDER_SIZE);
      			g.setStroke(new BasicStroke(JSS_WARNING_BORDER_SIZE));
      			g.drawRect(tempRect.x,tempRect.y, tempRect.width, tempRect.height);

            RoundedPolygon.paintComplexWarning(r.x+r.width-5, r.y-2, 6, 12,JSS_WARNING_BORDER_SIZE,g);
            fig.setToolTip(new org.eclipse.draw2d.Label(Messages.ErrorDecorator_PositionErrorToolTip,  ResourceManager.getPluginImage(JaspersoftStudioPlugin.PLUGIN_ID, "icons/resources/warning2.png"))); //$NON-NLS-2$
            g.setStroke(oldStroke);
            g.setColor(oldColor);
      }
  } 	
	
	/**
	 * Set the color of the border of an element when it is out of bounds
	 * @param newColor the new color
	 */
	public void setBorderColor(Color newColor){
		JSS_WARNING_BORDER_COLOR = newColor;
	}
	
	/**
	 * Set the size of the border of an element when it is out of bounds
	 * @param newSize the new size
	 */
	public void setBorderSize(float newSize){
		JSS_WARNING_BORDER_SIZE = newSize;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	};
	

}
	

