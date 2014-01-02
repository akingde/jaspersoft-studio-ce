package com.jaspersoft.studio.server.wizard.find;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
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

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.studio.server.AFinderUI;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;

public class FindResourcePage extends WizardPage {
	private FinderUI finderUI;

	protected FindResourcePage(MServerProfile sp) {
		super("findresource");
		setTitle("Find Resource");
		setDescription("Search for occurrence in label or description of resource.");
		finderUI = new FinderUI(sp);
	}

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		setControl(cmp);

		new Label(cmp, SWT.NONE).setText("Search:");

		txt = new Text(cmp, SWT.BORDER);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				doSearch();
			}
		});

		Button b = new Button(cmp, SWT.PUSH);
		b.setText("Search");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSearch();
			}
		});

		Section expcmp = new Section(cmp, ExpandableComposite.TREE_NODE);
		expcmp.setText("Resource Types");
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		gd.verticalIndent = 3;
		expcmp.setLayoutData(gd);
		expcmp.setExpanded(false);

		Composite scmp = new Composite(expcmp, SWT.NONE);
		scmp.setLayout(new GridLayout(4, false));

		ball = new Button(scmp, SWT.CHECK);
		ball.setText("Select All");
		ball.setSelection(true);
		ball.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean sel = ball.getSelection();
				for (Button b : typesMap.values())
					b.setSelection(sel);
				bds.setSelection(sel);
				finderUI.getTypes().clear();
			}
		});

		bds = new Button(scmp, SWT.CHECK);
		bds.setText("Select Datasources");
		bds.setSelection(true);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 3;
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

		Label lbl = new Label(scmp, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 4;
		lbl.setLayoutData(gd);

		Map<String, String> typeNames = ResourceFactory.getTypeNames();
		for (String rtype : typeNames.keySet()) {
			final Button bhiden = new Button(scmp, SWT.CHECK);
			bhiden.setText(typeNames.get(rtype));
			bhiden.setSelection(true);
			bhiden.setToolTipText(rtype);
			typesMap.put(rtype, bhiden);
			bhiden.addSelectionListener(typeListener);
		}

		expcmp.setClient(scmp);
		expcmp.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				UIUtils.relayoutDialog(getShell(), 0, -1);
			}
		});

		Composite tableComposite = new Composite(cmp, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		gd.heightHint = 300;
		gd.widthHint = 500;
		tableComposite.setLayoutData(gd);

		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);
		viewer = new TableViewer(tableComposite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE);
		TableViewerColumn col = new TableViewerColumn(viewer, SWT.NONE);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				ClientResourceLookup p = (ClientResourceLookup) cell.getElement();

				cell.setText(p.getLabel() + " : " + p.getUri());
				StyleRange myStyledRange = new StyleRange(p.getLabel().length() + 3, cell.getText().length(), Display.getCurrent().getSystemColor(SWT.COLOR_GRAY), null);
				StyleRange[] range = { myStyledRange };
				cell.setStyleRanges(range);

				cell.setImage(ResourceFactory.getIcon(p.getResourceType()));
				super.update(cell);
			}

			@Override
			public String getToolTipText(Object element) {
				ClientResourceLookup p = (ClientResourceLookup) element;
				String tt = p.getLabel();
				tt += "\n" + "Description: " + p.getDescription();
				tt += "\n" + "URI: " + p.getUri();
				tt += "\n" + "Type: " + p.getResourceType();
				tt += "\n" + "Creation Date: " + p.getCreationDate();
				tt += "\n" + "Update Date: " + p.getUpdateDate();
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

		txt.setFocus();
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
			try {
				getContainer().run(true, true, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						monitor.beginTask("Searching", IProgressMonitor.UNKNOWN);
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
}
