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
package com.jaspersoft.studio.components.chart.part;

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
