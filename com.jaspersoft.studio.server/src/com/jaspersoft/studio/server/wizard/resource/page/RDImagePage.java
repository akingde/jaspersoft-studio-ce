package com.jaspersoft.studio.server.wizard.resource.page;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

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
		scroll = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		scroll.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_CENTER));

		canvas = new Canvas(scroll, SWT.NO_REDRAW_RESIZE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (img != null)
					e.gc.drawImage(img, 0, 0);
				else
					e.gc.drawText("No image", 0, 0);
			}
		});

		scroll.setContent(canvas);
		scroll.setExpandVertical(true);
		scroll.setExpandHorizontal(true);
		scroll.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = scroll.getClientArea();
				scroll.setMinSize(canvas.computeSize(r.width, SWT.DEFAULT));
			}
		});
	}

	private Image img;
	private ScrolledComposite scroll; 
	private Canvas canvas;

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
						Rectangle bounds = img.getBounds();
						canvas.setSize(canvas.computeSize(bounds.width,
								bounds.height));
						scroll.setContent(canvas);
					}
					scroll.layout(true);
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
