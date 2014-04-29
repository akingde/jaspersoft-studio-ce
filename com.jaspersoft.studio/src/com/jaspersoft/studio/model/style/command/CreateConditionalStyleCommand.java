/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateConditionalStyleCommand extends Command {

	/** The jr conditional style. */
	private JRDesignConditionalStyle jrConditionalStyle;

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
		if (srcNode != null && srcNode.getValue() != null)
			this.jrConditionalStyle = (JRDesignConditionalStyle) srcNode.getValue();
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
			if (index < 0 || index > jrStyle.getConditionalStyleList().size())
				jrStyle.addConditionalStyle(jrConditionalStyle);
			else
				jrStyle.addConditionalStyle(index, jrConditionalStyle);
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
