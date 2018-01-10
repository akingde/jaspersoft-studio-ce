/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.style;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.map.model.itemdata.MapDataElementsConfigurationLabelProvider;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Cell Editor for the <code>StandardMapComponent.PROPERTY_PATH_STYLE_LIST</code> property
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapStylesCellEditor extends EditableDialogCellEditor {

	private MapDataElementsConfigurationLabelProvider labelProvider;
	
	public MapStylesCellEditor(Composite parent) {
		super(parent);
	}

	public MapStylesCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		MessageDialog.openInformation(UIUtils.getShell(), "Map Paths Cell Editor", "TO BE IMPLEMENTED");
		return getValue();
	}

	@Override
	protected void updateContents(Object value) {
		if(getDefaultLabel()==null) {
			return;
		}
		if(labelProvider==null){
			labelProvider = new MapDataElementsConfigurationLabelProvider("Paths");
		}
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
