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
package com.jaspersoft.studio.editor.gef.parts.band;


import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;

public class BandFeedbackFigure  extends RectangleFigure {

	String text = "";
	/**
	 * Instantiates a new ElementFeedbackFigure.
	 * 
	 * @param jd
	 *          the jd
	 * @param viewMargins
	 *          the view margins
	 */
	public BandFeedbackFigure() {
		setFill(false);
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	
	@Override
	public void paintClientArea(Graphics g) {


			if (g == null) return;
			
			Rectangle clientArea = getClientArea();
			Graphics2D gr = ((J2DGraphics) g).getGraphics2D();
			
			//Stroke oldStroke = graphics2d.getStroke();
			//gr.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, g.getAbsoluteScale()));
			
			Paint oldPaint = gr.getPaint();
      gr.setPaint(new Color(0,0,255,30));
      gr.fillRect(clientArea.x-30, clientArea.y-30, clientArea.width+60, clientArea.height+60);
      gr.setPaint(oldPaint);
      
      
      if (clientArea.width < 20 || clientArea.height < 20) return;

			// draw the line
			gr.setColor(Color.gray);
			// Draw the label...
			
			//Label label = new Label(text);
			//label.setFont(gr.getFont());
			FontMetrics fm = gr.getFontMetrics();
			Rectangle2D textBounds = fm.getStringBounds(text, gr);
			
			java.awt.Rectangle textBgBounds = new java.awt.Rectangle(
					  clientArea.x-30 + (clientArea.width+60)/2 - (int)textBounds.getWidth() /2 -10, 
						clientArea.y-30 + (clientArea.height+60)/2 - (int)textBounds.getHeight()/2 - 2, 
						(int)textBounds.getWidth() + 20,
						(int)textBounds.getHeight() + 4);
			
			gr.setColor(new Color(30,30,30,128));
			gr.fillRoundRect(textBgBounds.x, textBgBounds.y, textBgBounds.width, textBgBounds.height, 20, 20);
			
			
			gr.drawLine(clientArea.x-30,                             // X
      		        clientArea.y-30 + (clientArea.height+60)/2,  // Half Y
      		        clientArea.x-30 + (clientArea.width+60 -textBgBounds.width)/ 2, // Up to the right side of the label
      		        clientArea.y-30 + (clientArea.height+60)/2); // Same Y...
      
      gr.drawLine(clientArea.x-30 + (clientArea.width+60 + textBgBounds.width)/ 2, // From the left side of the label
	        clientArea.y-30 + (clientArea.height+60)/2,  // Half Y
	        clientArea.x-30 + clientArea.width+60, // Up to the full width
	        clientArea.y-30 + (clientArea.height+60)/2); // Same Y...

      gr.drawLine(clientArea.x-30 + (clientArea.width+60)/2,   // Half X
	        clientArea.y-30,  // Half Y
	        clientArea.x-30 + (clientArea.width+60)/2, // Half X
	        clientArea.y-30 + (clientArea.height+60 -textBgBounds.height)/ 2); // Up to the top of the label...

      gr.drawLine(clientArea.x-30 + (clientArea.width+60)/2, // Half X
      		clientArea.y-30 + (clientArea.height+60 + textBgBounds.height)/ 2,  // // Up to the bottom of the label...
      		clientArea.x-30 + (clientArea.width+60)/2, // Half X
      		clientArea.y-30 + clientArea.height+60); // Up to the bounds height...

      
      gr.setColor(Color.white);
			gr.drawString(text,textBgBounds.x+10, textBgBounds.y + fm.getAscent() );
			

	}
}

