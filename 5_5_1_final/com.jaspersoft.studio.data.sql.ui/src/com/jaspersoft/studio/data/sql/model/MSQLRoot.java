package com.jaspersoft.studio.data.sql.model;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.utils.Misc;

public class MSQLRoot extends MRoot {

	public MSQLRoot(ANode parent, Object value) {
		super(parent, value);
	}

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private String iq = "";

	public String getIdentifierQuote() {
		return iq;
	}

	public void setIdentifierQuote(String iq) {
		this.iq = Misc.nvl(iq);
	}
}
