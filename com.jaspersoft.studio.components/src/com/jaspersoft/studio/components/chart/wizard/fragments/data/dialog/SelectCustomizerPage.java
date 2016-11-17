/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.chart.ContextHelpIDs;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerDefinitionManager;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerWidgetsDescriptor;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRChartPlot;

/**
 * Dialog to select a chart customizer definition from the preferences or providing 
 * a {@link JRChartCustomizer} classname
 * 
 * @author Orlandin Marco
 *
 */
public class SelectCustomizerPage extends JSSHelpWizardPage {
	
	/**
	 * Provider to show orange the customizer that are defined in the preferences but doesn't have
	 * the required class in the classpath
	 */
	private class CustomizerLabelProvier extends ColumnLabelProvider implements ITableColorProvider {

		@Override
		public Color getForeground(Object element, int columnIndex) {
			CustomizerWidgetsDescriptor desc = (CustomizerWidgetsDescriptor)element;
			if (desc != userDefinedEntry && desc.getCustomizerClass() != null){
				String className = desc.getCustomizerClass();
				try{
					jConfig.getClassLoader().loadClass(className);
				} catch(Exception ex){
					return ColorConstants.orange;
				} catch (Error e) {
					return ColorConstants.orange;
				}
			}
			return ColorConstants.black;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}	
	}
	
	/**
	 * Provider to show show only the compatible charts customizer if the first option of the combo
	 */
	private class CompatibleContentProvider extends ListContentProvider {
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement != null && inputElement instanceof List){
				if (showAllElements != null && !showAllElements.isDisposed() && showAllElements.getSelectionIndex() == 1){
					return ((List<?>)inputElement).toArray();
				}
				List<Object> result = new ArrayList<Object>();
				for(Object element : (List<?>)inputElement){
					CustomizerWidgetsDescriptor desc = (CustomizerWidgetsDescriptor)element;
					if (desc.isPlotSupported(selectedChartPlot)){
						result.add(element);
					}
				}
				return result.toArray();
			}
			return new Object[0];
		}
	}
	
	/**
	 * The {@link ChartCustomizerDefinition} created from the controls of the preferences customizer (first tab)
	 */
	private ChartCustomizerDefinition valueSelection = null;
	
	/**
	 * Key to be used when a {@link ChartCustomizerDefinition} is created
	 */
	private String definitionKey;
	
	/**
	 * List of the customizer available in the preferences
	 */
	private List<CustomizerWidgetsDescriptor> tableInput;
	
	/**
	 * Special fake entry added to the input of the table to define an user provided customizer, by typing
	 * its classname
	 */
	private CustomizerWidgetsDescriptor userDefinedEntry;
	
	/**
	 * The preferences where the customizer can be selected among the ones defined in the preferences
	 */
	private TableViewer table;
	
	/**
	 * The plot of the selected chart
	 */
	private JRChartPlot selectedChartPlot;
	
	/**
	 * Combo to show all the customizers, even those not compatible with the current plot
	 */
	private Combo showAllElements;
	
	/**
	 * The {@link JasperReportsConfiguration} of the current report
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The number of chart customizers currently defined in the chart
	 */
	private int currentChartCustmizers = 0;
	
	/**
	 * Create a dialog to define a new customizer
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 * @param definitionKey an unique new key for the new {@link ChartCustomizerDefinition}
	 * @param dto the dto changed during this wizard page, it should be a copy in case the wizard is cancelled
	 * @param selectedChartPlot the plot of the chart where the customizer is added
	 */
	public SelectCustomizerPage(JasperReportsConfiguration jConfig, String definitionKey, CustomizerPropertyExpressionsDTO dto, JRChartPlot selectedChartPlot) {
		super("customizerTypeSelection"); //$NON-NLS-1$
		this.jConfig = jConfig;
		tableInput = new ArrayList<CustomizerWidgetsDescriptor>(CustomizerDefinitionManager.getCustomizerDefinitions(jConfig));
		userDefinedEntry = new CustomizerWidgetsDescriptor(){
			public boolean isPlotSupported(JRChartPlot plot) {
				return true;
			};
		};
		userDefinedEntry.setLabel(Messages.SelectCustomizerPage_customzierClassEntry);
		tableInput.add(userDefinedEntry);
		this.definitionKey = definitionKey;
		this.selectedChartPlot = selectedChartPlot;
		this.currentChartCustmizers = dto.getCustomizersNumber();
	}
	
	/**
	 * Create the table area

	 * @param tab tab where the controls will be created
	 */
	protected void createTableArea(Composite parent){
		table = new TableViewer(parent, SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		GridData tableData = new GridData(GridData.FILL_BOTH);
		table.getTable().setLayoutData(tableData);
		table.setContentProvider(new CompatibleContentProvider());
		table.setLabelProvider(new CustomizerLabelProvier());
		table.setInput(tableInput);
		table.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if(selection.getFirstElement() != null){
					CustomizerWidgetsDescriptor selCustomizer = (CustomizerWidgetsDescriptor)selection.getFirstElement();
					valueSelection = new ChartCustomizerDefinition(selCustomizer, definitionKey);
				}	
				validate();
			}
		});
	}
	

	/**
	 * Validate the input, looking if at least one entry is selected
	 */
	protected void validate(){
		ISelection seletion = table.getSelection();
		if (seletion == null || seletion.isEmpty()){
			setErrorMessage(Messages.SelectCustomizerPage_errorNoSelection);
		}  else {
			setErrorMessage(null);
			CustomizerWidgetsDescriptor desc = (CustomizerWidgetsDescriptor)((StructuredSelection)seletion).getFirstElement();
			if (desc != userDefinedEntry && desc.getCustomizerClass() != null){
				String fullClassName = desc.getCustomizerClass();
				try{
					jConfig.getClassLoader().loadClass(fullClassName);
				} catch (Exception ex){
					String className = getClassName(fullClassName);
					String baseMessage = Messages.SelectCustomizerPage_warningInvalidClass; 
					setMessage(MessageFormat.format(baseMessage, className), DialogPage.WARNING);
					getContainer().updateButtons();
					return;
				}
			} 
			if (desc != userDefinedEntry){
				setMessage(Messages.SelectCustomizerPage_pageMessageConfigurableCustomizer);
			} else if (currentChartCustmizers > 0){
				setMessage(Messages.SelectCustomizerPage_pageMessageMultiCustomizers);
			} else setMessage(Messages.SelectCustomizerPage_pageMessage);
		}
		getContainer().updateButtons();
	}
	
	/**
	 * Return the last segment of the classname, placed after the 
	 * last . separator. If there are no separator the full class
	 * name is returned
	 * 
	 * @param fullClassName the full classname
	 * @return the last segment of the classname
	 */
	private String getClassName(String fullClassName){
		int separatorIndex = fullClassName.lastIndexOf('.');
		if (separatorIndex != -1){
			return fullClassName.substring(separatorIndex + 1);
		} else {
			return fullClassName;
		}
	}

	@Override
	public void createControl(Composite parent) {
		setTitle(Messages.SelectCustomizerPage_pageTitle);
		setMessage(Messages.SelectCustomizerPage_pageMessage);

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		createTableArea(container);	
		
		//Create the combo 
		Composite comboContainer = new Composite(container, SWT.NONE);
		GridData comboContainerData = new GridData();
		comboContainerData.horizontalAlignment = SWT.CENTER;
		comboContainer.setLayoutData(comboContainerData);
		comboContainer.setLayout(new RowLayout());
		Label comboText = new Label(comboContainer, SWT.NONE);
		comboText.setText(Messages.SelectCustomizerPage_comboLabel);
		showAllElements = new Combo(comboContainer, SWT.READ_ONLY);
		showAllElements.setItems(new String[]{Messages.SelectCustomizerPage_comboEntryCompatible,Messages.SelectCustomizerPage_comboEntryAll});
		showAllElements.select(0);
		showAllElements.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				//refresh when the combo change value
				table.refresh();
			}
		});
		setControl(container);
	}
	
	/**
	 * When the page is visible validate the content
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			validate();
		}
	}

	/**
	 * Check if the current selection is based on a Customizer Definition file or is 
	 * the fake user defined customizer
	 * 
	 * @return true if the current selection is a user defined customizer, so a classname
	 * will be expected in the next step, false otherwise
	 */
	public boolean isUsingCustomDefinition(){
		return (valueSelection != null && valueSelection.getDescriptor() == userDefinedEntry);
	}
	
	/**
	 * Return the {@link ChartCustomizerDefinition} associated to the selection in the table
	 * 
	 * @return a not null {@link ChartCustomizerDefinition}, but only when the page is marked as complete
	 */
	public ChartCustomizerDefinition getSelectedDefinition(){
		return valueSelection;
	}
	
	/**
	 * The page is complete only if something is selected in the table
	 */
	@Override
	public boolean isPageComplete() {
		return getSelectedDefinition() != null && getErrorMessage() == null;
	}
	
	/**
	 * It is possible to flip to the next page only when this one is complete and there
	 * are widgets to show for the selected customizer
	 */
	@Override
	public boolean canFlipToNextPage() {
		boolean value = isPageComplete();
		if (value){
			WidgetsDescriptor descriptor = getSelectedDefinition().getDescriptor();
			if (descriptor != userDefinedEntry && !descriptor.hasWidgets()){
				value = false;
			}
		}
		return value;
	}
	
	/**
	 * Return the next page of the wizard, depending if the current selected customizer
	 * is the definition for a raw class or it is a customizer with a UI
	 */
	@Override
	public IWizardPage getNextPage() {
		if (isUsingCustomDefinition()){
			CustomizerNewWizard parentWizard = (CustomizerNewWizard)getWizard();
			EditClassPage editClassPage = parentWizard.getEditClassPage();
			editClassPage.setRawClass(""); //$NON-NLS-1$
			return editClassPage;
		} else {
			CustomizerNewWizard parentWizard = (CustomizerNewWizard)getWizard();
			EditCustomizerPage editCustmizerPage = parentWizard.getEditCustomizerPage();
			editCustmizerPage.setEditedElement(getSelectedDefinition());
			return editCustmizerPage;			
		}
	}

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SELECT_CUSTOMIZER;
	}
}
