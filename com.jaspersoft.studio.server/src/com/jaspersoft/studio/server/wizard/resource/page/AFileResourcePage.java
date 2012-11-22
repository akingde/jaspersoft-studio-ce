/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
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
		item.setText(Messages.AFileResourcePage_filetabitem);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		item.setControl(composite);

		Button bexport = new Button(composite, SWT.PUSH | SWT.LEFT);
		bexport.setText(Messages.AFileResourcePage_downloadfilebutton);
		bexport.setImage(Activator.getImage("icons/drive-download.png")); //$NON-NLS-1$
		bexport.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.SAVE);
				fd.setFilterExtensions(getFilter());
				fd.setFileName(res.getValue().getName());
				fd.setText(Messages.AFileResourcePage_filedialogtitle);
				String filename = fd.open();
				doSaveFile(filename);
			}

		});

		Button bimport = new Button(composite, SWT.PUSH | SWT.LEFT);
		bimport.setText(Messages.AFileResourcePage_uploadfile);
		bimport.setImage(Activator.getImage("icons/drive-upload.png")); //$NON-NLS-1$
		bimport.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.OPEN);
				fd.setFilterExtensions(getFilter());
				fd.setText(Messages.AFileResourcePage_selectresourcefile);
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
				PojoObservables.observeValue(res, "fileName")); //$NON-NLS-1$

		createFileTab(composite);

		handleFileChange();
	}

	protected void handleFileChange() {

	}

	protected void createFileTab(Composite tabFolder) {

	}

	protected abstract String[] getFilter();

	protected void doSaveFile(String filename) {
		if (filename != null) {
			try {
				WSClientHelper.getResource(AFileResourcePage.this.res,
						res.getValue(), filename);
				File file = new File(filename);
				int dotPos = filename.lastIndexOf("."); //$NON-NLS-1$
				String strFilename = filename.substring(0, dotPos);
				ImageTypeEnum itype = JRTypeSniffer.getImageTypeValue(FileUtils
						.getBytes(file));
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

}
