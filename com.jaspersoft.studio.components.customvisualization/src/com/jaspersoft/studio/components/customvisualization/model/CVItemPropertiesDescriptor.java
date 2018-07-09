/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.customvisualization.properties.SPCVItemPropertiesList;
import com.jaspersoft.studio.components.customvisualization.ui.editor.CVCPropertiesDialog;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.customvisualization.design.CVDesignComponent;

/**
 * Property descriptor for the
 * {@link CVDesignComponent#PROPERTY_ITEM_PROPERTIES} property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class CVItemPropertiesDescriptor extends NTextPropertyDescriptor {

	public CVItemPropertiesDescriptor(Object id, String displayName) {
		super(id, displayName);
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new EditableDialogCellEditor(parent) {
					
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				CVCProprtiesExpressionDTO v = ((CVCProprtiesExpressionDTO) getValue()).clone();
				CVCPropertiesDialog dialog = new CVCPropertiesDialog(cellEditorWindow.getShell(), v);
				if (dialog.open() == Window.OK)
					return v;
				return null;
			}
			
			@Override
			protected void updateContents(Object value) {
				if (getDefaultLabel() == null)
					return;
				String text = getLabelProvider().getText(value);
				getDefaultLabel().setText(text);
			}
			
		};
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return new CVItemPropertiesLabelProvider();
	}
	
	@Override
	public SPCVItemPropertiesList createWidget(Composite parent,
			AbstractSection section) {
		return new SPCVItemPropertiesList(parent,section,this);
	}

}
