/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.ejbql;

import java.util.Arrays;
import java.util.List;

import com.jaspersoft.studio.data.querydesigner.sql.text.SQLScanner;

/**
 * Class implementing a simple fuzzy scanner for EJB-QL.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class EJBQLScanner extends SQLScanner {
	
	private static List<String> ejbqlKeywords;
	
	@Override
	protected List<String> getSQLKeywords() {
		if (ejbqlKeywords == null) {
			ejbqlKeywords = Arrays.asList(new String[] { "as", "abs", "asc",
					"avg", "between", "both", "bit_length", "character_length",
					"char_length", "count", "concat", "current_time",
					"current_date", "current_timestamp", "delete", "desc",
					"distinct", "empty", "escape", "false", "fetch", "from",
					"group", "having", "is", "inner", "locate", "lower",
					"leading", "left", "length", "max", "member", "min", "mod",
					"new", "null", "object", "of", "order", "position",
					"select", "some", "sum", "size", "sqrt", "substr",
					"trailing", "true", "trim", "unknown", "update", "upper",
					"user", "where", "join", "all", "and", "any", "between",
					"by", "exists", "in", "like", "not", "null", "or" });
		}
		return ejbqlKeywords;
	}
	
}
