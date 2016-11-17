/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.descriptor;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPBoolean;

public class NextColumnPropertyDescriptor extends CheckBoxPropertyDescriptor {

	public NextColumnPropertyDescriptor(String displayName) {
		super(MTable.PROPERTY_COLUMNS_AUTORESIZE_NEXT, displayName);
	}
	
	@Override
	public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPBoolean<CheckBoxPropertyDescriptor>(parent, section, this){
			@Override
			protected void checkboxSelected() {
				JSSCompoundCommand command = new JSSCompoundCommand(section.getElement());
				command.setLabel("Take space from the next column");
				for(APropertyNode pNode : section.getElements()){
					if (pNode instanceof MTable){
						MTable table = (MTable)pNode;
						Command c = section.getChangePropertyCommand(MTable.PROPERTY_COLUMNS_AUTORESIZE_NEXT, cmb3Bool.getSelection(), table);
						if (c != null){
							command.add(c);
						}
					}
				}
				section.runCommand(command);
			}
		};
	}

}
