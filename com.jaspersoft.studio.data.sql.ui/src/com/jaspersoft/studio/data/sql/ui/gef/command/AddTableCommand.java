package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;

public class AddTableCommand extends Command {
	private Rectangle location;
	private MFrom parent;
	private Collection<MSqlTable> child;
	private List<MFromTable> fromTable;

	public AddTableCommand(MFrom parent, Collection<MSqlTable> child, Rectangle location) {
		this.location = location;
		this.parent = parent;
		this.child = child;
	}

	@Override
	public void execute() {
		MFromTable main = null;
		for (MSqlTable mtlb : child) {
			MFromTable ft = new MFromTable(parent, mtlb);
			if (location != null) {
				ft.setPropertyValue(MFromTable.PROP_X, location.x);
				ft.setPropertyValue(MFromTable.PROP_Y, location.y);
			}
			fromTable.add(ft);
			if (main != null) {
				main = ft;
				// join to main if the case
			}
		}
	}

	@Override
	public void undo() {
		for (MFromTable mft : fromTable)
			parent.removeChild(mft);
	}
}
