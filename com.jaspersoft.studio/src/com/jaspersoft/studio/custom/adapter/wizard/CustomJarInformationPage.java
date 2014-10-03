package com.jaspersoft.studio.custom.adapter.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.custom.adapter.AdapterInfo;
import com.jaspersoft.studio.custom.adapter.LoadedClassesContainer;
import com.jaspersoft.studio.custom.adapter.Pair;
import com.jaspersoft.studio.custom.adapter.PluginHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class CustomJarInformationPage extends JSSHelpWizardPage {

	private String jarPath = null;
	
	private Combo adapterInterfaceCombo;
	
	private Combo adapterImplementationCombo;
	
	private Combo adapterServiceCombo;
	
	private Combo adapterServiceFactoryCombo;
	
	private Pair adapterInterface;
	
	private Pair adapterImplementation;
	
	private Pair adapterService;
	
	private Pair adapterServiceFactory;
	
	private boolean generateDynamicControls = false;
	
	protected CustomJarInformationPage() {
		super("dataadapterselectionpage");
		setTitle("Data Adapter JAR selection");
		setDescription("Select the Jar file containing your data adapter. Then you will able to check and eventualy modify the classes read from you adapter");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		createPathControls(composite);
		createClassesControls(composite);
		createCompositeControls(composite);
		setPageComplete(false);
		setControl(composite);
	}
	
	private void createCompositeControls(Composite parent){
		Button compositeButton = new Button(parent, SWT.CHECK);
		compositeButton.setText("Create automatically controls to configure the data adapter using the castor file");
		compositeButton.setSelection(false);
		compositeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generateDynamicControls = ((Button)e.getSource()).getSelection();
			}
		});
	}
	
	private void createPathControls(Composite parent){
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(3,false));
		
		Label jarPathLabel = new Label(container, SWT.NONE);
		jarPathLabel.setText("JAR Path:");
		final Text jarPathText = new Text(container, SWT.BORDER);
		jarPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		jarPathText.setEditable(false);
		
		Button browseButton = new Button(container, SWT.NONE);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
	       fd.setText(Messages.common_open);
	       String[] filterExt = { "*.jar" }; //$NON-NLS-1$ 
	       fd.setFilterExtensions(filterExt);
	       String selected = fd.open();
	       if (selected != null){
	      	 jarPathText.setText(selected);
	      	 jarPath = selected;
	      	 updateField();
	       }
			}
		});
	}
	
	private void createClassesControls(Composite parent){
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(1,true));
		
		Composite leftColumn = new Composite(container, SWT.NONE);
		leftColumn.setLayoutData(new GridData(GridData.FILL_BOTH));
		leftColumn.setLayout(new GridLayout(2,false));
		Label adapterInterfaceLabel = new Label(leftColumn, SWT.NONE);
		adapterInterfaceLabel.setText("Data Adapter Interface:");
		adapterInterfaceCombo = new Combo(leftColumn, SWT.BORDER);
		adapterInterfaceCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label adapterImplementationLabel = new Label(leftColumn, SWT.NONE);
		adapterImplementationLabel.setText("Data Adapter Implementation:");
		adapterImplementationCombo = new Combo(leftColumn, SWT.BORDER);
		adapterImplementationCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label adapterServiceLabel  = new Label(leftColumn, SWT.NONE);
		adapterServiceLabel.setText("Data Adapter Service:");
		adapterServiceCombo = new Combo(leftColumn, SWT.BORDER);
		adapterServiceCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label adapterServiceFactoryLabel = new Label(leftColumn, SWT.NONE);
		adapterServiceFactoryLabel.setText("Data Adapter Service Factory:");
		adapterServiceFactoryCombo = new Combo(leftColumn, SWT.BORDER);
		adapterServiceFactoryCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		ModifyListener comboModified = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				storeValues();
				
			}
		};
		
		adapterServiceFactoryCombo.addModifyListener(comboModified);
		adapterInterfaceCombo.addModifyListener(comboModified);
		adapterServiceCombo.addModifyListener(comboModified);
		adapterImplementationCombo.addModifyListener(comboModified);
	}
	
	private void updateField(){
		File jarFile = new File(jarPath);
		if (jarFile.exists()){
			try {
				LoadedClassesContainer loadedClasses = PluginHelper.loadAndScanJar(jarFile);
				
				
				String[] implementations = LoadedClassesContainer.converToArray(loadedClasses.getAdapterImplementation());
				String[] services = LoadedClassesContainer.converToArray(loadedClasses.getService());
				String[] factories = LoadedClassesContainer.converToArray(loadedClasses.getServiceFatory());
				
				List<Pair> validInterfaces = new ArrayList<Pair>();
				validInterfaces.addAll(loadedClasses.getAdapterInterfaces());
				validInterfaces.addAll(loadedClasses.getAdapterImplementation());
				String[] interfaces = LoadedClassesContainer.converToArray(validInterfaces);
				
				adapterInterfaceCombo.setItems(interfaces);
				adapterImplementationCombo.setItems(implementations);
				adapterServiceCombo.setItems(services);
				adapterServiceFactoryCombo.setItems(factories);
				
				if (interfaces.length > 0) adapterInterfaceCombo.select(0);
				if (implementations.length > 0) adapterImplementationCombo.select(0);
				if (services.length > 0) adapterServiceCombo.select(0);
				if (factories.length > 0) adapterServiceFactoryCombo.select(0);
				setPageComplete(interfaces.length > 0 && implementations.length>0 && services.length>0 && factories.length>0);
				
			} catch (Exception e) {
				e.printStackTrace();
				setPageComplete(false);
			} 
		}
	}

	private void storeValues(){
		adapterInterface = LoadedClassesContainer.convertToPair(adapterInterfaceCombo.getText());
		adapterImplementation = LoadedClassesContainer.convertToPair(adapterImplementationCombo.getText());
		adapterService = LoadedClassesContainer.convertToPair(adapterServiceCombo.getText());
		adapterServiceFactory = LoadedClassesContainer.convertToPair(adapterServiceFactoryCombo.getText());
	}
	
	@Override
	protected String getContextName() {
		return null;
	}

	public String getJarPath() {
		return jarPath;
	}
	
	public void setCustomAdapterInfo(AdapterInfo info){
		info.setDataAdapterInterface(adapterInterface);
		info.setDataAdapterService(adapterService);
		info.setDataAdapterImplementation(adapterImplementation);
		info.setDataAdapterServiceFactory(adapterServiceFactory);
	}

	public boolean createDynamicControls(){
		return generateDynamicControls;
	}
}
