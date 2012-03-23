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
package com.jaspersoft.studio.editor.gef.commands;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.action.size.Size2BorderAction;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;

public class ResizeCommand extends Command {
	private int alignement;
	private Dimension parent;
	private JRDesignElement jrElement;

	private int oldX, oldY, oldWidth, oldHeight;

	public ResizeCommand(int alignement, EditPart editPart) {
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
			} else if (n instanceof MFrame) {
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
		oldWidth = jrElement.getWidth();
		oldHeight = jrElement.getHeight();

		int newX = oldX;
		int newY = oldY;
		int newWidth = oldWidth;
		int newHeight = oldHeight;
		switch (alignement) {
		case Size2BorderAction.WIDTH:
			newX = 0;
			newWidth = parent.width;
			break;
		case Size2BorderAction.HEIGHT:
			newY = 0;
			newHeight = parent.height;
			break;
		case Size2BorderAction.BOTH:
			newX = 0;
			newY = 0;
			newWidth = parent.width;
			newHeight = parent.height;
			break;
		}
		jrElement.setX(newX);
		jrElement.setY(newY);
		jrElement.setWidth(newWidth);
		jrElement.setHeight(newHeight);
	}

	@Override
	public void undo() {
		jrElement.setX(oldX);
		jrElement.setY(oldY);
		jrElement.setWidth(oldWidth);
		jrElement.setHeight(oldHeight);
	}
}
