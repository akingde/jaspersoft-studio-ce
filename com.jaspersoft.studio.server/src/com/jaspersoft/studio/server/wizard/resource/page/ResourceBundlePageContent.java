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

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MResource;

public class ResourceBundlePageContent extends AFileResourcePageContent {

	public ResourceBundlePageContent(ANode parent, MResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ResourceBundlePageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.resourcebundle";
	}

	@Override
	public String getName() {
		return Messages.RDResourceBundlePage_title;
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.*", "*.properties" }; //$NON-NLS-1$
	}

	private Text txt;

	@Override
	protected void createFileTab(Composite composite) {
		txt = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.WRAP | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		txt.setLayoutData(gd);
	}

	@Override
	protected String getIntialPattern() {
		return "*.properties";
	}

	@Override
	protected void handleFileChange() {
		super.handleFileChange();
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				try {
					File f = ((AFileResource) res).getFile();
					if (f == null && !res.getValue().getIsNew()) {
						f = File.createTempFile("jrsimgfile", ".properties"); //$NON-NLS-1$ //$NON-NLS-2$
						f.deleteOnExit();
						f.createNewFile();
						WSClientHelper.getResource(new NullProgressMonitor(), res, res.getValue(), f);
					}
					if (f != null && f.exists()) {
						txt.setText(FileUtils.readFileAsAString(f));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
