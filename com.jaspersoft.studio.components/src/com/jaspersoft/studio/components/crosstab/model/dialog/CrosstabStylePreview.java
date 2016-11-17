/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.dialog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.editor.gef.figures.borders.ShadowBorder;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.utils.AlfaRGB;

/**
 * This class is used to generate a preview of the layout of a crosstab, 
 * starting from a CrosstabStyle object.
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabStylePreview extends Composite {
	
	/**
	 * The style of the crosstab
	 */
	private CrosstabStyle crosstabStyle;
	
	/**
	 * The parent figure
	 */
	private Figure parentFigure;
	
	/**
	 * The area where the table will be inserted
	 */
	private Canvas square;
	
	/**
	 * Figure where the table will be painted
	 */
	private RectangleFigure crosstabPreview;
	
	private LightweightSystem lws;
	
	/**
	 * List of preview paint listener, called when the figure is painted. They
	 * can be used to perform additional paint operation
	 */
	private List<Listener> previewPaintListener = new ArrayList<Listener>();
	
	/**
	 * Create a preview with a default crosstab style.
	 *
	 * @param parent parent component
	 * @param style style of this composite
	 */
	public CrosstabStylePreview(Composite parent, int style){
		super(parent, style);
		crosstabStyle = new CrosstabStyle(AlfaRGB.getFullyOpaque(ColorConstants.lightBlue.getRGB()), ColorSchemaGenerator.SCHEMAS.DEFAULT, false);
		createFigure();
	}
	
	/**
	 * Set the crosstab style and redraw the preview image
	 * 
	 * @param style the new crosstab style
	 */
	public void setTableStyle(CrosstabStyle style){
		crosstabStyle = style;
		setTBounds();
	}

	/**
	 * Initialize the preview figure field and elements
	 */
	private void createFigure(){
		setLayout(new GridLayout(1,false));
		lws = new LightweightSystem();
		square = new Canvas(this, SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 2;
		square.setLayoutData(gd);
		lws.setControl(square);
		parentFigure = new Figure();
		parentFigure.setLayoutManager(new XYLayout());
		lws.setContents(parentFigure);
		crosstabPreview = new RectangleFigure() {

			@Override
			public void paint(Graphics graphics) {
				int y = 5;
			    int x = 5;
			    int w = getBounds().width -10;
			    int h = getBounds().height-10;
		        int rowHeight = h/4;
		        int rowWidth = w/4;
		        //I recalculate the total width and height with the rounded values;
		        w = rowWidth*4;
		        h = rowHeight*4;
		        org.eclipse.swt.graphics.Color originalBackgroun = graphics.getBackgroundColor();
		        org.eclipse.swt.graphics.Color originalForeground = graphics.getForegroundColor();
		        
		        
		        //Last row and column
		        Rectangle lastRow = new Rectangle(x, y+rowHeight*3, w, rowHeight);
		        Rectangle lastCol = new Rectangle(x+rowWidth*3, y, rowWidth, h);
		        graphics.setBackgroundColor(getSwtColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_TOTAL)));
		        graphics.fillRectangle(lastRow.x, lastRow.y, lastRow.width, lastRow.height);
		        graphics.fillRectangle(lastCol.x, lastCol.y, lastCol.width, lastCol.height);
		        
		        //column and row before the last
		        Rectangle beforeLastRow = new Rectangle(x, y+rowHeight*2, rowWidth*3, rowHeight);
		        Rectangle beforeLastCol = new Rectangle(x+rowWidth*2, y, rowWidth, rowHeight*3);
		        graphics.setBackgroundColor(getSwtColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_GROUP)));
		        graphics.fillRectangle(beforeLastRow.x, beforeLastRow.y, beforeLastRow.width, beforeLastRow.height);
		        graphics.fillRectangle(beforeLastCol.x, beforeLastCol.y, beforeLastCol.width, beforeLastCol.height);
		        
		        //detail cell
		        Rectangle detail = new Rectangle(x +rowWidth, y+rowHeight, rowWidth, rowHeight);
		        graphics.setBackgroundColor(getSwtColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_DETAIL)));
		        graphics.fillRectangle(detail.x, detail.y, detail.width, detail.height);
		        
		        //Measure cells
		        Rectangle measure1 = new Rectangle(x, y+rowHeight, rowWidth, rowHeight);
		        Rectangle measure2 = new Rectangle(x + rowWidth, y, rowWidth, rowHeight);
		        graphics.setBackgroundColor(getSwtColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_MEASURES)));
		        graphics.fillRectangle(measure1.x, measure1.y, measure1.width, measure1.height);
		        graphics.fillRectangle(measure2.x, measure2.y, measure2.width, measure2.height);
		        
		        if (crosstabStyle.isShowGrid()){
			        if (crosstabStyle.getWhiteGrid()) graphics.setForegroundColor(ColorConstants.white);
			        else graphics.setForegroundColor(ColorConstants.black);
				    // Draw border...
				    for (int i=0; i<5; i++)
				    {	
				    	if (i==0)
				    		graphics.drawLine(x + rowWidth, y+rowHeight*i, x+w, y+rowHeight*i);
				    	else 
				    		graphics.drawLine(x, y+rowHeight*i, x+w, y+rowHeight*i);
				    }
	
				    for (int i=0; i<5; i++)
				    {	
				    	if (i==0)
				    		graphics.drawLine(x+rowWidth*i, y + rowHeight, x+rowWidth*i, y+h);
				    	else 
				    		graphics.drawLine(x+rowWidth*i, y, x+rowWidth*i, y+h);
				    }
		        }

		        graphics.setBackgroundColor(originalBackgroun);
		        graphics.setForegroundColor(originalForeground);
		        firePreviewPaintListeners(graphics, x, y, w, h);
			}
		};
		crosstabPreview.setBorder(new ShadowBorder());
		parentFigure.add(crosstabPreview);	
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				setTBounds();
			}
		});
		Display.getCurrent().asyncExec(new Runnable() {

			public void run() {
				setTBounds();
			}
		});
	}
	
	private org.eclipse.swt.graphics.Color getSwtColor(Color awtColor){
		return SWTResourceManager.getColor(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
	}
	
	/**
	 * Set the size of the preview area and request a redraw
	 */
	public void setTBounds() {
		if (!isDisposed()) {
			Dimension psize = parentFigure.getSize();
			crosstabPreview.setSize(psize);
			crosstabPreview.setLocation(new Point(0,0));
			parentFigure.invalidate();

			square.redraw();
			lws.getUpdateManager().performUpdate();
		}
	}

	/**
	 * Add a preview figure paint listener. The listener is called
	 * once the preview figure is painted. On the data of the event
	 * there will be the graphics used to paint the preview figure
	 * so something else can be painted on top of it
	 * 
	 * @param listener a unique and not null listener
	 */
	public void addPreviewPaintListenr(Listener listener){
		if (listener != null && !previewPaintListener.contains(listener)){
			previewPaintListener.add(listener);
		}
	}
	
	/**
	 * Fire all the added preview paint listener
	 * 
	 * @param graphics the graphics used to paint the preview figure
	 * @param x the x of the preview figure
	 * @param y the y of the preview figure
	 * @param w the width of the preview figure
	 * @param h the height of the previw figure
	 */
	protected void firePreviewPaintListeners(Graphics graphics, int x, int y, int w, int h){
		Event e = new Event();
		e.widget = this;
		e.data = graphics;
		e.x = x;
		e.y = y;
		e.width = w;
		e.height = h;
		for(Listener listener : previewPaintListener){
			listener.handleEvent(e);
		}
	}
	
	/**
	 * When the composite is disposed the list listener is cleared
	 */
	@Override
	public void dispose() {
		super.dispose();
		previewPaintListener.clear();
	}
}
