package com.jaspersoft.studio.data.sql.model.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MExpression extends ANode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MExpression(ANode parent, Object value, int newIndex) {
		super(parent, value, newIndex);
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		String dt = "";
		if (isFirst())
			dt += prevCond + " ";
		String[] ops = null;
		if (operator.getNrOperands() > 3) {
			ops = new String[2];
			String sep = "";
			for (int i = 0; i < operands.size(); i++) {
				if (i == 0)
					ops[i] = operands.get(i).toSQLString();
				else {
					ops[1] += sep + operands.get(i).toSQLString();
					sep = ",";
				}
			}
		} else {
			ops = new String[operands.size()];
			for (int i = 0; i < ops.length; i++)
				ops[i] = operands.get(i).toSQLString();
		}
		return dt + MessageFormat.format(operator.getFormat(operator), (Object[]) ops);
	}

	@Override
	public StyledString getStyledDisplayText() {
		String dt = getDisplayText();
		StyledString ss = new StyledString(dt);
		if (isFirst())
			ss.setStyle(0, (prevCond + " ").length(), FontUtils.KEYWORDS_STYLER);
		if (operator.getNrOperands() != 2 || (operator.getNrOperands() == 2 && operator == Operator.LIKE)) {
			String sqlname = " " + operator.getSqlname() + " ";
			ss.setStyle(dt.indexOf(sqlname), sqlname.length(), FontUtils.KEYWORDS_STYLER);
		}
		if (operator.getNrOperands() == 3 && operator == Operator.BETWEEN) {
			ss.setStyle(dt.indexOf(" AND "), " AND ".length(), FontUtils.KEYWORDS_STYLER);
		}
		return ss;
	}

	private String prevCond = AMKeyword.AND_OPERATOR;
	private List<Operand> operands = new ArrayList<Operand>();
	private Operator operator = Operator.EQUALS;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Operand> getOperands() {
		return operands;
	}

	public void setOperands(List<Operand> operands) {
		this.operands = operands;
	}

	public String getPrevCond() {
		return prevCond;
	}

	public void setPrevCond(String prevCond) {
		this.prevCond = prevCond;
	}

	public boolean isFirst() {
		return getParent().getChildren().indexOf(this) != 0;
	}
}