/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.wizard.action;

import java.util.List;

import net.sf.jasperreports.engine.JRChart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.command.EditChartCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;

public class ChartWizardAction extends SelectionAction {
	public static final String ID = "charteditaction"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public ChartWizardAction(IWorkbenchPart part) {
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
			if (node instanceof MChart) {
				MChart n = (MChart) node;
				if (n.getValue().getChartType() == JRChart.CHART_TYPE_MULTI_AXIS)
					continue;
				INode parent = node.getParent();
				if (parent instanceof MFrame)
					return new EditChartCommand((MFrame) parent, (MChart) node);
				if (parent instanceof MBand)
					return new EditChartCommand((MBand) parent, (MChart) node);
				if (parent instanceof MElementGroup)
					return new EditChartCommand((MElementGroup) parent,
							(MChart) node);
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
		setId(ChartWizardAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
}
