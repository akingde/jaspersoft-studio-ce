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
package com.jaspersoft.studio.components.table.properties;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.SetColumnWidthCommand;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.components.table.StandardBaseColumn;

public class TableColumnSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, StandardBaseColumn.PROPERTY_WIDTH);
		createWidget4Property(parent,StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardBaseColumn.PROPERTY_WIDTH, Messages.MColumn_column_width);
		addProvidedProperties(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.MColumn_print_when_expression);
	}
	
	@Override
	public boolean changeProperty(Object property, Object newValue, List<Command> commands) {
		if (!isRefreshing() && getElements() != null && !getElements().isEmpty() && getEditDomain() != null) {
			CommandStack cs = getEditDomain().getCommandStack();
			JSSCompoundTableCommand cc = new JSSCompoundTableCommand(((MColumn)getElement()).getMTable());
			for (APropertyNode n : getElements()) {
				if (isChanged(property, newValue, n)) {
					if (StandardBaseColumn.PROPERTY_WIDTH.equals(property) && n instanceof MColumn){
						SetColumnWidthCommand setWidthCommand = new SetColumnWidthCommand((MColumn)n, (Integer)newValue);
						cc.add(setWidthCommand);
					} else {
						Command c = getChangePropertyCommand(property, newValue, n);
						if (c != null){
							cc.add(c);
						}
					}
				}
			}
			if (!cc.getCommands().isEmpty()) {
				if (commands != null){
					for (Command c : commands){
						cc.add(c);
					}
				}
				cs.execute(cc);
				getTabbedPropertySheetPage().showErrors();
				return true;
			}
		}
		return false;
	}
}
