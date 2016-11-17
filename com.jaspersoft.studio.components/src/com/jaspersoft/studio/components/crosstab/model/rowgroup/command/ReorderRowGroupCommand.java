/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.rowgroup.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.LazyCrosstabLayoutCommand;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.engine.JRException;
/*
 * The Class ReorderParameterCommand.
 */
public class ReorderRowGroupCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr parameter. */
	private JRDesignCrosstabRowGroup jrRowGroup;
	
	private MCrosstab crosstabNode;

	/** The jr dataset. */
	private JRDesignCrosstab jrCrosstab;
	
	private LazyCrosstabLayoutCommand layoutCommand;

	/**
	 * Instantiates a new reorder parameter command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderRowGroupCommand(MRowGroup child, MRowGroups parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = newIndex;
		this.crosstabNode = child.getMCrosstab();
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrRowGroup = (JRDesignCrosstabRowGroup) child.getValue();
		this.layoutCommand = new LazyCrosstabLayoutCommand(child.getMCrosstab());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrCrosstab.getRowGroupsList().indexOf(jrRowGroup);
		
		DeleteRowGroupCommand deleteCommand = new DeleteRowGroupCommand(crosstabNode, jrRowGroup);
		deleteCommand.execute();
		
		try {
			CrosstabUtil.addRowGroup(jrCrosstab, jrRowGroup, newIndex, deleteCommand.getRemovedCells());
		} catch (JRException e) {
			e.printStackTrace();
		}
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrRowGroup);
		layoutCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		DeleteRowGroupCommand deleteCommand = new DeleteRowGroupCommand(crosstabNode, jrRowGroup);
		deleteCommand.execute();
		
		try {
			CrosstabUtil.addRowGroup(jrCrosstab, jrRowGroup, oldIndex, deleteCommand.getRemovedCells());
		} catch (JRException e) {
			e.printStackTrace();
		}
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrRowGroup);
		layoutCommand.execute();
	}
	
	@Override
	public boolean canExecute() {
		return newIndex != -1;
	}
}
