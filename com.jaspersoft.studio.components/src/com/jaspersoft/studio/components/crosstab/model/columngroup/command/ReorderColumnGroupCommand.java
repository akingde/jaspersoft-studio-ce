/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.crosstab.model.columngroup.command;

import java.util.List;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroups;

/*
 * The Class ReorderParameterCommand.
 */
public class ReorderColumnGroupCommand extends Command {

	private int oldIndex, newIndex;

	private JRDesignCrosstabColumnGroup jrColumnGroup;

	private JRDesignCrosstab jrCrosstab;

	/**
	 * Instantiates a new reorder parameter command.
	 * 
	 * @param child
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderColumnGroupCommand(MColumnGroup child, MColumnGroups parent,
			int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = Math.max(0, newIndex);
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrColumnGroup = (JRDesignCrosstabColumnGroup) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		List<JRCrosstabColumnGroup> columns = jrCrosstab.getColumnGroupsList();
		oldIndex = columns.indexOf(jrColumnGroup);

		columns.remove(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementRemovedEvent(
				JRDesignCrosstab.PROPERTY_COLUMN_GROUPS, jrColumnGroup,
				oldIndex);
		if (newIndex >= 0 && newIndex < columns.size())
			columns.add(newIndex, jrColumnGroup);
		else
			columns.add(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementAddedEvent(
				JRDesignCrosstab.PROPERTY_COLUMN_GROUPS, jrColumnGroup,
				newIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		List<JRCrosstabColumnGroup> columns = jrCrosstab.getColumnGroupsList();
		columns.remove(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementRemovedEvent(
				JRDesignCrosstab.PROPERTY_COLUMN_GROUPS, jrColumnGroup,
				newIndex);
		if (oldIndex >= 0 && oldIndex < columns.size())
			columns.add(oldIndex, jrColumnGroup);
		else
			columns.add(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementAddedEvent(
				JRDesignCrosstab.PROPERTY_COLUMN_GROUPS, jrColumnGroup,
				oldIndex);
	}
}
