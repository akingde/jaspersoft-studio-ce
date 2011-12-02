package com.jaspersoft.studio.server.wizard.resource.page;

import java.io.File;

import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class AFileResourcePage extends AResourcePage {

	public AFileResourcePage(String pageName, ANode parent,
			AFileResource resource) {
		super(pageName, parent, resource);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createFileTab(tabFolder);
	}

	protected void createFileTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("File");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		item.setControl(composite);

		Button bexport = new Button(composite, SWT.PUSH | SWT.LEFT);
		bexport.setText("Download File");
		bexport.setImage(Activator.getImage("icons/drive-download.png"));
		bexport.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.SAVE);
				fd.setFilterExtensions(getFilter());
				fd.setText("Save Resource To File ...");
				String filename = fd.open();
				if (filename != null) {
					try {
						WSClientHelper.getResource(AFileResourcePage.this.res,
								res.getValue(), filename);
						File file = new File(filename);
						int dotPos = filename.lastIndexOf(".");
						String strFilename = filename.substring(0, dotPos);
						switch (JRTypeSniffer.getImageType(FileUtils
								.getBytes(file))) {
						case JRRenderable.IMAGE_TYPE_GIF:
							file = FileUtils.fileRenamed(file, strFilename,
									".gif");
							break;
						case JRRenderable.IMAGE_TYPE_JPEG:
							file = FileUtils.fileRenamed(file, strFilename,
									".jpeg");
							break;
						case JRRenderable.IMAGE_TYPE_PNG:
							file = FileUtils.fileRenamed(file, strFilename,
									".png");
							break;
						case JRRenderable.IMAGE_TYPE_TIFF:
							file = FileUtils.fileRenamed(file, strFilename,
									".tiff");
							break;
						}
						((AFileResource) res).setFile(file);
					} catch (Exception e1) {
						UIUtils.showError(e1);
					}
					handleFileChange();
				}
			}

		});

		Button bimport = new Button(composite, SWT.PUSH | SWT.LEFT);
		bimport.setText("Upload File");
		bimport.setImage(Activator.getImage("icons/drive-upload.png"));
		bimport.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.OPEN);
				fd.setFilterExtensions(getFilter());
				fd.setText("Select Resource File ...");
				String filename = fd.open();
				if (filename != null) {
					((AFileResource) res).setFile(new File(filename));
					AFileResourcePage.this.bindingContext.updateTargets();
				}
				handleFileChange();
			}

		});

		Text trefuri = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bindingContext.bindValue(SWTObservables.observeText(trefuri, SWT.NONE),
				PojoObservables.observeValue(res, "fileName"));

		createFileTab(composite);

		handleFileChange();
	}

	protected void handleFileChange() {

	}

	protected void createFileTab(Composite tabFolder) {

	}

	protected abstract String[] getFilter();

}
