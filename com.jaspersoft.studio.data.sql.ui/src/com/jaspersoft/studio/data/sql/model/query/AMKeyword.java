package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class AMKeyword extends MDBObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String ALIAS_KEYWORD = " AS ";
	public static final String[] ALIAS_KEYWORDS = new String[] { ALIAS_KEYWORD, " " };

	public static final String[] JOIN_KEYWORDS = new String[] { "", " INNER JOIN ", " LEFT OUTER JOIN ", " RIGHT OUTER JOIN ", " FULL OUTER JOIN " };
	public static final String[] ORDER_KEYWORDS = new String[] { "", " ASC ", " DESC " };

	public AMKeyword(ANode parent, String value, String image) {
		super(parent, value, image);
	}

	@Override
	public StyledString getStyledDisplayText() {
		return new StyledString(getDisplayText(), FontUtils.KEYWORDS_STYLER);
	}
}
