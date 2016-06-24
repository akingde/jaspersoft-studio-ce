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
package com.jaspersoft.studio.property.dataset.wizard;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.command.IQueryLanguageChanged;
import com.jaspersoft.studio.model.command.SyncDatasetRunCommand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.command.DeleteDatasetCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SyncDatasetRunParameters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Wizard page to create the language parameters for the selected dataset, the main dataset
 * and all the dataset run associated with the selected dataset. The parameters are created
 * only where needed. This can also be used to switch the language of a subdataset and connect
 * it to the main dataset troguh the parameters. It will also update all the dataset run associated
 * with the subdataset
 * 
 * @author Orlandin Marco
 *
 */
public class ConnectToDatasetWizardPage extends JSSHelpWizardPage {
	
	/**
	 * Add the requested parameters and remove the not requested ones from 
	 * the subdataset and main dataset
	 */
	protected IQueryLanguageChanged syncExecuter =  new IQueryLanguageChanged() {
		
		@Override
		public void syncDataset(JasperDesign jd, JRDesignDataset ds, String oldLang, String newLang) throws JRException {
			
			boolean isMainDataset = (ds == jd.getMainDataset());
			if (!isMainDataset){
				QueryExecuterFactory oldFactory = getFactory(oldLang);
				if (oldFactory != null){
					Object[] builtInParameters = oldFactory.getBuiltinParameters();
					if (builtInParameters != null){
						for(int i=0; i<builtInParameters.length; i+=2){
							String parameterName = (String)builtInParameters[i];
							ds.removeParameter(parameterName);
						}
					}
				}
			}
			QueryExecuterFactory newFactory = getFactory(newLang);
			if (newFactory != null) {
				Object[] builtInParameters = newFactory.getBuiltinParameters();
				if (builtInParameters != null){
					setupDataset(ds, builtInParameters);
					if (!isMainDataset) {
						ds = jd.getMainDesignDataset();
						setupDataset(ds, builtInParameters);			
					}
				}
			}
		}

		protected void setupDataset(JRDesignDataset ds, Object[] builtInParameters) throws JRException {
			for(int i=0; i<builtInParameters.length; i+=2){
				String parameterName = (String)builtInParameters[i];
				String paramterType = builtInParameters[i+1].toString();
				ds.removeParameter(parameterName);
				if (!ds.getParametersMap().containsKey(parameterName)) {
					JRDesignParameter p = new JRDesignParameter();
					p.setName(parameterName);
					p.setValueClassName(paramterType);
					p.setForPrompting(false);
					ds.addParameter(p);
				}
			}
		}

	};

	/**
	 * Cache map for a language name and its execution factory
	 */
	protected static final HashMap<String, QueryExecuterFactory> factories = new HashMap<String, QueryExecuterFactory>();
	
	/**
	 * The selected dataset
	 */
	private MDataset connectedDataset;
	
	/**
	 * A list of element that have a dataset run connected to the selected dataset
	 */
	protected List<IDatasetContainer> runReferences;
	
	/**
	 * List of needed parameters missing on the main dataset
	 */
	protected List<String> missingParamOnMain;
	
	/**
	 * List of needed parameters missing on the selected dataset
	 */
	protected List<String> missingParamOnDataset;
	
	/**
	 * List of needed parameters missing on the selected dataset
	 */
	protected List<String> removedParamOnDataset;
	
	/**
	 * Combo where you can select the language of the selected sub dataset
	 */
	protected Combo dataAdapterLanguage;
	
	/**
	 * Main composite where the list of the change on the parameters in written
	 */
	protected Composite mainComposite;
	
	/**
	 * Scrolled composite for the main composite
	 */
	protected ScrolledComposite scrollComposite;
	
	/**
	 * List of all the available languages for the subdataset
	 */
	private String[] availableLanguages;
	
	/**
	 * List of all the query executer bundles for the the current jasperconfiguration
	 */
	private List<JRQueryExecuterFactoryBundle> bundles;
	
	/**
	 * Command used to synchronized the dataset parameters
	 */
	private SyncDatasetRunCommand command;
	
	/**
	 * Contains every dataset run for the selected dataset (reference of the dataset run uses as key) and 
	 * as value has an info container. The info container basically contains a list of the parameters needed
	 * by the dataset run and the element that contains the dataset run
	 */
	private HashMap<MDatasetRun, InfoContainer> missingParamOnRun;
	
	/**
	 * Contains every dataset run for the selected dataset (reference of the dataset run uses as key) and 
	 * as value has an info container. The info container basically contains a list of the parameters are not needed
	 * by the dataset run and that can be removed
	 */
	private HashMap<MDatasetRun, InfoContainer> removedParamOnRun;
	
	/**
	 * Map that associate to each parameter (identified by its name) a java type
	 */
	private final HashMap<String, String> typeMap = new HashMap<String, String>();
	
	/**
	 * Create the page 
	 * 
	 * @param connectedDataset Dataset selected by the user
	 */
	public ConnectToDatasetWizardPage(MDataset connectedDataset) {
		super("connectionpage"); //$NON-NLS-1$
		setTitle(Messages.ConnectToDatasetWizardPage_dialogTitle);
		setDescription(Messages.ConnectToDatasetWizardPage_dialogDescription);
		this.connectedDataset = connectedDataset;
		JasperReportsConfiguration jConfig = connectedDataset.getJasperConfiguration();
		availableLanguages = ModelUtils.getQueryLanguagesOnly(jConfig);
		bundles = jConfig.getExtensions(JRQueryExecuterFactoryBundle.class);
	}

	
	@Override
	public void createControl(Composite parent) {
		Composite pageContainer = new Composite(parent, SWT.NONE);
		pageContainer.setLayout(new GridLayout(1,false));
		pageContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite comboContainer = new Composite(pageContainer, SWT.NONE);
		comboContainer.setLayout(new GridLayout(2,false));
		comboContainer.setData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(comboContainer, SWT.NONE).setText(Messages.ConnectToDatasetWizardPage_languageLabel);
		dataAdapterLanguage = new Combo(comboContainer, SWT.READ_ONLY);
		dataAdapterLanguage.setItems(availableLanguages);
		
		
		//Create the appropriate controls for this parameters
	  scrollComposite = new ScrolledComposite(pageContainer, SWT.V_SCROLL);
	  scrollComposite.setExpandVertical(true);
	  scrollComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		mainComposite = new Composite(scrollComposite, SWT.NONE);
		scrollComposite.setContent(mainComposite);
		mainComposite.setLayout(new GridLayout(1,false));
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		JRDesignDataset dataset =connectedDataset.getValue();
		int index = 0;
		if (dataset != null && dataset.getQuery() != null){
			String queryLanguage = dataset.getQuery().getLanguage();
			for(int i = 0; i< availableLanguages.length; i++){
				String item = availableLanguages[i];
				if (ModelUtils.safeEquals(item, queryLanguage)){
					index = i;
					break;
				}
			}
		}
		dataAdapterLanguage.select(index); 
		
		dataAdapterLanguage.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				updateContent();
				getContainer().updateButtons();
			}
		});
		
		updateContent();
		UIUtils.resizeAndCenterShell(parent.getShell(), 650, 550);
		setControl(pageContainer);
	}
	
	protected void updateContent(){
		for(Control child : mainComposite.getChildren()){
			child.dispose();
		}
		//Calculate which parameters are needed and where they are needed
		String datasetName = (String)connectedDataset.getPropertyActualValue(JRDesignDataset.PROPERTY_NAME);
		runReferences = DeleteDatasetCommand.getDatasetUsage(connectedDataset.getRoot().getChildren(), datasetName);
		List<String> neededParameters = getNeededParameters();
		List<String> removedParameters = getRemovedParameters();
		
		missingParamOnMain = getMissingParameterOnMainDataset(neededParameters);
		missingParamOnDataset = getMissingParameterOnDataset(neededParameters);
		missingParamOnRun = getMissingDatasetsRun(neededParameters);
		
		removedParamOnRun = getRemovedDatasetsRun(removedParameters, neededParameters);
		removedParamOnDataset = getRemovedParameterOnDataset(removedParameters, neededParameters);
		
		if (missingParamOnMain.isEmpty() && missingParamOnDataset.isEmpty() && missingParamOnRun.isEmpty() && 
					removedParamOnRun.isEmpty() && removedParamOnDataset.isEmpty() && dataAdapterLanguage.getText().equals(getOldLanguage())){
			new Label(mainComposite, SWT.NONE).setText(Messages.ConnectToDatasetWizardPage_noChangesLabel);
		} else {
			createNotEmptyContent(mainComposite);
		}
		scrollComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		mainComposite.setSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.layout(true, true);
		command = regenerateCommand();
	}
	
	/**
	 * Generate the command to synchronize the parameters
	 * 
	 * @return a not null command
	 */
	private SyncDatasetRunCommand regenerateCommand(){
		JRDesignDataset dataset = connectedDataset.getValue();
		String queryLanguage = null;
		if (dataset != null && dataset.getQuery() != null){
			queryLanguage = dataset.getQuery().getLanguage();
		}
		
		return new SyncDatasetRunCommand(connectedDataset, dataAdapterLanguage.getText(), queryLanguage){
			public void execute() {
				try{
					((JRDesignQuery)dataset.getValue().getQuery()).setLanguage(newLang);
					syncExecuter.syncDataset(dataset.getJasperDesign(), dataset.getValue(), oldLang, newLang);
					SyncDatasetRunParameters.syncDataset(dataset, oldLang, newLang, true);
				} catch (JRException ex){
					UIUtils.showError(ex);
					JaspersoftStudioPlugin.getInstance().logError(ex);
				}
			}
			
			@Override
			public void undo() {
				try {
					((JRDesignQuery)dataset.getValue().getQuery()).setLanguage(oldLang);
					syncExecuter.syncDataset(dataset.getJasperDesign(), dataset.getValue(), newLang, oldLang);
					SyncDatasetRunParameters.syncDataset(dataset, newLang, oldLang, true);
				} catch (JRException ex) {
					UIUtils.showError(ex);
					JaspersoftStudioPlugin.getInstance().logError(ex);
				}
			}
		};
	}
	
	/**
	 * Return a query executer factory for a specific language, the result is cached
	 * until the dialog is closed
	 * 
	 * @param language the language
	 * @return a factory for the language or null if it can't be found
	 */
	private QueryExecuterFactory getFactory(String language){
			if (language == null) return null;
			if (factories.containsKey(language)) return factories.get(language);
			for (JRQueryExecuterFactoryBundle bundle : bundles) {
				try {
					QueryExecuterFactory factory = bundle.getQueryExecuterFactory(language);
					if (factory != null) {
						factories.put(language, factory);
						return factory;
					}
				} catch (JRException e) {
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
			}
			return null;
	}
	
	/**
	 * Create the controls to show the list of parameters that will be created at the end of the wizard
	 * 
	 * @param mainComposite parent control
	 */
	private void createNotEmptyContent(Composite mainComposite){
		//The parameters added for every section (main dataset, selected dataset and dataset runs) are at least one of them
		String newLanguage = dataAdapterLanguage.getText();
		String oldLanguage = getOldLanguage();
		
		if (!ModelUtils.safeEquals(oldLanguage, newLanguage)){
			new Label(mainComposite, SWT.NONE).setText(MessageFormat.format(Messages.ConnectToDatasetWizardPage_changedLanguageLabel, newLanguage));
		}
		
		if (!missingParamOnMain.isEmpty()){
			new Label(mainComposite, SWT.NONE).setText(Messages.ConnectToDatasetWizardPage_additionToMaindataset);
			Composite parameterComposite = new Composite(mainComposite, SWT.NONE);
			parameterComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout listLayout = new GridLayout(1, false);
			listLayout.marginLeft = 15;
			parameterComposite.setLayout(listLayout);
			for(String missingParam : missingParamOnMain){
				new Label(parameterComposite, SWT.NONE).setText(missingParam);
			}
		}
		
		if (!missingParamOnDataset.isEmpty()){
			new Label(mainComposite, SWT.NONE).setText(MessageFormat.format(Messages.ConnectToDatasetWizardPage_additionToSelectedDataset, connectedDataset.getValue().getName()));
			Composite parameterComposite = new Composite(mainComposite, SWT.NONE);
			parameterComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout listLayout = new GridLayout(1, false);
			listLayout.marginLeft = 15;
			parameterComposite.setLayout(listLayout);
			for(String missingParam : missingParamOnDataset){
				new Label(parameterComposite, SWT.NONE).setText(missingParam);
			}
		}
		
		if (!removedParamOnDataset.isEmpty()){
			new Label(mainComposite, SWT.NONE).setText(MessageFormat.format(Messages.ConnectToDatasetWizardPage_removedFromDataset, connectedDataset.getValue().getName()));
			Composite parameterComposite = new Composite(mainComposite, SWT.NONE);
			parameterComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridLayout listLayout = new GridLayout(1, false);
			listLayout.marginLeft = 15;
			parameterComposite.setLayout(listLayout);
			for(String missingParam : removedParamOnDataset){
				new Label(parameterComposite, SWT.NONE).setText(missingParam);
			}
		}
		
		//Show the parameters that will be added or removed on the dataset runs
		for(IDatasetContainer datasetRuns : runReferences){
			for(MDatasetRun datasetRun : datasetRuns.getDatasetRunList()){
				
				InfoContainer missingContainer = missingParamOnRun.get(datasetRun);
				if (missingContainer != null){
					IDatasetContainer element = missingContainer.getContainer();
					String parentName = null;
					if (element instanceof APropertyNode){
						APropertyNode nodeElement = (APropertyNode)element;
						parentName = nodeElement.getDisplayText();
					}
					Label titleLabel = new Label(mainComposite, SWT.NONE);
					if (parentName != null) titleLabel.setText(MessageFormat.format(Messages.ConnectToDatasetWizardPage_additionToDatasetRun, new Object[]{parentName})); 
					else titleLabel.setText(Messages.ConnectToDatasetWizardPage_additionToDatasetRun2);
					Composite parameterComposite = new Composite(mainComposite, SWT.NONE);
					parameterComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
					GridLayout listLayout = new GridLayout(1, false);
					listLayout.marginLeft = 15;
					parameterComposite.setLayout(listLayout);
					for(String missingParam : missingContainer.getMissingParameters()){
							new Label(parameterComposite, SWT.NONE).setText(missingParam);
					}
				}
				
				InfoContainer removedContainer = removedParamOnRun.get(datasetRun);
				if (removedContainer != null){
					IDatasetContainer element = removedContainer.getContainer();
					String parentName = null;
					if (element instanceof APropertyNode){
						APropertyNode nodeElement = (APropertyNode)element;
						parentName = nodeElement.getDisplayText();
					}
					Label titleLabel = new Label(mainComposite, SWT.NONE);
					if (parentName != null) titleLabel.setText(MessageFormat.format(Messages.ConnectToDatasetWizardPage_removedFromDatasetRun1, new Object[]{parentName})); 
					else titleLabel.setText(Messages.ConnectToDatasetWizardPage_removedFromDatasetRun2);
					Composite parameterComposite = new Composite(mainComposite, SWT.NONE);
					parameterComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
					GridLayout listLayout = new GridLayout(1, false);
					listLayout.marginLeft = 15;
					parameterComposite.setLayout(listLayout);
					for(String missingParam : removedContainer.getMissingParameters()){
							new Label(parameterComposite, SWT.NONE).setText(missingParam);
					}
				}
			}
		}
	}
	
	/**
	 * Return the list (not null) of parameters that need to be added to the main dataset
	 * 
	 * @return a not null list of string, representing the missing parameters name
	 */
	private List<String> getMissingParameterOnMainDataset(List<String> neededParameters){
		List<String> result = new ArrayList<String>();
		JasperDesign design = connectedDataset.getJasperDesign();
		for(String neededParameter : neededParameters){
			if (!design.getParametersMap().containsKey(neededParameter)) result.add(neededParameter);
		}
		return result;
	}
	
	/**
	 * Return the list (not null) of parameters that need to be added to the selected dataset
	 * 
	 * @return a not null list of string, representing the missing parameters name
	 */
	private List<String> getMissingParameterOnDataset(List<String> neededParameters){
		List<String> result = new ArrayList<String>();
		Map<String, JRParameter> parametersMap = connectedDataset.getValue().getParametersMap();
		for(String neededParameter : neededParameters){
			if (!parametersMap.containsKey(neededParameter)) result.add(neededParameter);
		}
		return result;
	}
	
	/**
	 * Return the list (not null) of parameters that need to be removed frim the selected dataset
	 * 
	 * @return a not null list of string, representing the not needed parameters name
	 */
	private List<String> getRemovedParameterOnDataset(List<String> removedParameters, List<String> neededParameters){
		List<String> result = new ArrayList<String>();
		Map<String, JRParameter> parametersMap = connectedDataset.getValue().getParametersMap();
		for(String removedParameter : removedParameters){
			if (parametersMap.containsKey(removedParameter) && !neededParameters.contains(removedParameter)) result.add(removedParameter);
		}
		return result;
	}
	
	/**
	 * Return the list (not null) of parameters that need to be  to the passed dataset run
	 * 
	 * @param the dataset run to check
	 * @return a not null list of string, representing the missing parameters name
	 */
	private List<String> getMissingParameterOnDatasetRun(MDatasetRun datasetRun, List<String> neededParameters){
		List<String> result = new ArrayList<String>();
		JRDatasetParameter[] parameters = datasetRun.getValue().getParameters();
		HashSet<String> definedParameters = new HashSet<String>();
		for(JRDatasetParameter parameter : parameters){
			definedParameters.add(parameter.getName());
		}
		for(String neededParameter :  neededParameters){
			if (!definedParameters.contains(neededParameter)) result.add(neededParameter);
		}
		return result;
	}
	
	/**
	 * Return the list (not null) of parameters that can be removed from the dataset run
	 * 
	 * @param the dataset run to check
	 * @param removedParameters parameters that depends from the old language and can be removed
	 * @param neededParameters parameters that depends from the new language and will be added
	 * @return a not null list of string, representing the not needed parameters names
	 */
	private List<String> getRemovedParameterOnDatasetRun(MDatasetRun datasetRun, List<String> removedParameters, List<String> neededParameters){
		List<String> result = new ArrayList<String>();
		JRDatasetParameter[] parameters = datasetRun.getValue().getParameters();
		HashSet<String> definedParameters = new HashSet<String>();
		for(JRDatasetParameter parameter : parameters){
			String pname = parameter.getName();
			if (parameter.getExpression() != null && parameter.getExpression().getText() != null){
				String expText = parameter.getExpression().getText();
				if (expText.equals("$P{" + pname + "}")){ //$NON-NLS-1$ //$NON-NLS-2$
					definedParameters.add(pname);
				}
			}
		}
	
		for(String removedParameter :  removedParameters){
			if (definedParameters.contains(removedParameter) && !neededParameters.contains(removedParameter)){ 
				result.add(removedParameter);
			}
		}
		return result;
	}
	
	/**
	 * Return an hashmap that contains every dataset run for the selected dataset (reference of the dataset run uses as key) and 
	 * as value has an info container. The info container basically contains a list of the parameters needed
	 * by the dataset run and the element that contains the dataset run
	 */
	private HashMap<MDatasetRun, InfoContainer> getMissingDatasetsRun(List<String> neededParameters){
		HashMap<MDatasetRun, InfoContainer> result = new HashMap<MDatasetRun,InfoContainer>();	
		for(IDatasetContainer container : runReferences){
			List<MDatasetRun> runList = container.getDatasetRunList();
			for(MDatasetRun run : runList){
				InfoContainer missingParamters = result.get(run);
				if (missingParamters == null){
					List<String> missingParametersName = getMissingParameterOnDatasetRun(run, neededParameters);
					if (!missingParametersName.isEmpty()) result.put(run, new InfoContainer(missingParametersName, container, run));
				}
			}
		}
		return result;
	}
	
	/**
	 * Return an hashmap that contains every dataset run for the selected dataset (reference of the dataset run uses as key) and 
	 * as value has an info container. The info container basically contains a list of the parameters not needed
	 * by the dataset run and the element that contains the dataset run, and this parameters can be removed
	 * 
	 * @param removedParameters parameters that depends from the old language and can be removed
	 * @param neededParameters parameters that depends from the new language and will be added
	 */
	private HashMap<MDatasetRun, InfoContainer> getRemovedDatasetsRun(List<String> removedParameters, List<String> neededParameters){
		HashMap<MDatasetRun, InfoContainer> result = new HashMap<MDatasetRun, InfoContainer>();	
		for(IDatasetContainer container : runReferences){
			List<MDatasetRun> runList = container.getDatasetRunList();
			for(MDatasetRun run : runList){
				InfoContainer removedParamters = result.get(run);
				if (removedParamters == null){
					List<String> removedParametersName = getRemovedParameterOnDatasetRun(run, removedParameters, neededParameters);
					if (!removedParametersName.isEmpty()) result.put(run, new InfoContainer(removedParametersName, container, run));
				}
			}
		}
		return result;
	}
	
	/**
	 * Return the type for a specific parameter. It uses the parameter
	 * name to look in the type map. If the type can not be found it
	 * will use object as default
	 * 
	 * @param parameterName the parameter name
	 * @return a not null java class type
	 */
	protected String getParameterType(String parameterName){
		String type = typeMap.get(parameterName);
		return type != null ? type : "java.lang.Object"; //$NON-NLS-1$
	}
	
	/**
	 * Add the missing parameters where they are needed. Not used, substituted 
	 * by the synchronization command
	 */
	/*public void doAction(){
		JasperDesign design = connectedDataset.getJasperDesign();
		for(String missingParam : missingParamOnMain){
			JRDesignParameter param = new JRDesignParameter();
			param.setName(missingParam);
			param.setValueClassName(getParameterType(missingParam)); 
			try {
				design.addParameter(param);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		
		JRDesignDataset dataset = connectedDataset.getValue();
		for(String missingParam : missingParamOnDataset){
			JRDesignParameter param = new JRDesignParameter();
			param.setName(missingParam);
			param.setValueClassName(getParameterType(missingParam)); 
			try {
				dataset.addParameter(param);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		
		for(InfoContainer container : missingParamOnRun.values()){
			JRDesignDatasetRun run = container.getRun().getValue();
			for(String missingParam : container.getMissingParameters()){
				JRDesignDatasetParameter param = new JRDesignDatasetParameter();
				param.setName(missingParam);
				JRDesignExpression exp = new JRDesignExpression();
				exp.setText("$P{"+missingParam+"}"); //$NON-NLS-1$ //$NON-NLS-2$
				param.setExpression(exp);
				try {
					run.addParameter(param);
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
	
	/**
	 * Return the current language defined inside the dataset
	 * 
	 * @return the sub dataset language or the empty string if no language
	 * is defined
	 */
	private String getOldLanguage(){
		JRDesignDataset dataset = connectedDataset.getValue();
		if (dataset != null && dataset.getQuery() != null){
			String language = dataset.getQuery().getLanguage();
			return language;
		}
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Return the list of required parameters
	 * 
	 * @return a not null list of required parameters
	 */
	protected List<String> getNeededParameters(){
		typeMap.clear();
		String language = dataAdapterLanguage.getText();
		QueryExecuterFactory factory = getFactory(language);
		List<String> parameters = new ArrayList<String>();
		if (factory != null){
			Object[] builtInParameters = factory.getBuiltinParameters();
			if (builtInParameters != null){
				for(int i=0; i<builtInParameters.length; i+=2){
					String parameterName = (String)builtInParameters[i];
					String paramterType = builtInParameters[i+1].toString();
					parameters.add(parameterName);
					typeMap.put(parameterName, paramterType);
				}
			}
		}
		return parameters;
	}
	
	/**
	 * Return the  list of removed parameters, that are the parameters
	 * requested by the old language
	 * 
	 * @return list of the parameters required from the old language, not null
	 */
	protected List<String> getRemovedParameters(){
		JRDesignDataset dataset = connectedDataset.getValue();
		List<String> parameters = new ArrayList<String>();
		if (dataset != null && dataset.getQuery() != null){
			String language = dataset.getQuery().getLanguage();
			QueryExecuterFactory factory = getFactory(language);		
			if (factory != null){
				Object[] builtInParameters = factory.getBuiltinParameters();
				if (builtInParameters != null){
					for(int i=0; i<builtInParameters.length; i+=2){
						String parameterName = (String)builtInParameters[i];
						parameters.add(parameterName);
					}
				}
			}
		}
		return parameters;
	}
	
	/**
	 * True if there are parameters to add or remove, false otherwise
	 */
	public boolean canFinish(){
		if (runReferences == null || missingParamOnMain == null || missingParamOnDataset == null || removedParamOnDataset == null) return false;
		if (missingParamOnDataset.isEmpty() && missingParamOnMain.isEmpty() && missingParamOnRun.isEmpty() &&
					removedParamOnDataset.isEmpty() && removedParamOnRun.isEmpty() && ModelUtils.safeEquals(getOldLanguage(), dataAdapterLanguage.getText())) return false;
		return true;
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_CONNECT_TO_DOMAIN;
	}
	
	/**
	 * Return the current command to synchronize the parameters
	 * 
	 * @return a command
	 */
	public SyncDatasetRunCommand getCommand(){
		return command;
	}
}
