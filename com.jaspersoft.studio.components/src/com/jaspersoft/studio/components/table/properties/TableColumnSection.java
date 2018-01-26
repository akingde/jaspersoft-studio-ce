/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.properties;

import java.util.HashSet;
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

import net.sf.jasperreports.components.table.BaseColumn;
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
	
	/**
	 * If the changed property is done on multiple columns then a single {@link JSSCompoundTableCommand} will be 
	 * generated, cotaining all the command to resize every columns. Doing this the fill space operation will done
	 * only once when changing the columns width of more columns from the property view. Commands to change other properties
	 * will be generated as usual. Also if the changed property is the width build correctly the list of fixed size
	 * columns
	 */
	@Override
	public boolean changeProperty(Object property, Object newValue, List<Command> commands) {
		if (!isRefreshing() && getElements() != null && !getElements().isEmpty() && getEditDomain() != null) {
			CommandStack cs = getEditDomain().getCommandStack();
			JSSCompoundTableCommand cc = new JSSCompoundTableCommand(((MColumn)getElement()).getMTable());
			HashSet<BaseColumn> fixedColumns = new HashSet<BaseColumn>();
			for (APropertyNode n : getElements()) {
				if (StandardBaseColumn.PROPERTY_WIDTH.equals(property) && n instanceof MColumn){
					cc.setLayoutTableContent(true);
					//Add the column to the fixed ones
					fixedColumns.add(((MColumn)n).getValue());
					if (isChanged(property, newValue, n)) {
						SetColumnWidthCommand setWidthCommand = new SetColumnWidthCommand((MColumn)n, (Integer)newValue);
						cc.add(setWidthCommand);	
					}
				} else if (isChanged(property, newValue, n)) {
					Command c = getChangePropertyCommand(property, newValue, n);
					if (c != null){
						cc.add(c);
					}
				}
			}
			cc.setFixedColumns(fixedColumns);
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
