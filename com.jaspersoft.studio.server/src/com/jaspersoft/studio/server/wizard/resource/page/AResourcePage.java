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
import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class AResourcePage extends WizardPage {
	protected ANode parent;
	protected MResource res;
	protected DataBindingContext bindingContext;

	public AResourcePage(String pageName, ANode parent, MResource resource) {
		super(pageName);
		setTitle(Messages.AResourcePage_title);
		setDescription(Messages.AResourcePage_windowdescription);
		this.res = resource;
		this.parent = parent;
	}

	public void createControl(Composite parent) {
		bindingContext = new DataBindingContext();
		WizardPageSupport.create(this, bindingContext);

		TabFolder tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTabs(tabFolder);
		setControl(tabFolder);
	}

	protected void createTabs(TabFolder tabFolder) {
		createGeneralTab(tabFolder);
	}

	protected void createGeneralTab(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.AResourcePage_tabitemgeneral);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, Messages.AResourcePage_parentfolder);
		Text tparent = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tparent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tparent.setEnabled(false);

		UIUtils.createLabel(composite, Messages.AResourcePage_type);
		Text ttype = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ttype.setEnabled(false);

		UIUtils.createLabel(composite, Messages.AResourcePage_creationdate);
		Text tcdate = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tcdate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tcdate.setEnabled(false);

		UIUtils.createSeparator(composite, 2);

		UIUtils.createLabel(composite, Messages.AResourcePage_id);
		final Text tid = new Text(composite, SWT.BORDER);
		tid.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, Messages.AResourcePage_name);
		final Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, Messages.AResourcePage_description);
		Text tdesc = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 100;
		tdesc.setLayoutData(gd);

		final ResourceDescriptor rd = res.getValue();
		bindingContext.bindValue(SWTObservables.observeText(tparent, SWT.NONE),
				PojoObservables.observeValue(rd, "parentFolder")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables.observeText(tcdate, SWT.NONE),
				PojoObservables.observeValue(rd, "creationDate")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables.observeText(ttype, SWT.NONE),
				PojoObservables.observeValue(rd, "wsType")); //$NON-NLS-1$

		bindingContext.bindValue(
				SWTObservables.observeText(tid, SWT.Modify),
				PojoObservables.observeValue(rd, "name"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setAfterConvertValidator(new IDStringValidator()),
				null);

		bindingContext.bindValue(
				SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(rd, "label"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator()),
				null);
		bindingContext.bindValue(SWTObservables.observeText(tdesc, SWT.Modify),
				PojoObservables.observeValue(rd, "description")); //$NON-NLS-1$

		tid.setEditable(rd.getIsNew());
		if (rd.getIsNew()) {
			rd.setLabel(rd.getName());
			tid.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					tname.setText(tid.getText());
				}
			});
		}
		bindingContext.updateTargets();
		bindingContext.updateModels();
	}

}
