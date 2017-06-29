/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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

/**
 * Wizard page where the user can select an external jar as data adapter
 * and provide other informations to include that data adapter inside the 
 * plugin project
 * 
 * @author Orlandin Marco
 *
 */
public class CustomJarInformationPage extends JSSHelpWizardPage {

	/**
	 * Path of the selected jar
	 */
	private String jarPath = null;
	
	/**
	 * combo controls to select the class to use as the data adapter interface
	 */
	private Combo adapterInterfaceCombo;
	
	/**
	 * combo controls to select the class to use as the data adapter implementation
	 */
	private Combo adapterImplementationCombo;
	
	/**
	 * combo controls to select the class to use as the data adapter service
	 */
	private Combo adapterServiceCombo;
	
	/**
	 * combo controls to select the class to use as the data adapter service factory
	 */
	private Combo adapterServiceFactoryCombo;
	
	/**
	 * Store the class selected by the user as data adapter interface
	 */
	private Pair adapterInterface;
	
	/**
	 * Store the class selected by the user as data adapter implementation
	 */
	private Pair adapterImplementation;
	
	/**
	 * Store the class selected by the user as data adapter service
	 */
	private Pair adapterService;
	
	/**
	 * Store the class selected by the user as data adapter service factory
	 */
	private Pair adapterServiceFactory;
	
	/**
	 * Flag to know if the user want to generate automatically the controls to edit the data adapter
	 */
	private boolean generateDynamicControls = false;
	
	protected CustomJarInformationPage() {
		super("dataadapterselectionpage"); //$NON-NLS-1$
		setTitle(Messages.CustomJarInformationPage_title);
		setDescription(Messages.CustomJarInformationPage_description);
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
	
	/**
	 * Create the control to let the user choose if the want
	 * to generate automatically the controls to edit the data adapter 
	 * 
	 * @param parent parent of the controls
	 */
	private void createCompositeControls(Composite parent){
		Button compositeButton = new Button(parent, SWT.CHECK);
		compositeButton.setText(Messages.CustomJarInformationPage_automaticComposite);
		compositeButton.setSelection(false);
		compositeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generateDynamicControls = ((Button)e.getSource()).getSelection();
			}
		});
	}
	
	/**
	 * Generate the controls to selected a JAR on the filesystem
	 * 
	 * @param parent the parent of the controls
	 */
	private void createPathControls(Composite parent){
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(3,false));
		
		Label jarPathLabel = new Label(container, SWT.NONE);
		jarPathLabel.setText(Messages.CustomJarInformationPage_pathLabel);
		final Text jarPathText = new Text(container, SWT.BORDER);
		jarPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		jarPathText.setEditable(false);
		
		Button browseButton = new Button(container, SWT.NONE);
		browseButton.setText(Messages.common_browse);
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
	
	/**
	 * Generate the controls to select the class that define the data adapter.
	 * The selection his helped by searching the possibilities inside the specifed JAR
	 * file
	 * 
	 * @param parent the parent of the controls
	 */
	private void createClassesControls(Composite parent){
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(1,true));
		
		Composite leftColumn = new Composite(container, SWT.NONE);
		leftColumn.setLayoutData(new GridData(GridData.FILL_BOTH));
		leftColumn.setLayout(new GridLayout(2,false));
		Label adapterInterfaceLabel = new Label(leftColumn, SWT.NONE);
		adapterInterfaceLabel.setText(Messages.CustomJarInformationPage_interfaceLabel);
		adapterInterfaceCombo = new Combo(leftColumn, SWT.BORDER);
		adapterInterfaceCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label adapterImplementationLabel = new Label(leftColumn, SWT.NONE);
		adapterImplementationLabel.setText(Messages.CustomJarInformationPage_implementationLabel);
		adapterImplementationCombo = new Combo(leftColumn, SWT.BORDER);
		adapterImplementationCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label adapterServiceLabel  = new Label(leftColumn, SWT.NONE);
		adapterServiceLabel.setText(Messages.CustomJarInformationPage_serviceLabel);
		adapterServiceCombo = new Combo(leftColumn, SWT.BORDER);
		adapterServiceCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label adapterServiceFactoryLabel = new Label(leftColumn, SWT.NONE);
		adapterServiceFactoryLabel.setText(Messages.CustomJarInformationPage_serviceFactoryLabel);
		adapterServiceFactoryCombo = new Combo(leftColumn, SWT.BORDER);
		adapterServiceFactoryCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		//Listener to store the result when something in the combo changes
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
	
	/**
	 * When a jar file is selected then is it inspected to find the classes 
	 * that define the data adapter and then the combo are populated with the values of this
	 * classes to help the user to select them
	 */
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
		} else {
			setPageComplete(false);
		}
	}

	/**
	 * Store the values selected inside the combo as classes that define the data adapter
	 * inside an appropriate container
	 */
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

	/**
	 * Return the path of the selected jar
	 * 
	 * @return a not null path to a jar file
	 */
	public String getJarPath() {
		return jarPath;
	}
	
	/**
	 * Set inside the adapter info the information selected by the user
	 * as classes that define the data adapter
	 * 
	 * @param info an adapter info
	 */
	public void setCustomAdapterInfo(AdapterInfo info){
		info.setDataAdapterInterface(adapterInterface);
		info.setDataAdapterService(adapterService);
		info.setDataAdapterImplementation(adapterImplementation);
		info.setDataAdapterServiceFactory(adapterServiceFactory);
	}
	
	/**
	 * Return if the user want to create automatically the controls
	 * to edit the data adapter
	 * 
	 * @return true if the controls should be created automatically, false otherwise
	 */
	public boolean createDynamicControls(){
		return generateDynamicControls;
	}
}
