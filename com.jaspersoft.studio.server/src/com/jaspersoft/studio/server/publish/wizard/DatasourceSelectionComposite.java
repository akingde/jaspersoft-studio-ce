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
package com.jaspersoft.studio.server.publish.wizard;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.wizard.resource.AddResourceWizard;
import com.jaspersoft.studio.server.wizard.resource.ResourceWizard;
import com.jaspersoft.studio.server.wizard.resource.page.selector.ASelector;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.utils.Misc;

/**
 * This widget is used to select the data source that will be associated to a
 * report unit when created in the remote JasperServer repository.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DatasourceSelectionComposite extends Composite {

	private MResource res;
	private ANode parent;

	// Widgets stuff
	private Text textLocalDS;
	private Text textDSFromRepo;
	private Button btnSelectDSFromRepo;
	private Button btnSelectLocalDS;
	private Button rbDSFromRepo;
	private Button rbLocalDS;
	private Button rbNoDS;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DatasourceSelectionComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout(2, false);
		setLayout(gridLayout);

		rbDSFromRepo = new Button(this, SWT.RADIO);
		rbDSFromRepo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(SelectorDatasource.SelectionType.REMOTE_DATASOURCE);
			}
		});
		rbDSFromRepo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		rbDSFromRepo.setText(Messages.DatasourceSelectionComposite_FromRepository);

		textDSFromRepo = new Text(this, SWT.BORDER);
		textDSFromRepo.setEnabled(false);
		textDSFromRepo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		btnSelectDSFromRepo = new Button(this, SWT.ARROW | SWT.DOWN);
		btnSelectDSFromRepo.setEnabled(false);
		GridData gd_btnSelectDSFromRepo = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnSelectDSFromRepo.widthHint = 20;
		btnSelectDSFromRepo.setLayoutData(gd_btnSelectDSFromRepo);
		btnSelectDSFromRepo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectDatasourceFromRepository();
			}
		});

		rbLocalDS = new Button(this, SWT.RADIO);
		rbLocalDS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(SelectorDatasource.SelectionType.LOCAL_DATASOURCE);
			}
		});
		rbLocalDS.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		rbLocalDS.setText(Messages.DatasourceSelectionComposite_LocalDatasource);

		textLocalDS = new Text(this, SWT.BORDER);
		textLocalDS.setEnabled(false);
		textLocalDS.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		btnSelectLocalDS = new Button(this, SWT.ARROW | SWT.DOWN);
		btnSelectLocalDS.setEnabled(false);
		GridData gd_btnSelectLocalDS = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnSelectLocalDS.widthHint = 20;
		btnSelectLocalDS.setLayoutData(gd_btnSelectLocalDS);
		btnSelectLocalDS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLocalDatasource();
			}
		});

		rbNoDS = new Button(this, SWT.RADIO);
		rbNoDS.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		rbNoDS.setSelection(true);
		rbNoDS.setText(Messages.DatasourceSelectionComposite_NoDatasource);
		rbNoDS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeDatasource(res);
				setEnabled(SelectorDatasource.SelectionType.NO_DATASOURCE);
			}
		});

	}

	/**
	 * Configures the information needed to correctly use the datasource selection
	 * widget.
	 * 
	 * @param parent
	 *          the parent anode from which retrieve a {@link MServerProfile}
	 * @param resource
	 *          the resource for which we are configuring the datasource
	 */
	public void configurePage(ANode parent, MResource resource) {
		this.parent = parent;
		this.res = resource;

		ResourceDescriptor r = SelectorDatasource.getDatasource(res.getValue());
		if (r != null) {
			if (r.getIsReference()) {
				setEnabled(SelectorDatasource.SelectionType.REMOTE_DATASOURCE);
			} else {
				setEnabled(SelectorDatasource.SelectionType.LOCAL_DATASOURCE);
			}
		} else {
			setEnabled(SelectorDatasource.SelectionType.NO_DATASOURCE);
		}
	}

	/*
	 * Enables (and resets) the internal status of the widget depending on the
	 * type of datasource we are creating/modifying.
	 */
	private void setEnabled(SelectorDatasource.SelectionType type) {
		// Disable and reset all the widgets
		btnSelectDSFromRepo.setEnabled(false);
		textDSFromRepo.setEnabled(false);
		textDSFromRepo.setText(""); //$NON-NLS-1$

		btnSelectLocalDS.setEnabled(false);
		textLocalDS.setEnabled(false);
		textLocalDS.setText(""); //$NON-NLS-1$

		rbDSFromRepo.setSelection(false);
		rbLocalDS.setSelection(false);
		rbNoDS.setSelection(false);

		// Enable and check all the resource related information
		ResourceDescriptor r = SelectorDatasource.getDatasource(res.getValue());
		switch (type) {
		case REMOTE_DATASOURCE:
			rbDSFromRepo.setSelection(true);
			btnSelectDSFromRepo.setEnabled(true);
			// textDSFromRepo.setEnabled(true);
			if (r != null)
				textDSFromRepo.setText(Misc.nvl(r.getReferenceUri()));
			break;
		case LOCAL_DATASOURCE:
			rbLocalDS.setSelection(true);
			btnSelectLocalDS.setEnabled(true);
			// textLocalDS.setEnabled(true);
			if (r != null)
				textLocalDS.setText(Misc.nvl(r.getName()));
			break;
		case NO_DATASOURCE:
			rbNoDS.setSelection(true);
			break;
		}
	}

	/*
	 * Performs the selection of a local datasource. Shows a dialog where the user
	 * can choose the right one.
	 */
	private void selectLocalDatasource() {
		ResourceDescriptor runit = res.getValue();
		ResourceDescriptor ref = SelectorDatasource.getDatasource(runit);
		if (ASelector.isReference(ref))
			ref = null;

		Shell shell = Display.getDefault().getActiveShell();
		if (ref == null) {
			AddResourceWizard wizard = new AddResourceWizard(res);
			wizard.setOnlyDatasource(true);
			WizardDialog dialog = new WizardDialog(shell, wizard);
			dialog.create();
			if (dialog.open() != Dialog.OK)
				return;
			ref = wizard.getResource().getValue();

			ref.setIsReference(false);
			ref.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$
			// ref.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
			ref.setUriString(ref.getParentFolder() + "/" + ref.getName());//$NON-NLS-1$

			SelectorDatasource.replaceDatasource(res, ref);
		} else {
			MResource r = ResourceFactory.getResource(null, ASelector.cloneResource(ref), -1);
			ResourceWizard wizard = new ResourceWizard(parent, r, true);
			WizardDialog dialog = new WizardDialog(shell, wizard);
			dialog.create();
			if (dialog.open() != Dialog.OK)
				return;
			ASelector.copyFields(r.getValue(), ref);
		}
		textLocalDS.setText(Misc.nvl(ref.getUriString()));
	}

	/*
	 * Performs the selection of a datasource from a remote repository. Shows a
	 * dialog where the user can choose the right one.
	 */
	private void selectDatasourceFromRepository() {
		// N.B: remember we need to pass a fresh new MServerProfile info in
		// order
		// to avoid problem of refreshing (children/parent relationship changes)
		// due to tree viewer node expansion...
		RepositoryDialog rd = new RepositoryDialog(Display.getDefault().getActiveShell(), ServerManager.getMServerProfileCopy((MServerProfile) parent.getRoot())) {
			@Override
			public boolean isResourceCompatible(MResource r) {
				return SelectorDatasource.isDatasource(r.getValue());
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
					ref.setReferenceUri(ref.getUriString());
					ref.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$ //$NON-NLS-2$
					ref.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
					ref.setUriString(ref.getParentFolder() + "/" //$NON-NLS-1$
							+ ref.getName());
					SelectorDatasource.replaceDatasource(res, ref);

					textDSFromRepo.setText(ref.getReferenceUri());
				} catch (Exception e1) {
					UIUtils.showError(e1);
				}
			}
		}
	}

	/*
	 * Remove a previous existing datasource from the MResource instance
	 * specified.
	 */
	private void removeDatasource(final MResource res) {
		ResourceDescriptor rdel = SelectorDatasource.getDatasource(res.getValue());
		if (rdel != null)
			res.getValue().getChildren().remove(rdel);
	}

}
