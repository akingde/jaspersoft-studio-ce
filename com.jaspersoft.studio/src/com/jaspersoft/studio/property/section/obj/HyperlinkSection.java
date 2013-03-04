/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.section.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.hyperlink.parameter.ParameterPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPParameter;
import com.jaspersoft.studio.utils.ModelUtils;

public class HyperlinkSection extends AbstractSection {
	
	private ElementHider anchor;
	
	private ElementHider page;
	
	private ElementHider reference;
	
	private ElementHider when;

	private ElementHider tooltip;
	
	private ElementHider parameters;
	
	private ASPropertyWidget anchorWidget;
	
	private ASPropertyWidget referenceWidget;
	
	private ASPropertyWidget parametersWidget;
	
	private ASPropertyWidget whenWidget;
	
	private ASPropertyWidget tooltipWidget;
	
	private ASPropertyWidget pageWidget;
	
	private Combo targetCombo;
	
	private Combo typeCombo;
	
	private Composite mainComposite;
	
	private HashMap<String, ElementHider[]> hideList = null;
	
	private static String[] linkTargetItems=new String[]{
		HyperlinkTargetEnum.SELF.getName(),
		HyperlinkTargetEnum.BLANK.getName(),
		HyperlinkTargetEnum.TOP.getName(),
		HyperlinkTargetEnum.PARENT.getName(),};
	private static String[] linkTypeItems;
	
	static {
		ArrayList<HyperlinkTypeEnum> filteredTypes = new ArrayList<HyperlinkTypeEnum>(2);
		filteredTypes.add(HyperlinkTypeEnum.CUSTOM);	// Will be used automatically when user write a custom entry
		filteredTypes.add(HyperlinkTypeEnum.NULL);		// Makes no much sense into this widget
		List<String> alltypes=ModelUtils.getHyperlinkTypeNames4Widget(filteredTypes);		
		linkTypeItems=alltypes.toArray(new String[alltypes.size()]);
		
	}
	
	private class ElementHider {
		
		private Control[] controls;
		
		public ElementHider(Control[] controls){
			this.controls = controls;
		}
		
		public void setVisibility(boolean visible){
			for(Control control : controls){
				if (!control.isDisposed()) {
					if (control.getLayoutData() == null) control.setLayoutData(new GridData());
					GridData layout = (GridData)control.getLayoutData();
					layout.exclude = !visible;
					control.setVisible(visible);
				}
			}
		}
		
		public void showAll(){
			setVisibility(true);
		}
		
		public void hideAll(){
			setVisibility(false);
		}
		
	}
	
	private GridData gridDataGenerator(){
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		return gd;
	}

	@Override
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			anchorWidget.setData(element, element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION));
			referenceWidget.setData(element, element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION));
			whenWidget.setData(element, element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_HYPERLINK_WHEN_EXPRESSION));
			pageWidget.setData(element, element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION));
			tooltipWidget.setData(element, element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION));
			parametersWidget.setData(element, element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS));
			Object propertyValue = element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_LINK_TARGET);
			targetCombo.setText(propertyValue != null ? propertyValue.toString() : linkTargetItems[0]);
			propertyValue = element.getPropertyActualValue(JRDesignHyperlink.PROPERTY_LINK_TYPE);
			typeCombo.setText(propertyValue != null ? propertyValue.toString() : linkTypeItems[0]);
		}
		isRefreshing = false;
	}
	
	private void createMap(){
		if (hideList == null){
			hideList = new HashMap<String, HyperlinkSection.ElementHider[]>();
			hideList.put(linkTypeItems[0], new ElementHider[]{tooltip});
			hideList.put(linkTypeItems[1], new ElementHider[]{tooltip, parameters, reference});
			hideList.put(linkTypeItems[2], new ElementHider[]{tooltip, parameters, anchor});
			hideList.put(linkTypeItems[3], new ElementHider[]{tooltip, parameters, page});
			hideList.put(linkTypeItems[4], new ElementHider[]{tooltip, parameters, reference, anchor});
			hideList.put(linkTypeItems[5], new ElementHider[]{tooltip, parameters, reference, page});
			hideList.put(linkTypeItems[6], new ElementHider[]{tooltip, parameters, reference, anchor, page});
			hideList.put("Custom", new ElementHider[]{tooltip, parameters, reference, anchor, page});
		}
	}
	
	private void refreshVisibleComponents(){
		ElementHider[] hiders = new ElementHider[]{anchor, page, reference, when, tooltip, parameters};
		for(ElementHider hider : hiders)
			hider.hideAll();
		String selectedValue = typeCombo.getText();
		if (!hideList.containsKey(selectedValue)) selectedValue = "Custom";
		ElementHider[] actualHiders = hideList.get(selectedValue);
		for(ElementHider hider : actualHiders)
			hider.showAll();
		mainComposite.layout();
	}
	
	private void readValueFromCombo(Combo combo, String property){
		APropertyNode element = getElement();
		if (element != null) {
			element.setPropertyValue(property, combo.getText());
			int stringLength = combo.getText ().length (); 
			combo.setSelection(new Point (stringLength, stringLength));
		}
	}
	
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(new GridLayout(3, false));
		GridData parentData = new GridData(SWT.FILL,SWT.FILL, true, true);
		parentData.minimumHeight = 250;
		mainComposite.setLayoutData(parentData);
		
		Label targrtLabel = new Label(mainComposite, SWT.NONE);
		targrtLabel.setText("Link Target");
		targetCombo = new Combo(mainComposite, SWT.NONE);
		targetCombo.setLayoutData(gridDataGenerator()); 
		targetCombo.setItems(linkTargetItems);
		
		Label typeLabel = new Label(mainComposite, SWT.NONE);
		typeLabel.setText("Link Type");
		typeCombo = new Combo(mainComposite, SWT.NONE);
		typeCombo.setLayoutData(gridDataGenerator()); 
		typeCombo.setItems(linkTypeItems);
		
		Label anchorLabel = new Label(mainComposite, SWT.NONE);
		anchorLabel.setText("Anchor");
		JRExpressionPropertyDescriptor anchorExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION, Messages.MHyperLink_hyperlink_anchor_expression);
		anchorExpressionD.setDescription(Messages.MHyperLink_hyperlink_anchor_expression_description);
		anchorWidget = anchorExpressionD.createWidget(mainComposite, this);
		anchorWidget.getControl().setLayoutData(gridDataGenerator());
		anchor = new ElementHider(new Control[]{anchorLabel, anchorWidget.getControl()});
		
		Label pageLabel = new Label(mainComposite, SWT.NONE);
		pageLabel.setText("Page");
		JRExpressionPropertyDescriptor pageExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION, Messages.MHyperLink_hyperlink_page_expression);
		pageExpressionD.setDescription(Messages.MHyperLink_hyperlink_page_expression_description);
		pageWidget = pageExpressionD.createWidget(mainComposite, this);
		pageWidget.getControl().setLayoutData(gridDataGenerator());		
		page = new ElementHider(new Control[]{pageLabel, pageWidget.getControl()});
		
		Label referenceLabel = new Label(mainComposite, SWT.NONE);
		referenceLabel.setText("Reference");
		JRExpressionPropertyDescriptor referenceExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION, Messages.MHyperLink_hyperlink_reference_expression);
		referenceExpressionD.setDescription(Messages.MHyperLink_hyperlink_reference_expression_description);
		referenceWidget = referenceExpressionD.createWidget(mainComposite, this);
		referenceWidget.getControl().setLayoutData(gridDataGenerator());		
		reference = new ElementHider(new Control[]{referenceLabel, referenceWidget.getControl()});
		
		Label whenLabel = new Label(mainComposite, SWT.NONE);
		whenLabel.setText("When");
		JRExpressionPropertyDescriptor whenExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_WHEN_EXPRESSION, Messages.MHyperLink_whenexpr);
		whenExpressionD.setDescription(Messages.MHyperLink_whenexpr_desc);
		whenWidget = whenExpressionD.createWidget(mainComposite, this);
		whenWidget.getControl().setLayoutData(gridDataGenerator());		
		when = new ElementHider(new Control[]{whenLabel, whenWidget.getControl()});
		
		Label tooltipLabel = new Label(mainComposite, SWT.NONE);
		tooltipLabel.setText("ToolTip");
		JRExpressionPropertyDescriptor toolTipExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION, Messages.MHyperLink_hyperlink_tooltip_expression);
		toolTipExpressionD.setDescription(Messages.MHyperLink_hyperlink_tooltip_expression_description);
		tooltipWidget = toolTipExpressionD.createWidget(mainComposite, this);
		tooltipWidget.getControl().setLayoutData(gridDataGenerator());	
		tooltip = new ElementHider(new Control[]{tooltipLabel, tooltipWidget.getControl()});
		
		Label parametersLabel = new Label(mainComposite, SWT.NONE);
		parametersLabel.setText("Parameters");
		ParameterPropertyDescriptor propertiesD = new ParameterPropertyDescriptor(
				JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS, Messages.common_parameters);
		propertiesD.setDescription(Messages.MHyperLink_parameters_description);
		parametersWidget = propertiesD.createWidget(mainComposite, this);	
		Control button = ((SPParameter)parametersWidget).getButton();
		parameters = new ElementHider(new Control[]{parametersLabel, parametersWidget.getControl(), button});
		
		createMap();
		
		targetCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				readValueFromCombo(targetCombo, JRDesignHyperlink.PROPERTY_LINK_TARGET);
			}
		});
		targetCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				readValueFromCombo(targetCombo, JRDesignHyperlink.PROPERTY_LINK_TARGET);
			}
		});
		typeCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					refreshVisibleComponents();
					readValueFromCombo(typeCombo, JRDesignHyperlink.PROPERTY_LINK_TYPE);
			}
		});
		typeCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				refreshVisibleComponents();
				readValueFromCombo(typeCombo, JRDesignHyperlink.PROPERTY_LINK_TYPE);
			}
		});
		/*
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_TARGET).getControl().setLayoutData(gd);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_LINK_TYPE).getControl().setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Anchor");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Page");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Reference");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "When");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_WHEN_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Tooltip");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS);*/
	}

}
