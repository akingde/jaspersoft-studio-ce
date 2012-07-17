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
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.MGraphicElement;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteElementCommand extends Command {
	private JasperDesign jDesign;
	/** The jr group. */
	private JRElementGroup jrGroup;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The element position. */
	private int elementPosition = 0;
	private JRPropertiesHolder[] pholder;

	/**
	 * Instantiates a new delete element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteElementCommand(ANode destNode, MGraphicElement srcNode) {
		super();
		jrElement = (JRDesignElement) srcNode.getValue();
		jrGroup = jrElement.getElementGroup();
		jDesign = srcNode.getJasperDesign();
		ANode parent = srcNode.getParent();
		if (parent instanceof IContainerLayout)
			pholder = ((IContainerLayout) parent).getPropertyHolder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrGroup.getChildren().indexOf(jrElement);
		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElement(jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElement(jrElement);
		}
		if (jrGroup instanceof JRPropertiesHolder) {
			String uuid = null;
			if (jrGroup instanceof JRBaseElement)
				uuid = ((JRBaseElement) jrGroup).getUUID().toString();
			Dimension d = new Dimension(0, 0);
			if (jrGroup instanceof JRCommonElement) {
				JRCommonElement jce = (JRCommonElement) jrGroup;
				d.setSize(jce.getWidth(), jce.getHeight());
			}
			if (jrGroup instanceof JRDesignBand) {
				int w = jDesign.getPageWidth() - jDesign.getLeftMargin() - jDesign.getRightMargin();
				d.setSize(w, ((JRDesignBand) jrGroup).getHeight());
			}
			if (lCmd == null) {
				ILayout layout = LayoutManager.getLayout(pholder, jDesign, uuid);
				lCmd = new LayoutCommand(jrGroup, layout, d);
				lCmd.execute();
			}
		}
	}

	private LayoutCommand lCmd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		lCmd.undo();
		if (jrGroup instanceof JRDesignElementGroup) {
			if (elementPosition > ((JRDesignElementGroup) jrGroup).getChildren().size())
				((JRDesignElementGroup) jrGroup).addElement(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElement(elementPosition, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			if (elementPosition > ((JRDesignFrame) jrGroup).getChildren().size())
				((JRDesignFrame) jrGroup).addElement(jrElement);
			else
				((JRDesignFrame) jrGroup).addElement(elementPosition, jrElement);
		}
	}
}
