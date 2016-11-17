/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.columngroup.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroups;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.LazyCrosstabLayoutCommand;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.engine.JRException;

/*
 * The Class ReorderParameterCommand.
 */
public class ReorderColumnGroupCommand extends Command {

	private int oldIndex, newIndex;

	private JRDesignCrosstabColumnGroup jrColumnGroup;
	
	private MCrosstab crosstabNode;

	private JRDesignCrosstab jrCrosstab;

	private LazyCrosstabLayoutCommand layoutCommand;

	/**
	 * Instantiates a new reorder parameter command.
	 * 
	 * @param child
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderColumnGroupCommand(MColumnGroup child, MColumnGroups parent,
			int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = Math.max(0, newIndex);
		this.crosstabNode = child.getMCrosstab();
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrColumnGroup = (JRDesignCrosstabColumnGroup) child.getValue();
		this.layoutCommand = new LazyCrosstabLayoutCommand(child.getMCrosstab());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrCrosstab.getColumnGroupsList().indexOf(jrColumnGroup);
		
		DeleteColumnGroupCommand deleteCommand = new DeleteColumnGroupCommand(crosstabNode, jrColumnGroup);
		deleteCommand.execute();
		
		try {
			CrosstabUtil.addColumnGroup(jrCrosstab, jrColumnGroup, newIndex, deleteCommand.getRemovedCells());
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrColumnGroup);
		layoutCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		DeleteColumnGroupCommand deleteCommand = new DeleteColumnGroupCommand(crosstabNode, jrColumnGroup);
		deleteCommand.execute();
		
		try {
			CrosstabUtil.addColumnGroup(jrCrosstab, jrColumnGroup, oldIndex, deleteCommand.getRemovedCells());
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrColumnGroup);
		layoutCommand.undo();
	}
	
	@Override
	public boolean canExecute() {
		return newIndex != -1;
	}
}
