/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page;

import java.io.File;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.type.ImageTypeEnum;
import net.sf.jasperreports.engine.util.JRTypeSniffer;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SaveAsDialog;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.utils.Misc;

public abstract class AFileResourcePageContent extends APageContent {
	protected Text trefuri;

	public AFileResourcePageContent(ANode parent, MResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public AFileResourcePageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		if (!res.getValue().getIsNew()) {
			Button bexport = new Button(composite, SWT.PUSH | SWT.LEFT);
			bexport.setText(Messages.AFileResourcePage_downloadfilebutton);
			bexport.setImage(Activator.getDefault().getImage("icons/drive-download.png")); //$NON-NLS-1$
			bexport.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					SaveAsDialog saveAsDialog = new SaveAsDialog(trefuri.getShell());
					saveAsDialog.setOriginalName(res.getValue().getName() + "." + ((AFileResource) res).getDefaultFileExtension());
					if (saveAsDialog.open() == Dialog.OK) {
						IPath path = saveAsDialog.getResult();
						if (path != null) {
							IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
							if (file != null)
								doSaveFile(file.getLocation().toPortableString());
						}
					}
				}
			});
		}

		Button bimport = new Button(composite, SWT.PUSH | SWT.LEFT);
		bimport.setText(Messages.AFileResourcePage_uploadfile);
		bimport.setImage(Activator.getDefault().getImage("icons/drive-upload.png")); //$NON-NLS-1$
		bimport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filename = getFileDialog();
				if (filename != null)
					((AFileResource) res).setFile(new File(filename));
				handleFileChange();
			}
		});

		trefuri = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createFileTab(composite);

		handleFileChange();

		return composite;
	}

	protected void handleFileChange() {
		trefuri.setText(Misc.nvl(((AFileResource) res).getFileName()));
	}

	protected void createFileTab(Composite tabFolder) {

	}

	protected abstract String[] getFilter();

	protected void doSaveFile(String filename) {
		if (filename != null) {
			try {
				WSClientHelper.getResource(AFileResourcePageContent.this.res, res.getValue(), filename);
				File file = new File(filename);
				int dotPos = filename.lastIndexOf("."); //$NON-NLS-1$
				String strFilename = filename.substring(0, dotPos);
				ImageTypeEnum itype = JRTypeSniffer.getImageTypeValue(FileUtils.getBytes(file));
				if (itype == ImageTypeEnum.GIF) {
					file = FileUtils.fileRenamed(file, strFilename, ".gif"); //$NON-NLS-1$
				} else if (itype == ImageTypeEnum.JPEG) {
					file = FileUtils.fileRenamed(file, strFilename, ".jpeg"); //$NON-NLS-1$
				} else if (itype == ImageTypeEnum.PNG) {
					file = FileUtils.fileRenamed(file, strFilename, ".png"); //$NON-NLS-1$
				} else if (itype == ImageTypeEnum.TIFF) {
					file = FileUtils.fileRenamed(file, strFilename, ".tiff"); //$NON-NLS-1$
				}
				((AFileResource) res).setFile(file);
			} catch (Exception e1) {
				UIUtils.showError(e1);
			}
			handleFileChange();
		}
	}

	protected String getFileDialog() {
		FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
		fd.setFilterExtensions(getFilter());
		fd.setText(Messages.AFileResourcePage_selectresourcefile);
		String filename = fd.open();
		return filename;
	}
}
