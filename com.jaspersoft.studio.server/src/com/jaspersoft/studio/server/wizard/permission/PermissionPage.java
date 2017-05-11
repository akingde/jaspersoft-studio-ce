/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.permission;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.dto.permissions.RepositoryPermission;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.IConnection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class PermissionPage extends WizardPage {
	private AMResource res;
	private PermissionOptions optRole = new PermissionOptions();
	private PermissionOptions optUser = new PermissionOptions();
	private Composite cmpUser;
	private Composite cmpRole;
	private CTabFolder tabFolder;
	private ScrolledComposite scRole;
	private ScrolledComposite scUser;

	protected PermissionPage(AMResource res) {
		super("permissionresource"); //$NON-NLS-1$
		setTitle(Messages.PermissionPage_0);
		setDescription(Messages.PermissionPage_1);
		this.res = res;
		optRole.setRecipientTypeUser(false);
		optUser.setRecipientTypeUser(true);
	}

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout());

		tabFolder = new CTabFolder(cmp, SWT.FLAT | SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite c = new Composite(tabFolder, SWT.BORDER_DASH);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		c.setLayout(layout);
		Label lbl = new Label(c, SWT.NONE);
		lbl.setText(Messages.PermissionPage_7);
		GridData gd = new GridData(GridData.END | GridData.HORIZONTAL_ALIGN_END
				| GridData.VERTICAL_ALIGN_CENTER);
		gd.horizontalIndent = 200;
		lbl.setLayoutData(gd);

		final Text tSearch = new Text(c, SWT.SEARCH | SWT.ICON_SEARCH);
		gd = new GridData(GridData.END | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 150;
		tSearch.setLayoutData(gd);
		tSearch.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				tSearch.selectAll();
			}
		});
		tSearch.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				List<RepositoryPermission> perms = tabFolder
						.getSelectionIndex() == 0 ? permsUser : permsRoles;
				if (perms != null) {
					String txt = tSearch.getText().toLowerCase();
					if (Misc.isNullOrEmpty(txt))
						showPermissions(perms);
					else {
						List<RepositoryPermission> newPerms = new ArrayList<RepositoryPermission>();
						for (RepositoryPermission rp : perms) {
							if (getUserName(rp).toLowerCase().contains(txt))
								newPerms.add(rp);
						}
						showPermissions(newPerms);
					}
				}
			}
		});

		final int toolbarHeight = tSearch.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		tabFolder
				.setTabHeight(Math.max(toolbarHeight, tabFolder.getTabHeight()));
		tabFolder.setTopRight(c, SWT.FILL);

		createByUser(tabFolder);
		createByRole(tabFolder);

		setControl(cmp);

		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getPermissions();
			}
		});

		tabFolder.setSelection(1);
		getPermissions();
	}

	private List<RepositoryPermission> permsUser;
	private List<RepositoryPermission> permsRoles;

	private void getPermissions() {
		try {
			getContainer().run(false, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.PermissionPage_4,
							IProgressMonitor.UNKNOWN);
					try {
						IConnection wsClient = res.getWsClient();
						if (tabFolder.getSelectionIndex() == 0) {
							if (permsUser == null)
								permsUser = wsClient.getPermissions(
										res.getValue(), monitor, optUser);
							showPermissions(permsUser);
						} else {
							if (permsRoles == null)
								permsRoles = wsClient.getPermissions(
										res.getValue(), monitor, optRole);
							showPermissions(permsRoles);
						}
					} catch (Exception e) {
						UIUtils.showError(e);
					}
				}

			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
	}

	protected void showPermissions(List<RepositoryPermission> perms) {
		Composite cmp = tabFolder.getSelectionIndex() == 0 ? cmpUser : cmpRole;
		ScrolledComposite sc = tabFolder.getSelectionIndex() == 0 ? scUser
				: scRole;

		for (Control c : cmp.getChildren())
			c.dispose();
		for (RepositoryPermission rp : perms)
			createPair(cmp, rp);
		cmp.layout();
		sc.setMinSize(cmp.computeSize(SWT.DEFAULT, SWT.DEFAULT).x,
				cmp.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	}

	private void createByUser(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.PermissionPage_5);

		scUser = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		scUser.setLayoutData(new GridData(GridData.FILL_BOTH));

		cmpUser = new Composite(scUser, SWT.NONE);
		cmpUser.setLayout(new GridLayout(2, true));

		scUser.setContent(cmpUser);
		// Set the minimum size
		scUser.setMinSize(cmpUser.computeSize(SWT.DEFAULT, SWT.DEFAULT).x,
				cmpUser.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		// Expand both horizontally and vertically
		scUser.setExpandHorizontal(true);
		scUser.setExpandVertical(true);

		bptab.setControl(scUser);
	}

	private void createByRole(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.PermissionPage_6);

		scRole = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		scRole.setLayoutData(new GridData(GridData.FILL_BOTH));

		cmpRole = new Composite(scRole, SWT.NONE);
		cmpRole.setLayout(new GridLayout(2, true));

		scRole.setContent(cmpRole);
		// Set the minimum size
		scRole.setMinSize(cmpRole.computeSize(SWT.DEFAULT, SWT.DEFAULT).x,
				cmpRole.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		// Expand both horizontally and vertically
		scRole.setExpandHorizontal(true);
		scRole.setExpandVertical(true);

		bptab.setControl(scRole);
	}

	private int[] masks = new int[] { -1, 0, 1, 2, 6, 18, 30, 32 };

	private void createPair(Composite cmp, final RepositoryPermission perm) {
		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(getUserName(perm));
		lbl.setToolTipText(perm.getRecipient());

		final Combo cperm = new Combo(cmp, SWT.READ_ONLY);
		String[] items = new String[] { Messages.PermissionPage_3,
				Messages.PermissionPage_8, Messages.PermissionPage_9,
				Messages.PermissionPage_10, Messages.PermissionPage_11,
				Messages.PermissionPage_12, Messages.PermissionPage_13,
				Messages.PermissionPage_14 };
		int sel = -1;
		for (int i = 1; i < masks.length; i++)
			if (perm.getMask() == masks[i]) {
				sel = i;
				break;
			}
		if (perm.getUri() == null
				|| !perm.getUri().equals(res.getValue().getUriString()))
			items[sel] += " *"; //$NON-NLS-1$
		cperm.setItems(items);
		cperm.select(Math.max(sel, 0));
		cperm.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
				updatePermission(perm, cperm);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		cperm.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				updatePermission(perm, cperm);
			}
		});
	}

	protected String getUserName(RepositoryPermission perm) {
		String name = perm.getRecipient();
		name = name.substring(name.lastIndexOf("/") + 1); //$NON-NLS-1$
		return name;
	}

	public void setPermissions() {
		try {
			getContainer().run(false, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.PermissionPage_15,
							IProgressMonitor.UNKNOWN);
					boolean b = tabFolder.getSelectionIndex() == 0;
					try {
						if (permsUser != null)
							permsUser = res.getWsClient()
									.setPermissions(res.getValue(), permsUser,
											optUser, monitor);
						if (monitor.isCanceled())
							return;
						if (permsRoles != null)
							permsRoles = res.getWsClient().setPermissions(
									res.getValue(), permsRoles, optRole,
									monitor);
					} catch (Exception e) {
						UIUtils.showError(e);
					}
					showPermissions(b ? permsUser : permsRoles);
				}

			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
	}

	protected void updatePermission(final RepositoryPermission perm,
			final Combo cperm) {
		if (cperm.getSelectionIndex() == -1)
			perm.setUri(null);
		else
			perm.setUri(res.getValue().getUriString());
		int indx = cperm.getSelectionIndex();
		if (indx >= 0 && indx < masks.length)
			perm.setMask(masks[indx]);
	}
}
