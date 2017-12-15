/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band.command;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
/*/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteBandGroupFooterCommand extends Command {

	/** The jr band. */
	private JRDesignBand jrBand;

	/** The jr design section. */
	private JRDesignSection jrDesignSection;

	/** The index. */
	private int index;

	/**
	 * Instantiates a new delete band group footer command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteBandGroupFooterCommand(MReport destNode, MBandGroupFooter srcNode) {
		super();
		this.jrDesignSection = (JRDesignSection) (srcNode.getJrGroup()).getGroupFooterSection();
		this.jrBand = (JRDesignBand) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrDesignSection.getBandsList().size();
		jrDesignSection.removeBand(jrBand);
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
		if (index > jrDesignSection.getBandsList().size())
			jrDesignSection.addBand(jrBand);
		else
			jrDesignSection.addBand(index, jrBand);
	}
}
