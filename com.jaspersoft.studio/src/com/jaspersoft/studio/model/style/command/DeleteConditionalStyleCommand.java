/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
/*/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteConditionalStyleCommand extends Command {
	
	/** The jr style. */
	private JRDesignStyle jrStyle;
	
	/** The jr conditional style. */
	private JRConditionalStyle jrConditionalStyle;
	
	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete conditional style command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteConditionalStyleCommand(MStyle destNode, MConditionalStyle srcNode) {
		super();
		this.jrStyle = (JRDesignStyle) destNode.getValue();
		this.jrConditionalStyle = (JRConditionalStyle) srcNode.getValue();
	}
	
	public DeleteConditionalStyleCommand(JRDesignStyle destNode, JRConditionalStyle srcNode) {
		super();
		this.jrStyle = destNode;
		this.jrConditionalStyle = srcNode;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrStyle.getConditionalStyleList().indexOf(jrConditionalStyle);
		jrStyle.removeConditionalStyle(jrConditionalStyle);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrStyle == null || jrConditionalStyle == null)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (elementPosition < 0 || elementPosition > jrStyle.getConditionalStyleList().size())
			jrStyle.addConditionalStyle(jrConditionalStyle);
		else
			jrStyle.addConditionalStyle(elementPosition, jrConditionalStyle);
	}
}
