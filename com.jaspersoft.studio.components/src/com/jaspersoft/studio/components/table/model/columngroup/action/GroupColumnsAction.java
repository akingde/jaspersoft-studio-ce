/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.columngroup.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.CheckColumnsOrder;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnGroupCellCommand;
import com.jaspersoft.studio.components.table.model.column.command.FixCellHeightsCommand;
import com.jaspersoft.studio.components.table.model.column.command.MoveColumnCommand;
import com.jaspersoft.studio.components.table.model.column.command.RefreshColumnNamesCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.components.table.StandardColumnGroup;

/**
 *  Create group from a set of columns and create also an header for them 
 */
public class GroupColumnsAction extends ACachedSelectionAction {

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
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-join-row.png"));
		setDisabledImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-join-row.png"));
		setEnabled(false);
	}

	/**
	 * Return a list of unique and consecutive columns, not children of the detail 
	 * and under the same parent, this column can be grouped 
	 * 
	 * @return the list of column to group, can be null if the selection is invalid
	 */
	private List<MColumn> getSelectedColumns(){
		List<MColumn> columns = new ArrayList<MColumn>();
		List<Object> objects = getSelectedObjects();
		ANode currentParent = null;
		for (Object obj : objects) {
			if (obj instanceof EditPart)
				obj = ((EditPart)obj).getModel();
			if (obj instanceof MCell || obj instanceof MColumnGroup){
				MColumn col = (MColumn)obj;
				if (!isTableDetail(col)){
					//Check if has the same parent
					if (currentParent != null){
						if (currentParent != col.getParent()){
							return null;
						}
					} else {
						currentParent = col.getParent();
					}
					columns.add(col);
				}
			}
		}
		
		//If there are more than one column selected check if they are consecutive
		if (currentParent != null && columns.size() > 1){
			int[] indexes = new int[columns.size()];
			int i = 0;
			for(MColumn col : columns){
				indexes[i] = currentParent.getChildren().indexOf(col);
				i++;
			}
			Arrays.sort(indexes);
			for (i = 0; i < indexes.length - 1; i++) {
				  if (indexes[i] + 1 != indexes[i + 1]) {
					  //not consecutive
					  return null;
				  }
				}
		}
		return columns;
	}
	
	/*private AMCollection getColumnCollection(MColumn col){
		ANode parent = col.getParent();
		while(parent != null && !(parent instanceof AMCollection)){
			parent = parent.getParent();
		}
		return (AMCollection)parent;
	}*/

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
		JSSCompoundTableCommand c = new JSSCompoundTableCommand(Messages.CreateColumnAction_create_column_group, fmc.getMTable(), true);
		MColumnGroup mcolgr = new MColumnGroup();
		int index = mparent.getChildren().indexOf(fmc);
		CreateColumnGroupCommand cmd = createGroup(index, mparent, mcolgr);
		MTable table = fmc.getMTable();
		StandardColumnGroup newGroup = (StandardColumnGroup)cmd.createColumn(fmc.getJasperDesign(), table.getStandardTable(), table.getPropertiesMap());
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
