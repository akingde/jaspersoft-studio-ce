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
package com.jaspersoft.studio.model.image.command;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DialogEnabledCommand;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.image.command.dialog.ImageCreationDialog;

public class CreateImageCommand extends CreateElementCommand implements DialogEnabledCommand{

	private JRDesignExpression imageExpression;

	public CreateImageCommand(ANode destNode, MGraphicElement srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	public CreateImageCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateImageCommand(MElementGroup destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateImageCommand(MFrame destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Creates the object.
	 */
	@Override
	protected void createObject() {
		if (getJrElement() == null) {
			if (srcNode.getValue() == null)
				jrElement = srcNode.createJRElement(srcNode.getJasperDesign());
			else
				jrElement = (JRDesignElement) srcNode.getValue();

			if (jrElement != null)
				setElementBounds();

			// NOTE: #createObject() is invoked during executeCommand that is
			// supposed to be called only when the ImageCreationDialog has been
			// opened and closed successfully.
			// So if we are here, the user has already chosen how to set
			// the image expression correctly.
			((JRDesignImage)jrElement).setExpression(imageExpression);
		}
	}

	@Override
	public int openDialog() {
		ImageCreationDialog d=new ImageCreationDialog(Display.getCurrent().getActiveShell());
		d.configureDialog(jConfig);
		int dialogResult = d.open();
		imageExpression = d.getImageExpression();
		return dialogResult;
	}
}
