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
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.utils.ValidationUtils;
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
		contextName = ContextHelpIDs.wizardSelectServer;
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
				&& (getReportUnit() instanceof MReportUnit)
				&& getErrorMessage() == null;
	}
	
	@Override
	public void setErrorMessage(String newMessage) {
		super.setErrorMessage(newMessage);
		setPageComplete(newMessage==null);
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
				
				if(selected){
					performPageChecks();
				}
				else{
					// Clean error message and disable page complete enablement
					setErrorMessage(null);
					setPageComplete(false);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		// Report Unit ID (resource descriptor name)   
		Label lblRepoUnitID = new Label(composite, SWT.NONE);
		lblRepoUnitID.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,false));
		lblRepoUnitID.setText(Messages.RUnitLocationPage_lblreportunit);
		ruID = new Text(composite, SWT.BORDER);
		ruID.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ruID.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String rtext = ruID.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setName(rtext.replace(" ", ""));	 //$NON-NLS-1$ //$NON-NLS-2$
				setErrorMessage(ValidationUtils.validateName(rtext));
			}
		});
		ruID.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				if(Character.isWhitespace(e.character)){
					e.doit = false;	
				}
			}
		});
		ruID.setText(jDesign.getName().replace(" ", "")); //$NON-NLS-1$ //$NON-NLS-2$
		
		// Report Unit shown label (resource descriptor label)
		Label lblRepoUnitName = new Label(composite, SWT.NONE);
		lblRepoUnitName.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,false));
		lblRepoUnitName.setText(Messages.RUnitLocationPage_reportunitlabel);
		ruLabel = new Text(composite, SWT.BORDER);
		ruLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ruLabel.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String rtext = ruLabel.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setLabel(rtext);
				setErrorMessage(ValidationUtils.validateLabel(rtext));
			}
		});
		ruLabel.setText(jDesign.getName());
		
		// Report Unit description
		Label lblRepoUnitDescription = new Label(composite, SWT.NONE);
		GridData descLblGD = new GridData(SWT.FILL,SWT.TOP,false,false);
		lblRepoUnitDescription.setLayoutData(descLblGD);
		lblRepoUnitDescription.setText(Messages.RUnitLocationPage_reportunitdesc_label);
		ruDescription = new Text(composite, SWT.BORDER | SWT.MULTI);
		GridData descGD = new GridData(SWT.FILL,SWT.TOP,true,true);
		descGD.minimumHeight=50;
		ruDescription.setLayoutData(descGD);
		ruDescription.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String rtext = ruDescription.getText();
				ResourceDescriptor ru = getNewRunit().getValue();
				ru.setDescription(rtext);
				setErrorMessage(ValidationUtils.validateDesc(rtext));
			}
		});
		ruDescription.setText("");		 //$NON-NLS-1$

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
		if (n != null) {
			treeViewer.setInput(n.getRoot());
			setSelection(n);
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
		return newrunit;
	}

	protected void handleSelectionChanged(Object obj) {
		boolean isFolder = obj instanceof MFolder;
		bnRunit.setSelection(isFolder);
		bnRunit.setEnabled(isFolder);
		ruLabel.setEnabled(bnRunit.getSelection() && isFolder);
		ruID.setEnabled(bnRunit.getSelection() && isFolder);
		ruDescription.setEnabled(bnRunit.getSelection() && isFolder);

		reportUnit = getNewRunit();
		performPageChecks();
		if (obj instanceof MReportUnit)
			reportUnit = (MReportUnit) obj;
		else if (obj instanceof MFolder) {
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
		String errorMsg=null;
		errorMsg=ValidationUtils.validateName(ruID.getText());
		if(errorMsg==null){
			errorMsg=ValidationUtils.validateLabel(ruLabel.getText());
		}
		if(errorMsg==null){
			errorMsg=ValidationUtils.validateDesc(ruDescription.getText());
		}
		setErrorMessage(errorMsg);
		setPageComplete(errorMsg==null);
	}
}
