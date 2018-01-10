/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * Section to display the widget for the configuration of the background image
 * inside the properties view
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundImageSection extends AbstractSection {

	/**
	 * Section where the controls for the size of the background are placed
	 */
	private ExpandableComposite sectionSize;
	
	/**
	 * Section where the controls for the position of the background are placed
	 */
	private ExpandableComposite sectionLocation;
	
	/**
	 * Section where the controls for the transparency and ratio of the background are placed
	 */
	private ExpandableComposite appearanceSection;
	
	/**
	 * Since the properties of the image came not from a jr object but from the properties
	 * map the key of the event fired from jr is always the same and there is now way to know
	 * which property was updated. So all the widgets on the properties of the image are 
	 * updated when something in the map change
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (!isDisposed()) {
			setRefreshing(true);
			APropertyNode element = getElement();
			if (element != null) {
				element.getPropertyDescriptors();
				for (Object key : widgets.keySet()) {
					widgets.get(key).setData(element, element.getPropertyValue(key));
				}
			}
			setRefreshing(false);
		}
	}
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		//Create the size controls
		Composite container = getWidgetFactory().createSection(parent, Messages.SizeSection_sizeSectionTitle, true, 4);
		sectionSize = (ExpandableComposite)container.getParent();
		
		ASPropertyWidget<?> hw = createWidget4Property(container, MBackgrounImage.PROPERTY_WIDTH);
		CLabel lbl = hw.getLabel();
		lbl.setText(Messages.SizeSection_widthLabel);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		hw = createWidget4Property(container, MBackgrounImage.PROPERTY_HEIGHT);
		lbl = hw.getLabel();
		lbl.setText(Messages.SizeSection_heightLabel);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		//Create the location controls
		container = getWidgetFactory().createSection(parent, Messages.LocationSection_locationLabel, true, 4);
		sectionLocation = (ExpandableComposite)container.getParent();

		ASPropertyWidget<?> pw = createWidget4Property(container, MBackgrounImage.PROPERTY_X);
		lbl = pw.getLabel();
		lbl.setText(Messages.LocationSection_xCoordinateLabel);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		pw = createWidget4Property(container, MBackgrounImage.PROPERTY_Y);
		lbl = pw.getLabel();
		lbl.setText(Messages.LocationSection_yCoordinateLabel);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		//Create the transparency and ratio controls
		container = getWidgetFactory().createSection(parent, Messages.MBackgrounImage_labelAppearance, true, 2);
		sectionLocation = (ExpandableComposite)container.getParent();
		pw = createWidget4Property(container, MBackgrounImage.PROPERTY_ALPHA);
		pw = createWidget4Property(container, MBackgrounImage.PROPERTY_KEEP_RATIO, false);
	}
	
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MBackgrounImage.PROPERTY_X, Messages.LocationSection_xCoordinateLabel);
		addProvidedProperties(MBackgrounImage.PROPERTY_Y, Messages.LocationSection_yCoordinateLabel);
		addProvidedProperties(MBackgrounImage.PROPERTY_WIDTH, Messages.MGraphicElement_width);
		addProvidedProperties(MBackgrounImage.PROPERTY_HEIGHT, Messages.common_height);
		addProvidedProperties(MBackgrounImage.PROPERTY_ALPHA, Messages.MBackgrounImage_labelCategory);
		addProvidedProperties(MBackgrounImage.PROPERTY_KEEP_RATIO, Messages.MBackgrounImage_labelKeepRatio);
	}
	
	@Override
	public void expandForProperty(Object propertyId) {
		if (MBackgrounImage.PROPERTY_WIDTH.equals(propertyId) || MBackgrounImage.PROPERTY_HEIGHT.equals(propertyId)){
			expandSection(sectionSize);
		} else if  (MBackgrounImage.PROPERTY_X.equals(propertyId) || MBackgrounImage.PROPERTY_Y.equals(propertyId)){
			expandSection(sectionLocation);
		} else if  (MBackgrounImage.PROPERTY_ALPHA.equals(propertyId) || MBackgrounImage.PROPERTY_KEEP_RATIO.equals(propertyId)){
			expandSection(appearanceSection);
		}
	}
	
	/**
	 * Expand the passed section if it is not null and not expanded
	 * 
	 * @param section a section
	 */
	private void expandSection(ExpandableComposite section){
		if (section != null && !section.isExpanded()) section.setExpanded(true);
	}
	
}
