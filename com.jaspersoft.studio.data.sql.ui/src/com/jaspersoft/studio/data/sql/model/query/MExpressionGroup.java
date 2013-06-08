package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MExpressionGroup extends AMKeyword {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MExpressionGroup(ANode parent) {
		super(parent, AMKeyword.AND_OPERATOR, null);
	}

	@Override
	public String getDisplayText() {
		String str = "";
		if (isFirst())
			str += "(";
		str += super.getDisplayText() + " (";
		if (getChildren().isEmpty())
			str += ")";
		return str;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString ss = new StyledString();
		if (isFirst())
			ss.append("(");
		else {
			ss.append(super.getDisplayText(), FontUtils.KEYWORDS_STYLER);
			ss.append(" (");
		}
		if (getChildren().isEmpty())
			ss.append(")");
		return ss;
	}
}
