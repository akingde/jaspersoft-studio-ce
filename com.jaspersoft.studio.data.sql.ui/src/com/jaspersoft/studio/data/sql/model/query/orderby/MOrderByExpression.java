package com.jaspersoft.studio.data.sql.model.query.orderby;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MOrderByExpression extends AMOrderByMember<String> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MSelectExpression msExpression;

	public MOrderByExpression(ANode parent, MSelectExpression msExpression) {
		this(parent, msExpression.getValue());
		this.msExpression = msExpression;
	}

	public MOrderByExpression(ANode parent, String value) {
		this(parent, value, -1);
	}

	public MOrderByExpression(ANode parent, String value, int index) {
		super(parent, value, null, index);
	}

	public MSelectExpression getMSelectionExpression() {
		return msExpression;
	}

	public void setMSelectionExpression(MSelectExpression msExpression) {
		this.msExpression = msExpression;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString ss = new StyledString();
		if (msExpression != null) {
			if (msExpression.getAlias() != null)
				ss.append(msExpression.getAlias());
			else
				ss.append(msExpression.getValue());
		} else
			ss.append(super.getStyledDisplayText());
		return ss.append(addDirection(), FontUtils.KEYWORDS_STYLER);
	}

	@Override
	public String toSQLString() {
		StringBuffer ss = new StringBuffer();
		if (msExpression != null) {
			if (msExpression.getAlias() != null)
				ss.append(msExpression.getAlias());
			else
				ss.append(msExpression.getValue());
		} else
			ss.append(super.getStyledDisplayText());
		return ss.append(addDirection()).toString();
	}

	@Override
	public String getToolTip() {
		if (msExpression != null)
			return msExpression.getToolTip();
		return getValue();
	}

}
