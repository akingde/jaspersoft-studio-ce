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
package com.jaspersoft.studio.editor.gef.decorator;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.java2d.J2DUtils;

public class PDFDecorator implements IDecorator {

  static ImageIcon startImageAwt = null;
  static ImageIcon endImageAwt = null;
  
  
  public PDFDecorator(){
  	if (startImageAwt == null || endImageAwt == null){
  			startImageAwt = new javax.swing.ImageIcon(PDFDecorator.class.getResource("/icons/resources/corner1.png"));
  			endImageAwt = new javax.swing.ImageIcon(PDFDecorator.class.getResource("/icons/resources/corner2.png"));
  	}
  }
	
	@Override
	public void paint(Graphics graphics, ComponentFigure fig) {
		//if (!IReportManager.getInstance().getPreferences().getBoolean("showPDF508Tags", false)) return;

    if (fig.getJrElement() instanceof JRDesignElement)
    {
    	
  		Rectangle r = fig.getBounds();
  		Graphics2D g = ((J2DGraphics)graphics).getGraphics2D();
  		Stroke oldStroke = g.getStroke();
  		g.setStroke(J2DUtils.getInvertedZoomedStroke(oldStroke, graphics.getAbsoluteScale()));
        

        String tagValue = "";
        String startString = "";
        String fullString = "";
        String endString = "";
        
        boolean drawstart = false;
        boolean drawend = false;

        String[] tags = {
            "net.sf.jasperreports.export.pdf.tag.h1","H1",
            "net.sf.jasperreports.export.pdf.tag.h2","H2",
            "net.sf.jasperreports.export.pdf.tag.h3","H3",
            "net.sf.jasperreports.export.pdf.tag.table","TBL",
            "net.sf.jasperreports.export.pdf.tag.tr","TR",
            "net.sf.jasperreports.export.pdf.tag.th","TH",
           "net.sf.jasperreports.export.pdf.tag.td","TD"
        };
        
      
        JRPropertiesMap v =  fig.getJrElement().getPropertiesMap();
        
        for (int i=0; i<tags.length; i += 2)
        {  
            String prop = tags[i];
            String label = tags[i+1];
            tagValue = v.getProperty(prop);
            if (tagValue != null){
	            if (tagValue.equals("full")) { drawstart = true; drawend=true;  fullString += label + " ";}
	            else if (tagValue.equals("start"))  { drawstart = true; startString += label + " ";}
	            else if (tagValue.equals("end"))  { drawend = true; endString = label + " " + endString; }
            }

        }
        if (drawstart) drawStart(g,r);
        if (drawend) drawEnd(g, r);

        Font f = g.getFont();

        Color color = g.getColor();
      
        startString = startString.trim();
        endString = endString.trim();
        fullString = fullString.trim();
        
        g.setFont(new Font("SansSerif", 0, 10));
        g.setColor(new Color(195,47,193));

        if (startString.length() > 0) {
            g.drawString(startString,r.x+4, r.y+10);
        }

        if (endString.length() > 0) {
            int strWidth = graphics.getFontMetrics().getAverageCharWidth()*endString.length();
            g.drawString(endString, r.x + r.width - 3 - strWidth, r.y + r.height - 3 );
        }

        if (fullString.length() > 0) {
            int strWidth = 0;
            if (startString.length() > 0) strWidth = graphics.getFontMetrics().getAverageCharWidth()*(startString.length()+1);
            AttributedString as = new AttributedString(fullString);
            as.addAttribute(TextAttribute.FONT, graphics.getFont());
            as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            g.drawString(as.getIterator(), r.x + 4 + strWidth, r.y + 10);
        }

        g.setFont(f);
        g.setColor(color);
        
    } 
    //gr.setTransform(af);
	}


private void drawEnd(Graphics2D gr, Rectangle r) {
    gr.drawImage(endImageAwt.getImage(), r.x + r.width-endImageAwt.getIconWidth() -2 , r.y + r.height- endImageAwt.getIconHeight() - 2, null);
}
private void drawStart(Graphics2D gr, Rectangle r) {
   gr.drawImage(startImageAwt.getImage(), r.x, r.y, null);
}

}
