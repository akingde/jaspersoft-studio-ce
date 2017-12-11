/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.figures;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;



/**
 * A image figure (for which you are supposed to provide an image)
 * which paints on top a busy animated circle.
 * 
 * @author gtoffoli
 *
 */
public class BusyImageFigure extends ImageFigure {

	protected boolean busy = false;
	private Timer timer = null;
	private int angle = 0;
	
	private int LINES = 12;
	// There are 11 lines that are printed.
	// This variable keeps track of which one is painted in black.
	private int blackPosition = 0;
	
	public boolean isBusy() {
		return busy;
	}

	/**
	 * Turn the busy indicator on and off.
	 * Please note that when off, the indicator is not visible at all.
	 * 
	 * @param busy
	 */
	public void setBusy(boolean busy) {
		
		if (busy != this.busy)
		{
			this.busy = busy;
			
			if (this.busy)
			{
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						angle += 360/LINES;
						blackPosition++;
						blackPosition%=LINES;
						
						if (angle >= 360) {
							angle = 0;
				        }
						
						if (UIUtils.getDisplay() == null || UIUtils.getDisplay().isDisposed()) return;
						
						UIUtils.getDisplay().asyncExec(new Runnable() {

				            public void run() {
				            	revalidate();
				            	notifyImageChanged();
				            	repaint();
				            }
				        });
					}
				}, 0l, 150l);
			}
			else
			{
				if (timer != null)
				{
					timer.cancel();
					timer = null;
				}
			}
		}
		
		this.busy = busy;
	}

	public BusyImageFigure(Image image){
		super(image);
	}

	@Override
	public void paint(Graphics g) {
		Rectangle area = getBounds().getShrinked(getInsets());

		if(this.busy){
			Image icon = createBusyIcon();
			
			if (icon != null)
			{
				g.drawImage(icon, new Point(area.x + area.width/2 - icon.getImageData().width/2,
											area.y + area.height/2 - icon.getImageData().height/2));
		
				icon.dispose();
			}
			else
			{
				System.out.println("Icon is null!!");
			}
		} else if (getImage() != null){
			g.pushState();
			try {
				Image image = getImage();
				int scaledX = (area.width - (int)Math.round(image.getImageData().width/g.getAbsoluteScale())) / 2 + area.x;
				int scaledY = (area.height - (int)Math.round(image.getImageData().height/g.getAbsoluteScale())) / 2 + area.y;
				int scaledHeight = (int)Math.round(image.getImageData().height/g.getAbsoluteScale());
				int scaledWidth = (int)Math.round(image.getImageData().width/g.getAbsoluteScale());
				g.drawImage(image, 0, 0, image.getImageData().width, image.getImageData().height, scaledX , scaledY, scaledWidth, scaledHeight);
				g.restoreState();
			} finally {
				g.popState();
			}	
		}
	}
	
	public Dimension getPreferredSize(int wHint, int hHint) {
		//Decrees the preferred size of the lable height
		return new Dimension(BookPagesFigure.PREFERRED_WIDTH, BookPagesFigure.PREFERRED_HEIGHT - BookPagesFigure.PREFERRED_LABEL_HEIGHT);
	}

	/**
	 * 
	 * fadeLimit = 15;
	 * @param step
	 * @return
	 */
	private Image createBusyIcon()
	{
		int w = 36;
		int h = 36;
		BufferedImage bufImage = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = (Graphics2D) bufImage.createGraphics();
		
		// Paint the busy indicator
	    int cx = w / 2;
	    int cy = h / 2;
	    int lineSize = 6;
	    
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    
	    // 4 pixel width of the lines
	    g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	    
	    g2.setPaint(Color.white);
	    g2.fillRect(0, 0, bufImage.getWidth(), bufImage.getHeight());
	    g2.rotate(Math.PI * angle / 180, cx, cy);
	    
	    for (int i = 0; i < LINES; i++) {
	    	
	      // The color of each line of the busy indicator uses 
	      // a different alpha (from 255->balack to 0->transparent).
	      // The alpha must be set to 255 on the correct line...
	    	
	      // Calculate distance from the black position...
	      int alpha = (LINES - blackPosition) - i;
	      if (alpha < 0) alpha += LINES;
	      
	      g2.setColor(new Color(115,115,146, (int)((255.0f/LINES) * alpha) ));
	      g2.drawLine(cx + lineSize, cy, cx + lineSize * 2, cy);
	      g2.rotate(-Math.PI / 6, cx, cy);
	    }
	 
	    return UIUtils.awt2Swt(bufImage);
	}
	
	
}

