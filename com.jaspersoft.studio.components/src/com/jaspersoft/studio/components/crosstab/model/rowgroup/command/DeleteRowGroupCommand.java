/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.rowgroup.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.command.PostSetSizeCell;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.engine.JRException;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteRowGroupCommand extends Command {

	private JRDesignCrosstab jrCrosstab;
	
	private JRDesignCrosstabRowGroup jrRowGroup;
	
	private MCrosstab crosstabNode;
	
	private Map<String, JRCrosstabCell> removedCells = null;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete parameter command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 */
	public DeleteRowGroupCommand(MRowGroups destNode, MRowGroup srcNode) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.crosstabNode = (MCrosstab)destNode.getParent();
		this.jrRowGroup = (JRDesignCrosstabRowGroup) srcNode.getValue();
	}

	public DeleteRowGroupCommand(MCrosstab destNode, MRowGroup srcNode) {
		super();
		this.jrCrosstab = destNode.getValue();
		this.crosstabNode = destNode;
		this.jrRowGroup = (JRDesignCrosstabRowGroup) srcNode.getValue();
	}
	
	public DeleteRowGroupCommand(MCrosstab crosstab, JRDesignCrosstabRowGroup group){
		super();
		this.jrCrosstab = crosstab.getValue();
		this.crosstabNode = crosstab;
		this.jrRowGroup = group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrCrosstab.getRowGroupsList().indexOf(jrRowGroup);
		removeRowGroup(jrCrosstab, jrRowGroup);
		JSSCompoundCommand c = new JSSCompoundCommand("Resize Crosstab Cell", crosstabNode);
		PostSetSizeCell.createLayoutCommand(crosstabNode, c);
		c.execute();
	}
	
	protected void removeRowGroup(JRDesignCrosstab jrCross, JRDesignCrosstabRowGroup jrRowGr) {
		removedCells = new HashMap<String, JRCrosstabCell>();
		String name = jrRowGr.getName();
		List<JRCrosstabCell> cells = new ArrayList<JRCrosstabCell>(jrCross.getCellsList());
		for (int i = 0; i < cells.size(); i++) {
			JRDesignCrosstabCell cell = (JRDesignCrosstabCell) cells.get(i);
			if (cell != null) {
				String totalGroup = cell.getRowTotalGroup();
				if (totalGroup != null && totalGroup.equals(name)) {
					removedCells.put(cell.getColumnTotalGroup(), cell);
					jrCross.removeCell(cell);
				}
			}
		}
		jrCross.removeRowGroup(jrRowGr);
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrRowGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrCrosstab == null || jrRowGroup == null)
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
			CrosstabUtil.addRowGroup(jrCrosstab, jrRowGroup, elementPosition, removedCells);
			JSSCompoundCommand c = new JSSCompoundCommand("Resize Crosstab Cell", crosstabNode);
			PostSetSizeCell.createLayoutCommand(crosstabNode, c);
			c.execute();
			jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrRowGroup);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, JRCrosstabCell> getRemovedCells(){
		return removedCells;
	}
}
