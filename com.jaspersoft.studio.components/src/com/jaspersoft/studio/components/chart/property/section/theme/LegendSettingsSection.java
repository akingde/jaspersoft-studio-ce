/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.theme;

import java.beans.PropertyChangeEvent;

import net.sf.jasperreports.chartthemes.simple.LegendSettings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Legend settings section used in the chart theme for the legend items
 * 
 * @author Orlandin Marco
 *
 */
public class LegendSettingsSection extends AbstractSection {
	
	private ExpandableComposite paddingSection;
	
	private Composite strokeContainer = null;
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		Composite group = getWidgetFactory().createComposite(parent);
		group.setLayout(new GridLayout(2, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, LegendSettings.PROPERTY_showLegend, false).getControl().setLayoutData(gd);
		createWidget4Property(group, LegendSettings.PROPERTY_position);
		createWidget4Property(group, LegendSettings.PROPERTY_horizontalAlignment);
		createWidget4Property(group, LegendSettings.PROPERTY_verticalAlignment);
		createWidget4Property(group, LegendSettings.PROPERTY_foregroundPaint);
		createWidget4Property(group, LegendSettings.PROPERTY_backgroundPaint);

		//Block frame properties
		String preID = LegendSettings.PROPERTY_frame;
		Composite paddingComposite = getWidgetFactory().createSection(parent, "Block Frame", true, 5);   
		paddingSection = (ExpandableComposite)paddingComposite.getParent();
		paddingSection.setExpanded(false);
		
		createWidget4Property(paddingComposite, preID + PadUtil.PADDING_TOP);
		createWidget4Property(paddingComposite, preID + PadUtil.PADDING_BOTTOM);
		strokeContainer = new Composite(paddingComposite, SWT.NONE);
		GridLayout strokeLayout = new GridLayout(2,false);
		strokeLayout.horizontalSpacing = 0;
		strokeLayout.verticalSpacing = 0;
		strokeLayout.marginWidth = 0;
		strokeLayout.marginHeight = 0;
		strokeContainer.setLayout(strokeLayout);
		strokeContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		createWidget4Property(strokeContainer, preID + PadUtil.FRAME_STROKE);
		createWidget4Property(paddingComposite, preID + PadUtil.PADDING_LEFT);
		createWidget4Property(paddingComposite, preID + PadUtil.PADDING_RIGHT);
		new Label(paddingComposite, SWT.None);
		
		GridData colorData = new GridData();
		colorData.horizontalSpan = 4;
		colorData.widthHint = 200;
		
		createWidget4Property(paddingComposite, preID + PadUtil.FRAME_COLOR).getControl().setLayoutData(colorData);	
		createWidget4Property(paddingComposite, preID + PadUtil.FRAME_FILL, false);
	}
	
	/**
	 * Show the stroke control if the frame is of type fill, otherwise hide it
	 */
	private void updateFillControl(){
		if (strokeContainer != null && getElement() != null){
			Object isFill = getElement().getPropertyValue(LegendSettings.PROPERTY_frame + PadUtil.FRAME_FILL);
			if (isFill instanceof DefaultValue){
				isFill = ((DefaultValue)isFill).getValue();
			}
			strokeContainer.setVisible(isFill != null? !((Boolean)isFill) : false);
		}
	}
	
	/**
	 * Need to check if the stroke controls is visible or not
	 */
	@Override
	public void aboutToBeShown() {
		updateFillControl();
		super.aboutToBeShown();
	}

	/**
	 * Since the changes on the block frame fire a property with an id different 
	 * from any widget, the update must be done manually
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(LegendSettings.PROPERTY_frame)){
			if (!isDisposed()) {
				setRefreshing(true);
				APropertyNode element = getElement();
				String preID = LegendSettings.PROPERTY_frame;
				widgets.get(preID + PadUtil.FRAME_COLOR).setData(element, element.getPropertyValue(preID + PadUtil.FRAME_COLOR));
				setRefreshing(false);
			}
			updateFillControl();
		} else super.propertyChange(evt);
	}
	
	private void expandSection(ExpandableComposite section){
		if (section != null && !section.isExpanded()) section.setExpanded(true);
	}
	
	@Override
	public void expandForProperty(Object propertyId) {
		if (propertyId.toString().startsWith(LegendSettings.PROPERTY_frame)) expandSection(paddingSection);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(LegendSettings.PROPERTY_showLegend, Messages.MLegendSettings_showLegendTitle);
		addProvidedProperties(LegendSettings.PROPERTY_position, Messages.MLegendSettings_positionTitle);
		addProvidedProperties(LegendSettings.PROPERTY_horizontalAlignment, Messages.MLegendSettings_legendHAlignTitle);
		addProvidedProperties(LegendSettings.PROPERTY_verticalAlignment, Messages.MLegendSettings_legendVAlignTitle);
		addProvidedProperties(LegendSettings.PROPERTY_foregroundPaint, Messages.MLegendSettings_legendForegroundColorTitle);
		addProvidedProperties(LegendSettings.PROPERTY_backgroundPaint, Messages.MLegendSettings_legendBackgroundColorTitle);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.PADDING_TOP, com.jaspersoft.studio.messages.Messages.common_top);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.PADDING_BOTTOM, com.jaspersoft.studio.messages.Messages.common_bottom);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.PADDING_LEFT, com.jaspersoft.studio.messages.Messages.common_left);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.PADDING_RIGHT, com.jaspersoft.studio.messages.Messages.common_right);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.FRAME_COLOR, com.jaspersoft.studio.messages.Messages.common_line_color);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.FRAME_FILL, com.jaspersoft.studio.messages.Messages.common_fill);
		addProvidedProperties(LegendSettings.PROPERTY_frame+PadUtil.FRAME_STROKE, com.jaspersoft.studio.messages.Messages.MLinePen_line_width);
	}
}
