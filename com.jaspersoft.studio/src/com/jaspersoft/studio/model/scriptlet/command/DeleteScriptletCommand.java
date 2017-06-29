/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.scriptlet.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.scriptlet.MScriptlet;
import com.jaspersoft.studio.model.scriptlet.MScriptlets;
/*/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteScriptletCommand extends Command {

	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/** The jr scriptlet. */
	private JRDesignScriptlet jrScriptlet;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete scriptlet command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteScriptletCommand(MScriptlets destNode, MScriptlet srcNode) {
		super();
		this.jrDataset = (JRDesignDataset) destNode.getValue();
		this.jrScriptlet = (JRDesignScriptlet) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrScriptlet.getName().equals("REPORT_SCRIPTLET"))
			jrDataset.setScriptletClass(null);
		else {
			elementPosition = jrDataset.getScriptletsList().indexOf(jrScriptlet);
			jrDataset.removeScriptlet(jrScriptlet);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDataset == null || jrScriptlet == null)
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
		try {
			if (jrScriptlet.getName().equals("REPORT_SCRIPTLET"))
				jrDataset.setScriptletClass(jrScriptlet.getValueClassName());
			else {
				if (elementPosition < 0 || elementPosition > jrDataset.getScriptletsList().size())
					jrDataset.addScriptlet(jrScriptlet);
				else
					jrDataset.addScriptlet(elementPosition, jrScriptlet);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
