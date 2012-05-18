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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;

public class SelectorJrxml {
	private Button brRepo;
	private Text jsRefDS;
	private Button brLocal;
	private Text jsLocDS;
	private Button bLoc;
	private Button bRef;
	private MResource res;

	public void createControls(Composite cmp, final ANode parent,
			final MResource res) {
		this.res = res;

		Composite composite = new Composite(cmp, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(2, false));

		brRepo = new Button(composite, SWT.RADIO);
		brRepo.setText("Select from Repository");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brRepo.setLayoutData(gd);
		brRepo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(0);
			}
		});

		jsRefDS = new Text(composite, SWT.BORDER);
		jsRefDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bRef = new Button(composite, SWT.PUSH);
		bRef.setText("Browse");
		bRef.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RepositoryDialog rd = new RepositoryDialog(Display.getDefault()
						.getActiveShell(), parent.getRoot()) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return r instanceof MJrxml;
					}
				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						ResourceDescriptor runit = res.getValue();
						try {
							ResourceDescriptor ref = rs.getValue();
							ref = WSClientHelper.getResource(parent, ref);
							ref.setIsReference(true);
							ref.setMainReport(true);
							ref.setReferenceUri(ref.getUriString());
							ref.setParentFolder(runit.getUriString() + "_files");
							ref.setWsType(ResourceDescriptor.TYPE_JRXML);
							ref.setUriString(ref.getParentFolder() + "/"
									+ ref.getName());
							replaceMainReport(res, ref);

							jsRefDS.setText(ref.getUriString());
						} catch (Exception e1) {
							UIUtils.showError(e1);
						}
					}
				}
			}
		});

		brLocal = new Button(composite, SWT.RADIO);
		brLocal.setText("Local Resource");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brLocal.setLayoutData(gd);
		brLocal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(1);
			}
		});

		jsLocDS = new Text(composite, SWT.BORDER);
		jsLocDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bLoc = new Button(composite, SWT.PUSH);
		bLoc.setText("Browse");
		bLoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog wizard = new FilteredResourcesSelectionDialog(
						Display.getCurrent().getActiveShell(), false,
						ResourcesPlugin.getWorkspace().getRoot(),
						IResource.FILE);
				wizard.setInitialPattern("*.jrxml");//$NON-NLS-1$
				if (wizard.open() == Dialog.OK) {
					ResourceDescriptor jrxmlDescriptor = new ResourceDescriptor();
					jrxmlDescriptor.setName("main_jrxml");
					jrxmlDescriptor.setLabel("Main Jrxml");
					jrxmlDescriptor.setWsType(ResourceDescriptor.TYPE_JRXML);
					jrxmlDescriptor.setIsNew(true);
					jrxmlDescriptor.setMainReport(true);
					jrxmlDescriptor.setIsReference(false);
					jrxmlDescriptor.setHasData(true);

					replaceMainReport(res, jrxmlDescriptor);

					((AFileResource) res).setFile(new File(((IFile) wizard
							.getFirstResult()).getLocationURI()));
				}
			}
		});
		ResourceDescriptor r = getMainReport(res.getValue());
		if (r != null) {
			if (r.getIsReference())
				setEnabled(0);
			else
				setEnabled(1);
		}
	}

	public static void replaceMainReport(final MResource res,
			ResourceDescriptor rd) {
		ResourceDescriptor rdel = getMainReport(res.getValue());
		if (rdel != null) {
			int index = res.getValue().getChildren().indexOf(rdel);
			if (index >= 0)
				res.getValue().getChildren().remove(index);
		}
		res.getValue().getChildren().add(rd);
	}

	private static ResourceDescriptor getMainReport(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_JRXML)
					&& r.isMainReport()) {
				return r;
			}
		}
		return null;
	}

	private void setEnabled(int pos) {
		bRef.setEnabled(false);
		jsRefDS.setEnabled(false);

		bLoc.setEnabled(false);
		jsLocDS.setEnabled(false);

		brRepo.setSelection(false);
		brLocal.setSelection(false);

		jsRefDS.setText("");
		jsLocDS.setText("");

		ResourceDescriptor r = getMainReport(res.getValue());
		switch (pos) {
		case 0:
			bRef.setEnabled(true);
			brRepo.setSelection(true);
			jsRefDS.setEnabled(true);
			if (r != null)
				jsRefDS.setText(Misc.nvl(r.getReferenceUri()));
			break;
		case 1:
			brLocal.setSelection(true);
			bLoc.setEnabled(true);
			jsLocDS.setEnabled(true);
			if (r != null)
				jsLocDS.setText(Misc.nvl(r.getName()));
			break;
		}

	}

}
