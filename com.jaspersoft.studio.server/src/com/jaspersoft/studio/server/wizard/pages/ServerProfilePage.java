/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.wizard.pages;

import net.sf.jasperreports.eclipse.ui.validator.EmptyStringValidator;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.compatibility.dialog.VersionCombo;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.wizard.validator.URLValidator;
import com.jaspersoft.studio.utils.SWTResourceManager;
import com.jaspersoft.studio.utils.UIUtils;

public class ServerProfilePage extends WizardPage {
	private MServerProfile sprofile;

	public ServerProfilePage(MServerProfile sprofile) {
		super("serverprofilepage"); //$NON-NLS-1$
		setTitle(Messages.ServerProfilePage_1);
		setDescription(Messages.ServerProfilePage_2);
		this.sprofile = sprofile;
	}

	public void createControl(final Composite parent) {
		DataBindingContext dbc = new DataBindingContext();
		WizardPageSupport.create(this, dbc);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		new Label(composite, SWT.NONE).setText(Messages.ServerProfilePage_3);
		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		new Label(composite, SWT.NONE).setText(Messages.ServerProfilePage_4);

		Text turl = new Text(composite, SWT.BORDER);
		turl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Group gr = new Group(composite, SWT.NONE);
		gr.setText(Messages.ServerProfilePage_8);
		gr.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gr.setLayoutData(gd);

		new Label(gr, SWT.NONE).setText(Messages.ServerProfilePage_9);
		Text torg = new Text(gr, SWT.BORDER);
		torg.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(gr, SWT.NONE).setText(Messages.ServerProfilePage_10);
		Text tuser = new Text(gr, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(gr, SWT.NONE).setText(Messages.ServerProfilePage_11);
		Text tpass = new Text(gr, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Section expcmp = new Section(composite,
				ExpandableComposite.TREE_NODE);
		expcmp.setTitleBarForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		UIUtils.setBold(expcmp);
		expcmp.setText("Advanced Settings");
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		expcmp.setLayoutData(gd);
		expcmp.setExpanded(true);

		Composite cmp = new Composite(expcmp, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		expcmp.setClient(cmp);
		expcmp.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				parent.getParent().layout(true);
			}
		});
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				expcmp.setExpanded(false);
			}
		});

		new Label(cmp, SWT.NONE).setText("JasperReports Library Version");

		VersionCombo cversion = new VersionCombo(cmp);
		cversion.setVersion(JRXmlWriterHelper.LAST_VERSION);

		new Label(cmp, SWT.NONE).setText("Connection Timeout [ms]");

		Text ttimeout = new Text(cmp, SWT.BORDER);
		ttimeout.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bchunked = new Button(cmp, SWT.CHECK);
		bchunked.setText("Http Chuncked Requests");

		ServerProfile value = sprofile.getValue();
		dbc.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(value, "name"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator() {
							@Override
							public IStatus validate(Object value) {
								IStatus s = super.validate(value);
								if (s.equals(Status.OK_STATUS)
										&& !ServerManager.isUniqueName(
												sprofile, (String) value)) {
									return ValidationStatus
											.error(Messages.ServerProfilePage_13);
								}
								return s;
							}
						}), null);
		dbc.bindValue(SWTObservables.observeText(turl, SWT.Modify),
				PojoObservables.observeValue(value, "url"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setAfterConvertValidator(new URLValidator()), null);
		dbc.bindValue(SWTObservables.observeText(torg, SWT.Modify),
				PojoObservables.observeValue(value, "organisation")); //$NON-NLS-1$
		dbc.bindValue(
				SWTObservables.observeText(tuser, SWT.Modify),
				PojoObservables.observeValue(value, "user"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setAfterConvertValidator(new EmptyStringValidator()),
				null);
		dbc.bindValue(SWTObservables.observeText(tpass, SWT.Modify),
				PojoObservables.observeValue(value, "pass")); //$NON-NLS-1$

		dbc.bindValue(SWTObservables.observeText(ttimeout, SWT.Modify),
				PojoObservables.observeValue(value, "timeout")); //$NON-NLS-1$

		dbc.bindValue(SWTObservables.observeSelection(bchunked),
				PojoObservables.observeValue(value, "chunked")); //$NON-NLS-1$

		dbc.bindValue(SWTObservables.observeText(cversion.getControl()),
				PojoObservables.observeValue(new Proxy(value), "jrVersion")); //$NON-NLS-1$
	}

	public class Proxy {
		private ServerProfile sp;

		public Proxy(ServerProfile sp) {
			this.sp = sp;
		}

		public void setJrVersion(String v) {
			sp.setJrVersion(VersionCombo.getJrVersion(v));
		}

		public String getJrVersion() {
			return VersionCombo.getLabelVersion(sp.getJrVersion());
		}
	}

	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem()
				.displayHelp("com.jaspersoft.studio.doc.jaspersoftserver");
	}
}
