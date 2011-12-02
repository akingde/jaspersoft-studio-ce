package com.jaspersoft.studio.server.wizard.resource.page;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MRImage;

public class RDImagePage extends AFileResourcePage {

	public RDImagePage(ANode parent, MRImage resource) {
		super("rdimage", parent, resource);
		setTitle("Image");
		setDescription("Image resource");
	}

	@Override
	protected void createFileTab(Composite composite) {
		final Point origin = new Point(0, 0);
		canvas = new Canvas(composite, SWT.NO_BACKGROUND | SWT.NO_REDRAW_RESIZE
				| SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		canvas.setLayoutData(gd);

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				if (img != null) {
					Rectangle client = canvas.getClientArea();
					gc.fillRectangle(0, 0, client.width, client.height);

					gc.drawImage(img, origin.x, origin.y);
				} else
					e.gc.drawText("No image", 0, 0);
			}
		});
		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int hSelection = hBar.getSelection();
				int destX = -hSelection - origin.x;
				Rectangle rect = img.getBounds();
				canvas.scroll(destX, 0, 0, 0, rect.width, rect.height, false);
				origin.x = -hSelection;
			}
		});
		final ScrollBar vBar = canvas.getVerticalBar();
		vBar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int vSelection = vBar.getSelection();
				int destY = -vSelection - origin.y;
				Rectangle rect = img.getBounds();
				canvas.scroll(0, destY, 0, 0, rect.width, rect.height, false);
				origin.y = -vSelection;
			}
		});
		resizeListener = new Listener() {
			public void handleEvent(Event e) {
				Rectangle rect = img.getBounds();
				Rectangle client = canvas.getClientArea();
				hBar.setMaximum(rect.width);
				vBar.setMaximum(rect.height);
				hBar.setThumb(Math.min(rect.width, client.width));
				vBar.setThumb(Math.min(rect.height, client.height));
				int hPage = rect.width - client.width;
				int vPage = rect.height - client.height;
				int hSelection = hBar.getSelection();
				int vSelection = vBar.getSelection();
				if (hSelection >= hPage) {
					if (hPage <= 0)
						hSelection = 0;
					origin.x = -hSelection;
				}
				if (vSelection >= vPage) {
					if (vPage <= 0)
						vSelection = 0;
					origin.y = -vSelection;
				}
				canvas.redraw();
			}
		};
		canvas.addListener(SWT.Resize, resizeListener);
	}

	private Image img;
	private Canvas canvas;
	private Listener resizeListener;

	@Override
	protected void handleFileChange() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				try {
					File f = ((AFileResource) res).getFile();
					if (f == null && !res.getValue().getIsNew()) {
						f = File.createTempFile("jrsimgfile", ".png");
						f.deleteOnExit();
						f.createNewFile();
						WSClientHelper.getResource(res, res.getValue(), f);
					}
					if (f != null && f.exists()) {
						img = new Image(Display.getDefault(), f
								.getAbsolutePath());
						resizeListener.handleEvent(null);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.png", "*.jpg", "*.jpeg", "*.gif" };
	}
}
