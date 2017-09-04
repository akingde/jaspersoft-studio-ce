/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertySheet;

import com.jaspersoft.studio.editor.gef.selection.SelectElementsByValueCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptors.IEnumDescriptors;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.SelectionHelper;

public class SPReadComboEnum<T extends IPropertyDescriptor & IEnumDescriptors> extends ASPropertyWidget<T> {
	private Combo combo;

	public SPReadComboEnum(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return combo;
	}

	protected void createComponent(Composite parent) {
		combo = section.getWidgetFactory().createCombo(parent, SWT.FLAT | SWT.READ_ONLY);
		combo.setItems(pDescriptor.getEnumItems());
		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo.getSelectionIndex();
				EditPart nodePart = section.getElement().getTreeEditPart();
				Object value = section.getElement().getValue();
				SelectElementsByValueCommand selectCommand = null;
				EditPartViewer viewer = null;
				if (nodePart != null) {			
					viewer = nodePart.getViewer();
					Object valueToSelect = section.getElement().getValue();
					selectCommand = new SelectElementsByValueCommand(valueToSelect, nodePart.getViewer());
				}
				
				section.changeProperty(pDescriptor.getId(), index);
				
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
		});
		combo.setToolTipText(pDescriptor.getDescription());
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

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		int index = 0;
		if (b != null)
			index = ((Number) b).intValue();
		combo.select(index);
		combo.setEnabled(pnode.isEditable());
	}

}
