package com.jaspersoft.studio.components.table.model.dialog;

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

public class TableStylePreview extends Composite {
	
	private TableStyle tableStyle;
	
	private Figure parentFigure;
	
	private Canvas square;
	
	private RectangleFigure borderPreview;
	
	private J2DLightweightSystem lws;
	
	
	public TableStylePreview(Composite parent, int style){
		super(parent, style);
		tableStyle = new TableStyle(ColorConstants.lightBlue.getRGB(), ColorSchemaGenerator.SCHEMA_DEFAULT,0,ColorConstants.black.getRGB(),false);
		createFigure();
	}
	
	public TableStylePreview(Composite parent, int style, TableStyle tableStyle) {
		super(parent, style);
		this.tableStyle = tableStyle;
		createFigure();
	}
	
	public void setTableStyle(TableStyle style){
		tableStyle = style;
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
		        int rowHeight = h/7;
		        Rectangle row_bounds = new Rectangle(x,y + rowHeight*2, w, rowHeight);
		        Graphics2D g = ComponentFigure.getG2D(graphics);
		        g.setColor(Color.WHITE);
			    g.fillRect(row_bounds.x, row_bounds.y, row_bounds.width, row_bounds.height);
			    Color c = null;
			    row_bounds = new Rectangle(x,y + rowHeight*3, w, rowHeight);
			    g.setColor(Color.WHITE);
			    if (tableStyle.hasAlternateColor())
			    {
			    	c = tableStyle.getColorValue(TableStyle.COLOR2);
			        g.setColor(c);
			    }
			    g.fill(row_bounds);
			    row_bounds = new Rectangle(x,y + rowHeight*4, w, rowHeight);
			    g.setColor(Color.WHITE);
			    g.fill(row_bounds);

			    // TABLE HEADER
			    row_bounds = new Rectangle(x,y + rowHeight*0, w, rowHeight);
			    c = tableStyle.getColorValue(TableStyle.COLOR3);
			    g.setColor(c);
			    g.fill(row_bounds);

			    // TABLE FOOTER
			    row_bounds = new Rectangle(x,y + rowHeight*6, w, rowHeight);
			    g.setColor(c);
			    g.fill(row_bounds);


			    // COLUMN HEADER
			    row_bounds = new Rectangle(x,y + rowHeight*1, w, rowHeight);
			    c = tableStyle.getColorValue(TableStyle.COLOR2);
			    g.setColor(c);
			    g.fill(row_bounds);

			    // COLUMN FOOTER
			    row_bounds = new Rectangle(x,y + rowHeight*5, w, rowHeight);
			    g.setColor(c);
			    g.fill(row_bounds);


			    g.setColor(tableStyle.getBorderColor());
			    // Draw border...
			    for (int i=0; i<8; ++i)
			    {
			    	g.drawLine(x, y+rowHeight*i, x+w, y+rowHeight*i);
			    }

			    h = rowHeight*7;
			    if (tableStyle.getBorderStyle() == 0)
			    {
			        for (int i=0; i<3; ++i)
			        {
			            g.drawLine(x+(i*(w/3)), y, x+(i*(w/3)), y+h);
			        }
			        g.drawLine(x+w, y, x+w, y+h-1);
			    }
			    if (tableStyle.getBorderStyle() == 2)
			    {
			        g.drawLine(x, y, x, y+h);
			        g.drawLine(x+w, y, x+w, y+h-1);
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
