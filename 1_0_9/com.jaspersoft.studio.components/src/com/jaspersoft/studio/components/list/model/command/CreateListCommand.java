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
package com.jaspersoft.studio.components.list.model.command;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.list.model.command.wizard.ListWizard;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;

public class CreateListCommand extends CreateElementCommand {

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateListCommand(MElementGroup destNode, MGraphicElement srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateListCommand(MFrame destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateListCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param position
	 *            the position
	 * @param index
	 *            the index
	 */
	public CreateListCommand(ANode destNode, MGraphicElement srcNode,
			Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	/**
	 * Creates the object.
	 */
	@Override
	protected void createObject() {
		if (jrElement == null) {
			ListWizard wizard = new ListWizard();
			WizardDialog dialog = new WizardDialog(Display.getDefault()
					.getActiveShell(), wizard);
			wizard.init(jConfig);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				srcNode = wizard.getList();
				if (srcNode.getValue() == null)
					jrElement = srcNode.createJRElement(srcNode
							.getJasperDesign());
				else {
					jrElement = (JRDesignElement) srcNode.getValue();
				}
				if (jrElement != null) {
					setElementBounds();
					JRDesignComponentElement jrel = (JRDesignComponentElement) jrElement;
					StandardListComponent jrList = (StandardListComponent) jrel
							.getComponent();
					DesignListContents contents = (DesignListContents) jrList
							.getContents();
					contents.setHeight(jrElement.getHeight());
					contents.setWidth(jrElement.getWidth());
				}
			}
		}
	}

}
