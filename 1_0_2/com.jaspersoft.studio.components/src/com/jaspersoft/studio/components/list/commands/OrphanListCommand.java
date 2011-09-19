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
package com.jaspersoft.studio.components.list.commands;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.list.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
/*/*
 * The Class OrphanElementGroupCommand.
 * 
 * @author Chicu Veaceslav
 */
public class OrphanListCommand extends Command {
	
	/** The index. */
	private int index;
	
	/** The jr element. */
	private JRDesignElementGroup jrElement;
	
	/** The jr group. */
	private JRElementGroup jrGroup;

	/**
	 * Instantiates a new orphan element group command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanListCommand(ANode parent, MElementGroup child) {
		super(Messages.OrphanListCommand_orphan_child);
		this.jrElement = (JRDesignElementGroup) child.getValue();
		this.jrGroup = (JRElementGroup) parent.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrGroup.getChildren().indexOf(jrElement);
		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).removeElementGroup(jrElement);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).removeElementGroup(jrElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrGroup instanceof JRDesignElementGroup) {
			if (index > ((JRDesignElementGroup) jrGroup).getChildren().size())
				((JRDesignElementGroup) jrGroup).addElementGroup(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElementGroup(index, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			if (index > ((JRDesignFrame) jrGroup).getChildren().size())
				((JRDesignFrame) jrGroup).addElementGroup(jrElement);
			else
				((JRDesignFrame) jrGroup).addElementGroup(index, jrElement);
		}
	}

}
