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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.kpi.Activator;
import com.jaspersoft.studio.kpi.dialog.pages.DatasetPage;
import com.jaspersoft.studio.kpi.dialog.pages.ParametersPage;
import com.jaspersoft.studio.kpi.dialog.pages.RangePage;
import com.jaspersoft.studio.kpi.dialog.pages.SeriesPage;
import com.jaspersoft.studio.kpi.dialog.pages.TitlePage;
import com.jaspersoft.studio.kpi.dialog.pages.ValuePage;
import com.jaspersoft.studio.kpi.dialog.pages.WidgetPage;
import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Wizard page where all the configuration pages of a KPI are shown. All the pages
 * are created in a lazy way inside the same composite and the requested one 
 * is put on the top of a stack layout
 * 
 * @author Orlandin Marco
 *
 */
public class KPIConfiguratorPage extends JSSHelpWizardPage {

	/**
	 * List of all the defined pages to configure the JPI
	 */
	private List<AbstractKPIConfigurationPage> pages = new ArrayList<AbstractKPIConfigurationPage>();
	
	/**
	 * Label used to show the title of the current page
	 */
	protected Label titleArea;
	
	/**
	 * Composite where the pages are created
	 */
	private Composite pagesComposite;
	
	/**
	 * The layout of the pages composite
	 */
	private StackLayout stackLayout;
	
	/**
	 * The Jasperdesign of the KPI.
	 */
	private JasperDesign kpiJasperDesign = null;
	
	//SERVER INFORMATIONS OF THE KPI REPORT UNIT
	
	private String datasourceURI = null;
	
	private IConnection client = null;
	
	private MServerProfile server = null;
	
	private ResourceDescriptor parentReportUnit = null;
	
	private ResourceDescriptor kpiReportUnit = null;
	
	/**
	 * Selection listener called when a configuration page is selected 
	 * from the pages list. It create it if necessary and put it on the top
	 * of the stack layout and update the title label
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class PageChooser extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			int selectionIndex = ((Table) e.widget).getSelectionIndex();
			AbstractKPIConfigurationPage selectedCategory = pages.get(selectionIndex);
			stackLayout.topControl = selectedCategory.getComposite(pagesComposite);
			titleArea.setText(selectedCategory.getTitle());
			titleArea.getParent().layout(true, true);
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			AbstractKPIConfigurationPage selectedCategory = pages.get(0);
			stackLayout.topControl = selectedCategory.getComposite(pagesComposite);
			titleArea.setText(selectedCategory.getTitle());
			titleArea.getParent().layout(true, true);
		}
	}
	
	/**
	 * Create the wizard page
	 */
	protected KPIConfiguratorPage() {
		super("kpi"); // //$NON-NLS-0$ //$NON-NLS-1$
		setTitle(Messages.KPIConfiguratorPage_wizardTitle);
		setDescription(Messages.KPIConfiguratorPage_wizardDescription);
	}
	
	/**
	 * Load the basic JasperDesign for this KPI.
	 * If a kpiReportUnit is provided and a client is available,
	 * the file is loaded from the Server, otherwise a new blank KPI jrxml is
	 * loaded from the resources.
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
					f = FileUtils.createTempFile("kpi_", ".jrxml"); //$NON-NLS-1$ //$NON-NLS-2$
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
				URL resource = Activator.getDefault().getBundle().getResource("/resources/templates/kpi.jrxml"); //$NON-NLS-1$
				kpiJasperDesign = JRXmlLoader.load(resource.openStream());
			} catch (Exception e) {
				// TODO this should never happen.... but if it does, it should be some kind of
				// fatal error!!
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Create all the available configuration pages
	 */
	protected void addCategories(){
		pages.add(new WidgetPage(kpiJasperDesign));
		pages.add(new DatasetPage(this, kpiJasperDesign));
		pages.add(new TitlePage(kpiJasperDesign));
		pages.add(new ValuePage(kpiJasperDesign));
		pages.add(new RangePage(kpiJasperDesign));
		pages.add(new SeriesPage(kpiJasperDesign));
		pages.add(new ParametersPage(kpiJasperDesign));
	}
	
	/**
	 * Initialize the table control where all the pages are listend
	 * and the visible one can be selected
	 * 
	 * @param table
	 */
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
	
	/**
	 * Convert the current KPI design file into a JRXML, save it into the temp folder
	 * and return its path
	 * 
	 * @return the path of a jrxml file representing the current KPI design and return it.
	 * Return null if there were an error while saving
	 */
	public String getJrxmlFile(){
		File tempFolder = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		String name = kpiJasperDesign.getName();
		//search the first available name
		File targetFile = new File(tempFolder, name+".jrxml"); //$NON-NLS-1$
		int counter = 0;
		while(targetFile.exists()){
			targetFile = new File(tempFolder, name+"_"+counter+".jrxml"); //$NON-NLS-1$ //$NON-NLS-2$
			counter++;
		}
		try {
			JRXmlWriter.writeReport(kpiJasperDesign,targetFile.getAbsolutePath(), FileUtils.UTF8_ENCODING);
		} catch (JRException e) {
			e.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(e);
			return null;
		}
		return targetFile.getAbsolutePath();
	}
	
	@Override
	protected String getContextName() {
		return null;
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
		
		
		Composite contentContainer = new Composite(c, SWT.NONE);
		GridLayout contentContainerLayout = new GridLayout(1, false);
		contentContainerLayout.marginWidth = 0;
		contentContainerLayout.marginHeight = 0;
		contentContainer.setLayout(contentContainerLayout);
		contentContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		titleArea = new Label(contentContainer, SWT.WRAP);
		titleArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//titleArea.setFont(JFaceResources.getBannerFont());
		Label separator=new Label(contentContainer, SWT.SEPARATOR|SWT.HORIZONTAL);
		GridData gd_separator = new GridData(SWT.FILL,SWT.TOP,true,false,1,1);
		separator.setLayoutData(gd_separator);		
		
		pagesComposite = new Composite(contentContainer, SWT.NONE);
		pagesComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		stackLayout = new StackLayout();
		pagesComposite.setLayout(stackLayout);
		
		createTableColumn(pagesTable);
		setControl(c);
	}
	
	//Getters and setters for the report unit informations
	
	public String getDatasourceUri(){
		return datasourceURI;
	}
	
	public void setDatasourceUri(String datasourceUri){
		this.datasourceURI = datasourceUri;
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
}
