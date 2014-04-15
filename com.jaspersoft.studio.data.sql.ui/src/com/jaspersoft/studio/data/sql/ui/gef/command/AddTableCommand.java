package com.jaspersoft.studio.data.sql.ui.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class AddTableCommand extends Command {
	private Rectangle location;
	private MFrom parent;
	private MSqlTable child;
	private MFromTable fromTable;

	public AddTableCommand(MFrom parent, MSqlTable child, Rectangle location) {
		this.location = location;
		this.parent = parent;
		this.child = child;
	}

	@Override
	public void execute() {
		fromTable = new MFromTable(parent, child);
		if (location != null) {
			fromTable.setPropertyValue(MFromTable.PROP_X, location.x);
			fromTable.setPropertyValue(MFromTable.PROP_Y, location.y);
		}
	}

	@Override
	public void undo() {
		parent.removeChild(fromTable);
	}
}
