/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chartspider.part;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.chartspider.model.MSpiderChart;
import com.jaspersoft.studio.components.chartspider.model.command.EditSpiderChartCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;

public class SpiderChartEditPart extends FigureEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Command cmd = null;
			MSpiderChart mchart = (MSpiderChart) getModel();
			INode parent = mchart.getParent();
			if (parent instanceof MFrame)
				cmd = new EditSpiderChartCommand((MFrame) parent, mchart);
			if (parent instanceof MBand)
				cmd = new EditSpiderChartCommand((MBand) parent, mchart);
			if (parent instanceof MElementGroup)
				cmd = new EditSpiderChartCommand((MElementGroup) parent, mchart);

			getViewer().getEditDomain().getCommandStack().execute(cmd);

		} else
			super.performRequest(req);
	}
}
