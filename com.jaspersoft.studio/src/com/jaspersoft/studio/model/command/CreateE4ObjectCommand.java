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

package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.frame.MFrame;

public class CreateE4ObjectCommand extends CreateElementCommand {
	protected ANode child;
	protected ANode parent;

	public CreateE4ObjectCommand(ANode child, ANode parent, Rectangle location, int index) {
		super();
		this.child = child;
		this.parent = parent;
		this.location = location;
		this.index = index;
		this.jasperDesign = parent.getJasperDesign();
	}

	@Override
	protected void createObject() {
		try {
			Tag tag = Tag.getExpression(child);
			ANode n = null;
			if (parent instanceof MFrame) {
				n = (ANode) ((MFrame) parent).getBand();
			} else
				n = fixPosition(parent, child, location);
			if (n instanceof MBand) {
				JRDesignBand b = (JRDesignBand) n.getValue();
				BandTypeEnum btype = b.getOrigin().getBandTypeValue();
				if (btype.equals(BandTypeEnum.DETAIL)) {
					srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.COLUMN_FOOTER) || btype.equals(BandTypeEnum.COLUMN_HEADER)) {
					var = Tag.createVariable(tag, ResetTypeEnum.COLUMN, null, jasperDesign);
					srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.GROUP_FOOTER)) {
					var = Tag.createVariable(tag, ResetTypeEnum.GROUP, ((MBandGroupFooter) n).getJrGroup(), jasperDesign);
					srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.GROUP_HEADER)) {
					var = Tag.createVariable(tag, ResetTypeEnum.GROUP, ((MBandGroupHeader) n).getJrGroup(), jasperDesign);
					srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.SUMMARY) || btype.equals(BandTypeEnum.TITLE)) {
					var = Tag.createVariable(tag, ResetTypeEnum.REPORT, null, jasperDesign);
					srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else if (btype.equals(BandTypeEnum.PAGE_FOOTER) || btype.equals(BandTypeEnum.PAGE_HEADER)
						|| btype.equals(BandTypeEnum.LAST_PAGE_FOOTER)) {
					var = Tag.createVariable(tag, ResetTypeEnum.PAGE, null, jasperDesign);
					srcNode = Tag.createTextField(tag.txt.replaceAll("%", tag.name), tag.classname);
				} else {
					srcNode = Tag.createStaticText(tag.name);
				}
			} else {
				srcNode = Tag.createStaticText(tag.name);
			}
			if (parent instanceof MFrame)
				setContext(parent, srcNode, index);
			else
				setContext(n, srcNode, index);

			super.createObject();
		} catch (Exception e) {

		}
	}

	private JRDesignVariable var;

	@Override
	public void execute() {
		super.execute();
		try {
			if (var != null)
				jasperDesign.addVariable((JRDesignVariable) var);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (var != null)
			jasperDesign.removeVariable(var);
	}
}
