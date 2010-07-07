/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.group.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.group.MGroups;

// TODO: Auto-generated Javadoc
/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateGroupCommand extends Command {
	
	/** The jr field. */
	private JRDesignGroup jrField;
	
	/** The jr data set. */
	private JRDesignDataset jrDataSet;
	
	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the group command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreateGroupCommand(MReport destNode, MGroup srcNode, Point position, int index) {
		super();
		this.jrDataSet = (JRDesignDataset) destNode.getJasperDesign().getMainDataset();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrField = (JRDesignGroup) srcNode.getValue();
	}

	/**
	 * Instantiates a new creates the group command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreateGroupCommand(MGroups destNode, MGroup srcNode, Point position, int index) {
		super();
		this.jrDataSet = (JRDesignDataset) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrField = (JRDesignGroup) srcNode.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrField == null) {
			this.jrField = MGroup.createJRGroup(jrDataSet);
		}
		if (jrField != null) {
			try {
				if (index < 0 || index > jrDataSet.getGroupsList().size())
					jrDataSet.addGroup(jrField);
				else
					jrDataSet.addGroup(index, jrField);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDataSet.removeGroup(jrField);
	}
}
