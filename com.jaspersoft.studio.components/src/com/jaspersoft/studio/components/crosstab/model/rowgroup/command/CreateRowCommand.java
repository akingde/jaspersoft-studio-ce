/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.rowgroup.command;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateRowCommand extends Command {

	private JRDesignCrosstabRowGroup jrGroup;
	private JRDesignCrosstab jrCrosstab;

	private int index;
	private JasperDesign jasperDesign;

	/**
	 * Instantiates a new creates the parameter command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param position
	 *            the position
	 * @param index
	 *            the index
	 */
	public CreateRowCommand(MRowGroups destNode, MRowGroup srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateRowCommand(MCrosstab destNode, MRowGroup srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateRowCommand(MRowGroup destNode, MRowGroup srcNode, int index) {
		this(destNode.getMCrosstab(), srcNode, index);
	}

	public CreateRowCommand(MCell destNode, MRowGroup srcNode, int index) {
		this(destNode.getMCrosstab(), srcNode, index);
	}

	private CreateRowCommand(ANode destNode, MRowGroup srcNode, int index) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrGroup = (JRDesignCrosstabRowGroup) srcNode.getValue();
		jasperDesign = destNode.getJasperDesign();
	}

	public CreateRowCommand(ANode destNode, JRDesignCrosstabRowGroup jrGroup, int index) {
		super();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.index = index;
		this.jrGroup = jrGroup;
		jasperDesign = destNode.getJasperDesign();
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrGroup == null) {
			jrGroup = CrosstabUtil.createRowGroup(jasperDesign, jrCrosstab,
					Messages.CreateRowGroupCommand_row_group,
					CrosstabTotalPositionEnum.END);
		}
		if (jrGroup != null) {
			try {
				CrosstabUtil.addRowGroup(jrCrosstab, jrGroup, index);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage()
						.startsWith(
								"A group or measure having the same name already exists in the crosstab.")) { //$NON-NLS-1$
					String defaultName = ModelUtils.getDefaultName(
							jrCrosstab.getRowGroupIndicesMap(),
							"CopyOFRowGroup_"); //$NON-NLS-1$
					InputDialog dlg = new InputDialog(
							Display.getDefault().getActiveShell(),
							Messages.CreateRowGroupCommand_row_group_name,
							Messages.CreateRowGroupCommand_row_group_dialog_text,
							defaultName, null);
					if (dlg.open() == InputDialog.OK) {
						jrGroup.setName(dlg.getValue());
						execute();
					}
				}
			}
		}
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrGroup);
		//Fire the event to eventually update the crosstab columns size
		jrCrosstab.getEventSupport().firePropertyChange(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, null, jrGroup);
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
		String name = jrGroup.getName();
		List<?> cells = jrCrosstab.getCellsList();
		jrCrosstab.removeRowGroup(jrGroup);
		for (int i = 0; i < cells.size(); ++i) {
			JRDesignCrosstabCell cell = (JRDesignCrosstabCell) cells.get(i);
			if (cell != null) {
				String totalGroup = cell.getColumnTotalGroup();
				if (totalGroup != null && totalGroup.equals(name)) {
					jrCrosstab.removeCell(cell);
					i--;
				}
			}
		}
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrGroup);
		//Fire the event to eventually update the crosstab columns size
		jrCrosstab.getEventSupport().firePropertyChange(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, null, jrGroup);
	}
}
