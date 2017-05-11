/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MUnion;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.expression.MExpression;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoinDetail;
import com.jaspersoft.studio.data.sql.model.query.groupby.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.operand.AOperand;
import com.jaspersoft.studio.data.sql.model.query.operand.FieldOperand;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public class MSQLRoot extends MRoot {

	public MSQLRoot(ANode parent, JRDesignDataset value) {
		super(parent, value);
	}

	@Override
	public JRDesignDataset getValue() {
		return (JRDesignDataset) super.getValue();
	}

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private String iq = "";
	private boolean quoteExceptions = true;

	public void setQuoteExceptions(boolean quoteExceptions) {
		this.quoteExceptions = quoteExceptions;
	}

	public boolean isQuoteExceptions() {
		return quoteExceptions;
	}

	public String getIdentifierQuote() {
		return iq;
	}

	public void setIdentifierQuote(String iq) {
		this.iq = Misc.nvl(iq);
	}

	private List<TableJoinDetail> joins = new ArrayList<TableJoinDetail>();

	public List<TableJoinDetail> getJoins() {
		return joins;
	}

	public void rebuildJoins() {
		joins.clear();

		new ModelVisitor<Object>(this) {

			@Override
			public boolean visit(INode n) {
				if (n instanceof MSelect)
					return false;
				if (n instanceof MWhere)
					return false;
				if (n instanceof MHaving)
					return false;
				if (n instanceof MGroupBy)
					return false;
				if (n instanceof MOrderBy)
					return false;
				if (n instanceof MUnion)
					return false;
				if (n instanceof MFromTable && n.getValue() instanceof MQueryTable)
					visit(((MQueryTable) n.getValue()).getSubquery());
				if (n instanceof MFromTableJoin && !((MFromTableJoin) n).getJoinKey().equals("ON")) {
					joins.add(new TableJoinDetail((MFromTable) n, (MFromTable) n.getParent(), null));
				} else if (n instanceof MExpression) {
					MExpression mexp = (MExpression) n;
					MFromTable src = null;
					MFromTable dest = null;
					if (mexp.getOperands().size() == 2) {
						for (AOperand op : mexp.getOperands())
							if (op instanceof FieldOperand) {
								if (src == null)
									src = ((FieldOperand) op).getFromTable();
								else if (dest == null)
									dest = ((FieldOperand) op).getFromTable();
							}
						if (src != null && dest != null)
							joins.add(new TableJoinDetail(src, dest, mexp));
					}
				}
				return true;
			}
		};
	}
}
