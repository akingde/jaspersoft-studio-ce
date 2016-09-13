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
package com.jaspersoft.studio.components.chart.wizard.fragments.data.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyDescriptor;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog.CustomizerEditWizard;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog.CustomizerNewWizard;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.SectionContainerComposite;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.chartcustomizers.ProxyChartCustomizer;
import net.sf.jasperreports.eclipse.ui.util.PersistentLocationWizardDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRChartPlot;

/**
 * Widget used to show the table and the dynamic controls to add/remove and 
 * edit the properties of the chart customizer. The class is abstract, the method
 * changePropertyOn must be implemented specify what to do when something on the internal 
 * properties set changes. To initialize the data field with the property on an element
 * the method update must be called
 * 
 * @author Orlandin Marco
 *
 */
public abstract class ChartCustomizerWidget {
	
	/**
	 * Provider to show orange the customizer that are defined in the preferences but doesn't have
	 * the required class in the classpath
	 */
	private class CustomizerLabelProvier extends ColumnLabelProvider implements ITableColorProvider {

		@Override
		public Color getForeground(Object element, int columnIndex) {
			ChartCustomizerDefinition def = (ChartCustomizerDefinition)element;
			if (def != null && def.getCustomizerClass() != null){
				String className = def.getCustomizerClass();
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
	 * Main composite that contains all the other controls of the widget
	 */
	private Composite controlsContainer;
	
	/**
	 * If the parent of this widget it is a {@link SectionContainerComposite} the reference
	 * to it is stored, in this way it is possible to refresh the scrollbar when the content is update
	 */
	private SectionContainerComposite container;

	/**
	 * The list of the customizer currently set on the element and shown on the table
	 */
	private List<ChartCustomizerDefinition> selectedCustomizers;
	
	/**
	 * The viewer to show the table of all the customizers associated to the element
	 */
	private TableViewer customizerTable;
	
	/**
	 * The {@link CustomizerPropertyExpressionsDTO} of the selected element
	 */
	private CustomizerPropertyExpressionsDTO currentDTO = null;
	
	/**
	 * The button to edit the definition of the selected customizer on the table
	 */
	private Button editButton;
	
	/**
	 * The button to delete the definition of a selected customizer in the table
	 */
	private Button deleteButton;
	
	/**
	 * The button to move up of a position the selected customizer in the table
	 */
	private Button upButton;

	/**
	 * The button to move down of a position the selected customizer in the table
	 */
	private Button downButton;
	
	/**
	 * The selected element
	 */
	private MChart selectedElement;
	
	/**
	 * The {@link JasperReportsConfiguration} of the current report
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * Create the controls but doesn't fill them. For that the method update must
	 * be called. The main controls will be a section with a label if this is placed inside
	 * the properties view, otherwise it will be a simple composite container (so it can be 
	 * easily placed also into dialogs
	 * 
	 * @param parent the parent where the control should be created
	 */
	public ChartCustomizerWidget(Composite parent) {
		if (parent instanceof SectionContainerComposite){
			//Widget created inside the properties view, create the section
			controlsContainer = createSection(parent, "Chart Customizers", true);
			container = (SectionContainerComposite)parent;
		} else {
			//Widget created in another dialog, use a simple composite as main container
			controlsContainer = new Composite(parent, SWT.NONE);
			controlsContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
			controlsContainer.setLayout(new GridLayout(1, false));
			container = null;
		}
		Composite proxyContainer = new Composite(controlsContainer, SWT.NONE);
		GridLayout proxyContainerData = new GridLayout(2, false);
		proxyContainerData.horizontalSpacing = 0;
		proxyContainerData.verticalSpacing = 0;
		proxyContainerData.marginWidth = 0;
		proxyContainerData.marginHeight = 0;
		proxyContainer.setLayout(proxyContainerData);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		proxyContainer.setLayoutData(gd);	
		buildTableArea(proxyContainer);
	}
	
	/**
	 * Initialize the widgets with the values of a real element
	 * 
	 * @param selectedElement the element from where the properties came, must be not null
	 * @param dto the {@link ChartCustomizerDefinition} of the element, must be not null
	 */
	public void update(MChart selectedElement, CustomizerPropertyExpressionsDTO dto) {
		this.selectedElement = selectedElement;
		jConfig = selectedElement.getJasperConfiguration();
		currentDTO = dto;
		selectedCustomizers = currentDTO.getDefinedCustomizers();
		customizerTable.setInput(selectedCustomizers);
	}
	
	/**
	 * Create the button to add a new chart customizer 
	 * 
	 * @param parent composite where the button should be placed
	 */
	protected void createNewButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button newButton = new Button(parent, SWT.PUSH);
		newButton.setLayoutData(buttonsData);
		newButton.setText(Messages.common_new);
		newButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				String uniqueKey = getPropertyDTO().getUniqueKey();
				JRChartPlot selectedPlot = selectedElement.getValue().getPlot();

				CustomizerPropertyExpressionsDTO dto = new CustomizerPropertyExpressionsDTO(getPropertyDTO().clone());
				CustomizerNewWizard wizard = new CustomizerNewWizard(uniqueKey, getExpressionContext(), dto, jConfig, selectedPlot);		
				PersistentLocationWizardDialog dialog = new PersistentLocationWizardDialog(UIUtils.getShell(), wizard);		
				dialog.setStoreSetting(false);
				dialog.setDefaultSize(650, 500);
				if (dialog.open() == Dialog.OK){
					ChartCustomizerDefinition definition = wizard.getDefinition();
					if (definition != null){
						selectedCustomizers.add(definition);
						String classProp = ProxyChartCustomizer.CUSTOMIZER_CLASS_ATTRIUBUTE;
						dto.addProperty(uniqueKey + classProp, definition.getCustomizerClass(), false);
						changePropertyOn(MChart.CHART_PROPERTY_CUSTOMIZER, dto, selectedElement);
						currentDTO = dto;
						customizerTable.refresh();	
					}
				}
			}
		});	
	}
	
	/**
	 * Create the button to edit the entry of a selected customizer
	 * 
	 * @param parent the parent where the button will be created
	 * @return the created button
	 */
	protected Button createEditButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button editButton = new Button(parent, SWT.PUSH);
		editButton.setLayoutData(buttonsData);
		editButton.setText(Messages.common_edit);
		editButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				editAction();
			}
		});
		editButton.setEnabled(false);
		return editButton;
	}
	
	/**
	 * Create the button to delete the entry of a selected customizer
	 * 
	 * @param parent the parent where the button will be created
	 * @return the created button
	 */
	protected Button createDeleteButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button deleteButton = new Button(parent, SWT.PUSH);
		deleteButton.setLayoutData(buttonsData);
		deleteButton.setText(Messages.common_delete);
		deleteButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomizerPropertyExpressionsDTO dto = getPropertyDTO();
				StructuredSelection selection = (StructuredSelection)customizerTable.getSelection();
				if (selection != null && selection.size() > 0){
					ChartCustomizerDefinition definition = (ChartCustomizerDefinition)selection.getFirstElement();
					selectedCustomizers.remove(definition);
					for(PropertyExpressionDTO property : new ArrayList<PropertyExpressionDTO>(dto.getProperties())){
						if (property.getName().startsWith(definition.getKey())){
							dto.removeProperty(property.getName(), property.isExpression());
						}
					}
					changePropertyOn(MChart.CHART_PROPERTY_CUSTOMIZER, dto, selectedElement);
				} 
				customizerTable.refresh();	
			}
		});
		deleteButton.setEnabled(false);
		return deleteButton;
	}
	
	/**
	 * Create the button to move up the entry of a selected customizer
	 * 
	 * @param parent the parent where the button will be created
	 * @return the created button
	 */
	protected Button createUpButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button upButton = new Button(parent, SWT.PUSH);
		upButton.setLayoutData(buttonsData);
		upButton.setText(Messages.common_up);
		upButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)customizerTable.getSelection();
				if (selection != null && selection.size() > 0){
					ChartCustomizerDefinition definition = (ChartCustomizerDefinition)selection.getFirstElement();
					int index = selectedCustomizers.indexOf(definition);
					selectedCustomizers.remove(index);
					index --;
					selectedCustomizers.add(index, definition);
					rebuildPositionsList();
					changePropertyOn(MChart.CHART_PROPERTY_CUSTOMIZER, getPropertyDTO(), selectedElement);
					customizerTable.refresh();
					updateButtonEnablemenet(definition);
				}
			}
		});
		upButton.setEnabled(false);
		return upButton;
	}
	
	/**
	 * Create the button to move down the entry of a selected customizer
	 * 
	 * @param parent the parent where the button will be created
	 * @return the created button
	 */
	protected Button createDownButton(Composite parent){
		GridData buttonsData = new GridData(GridData.FILL_HORIZONTAL);
		Button downButton = new Button(parent, SWT.PUSH);
		downButton.setLayoutData(buttonsData);
		downButton.setText(Messages.common_down);
		downButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)customizerTable.getSelection();
				if (selection != null && selection.size() > 0){
					ChartCustomizerDefinition definition = (ChartCustomizerDefinition)selection.getFirstElement();
					int index = selectedCustomizers.indexOf(definition);
					selectedCustomizers.remove(index);
					index ++;
					selectedCustomizers.add(index, definition);
					rebuildPositionsList();
					changePropertyOn(MChart.CHART_PROPERTY_CUSTOMIZER, getPropertyDTO(), selectedElement);
					customizerTable.refresh();
					updateButtonEnablemenet(definition);
				}
			}
		});
		downButton.setEnabled(false);
		return downButton;
	}
	
	/**
	 * Called when a customizer entry is moved up or down, it update the list of properties of
	 * the element to reflect the order of the customizer in the table
	 */
	protected void rebuildPositionsList(){
		CustomizerPropertyExpressionsDTO dto = getPropertyDTO();
		String classAttribute = ProxyChartCustomizer.CUSTOMIZER_CLASS_ATTRIUBUTE;
		//Only the class matter for the order determination and the priority is the position in the properties list
		//so first we remove all the customizer class properties
		for(ChartCustomizerDefinition definition : selectedCustomizers){
			dto.removeProperty(definition.getKey() + classAttribute, false);
		}
		//And then we re-add them in the same order of the customizers list
		for(ChartCustomizerDefinition definition : selectedCustomizers){
			dto.addProperty(definition.getKey() + classAttribute, definition.getCustomizerClass(), false);
		}
	}

	/**
	 * Build the area with the table and the buttons to handle the customizers
	 * 
	 * @param parent composite where the controls will be placed
	 */
	private void buildTableArea(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		cmp.setLayoutData(gd);
		GridLayout cmpLayout = new GridLayout(2, false);
		cmpLayout.horizontalSpacing = 3;
		cmpLayout.verticalSpacing = 0;
		cmpLayout.marginWidth = 0;
		cmpLayout.marginHeight = 0;
		cmp.setLayout(cmpLayout);

		Table tbl = new Table(cmp, SWT.BORDER | SWT.SINGLE);
		tbl.setLinesVisible(false);
		tbl.setHeaderVisible(false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 150;
		tbl.setLayoutData(gd);

		customizerTable = new TableViewer(tbl);
		customizerTable.setContentProvider(new ListContentProvider());
		customizerTable.setLabelProvider(new CustomizerLabelProvier());
		selectedCustomizers = new ArrayList<ChartCustomizerDefinition>();
		customizerTable.setInput(selectedCustomizers);

		Composite bcmp = new Composite(cmp, SWT.NONE);
		GridLayout bcmpLayout = new GridLayout(1, false);
		bcmpLayout.horizontalSpacing = 0;
		bcmpLayout.verticalSpacing = 2;
		bcmpLayout.marginWidth = 0;
		bcmpLayout.marginHeight = 0;
		bcmp.setLayout(bcmpLayout);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 100;
		bcmp.setLayoutData(gd);
		
		createNewButton(bcmp);
		editButton = createEditButton(bcmp);
		deleteButton= createDeleteButton(bcmp);
		upButton = createUpButton(bcmp);
		downButton = createDownButton(bcmp);
		
		//Selection change listener used to update the button enable status and to update
		//the dynamic ui if the selected customizer has a custom ui definition
		customizerTable.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				try {
					StructuredSelection selection = (StructuredSelection)event.getSelection();
					if (selection.size() > 0){
						ChartCustomizerDefinition definition = (ChartCustomizerDefinition)selection.getFirstElement();									
						updateButtonEnablemenet(definition);
					} else {
						updateButtonEnablemenet(null);
					}
				} finally {
					if (container != null){
						container.refreshPageComposite();
					}
				}	
			}
		});
		
		//Listener to treat the doubleclick a selection plus edit button pressed
		customizerTable.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				editAction();
			}
		});
	}
	
	/**
	 * Update the button status basing it on the selected customizer on the table
	 * 
	 * @param definition the definition of the selected customizer
	 */
	protected void updateButtonEnablemenet(ChartCustomizerDefinition definition){
		if (definition == null){
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			upButton.setEnabled(false);
			downButton.setEnabled(false);	
		} else {
			//Enable the edit button only if there are widgets to edit
			if (!definition.isOnlyClass() && !definition.getDescriptor().hasWidgets()){
				editButton.setEnabled(false);
			} else {
				editButton.setEnabled(true);
			}
			deleteButton.setEnabled(true);
			int selectionIndex = selectedCustomizers.indexOf(definition);
			upButton.setEnabled(selectionIndex > 0);
			downButton.setEnabled(selectionIndex < selectedCustomizers.size() - 1);
		}
	}
	
	/**
	 * Open the dialog to edit the selected customizer
	 */
	protected void editAction(){
		StructuredSelection selection = (StructuredSelection)customizerTable.getSelection();
		if (!selection.isEmpty()){	
			ChartCustomizerDefinition editElement = (ChartCustomizerDefinition)selection.getFirstElement();
			
			//Avoid the edit action is the selected elements has not widgets to show
			if (!editElement.isOnlyClass() && !editElement.getDescriptor().hasWidgets()){
				return;
			}
			
			CustomizerPropertyExpressionsDTO dto = new CustomizerPropertyExpressionsDTO(getPropertyDTO().clone());
			CustomizerEditWizard wizard = new CustomizerEditWizard(editElement, getExpressionContext(), dto, jConfig);		
			PersistentLocationWizardDialog dialog = new PersistentLocationWizardDialog(UIUtils.getShell(), wizard);
			dialog.setStoreSetting(false);
			dialog.setDefaultSize(650, 500);
			if (dialog.open() == Dialog.OK){
				ChartCustomizerDefinition definition = wizard.getDefinition();
				if (definition != null && definition.isOnlyClass()){
					String classProp = ProxyChartCustomizer.CUSTOMIZER_CLASS_ATTRIUBUTE;
					dto.setProperty(editElement.getKey() + classProp, definition.getCustomizerClass(), false);
					//Commit the new dto
					changePropertyOn(MChart.CHART_PROPERTY_CUSTOMIZER, dto, selectedElement);
					currentDTO = dto;
					selectedCustomizers = getPropertyDTO().getDefinedCustomizers();
					customizerTable.setInput(selectedCustomizers);
				} else {
					changePropertyOn(MChart.CHART_PROPERTY_CUSTOMIZER, dto, selectedElement);
					currentDTO = dto;
				}
			}					
		}
	}

	/**
	 * Return the main composite where all the other controls are placed
	 * 
	 * @return a not null control
	 */
	public Control getControl() {
		return controlsContainer;
	}

	/**
	 * Return the expression context of the selected element
	 * 
	 * @return a not null {@link ExpressionContext}
	 */
	private ExpressionContext getExpressionContext() {
		return ModelUtils.getElementExpressionContext(null, selectedElement);
	}
	
	/**
	 * It return the  {@link CustomizerPropertyDescriptor} of the current element. If it 
	 * was not loaded before it is requested to the element, stored and returned
	 * 
	 * @return a not null {@link CustomizerPropertyDescriptor}
	 */
	public CustomizerPropertyExpressionsDTO getPropertyDTO(){
		return currentDTO;
	}

	/**
	 * Create a section composite where some controls could be placed
	 * 
	 * @param parent the parent of the section
	 * @param text the text of the section
	 * @param expandable true if the section is expandable, false otherwise
	 * @return the composite internal to the section
	 */
	protected Composite createSection(Composite parent, String text, boolean expandable) {
		int style = Section.EXPANDED;
		if (expandable)
			style = style | Section.TREE_NODE;
		Section section = new Section(parent, style);
		section.titleBarTextMarginWidth = 0;

		section.setFont(SWTResourceManager.getBoldFont(section.getFont()));

		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = ((GridLayout)parent.getLayout()).numColumns;
			section.setLayoutData(gd);
		}
		section.setText(text);
		section.setSeparatorControl(new Label(section, SWT.SEPARATOR | SWT.HORIZONTAL));
		Composite cmp = new Composite(section, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 4;
		layout.marginWidth = 2;
		cmp.setLayout(layout);

		section.setClient(cmp);
		section.addExpansionListener(new ExpansionAdapter() {
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				if (container != null){
					container.refreshPageComposite();
				}
			}
		});
		return cmp;
	}

	/**
	 * Must be implemented by the subclass, it is called by a chart customizer property change 
	 * in the properties set. The implementation will provide the logic to write this properties in the
	 * element
	 * 
	 * @param property the name of the property, typically it will be a {@link MChart.CHART_PROPERTY_CUSTOMIZER}
	 * @param value the {@link CustomizerPropertyExpressionsDTO} to store inside the element 
	 * @param target the element where the properties should be stored
	 */
	public abstract void changePropertyOn(String property, CustomizerPropertyExpressionsDTO value, APropertyNode target);
	
}
