package com.jaspersoft.studio.data.sql.model.query.from;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.model.ANode;

public class MFromTable extends AMQueryAliased<MSqlTable> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFromTable(ANode parent, MSqlTable value) {
		super(parent, value, null);
	}

	public MFromTable(ANode parent, MSqlTable value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public String getToolTip() {
		MSqlTable mc = getValue();
		String tooltip = mc.toSQLString();
		tooltip += addAlias();
		if (getValue().getRemarks() != null)
			tooltip += "\n" + mc.getRemarks();
		return tooltip;
	}

}
