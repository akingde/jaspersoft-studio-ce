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
package com.jaspersoft.studio.components.chartspider.wizard.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.chartspider.model.MSpiderChart;
import com.jaspersoft.studio.components.chartspider.model.command.EditSpiderChartCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.band.MBand;

public class ChartSpiderWizardAction extends SelectionAction {
	public static final String ID = "chartspidereditaction"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public ChartSpiderWizardAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createEditCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	public Command createEditCommand(List<?> objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			ANode node = (ANode) object.getModel();
			if (node instanceof MSpiderChart) {
				INode parent = node.getParent();
				if (parent instanceof MFrame)
					return new EditSpiderChartCommand((MFrame) parent,
							(MSpiderChart) node);
				if (parent instanceof MBand)
					return new EditSpiderChartCommand((MBand) parent,
							(MSpiderChart) node);
				if (parent instanceof MElementGroup)
					return new EditSpiderChartCommand((MElementGroup) parent,
							(MSpiderChart) node);
			}
		}

		return null;
	}

	@Override
	public void run() {
		execute(createEditCommand(getSelectedObjects()));
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText("Edit Chart Wizard");
		setToolTipText("Edit chart wizard");
		setId(ChartSpiderWizardAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
}
