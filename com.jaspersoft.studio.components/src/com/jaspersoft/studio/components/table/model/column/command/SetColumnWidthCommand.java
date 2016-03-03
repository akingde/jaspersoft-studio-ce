package com.jaspersoft.studio.components.table.model.column.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.model.column.MColumn;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

public class SetColumnWidthCommand extends Command {
	
	private final static ArrayList<BaseColumn> EMPTY_ARRAY = new ArrayList<BaseColumn>();
	
	private StandardTable table;
	
	private BaseColumn column;
	
	private int newWidth;
	
	private HashMap<StandardBaseColumn, Integer> undoWidths = new HashMap<StandardBaseColumn, Integer>();
	
	public SetColumnWidthCommand(MColumn column, int newWidth){
		this.newWidth = newWidth;
		this.column = column.getValue();
		this.table = column.getTable().getStandardTable();
	}
	
	public void setWidth() {
		int delta = newWidth - column.getWidth();
		setColumnWidth((StandardBaseColumn)column, newWidth);
		
		if (column instanceof StandardColumnGroup){
			setWidthOnChildren((StandardColumnGroup) column,  newWidth, EMPTY_ARRAY);		
		}

		BaseColumn currentCol = column;
		StandardColumnGroup currentParent = getParent(currentCol);
		while(currentParent != null){
			setColumnWidth(currentParent, currentParent.getWidth() + delta);
			currentCol = currentParent;
			currentParent = getParent(currentCol);
		}
	}
	
	protected void setColumnWidth(StandardBaseColumn col, int width){
		undoWidths.put(col, col.getWidth());
		col.setWidth(width);
	}
	
	protected void setWidthOnChildren(StandardColumnGroup group, int newSize, List<BaseColumn> excludedChildren){
		int[] childrenNewWidths = getColumnsProportionalWidth(group.getColumns(), newSize, excludedChildren);
		int index = 0;
		for(BaseColumn child : group.getColumns()){
			int newChildWidth = childrenNewWidths[index];
			if (child instanceof StandardColumnGroup){
				setWidthOnChildren((StandardColumnGroup)child, newChildWidth, excludedChildren);
			}
			setColumnWidth((StandardBaseColumn)child, newChildWidth);
			index++;
		}
	}
	
	private StandardColumnGroup getParent(BaseColumn child){
		for(BaseColumn tableChild : table.getColumns()){
			if (tableChild == child) return null;
		}
		for(BaseColumn tableChild : table.getColumns()){
			if (tableChild instanceof StandardColumnGroup){
				StandardColumnGroup searchInGroup = getParent(child, (StandardColumnGroup)tableChild);
				if (searchInGroup != null) return searchInGroup;
			}
		}
		return null;
	}

	private StandardColumnGroup getParent(BaseColumn child, StandardColumnGroup currentGroup){
		for(BaseColumn groupChild : currentGroup.getColumns()){
			if (groupChild == child) return currentGroup;
		}
		for(BaseColumn groupChild : currentGroup.getColumns()){
			if (groupChild instanceof StandardColumnGroup){
				StandardColumnGroup searchInGroup = getParent(child, (StandardColumnGroup)groupChild);
				if (searchInGroup != null) return searchInGroup;
			}
		}
		return null;
	}
	
	@Override
	public void execute() {
		undoWidths.clear();
		setWidth();
	}
	
	@Override
	public boolean canExecute() {
		return newWidth >= 0 && column != null && table != null ;
	}
	
	private int[] getColumnsProportionalWidth(List<BaseColumn> columns, int newWidth, List<BaseColumn> fixedColumns){
		int[] proportionalWidths = new int[columns.size()];
		int index = 0;
		int currentColumnsWidth = 0;
		for(BaseColumn col : columns){
			currentColumnsWidth += col.getWidth();
		}
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;			
		for(BaseColumn col : columns){
			if(fixedColumns.contains(col)){
				proportionalWidths[index] = col.getWidth();
				columnsTotalWidth += col.getWidth();		
			} else {
				float proportionalFactor = (float)col.getWidth() / (float)currentColumnsWidth;
				//casting to int is the same to do the floor operation, since it drop the decimal
				int proportionalWidth = (int)(proportionalFactor * newWidth);
				proportionalWidths[index] = proportionalWidth;
				columnsTotalWidth += proportionalWidth;				
			}
			index ++;
		}
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		index = 0;
		while (remains > 0 && proportionalWidths.length > 0){
			BaseColumn currentColumn = columns.get(index);
			if (!fixedColumns.contains(currentColumn)){
				proportionalWidths[index]++;
				remains--;	
			}
			index++;
			if (index == proportionalWidths.length){
				index = 0;
			}
		}
		return proportionalWidths;
	}
	
	@Override
	public void undo() {
		for(Entry<StandardBaseColumn, Integer> undoEntry : undoWidths.entrySet()){
			undoEntry.getKey().setWidth(undoEntry.getValue());
		}
	}

}
