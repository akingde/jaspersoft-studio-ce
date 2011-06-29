/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.model.sortfield.command;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSortField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.sortfield.MSortField;
import com.jaspersoft.studio.model.sortfield.MSortFields;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateSortFieldCommand extends Command {

	/** The jr field. */
	private JRDesignSortField jrField;

	/** The jr data set. */
	private JRDesignDataset jrDataSet;

	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the field command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateSortFieldCommand(MSortFields destNode, MSortField srcNode, int index) {
		super();
		this.jrDataSet = (JRDesignDataset) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrField = (JRDesignSortField) srcNode.getValue();
	}

	public CreateSortFieldCommand(JRDesignDataset destNode, JRDesignSortField srcNode, int index) {
		super();
		this.jrDataSet = destNode;
		this.index = index;
		this.jrField = srcNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrField == null) {
			jrField = MSortField.createJRSortField(jrDataSet);
			jrField.setName(getName(jrDataSet.getSortFieldsList(), "SortField"));
		}
		if (jrField != null) {
			if (index < 0)
				index = jrDataSet.getSortFieldsList().size();
			try {
				if (index < 0 || index > jrDataSet.getSortFieldsList().size())
					jrDataSet.addSortField(jrField);
				else
					jrDataSet.addSortField(index, jrField);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage().startsWith("Duplicate declaration")) { //$NON-NLS-1$
					String defaultName = getName(jrDataSet.getSortFieldsList(), "CopyOFSortField_"); //$NON-NLS-1$
					InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "Sort Field Name",
							"Please, enter unique sort field name", defaultName, null);
					if (dlg.open() == InputDialog.OK) {
						jrField.setName(dlg.getValue());
						execute();
					}
				}
			}
		}
	}

	private String getName(List<JRSortField> fields, String name) {
		int i = 1;
		while (i < 100000) {
			String iname = name + i;
			boolean found = false;
			for (JRSortField f : fields) {
				if (f.getName().equals(iname)) {
					found = true;
					break;
				}
			}
			if (!found)
				return iname;
			i++;
		}
		return name;
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
		jrDataSet.removeSortField(jrField);
	}
}
