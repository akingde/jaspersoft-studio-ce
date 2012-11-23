/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.variable.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariables;

/*
 * /* link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteVariableCommand extends Command {

	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/** The jr variable. */
	private JRDesignVariable jrVariable;
	private JRSortField jrSortField;
	private int oldSortFieldindex = 0;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete variable command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteVariableCommand(MVariables destNode, MVariable srcNode) {
		super();
		this.jrDataset = (JRDesignDataset) destNode.getValue();
		this.jrVariable = (JRDesignVariable) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrDataset.getVariablesList().indexOf(jrVariable);
		jrDataset.removeVariable(jrVariable);

		if (jrSortField == null)
			for (JRSortField sf : jrDataset.getSortFieldsList()) {
				if (sf.getType().equals(SortFieldTypeEnum.VARIABLE) && sf.getName().equals(jrVariable.getName())) {
					jrSortField = sf;
					break;
				}
			}
		if (jrSortField != null) {
			oldSortFieldindex = jrDataset.getSortFieldsList().indexOf(jrSortField);
			jrDataset.removeSortField(jrSortField);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDataset == null || jrVariable == null)
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
			if (elementPosition < 0 || elementPosition > jrDataset.getVariablesList().size())
				jrDataset.addVariable(jrVariable);
			else
				jrDataset.addVariable(elementPosition, jrVariable);

			if (jrSortField != null) {
				if (oldSortFieldindex >= 0 && oldSortFieldindex < jrDataset.getSortFieldsList().size())
					jrDataset.addSortField(oldSortFieldindex, jrSortField);
				else
					jrDataset.addSortField(jrSortField);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
