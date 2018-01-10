/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.part;

import net.sf.jasperreports.engine.JRChart;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.command.EditChartCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;

public class ChartEditPart extends FigureEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Command cmd = null;
			MChart mchart = (MChart) getModel();
			if (mchart.getValue().getChartType() == JRChart.CHART_TYPE_MULTI_AXIS)
				return;
			INode parent = mchart.getParent();
			if (parent instanceof MFrame)
				cmd = new EditChartCommand((MFrame) parent, mchart);
			if (parent instanceof MBand)
				cmd = new EditChartCommand((MBand) parent, mchart);
			if (parent instanceof MElementGroup)
				cmd = new EditChartCommand((MElementGroup) parent, mchart);

			getViewer().getEditDomain().getCommandStack().execute(cmd);

		} else
			super.performRequest(req);
	}
}
