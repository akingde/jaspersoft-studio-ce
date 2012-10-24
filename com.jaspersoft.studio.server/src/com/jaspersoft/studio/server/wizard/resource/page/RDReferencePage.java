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
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.utils.UIUtils;

public class RDReferencePage extends AResourcePage {

	public RDReferencePage(ANode parent, MReference resource) {
		super("rdreference", parent, resource);
		setTitle("Reference");
		setDescription("JasperServer reference");
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createReferenceTab(tabFolder);
	}

	protected void createReferenceTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("Reference");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, "Referenced Descriptor");

		trefuri = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		trefuri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bbrowse = new Button(composite, SWT.ARROW | SWT.DOWN);
		bbrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				Shell shell = Display.getDefault().getActiveShell();
				RepositoryDialog rd = 
						new RepositoryDialog(shell, 
						ServerManager.getMServerProfileCopy((MServerProfile)parent.getRoot())) {

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

		UIUtils.createLabel(cmp, "Parent Folder");
		tparent = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		tparent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(cmp, "Type");
		ttype = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(cmp, "Creation Date:");
		tcdate = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		tcdate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createSeparator(cmp, 2);

		UIUtils.createLabel(cmp, "ID");
		tid = new Text(cmp, SWT.BORDER);
		tid.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(cmp, "Name");
		tname = new Text(cmp, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(cmp, "Description");
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
				PojoObservables.observeValue(res.getValue(), "referenceUri"));
		if (ref != null) {
			bindingContext.bindValue(
					SWTObservables.observeText(tparent, SWT.NONE),
					PojoObservables.observeValue(ref, "parentFolder"));

			bindingContext.bindValue(
					SWTObservables.observeText(tcdate, SWT.NONE),
					PojoObservables.observeValue(ref, "creationDate"));

			bindingContext.bindValue(
					SWTObservables.observeText(ttype, SWT.NONE),
					PojoObservables.observeValue(ref, "wsType"));

			bindingContext
					.bindValue(
							SWTObservables.observeText(tid, SWT.Modify),
							PojoObservables.observeValue(ref, "name"),
							new UpdateValueStrategy()
									.setAfterConvertValidator(new EmptyStringValidator()),
							null);

			bindingContext
					.bindValue(
							SWTObservables.observeText(tname, SWT.Modify),
							PojoObservables.observeValue(ref, "label"),
							new UpdateValueStrategy()
									.setAfterConvertValidator(new EmptyStringValidator()),
							null);
			bindingContext.bindValue(
					SWTObservables.observeText(tdesc, SWT.Modify),
					PojoObservables.observeValue(ref, "description"));
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
