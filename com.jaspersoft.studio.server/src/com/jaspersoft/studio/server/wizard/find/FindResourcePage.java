/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.find;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.SystemUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientFile.FileType;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryComposite;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;
import com.jaspersoft.studio.utils.Misc;

import jersey.repackaged.com.google.common.collect.BiMap;
import jersey.repackaged.com.google.common.collect.HashBiMap;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class FindResourcePage extends WizardPage {
	private FinderUI finderUI;
	private String[] itypes;
	private String[] etypes;
	private String name;
	private boolean containedResource = false;

	public void setDefaultName(String name) {
		this.name = name;
	}

	public void setFilterTypes(String[] in, String[] excl) {
		this.itypes = in;
		this.etypes = excl;

		List<String> tps = finderUI.getTypes();
		tps.clear();
		if (itypes != null)
			for (String t : itypes)
				tps.add(t);
		if (etypes != null)
			for (String t : etypes)
				if (tps.contains(t))
					tps.remove(t);
	}

	private MServerProfile sp;

	protected FindResourcePage(MServerProfile sp, boolean containedResource) {
		super("findresource"); //$NON-NLS-1$
		this.containedResource = containedResource;
		setTitle(Messages.FindResourcePage_1);
		setDescription(Messages.FindResourcePage_2);
		finderUI = new FinderUI(ServerManager.getMServerProfileCopy(sp));
		this.sp = sp;
	}

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		setControl(cmp);

		new Label(cmp, SWT.NONE).setText(Messages.FindResourcePage_3);

		txt = new Text(cmp, SWT.BORDER);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				tabFolder.setSelection(1);
				doSearch();
			}
		});

		Button b = new Button(cmp, SWT.PUSH);
		b.setText(Messages.FindResourcePage_4);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(1);
				doSearch();
			}
		});
		if (itypes == null && etypes == null) {
			Section expcmp = new Section(cmp, ExpandableComposite.TREE_NODE);
			expcmp.setText(Messages.FindResourcePage_5);
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.horizontalSpan = 3;
			gd.verticalIndent = 3;
			expcmp.setLayoutData(gd);
			expcmp.setExpanded(false);

			Composite scmp = new Composite(expcmp, SWT.NONE);
			scmp.setLayout(new GridLayout(3, false));

			Map<String, String> typeNames = createAllFilters(scmp);

			createDatasourceFilters(scmp, typeNames);
			createFileTypeFilters(scmp, typeNames);

			expcmp.setClient(scmp);
			expcmp.addExpansionListener(new ExpansionAdapter() {
				public void expansionStateChanged(ExpansionEvent e) {
					UIUtils.relayoutDialog(getShell(), 0, -1);
				}
			});
		}

		tabFolder = new CTabFolder(cmp, SWT.FLAT | SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		gd.heightHint = 300;
		gd.widthHint = 500;
		tabFolder.setLayoutData(gd);

		createTreeView(tabFolder);
		createListView(tabFolder);

		tabFolder.setSelection(0);

		if (!Misc.isNullOrEmpty(name)) {
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					txt.setText(name);
					// doSearch();
				}
			});
		}

		txt.setFocus();
		setPageComplete(false);
	}

	private Map<String, String> createAllFilters(Composite scmp) {
		Composite dsCmp = new Composite(scmp, SWT.NONE);
		dsCmp.setLayout(new GridLayout(2, false));
		dsCmp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		ball = new Button(dsCmp, SWT.CHECK);
		ball.setText(Messages.FindResourcePage_6);
		ball.setSelection(true);
		ball.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean sel = ball.getSelection();
				for (Button b : typesMap.values())
					b.setSelection(sel);
				bds.setSelection(sel);
				bft.setSelection(sel);
				finderUI.getTypes().clear();
			}
		});
		Label lbl = new Label(dsCmp, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		Map<String, String> typeNames = ResourceFactory.getTypeNames();
		for (String rtype : typeNames.keySet()) {
			if (dsTypes.contains(rtype))
				continue;
			final Button bhiden = new Button(dsCmp, SWT.CHECK);
			bhiden.setText(typeNames.get(rtype));
			bhiden.setSelection(true);
			bhiden.setToolTipText(rtype);
			typesMap.put(rtype, bhiden);
			bhiden.addSelectionListener(typeListener);
		}
		return typeNames;
	}

	private Map<String, String> createFileTypeFilters(Composite scmp, Map<String, String> typeNames) {
		if (sp.getWsClient().getServerInfo().getVersion().compareTo("5.5") >= 0) {
			Composite dsCmp = new Composite(scmp, SWT.NONE);
			dsCmp.setLayout(new GridLayout(3, false));
			GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
			// gd.horizontalSpan = 2;
			dsCmp.setLayoutData(gd);

			bft = new Button(dsCmp, SWT.CHECK);
			bft.setText("Files By Type");
			bft.setSelection(true);
			bft.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					boolean sel = bft.getSelection();
					for (Button b : typesMap.values()) {
						for (FileType ft : FileType.values())
							if (ft.name().equalsIgnoreCase(b.getText())) {
								b.setSelection(sel);
								break;
							}
					}
					if (!sel)
						ball.setSelection(false);
					finderUI.getTypes().clear();
				}
			});
			Label lbl = new Label(dsCmp, SWT.SEPARATOR | SWT.HORIZONTAL);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			lbl.setLayoutData(gd);

			for (FileType type : FileType.values()) {
				final Button bhiden = new Button(dsCmp, SWT.CHECK);
				bhiden.setText(type.name());
				bhiden.setSelection(true);
				bhiden.setToolTipText(type.name());
				typesMap.put(type.name(), bhiden);
				bhiden.addSelectionListener(typeListener);
			}
		}
		return typeNames;
	}

	private void createDatasourceFilters(Composite scmp, Map<String, String> typeNames) {
		Composite dsCmp = new Composite(scmp, SWT.NONE);
		dsCmp.setLayout(new GridLayout(1, false));
		dsCmp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		bds = new Button(dsCmp, SWT.CHECK);
		bds.setText(Messages.FindResourcePage_7);
		bds.setSelection(true);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 1;
		bds.setLayoutData(gd);
		bds.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean sel = bds.getSelection();
				for (Button b : typesMap.values()) {
					String v = typesMap.inverse().get(b);
					if (v != null && dsTypes.contains(v))
						b.setSelection(sel);
				}
				if (!sel)
					ball.setSelection(false);
				setTypes();
			}
		});

		Label lbl = new Label(dsCmp, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		for (String rtype : dsTypes) {
			if (!typeNames.containsKey(rtype))
				continue;
			final Button bhiden = new Button(dsCmp, SWT.CHECK);
			bhiden.setText(Misc.nvl(typeNames.get(rtype), rtype));
			bhiden.setSelection(true);
			bhiden.setToolTipText(rtype);
			typesMap.put(rtype, bhiden);
			bhiden.addSelectionListener(typeListener);
		}
	}

	private void createTreeView(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Tree");

		RepositoryComposite rcom = new RepositoryComposite(tabFolder, SWT.NONE, finderUI.getServerProfile(), false) {

			@Override
			protected void okPressed() {
				((FindWizardDialog) getContainer()).finishPressed();
			}

			@Override
			protected void setOkButtonEnabled(boolean resCompatible) {
				FindResourcePage.this.setPageComplete(resCompatible);
			}

			@Override
			protected void createReadRepositoryJob() {
				try {
					getContainer().run(true, true, new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException, InterruptedException {
							doReadRepository(monitor);
						}
					});
				} catch (InvocationTargetException e) {
					UIUtils.showError(e.getCause());
				} catch (InterruptedException e) {
					UIUtils.showError(e.getCause());
				}
			}

			@Override
			public boolean isResourceCompatible(AMResource r) {
				if (Misc.isNullOrEmpty(finderUI.getTypes()))
					return true;
				if (!finderUI.isShowHidden() && !containedResource && r.getValue().getParentFolder().endsWith("_files"))
					return false;
				String type = WsTypes.INST().toRestType(r.getValue().getWsType());
				for (String t : finderUI.getTypes()) {
					if (t.equals(type))
						return true;
				}
				return false;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.server.properties.dialog.
			 * RepositoryComposite
			 * #setResource(com.jaspersoft.studio.server.model.MResource)
			 */
			@Override
			public void setResource(AMResource res) {
				super.setResource(res);
				value = res.getValue();
			}
		};
		rcom.setLayoutData(new GridData(GridData.FILL_BOTH));

		bptab.setControl(rcom);
	}

	private void createListView(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("List");

		Composite tableComposite = new Composite(tabFolder, SWT.NONE);
		tableComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		bptab.setControl(tableComposite);

		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);
		viewer = new TableViewer(tableComposite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE);
		TableViewerColumn col = new TableViewerColumn(viewer, SWT.NONE);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				ClientResourceLookup p = (ClientResourceLookup) cell.getElement();

				cell.setText(p.getLabel() + " : " + p.getUri()); //$NON-NLS-1$
				StyleRange myStyledRange = new StyleRange(p.getLabel().length() + 3, cell.getText().length(),
						Display.getCurrent().getSystemColor(SWT.COLOR_GRAY), null);
				StyleRange[] range = { myStyledRange };
				cell.setStyleRanges(range);

				cell.setImage(ResourceFactory.getIcon(p.getResourceType()));
				super.update(cell);
			}

			@Override
			public String getToolTipText(Object element) {
				ClientResourceLookup p = (ClientResourceLookup) element;
				String tt = p.getLabel();
				tt += "\n" + Messages.FindResourcePage_10 + p.getDescription(); //$NON-NLS-1$
				tt += "\n" + Messages.FindResourcePage_uri + p.getUri(); //$NON-NLS-1$
				tt += "\n" + Messages.FindResourcePage_14 + p.getResourceType(); //$NON-NLS-1$
				tt += "\n" + Messages.FindResourcePage_16 + p.getCreationDate(); //$NON-NLS-1$
				tt += "\n" + Messages.FindResourcePage_18 + p.getUpdateDate(); //$NON-NLS-1$
				return tt;
			}
		});
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				((FindWizardDialog) getContainer()).finishPressed();
			}
		});

		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(100));
		final Table table = viewer.getTable();
		table.setHeaderVisible(false);
		table.setLinesVisible(false);

		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (res != null)
					try {
						int[] sel = table.getSelectionIndices();
						if (sel.length > 0)
							value = WSClientHelper.toResourceDescriptor(finderUI.getServerProfile(), res.get(sel[0]));
						if (value != null)
							setPageComplete(true);
					} catch (Exception e1) {
						UIUtils.showError(e1);
					}
			}
		});
	}

	private BiMap<String, Button> typesMap = HashBiMap.create();
	private ResourceDescriptor value;
	private Text txt;
	private java.util.List<ClientResourceLookup> res;
	private TableViewer viewer;
	private static Set<String> dsTypes = WsTypes.INST().getDatasources();

	public ResourceDescriptor getValue() {
		return value;
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage() && getValue() != null;
	}

	private void setTypes() {
		List<String> tps = finderUI.getTypes();
		tps.clear();
		for (Button b : typesMap.values())
			if (b.getSelection())
				tps.add(typesMap.inverse().get(b));
	}

	private SelectionListener typeListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button bhiden = (Button) e.getSource();
			String type = typesMap.inverse().get(bhiden);
			if (!bhiden.getSelection()) {
				ball.setSelection(false);
				if (dsTypes.contains(type))
					bds.setSelection(false);
			}
			setTypes();
		}
	};
	private Button bds;
	private Button ball;
	private int started = 0;
	private boolean ended = true;
	private CTabFolder tabFolder;
	private Button bft;

	class FinderUI extends AFinderUI {
		public FinderUI(MServerProfile sp) {
			super(sp);
		}

		@Override
		public void showResults(final java.util.List<ClientResourceLookup> res) {
			FindResourcePage.this.res = res;
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					setPageComplete(res != null);
					if (res != null)
						viewer.setInput(res);
					else
						viewer.setInput(Collections.EMPTY_LIST);
					value = null;
					setPageComplete(false);
					ended = true;
					started--;
					if (started > 0) {
						started = 0;
						doSearch();
					}
				}
			});
		}
	}

	private void doSearch() {
		finderUI.setText(txt.getText());
		started++;
		if (ended) {
			ended = false;
			search();
		}
	}

	private void search() {
		if (SystemUtils.IS_OS_WINDOWS)
			new Thread(new Runnable() {
				public void run() {
					try {
						WSClientHelper.findResources(new NullProgressMonitor(), finderUI);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		else
			try {
				getContainer().run(true, true, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						monitor.beginTask(Messages.FindResourcePage_19, IProgressMonitor.UNKNOWN);
						try {
							WSClientHelper.findResources(monitor, finderUI);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							monitor.done();
						}
					}
				});
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	}
}
