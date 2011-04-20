/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model.columngroup.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.crosstab.model.columngroup.MColumnGroups;
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
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderColumnGroupCommand(MColumnGroup child, MColumnGroups parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrColumnGroup = (JRDesignCrosstabColumnGroup) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrCrosstab.getColumnGroupsList().indexOf(jrColumnGroup);

		jrCrosstab.getColumnGroupsList().remove(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementRemovedEvent(JRDesignCrosstab.PROPERTY_COLUMN_GROUPS,
				jrColumnGroup, oldIndex);
		if (newIndex >= 0 && newIndex < jrCrosstab.getColumnGroupsList().size())
			jrCrosstab.getColumnGroupsList().add(newIndex, jrColumnGroup);
		else
			jrCrosstab.getColumnGroupsList().add(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementAddedEvent(JRDesignCrosstab.PROPERTY_COLUMN_GROUPS,
				jrColumnGroup, newIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrCrosstab.getColumnGroupsList().remove(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementRemovedEvent(JRDesignCrosstab.PROPERTY_COLUMN_GROUPS,
				jrColumnGroup, newIndex);
		if (oldIndex >= 0 && oldIndex < jrCrosstab.getColumnGroupsList().size())
			jrCrosstab.getColumnGroupsList().add(oldIndex, jrColumnGroup);
		else
			jrCrosstab.getColumnGroupsList().add(jrColumnGroup);
		jrCrosstab.getEventSupport().fireCollectionElementAddedEvent(JRDesignCrosstab.PROPERTY_COLUMN_GROUPS,
				jrColumnGroup, oldIndex);
	}
}
