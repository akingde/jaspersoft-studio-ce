/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.IRefreshableCellEditor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Cell editor used for the combo font, it update the combo when the font items list
 * changes
 * 
 * @author Orlandin Marco
 *
 */
public class FontNameCellEditor extends RWComboBoxCellEditor implements IRefreshableCellEditor {
	
	public FontNameCellEditor(Composite parent, String[] items) {
		super(parent, items);
	}
	
	/**
	 * Uses the current node to recover the font names and update the combo if they are
	 * out of synch
	 */
	@Override
	public void refresh(ANode selectedModel) {
		if (selectedModel != null){
			JasperReportsConfiguration jConfig = selectedModel.getJasperConfiguration();
			if (jConfig != null && !ModelUtils.safeEquals(getItems(), jConfig.getFontList())){
				setItems(jConfig.getFontList());	
			}
		}
	}
}
