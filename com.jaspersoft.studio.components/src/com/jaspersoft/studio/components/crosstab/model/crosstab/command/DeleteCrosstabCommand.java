/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.sf.jasperreports.eclipse.ui.util.RunnableCancelQuestion.RESPONSE_TYPE;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.DeleteElementCommand;
import com.jaspersoft.studio.model.style.command.DeleteStyleCommand;

/**
 * Delete the element and also the styles that where used inside and 
 * that will be not used anymore once it is deleted
 * 
 * @author Orlandin Marco 
 */
public class DeleteCrosstabCommand extends DeleteElementCommand {

	/**
	 * The list of command executed to delete the styles associated to the element
	 */
	private List<Command> delteStylesCommand = new ArrayList<Command>();
	
	/**
	 * The element to delete
	 */
	private MCrosstab crosstab;
	
	/**
	 * Flag to remeber if the operation was cancelled or not
	 */
	private boolean cancelled = false;
	
	/**
	 *Create the command
	 *
	 *@param srcNode the table to delete, must be not null
	 *
	 */
	public DeleteCrosstabCommand(MCrosstab srcNode) {
		super(srcNode);
		crosstab = srcNode;
	}

	/**
	 * Check if a style is used only by the crosstab or also by someone else
	 * 
	 * @param nodesUsingInsideCrosstab nodes using the style that are inside the ccrosstab
	 * @param nodesUsingOutsideCrosstab nodes using the style in the whole report
	 * 
	 *  @return true if the styles is used only by the nodes inside the crosstab, false otherwise
	 */
	private boolean isUsedOnlyByCrosstab(List<ANode> nodesUsingInsideCrosstab, List<ANode> nodesUsingOutsideCrosstab){
		if (nodesUsingInsideCrosstab == null || nodesUsingOutsideCrosstab == null) return false;
		if (nodesUsingInsideCrosstab.size() != nodesUsingOutsideCrosstab.size()) return false;
		
		for(ANode nodeUsingInsideTable : nodesUsingInsideCrosstab){
			if (!nodesUsingOutsideCrosstab.contains(nodeUsingInsideTable)){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void execute() {
		delteStylesCommand.clear();
		ANode root = (ANode)crosstab.getParent().getRoot();
		//get the list of styles used in the element
		HashMap<String, List<ANode>> reportStyles = root.getUsedStyles();
		HashMap<String, List<ANode>> crosstabStyles = crosstab.getUsedStyles();
		
		//check which styles were used in the element but not in the rest of the report
		List<String> unusedStyles = new ArrayList<String>();
		
		for(Entry<String, List<ANode>> entry : crosstabStyles.entrySet()){
			List<ANode> nodesUsingOutsideTable = reportStyles.get(entry.getKey());
			if (jDesign.getStylesMap().containsKey(entry.getKey()) && 
					isUsedOnlyByCrosstab(entry.getValue(), nodesUsingOutsideTable)){
				unusedStyles.add(entry.getKey());
			}
		}
		if (!unusedStyles.isEmpty()){
			//If there are style that can be removed ask the user what to do and in case
			//generate the commands to remove them
			String unusedStylesName = ""; //$NON-NLS-1$
			for(int i = 0; i<unusedStyles.size(); i++){
				unusedStylesName += unusedStyles.get(i);
				if (i != unusedStyles.size()-1 ){
					unusedStylesName += ", "; //$NON-NLS-1$
				}
			}
			RESPONSE_TYPE response = UIUtils.showCancellableConfirmation(Messages.DeleteTableCommand_unusedTitle, MessageFormat.format(Messages.DeleteTableCommand_unusedMessage, new Object[]{unusedStylesName}));
			if (response == RESPONSE_TYPE.YES){
				for(String style : unusedStyles){
					JRStyle styleObject = jDesign.getStylesMap().get(style);
					DeleteStyleCommand command = new DeleteStyleCommand(jDesign, (JRDesignStyle)styleObject);
					delteStylesCommand.add(command);
				}
			} else if (response == RESPONSE_TYPE.CANCEL) {
				cancelled = true;
			}
		}
		if (!cancelled){
			for(Command cmd : delteStylesCommand){
				cmd.execute();
			}
			super.execute();
		}
	}
	
	@Override
	public void undo() {
		if (!cancelled){
			for(Command cmd : delteStylesCommand){
				cmd.undo();
			}
			super.undo();
		}
	}
	
}
