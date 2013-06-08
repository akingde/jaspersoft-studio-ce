package com.jaspersoft.studio.data.sql.model.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.IQueryString;
import com.jaspersoft.studio.data.sql.model.enums.Operator;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MExpression extends ANode implements IQueryString {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String id;

	public MExpression(ANode parent, Object value, int newIndex) {
		super(parent, value, newIndex);
		id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MExpression && ((MExpression) obj).getId().equals(getId());
	}

	public int hashCode() {
		return getId().hashCode();
	};

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String toSQLString() {
		return "\n\t " + getDisplayText() + " ";
	}

	@Override
	public String getDisplayText() {
		String dt = "";
		if (!isFirst())
			dt += prevCond + " ";
		String[] ops = null;
		if (operator.getNrOperands() > 3) {
			ops = new String[] { "", "" };
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
		return dt + MessageFormat.format(operator.getFormat(operator), (Object[]) ops) + isLastInGroup(getParent(), this);
	}

	@Override
	public StyledString getStyledDisplayText() {
		String dt = getDisplayText();
		StyledString ss = new StyledString(dt);
		if (!isFirst())
			ss.setStyle(0, (prevCond + " ").length(), FontUtils.KEYWORDS_STYLER);
		if (operator.getNrOperands() != 2 || (operator.getNrOperands() == 2 && operator == Operator.LIKE)) {
			String sqlname = " " + operator.getSqlname() + " ";
			ss.setStyle(dt.indexOf(sqlname), sqlname.length(), FontUtils.KEYWORDS_STYLER);
		}
		if (operator.getNrOperands() == 3 && operator == Operator.BETWEEN)
			ss.setStyle(dt.indexOf(" AND "), " AND ".length(), FontUtils.KEYWORDS_STYLER);
		return ss;
	}

	private String isLastInGroup(ANode p, ANode child) {
		if (p == null)
			return "";
		String str = "";
		List<INode> ch = p.getChildren();
		if (p instanceof MExpressionGroup && ch.indexOf(child) == ch.size() - 1)
			str += ")" + isLastInGroup(p.getParent(), p);
		return str;
	}

	private String prevCond = AMKeyword.AND_OPERATOR;
	private List<AOperand> operands = new ArrayList<AOperand>();
	private Operator operator = Operator.EQUALS;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<AOperand> getOperands() {
		return operands;
	}

	public void setOperands(List<AOperand> operands) {
		this.operands = operands;
	}

	public String getPrevCond() {
		return prevCond;
	}

	public void setPrevCond(String prevCond) {
		this.prevCond = prevCond;
	}

}