package com.jaspersoft.studio.data.wizard;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.wizard.pages.DataAdapterEditorPage;
import com.jaspersoft.studio.data.wizard.pages.DataAdaptersListPage;

public class DataAdapterWizard extends Wizard implements SelectionListener {

	private DataAdapter dataAdapter                     = null;
	private DataAdapterWizardDialog wizardDialog        = null;
	private DataAdapterFactory selectedFactory          = null;
	private DataAdaptersListPage dataAdapterListPage    = null;
	private DataAdapterEditorPage dataAdapterEditorPage = null;

	/**
	 * This constructor will set the data adapter wizard.
	 * The data adapters list is displayed as first page,
	 * then the edit page is shown.
	 */
	public DataAdapterWizard() {
		setWindowTitle("DataAdapter Wizard");
	}
	
	/**
	 * Pass to this constructor a dataAdapter to be edited.
	 * This will set the wizard directly to edit page.
	 * 
	 * @param dataAdapter
	 */
	public DataAdapterWizard(DataAdapter dataAdapter) {
		this();
		this.dataAdapter = dataAdapter;
	}
	
	// WizardDialog Setter and Getter
	public void setWizardDialog(DataAdapterWizardDialog wizardDialog) {
		this.wizardDialog = wizardDialog;
		if(this.wizardDialog != null) {
			this.wizardDialog.addTestListener(this);
		}
	}
	
	public DataAdapterWizardDialog getWizardDialog() {
		return wizardDialog;
	}

	@Override
	public void addPages() {
		System.out.println("addPages");
		if(dataAdapter == null) {
			System.out.println("dataAdapter is null");
			dataAdapterListPage = new DataAdaptersListPage();
			addPage(dataAdapterListPage);
		}
		dataAdapterEditorPage = new DataAdapterEditorPage();
		addPage(dataAdapterEditorPage);
	}
	
	@Override
	public void createPageControls(Composite pageContainer)
	{
		super.createPageControls(pageContainer);
	  
		if (this.dataAdapter != null)
		{
			DataAdapter editedDataAdapter = DataAdapterManager.cloneDataAdapter( this.dataAdapter );
			dataAdapterEditorPage.setDataAdapter(editedDataAdapter);
		}
	}

	@Override
	public IWizardPage getStartingPage() {
		System.out.println("getStartingPage");
		IWizardPage page = super.getStartingPage();
		updateTestButton(page);
		return page;
	}
	
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		updateTestButton(page);
		return super.getPreviousPage(page);
	}
	
	public void updateTestButton(IWizardPage page)
	{
		getWizardDialog().setTestButtonEnabled(page == dataAdapterEditorPage);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		// We enable the test button only in the second step
		updateTestButton(page);
		
		if (page == dataAdapterListPage)
		{
			// Update the layout of the editor page with the proper data adapter editor
			// provided by the new data adapter
			DataAdapterFactory factory = dataAdapterListPage.getSelectedFactory();
			System.out.println("Requested the editor page " + factory);
			
			// 1. instance a new dataAdapter using the factory
			DataAdapter newDataAdapter = factory.createDataAdapter();
			
			// 2. set in the wizard page the data adapter to edit
			if(selectedFactory != factory) {
				dataAdapterEditorPage.setDataAdapter(newDataAdapter);
				selectedFactory = factory;
			}
			
		}
		return super.getNextPage(page);
	}

	// Save the new adapter using the manager
	@Override
	public boolean performFinish() {
		DataAdapter editedDataAdapter = dataAdapterEditorPage.getDataAdapter();
		
		if (this.dataAdapter == null)
		{
			this.dataAdapter = editedDataAdapter;
		}
		else  // We are modifying an existing adapter....
		{
			// ... let's update with the adapter just modified ...
			this.dataAdapter.loadProperties(editedDataAdapter.getProperties());
			
			// ... but each DataAdapter has a unique name ...
			if (!isDataAdapterNameUnique(editedDataAdapter.getName()))
			{
				this.dataAdapter.setName(editedDataAdapter.getName());
			}
		}
		return true;
	}

  public boolean isDataAdapterNameUnique(String dataAdapterName) {
		List<DataAdapter> dataAdapters = DataAdapterManager.getDataAdapters();
		for (DataAdapter dataAdapter : dataAdapters) {
			if (dataAdapterName.equals(dataAdapter.getName())) return false;
		}
		return true;
	}

	/**
   * This method is called when the test button is pressed
   */
	public void widgetSelected(SelectionEvent e) {
		if(getContainer().getCurrentPage() == dataAdapterEditorPage) {
			try {
				dataAdapterEditorPage.getDataAdapterEditor().getDataAdapter().test();
				
				MessageBox mb = new MessageBox(getContainer().getShell(), SWT.ICON_INFORMATION | SWT.OK);
        mb.setText("Test");
        mb.setMessage("Succesful");
        mb.open();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Returns the new data adapter (or the modified data adapter in case the wizard is used to edit an
	 * existing data adapter).
	 * It returns null (or the original data adapter) if the wizard has not been completed.
	 * The returned object is the same used in the constructor in case of editing.
	 * 
	 * @return
	 */
	public DataAdapter getDataAdapter()
	{
		return this.dataAdapter;
	}


	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
