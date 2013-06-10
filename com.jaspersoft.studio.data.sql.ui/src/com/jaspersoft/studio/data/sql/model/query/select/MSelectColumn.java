package com.jaspersoft.studio.data.sql.model.query.select;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.query.AMQueryAliased;
import com.jaspersoft.studio.model.ANode;

public class MSelectColumn extends AMQueryAliased<MColumn> {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MSelectColumn(ANode parent, MColumn value) {
		super(parent, value, null);
	}

	public MSelectColumn(ANode parent, MColumn value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public String getToolTip() {
		MColumn mc = getValue();
		String tooltip = mc.toSQLString();
		tooltip += addAlias();
		tooltip += "\n" + mc.getTypeName();
		if (getValue().getRemarks() != null)
			tooltip += "\n" + mc.getRemarks();
		return tooltip;
	}


}