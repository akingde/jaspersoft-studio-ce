/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.columngroup.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroups;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnCommand extends Command {

	private JRDesignCrosstabColumnGroup jrGroup;
	
	private MCrosstab crosstabNode;

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
	public CreateColumnCommand(MColumnGroups destNode, MColumnGroup srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateColumnCommand(MColumnGroup destNode, MColumnGroup srcNode, int index) {
		this(destNode.getMCrosstab(), srcNode, index);
	}

	public CreateColumnCommand(MCell destNode, MColumnGroup srcNode, int index) {
		this(destNode.getMCrosstab(), srcNode, index);
	}

	public CreateColumnCommand(MCrosstab destNode, MColumnGroup srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}
	
	public CreateColumnCommand(MCrosstab destNode, JRDesignCrosstabColumnGroup jrGroup, int index) {
		super();
		this.crosstabNode = destNode;
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.index = index;
		this.jrGroup = jrGroup;
		jasperDesign = destNode.getJasperDesign();
	}

	private CreateColumnCommand(ANode destNode, MColumnGroup srcNode, int index) {
		super();
		this.crosstabNode = srcNode.getMCrosstab();
		this.jrCrosstab = (JRDesignCrosstab) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null){
			this.jrGroup = (JRDesignCrosstabColumnGroup) srcNode.getValue();
		}
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
			jrGroup = CrosstabUtil.createColumnGroup(jasperDesign, jrCrosstab,
													Messages.CreateColumnGroupCommand_column_group,
													CrosstabTotalPositionEnum.END);
		}
		if (jrGroup != null) {
			try {
				CrosstabUtil.addColumnGroup(jrCrosstab, jrGroup, index);
			} catch (JRException e) {
				e.printStackTrace();
				if (e.getMessage()
						.startsWith(
								"A group or measure having the same name already exists in the crosstab.")) { //$NON-NLS-1$
					String defaultName = ModelUtils.getDefaultName(
							jrCrosstab.getColumnGroupIndicesMap(),
							"CopyOFColumnGroup_"); //$NON-NLS-1$
					InputDialog dlg = new InputDialog(
							Display.getDefault().getActiveShell(),
							Messages.CreateColumnGroupCommand_column_group_name,
							Messages.CreateColumnGroupCommand_column_group_name_dialog,
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
		DeleteColumnGroupCommand deleteCommand = new DeleteColumnGroupCommand(crosstabNode, jrGroup);
		deleteCommand.execute();
		jrCrosstab.getEventSupport().firePropertyChange(MCrosstab.UPDATE_CROSSTAB_MODEL, null, jrGroup);
		//Fire the event to eventually update the crosstab columns size
		jrCrosstab.getEventSupport().firePropertyChange(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, jrGroup, null);
	}
}
