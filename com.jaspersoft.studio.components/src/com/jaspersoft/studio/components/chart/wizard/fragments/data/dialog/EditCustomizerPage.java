/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.ContextHelpIDs;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.widgets.framework.ui.SeriesSelectionPanelManager;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedListener;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.manager.panel.BasePanelManager;
import com.jaspersoft.studio.widgets.framework.manager.panel.IPanelManager;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.engine.NamedChartCustomizer;

/**
 * Page used to edit the properties of a customizer. It show the controls defined
 * by the {@link ChartCustomizerDefinition} to build a UI for the custmizer
 * 
 * @author Orlandin Marco
 *
 */
public class EditCustomizerPage extends JSSHelpWizardPage {
	
	/**
	 * {@link DtoPropertyEditor} that simply trigger the validation of this page when 
	 * a property change value
	 *
	 */
	private class DialogDtoPropertyEditor extends DtoPropertyEditor {
		
		public DialogDtoPropertyEditor(String keyPrefix, CustomizerPropertyExpressionsDTO propertiesDTO) {
			super(keyPrefix, propertiesDTO);
		}

		public void createUpdateProperty(String propertyName, String value, net.sf.jasperreports.engine.JRExpression valueExpression) {
			super.createUpdateProperty(propertyName, value, valueExpression);
			//this will trigger the validate
			getContainer().updateButtons();
		};
		
		public void removeProperty(String propertyName) {
			super.removeProperty(propertyName);
			//this will trigger the validate
			getContainer().updateButtons();
		};		
	}; 

	/**
	 * Composite where the controls are created
	 */
	private Composite dynamicParent;
	
	/**
	 * Scrolled composite where the elements are created
	 */
	private ScrolledComposite scrolledContainer;
	
	/**
	 * Expression context for the dynamic controls
	 */
	private ExpressionContext ec;
	
	/**
	 * DTO of where the changes are stored and from where the value are read
	 */
	private CustomizerPropertyExpressionsDTO dto;
	
	/**
	 * {@link JasperReportsConfiguration} of the current report
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The currently edited element 
	 */
	private ChartCustomizerDefinition editedElement;
	
	/**
	 * The panel manager used to build the UI of the customizer
	 */
	private IPanelManager currentPanelManager = null;

	/**
	 * Create the page 
	 * 
	 * @param jConfig {@link JasperReportsConfiguration} of the current report
	 * @param ec {@link ExpressionContext} used for the dynamic widgets
	 * @param dto {@link CustomizerPropertyExpressionsDTO} from where the value are read and stored
	 */
	public EditCustomizerPage(JasperReportsConfiguration jConfig, ExpressionContext ec, CustomizerPropertyExpressionsDTO dto) {
		super("customizerEditPage"); //$NON-NLS-1$
		this.dto = dto;
		this.jConfig = jConfig;
		this.ec = ec;
		setMessage(getCustmizerMessage());
	}
	
	protected String getCustmizerMessage(){
		if (getCurrentDefinition() != null && getCurrentDefinition().getDescriptor() != null){
			return getCurrentDefinition().getDescriptor().getDescription();
		} else return Messages.EditCustomizerPage_pageMessage;
	}
	
	@Override
	public void createControl(Composite parent) {		
		scrolledContainer = new ScrolledComposite(parent, SWT.V_SCROLL);
		scrolledContainer.setExpandHorizontal(true);
		scrolledContainer.setExpandVertical(true);
		dynamicParent = new Composite(scrolledContainer, SWT.NONE);
		scrolledContainer.setContent(dynamicParent);
		setControl(scrolledContainer);
	}
	
	/**
	 * When this step is visible reinitialize the controls
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			//Dispose the old panel manager if present
			if(currentPanelManager != null){
				currentPanelManager.disposeWidgets();
				currentPanelManager = null;
			}	
			setTitle(getCurrentDefinition().getDescriptor().getLabel());
			ChartCustomizerDefinition selectedDefinition = getCurrentDefinition();
			String propertyKeyPrefix = NamedChartCustomizer.CUSTOMIZER_PROPERTY_PREFIX + selectedDefinition.getKey() + '.';
			DialogDtoPropertyEditor pEditor = new DialogDtoPropertyEditor(propertyKeyPrefix, dto);
			WidgetsDescriptor cd = selectedDefinition.getDescriptor();
			
			//Get the panel manager from the customizer or use a default one if not available
			currentPanelManager = cd.getPanelManager(dynamicParent);
			if (currentPanelManager == null) {
				currentPanelManager = new BasePanelManager(dynamicParent);
			}
			
			//Create the widgets trough the panel manager
			for(WidgetPropertyDescriptor p : cd.getPlainWidgets()) {
				ItemPropertyDescription<?> descriptor = WidgetFactory.createItemPropertyDescriptor(cd, p, jConfig);
				IWItemProperty widget = currentPanelManager.createWidget(p, descriptor, pEditor, ec);
				if (p.getName().equals(SeriesSelectionPanelManager.SERIES_SELECTION_TYPE_PROPERTY)){
					widget.addModifyListener(new ItemPropertyModifiedListener() {
						
						@Override
						public void itemModified(ItemPropertyModifiedEvent event) {
							getContainer().updateButtons();
						}
					});
				} 
			}
			
			//Update the widgets value
			currentPanelManager.updateWidgets();
			int compositeHeight = dynamicParent.computeSize(500, SWT.DEFAULT).y;
			scrolledContainer.setMinHeight(compositeHeight);
			dynamicParent.layout(true, true);
			
			//this will trigger the validate
			getContainer().updateButtons();
		}
	}
	
	/**
	 * Validate the input. It check if there is at least a widget that fails its validation and
	 * return an error
	 */
	protected boolean validate(){
		if (currentPanelManager != null){
			List<String> invalidProperty = currentPanelManager.validateWidgets(true);
			if (invalidProperty != null && !invalidProperty.isEmpty()){
				setErrorMessage(invalidProperty.get(0));
				return false;
			}
		}
		setMessage(getCustmizerMessage());
		setErrorMessage(null);
		return true;
	}
	
	@Override
	public boolean isPageComplete() {
		return validate();
	}
	
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_CONFIGURE_CUSTOMIZER;
	}
	
	/**
	 * Set the edited definition
	 * 
	 * @param editedElement the {@link ChartCustomizerDefinition} edited in this step, must
	 * be not null
	 */
	public void setEditedElement(ChartCustomizerDefinition editedElement){
		this.editedElement = editedElement;
	}
	
	/**
	 * Return the edited definition.
	 * 
	 * @return the definition edited in this step
	 */
	protected ChartCustomizerDefinition getCurrentDefinition(){
		return editedElement;
	}
	
	/**
	 * This page has never a next page
	 */
	@Override
	public IWizardPage getNextPage() {
		return null;
	}
}
