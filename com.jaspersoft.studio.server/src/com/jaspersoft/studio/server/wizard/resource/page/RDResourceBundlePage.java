/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.resource.page;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.utils.FileUtils;

public class RDResourceBundlePage extends AFileResourcePage {

	private Text txt;

	public RDResourceBundlePage(ANode parent, MResourceBundle resource) {
		super("rdresourcebundle", parent, resource);
		setTitle("Resource Bundle");
		setDescription("Resource Bundle");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.properties" };
	}

	@Override
	protected void createFileTab(Composite composite) {
		txt = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.WRAP
				| SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		txt.setLayoutData(gd);
	}

	@Override
	protected void handleFileChange() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				try {
					File f = ((AFileResource) res).getFile();
					if (f == null && !res.getValue().getIsNew()) {
						f = File.createTempFile("jrsimgfile", ".properties");
						f.deleteOnExit();
						f.createNewFile();
						WSClientHelper.getResource(res, res.getValue(), f);
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
