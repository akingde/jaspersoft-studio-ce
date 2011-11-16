package com.jaspersoft.studio.server.wizard.resource.page;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MRImage;

public class RDImagePage extends AFileResourcePage {

	private Label imgLbl;

	public RDImagePage(ANode parent, MRImage resource) {
		super("rdimage", parent, resource);
		setTitle("Image");
		setDescription("Image resource");
	}

	@Override
	protected void createFileTab(Composite composite) {
		imgLbl = new Label(composite, SWT.CENTER | SWT.BORDER);
		imgLbl.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_CENTER));
	}

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
						Image img = new Image(Display.getDefault(), f
								.getAbsolutePath());
						imgLbl.setImage(img);
						imgLbl.setText("");
					} else {
						imgLbl.setImage(null);
						imgLbl.setText("No Image");
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
