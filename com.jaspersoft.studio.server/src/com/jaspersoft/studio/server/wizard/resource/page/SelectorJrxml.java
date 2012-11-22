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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
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
				if (brRepo.getSelection())
					setEnabled(0, false);
			}
		});

		jsRefDS = new Text(composite, SWT.BORDER);
		jsRefDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bRef = new Button(composite, SWT.PUSH);
		bRef.setText("Browse");
		bRef.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// N.B: remember we need to pass a fresh new MServerProfile info
				// in order
				// to avoid problem of refreshing (children/parent relationship
				// changes)
				// due to tree viewer node expansion...
				RepositoryDialog rd = new RepositoryDialog(Display.getDefault()
						.getActiveShell(), ServerManager
						.getMServerProfileCopy((MServerProfile) parent
								.getRoot())) {
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
							ref.setParentFolder(runit.getParentFolder() + "/"
									+ runit.getName() + "_files");
							ref.setWsType(ResourceDescriptor.TYPE_JRXML);
							ref.setUriString(ref.getParentFolder() + "/"
									+ ref.getName());
							replaceMainReport(res, ref);
							fireSelectionChanged();

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
				if (brLocal.getSelection())
					setEnabled(1, false);
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
					fireSelectionChanged();

					resfile = new File(((IFile) wizard.getFirstResult())
							.getLocationURI());
					((AFileResource) res).setFile(resfile);

					jsLocDS.setText(resfile.getAbsolutePath());
				}
			}
		});
		ResourceDescriptor r = getMainReport(res.getValue());
		if (r != null) {
			if (r.getIsReference()) {
				rrepo = r;
				setEnabled(0, true);
			} else {
				rlocal = r;
				setEnabled(1, true);
			}
		} else
			setEnabled(1, true);
	}

	private SelectionListener listener;

	public void addSelectionListener(SelectionListener listener) {
		this.listener = listener;
	}

	public static void replaceMainReport(final MResource res,
			ResourceDescriptor rd) {
		ResourceDescriptor rdel = getMainReport(res.getValue());
		if (rdel != null) {
			int index = res.getValue().getChildren().indexOf(rdel);
			if (index >= 0)
				res.getValue().getChildren().remove(index);
		}
		if (rd != null)
			res.getValue().getChildren().add(rd);
	}

	public static ResourceDescriptor getMainReport(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_JRXML)
					&& r.isMainReport()) {
				return r;
			}
		}
		return null;
	}

	private ResourceDescriptor rlocal;
	private ResourceDescriptor rrepo;
	private File resfile;

	private void setEnabled(int pos, boolean init) {
		bRef.setEnabled(false);
		jsRefDS.setEnabled(false);

		bLoc.setEnabled(false);
		jsLocDS.setEnabled(false);

		brRepo.setSelection(false);
		brLocal.setSelection(false);

		jsRefDS.setText("");
		jsLocDS.setText("");

		ResourceDescriptor r = getMainReport(res.getValue());
		AFileResource aFileResource = (AFileResource) res;
		File file = aFileResource.getFile();
		switch (pos) {
		case 0:
			if (!init) {
				rlocal = r;
				resfile = file;
				replaceMainReport(res, rrepo);
				aFileResource.setFile(null);
			}
			bRef.setEnabled(true);
			brRepo.setSelection(true);
			jsRefDS.setEnabled(true);
			if (rrepo != null)
				jsRefDS.setText(Misc.nvl(rrepo.getReferenceUri()));
			break;
		case 1:
			if (!init) {
				rrepo = r;
				replaceMainReport(res, rlocal);
			}

			brLocal.setSelection(true);
			bLoc.setEnabled(true);
			jsLocDS.setEnabled(true);
			if (rlocal != null) {
				if (resfile != null) {
					jsLocDS.setText(resfile.getAbsolutePath());
					aFileResource.setFile(resfile);
				} else
					jsLocDS.setText(Misc.nvl(rlocal.getName()));
			}
			break;
		}

	}

	private void fireSelectionChanged() {
		if (listener != null)
			listener.widgetSelected(null);
	}

}
