package com.jaspersoft.studio.data.querydesigner.ejbql;

import java.util.Arrays;
import java.util.List;

import com.jaspersoft.studio.data.querydesigner.sql.SQLLineStyler;
import com.jaspersoft.studio.data.querydesigner.sql.SQLScanner;
import com.jaspersoft.studio.data.querydesigner.sql.SimpleSQLQueryDesigner;

/**
 * Simple query designer for EJB-QL that provides syntax highlighting.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class EJBQLQueryDesigner extends SimpleSQLQueryDesigner {

	private static List<String> ejbqlKeywords;
	
	@Override
	protected SQLLineStyler getSQLBasedLineStyler() {
		return new EJBQLLineStyler();
	}

	/*
	 * Class implementing a simple fuzzy scanner for EJB-QL.
	 */
	private class EJBQLScanner extends SQLScanner {
		
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
	
	/*
	 * Line style for EJB-QL language.
	 */
	private class EJBQLLineStyler extends SQLLineStyler {
		@Override
		protected SQLScanner getSQLBasedScanner() {
			return new EJBQLScanner();
		}
	}
}

