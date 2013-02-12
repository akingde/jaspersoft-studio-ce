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

import net.sf.jasperreports.eclipse.ui.validator.EmptyStringValidator;
import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.utils.UIUtils;

public class ResourcePageContent extends APageContent {

	public ResourcePageContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ResourcePageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.resource";
	}

	@Override
	public String getName() {
		return Messages.AResourcePage_title;
	}

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		UIUtils.createLabel(composite, Messages.AResourcePage_parentfolder);
		Text tparent = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		tparent.setLayoutData(gd);
		tparent.setEnabled(false);

		UIUtils.createLabel(composite, Messages.AResourcePage_type);
		Text ttype = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ttype.setEnabled(false);

		Button bisRef = new Button(composite, SWT.CHECK);
		bisRef.setText("is Reference");
		bisRef.setEnabled(false);

		UIUtils.createLabel(composite, Messages.AResourcePage_creationdate);
		Text tcdate = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		tcdate.setLayoutData(gd);
		tcdate.setEnabled(false);

		UIUtils.createSeparator(composite, 3);

		UIUtils.createLabel(composite, Messages.AResourcePage_id);
		final Text tid = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		tid.setLayoutData(gd);

		UIUtils.createLabel(composite, Messages.AResourcePage_name);
		final Text tname = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		tname.setLayoutData(gd);

		UIUtils.createLabel(composite, Messages.AResourcePage_description);
		Text tdesc = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 100;
		gd.horizontalSpan = 2;
		tdesc.setLayoutData(gd);

		final ResourceDescriptor rd = res.getValue();
		bindingContext.bindValue(SWTObservables.observeText(tparent, SWT.NONE),
				PojoObservables.observeValue(rd, "parentFolder")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables.observeText(tcdate, SWT.NONE),
				PojoObservables.observeValue(rd, "creationDate")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables.observeText(ttype, SWT.NONE),
				PojoObservables.observeValue(rd, "wsType")); //$NON-NLS-1$

		bindingContext.bindValue(SWTObservables.observeSelection(bisRef),
				PojoObservables.observeValue(rd, "isReference")); //$NON-NLS-1$

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

		return composite;
	}
}
