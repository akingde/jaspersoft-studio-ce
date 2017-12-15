/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.columngroup.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.command.PostSetSizeCell;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroups;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.engine.JRException;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteColumnGroupCommand extends Command {

	private JRDesignCrosstab jrCrosstab;
	
	private JRDesignCrosstabColumnGroup jrColumnGroup;
	
	private MCrosstab crosstabNode;
	
	private Map<String, JRCrosstabCell>removedCells = null;

	/** The element position. */
	private int index = 0;

	/**
	 * Instantiates a new delete parameter command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 */
	public DeleteColumnGroupCommand(MColumnGroups destNode, MColumnGroup srcNode) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.crosstabNode = (MCrosstab)destNode.getParent();
		this.jrColumnGroup = (JRDesignCrosstabColumnGroup) srcNode.getValue();
	}

	public DeleteColumnGroupCommand(MCrosstab destNode, MColumnGroup srcNode) {
		super();
		this.jrCrosstab = destNode.getValue();
		this.crosstabNode = destNode;
		this.jrColumnGroup = (JRDesignCrosstabColumnGroup) srcNode.getValue();
	}
	
	public DeleteColumnGroupCommand(MCrosstab crosstab, JRDesignCrosstabColumnGroup columnGroup) {
		super();
		this.jrCrosstab = crosstab.getValue();
		this.crosstabNode = crosstab;
		this.jrColumnGroup = columnGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrCrosstab.getColumnGroupsList().indexOf(jrColumnGroup);
		removeColumnGroup(jrCrosstab, jrColumnGroup);
		JSSCompoundCommand c = new JSSCompoundCommand("Resize Crosstab Cell", crosstabNode);
		PostSetSizeCell.createLayoutCommand(crosstabNode, c);
		c.execute();
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrColumnGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrCrosstab == null || jrColumnGroup == null)
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
			CrosstabUtil.addColumnGroup(jrCrosstab, jrColumnGroup, index, removedCells);
			JSSCompoundCommand c = new JSSCompoundCommand("Resize Crosstab Cell", crosstabNode);
			PostSetSizeCell.createLayoutCommand(crosstabNode, c);
			c.execute();
			jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrColumnGroup);
		} catch(JRException ex){
			ex.printStackTrace();
		}
	}
	
	public void removeColumnGroup(JRDesignCrosstab jrCross, JRDesignCrosstabColumnGroup jrColGr) {
		removedCells = new HashMap<String, JRCrosstabCell>();
		String name = jrColGr.getName();
		List<JRCrosstabCell> cells = new ArrayList<JRCrosstabCell>(jrCross.getCellsList());
		for (int i = 0; i < cells.size(); i++) {
			JRDesignCrosstabCell cell = (JRDesignCrosstabCell) cells.get(i);
			if (cell != null) {
				String totalGroup = cell.getColumnTotalGroup();
				if (totalGroup != null && totalGroup.equals(name)) {
					removedCells.put(cell.getRowTotalGroup(), cell);
					jrCross.removeCell(cell);
				}
			}
		}
		jrCross.removeColumnGroup(jrColGr);
		jrCross.preprocess();
	}
	
	public Map<String, JRCrosstabCell> getRemovedCells(){
		return removedCells;
	}
}
