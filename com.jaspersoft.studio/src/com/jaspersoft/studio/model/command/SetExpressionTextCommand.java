/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import org.eclipse.gef.commands.Command;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Command used to update the textual value of an expression
 * 
 * @author Orlandin Marco
 *
 */
public class SetExpressionTextCommand extends Command{
	
	/**
	 * The expression to update
	 */
	private JRExpression exp = null;
	
	/**
	 * The old value, used for undo
	 */
	private String oldText = null;
	
	/**
	 * The new textual value of the expression
	 */
	private String newText = null;
	
	/**
	 * Create the command 
	 * 
	 * @param exp The expression to update. The command can execute only if it is not null
	 * @param newValue The new textual value of the expression
	 */
	public SetExpressionTextCommand(JRExpression exp, String newValue){
		this.exp = exp;
		this.newText = newValue;
	}
	
	@Override
	public void execute() {
		oldText = exp.getText();
		((JRDesignExpression)exp).setText(newText);
	}
	
	@Override
	public void undo() {
		((JRDesignExpression)exp).setText(oldText);
	}
	
	@Override
	public boolean canExecute() {
		return exp != null;
	}
	
	@Override
	public boolean canUndo() {
		return exp != null;
	}
}
