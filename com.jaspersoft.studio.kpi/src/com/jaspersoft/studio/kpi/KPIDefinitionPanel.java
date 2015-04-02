package com.jaspersoft.studio.kpi;

import java.io.File;
import java.net.URL;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
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
	
	/**
	 * The kpiJasperDesign.
	 */
	private JasperDesign kpiJasperDesign = null;
	
	
	protected KPIDefinitionPanel() {
		super("kpi"); // //$NON-NLS-0$
		// TODO Auto-generated constructor stub
		
		setTitle("KPI Definition");
		setDescription("Add/Modify a KPI for a Report Unit");
	}
	
	
	
	/**
	 * Load the basic JasperDesign for this KPI.
	 * If a kpiReportUnit is provided and a client is available,
	 * the file is loaded from the Server, otherwise a new blank kpi jrxml is
	 * loaded from the resources.
	 * 
	 */
	public void loadJasperDesign()
	{
		
		// Lucky case: this report unit already has a KPI, let's load it from
		// JasperReports Server
		if (getKpiReportUnit() != null && getWSClient() != null)
		{
			ResourceDescriptor jrxmlRd = null;
			
			// Find the main jrxml
			for (ResourceDescriptor rd : getKpiReportUnit().getChildren())
			{
				if (rd.isMainReport())
				{
					jrxmlRd = rd;
				}
				
				if (SelectorDatasource.isDatasource(rd))
				{
					// Set the existing dataset uri...
					dataSourceUri.setText(  rd.getUriString()  );
				}
			}
			
			// If found, download it and load it
			if (jrxmlRd != null)
			{	
				// Load the jrxml of this KPI...
				File f = null;
				try {
					f = FileUtils.createTempFile("kpi_", ".jrxml");
					getWSClient().get(new NullProgressMonitor(), jrxmlRd, f);
				
					kpiJasperDesign = JRXmlLoader.load(f);
					
					// Delete the file
					f.delete();
					
				} catch (Exception e) {
					// TODO To decide to do when I identified the KPI jrxml but I have no idea what to do with it...
					// since I was not able to load it...
					e.printStackTrace();
				}
			}
		}
		
		// If I was not able to find an existing JasperDesign, I load the one we have as starting point
		// from the resources.
		if (this.kpiJasperDesign == null)
		{
			try {
				// Load the standard kpi.jrxml from the resources
				URL resource = Activator.getDefault().getBundle().getResource("/resources/kpi.jrxml");
				kpiJasperDesign = JRXmlLoader.load(resource.openStream());
			} catch (Exception e) {
				// TODO this should never happen.... but if it does, it should be some kind of
				// fatal error!!
				e.printStackTrace();
			}
		}
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
