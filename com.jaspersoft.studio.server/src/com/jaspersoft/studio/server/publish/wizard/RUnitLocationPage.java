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
package com.jaspersoft.studio.server.publish.wizard;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;

public class RUnitLocationPage extends WizardPage {
	private JasperDesign jDesign;
	private TreeViewer treeViewer;
	private Button bnRunit;
	private Text ruName;
	private MReportUnit reportUnit;
	private ANode n;

	public RUnitLocationPage(JasperDesign jDesign, ANode n) {
		super("serverpublish"); //$NON-NLS-1$
		setTitle("Publish To JasperServer");
		setDescription("Select Jasper Reports Server and location where report will be published");
		this.jDesign = jDesign;
		this.n = n;
	}

	public MReportUnit getReportUnit() {
		if (reportUnit != null) {
			ResourceDescriptor runitvalue = reportUnit.getValue();
			if (runitvalue.getName() == null || runitvalue.getName().isEmpty()) {
				runitvalue.setName(jDesign.getName());
				runitvalue.setLabel(jDesign.getName());
			}
		}
		return reportUnit;
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage()
				&& (getReportUnit() instanceof MReportUnit);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		treeViewer = new TreeViewer(composite, SWT.SINGLE | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 300;
		gd.minimumWidth = 400;
		gd.horizontalSpan = 2;
		treeViewer.getTree().setLayoutData(gd);
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		ColumnViewerToolTipSupport.enableFor(treeViewer);
		bnRunit = new Button(composite, SWT.CHECK);
		bnRunit.setText("Create Report Unit");
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 2;
		bnRunit.setLayoutData(gd);
		bnRunit.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ruName.setEnabled(bnRunit.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Report Unit Name");

		ruName = new Text(composite, SWT.BORDER);
		ruName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ruName.setText(jDesign.getName());
		ruName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String rtext = ruName.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setName(rtext);
				ru.setLabel(rtext);
			}
		});

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection ts = (TreeSelection) event.getSelection();
				Object obj = ts.getFirstElement();
				handleSelectionChanged(obj);
			}

		});
		treeViewer.addTreeListener(new ITreeViewerListener() {

			private ServerProvider serverProvider;

			public void treeExpanded(TreeExpansionEvent event) {
				if (!skipEvents) {
					if (serverProvider == null)
						serverProvider = new ServerProvider();
					serverProvider.handleTreeEvent(event);
				}
			}

			public void treeCollapsed(TreeExpansionEvent event) {

			}
		});

		fillInput();
	}

	private void fillInput() {
		treeViewer.setInput(n.getRoot());
		setSelection(n);
	}

	private MReportUnit newrunit;

	private MReportUnit getNewRunit() {
		if (newrunit == null) {
			ResourceDescriptor rd = new ResourceDescriptor();
			rd.setIsNew(true);
			rd.setIsReference(false);
			rd.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
			newrunit = new MReportUnit(null, rd, -1);
		}
		return newrunit;
	}

	protected void handleSelectionChanged(Object obj) {
		boolean isFolder = obj instanceof MFolder;
		bnRunit.setEnabled(isFolder);
		ruName.setEnabled(bnRunit.getSelection() && isFolder);

		reportUnit = getNewRunit();
		setPageComplete(true);
		if (obj instanceof MReportUnit)
			reportUnit = (MReportUnit) obj;
		else if (obj instanceof MFolder) {
			reportUnit.setParent((ANode) obj, -1);
			ResourceDescriptor nrd = reportUnit.getValue();
			String id = ruName.getText();
			nrd.setName(id);
			nrd.setLabel(id);
			String uri = ((MFolder) obj).getValue().getUriString();
			nrd.setParentFolder(uri);
			nrd.setUriString(uri + "/" + id);
		} else {
			setPageComplete(false);
		}
	}

	private boolean skipEvents = false;

	protected void setSelection(final ANode sp) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				skipEvents = true;
				treeViewer.refresh();
				if (sp != null)
					treeViewer.setSelection(new StructuredSelection(sp), true);
				skipEvents = false;

				handleSelectionChanged(sp);
			}
		});
	}

}
