package com.jaspersoft.studio.components.crosstab.model.dialog;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.borders.ShadowBorder;
import com.jaspersoft.studio.editor.java2d.J2DLightweightSystem;
import com.jaspersoft.studio.property.color.ColorSchemaGenerator;

public class CrosstabStylePreview extends Composite {
	
	/**
	 * The style of the table
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
	private RectangleFigure borderPreview;
	
	private J2DLightweightSystem lws;
	
	/**
	 * Create a preview with a default table style
	 *
	 * @param parent
	 * @param style
	 */
	public CrosstabStylePreview(Composite parent, int style){
		super(parent, style);
		crosstabStyle = new CrosstabStyle(ColorConstants.lightBlue.getRGB(), ColorSchemaGenerator.SCHEMAS.DEFAULT, false);
		createFigure();
	}
	
	public CrosstabStylePreview(Composite parent, int style, CrosstabStyle tableStyle) {
		super(parent, style);
		this.crosstabStyle = tableStyle;
		createFigure();
	}
	
	/**
	 * Set the table style and redraw the preview image
	 * 
	 * @param style the new table style
	 */
	public void setTableStyle(CrosstabStyle style){
		crosstabStyle = style;
		setTBounds();
	}

	private void createFigure(){
		setLayout(new GridLayout(1,false));
		lws = new J2DLightweightSystem();
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
		        int rowHeight = h/4;
		        int rowWidth = w/4;
		        //I recalculate the total width and height with the rounded values;
		        w = rowWidth*4;
		        h = rowHeight*4;
		        Graphics2D g = ComponentFigure.getG2D(graphics);
		        
		        //Last row
		        Rectangle lastRow = new Rectangle(x, y+rowHeight*3, w, rowHeight);
		        Rectangle lastCol = new Rectangle(x+rowWidth*3, y, rowWidth, h);
		        g.setColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_TOTAL));
		        g.fillRect(lastRow.x, lastRow.y, lastRow.width, lastRow.height);
		        g.fillRect(lastCol.x, lastCol.y, lastCol.width, lastCol.height);
		        
		        //Before last row
		        Rectangle beforeLastRow = new Rectangle(x, y+rowHeight*2, rowWidth*3, rowHeight);
		        Rectangle beforeLastCol = new Rectangle(x+rowWidth*2, y, rowWidth, rowHeight*3);
		        g.setColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_COL_HEADER));
		        g.fillRect(beforeLastRow.x, beforeLastRow.y, beforeLastRow.width, beforeLastRow.height);
		        g.fillRect(beforeLastCol.x, beforeLastCol.y, beforeLastCol.width, beforeLastCol.height);
		        
		        //detail
		        Rectangle detail = new Rectangle(x +rowWidth, y+rowHeight, rowWidth, rowHeight);
		        g.setColor(Color.white);
		        g.fillRect(detail.x, detail.y, detail.width, detail.height);
		        
		        //Measure
		        Rectangle measure1 = new Rectangle(x, y+rowHeight, rowWidth, rowHeight);
		        Rectangle measure2 = new Rectangle(x + rowWidth, y, rowWidth, rowHeight);
		        g.setColor(crosstabStyle.getColorValue(CrosstabStyle.COLOR_TABLE_HEADER));
		        g.fillRect(measure1.x, measure1.y, measure1.width, measure1.height);
		        g.fillRect(measure2.x, measure2.y, measure2.width, measure2.height);
		        
		        if (crosstabStyle.getWhiteGrid()) g.setColor(Color.white);
		        else g.setColor(Color.black);
			    // Draw border...
			    for (int i=0; i<5; i++)
			    {	
			    	if (i==0)
			    		g.drawLine(x + rowWidth, y+rowHeight*i, x+w, y+rowHeight*i);
			    	else 
			    		g.drawLine(x, y+rowHeight*i, x+w, y+rowHeight*i);
			    }

			    for (int i=0; i<5; i++)
			    {	
			    	if (i==0)
			    		g.drawLine(x+rowWidth*i, y + rowHeight, x+rowWidth*i, y+h);
			    	else 
			    		g.drawLine(x+rowWidth*i, y, x+rowWidth*i, y+h);
			    }

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
	
	/**
	 * Set the size of the preview area and request a redraw
	 */
	public void setTBounds() {
		if (!isDisposed()) {
			Dimension psize = parentFigure.getSize();
			borderPreview.setSize(psize);
			borderPreview.setLocation(new Point(0,0));
			parentFigure.invalidate();

			square.redraw();
			lws.getUpdateManager().performUpdate();
		}
	}

}
