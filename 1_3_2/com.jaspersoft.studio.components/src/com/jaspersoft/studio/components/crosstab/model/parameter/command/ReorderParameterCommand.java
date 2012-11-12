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
package com.jaspersoft.studio.components.crosstab.model.parameter.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabParameter;
import net.sf.jasperreports.engine.JRException;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.parameter.MCrosstabParameters;
import com.jaspersoft.studio.model.parameter.MParameter;
/*/*
 * The Class ReorderParameterCommand.
 */
public class ReorderParameterCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr parameter. */
	private JRDesignCrosstabParameter jrParameter;

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
	public ReorderParameterCommand(MParameter child, MCrosstabParameters parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrParameter = (JRDesignCrosstabParameter) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrCrosstab.getParametersList().indexOf(jrParameter);

		try {
			jrCrosstab.removeParameter(jrParameter);
			if (newIndex >= 0 && newIndex < jrCrosstab.getParametersList().size())
				jrCrosstab.addParameter(newIndex, jrParameter);
			else
				jrCrosstab.addParameter(jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		try {
			jrCrosstab.removeParameter(jrParameter);
			if (oldIndex >= 0 && oldIndex < jrCrosstab.getParametersList().size())
				jrCrosstab.addParameter(oldIndex, jrParameter);
			else
				jrCrosstab.addParameter(jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
