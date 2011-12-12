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
package com.jaspersoft.studio.model.band.command;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.band.MBand;
/*/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteBandCommand extends Command {
	
	/** The jr element. */
	private JRDesignBand jrElement;
	
	/** The band type. */
	private BandTypeEnum bandType;
	
	/** The jr design. */
	private JasperDesign jrDesign;

	/**
	 * Instantiates a new delete band command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteBandCommand(ANode destNode, MBand srcNode) {
		super();
		this.jrElement = (JRDesignBand) srcNode.getValue();
		this.bandType = srcNode.getBandType();
		this.jrDesign = srcNode.getJasperDesign();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		switch (bandType) {
		case TITLE:
			jrDesign.setTitle(null);
			break;
		case PAGE_HEADER:
			jrDesign.setPageHeader(null);
			break;
		case COLUMN_HEADER:
			jrDesign.setColumnHeader(null);
			break;
		case COLUMN_FOOTER:
			jrDesign.setColumnFooter(null);
			break;
		case PAGE_FOOTER:
			jrDesign.setPageFooter(null);
			break;
		case LAST_PAGE_FOOTER:
			jrDesign.setLastPageFooter(null);
			break;
		case SUMMARY:
			jrDesign.setSummary(null);
			break;
		case BACKGROUND:
			jrDesign.setBackground(null);
			break;
		case NO_DATA:
			jrDesign.setNoData(null);
			break;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		switch (bandType) {
		case TITLE:
			jrDesign.setTitle(jrElement);
			break;
		case PAGE_HEADER:
			jrDesign.setPageHeader(jrElement);
			break;
		case COLUMN_HEADER:
			jrDesign.setColumnHeader(jrElement);
			break;
		case COLUMN_FOOTER:
			jrDesign.setColumnFooter(jrElement);
			break;
		case PAGE_FOOTER:
			jrDesign.setPageFooter(jrElement);
			break;
		case LAST_PAGE_FOOTER:
			jrDesign.setLastPageFooter(jrElement);
			break;
		case SUMMARY:
			jrDesign.setSummary(jrElement);
			break;
		case BACKGROUND:
			jrDesign.setBackground(jrElement);
			break;
		case NO_DATA:
			jrDesign.setNoData(jrElement);
			break;
		}
	}
}
