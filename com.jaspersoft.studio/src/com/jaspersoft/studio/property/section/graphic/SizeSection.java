/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class SizeSection extends AbstractRealValueSection {

	private ExpandableComposite section;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, Messages.SizeSection_sizeSectionTitle, true, 4);
		section = (ExpandableComposite) parent.getParent();

		ASPropertyWidget<?> hw = createWidget4Property(parent, JRDesignElement.PROPERTY_WIDTH);
		CLabel lbl = hw.getLabel();
		lbl.setText(Messages.SizeSection_widthLabel);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		hw = createWidget4Property(parent, JRDesignElement.PROPERTY_HEIGHT);
		lbl = hw.getLabel();
		lbl.setText(Messages.SizeSection_heightLabel);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		ASPropertyWidget<?> w = createWidget4Property(parent, JRDesignElement.PROPERTY_STRETCH_TYPE);
		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		w.getControl().setLayoutData(gd);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignElement.PROPERTY_WIDTH, Messages.MGraphicElement_width);
		addProvidedProperties(JRDesignElement.PROPERTY_HEIGHT, Messages.common_height);
		addProvidedProperties(JRDesignElement.PROPERTY_STRETCH_TYPE, Messages.common_stretch_type);
	}
	
	/**
	 * Create a custom command that will trigger a layout after the resize
	 */
	@Override
	public Command getChangePropertyCommand(Object property, Object newValue, APropertyNode n) {
		if (isChanged(property, newValue, n)) {
			//Check if the node provide a SetValueCommand provide and use it in case, otherwise
			//create a standard SetValueCommand
			ISetValueCommandProvider provider = (ISetValueCommandProvider)n.getAdapter(ISetValueCommandProvider.class);
			if (provider != null){
				return provider.getSetValueCommand(n, n.getDisplayText(), property, newValue);
			} else {
				SetValueCommand setCommand = new SetValueCommand(n.getDisplayText()) {
					
					public void execute() {
						super.execute();
						layoutChildAndParent();
					};
					
					public void undo() {
						super.undo();
						layoutChildAndParent();
					};
					
					/**
					 * Execute the layout both of the moved element and of its parent
					 */
					private void layoutChildAndParent(){
						//layout the children of the element if any
						ANode elementNode = (ANode)target;
						LayoutManager.layoutContainer(elementNode);
						
						//layout the parent
						LayoutManager.layoutContainer(elementNode.getParent());
					}
				};
				setCommand.setTarget(n);
				setCommand.setPropertyId(property);
				setCommand.setPropertyValue(newValue);
				return setCommand;
			}
		}
		return null;
	}

	@Override
	public void expandForProperty(Object propertyId) {
		if (section != null && !section.isExpanded())
			section.setExpanded(true);
	}

}
