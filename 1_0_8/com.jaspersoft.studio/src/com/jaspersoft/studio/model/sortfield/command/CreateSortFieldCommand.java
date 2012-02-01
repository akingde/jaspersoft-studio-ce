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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.sortfield.MSortField;
import com.jaspersoft.studio.model.sortfield.MSortFields;
import com.jaspersoft.studio.model.sortfield.command.wizard.SortFieldWizard;

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
		createObject();
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
			}
		}
	}

	private void createObject() {
		if (jrField == null) {
			jrField = MSortField.createJRSortField(jrDataSet);
			SortFieldWizard wizard = new SortFieldWizard();
			wizard.init(jrDataSet, jrField);
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() != Dialog.OK) {
				jrField = null;
				return;
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
		if (jrField != null)
			jrDataSet.removeSortField(jrField);
	}
}
