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
package com.jaspersoft.studio.components.table.model.columngroup.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableDetail;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.CheckColumnsOrder;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnGroupCellCommand;
import com.jaspersoft.studio.components.table.model.column.command.FixCellHeightsCommand;
import com.jaspersoft.studio.components.table.model.column.command.MoveColumnCommand;
import com.jaspersoft.studio.components.table.model.column.command.RefreshColumnNamesCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompundTableCommand;
import com.jaspersoft.studio.editor.outline.actions.ACreateAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.components.table.StandardColumnGroup;

/**
 *  Create group from a set of columns and create also an header for them 
 */
public class GroupColumnsAction extends ACreateAction {

	/**
	 * The Constant ID.
	 */
	public static final String ID = "create_table_column_group"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public GroupColumnsAction(IWorkbenchPart part) {
		super(part);
		setCreationFactory(new JDPaletteCreationFactory(MColumnGroup.class));
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateColumnAction_create_column_group);
		setToolTipText(Messages.CreateColumnAction_create_column_group_tool_tip);
		setId(GroupColumnsAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor(
				"icons/table-join-row.png"));
		setDisabledImageDescriptor(Activator.getDefault().getImageDescriptor(
				"icons/table-join-row.png"));
		setEnabled(false);
	}

	/**
	 * Return a list of unique columns, not children of the detail and under the
	 * same parent to group 
	 * 
	 * @return the list of column to group, can be null if the selection is invalid
	 */
	private List<MColumn> getSelectedColumns(){
		List<MColumn> columns = new ArrayList<MColumn>();
		List<Object> objects = getSelectedObjects();
		AMCollection currentParent = null;
		for (Object obj : objects) {
			if (obj instanceof EditPart)
				obj = ((EditPart)obj).getModel();
			if (obj instanceof MColumn){
				MColumn col = (MColumn)obj;
				if (!isTableDetail(col)){
					//Check if has the same parent
					if (currentParent != null){
						if (currentParent != getColumnCollection(col)){
							return null;
						}
					} else {
						currentParent = getColumnCollection(col);
					}
					columns.add(col);
				}
			}
		}
		return columns;
	}
	
	private AMCollection getColumnCollection(MColumn col){
		ANode parent = col.getParent();
		while(parent != null && !(parent instanceof AMCollection)){
			parent = parent.getParent();
		}
		return (AMCollection)parent;
	}

	/**
	 * Check if a column is a descendant of the table detail 
	 * 
	 * @param column a column
	 * @return true if the column is null, one of his ancestor before
	 * the collection node is null or if its collection node is a {@link MTableDetail},
	 * false otherwise
	 */
	private boolean isTableDetail(MColumn column){
		if (column == null) return true;
		ANode parent = column.getParent();
		while (parent != null && !(parent instanceof AMCollection)){
			parent = parent.getParent();
		}
		return (parent == null || parent instanceof MTableDetail);
	}
	
	@Override
	public Command createCommand() {
		List<MColumn> columns = getSelectedColumns();
		MColumn fmc = columns.get(0);
		ANode mparent = fmc.getParent();
		if (mparent == null)
			return null;
		JSSCompundTableCommand c = new JSSCompundTableCommand(Messages.CreateColumnAction_create_column_group, fmc.getMTable(), true);
		MColumnGroup mcolgr = new MColumnGroup();
		int index = mparent.getChildren().indexOf(fmc);
		CreateColumnGroupCommand cmd = createGroup(index, mparent, mcolgr);
		StandardColumnGroup newGroup = (StandardColumnGroup)cmd.createColumn(fmc.getJasperDesign(), fmc.getMTable().getStandardTable());
		mcolgr.setValue(newGroup);

		c.add(new RefreshColumnNamesCommand(mparent, false, true));

		// Create the commands to fix the order on the undo
		List<CheckColumnsOrder> fixOrderCommandList = new ArrayList<CheckColumnsOrder>();
		for (MColumn src : columns) {
			fixOrderCommandList.add(new CheckColumnsOrder(src));
		}
		Collections.sort(fixOrderCommandList);
		// This commands are executed on the undo, so the list must be reversed
		Collections.reverse(fixOrderCommandList);
		c.addAll((List) fixOrderCommandList);

		// Add the commands to move the columns
		c.add(createGroup(index, mparent, mcolgr));
		for (MColumn src : columns) {
			c.add(new MoveColumnCommand(src, mcolgr, false));
		}

		c.add(new FixCellHeightsCommand(fmc));
		c.add(new RefreshColumnNamesCommand(mparent, true, false));
		c.add(new CreateColumnGroupCellCommand(fmc.getSection(), mparent, newGroup));
		return c;
	}
	
	@Override
	protected boolean calculateEnabled() {
		List<MColumn> columns = getSelectedColumns();
		if (columns == null|| columns.isEmpty()) return false;
		MTable table = columns.get(0).getMTable(); 
		if (table != null){
			return table.getStandardTable().getColumns().size() > 0;
		}
		return false;
	}

	public CreateColumnGroupCommand createGroup(int index, ANode mparent,
			MColumnGroup mcolgr) {
		CreateColumnGroupCommand cmd = null;
		if (mparent instanceof MColumn)
			cmd = new CreateColumnGroupCommand((MColumn) mparent, mcolgr, index);
		if (mparent instanceof AMCollection)
			cmd = new CreateColumnGroupCommand((AMCollection) mparent, mcolgr,
					index);
		cmd.setResize(false);
		return cmd;
	}
}
