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
package com.jaspersoft.studio.model.group.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.group.MGroups;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateGroupCommand extends Command {

	/** The jr field. */
	protected JRDesignGroup jrGroup;

	/** The jr data set. */
	protected JRDesignDataset jrDataSet;

	/** The index. */
	private int index;
	protected JasperDesign jrDesign;

	/**
	 * Instantiates a new creates the group command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateGroupCommand(MReport destNode, MGroup srcNode, int index) {
		super();
		this.jrDataSet = (JRDesignDataset) destNode.getJasperDesign().getMainDataset();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrGroup = (JRDesignGroup) srcNode.getValue();
		this.jrDesign = destNode.getJasperDesign();
	}

	/**
	 * Instantiates a new creates the group command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateGroupCommand(MGroups destNode, MGroup srcNode, int index) {
		super();
		this.jrDataSet = (JRDesignDataset) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrGroup = (JRDesignGroup) srcNode.getValue();
	}

	protected void createObject() {
		if (jrGroup == null) {
			jrGroup = MGroup.createJRGroup(jrDataSet);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrGroup != null) {
			try {
				if (index < 0 || index > jrDataSet.getGroupsList().size())
					jrDataSet.addGroup(jrGroup);
				else
					jrDataSet.addGroup(index, jrGroup);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage().startsWith("Duplicate declaration")) { //$NON-NLS-1$
					String defaultName = ModelUtils.getDefaultName(jrDataSet.getGroupsMap(), "CopyOFGroup_"); //$NON-NLS-1$
					InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
							Messages.CreateGroupCommand_group_name, Messages.CreateGroupCommand_group_name_dialog_text, defaultName,
							null);
					if (dlg.open() == InputDialog.OK) {
						jrGroup.setName(dlg.getValue());
						execute();
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDataSet.removeGroup(jrGroup);
	}
}
