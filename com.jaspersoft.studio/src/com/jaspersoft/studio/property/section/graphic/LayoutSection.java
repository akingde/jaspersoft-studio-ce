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
package com.jaspersoft.studio.property.section.graphic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.ILayoutUIProvider;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Layout section, can be used to display dynamically configuration controls
 * depending by the Layout strategy of the parent of the selected elements. The 
 * created controls are cached and display when needed using a stack layout
 */
public class LayoutSection extends AbstractSection {
	
	/**
	 * The property descriptor for the layout combo
	 */
	private JSSComboPropertyDescriptor pd;
	
	/**
	 * The SWT section where the controls are created
	 */
	private Section section;
	
	/**
	 * The layout combo where the layout can be selected
	 */
	private SPLayoutCombo layoutCombo;
	
	/**
	 * Hashmap used to cache the created configuration composite for a specific layout
	 */
	private HashMap<ILayout, Composite> configurationMap = new HashMap<ILayout, Composite>();
	
	/**
	 * The composite where the controls for the configuration of the layout are created. It has
	 * a {@link StackLayout}, so the controls for each layout strategy are shown trough it
	 */
	private Composite layoutConfigurationPanel;
	
	/**
	 * The composite where the layout combo and its label are created
	 */
	private Composite comboContainer;
	
	/**
	 * Property change listener to refresh the section when there is a property change event on the 
	 * property map of an element. This because a property changed in the parent could modify its 
	 * layout strategy and so some controls should be added it a child is selected
	 */
	private PropertyChangeListener elementPropertyChange = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(MGraphicElement.PROPERTY_MAP) && evt.getSource() != getElement()){
				showSection();
			}
		}
	};
	
	public LayoutSection() {
		super();
		pd = new JSSComboPropertyDescriptor(MGraphicElement.PROPERTY_MAP, Messages.LayoutSection_combotitle, null);
		pd.setDescription(Messages.LayoutSection_combodescription);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MGraphicElement.PROPERTY_MAP, Messages.LayoutSection_combotitle);
	}
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		section = getWidgetFactory().createAndGetSection(parent, Messages.LayoutSection_sectiontitle, false, 2);
		Composite container = (Composite)section.getClient();
		container.setLayout(getNoSpaceLayout(1));
		
		//Create the controls for the combo
		comboContainer = new Composite(container, SWT.NONE);
		comboContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comboContainer.setLayout(getNoSpaceLayout(2));
		getWidgetFactory().createCLabel(comboContainer, Messages.LayoutSection_propertylable, SWT.RIGHT);
		layoutCombo = new SPLayoutCombo(comboContainer, this, pd);
		
		//Create the are where the layout additional controls are created
		layoutConfigurationPanel = new Composite(container, SWT.NONE);
		layoutConfigurationPanel.setLayout(new StackLayout());
		setLayoutAreaVisible(false);
		widgets.put(pd.getId(), layoutCombo); 
			
	}
	
	/**
	 * Show the controls for the configuration of the child of an element with a specific layout,
	 * if that layout require so.
	 * 
	 * @return true if control are required and displayed by the layout strategy of the parent, 
	 * false otherwise
	 */
	protected boolean showControls(){
		JRPropertiesMap parentProperties = LayoutManager.getPropertyMap(getElement().getParent());
		JRPropertiesMap elementProperties = LayoutManager.getPropertyMap(getElement());
		
		if (parentProperties != null){
			//get the layout of the parent
			String str = parentProperties.getProperty(ILayout.KEY);
			if (str == null){
				str = FreeLayout.class.getName();
			}
			ILayout parentLayout = LayoutManager.getLayout(str);	
			
			if (parentLayout != null){
				//check if the layout of the parent require additional controls to be shown on the children
				if (parentLayout.showAdditionalControls(elementProperties, parentProperties)){
					if (!configurationMap.containsKey(parentLayout)){
						//check if its controls where already created, if not create them
						Composite container = new Composite(layoutConfigurationPanel, SWT.NONE);
						container.setLayout(getNoSpaceLayout(1));
						ILayoutUIProvider controlsProvider = parentLayout.getControlsProvider();
						controlsProvider.createControls(container);
						container.setData(controlsProvider);
						configurationMap.put(parentLayout, container);
					}
					//the control are for sure created here, move them in the foreground and set their data
					Composite currentContainer = configurationMap.get(parentLayout);
					((StackLayout)layoutConfigurationPanel.getLayout()).topControl = currentContainer;
					setLayoutAreaVisible(true);
					ILayoutUIProvider controlsProvider = (ILayoutUIProvider)currentContainer.getData();
					controlsProvider.setData(getElement(), this);
					return true;
				} else {
					//the layout of the parent doesn't require additional controls, hide the visible one if any
					((StackLayout)layoutConfigurationPanel.getLayout()).topControl = null;
					setLayoutAreaVisible(false);
				}
			}
		}
		return false;
	}
	
	/**
	 * Show or hide the layout section if there is at least a controls to show (or the combo
	 * area or additional controls provided by the parent)
	 */
	protected void showSection() {
		if (section != null){
			if (getElement().getValue() == null){
				//The properties are not visible if the band is not created (the layout section is used
				// also for bands)
				section.setVisible(false);
				((GridData)section.getLayoutData()).exclude = true;
				section.getParent().getParent().layout(true, true);
			} else {
				boolean showCombo = getElement() instanceof IContainerLayout;
				comboContainer.setVisible(showCombo);
				GridData data = new GridData(GridData.FILL_HORIZONTAL);
				data.exclude = !showCombo;
				comboContainer.setLayoutData(data);
				boolean showControls = showControls();
				section.setVisible(showCombo || showControls);
				((GridData)section.getLayoutData()).exclude = !(showCombo || showControls);
				section.getParent().getParent().layout(true, true);
			}
		}
	}
	
	/**
	 * Create a grid layout with the specified number of columns and no
	 * width\height margin
	 * 
	 * @param colNumber a positive number of columns
	 * @return a not null grid layout
	 */
	private GridLayout getNoSpaceLayout(int colNumber){
		GridLayout layout = new GridLayout(colNumber, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		return layout;
	}
	
	/**
	 * Hide or show the layout area, when hidden it is also excluded from the size
	 * calculation
	 * 
	 * @param value true to show the area, false to hide it
	 */
	protected void setLayoutAreaVisible(boolean value){
		layoutConfigurationPanel.setVisible(value);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.exclude = !value;
		layoutConfigurationPanel.setLayoutData(layoutData);
	}
	
	/**
	 * Add the listener on the root element, to check if the property map of any element changes
	 */
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		if (getElement() != null && getElement().getRoot() != null){
			getElement().getRoot().getPropertyChangeSupport().removePropertyChangeListener(elementPropertyChange);
			getElement().getRoot().getPropertyChangeSupport().addPropertyChangeListener(elementPropertyChange);
			showSection();
		}
	}
	
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();
		if (getElement() != null && getElement().getRoot() != null){
			getElement().getRoot().getPropertyChangeSupport().removePropertyChangeListener(elementPropertyChange);
		}
	}
	
}
