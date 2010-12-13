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
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;

// TODO: Auto-generated Javadoc
/**
 * The Class ReorderBandCommand.
 */
public class ReorderBandCommand extends Command {
	
	/** The new index. */
	private int oldIndex, newIndex;
	
	/** The jr band. */
	private JRDesignBand jrBand;
	
	/** The jr design section. */
	private JRDesignSection jrDesignSection;

	/**
	 * Instantiates a new reorder band command.
	 * 
	 * @param child
	 *          the child
	 * @param newIndex
	 *          the new index
	 */
	public ReorderBandCommand(MBandGroupHeader child, int newIndex) {
		super(Messages.ReorderBandCommand_reorder_elements);

		this.newIndex = newIndex;
		this.jrDesignSection = (JRDesignSection) child.getJrGroup().getGroupHeaderSection();
		this.jrBand = (JRDesignBand) child.getValue();
	}

	/**
	 * Instantiates a new reorder band command.
	 * 
	 * @param child
	 *          the child
	 * @param newIndex
	 *          the new index
	 */
	public ReorderBandCommand(MBandGroupFooter child, int newIndex) {
		super(Messages.ReorderBandCommand_reorder_elements);

		this.newIndex = newIndex;
		this.jrDesignSection = (JRDesignSection) child.getJrGroup().getGroupFooterSection();
		this.jrBand = (JRDesignBand) child.getValue();
	}

	/**
	 * Instantiates a new reorder band command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderBandCommand(MBand child, MReport parent, int newIndex) {
		super(Messages.ReorderBandCommand_reorder_elements);

		this.newIndex = newIndex;
		this.jrDesignSection = (JRDesignSection) parent.getJasperDesign().getDetailSection();
		this.jrBand = (JRDesignBand) child.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrDesignSection.getBandsList().indexOf(jrBand);
		jrDesignSection.removeBand(jrBand);
		if (newIndex < 0 || newIndex > jrDesignSection.getBandsList().size())
			jrDesignSection.addBand(jrBand);
		else
			jrDesignSection.addBand(newIndex, jrBand);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrDesignSection.removeBand(jrBand);
		if (oldIndex < 0 || oldIndex > jrDesignSection.getBandsList().size())
			jrDesignSection.addBand(jrBand);
		else
			jrDesignSection.addBand(oldIndex, jrBand);

	}
}
