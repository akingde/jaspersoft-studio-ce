/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.kpi;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;
import com.jaspersoft.studio.server.wizard.find.FindResourceWizard;
import com.jaspersoft.studio.server.wizard.find.FindWizardDialog;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;


/**
 * Main editor on a single pane for the KPI.
 * 
 * When instancing this class, the user should provide a WSClient, a server profile (MServerProfile) and optionally
 * set kpiReportUnit resource descriptor if it exists. Then invoke loadJasperDesign() to initialize the model.
 * 
 * 
 * @author gtoffoli
 *
 */
public class KPIDefinitionPanel extends JSSHelpWizardPage {

	Label jrxmlFileUriLabel = null;
	Text jrxmlFileUri = null;
	Button jrxmlFileUriPickerButton = null;
	
	Label dataSourceUriLabel = null;
	Text dataSourceUri = null;
	Button dataSourceUriPickerButton = null;
	
	private IConnection client = null;
	private MServerProfile server = null;
	private ResourceDescriptor parentReportUnit = null;
	private ResourceDescriptor kpiReportUnit = null;
	
	
	protected KPIDefinitionPanel() {
		super("kpi"); // //$NON-NLS-0$
		// TODO Auto-generated constructor stub
		
		setTitle("KPI Definition");
		setDescription("Add/Modify a KPI for a Report Unit");
	}
	
	/**
	 * Build the main UI
	 */
	@Override
	public void createControl(Composite parent) {
		
		Composite c = new Composite(parent, SWT.NONE);
		
		c.setLayout(new GridLayout(2,false));
		
		
		jrxmlFileUriLabel = new Label(c, SWT.NONE);
		GridData gd = new GridData( GridData.FILL_HORIZONTAL );
		gd.horizontalSpan = 2;
		jrxmlFileUriLabel.setLayoutData(gd);
		jrxmlFileUriLabel.setText("KPI Jrxml");
		
		
		jrxmlFileUri = new Text(c, SWT.BORDER);
		jrxmlFileUri.setLayoutData(new GridData( GridData.FILL_HORIZONTAL ));
		
		jrxmlFileUriPickerButton = new Button(c, SWT.PUSH);
		jrxmlFileUriPickerButton.setText("Browse...");
		jrxmlFileUriPickerButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				
				String filePath = getResourceDialog();
				jrxmlFileUri.setText(filePath);
			}
		});
		
		
		dataSourceUriLabel = new Label(c, SWT.NONE);
		gd = new GridData( GridData.FILL_HORIZONTAL );
		gd.horizontalSpan = 2;
		dataSourceUriLabel.setLayoutData(gd);
		dataSourceUriLabel.setText("Datasource URI (blank if none)");
		
		dataSourceUri = new Text(c, SWT.BORDER);
		gd = new GridData( GridData.FILL_HORIZONTAL );
//		gd.heightHint = 16;
//		gd.horizontalSpan = 2;
		dataSourceUri.setLayoutData(gd); //new GridData( GridData.FILL_HORIZONTAL ));
		
		dataSourceUriPickerButton = new Button(c, SWT.PUSH);
		dataSourceUriPickerButton.setText("Browse...");
		dataSourceUriPickerButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				
				
				FindResourceWizard frw = new FindResourceWizard( server);
				//Set<String> types  = DatasourcesAllFilter.getTypes();
				frw.setFilterTypes(WsTypes.INST().getDatasourcesArray() , null);
				
				FindWizardDialog dialog = new FindWizardDialog(UIUtils.getShell(), frw);
				
				if (dialog.open() == Dialog.OK)
				{
					ResourceDescriptor rd = frw.getValue();
					dataSourceUri.setText( rd.getUriString() );
				}
			}
		});
		
		
		if (getKpiReportUnit() != null)
		{
			// Find the main jrxml
			for (ResourceDescriptor rd : getKpiReportUnit().getChildren())
			{
				if (SelectorDatasource.isDatasource(rd))
				{
					// Set the existing dataset uri...
					dataSourceUri.setText(  rd.getUriString()  );
					break;
				}
			}
			
		}
		
		setControl(c);
		
	}

	
	/**
	 * Return a resource by selecting it from the workspace, useful when a jrxml must be selected
	 * 
	 * @return the path of the resource
	 */
	protected String getResourceDialog() {
		FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(
				UIUtils.getShell(), false, ResourcesPlugin.getWorkspace()
						.getRoot(), IResource.FILE);
		dialog.setTitle("");
		dialog.setInitialPattern("*.jrxml"); //$NON-NLS-1$
		
		if (dialog.open() == Window.OK) {
			IFile file = (IFile) dialog.getFirstResult();
			return file.getLocation().toPortableString();
		}
		return null;
	}
	
	
	@Override
	protected String getContextName() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResourceDescriptor getParentReportUnit() {
		return parentReportUnit;
	}

	public void setParentReportUnit(ResourceDescriptor parentReportUnit) {
		this.parentReportUnit = parentReportUnit;
	}

	public IConnection getWSClient() {
		return client;
	}

	public void setWSClient(IConnection client) {
		this.client = client;
	}

	public ResourceDescriptor getKpiReportUnit() {
		return kpiReportUnit;
	}

	public void setKpiReportUnit(ResourceDescriptor kpiReportUnit) {
		this.kpiReportUnit = kpiReportUnit;
	}
	
	
	/**
	 * Potentially to be removed. This method is invoked
	 * by the finish action of the wizard to deploy the new
	 * KPI jrxml
	 * 
	 * @return
	 */
	public String getJrxmlFile()
	{
		return jrxmlFileUri.getText();
	}
	
	
	
	/**
	 * This method is invoked by the finish action of the wizard
	 * to deploy the new KPI jrxml
	 * 
	 * @return
	 */
	public String getDatasourceUri()
	{
		return dataSourceUri.getText();
	}

	public MServerProfile getMServerProfile() {
		return server;
	}

	public void setMServerProfile(MServerProfile server) {
		this.server = server;
	}
	
	

}
