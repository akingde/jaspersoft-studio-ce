package com.jaspersoft.studio.components.table.model.column.command;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.model.ANode;

public class MoveColumnCommand extends Command {

	private int oldIndex, newIndex = -1;

	private StandardBaseColumn destColumn;
	private StandardBaseColumn srcColumn;

	private StandardColumnGroup pdestColGroup;
	private StandardColumnGroup psrcColGroup;

	private StandardTable jrTable;
	private TableManager tbManager;

	public MoveColumnCommand(MColumn src, MColumn dest) {
		super(Messages.ReorderColumnCommand_reorder_columns);
		tbManager = src.getMTable().getTableManager();
		this.jrTable = TableManager.getTable(src);
		this.srcColumn = src.getValue();
		this.destColumn = dest.getValue();

		ANode srcparent = src.getParent();
		if (srcparent instanceof AMCollection)
			oldIndex = jrTable.getColumns().indexOf(srcColumn);
		else if (srcparent.getValue() instanceof StandardColumnGroup) {
			psrcColGroup = (StandardColumnGroup) srcparent.getValue();
			oldIndex = psrcColGroup.getColumns().indexOf(srcColumn);
		}
		ANode destparent = dest.getParent();
		if (destparent instanceof AMCollection)
			newIndex = jrTable.getColumns().indexOf(destColumn);
		else if (destparent.getValue() instanceof StandardColumnGroup) {
			pdestColGroup = (StandardColumnGroup) destparent.getValue();
			newIndex = pdestColGroup.getColumns().indexOf(destColumn);
		}
	}

	@Override
	public void execute() {
		delColumn(psrcColGroup, srcColumn);
		addColumn(pdestColGroup, newIndex, srcColumn);
		tbManager.refresh();
	}

	@Override
	public void undo() {
		delColumn(pdestColGroup, srcColumn);
		addColumn(psrcColGroup, oldIndex, srcColumn);
		tbManager.refresh();
	}

	private void delColumn(StandardColumnGroup colGroup, StandardBaseColumn col) {
		if (colGroup != null)
			colGroup.removeColumn(col);
		else
			jrTable.removeColumn(col);
	}

	private void addColumn(StandardColumnGroup colGroup, int index,
			StandardBaseColumn col) {
		if (colGroup != null) {
			if (index >= 0 && index < colGroup.getColumns().size())
				colGroup.addColumn(index, col);
			else
				colGroup.addColumn(col);
		} else {
			if (index >= 0 && index < jrTable.getColumns().size())
				jrTable.addColumn(index, col);
			else
				jrTable.addColumn(col);
		}
	}
}
