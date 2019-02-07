/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateConditionalStyleCommand extends Command {

	/** The jr conditional style. */
	private JRConditionalStyle jrConditionalStyle;

	/** The jr style. */
	private JRDesignStyle jrStyle;

	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the conditional style command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateConditionalStyleCommand(MStyle destNode, MConditionalStyle srcNode, int index) {
		super();
		this.index = index;
		this.jrStyle = (JRDesignStyle) destNode.getValue();
		if (srcNode != null && srcNode.getValue() != null) {
			this.jrConditionalStyle = (JRConditionalStyle) srcNode.getValue();
		}
	}
	
	/**
	 * Create the command to build the conditional style. The style is always the 
	 * last children of the parent node
	 * 
	 *  @param destNode parent node
	 *  @param style the JR object of the conditional style. It should be a new object
	 *  not used from other element of the model
	 */
	public CreateConditionalStyleCommand(MStyle destNode, JRConditionalStyle style) {
		super();
		this.index = -1;
		this.jrStyle = (JRDesignStyle) destNode.getValue();
		this.jrConditionalStyle = style;
	}
	
	/**
	 * Create the command to build the conditional style. The style is always the 
	 * last children of the parent node
	 * 
	 *  @param destNode parent style
	 *  @param style the JR object of the conditional style. It should be a new object
	 *  not used from other element of the model
	 */
	public CreateConditionalStyleCommand(JRDesignStyle destNode, JRConditionalStyle style) {
		super();
		this.index = -1;
		this.jrStyle = destNode;
		this.jrConditionalStyle = style;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrConditionalStyle == null) {
			this.jrConditionalStyle = MConditionalStyle.createJRStyle();
		}
		if (jrConditionalStyle != null) {
			if (index < 0 || index > jrStyle.getConditionalStyleList().size()){
				jrStyle.addConditionalStyle(jrConditionalStyle);
			}
			else {
				jrStyle.addConditionalStyle(index, jrConditionalStyle);
			}
			SelectionHelper.setOutlineSelection(jrConditionalStyle);
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
		jrStyle.removeConditionalStyle(jrConditionalStyle);
	}
}
