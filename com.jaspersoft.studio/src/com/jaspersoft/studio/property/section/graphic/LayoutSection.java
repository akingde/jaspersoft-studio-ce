/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

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
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.RealSizeStackLayout;

import net.sf.jasperreports.engine.JRPropertiesMap;

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
	 * Node of the root where the last listener was placed, until it is null
	 * it means that there aren't active listeners
	 */
	private INode listenedRoot = null;
	
	/**
	 * Hashmap used to cache the created configuration composite for a specific layout
	 */
	private HashMap<ILayout, Composite> childConfigurationMap = new HashMap<ILayout, Composite>();
	
	/**
	 * Hashmap used to cache the created configuration composite for a specific layout
	 */
	private HashMap<ILayout, Composite> nodeConfigurationMap = new HashMap<ILayout, Composite>();
	
	/**
	 * The composite where the controls for the configuration of the layout are created. It has
	 * a {@link StackLayout}, so the controls for each layout strategy are shown trough it
	 */
	private Composite parentLayoutConfigurationPanel;
	
	/**
	 * The composite where the controls for the configuration of the layout are created. It has
	 * a {@link StackLayout}, so the controls for each layout strategy are shown trough it
	 */
	private Composite currentLayoutConfigurationPanel;
	
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
		layoutCombo = new SPLayoutCombo(comboContainer, this, pd){
			@Override
			protected void handlePropertyChange() {
				super.handlePropertyChange();
				showSection();
			}
		};
		
		//Create the are where the layout additional controls are created
		parentLayoutConfigurationPanel = new Composite(container, SWT.NONE);
		parentLayoutConfigurationPanel.setLayout(new RealSizeStackLayout());
		currentLayoutConfigurationPanel = new Composite(container, SWT.NONE);
		currentLayoutConfigurationPanel.setLayout(new RealSizeStackLayout());
		
		setParentLayoutAreaVisible(false);
		setCurrentLayoutAreaVisible(false);
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
		
		boolean isShowingControls = false;
		if (parentProperties != null){
			//get the layout of the parent
			String str = parentProperties.getProperty(ILayout.KEY);
			if (str == null){
				str = FreeLayout.class.getName();
			}
			ILayout parentLayout = LayoutManager.getLayout(str);	
			
			if (parentLayout != null){
				//check if the layout of the parent require additional controls to be shown on the children
				if (parentLayout.showAdditionalControlsOnChild(elementProperties, parentProperties)){
					Composite currentContainer = childConfigurationMap.get(parentLayout);
					if (currentContainer == null || currentContainer.isDisposed()){
						//check if its controls where already created, if not create them
						currentContainer = new Composite(parentLayoutConfigurationPanel, SWT.NONE);
						currentContainer.setLayout(getNoSpaceLayout(1));
						ILayoutUIProvider controlsProvider = parentLayout.getControlsProvider();
						controlsProvider.createControls(currentContainer);
						currentContainer.setData(controlsProvider);
						childConfigurationMap.put(parentLayout, currentContainer);
					}
					//the control are for sure created here, move them in the foreground and set their data
					((StackLayout)parentLayoutConfigurationPanel.getLayout()).topControl = currentContainer;
					setParentLayoutAreaVisible(true);
					ILayoutUIProvider controlsProvider = (ILayoutUIProvider)currentContainer.getData();
					controlsProvider.setData(getElement(), this);
					isShowingControls = true;
				} else {
					//the layout of the parent doesn't require additional controls, hide the visible one if any
					((StackLayout)parentLayoutConfigurationPanel.getLayout()).topControl = null;
					setParentLayoutAreaVisible(false);
				}
			}
		}
		
		if (elementProperties != null){
			//get the layout of the current node
			String str = elementProperties.getProperty(ILayout.KEY);
			if (str == null){
				str = FreeLayout.class.getName();
			}
			ILayout currentLayout = LayoutManager.getLayout(str);	
			
			if (currentLayout != null){
				//check if the layout of the parent require additional controls to be shown on the children
				if (currentLayout.showAdditionalControlsOnNode(elementProperties, parentProperties)){
					Composite currentContainer = nodeConfigurationMap.get(currentLayout);
					if (currentContainer == null || currentContainer.isDisposed()){
						//check if its controls where already created, if not create them
						currentContainer = new Composite(currentLayoutConfigurationPanel, SWT.NONE);
						currentContainer.setLayout(getNoSpaceLayout(1));
						ILayoutUIProvider controlsProvider = currentLayout.getControlsProvider();
						controlsProvider.createLayoutControls(currentContainer);
						currentContainer.setData(controlsProvider);
						nodeConfigurationMap.put(currentLayout, currentContainer);
					}
					//the control are for sure created here, move them in the foreground and set their data
					((StackLayout)currentLayoutConfigurationPanel.getLayout()).topControl = currentContainer;
					setCurrentLayoutAreaVisible(true);
					ILayoutUIProvider controlsProvider = (ILayoutUIProvider)currentContainer.getData();
					controlsProvider.setData(getElement(), this);
					isShowingControls = true;
				} else {
					//the layout of the parent doesn't require additional controls, hide the visible one if any
					((StackLayout)currentLayoutConfigurationPanel.getLayout()).topControl = null;
					setCurrentLayoutAreaVisible(false);
				}
			}
		}
		return isShowingControls;
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
	protected void setParentLayoutAreaVisible(boolean value){
		parentLayoutConfigurationPanel.setVisible(value);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.exclude = !value;
		parentLayoutConfigurationPanel.setLayoutData(layoutData);
	}
	
	/**
	 * Hide or show the layout area, when hidden it is also excluded from the size
	 * calculation
	 * 
	 * @param value true to show the area, false to hide it
	 */
	protected void setCurrentLayoutAreaVisible(boolean value){
		currentLayoutConfigurationPanel.setVisible(value);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.exclude = !value;
		currentLayoutConfigurationPanel.setLayoutData(layoutData);
	}
	
	/**
	 * Add the listener on the root element, to check if the property map of any element changes
	 */
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		//if there is an active listener remove it
		if (listenedRoot != null){
			listenedRoot.getPropertyChangeSupport().removePropertyChangeListener(elementPropertyChange);
			listenedRoot = null;
		}
		//Then add a new listener to the root
		if (getElement() != null && getElement().getRoot() != null){
			listenedRoot = getElement().getRoot();
			listenedRoot.getPropertyChangeSupport().addPropertyChangeListener(elementPropertyChange);
			showSection();
		}
	}
	
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();
		//if there is an active listener remove it
		if (listenedRoot != null){
			listenedRoot.getPropertyChangeSupport().removePropertyChangeListener(elementPropertyChange);
			listenedRoot = null;
		}
	}
	
	@Override
	public boolean hasDynamicContent() {
		return true;
	}
}
