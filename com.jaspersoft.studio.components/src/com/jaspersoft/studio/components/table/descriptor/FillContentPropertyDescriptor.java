/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.table.descriptor;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.part.editpolicy.SetTableAutoresizeProperty;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPBoolean;

/**
 * Property descriptor used for the flag used to make the table fill the available space.
 * It create a custom command that allow to set the flag and trigger the fill of the table
 * 
 * @author Orlandin Marco
 *
 */
public class FillContentPropertyDescriptor extends CheckBoxPropertyDescriptor {

	public FillContentPropertyDescriptor(String displayName) {
		super(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, displayName);
	}
	
	@Override
	public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPBoolean<CheckBoxPropertyDescriptor>(parent, section, this){
			@Override
			protected void checkboxSelected() {
				JSSCompoundCommand command = new JSSCompoundCommand(section.getElement());
				command.setLabel("Set Fill Space");
				for(APropertyNode pNode : section.getElements()){
					if (pNode instanceof MTable){
						MTable table = (MTable)pNode;
						SetTableAutoresizeProperty setCommand = new SetTableAutoresizeProperty(table, cmb3Bool.getSelection());
						command.add(setCommand);
					}
				}
				section.runCommand(command);
			}
		};
	}

}
