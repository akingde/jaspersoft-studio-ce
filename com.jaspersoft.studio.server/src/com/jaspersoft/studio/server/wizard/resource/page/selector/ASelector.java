package com.jaspersoft.studio.server.wizard.resource.page.selector;

import java.util.ArrayList;
import java.util.List;

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
import com.jaspersoft.studio.server.utils.IPageCompleteListener;
import com.jaspersoft.studio.server.wizard.resource.ResourceWizard;
import com.jaspersoft.studio.utils.Misc;

public abstract class ASelector {
	protected Button brRepo;
	protected Text jsRefDS;
	protected Button brLocal;
	protected Text jsLocDS;
	protected Button bLoc;
	protected Button bRef;
	protected MResource res;

	public void createControls(Composite cmp, final ANode parent, final MResource res) {
		this.res = res;

		Composite composite = new Composite(cmp, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(2, false));

		createRepository(composite, parent);

		createLocal(composite, parent);
		init();
	}

	protected void createRepository(Composite parent, final ANode pnode) {
		brRepo = new Button(parent, SWT.RADIO);
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

		jsRefDS = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		jsRefDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bRef = new Button(parent, SWT.PUSH);
		bRef.setText("...");
		bRef.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RepositoryDialog rd = new RepositoryDialog(bRef.getShell(), ServerManager.getMServerProfileCopy((MServerProfile) pnode.getRoot())) {

					@Override
					public boolean isResourceCompatible(MResource r) {
						return isResCompatible(r);
					}
				};
				if (rd.open() == Dialog.OK) {
					MResource rs = rd.getResource();
					if (rs != null) {
						ResourceDescriptor runit = res.getValue();
						try {
							ResourceDescriptor ref = rs.getValue();
							ref = WSClientHelper.getResource(pnode, ref);
							ref.setIsReference(false);
							ref.setReferenceUri(ref.getUriString());
							ref.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$
							ref.setUriString(ref.getParentFolder() + "/" //$NON-NLS-1$
									+ ref.getName());
							ref.setWsType(ResourceDescriptor.TYPE_REFERENCE);
							replaceChildren(res, ref);

							jsRefDS.setText(ref.getReferenceUri());
						} catch (Exception e1) {
							UIUtils.showError(e1);
						}
					}
					firePageComplete();
				}
			}
		});
	}

	public void resetResource() {
		replaceChildren(res, null);
		setEnabled(isReference(res.getValue()) ? 0 : 1);
	}

	protected void replaceChildren(final MResource res, ResourceDescriptor rd) {
		ResourceDescriptor rdel = getResourceDescriptor(res.getValue());
		List<ResourceDescriptor> children = res.getValue().getChildren();
		if (rdel != null) {
			int index = children.indexOf(rdel);
			if (index >= 0)
				children.remove(index);
		}
		if (rd != null)
			children.add(rd);
	}

	protected abstract boolean isResCompatible(MResource r);

	protected void createLocal(Composite parent, final ANode pnode) {
		brLocal = new Button(parent, SWT.RADIO);
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

		jsLocDS = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		jsLocDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		bLoc = new Button(parent, SWT.PUSH);
		bLoc.setText("..."); //$NON-NLS-1$
		bLoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ResourceDescriptor runit = res.getValue();
				ResourceDescriptor ref = getResourceDescriptor(runit);
				if (isReference(ref))
					ref = null;
				boolean newref = false;
				if (ref != null)
					ref = cloneResource(ref);
				else {
					ref = createLocal(res);
					ref.setIsNew(true);
					ref.setIsReference(false);
					ref.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$

					newref = true;
				}
				MResource r = ResourceFactory.getResource(null, ref, -1);
				ResourceWizard wizard = new ResourceWizard(pnode, r, true);
				WizardDialog dialog = new WizardDialog(bLoc.getShell(), wizard);
				dialog.create();
				if (dialog.open() != Dialog.OK)
					return;
				ref.setUriString(ref.getParentFolder() + "/" + ref.getName()); //$NON-NLS-1$
				if (newref)
					replaceChildren(res, ref);
				else
					ASelector.copyFields(ref, r.getValue());
				// ASelector.copyFields(res.getValue(), ref);
				jsLocDS.setText(Misc.nvl(ref.getName()));
				firePageComplete();
			}
		});
	}

	public static boolean isReference(ResourceDescriptor ref) {
		return ref != null && (ref.getIsReference() || ref.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE));
	}

	protected abstract ResourceDescriptor createLocal(MResource res);

	protected void init() {
		setEnabled(isReference(getResourceDescriptor(res.getValue())) ? 0 : 1);
	}

	public boolean isPageComplete() {
		return getResourceDescriptor(res.getValue()) != null;
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
		bRef.setEnabled(false);
		jsRefDS.setEnabled(false);

		bLoc.setEnabled(false);
		jsLocDS.setEnabled(false);

		brRepo.setSelection(false);
		brLocal.setSelection(false);

		jsRefDS.setText(""); //$NON-NLS-1$
		jsLocDS.setText(""); //$NON-NLS-1$

		ResourceDescriptor r = getResourceDescriptor(res.getValue());
		switch (pos) {
		case 0:
			bRef.setEnabled(true);
			brRepo.setSelection(true);
			// jsRefDS.setEnabled(true);
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
	}

	public static void copyFields(ResourceDescriptor rd, ResourceDescriptor rnew) {
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

		rnew.setParameters(rd.getParameters());
		rnew.setProperties(rd.getProperties());
		rnew.setChildren(rd.getChildren());
	}

	public static ResourceDescriptor cloneResource(ResourceDescriptor rd) {
		ResourceDescriptor rnew = new ResourceDescriptor();
		rnew.setIsNew(rd.getIsNew());
		rnew.setIsReference(rd.getIsReference());
		rnew.setName(rd.getName());
		rnew.setLabel(rd.getLabel());
		rnew.setDescription(rd.getDescription());
		rnew.setUriString(rd.getUriString());
		rnew.setParentFolder(rd.getParentFolder());
		rnew.setDataSourceType(rd.getDataSourceType());
		rnew.setWsType(rd.getWsType());

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

		rnew.setParameters(rd.getParameters());
		rnew.setProperties(rd.getProperties());
		rnew.setChildren(rd.getChildren());

		return rnew;
	}
}
