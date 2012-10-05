/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.properties;

import java.io.File;

import net.sf.jasperreports.engine.type.ImageTypeEnum;
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
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class AResourceSection extends ASection {
	private Text trefuri;
	private Button bimport;
	private Button bexport;

	@Override
	protected void createSectionControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {

		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmp.setBackground(parent.getBackground());

		bexport = new Button(cmp, SWT.PUSH);
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
						WSClientHelper.getResource(res, res.getValue(),
								filename);
						File file = new File(filename);
						int dotPos = filename.lastIndexOf(".");
						String strFilename = filename.substring(0, dotPos);

						ImageTypeEnum itype = JRTypeSniffer
								.getImageTypeValue(FileUtils.getBytes(file));
						if (itype == ImageTypeEnum.GIF) {
							fileRenamed(file, strFilename, ".gif");
						} else if (itype == ImageTypeEnum.JPEG) {
							fileRenamed(file, strFilename, ".jpeg");
						} else if (itype == ImageTypeEnum.PNG) {
							fileRenamed(file, strFilename, ".png");
						} else if (itype == ImageTypeEnum.TIFF) {
							fileRenamed(file, strFilename, ".tiff");
						}
					} catch (Exception e1) {
						UIUtils.showError(e1);
					}
				}
			}

			private void fileRenamed(File file, String strFilename, String ext) {
				String fname = strFilename + ext;
				file.renameTo(new File(fname));
				UIUtils.showWarning("Attention! file type is different, so it was renamed to:\n "
						+ fname);
			}

		});

		bimport = new Button(cmp, SWT.PUSH);
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
					bindingContext.updateTargets();
				}
			}

		});

		trefuri = getWidgetFactory().createText(cmp, "",
				SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	public void enableFields(boolean enable) {
		trefuri.setEditable(enable);
		bimport.setEnabled(enable);
	}

	@Override
	protected void bind() {
		bindingContext.bindValue(SWTObservables.observeText(trefuri, SWT.NONE),
				PojoObservables.observeValue(res, "fileName"));
	}

	protected abstract String[] getFilter();

}
