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
package com.jaspersoft.studio.components.crosstab.model.rowgroup.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;
/*
 * The Class ReorderParameterCommand.
 */
public class ReorderRowGroupCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr parameter. */
	private JRDesignCrosstabRowGroup jrRowGroup;

	/** The jr dataset. */
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
	public ReorderRowGroupCommand(MRowGroup child, MRowGroups parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrRowGroup = (JRDesignCrosstabRowGroup) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrCrosstab.getRowGroupsList().indexOf(jrRowGroup);

		jrCrosstab.getRowGroupsList().remove(jrRowGroup);
		jrCrosstab.getEventSupport().fireCollectionElementRemovedEvent(JRDesignCrosstab.PROPERTY_ROW_GROUPS, jrRowGroup,
				oldIndex);
		if (newIndex >= 0 && newIndex < jrCrosstab.getRowGroupsList().size())
			jrCrosstab.getRowGroupsList().add(newIndex, jrRowGroup);
		else
			jrCrosstab.getRowGroupsList().add(jrRowGroup);
		jrCrosstab.getEventSupport().fireCollectionElementAddedEvent(JRDesignCrosstab.PROPERTY_ROW_GROUPS, jrRowGroup,
				newIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {

		jrCrosstab.getRowGroupsList().remove(jrRowGroup);
		jrCrosstab.getEventSupport().fireCollectionElementRemovedEvent(JRDesignCrosstab.PROPERTY_ROW_GROUPS, jrRowGroup,
				newIndex);
		if (oldIndex >= 0 && oldIndex < jrCrosstab.getRowGroupsList().size())
			jrCrosstab.getRowGroupsList().add(oldIndex, jrRowGroup);
		else
			jrCrosstab.getRowGroupsList().add(jrRowGroup);
		jrCrosstab.getEventSupport().fireCollectionElementAddedEvent(JRDesignCrosstab.PROPERTY_ROW_GROUPS, jrRowGroup,
				oldIndex);
	}
}
