package com.jaspersoft.studio.data.sql.ui.gef.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.metadata.keys.ForeignKey;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.model.INode;

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
		if (fromTable == null) {
			fromTable = new ArrayList<MFromTable>();
			Map<ForeignKey, MFromTable> fks = new HashMap<ForeignKey, MFromTable>();
			for (MSqlTable mtlb : child) {
				MFromTable ft = new MFromTable(parent, mtlb);
				if (location != null && child.size() == 1) {
					ft.setPropertyValue(MFromTable.PROP_X, location.x);
					ft.setPropertyValue(MFromTable.PROP_Y, location.y);
				}
				fromTable.add(ft);
				for (INode n : mtlb.getChildren()) {
					if (n instanceof MSQLColumn) {
						List<ForeignKey> lfk = ((MSQLColumn) n).getForeignKeys();
						if (lfk != null && !lfk.isEmpty())
							for (ForeignKey fk : lfk)
								fks.put(fk, ft);
					}
				}
			}
			if (fromTable.size() > 1 && !fks.keySet().isEmpty())
				// ok, we have all the keys, now let's join
				for (ForeignKey fk : fks.keySet()) {
					if (fk != null && fk.getSrcColumns() != null)
						for (MSQLColumn c : fk.getSrcColumns()) {
							MFromTable dest = hasTable(c);
							if (dest != null && !(dest instanceof MFromTableJoin)) {
								MFromTable src = fks.get(fk);
								MFromTable p = src instanceof MFromTableJoin ? (MFromTable) src.getParent() : src;

								dest.setParent(null, -1);
								fromTable.remove(dest);

								MFromTableJoin join = new MFromTableJoin(p, dest.getValue());
								MExpression mexpr = new MExpression(join, src, -1);
								mexpr.getOperands().add(new FieldOperand(fk.getSrcColumns()[0], join, mexpr));
								mexpr.getOperands().add(new FieldOperand(c, src, mexpr));

								fromTable.add(join);
								fks.put(fk, join);
							}
						}
				}
		} else {
			for (MFromTable mft : fromTable)
				mft.setParent(parent, -1);
		}
	}

	private MFromTable hasTable(MSQLColumn c) {
		for (MFromTable ft : fromTable)
			if (ft.getValue().equals(c.getParent()))
				return ft;
		return null;
	}

	@Override
	public void undo() {
		for (MFromTable mft : fromTable)
			parent.removeChild(mft);
	}
}
