/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.utils.IPageCompleteListener;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;
import com.jaspersoft.studio.server.wizard.resource.ResourceWizard;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public abstract class ASelector {
	protected Button brRepo;
	protected Text jsRefDS;
	protected Button brLocal;
	protected Text jsLocDS;
	protected Button bLoc;
	protected Button bRef;
	protected AMResource res;
	protected ResourceDescriptor resRD;
	protected ANode parent;

	public Control createControls(Composite cmp, final ANode parent, final AMResource res) {
		this.res = res;
		this.parent = parent;
		if (res != null)
			this.resRD = res.getValue();

		Composite composite = new Composite(cmp, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(2, false));

		createRepository(composite);

		createLocal(composite);
		init();
		return composite;
	}

	protected boolean valid = true;
	private boolean refresh = false;

	protected void createRepository(Composite prnt) {
		brRepo = new Button(prnt, SWT.RADIO);
		brRepo.setText(Messages.SelectorQuery_selectfromrepository);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brRepo.setLayoutData(gd);
		brRepo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(0);
			}
		});

		jsRefDS = new Text(prnt, SWT.BORDER);
		jsRefDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		jsRefDS.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (!refresh) {
					final String uri = jsRefDS.getText().trim();
					Job job = new Job("Validating") {
						private ResourceDescriptor newrd;

						@Override
						protected IStatus run(IProgressMonitor monitor) {
							IStatus status = Status.OK_STATUS;
							try {
								ResourceDescriptor rd = createLocal((AMResource) null);
								rd.setUriString(uri);
								newrd = WSClientHelper.getResource(monitor, res.getWsClient(), rd, null);
								valid = newrd != null && isResCompatible(ResourceFactory.getResource(null, newrd, -1));

							} catch (Exception e) {
								valid = false;
								e.printStackTrace();
							} finally {
								monitor.done();
							}
							UIUtils.getDisplay().asyncExec(new Runnable() {

								@Override
								public void run() {
									if (valid)
										setRemoteResource(newrd, res, false);
									firePageComplete();
								}
							});
							return status;
						}
					};
					job.setPriority(Job.SHORT);
					job.setSystem(false);
					job.setUser(true);
					job.schedule();
				}
			}
		});
		InputHistoryCache.bindText(jsRefDS, this.getClass().getName());

		bRef = new Button(prnt, SWT.PUSH);
		bRef.setText("...");
		bRef.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MServerProfile msp = ServerManager.getMServerProfileCopy((MServerProfile) parent.getRoot());
				if (msp.isSupported(Feature.SEARCHREPOSITORY)) {
					ResourceDescriptor rd = FindResourceJob.doFindResource(msp, getIncludeTypes(), getExcludeTypes());
					if (rd != null)
						setRemoteResource(rd, parent, true);
				} else {
					RepositoryDialog rd = new RepositoryDialog(bRef.getShell(), msp) {

						@Override
						public boolean isResourceCompatible(AMResource r) {
							return isResCompatible(r);
						}
					};
					if (rd.open() == Dialog.OK) {
						AMResource rs = rd.getResource();
						if (rs != null)
							setRemoteResource(rs.getValue(), parent, true);
						valid = true;
					}
				}
			}
		});
	}

	private void setRemoteResource(ResourceDescriptor rd, ANode pnode, boolean modifyText) {
		ResourceDescriptor runit = resRD;
		try {
			rd = WSClientHelper.getResource(new NullProgressMonitor(), pnode, rd);
			if (runit.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT)) {
				rd.setIsReference(true);
				rd.setReferenceUri(rd.getUriString());
				rd.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$ //$NON-NLS-2$
				rd.setUriString(rd.getParentFolder() + "/" + rd.getName());//$NON-NLS-1$
				setupResource(rd);
				replaceChildren(rd);
				if (modifyText)
					jsRefDS.setText(rd.getReferenceUri());
			} else {
				setupResource(rd);
				replaceChildren(rd);
				if (modifyText)
					jsRefDS.setText(rd.getUriString());
			}
		} catch (Exception e1) {
			UIUtils.showError(e1);
		}
		firePageComplete();
	}

	public void resetResource() {
		replaceChildren(null);
		setEnabled(isReference(resRD) ? 0 : 1);
	}

	protected void replaceChildren(ResourceDescriptor rd) {
		ResourceDescriptor rdel = getResourceDescriptor(resRD);
		List<ResourceDescriptor> children = resRD.getChildren();
		if (rdel != null) {
			int index = children.indexOf(rdel);
			if (index >= 0)
				children.remove(index);
		}
		if (rd != null)
			children.add(rd);
	}

	protected abstract String[] getIncludeTypes();

	protected abstract String[] getExcludeTypes();

	protected abstract boolean isResCompatible(AMResource r);

	protected void createLocal(Composite prnt) {
		brLocal = new Button(prnt, SWT.RADIO);
		brLocal.setText(Messages.SelectorQuery_localresource);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		brLocal.setLayoutData(gd);
		brLocal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEnabled(1);
			}
		});

		jsLocDS = new Text(prnt, SWT.BORDER | SWT.READ_ONLY);
		jsLocDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bLoc = new Button(prnt, SWT.PUSH);
		bLoc.setText("..."); //$NON-NLS-1$
		bLoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ResourceDescriptor runit = resRD;
				ResourceDescriptor ref = getResourceDescriptor(runit);
				if (isReference(ref))
					ref = null;
				// boolean newref = false;
				AMResource r = null;
				if (ref != null) {
					ref = cloneResource(ref);
					r = ResourceFactory.getResource(null, ref, -1);
					if (!showLocalWizard(r, parent))
						return;
				} else {
					r = getLocalResource(res, runit, parent);
					if (r != null)
						ref = r.getValue();
					// newref = true;
				}
				if (r == null)
					return;
				ref.setUriString(ref.getParentFolder() + "/" + ref.getName()); //$NON-NLS-1$
				// if (newref)
				replaceChildren(ref);
				// else
				// ASelector.copyFields(getResourceDescriptor(runit),
				// r.getValue());
				// ASelector.copyFields(res.getValue(), ref);
				jsLocDS.setText(Misc.nvl(ref.getName()));
				firePageComplete();
			}
		});
	}

	protected AMResource getLocalResource(AMResource res, ResourceDescriptor runit, ANode pnode) {
		ResourceDescriptor ref = createLocal(res);
		ref.setIsNew(true);
		ref.setIsReference(false);
		ref.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$
		setupResource(ref);
		ref.setDirty(true);

		AMResource r = ResourceFactory.getResource(null, ref, -1);
		if (!showLocalWizard(r, pnode))
			return null;
		return r;
	}

	protected boolean showLocalWizard(AMResource r, ANode pnode) {
		ResourceWizard wizard = new ResourceWizard(pnode, r, true, true);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.create();
		return dialog.open() == Dialog.OK;
	}

	protected void setupResource(ResourceDescriptor rd) {

	}

	public static boolean isReference(ResourceDescriptor ref) {
		return ref != null && (ref.getIsReference() || ref.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE));
	}

	protected abstract ResourceDescriptor createLocal(AMResource res);

	protected void init() {
		setEnabled(isReference(getResourceDescriptor(resRD)) ? 0 : 1);
	}

	public boolean isPageComplete() {
		boolean val = getResourceDescriptor(resRD) != null;
		if (val && brRepo.getSelection())
			return valid;
		return val;
	}

	private List<IPageCompleteListener> listeners = new ArrayList<IPageCompleteListener>();

	public void firePageComplete() {
		boolean completed = isPageComplete();
		for (IPageCompleteListener l : listeners)
			l.pageCompleted(completed);
	}

	public void addPageCompleteListener(IPageCompleteListener listener) {
		listeners.add(listener);
	}

	public void removePageCompleteListener(IPageCompleteListener listener) {
		listeners.remove(listener);
	}

	protected abstract ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru);

	protected void setEnabled(int pos) {
		if (refresh)
			return;
		refresh = true;
		bRef.setEnabled(false);
		jsRefDS.setEnabled(false);

		bLoc.setEnabled(false);
		jsLocDS.setEnabled(false);

		brRepo.setSelection(false);
		brLocal.setSelection(false);

		jsRefDS.setText(""); //$NON-NLS-1$
		jsLocDS.setText(""); //$NON-NLS-1$

		ResourceDescriptor r = getResourceDescriptor(resRD);
		if (r == null && resRD != null) {
			for (ResourceDescriptor rd : resRD.getChildren()) {
				if (rd != null && rd.getWsType() != null && rd.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE)) {
					r = rd;
					pos = 0;
				}
			}
		}
		switch (pos) {
		case 0:
			bRef.setEnabled(true);
			brRepo.setSelection(true);
			jsRefDS.setEnabled(true);
			if (isReference(r))
				jsRefDS.setText(Misc.nvl(r.getReferenceUri()));
			break;
		case 1:
			brLocal.setSelection(true);
			bLoc.setEnabled(true);
			// jsLocDS.setEnabled(true);
			if (r != null && !r.getIsReference() && !r.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE))
				jsLocDS.setText(Misc.nvl(r.getName()));
			break;
		}
		refresh = false;
	}

	public static void copyFields(ResourceDescriptor rd, ResourceDescriptor rnew) {
		rnew.setParameters(rd.getParameters());
		rnew.setProperties(rd.getProperties());
		rnew.setChildren(rd.getChildren());

		rnew.setQueryData(rd.getQueryData());
		rnew.setQueryValueColumn(rd.getQueryValueColumn());
		rnew.setQueryVisibleColumns(rd.getQueryVisibleColumns());

		rnew.setListOfValues(rd.getListOfValues());

		rnew.setPattern(rd.getPattern());
		rnew.setMinValue(rd.getMinValue());
		rnew.setMaxValue(rd.getMaxValue());
		rnew.setStrictMin(rd.isStrictMin());
		rnew.setStrictMax(rd.isStrictMax());
		rnew.setDataType(rd.getDataType());

		rnew.setJndiName(rd.getJndiName());

		rnew.setBeanMethod(rd.getBeanMethod());
		rnew.setBeanName(rd.getBeanName());

		rnew.setDriverClass(rd.getDriverClass());
		rnew.setUsername(rd.getUsername());
		rnew.setPassword(rd.getPassword());
		rnew.setConnectionUrl(rd.getConnectionUrl());

		rnew.setName(rd.getName());
		rnew.setLabel(rd.getLabel());
		rnew.setDescription(rd.getDescription());

		rnew.setData(rd.getData());
		rnew.setHasData(rd.getHasData());
		rnew.setSql(rd.getSql());
	}

	public static ResourceDescriptor cloneResource(ResourceDescriptor rd) {
		ResourceDescriptor rnew = new ResourceDescriptor();
		copyFields(rd, rnew);

		rnew.setIsNew(rd.getIsNew());
		rnew.setIsReference(rd.getIsReference());

		rnew.setUriString(rd.getUriString());
		rnew.setParentFolder(rd.getParentFolder());
		rnew.setDataSourceType(rd.getDataSourceType());
		rnew.setWsType(rd.getWsType());

		return rnew;
	}
}
