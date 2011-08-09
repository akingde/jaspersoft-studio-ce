/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.dataset.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.wizard.DatasetWizard;
/*/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateDatasetCommand extends Command {

	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the dataset command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateDatasetCommand(MReport destNode, MDataset srcNode, int index) {
		super();
		this.jrDesign = destNode.getJasperDesign();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrDataset = (JRDesignDataset) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrDataset != null) {
			try {
				if (index < 0 || index > jrDesign.getDatasetsList().size())
					jrDesign.addDataset(jrDataset);
				else
					jrDesign.addDataset(index, jrDataset);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}

	protected void createObject() {
		if (jrDataset == null) {
			DatasetWizard wizard = new DatasetWizard();
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			wizard.init(jrDesign);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				MDataset srcNode = wizard.getDataset();
				if (srcNode.getValue() == null)
					jrDataset = MDataset.createJRDataset(jrDesign);
				else {
					jrDataset = (JRDesignDataset) srcNode.getValue();
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
		jrDesign.removeDataset(jrDataset);
	}
}
