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
package com.jaspersoft.studio.model.band.command;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.band.MBandGroupFooter;
/*
 * creates a band in a Group.
 * 
 * @author Chicu Veaceslav
 */
public class CreateBandGroupFooterCommand extends Command {

	/** The jr band. */
	private JRDesignBand jrBand;

	/** The jr design section. */
	private JRDesignSection jrDesignSection;

	/** The index. */
	private int index = -1;

	/**
	 * Instantiates a new creates the band group footer command.
	 * 
	 * @param destNode
	 *          the dest node
	 */
	public CreateBandGroupFooterCommand() {
		super();
	}

	/**
	 * Instantiates a new creates the band group footer command.
	 * 
	 * @param destNode
	 *          the dest node
	 */
	public CreateBandGroupFooterCommand(MBandGroupFooter destNode) {
		super();
		this.jrDesignSection = (JRDesignSection) (destNode.getJrGroup()).getGroupFooterSection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrBand == null) {
			jrBand = MBandGroupFooter.createJRBand();
		}
		if (jrBand != null && jrDesignSection != null) {
			if (index < 0 || index > jrDesignSection.getBandsList().size())
				jrDesignSection.addBand(jrBand);
			else
				jrDesignSection.addBand(index, jrBand);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrBand != null && jrDesignSection != null) {
			index = jrDesignSection.getBandsList().indexOf(jrBand);
			jrDesignSection.removeBand(jrBand);
		}
	}

}
