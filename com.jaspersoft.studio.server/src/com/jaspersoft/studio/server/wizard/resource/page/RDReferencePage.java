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

import net.sf.jasperreports.eclipse.ui.validator.EmptyStringValidator;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.utils.UIUtils;

public class RDReferencePage extends AResourcePage {

	public RDReferencePage(ANode parent, MReference resource) {
		super(Messages.RDReferencePage_id, parent, resource);
		setTitle(Messages.RDReferencePage_title);
		setDescription(Messages.RDReferencePage_desc);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createReferenceTab(tabFolder);
	}

	protected void createReferenceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.RDReferencePage_textreference);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, Messages.RDReferencePage_referencedesc);

		trefuri = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bbrowse = new Button(composite, SWT.ARROW | SWT.DOWN);
		bbrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				Shell shell = Display.getDefault().getActiveShell();
				RepositoryDialog rd = new RepositoryDialog(shell, ServerManager
						.getMServerProfileCopy((MServerProfile) parent
								.getRoot())) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return !(r instanceof MFolder);
					}

				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						res.getValue().setReferenceUri(
								rs.getValue().getUriString());
						loadReference(res.getValue());
						bindingContext.updateTargets();
					}
				}
			}

		});

		Composite cmp = new Composite(composite, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		cmp.setLayoutData(gd);

		UIUtils.createLabel(cmp, Messages.RDReferencePage_parentfolder);
		tparent = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		tparent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tparent.setEnabled(false);

		UIUtils.createLabel(cmp, Messages.RDReferencePage_type);
		ttype = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ttype.setEnabled(false);

		UIUtils.createLabel(cmp, Messages.RDReferencePage_creationdate);
		tcdate = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		tcdate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tcdate.setEnabled(false);

		UIUtils.createSeparator(cmp, 2);

		UIUtils.createLabel(cmp, Messages.RDReferencePage_refid);
		tid = new Text(cmp, SWT.BORDER);
		tid.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(cmp, Messages.RDReferencePage_refname);
		tname = new Text(cmp, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(cmp, Messages.RDReferencePage_refdesc);
		tdesc = new Text(cmp, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 100;
		tdesc.setLayoutData(gd);

		loadReference(res.getValue());
	}

	protected void bind() {
		Object[] bds = bindingContext.getBindings().toArray();
		for (Object obj : bds) {
			Binding b = (Binding) obj;
			bindingContext.removeBinding(b);
			b.dispose();
		}
		bindingContext.bindValue(SWTObservables.observeText(trefuri, SWT.NONE),
				PojoObservables.observeValue(res.getValue(), "referenceUri")); //$NON-NLS-1$
		if (ref != null) {
			bindingContext.bindValue(
					SWTObservables.observeText(tparent, SWT.NONE),
					PojoObservables.observeValue(ref, "parentFolder")); //$NON-NLS-1$

			bindingContext.bindValue(
					SWTObservables.observeText(tcdate, SWT.NONE),
					PojoObservables.observeValue(ref, "creationDate")); //$NON-NLS-1$

			bindingContext.bindValue(
					SWTObservables.observeText(ttype, SWT.NONE),
					PojoObservables.observeValue(ref, "wsType")); //$NON-NLS-1$

			bindingContext
					.bindValue(
							SWTObservables.observeText(tid, SWT.Modify),
							PojoObservables.observeValue(ref, "name"), //$NON-NLS-1$
							new UpdateValueStrategy()
									.setAfterConvertValidator(new EmptyStringValidator()),
							null);

			bindingContext
					.bindValue(
							SWTObservables.observeText(tname, SWT.Modify),
							PojoObservables.observeValue(ref, "label"), //$NON-NLS-1$
							new UpdateValueStrategy()
									.setAfterConvertValidator(new EmptyStringValidator()),
							null);
			bindingContext.bindValue(
					SWTObservables.observeText(tdesc, SWT.Modify),
					PojoObservables.observeValue(ref, "description")); //$NON-NLS-1$
		}
		bindingContext.updateTargets();
	}

	private ResourceDescriptor ref;
	private Text trefuri;
	private Text tparent;
	private Text ttype;
	private Text tcdate;
	private Text tid;
	private Text tname;
	private Text tdesc;

	private void loadReference(ResourceDescriptor rd) {
		try {
			ref = WSClientHelper.getReference(parent, rd);
			if (ref != null) {
				bind();
				bindingContext.updateTargets();
			}
		} catch (Exception e) {
			UIUtils.showError(e);
		}
	}
}
