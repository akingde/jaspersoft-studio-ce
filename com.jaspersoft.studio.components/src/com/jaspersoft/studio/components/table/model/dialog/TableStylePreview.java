/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.dialog;

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

import com.jaspersoft.studio.components.table.model.dialog.TableStyle.BorderStyleEnum;
import com.jaspersoft.studio.editor.gef.figures.borders.ShadowBorder;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;
import com.jaspersoft.studio.utils.AlfaRGB;

/**
 * Generate a preview of a table with a table style applied on it
 * 
 * @author Orlandin Marco & Giulio Toffoli
 *
 */
public class TableStylePreview extends Composite {
	
	/**
	 * The style of the table
	 */
	private TableStyle tableStyle;
	
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
	private RectangleFigure borderPreview;
	
	private LightweightSystem lws;
	
	/**
	 * List of preview paint listener, called when the figure is painted. They
	 * can be used to perform additional paint operation
	 */
	private List<Listener> previewPaintListener = new ArrayList<Listener>();
	
	/**
	 * Create a preview with a default table style
	 *
	 * @param parent
	 * @param style
	 */
	public TableStylePreview(Composite parent, int style){
		super(parent, style);
		AlfaRGB baseColor = AlfaRGB.getFullyOpaque(ColorConstants.lightBlue.getRGB());
		AlfaRGB borderColor = AlfaRGB.getFullyOpaque(ColorConstants.black.getRGB());
		tableStyle = new TableStyle(baseColor, ColorSchemaGenerator.SCHEMAS.DEFAULT,BorderStyleEnum.FULL,borderColor ,false);
		createFigure();
	}
	
	/**
	 * Set the table style and redraw the preview image
	 * 
	 * @param style the new table style
	 */
	public void setTableStyle(TableStyle style){
		tableStyle = style;
		setTBounds();
	}
	
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
		
		borderPreview = new RectangleFigure() {

			@Override
			public void paint(Graphics graphics) {
				int y = 5;
			    int x = 5;
			    int w = getBounds().width -10;
			    int h = getBounds().height-10;
		        int rowHeight = h/7;
		        org.eclipse.swt.graphics.Color originalBackgroun = graphics.getBackgroundColor();
		        org.eclipse.swt.graphics.Color originalForeground = graphics.getForegroundColor();
		        
		        Rectangle row_bounds = new Rectangle(x,y + rowHeight*2, w, rowHeight);
		        
		        Color colorDetail =  tableStyle.getColorValue(TableStyle.STANDARD_COLOR_DETAIL);
		        
		        graphics.setBackgroundColor(getSwtColor(colorDetail));
			    graphics.fillRectangle(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);
			    Color c = null;
			    row_bounds = new Rectangle(x,y + rowHeight*3, w, rowHeight);
			    graphics.setBackgroundColor(getSwtColor(colorDetail));
			    if (tableStyle.hasAlternateColor())
			    {
			    	c = tableStyle.getColorValue(TableStyle.COLOR_DETAIL);
			    	graphics.setBackgroundColor(getSwtColor(c));
			    }
			    graphics.fillRectangle(row_bounds);
			    
			    row_bounds = new Rectangle(x,y + rowHeight*4, w, rowHeight);
			    graphics.setBackgroundColor(getSwtColor(colorDetail));
			    graphics.fillRectangle(row_bounds);

			    // TABLE HEADER
			    row_bounds = new Rectangle(x,y + rowHeight*0, w, rowHeight);
			    c = tableStyle.getColorValue(TableStyle.COLOR_TABLE_HEADER);
			    graphics.setBackgroundColor(getSwtColor(c));
			    graphics.fillRectangle(row_bounds);

			    // TABLE FOOTER
			    row_bounds = new Rectangle(x,y + rowHeight*6, w, rowHeight);
			    graphics.setBackgroundColor(getSwtColor(c));
			    graphics.fillRectangle(row_bounds);


			    // COLUMN HEADER
			    row_bounds = new Rectangle(x,y + rowHeight*1, w, rowHeight);
			    c = tableStyle.getColorValue(TableStyle.COLOR_COL_HEADER);
			    graphics.setBackgroundColor(getSwtColor(c));
			    graphics.fillRectangle(row_bounds);

			    // COLUMN FOOTER
			    row_bounds = new Rectangle(x,y + rowHeight*5, w, rowHeight);
			    graphics.setBackgroundColor(getSwtColor(c));
			    graphics.fillRectangle(row_bounds);


			    graphics.setForegroundColor(getSwtColor(tableStyle.getBorderColor()));
			    // Draw border...
			    for (int i=0; i<8; ++i)
			    {
			    	graphics.drawLine(x, y+rowHeight*i, x+w, y+rowHeight*i);
			    }

			    h = rowHeight*7;
			    if (tableStyle.getBorderStyle() == BorderStyleEnum.FULL)
			    {
			        for (int i=0; i<3; ++i)
			        {
			        	graphics.drawLine(x+(i*(w/3)), y, x+(i*(w/3)), y+h);
			        }
			        graphics.drawLine(x+w, y, x+w, y+h-1);
			    }
			    if (tableStyle.getBorderStyle() == BorderStyleEnum.ONLY_HORIZONTAL)
			    {
			    	graphics.drawLine(x, y, x, y+h);
			    	graphics.drawLine(x+w, y, x+w, y+h-1);
			    }
			    
		        graphics.setBackgroundColor(originalBackgroun);
		        graphics.setForegroundColor(originalForeground);
		        firePreviewPaintListeners(graphics, x, y, w, h);
			}
		};
		borderPreview.setBorder(new ShadowBorder());
		parentFigure.add(borderPreview);	
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
		if (!isDisposed() ) {
			Dimension psize = parentFigure.getSize();
			borderPreview.setSize(psize);
			borderPreview.setLocation(new Point(0,0));
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
