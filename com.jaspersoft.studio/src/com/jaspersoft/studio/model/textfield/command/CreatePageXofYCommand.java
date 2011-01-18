/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.textfield.command;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.textfield.MPageNumber;
import com.jaspersoft.studio.model.textfield.MPageXofY;
import com.jaspersoft.studio.model.textfield.MTotalPages;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreatePageXofYCommand extends CreateElementCommand {

	/** The jr design. */
	private JasperDesign jrDesign;

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreatePageXofYCommand(MElementGroup destNode, MPageXofY srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreatePageXofYCommand(MFrame destNode, MPageXofY srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreatePageXofYCommand(MBand destNode, MPageXofY srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the page xof y command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreatePageXofYCommand(ANode destNode, MPageXofY srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.command.CreateElementCommand#setContext(com.jaspersoft.studio.model.ANode,
	 * com.jaspersoft.studio.model.MGeneric, int)
	 */
	@Override
	protected void setContext(ANode destNode, MGraphicElement srcNode, int index) {
		super.setContext(destNode, srcNode, index);
		jrDesign = destNode.getJasperDesign();
	}

	/**
	 * Creates the object.
	 */
	protected void createObject() {
		Rectangle location = getLocation();
		if (location == null)
			location = new Rectangle(0, 0, 10, 10);
		int index = getIndex();
		JRElementGroup jrGroup = getJrGroup();
		if (index < 0)
			index = jrGroup.getChildren().size();

		// CREATE 2 TEXTFIELDS
		MPageNumber mPageNumber = new MPageNumber();
		JRDesignTextField tfPageNumber = mPageNumber.createJRElement(jrDesign);
		tfPageNumber.setX(location.x);
		tfPageNumber.setY(location.y);
		tfPageNumber.setWidth(mPageNumber.getDefaultWidth());
		tfPageNumber.setHeight(mPageNumber.getDefaultHeight());

		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).addElement(index, tfPageNumber);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).addElement(index, tfPageNumber);
		// CREATE STATIC FIELD SEPARATOR
		JRDesignStaticText tfStaticText = new JRDesignStaticText(jrDesign);
		tfStaticText.setText("/"); //$NON-NLS-1$
		tfStaticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
		tfStaticText.setX(location.x + tfPageNumber.getWidth());
		tfStaticText.setY(location.y);
		tfStaticText.setWidth(10);
		tfStaticText.setHeight(mPageNumber.getDefaultHeight());

		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).addElement(index, tfStaticText);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).addElement(index, tfStaticText);

		// CREATE SECOND TEXT FIELD
		MTotalPages mTotalPages = new MTotalPages();
		JRDesignTextField tfPageTotal = mTotalPages.createJRElement(jrDesign);
		tfPageTotal.setX(location.x + tfPageNumber.getWidth() + tfStaticText.getWidth());
		tfPageTotal.setY(location.y);
		tfPageTotal.setWidth(mTotalPages.getDefaultWidth());
		tfPageTotal.setHeight(mTotalPages.getDefaultHeight());

		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).addElement(index + 1, tfPageTotal);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).addElement(index + 1, tfPageTotal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.command.CreateElementCommand#execute()
	 */
	@Override
	public void execute() {
		createObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.command.CreateElementCommand#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return false;
	}
}
