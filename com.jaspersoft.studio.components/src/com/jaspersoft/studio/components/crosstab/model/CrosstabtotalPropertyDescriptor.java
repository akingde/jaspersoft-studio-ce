package com.jaspersoft.studio.components.crosstab.model;

import org.eclipse.gef.EditPart;
/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.PropertySheet;

import com.jaspersoft.studio.editor.gef.selection.SelectElementsByValueCommand;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPReadComboEnum;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;

/**
 * Property descriptor for the crosstab total property. This override the widget creation to create 
 * a modified combo that trigger a new selection after the property change. This is done because
 * the total change trigger the creation of new cells and for this reason the selection must
 * be refreshed (otherwise the property view will point to a non correct selection), see
 * http://jira.jaspersoft.com/browse/JSS-613 
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabtotalPropertyDescriptor extends NamedEnumPropertyDescriptor<CrosstabTotalPositionEnum> {

	public CrosstabtotalPropertyDescriptor(Object id, String displayName, CrosstabTotalPositionEnum nenum,
			NullEnum type) {
		super(id, displayName, nenum, type);
	}
	
	@Override
	public ASPropertyWidget<NamedEnumPropertyDescriptor<CrosstabTotalPositionEnum>> createWidget(Composite parent,AbstractSection section) {
		return new SPReadComboEnum<NamedEnumPropertyDescriptor<CrosstabTotalPositionEnum>>(parent, section, this) {
			
			@Override
			protected void handleChange(int selectionIndex) {
				EditPart nodePart = section.getElement().getTreeEditPart();
				Object value = section.getElement().getValue();
				SelectElementsByValueCommand selectCommand = null;
				EditPartViewer viewer = null;
				if (nodePart != null) {			
					viewer = nodePart.getViewer();
					Object valueToSelect = section.getElement().getValue();
					selectCommand = new SelectElementsByValueCommand(valueToSelect, nodePart.getViewer());
				}
				
				section.changeProperty(pDescriptor.getId(), selectionIndex);
				
				if (selectCommand != null) {
					selectCommand.execute();
					PropertySheet propertySheet = getPropertySheet();
					//FIXME: need to set the selection manually because of http://jira.jaspersoft.com/browse/JSS-613 
					//simply selecting the new node on the outline fails to trigger the refresh of the properties view (that keep the reference to
					//an old and unexisting node). This depends on the eclipse compatibility layer that fails to schedule the selection event for
					//the listeners (like the property view)
					if (propertySheet != null) {
						propertySheet.selectionChanged(section.getPart(), new StructuredSelection(SelectionHelper.getEditPartByValue(value,  viewer)));
					}
				}
			}
			
			/**
			 * Return the property sheet view, should be always available since this control is typically created
			 * inside it
			 * 
			 * @return the property sheet view or null if it can't be found
			 */
			private PropertySheet getPropertySheet() {
				IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
				for(IViewReference reference : references) {
					if (reference.getId().equals("org.eclipse.ui.views.PropertySheet") && reference.getView(false) instanceof PropertySheet) {
						return (PropertySheet)reference.getView(false);
					}
				}
				return null;
			}
		};
	}

}
