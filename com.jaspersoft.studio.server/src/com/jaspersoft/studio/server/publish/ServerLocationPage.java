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
package com.jaspersoft.studio.server.publish;

import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
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
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.model.MDummy;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;

public class ServerLocationPage extends WizardPage {
	private JasperDesign jDesign;
	private TreeViewer treeViewer;
	private Button bnRunit;
	private Text ruName;
	private MReportUnit reportUnit;

	public ServerLocationPage(JasperDesign jDesign) {
		super("serverpublish"); //$NON-NLS-1$
		setTitle("Publish To JasperServer");
		setDescription("Select Jasper Reports Server and location where report will be published");
		this.jDesign = jDesign;
	}

	public MReportUnit getReportUnit() {
		if (reportUnit.getValue().getName().isEmpty()) {
			reportUnit.getValue().setName(jDesign.getName());
			reportUnit.getValue().setLabel(jDesign.getName());
		}
		return reportUnit;
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
		Job job = new Job("Find Report Unit") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					final MRoot root = new MRoot(null, null);
					ANode sp = null;
					List<ServerProfile> servers = ServerManager.getServerList();
					for (ServerProfile s : servers) {
						sp = new MServerProfile(root, s);
						new MDummy(sp);
						try {
							WSClientHelper
									.connect((MServerProfile) sp, monitor);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							treeViewer.setInput(root);
						}
					});

					final String prop = jDesign
							.getProperty(JrxmlExporter.PROP_SERVERURL);
					if (prop != null) {
						final MServerProfile mserv = (MServerProfile) new ModelVisitor(
								root) {

							@Override
							public boolean visit(INode n) {
								if (n instanceof MServerProfile
										&& ((MServerProfile) n).getValue()
												.getUrl().equals(prop)) {
									setObject(n);
									return false;
								}
								return false;
							}
						}.getObject();

						sp = findReportUnit(mserv, monitor);
					}
					setSelection(sp);
					return Status.OK_STATUS;
				} finally {
					monitor.done();
				}
			}

		};
		job.setPriority(Job.LONG);
		job.schedule();
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

	protected ANode findReportUnit(ANode sp, IProgressMonitor monitor) {
		try {
			MServerProfile mserv = (MServerProfile) sp;
			WSClientHelper.connect(mserv, monitor);
			if (sp != null && sp instanceof MServerProfile) {
				String prunit = jDesign
						.getProperty(JrxmlExporter.PROP_REPORTUNIT);
				if (prunit != null) {
					WSClientHelper.connectGetData(mserv, monitor);
					sp = WSClientHelper.findSelected(mserv.getChildren(),
							monitor, prunit, mserv.getWsClient());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
}
