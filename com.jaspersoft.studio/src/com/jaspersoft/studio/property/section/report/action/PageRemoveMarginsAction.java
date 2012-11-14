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
package com.jaspersoft.studio.property.section.report.action;

import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.report.ReportEditor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.property.SetValueCommand;

public class PageRemoveMarginsAction extends SelectionAction {
	public static final String ID = "pageRemoveMarginsAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public PageRemoveMarginsAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText("Remove Page Margins");
		setToolTipText("Remove Page Margins");
		setId(ID);
		setEnabled(false);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		ReportEditor part = (ReportEditor) getWorkbenchPart();
		MReport n = (MReport) part.getModel().getChildren().get(0);
		JasperDesign jd = n.getJasperDesign();

		CompoundCommand c = new CompoundCommand(getText());
		c.add(createResetCommand(n, JasperDesign.PROPERTY_LEFT_MARGIN, 0));
		c.add(createResetCommand(n, JasperDesign.PROPERTY_RIGHT_MARGIN, 0));
		c.add(createResetCommand(n, JasperDesign.PROPERTY_TOP_MARGIN, 0));
		c.add(createResetCommand(n, JasperDesign.PROPERTY_BOTTOM_MARGIN, 0));

		int w = jd.getPageWidth() - jd.getLeftMargin() - jd.getRightMargin();
		int h = jd.getPageHeight() - jd.getTopMargin() - jd.getBottomMargin();

		c.add(createResetCommand(n, JasperDesign.PROPERTY_PAGE_WIDTH, w));
		c.add(createResetCommand(n, JasperDesign.PROPERTY_PAGE_HEIGHT, h));

		part.getEditDomain().getCommandStack().execute(c);

	}

	private Command createResetCommand(APropertyNode n, Object prop, int value) {
		SetValueCommand cmd = new SetValueCommand();
		cmd.setPropertyId(prop);
		cmd.setPropertyValue(value);
		cmd.setTarget(n);
		return cmd;
	}

	@Override
	protected boolean calculateEnabled() {
		List<Object> selection = getSelectedObjects();
		if (!selection.isEmpty() && selection.size() == 1) {
			Object obj = selection.get(0);
			if (obj instanceof ReportPageEditPart || obj instanceof BandEditPart)
				return true;
		}

		return false;
	}
}
