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
package com.jaspersoft.studio.crosstab.model.measure.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.engine.JRException;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.model.measure.MMeasure;
import com.jaspersoft.studio.crosstab.model.measure.MMeasures;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteMeasureCommand extends Command {

	/** The jr dataset. */
	private JRDesignCrosstab jrCrosstab;

	/** The jr parameter. */
	private JRDesignCrosstabMeasure jrMeasure;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete parameter command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteMeasureCommand(MMeasures destNode, MMeasure srcNode) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.jrMeasure = (JRDesignCrosstabMeasure) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrCrosstab.getMesuresList().indexOf(jrMeasure);
		jrCrosstab.removeMeasure(jrMeasure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrCrosstab == null || jrMeasure == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		try {
			if (elementPosition < 0 || elementPosition > jrCrosstab.getMesuresList().size())
				jrCrosstab.addMeasure(jrMeasure);
			else
				jrCrosstab.addMeasure(jrMeasure);
			// jrCrosstab.addParameter(elementPosition, jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
