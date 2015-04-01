package com.jaspersoft.studio.kpi.dialog;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.kpi.Activator;
import com.jaspersoft.studio.kpi.dialog.pages.DatasetPage;
import com.jaspersoft.studio.kpi.dialog.pages.RangePage;
import com.jaspersoft.studio.kpi.dialog.pages.TitlePage;
import com.jaspersoft.studio.kpi.dialog.pages.ValuePage;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class KPIConfiguratorPage extends JSSHelpWizardPage {

	private List<AbstractKPIConfigurationPage> pages = new ArrayList<AbstractKPIConfigurationPage>();
	
	private Composite pagesComposite;
	
	private StackLayout stackLayout;
	
	private String datasourceURI = null;
	
	private IConnection client = null;
	
	private MServerProfile server = null;
	
	private ResourceDescriptor parentReportUnit = null;
	
	private ResourceDescriptor kpiReportUnit = null;
	
	/**
	 * The kpiJasperDesign.
	 */
	private JasperDesign kpiJasperDesign = null;
	
	private class PageChooser extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			int selectionIndex = ((Table) e.widget).getSelectionIndex();
			AbstractKPIConfigurationPage selectedCategory = pages.get(selectionIndex);
			stackLayout.topControl = selectedCategory.getComposite(pagesComposite, kpiJasperDesign);
			pagesComposite.layout();
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			AbstractKPIConfigurationPage selectedCategory = pages.get(0);
			stackLayout.topControl = selectedCategory.getComposite(pagesComposite, kpiJasperDesign);
			pagesComposite.layout();
		}
	}
	
	
	protected KPIConfiguratorPage() {
		super("kpi"); // //$NON-NLS-0$
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
					datasourceURI = rd.getUriString();
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
	
	protected void addCategories(){
		pages.add(new TitlePage());
		pages.add(new ValuePage("Value", "value_variable"));
		pages.add(new ValuePage("Target", "target_variable"));
		pages.add(new RangePage());
		pages.add(new DatasetPage(this));
	}

	@Override
	public void createControl(Composite parent) {
		addCategories();
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2,false));
		
		Table pagesTable = new Table(c, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		gd.widthHint = 150;
		pagesTable.setLayoutData(gd);
		pagesComposite = new Composite(c, SWT.NONE);
		pagesComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		stackLayout = new StackLayout();
		pagesComposite.setLayout(stackLayout);
		
		createTableColumn(pagesTable);
		setControl(c);
	}
	
	private void createTableColumn(Table table) {
		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(table, SWT.NONE);
		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		table.setLayout(tlayout);

		for (TableColumn c : col)
			c.pack();

		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				AbstractKPIConfigurationPage category = (AbstractKPIConfigurationPage)element;
				return category.getName();
			}
		});
		tableViewer.setInput(pages);
		PageChooser tableSelection = new PageChooser();
		table.addSelectionListener(tableSelection);
		table.setSelection(0);
		tableSelection.widgetDefaultSelected(null);
	}

	@Override
	protected String getContextName() {
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
	
	public MServerProfile getMServerProfile() {
		return server;
	}

	public void setMServerProfile(MServerProfile server) {
		this.server = server;
	}
	
	public String getJrxmlFile(){
		File tempFolder = new File(System.getProperty("java.io.tmpdir"));
		String name = kpiJasperDesign.getName();
		File targetFile = new File(tempFolder, name+".jrxml");
		int counter = 0;
		while(targetFile.exists()){
			targetFile = new File(tempFolder, name+"_"+counter+".jrxml");
			counter++;
		}
		try {
			JRXmlWriter.writeReport(kpiJasperDesign,targetFile.getAbsolutePath(), "UTF-8");
		} catch (JRException e) {
			e.printStackTrace();
		}
		return targetFile.getAbsolutePath();
	}
	
	public String getDatasourceUri(){
		return datasourceURI;
	}
	
	public void setDatasourceUri(String datasourceUri){
		this.datasourceURI = datasourceUri;
	}
}
