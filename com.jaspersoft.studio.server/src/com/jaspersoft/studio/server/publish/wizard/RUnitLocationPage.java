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

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
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
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.publish.FindReportUnit;
import com.jaspersoft.studio.server.utils.ValidationUtils;
import com.jaspersoft.studio.server.wizard.resource.page.ResourcePageContent;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class RUnitLocationPage extends JSSHelpWizardPage {
	private JasperDesign jDesign;
	private TreeViewer treeViewer;
	private Button bnRunit;
	private Text ruLabel;
	private MReportUnit reportUnit;
	private ANode n;

	public RUnitLocationPage(JasperDesign jDesign, ANode n) {
		super("serverpublish"); //$NON-NLS-1$
		setTitle(Messages.RUnitLocationPage_title);
		setDescription(Messages.RUnitLocationPage_description);
		this.jDesign = jDesign;
		this.n = n;
	}

	public void setValue(JasperDesign jDesign, ANode n) {
		this.jDesign = jDesign;
		this.n = n;
		fillInput();
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SELECT_SERVER;
	}

	public MReportUnit getReportUnit() {
		if (reportUnit != null && jDesign != null) {
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
		return super.canFlipToNextPage();
	}

	@Override
	public boolean isPageComplete() {
		return super.isPageComplete() && (getReportUnit() instanceof MReportUnit && getReportUnit().getParent() != null) && getErrorMessage() == null;
	}

	@Override
	public void setErrorMessage(String newMessage) {
		super.setErrorMessage(newMessage);
		setPageComplete(newMessage == null);
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
		bnRunit.setText(Messages.RUnitLocationPage_addreportunit_button);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.horizontalSpan = 2;
		bnRunit.setLayoutData(gd);
		bnRunit.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				boolean selected = bnRunit.getSelection();
				// Enable/Disable the detail textboxes
				ruLabel.setEnabled(selected);
				ruID.setEnabled(selected);
				ruDescription.setEnabled(selected);

				if (selected) {
					performPageChecks();
				} else {
					// Clean error message and disable page complete enablement
					setErrorMessage(null);
					setPageComplete(false);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		// Report Unit shown label (resource descriptor label)
		Label lblRepoUnitName = new Label(composite, SWT.NONE);
		lblRepoUnitName.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		lblRepoUnitName.setText(Messages.RUnitLocationPage_reportunitlabel);
		ruLabel = new Text(composite, SWT.BORDER);
		ruLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Report Unit ID (resource descriptor name)
		Label lblRepoUnitID = new Label(composite, SWT.NONE);
		lblRepoUnitID.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		lblRepoUnitID.setText(Messages.RUnitLocationPage_lblreportunit);
		ruID = new Text(composite, SWT.BORDER);
		ruID.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ruID.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (isRefresh)
					return;
				isRefresh = true;
				int cp = ruID.getSelection().x;
				String rtext = ruID.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setName(ResourcePageContent.safeChar(rtext));
				String txt = ru.getName();
				ruID.setText(txt);
				ruID.setSelection(cp, cp);
				isRefresh = false;
			}
		});

		ruLabel.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (isRefresh)
					return;
				isRefresh = true;

				String rtext = ruLabel.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setName(ResourcePageContent.safeChar(rtext));
				ru.setLabel(rtext);
				setErrorMessage(ValidationUtils.validateLabel(rtext));
				ruLabel.setText(ru.getLabel());
				ruID.setText(ru.getName());
				isRefresh = false;
			}
		});

		// Report Unit description
		Label lblRepoUnitDescription = new Label(composite, SWT.NONE);
		GridData descLblGD = new GridData(SWT.FILL, SWT.TOP, false, false);
		lblRepoUnitDescription.setLayoutData(descLblGD);
		lblRepoUnitDescription.setText(Messages.RUnitLocationPage_reportunitdesc_label);
		ruDescription = new Text(composite, SWT.BORDER | SWT.MULTI);
		GridData descGD = new GridData(SWT.FILL, SWT.TOP, true, true);
		descGD.minimumHeight = 50;
		ruDescription.setLayoutData(descGD);
		ruDescription.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (isRefresh)
					return;
				String rtext = ruDescription.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setDescription(rtext);
				setErrorMessage(ValidationUtils.validateDesc(rtext));
			}
		});
		ruDescription.setText(""); //$NON-NLS-1$

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection ts = (TreeSelection) event.getSelection();
				Object obj = ts.getFirstElement();
				handleSelectionChanged(obj);
			}

		});
		treeViewer.addTreeListener(new ITreeViewerListener() {

			private ServerProvider serverProvider;

			public void treeExpanded(final TreeExpansionEvent event) {
				if (!skipEvents) {
					try {
						getContainer().run(false, true, new IRunnableWithProgress() {
							public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
								monitor.beginTask(Messages.Publish2ServerWizard_MonitorName, IProgressMonitor.UNKNOWN);
								try {
									if (serverProvider == null)
										serverProvider = new ServerProvider();
									Object element = event.getElement();
									boolean be = reportUnit.getParent() == element;
									serverProvider.handleTreeEvent(event, monitor);
									if (be) {
										MFolder f = (MFolder) element;
										String nm = reportUnit.getValue().getName();
										boolean isnew = true;
										for (INode n : f.getChildren()) {
											if (n instanceof MReportUnit) {
												if (((MReportUnit) n).getValue().getName().equals(nm)) {
													reportUnit = (MReportUnit) n;
													isnew = false;
													break;
												}
											}
										}
										if (isnew)
											reportUnit.setParent(f, -1);
									}
								} catch (Exception e) {
									if (e instanceof InterruptedException)
										throw (InterruptedException) e;
									else
										UIUtils.showError(e);
								} finally {
									monitor.done();
								}
							}
						});
					} catch (InvocationTargetException e) {
						UIUtils.showError(e.getCause());
					} catch (InterruptedException e) {
						UIUtils.showError(e.getCause());
					}

				}
			}

			public void treeCollapsed(TreeExpansionEvent event) {

			}
		});

		fillInput();
	}

	public void fillInput() {
		if (jDesign != null) {
			ruID.setText(jDesign.getName().replace(" ", "")); //$NON-NLS-1$ //$NON-NLS-2$
			ruLabel.setText(jDesign.getName());
		}
		setSelectedNode();
		if (n instanceof MServerProfile)
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					look4SelectedUnit((MServerProfile) n);
				}
			});
	}

	private void setSelectedNode() {
		if (n != null) {
			INode root = n.getRoot();
			if (root instanceof MServerProfile)
				root = ((MServerProfile) root).getRoot();
			if (!treeViewer.getTree().isDisposed()) {
				treeViewer.setInput(root);
				setSelection(n);
			}
		}
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
		if (jDesign != null) {
			ResourceDescriptor rd = newrunit.getValue();
			if (rd.getName() == null) {
				rd.setName(jDesign.getName());
				rd.setLabel(jDesign.getName());
			}
		}
		return newrunit;
	}

	private boolean isRefresh = false;

	protected void handleSelectionChanged(Object obj) {
		if (isRefresh)
			return;
		isRefresh = true;
		boolean isFolder = obj instanceof MFolder;
		bnRunit.setSelection(isFolder);
		bnRunit.setEnabled(isFolder);
		ruLabel.setEnabled(bnRunit.getSelection() && isFolder);
		ruID.setEnabled(bnRunit.getSelection() && isFolder);
		ruDescription.setEnabled(bnRunit.getSelection() && isFolder);

		reportUnit = getNewRunit();
		if (obj instanceof MReportUnit) {
			reportUnit = (MReportUnit) obj;
			ruLabel.setText(Misc.nvl(reportUnit.getValue().getLabel()));
			ruID.setText(Misc.nvl(reportUnit.getValue().getName()));
			ruDescription.setText(Misc.nvl(reportUnit.getValue().getDescription()));
		} else if (obj instanceof MFolder) {
			reportUnit.setParent((ANode) obj, -1);
			ResourceDescriptor nrd = reportUnit.getValue();
			nrd.setName(ruID.getText());
			nrd.setLabel(ruLabel.getText());
			nrd.setDescription(ruDescription.getText());
			String uri = ((MFolder) obj).getValue().getUriString();
			nrd.setParentFolder(uri);
			nrd.setUriString(uri + "/" + nrd.getName()); //$NON-NLS-1$
		} else {
			setPageComplete(false);
		}
		performPageChecks();
		isRefresh = false;
	}

	private boolean skipEvents = false;
	private Text ruID;
	private Text ruDescription;

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

	/*
	 * Perform validation checks and eventually set the error message.
	 */
	private void performPageChecks() {
		String errorMsg = null;
		errorMsg = ValidationUtils.validateName(ruID.getText());
		if (errorMsg == null) {
			errorMsg = ValidationUtils.validateLabel(ruLabel.getText());
		}
		if (errorMsg == null) {
			errorMsg = ValidationUtils.validateDesc(ruDescription.getText());
		}
		setErrorMessage(errorMsg);
		setPageComplete(errorMsg == null);
	}

	private void look4SelectedUnit(final MServerProfile mres) {

		Job job = new Job(Messages.FindReportUnit_jobname) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(Messages.Publish2ServerWizard_MonitorName, IProgressMonitor.UNKNOWN);
				try {
					ANode node = FindReportUnit.findReportUnit(mres, monitor, jDesign);
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					if (n != mres)
						return Status.CANCEL_STATUS;
					n = node;
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							setSelectedNode();
						}
					});
				} finally {
					monitor.done();
				}
				return Status.OK_STATUS;
			}

		};
		job.setPriority(Job.LONG);
		job.schedule();
	}
}
