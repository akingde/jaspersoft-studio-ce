/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.commands;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;

public class AlignCommand extends Command {
	private int alignement;
	private Dimension parent;
	private JRDesignElement jrElement;

	private int oldX, oldY;

	public AlignCommand(int alignement, EditPart editPart) {
		super();
		this.alignement = alignement;
		MGraphicElement m = (MGraphicElement) editPart.getModel();
		jrElement = (JRDesignElement) m.getValue();

		INode n = m.getParent();
		if (n instanceof IContainer) {
			if (n instanceof MBand) {
				// height of band, width of Report - margins
				int h = ((JRDesignBand) ((MBand) n).getValue()).getHeight();
				JasperDesign jasperDesign = m.getJasperDesign();
				int w = jasperDesign.getPageWidth() - jasperDesign.getLeftMargin() - jasperDesign.getRightMargin();
				parent = new Dimension(w, h);
			} else if(n instanceof MFrame){
				JRDesignFrame jrDesignFrame = (JRDesignFrame) ((MFrame) n).getValue();
				int h = jrDesignFrame.getHeight();
				int w = jrDesignFrame.getWidth();
				parent = new Dimension(w, h);
			}
		}

	}

	@Override
	public void execute() {
		oldX = jrElement.getX();
		oldY = jrElement.getY();

		int newX = oldX;
		int newY = oldY;
		switch (alignement) {
		case PositionConstants.LEFT:
			newX = 0;
			break;
		case PositionConstants.RIGHT:
			newX = parent.width - jrElement.getWidth();
			break;
		case PositionConstants.TOP:
			newY = 0;
			break;
		case PositionConstants.BOTTOM:
			newY = parent.height - jrElement.getHeight();
			break;
		case PositionConstants.CENTER:
			newX = parent.width / 2 - jrElement.getWidth() / 2;
			break;
		case PositionConstants.MIDDLE:
			newY = parent.height / 2 - jrElement.getHeight() / 2;
			break;
		}
		jrElement.setX(newX);
		jrElement.setY(newY);
	}

	@Override
	public void undo() {
		jrElement.setX(oldX);
		jrElement.setY(oldY);
	}
}
